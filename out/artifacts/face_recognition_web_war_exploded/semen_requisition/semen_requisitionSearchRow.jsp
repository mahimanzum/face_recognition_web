<%@page pageEncoding="UTF-8" %>

<%@page import="semen_requisition.Semen_requisitionDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="semen_requisition.Semen_requisitionAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.SEMEN_REQUISITION_EDIT_LANGUAGE, loginDTO);
%>


<%
Semen_requisitionDTO row = (Semen_requisitionDTO)request.getAttribute("semen_requisitionDTO");
if(row == null)
{
	row = new Semen_requisitionDTO();
	
}
System.out.println("row = " + row);


int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";
}
out.println("<td style='display:none;'><input type='hidden' id='failureMessage_" + i + "' value='" + failureMessage + "'/></td>");

String value = "";


											
		
											
											out.println("<td id = '" + i + "_groupId'>");
											value = row.groupId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = Semen_requisitionAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_breedType'>");
											value = row.breedType + "";
											
											value = Semen_requisitionAnotherDBDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_noOfDose'>");
											value = row.noOfDose + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_requisitionDate'>");
											value = row.requisitionDate + "";
											SimpleDateFormat format_requisitionDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_requisitionDate = format_requisitionDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_requisitionDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_isDelivered'>");
											value = row.isDelivered + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick="+ onclickFunc +">Edit</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

