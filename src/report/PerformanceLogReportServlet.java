package report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.ApiResponse;
import common.PerformanceLog;
import common.RequestFailureException;

import java.util.*;
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.JSPConstant;
import util.SqlPair;




class ReportTemplate{
	int menuID;
	String reportCriteria;
	String reportDisplay;
	String reportOrder;
	boolean isSinglePageReport;
}


@WebServlet("/Report/performanceLogServlet")
public class PerformanceLogReportServlet  extends HttpServlet {
	ReportTemplateDAO reportTemplateDAO = new ReportTemplateDAO();
	ReportTemplate reportTemplate = new ReportTemplate();
	
	SqlPair sqlPair = null;
	
	ReportMetadata reportMetadata = null;
	
	public PerformanceLogReportServlet(){
		reportTemplate.reportDisplay="display.PerformanceLog.ipAddress=Ip Address,display.user.userName=User Name";
		reportTemplate.reportCriteria = "criteria.PerformanceLog.URI.eq,criteria.user.userName.like";
		reportTemplate.reportOrder = "user.userName";
		reportTemplate.isSinglePageReport = true;
	}
	
	private ReportService reportService = new ReportService();
	private Map<String,Class> mapOfClassToString = new HashMap<String,Class>(){{
		put("PerformanceLog", PerformanceLog.class);
		put("user", UserDTO.class);
	}};
	
	private String sql = " performance_log join user on (performance_log.userID = user.ID) ";
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter("actionType");
		ApiResponse apiResponse = null;
		
		if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.REPORT_PAGE)){
			
			if(ActionTypeConstant.REPORT_PAGE.equals(actionType)){
				
				request.getRequestDispatcher(JSPConstant.PERFORMANCE_LOG_REPORT).forward(request, response);
				
				return;
				
			}else if(ActionTypeConstant.REPORT_PAGE_SUMMARY.equals(actionType)){
				
				request.getRequestDispatcher(JSPConstant.PERFORMANCE_LOG_SUMMARY_REPORT).forward(request, response);
				
				return;
				
			}else if(ActionTypeConstant.REPORT_COUNT.equals(actionType)){
				try{
					int count = getTotalCount(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(count,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_RESULT.equals(actionType)){
				try{
					List<?> list  = getReport(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(list,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_TEMPLATE.equals(actionType)){
				
				ReportTemplate reportTemplate = reportTemplateDAO.getReportTemplateByMenuID(MenuConstants.REPORT_PAGE);
				
				apiResponse = ApiResponse.makeSuccessResponse(reportTemplate,"Success");
				
			}else if(ActionTypeConstant.REPORT_PAGINATION.equals(actionType)){
				try{
					Object object = getPaginatedReport(request);
					apiResponse = ApiResponse.makeSuccessResponse(object,"Success");
				}catch(Exception ex){
					throw new RuntimeException(ex);
				}
			}
						

			PrintWriter writer = response.getWriter();
			writer.write(new Gson().toJson(apiResponse));
			writer.flush();
			writer.close();
			
		}else{
			request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		

		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter("actionType");
		
		ApiResponse apiResponse = null;
		
		if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.REPORT_PAGE)){
			
			if(ActionTypeConstant.REPORT_COUNT.equals(actionType)){
				try{
					int count = getTotalCount(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(count,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_RESULT.equals(actionType)){
				try{
					List<?> list  = getReport(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(list,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_TEMPLATE.equals(actionType)){
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.REPORT_PERFORMANCE_LOG_TEMPLATE_UPDATE)){
					reportTemplate = createReportTemplate(request);
					
					int count = reportTemplateDAO.updateReport(reportTemplate);
					if(count == 0){
						reportTemplateDAO.insert(reportTemplate);
					}
					
					apiResponse = ApiResponse.makeSuccessResponse(null,"Success");
				}else{
					apiResponse = ApiResponse.makeErrorResponse("You do not have permission to save this template.");
				}
			}
			
			PrintWriter writer = response.getWriter();
			writer.write(new Gson().toJson(apiResponse));
			writer.flush();
			writer.close();
			
		}else{
			request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
		}
	}
	
	private ReportTemplate createReportTemplate(HttpServletRequest request) {
		
		String[] orderByColumns = request.getParameterValues("orderByColumns");
		List<String> displayColumns = new ArrayList<>();
		List<String> criteriaColumns = new ArrayList<>();
		
		for(String parameterName:request.getParameterMap().keySet()){
			if(parameterName.startsWith("display")){
				displayColumns.add(parameterName+"="+request.getParameter(parameterName));
			}
			if(parameterName.startsWith("criteria")){
				criteriaColumns.add(parameterName);
			}
		}
		String orderByColumnString = "";
		String displayColumnString = "";
		String criteriaColumnString = "";
		if(orderByColumns!=null){
			for(int i=0;i<orderByColumns.length;i++){
				if(i!=0){
					orderByColumnString+=",";
				}
				String orderByColumn = orderByColumns[i];
				orderByColumnString+=orderByColumn;
			}
		}
		for(int i=0;i<displayColumns.size();i++){
			if(i!=0){
				displayColumnString+=",";
			}
			String displayColumn = displayColumns.get(i); 
			displayColumnString+=displayColumn;
		}
		for(int i=0;i<criteriaColumns.size();i++){
			if(i!=0){
				criteriaColumnString+=",";
			}
			String criteraiColumn = criteriaColumns.get(i);
			if(!criteraiColumn.endsWith("leq")){
				criteriaColumnString += criteraiColumn;
			}
		}
		
		ReportTemplate reportTemplate = new ReportTemplate();
		
		reportTemplate.reportDisplay = displayColumnString;
		reportTemplate.reportCriteria = criteriaColumnString;
		reportTemplate.reportOrder = orderByColumnString;
		reportTemplate.isSinglePageReport = (request.getParameter("isSinglePageReport")!=null);
		reportTemplate.menuID = MenuConstants.REPORT_PAGE;
		
		return reportTemplate;
		
	}

	private int getTotalCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int count = reportService.getTotalCount(mapOfClassToString, sql, request);
		return count;
	}
	
	private List<List<Object>> getReport(HttpServletRequest request, HttpServletResponse response) throws Exception{

		Integer recordPerPage = Integer.parseInt(request.getParameter("RECORDS_PER_PAGE"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageno"));
		int offset = (pageNo-1)*recordPerPage;
		
		reportMetadata = new ReportMetadata(mapOfClassToString, request);
		
		return reportService.getResultSet(reportMetadata,sql,recordPerPage, offset);
	}
	
	private List<List<Object>> getPaginatedReport(HttpServletRequest request) throws Exception{
		int recordPerPage = Integer.parseInt(request.getParameter("recordPerPage"));
		int currentPageNo = Integer.parseInt(request.getParameter("currentPageNo"));
		int offset = (currentPageNo-1)*recordPerPage;
		return reportService.getResultSet(reportMetadata,sql,recordPerPage, offset);
	}

}
