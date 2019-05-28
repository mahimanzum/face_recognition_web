<%@page pageEncoding="UTF-8" %>

<%@page import="semen_distribution.Semen_distributionDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="semen_distribution.Semen_distributionAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_LANGUAGE, loginDTO);
%>


<%
Semen_distributionDTO row = (Semen_distributionDTO)request.getAttribute("semen_distributionDTO");
if(row == null)
{
	row = new Semen_distributionDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = Semen_distributionAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_noOfDose'>");
											value = row.noOfDose + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_requisitionId'>");
											value = row.requisitionId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_groupId'>");
											value = row.groupId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_transactionDate'>");
											value = row.transactionDate + "";
											SimpleDateFormat format_transactionDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_transactionDate = format_transactionDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_transactionDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



	
											out.println("<td id = '" + i + "_Edit'>");										
	
											out.println("<a href='Semen_distributionServlet?actionType=getEditPage&ID=" +row.iD + "'>Edit</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

