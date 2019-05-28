package monthly_candidate_bull_production_report;


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
import common.RequestFailureException;
import language.LC;
import language.LM;

import java.util.*;
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.JSPConstant;
import pbReport.*;






@WebServlet("/Monthly_candidate_bull_production_report_Servlet")
public class Monthly_candidate_bull_production_report_Servlet  extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ReportTemplate reportTemplate = new ReportTemplate();
	
	String Criteria[][] = 
	{
	};
	
	String Display[][] = 
	{
		{"display","","source_name","text",""},		
		{"display","","total_candidate_bulls","text",""}		
	};
	
	String GroupBy = "";
	String OrderBY = "";
	
	public Monthly_candidate_bull_production_report_Servlet(){

	}
	
	private ReportService reportService = new ReportService();
	
	private String sql;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		
		long l1stDayOfMonth = DateUtils.get1stDayOfMonth();
		long l1stDayOfJuly = DateUtils.get1stDayOfJuly();
		System.out.println("1st day of month " + l1stDayOfMonth);  
		System.out.println("1st day of july " + l1stDayOfJuly);  
		
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter("actionType");
		ApiResponse apiResponse = null;
		System.out.println("In ssservlet doget, actiontype = " + actionType);
		
		sql = "(SELECT source.name_en AS source_name,"
				+ " SUM(number_of_candidate_bulls) AS total_candidate_bulls"
				+ " FROM candidate_bull_production"
				+ " JOIN source ON candidate_bull_production.source_type = source.id"
				+ " WHERE production_date >= " + l1stDayOfMonth 
				+ " GROUP BY source_type)"
				+ " AS derived";

		Display[0][4] = LM.getText(LC.MONTHLY_CANDIDATE_BULL_PRODUCTION_REPORT_SELECT_SOURCENAME, loginDTO);
		Display[1][4] = LM.getText(LC.MONTHLY_CANDIDATE_BULL_PRODUCTION_REPORT_SELECT_TOTALCANDIDATEBULLS, loginDTO);

		
		if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.MONTHLY_CANDIDATE_BULL_PRODUCTION_REPORT_DETAILS))
		{
			
			if(ActionTypeConstant.REPORT_PAGE.equals(actionType)){
				
				request.getRequestDispatcher("/monthly_candidate_bull_production_report/monthly_candidate_bull_production_report.jsp").forward(request, response);
				
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
					List list  = getReport(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(list,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_TEMPLATE.equals(actionType)){
				
				apiResponse = ApiResponse.makeSuccessResponse(reportTemplate,"Success");
				
			}
			

			PrintWriter writer = response.getWriter();
			writer.write(new Gson().toJson(apiResponse));
			writer.flush();
			writer.close();
			
		}
//		else{
//			request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
//		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		

		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter("actionType");
		System.out.println("In ssservlet dopost, actiontype = " + actionType);
		
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
					List list  = getReport(request, response);
					apiResponse = ApiResponse.makeSuccessResponse(list,"Success");
				}catch(Exception ex){
					apiResponse = ApiResponse.makeErrorResponse(ex.getMessage());
					if(ex instanceof RequestFailureException){
						throw (RequestFailureException)ex;
					}
					throw new RuntimeException(ex);
				}
			}else if(ActionTypeConstant.REPORT_TEMPLATE.equals(actionType)){
				
				
				reportTemplate = createReportTemplate(request);
				
				apiResponse = ApiResponse.makeSuccessResponse(null,"Success");
				
			}
			else if(actionType.equals("getGeo"))
			{
				System.out.println("going to geoloc ");
				request.getRequestDispatcher("geolocation/geoloc.jsp").forward(request, response);
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
			criteriaColumnString += criteraiColumn;
		}
		
		ReportTemplate reportTemplate = new ReportTemplate();
		
		reportTemplate.reportDisplay = displayColumnString;
		reportTemplate.reportCriteria = criteriaColumnString;
		reportTemplate.reportOrder = orderByColumnString;
		
		return reportTemplate;
		
	}

	private int getTotalCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int count = reportService.getTotalCount(sql, request, Criteria, Display, GroupBy, OrderBY);
		return count;
	}
	
	private List<List<Object>> getReport(HttpServletRequest request, HttpServletResponse response) throws Exception{

		Integer recordPerPage = Integer.parseInt(request.getParameter("RECORDS_PER_PAGE"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageno"));
		System.out.println("recordPerPage = " + recordPerPage + " pageNo = " + pageNo);
		int offset = (pageNo-1)*recordPerPage;
		return reportService.getResultSet(sql, request, recordPerPage,offset, Criteria, Display, GroupBy, OrderBY);
	}

}
