package report;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import annotation.ColumnName;
import annotation.TableName;
import common.ApiResponse;
import common.PerformanceLog;
import common.RequestFailureException;
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.JSPConstant;
import util.ModifiedSqlGenerator;
import util.SqlPair;
import report.ReportParameterDTO;

@WebServlet("/Report/reportServlet")
public class ReportServlet extends HttpServlet {
	ReportTemplateDAO reportTemplateDAO = new ReportTemplateDAO();
	ReportTemplate reportTemplate = new ReportTemplate();
	
	//List<?> reportFieldList;
	
//	private String sql = " performance_log join user on (performance_log.userID = user.ID) ";
	
	
	
	private Map<Integer,String> mapOfJoinSqlToMenuID = new HashMap<>();
	private Map<Integer,List<?>> mapOfReportFieldListToMenuID = new HashMap<>();
	private Map<Integer,Map<String,Class>> mapOfClassToStringToMenuID = new HashMap<>();
	
	
	ReportMetadata reportMetadata = null;
	

	private ReportService reportService = new ReportService();
	
	//private Map<String,Class> mapOfClassToString = new HashMap<String,Class>(){{put("PerformanceLog", PerformanceLog.class);put("user", UserDTO.class);}};
	
	
	
	
	private int getMenuID(HttpServletRequest request) throws ServletException,IOException{
		String menuIDString = request.getParameter("menuID");
		if(menuIDString==null){
			throw new RuntimeException("No Menu ID found.");
		}
		return Integer.parseInt(menuIDString);
		
	}
	
	private void checkPermission(int menuID){}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int menuID = getMenuID(request);
		checkPermission(menuID);
		
		
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter("actionType");
		ApiResponse apiResponse = null;
		
		if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.REPORT_PAGE)){
			
			if(ActionTypeConstant.REPORT_PAGE.equals(actionType)){
				
				
				
				request.setAttribute("reportFieldList", mapOfReportFieldListToMenuID.get(menuID));
				request.getRequestDispatcher(JSPConstant.REPORT).forward(request, response);
				
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
			}else if(ActionTypeConstant.DTO_FIELD_LIST.equals(actionType)){
				
				String dtoName = request.getParameter("dtoName");
				
				getColumnListByDTOName(dtoName, request, response);
				return;
			}else if(ActionTypeConstant.DEFAULT_FIELD_LIST.equals(actionType)){
				getReportParameterList(request, response);
				return;
			}
						
			sendJson(response, apiResponse);
			
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
			}else if(ActionTypeConstant.DEFAULT_FIELD_LIST.equals(actionType)){
				try{
					saveReportFields(request, response);
				}catch(Exception ex){
					throw new RuntimeException(ex);
				}
				return;
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
		
		int menuID = getMenuID(request);
		
		int count = reportService.getTotalCount(mapOfClassToStringToMenuID.getOrDefault(menuID, Collections.emptyMap()
				), mapOfJoinSqlToMenuID.get(menuID), request);
		return count;
	}
	
	private List<List<Object>> getReport(HttpServletRequest request, HttpServletResponse response) throws Exception{

		Integer recordPerPage = Integer.parseInt(request.getParameter("RECORDS_PER_PAGE"));
		Integer pageNo = Integer.parseInt(request.getParameter("pageno"));
		int offset = (pageNo-1)*recordPerPage;
		int menuID = getMenuID(request);
		
		reportMetadata = new ReportMetadata(mapOfClassToStringToMenuID.getOrDefault(menuID, Collections.emptyMap()), request);
		
		return reportService.getResultSet(reportMetadata,mapOfJoinSqlToMenuID.get(menuID),recordPerPage, offset);
	}
	
	private List<List<Object>> getPaginatedReport(HttpServletRequest request) throws Exception{
		int recordPerPage = Integer.parseInt(request.getParameter("recordPerPage"));
		int currentPageNo = Integer.parseInt(request.getParameter("currentPageNo"));
		int offset = (currentPageNo-1)*recordPerPage;
		int menuID = getMenuID(request);
		return reportService.getResultSet(reportMetadata,mapOfJoinSqlToMenuID.get(menuID),recordPerPage, offset);
	}
	
	
	
	private void sendJson(HttpServletResponse response,Object object) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.write(new Gson().toJson(object));
		writer.flush();
		writer.close();
	}
	
	private boolean isAnnotatedWithTableNameAnnotation(Class<?> classObject) throws Exception{
		return classObject.getAnnotation(TableName.class)!=null;
	}
	
	private boolean isAnnotatedWithColumnName(Field field) throws Exception{
		field.setAccessible(true);
		return field.getAnnotation(ColumnName.class)!=null;
	}
	
	
	private void getColumnListByDTOName(String dtoNames,HttpServletRequest request
			,HttpServletResponse response) throws IOException{
		try{
			
			String[] dtoNameArray = dtoNames.split(",");
			List<String> fieldNameList = new ArrayList<>();
			for(String dtoName:dtoNameArray){
				Class<?> classObject = Class.forName(dtoName);
				
				if(!isAnnotatedWithTableNameAnnotation(classObject)){
					throw new Exception("The dto "+dtoName+" is not annotated with @TableName");
				}
				
				for(Field field: classObject.getDeclaredFields()){
					field.setAccessible(true);
					if(isAnnotatedWithColumnName(field)){
						fieldNameList.add(dtoName+"."+field.getName());
					}
				}
			}
			sendJson(response, ApiResponse.makeSuccessResponse(fieldNameList,""));
		}catch(Exception ex){
			sendJson(response, ApiResponse.makeErrorResponse(ex.toString()));
		}
	}
	
	
	private void saveReportFields(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<ReportParameterDTO> reportParameterDTOs = new ArrayList<>();
		int menuID = getMenuID(request);
		
		Map<String,Class> mapOfClassToSimpleName = new HashMap<>();
		
		Set<String> set = new TreeSet<>();
		
		for(String paramName:request.getParameterMap().keySet()){
			
			
			if(paramName.equals("dtoName")){
				
				String dtoNames = request.getParameter(paramName);
				
				for(String dtoName:dtoNames.split(",")){
					Class<?> classObject = Class.forName(dtoName);
					mapOfClassToSimpleName.put(classObject.getSimpleName(),classObject);
				} 
				
				continue;
			}
			
			
			String[] paramValues = request.getParameterValues(paramName);
			
			
			
			if(paramValues!=null && paramValues.length==4){
				ReportParameterDTO reportParameterDTO = new ReportParameterDTO();
				reportParameterDTO.menuID = menuID;
				reportParameterDTO.dataComment = paramValues[1];
				reportParameterDTO.canonicalFullName = paramName;
				reportParameterDTO.userDefinedFullName = paramValues[0];
				reportParameterDTO.dataValue = paramValues[2];
				reportParameterDTO.operatorType = paramValues[3];
				if(paramValues[3].trim().length()!=0){
					try{
						// check operator types
						reportParameterDTOs.add(reportParameterDTO);
						//mapOfClassToSimpleName.put(reportParameterDTO.getClassName(), reportParameterDTO.getClassObject());
						
						
						
						//sql = ModifiedSqlGenerator.getTableName(reportParameterDTO.getClassObject());
					}catch(Exception ex){
						throw new RuntimeException(ex);
					}
				}
				set.add(reportParameterDTO.getClassObject().getCanonicalName());
			}
		}
		
		
		mapOfReportFieldListToMenuID.put(menuID, reportParameterDTOs);
		
		//reportFieldList = reportParameterDTOs;
		
		
		List<Class<?>> classObjectList = new ArrayList<>();
		for(String className: set){
			classObjectList.add(Class.forName(className));
		}
		
		
		
		
		String joinSql = ModifiedSqlGenerator.getJoinSqlByClassObjectList(classObjectList);
		mapOfJoinSqlToMenuID.put(menuID, joinSql);
		
		mapOfClassToStringToMenuID.put(menuID, mapOfClassToSimpleName);
		
	}
	
	
	class ReportParameterObject{
		public String dotNames;
		public Object reportParameters;
	}
	
	
	private void getReportParameterList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{

		
		
		
		int menuID = getMenuID(request);
		
		Map<String,Class> mapOfClassObjectToSimapleName = mapOfClassToStringToMenuID.getOrDefault(menuID, Collections.emptyMap());
		String dtoNames = "";
		int index = 0;
		for(Class classObject:mapOfClassObjectToSimapleName.values()){
			if(index!=0){
				dtoNames+=",";
			}
			dtoNames+=classObject.getCanonicalName();
			index++;
		}
		
		
		ReportParameterObject reportParameterObject = new ReportParameterObject();
		reportParameterObject.dotNames = dtoNames;
		reportParameterObject.reportParameters = mapOfReportFieldListToMenuID.get(menuID);
		
		sendJson(response, ApiResponse.makeSuccessResponse(reportParameterObject,"Success"));
	}
	
	
	public static void main(String[] args) throws Exception{
		String sql = ModifiedSqlGenerator.getJoinSqlByClassObjectList(Arrays.asList(UserDTO.class,PerformanceLog.class));
		System.out.println(sql);
	}
	

}
