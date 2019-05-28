<%@page pageEncoding="UTF-8" %>

<%@page import="report.ReportDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="report.ReportAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
%>


<%
ReportDTO row = (ReportDTO)request.getAttribute("reportDTO");
if(row == null)
{
	row = new ReportDTO();
	
}
System.out.println("row = " + row);

GeoLocationDAO2 geoLocationDAO = new GeoLocationDAO2();

int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";
}
out.println("<td style='display:none;'><input type='hidden' id='failureMessage_" + i + "' value='" + failureMessage + "'/></td>");

String value = "";


											
		
											
											out.println("<td id = '" + i + "_name'>");
											out.println("name");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_nid'>");
											out.println("nid");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_phoneNumber'>");
											out.println("phone_number");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_address'>");
											out.println("address");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_addressDetails'>");
											out.println("address_details");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_mailId'>");
											out.println("mail_id");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_typeId'>");
											out.println("type_id");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_modelName'>");
											out.println("model_name");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_color'>");
											out.println("color");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineNumber'>");
											out.println("engine_number");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineType'>");
											out.println("engine_type");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_chassisNumber'>");
											out.println("chassis_number");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineCc'>");
											out.println("engine_cc");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_registrationNumber'>");
											out.println("registration_number");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_manufacturer'>");
											out.println("manufacturer");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_manufacturingYear'>");
											out.println("manufacturing_year");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_moreDetails'>");
											out.println("more_details");
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_reportingDate'>");
											value = row.reportingDate + "";
											SimpleDateFormat format_reportingDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_reportingDate = format_reportingDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_reportingDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_reporterId'>");
											value = row.reporterId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_vehicleId'>");
											value = row.vehicleId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_lostDate'>");
											value = row.lostDate + "";
											SimpleDateFormat format_lostDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_lostDate = format_lostDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_lostDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_foundDate'>");
											value = row.foundDate + "";
											SimpleDateFormat format_foundDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_foundDate = format_foundDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_foundDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_statusId'>");
											value = row.statusId + "";
											
											value = ReportAnotherDBDAO.getName (Language, Integer.parseInt(value) , "status");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_thanaAddress'>");
											value = row.thanaAddress + "";
											out.println(geoLocationDAO.getLocationText(Integer.parseInt(value)));
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_blog'>");
											value = row.blog + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_image1'>");
											value = row.image1 + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick="+ onclickFunc +">Edit</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

