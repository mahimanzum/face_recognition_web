<%@page pageEncoding="UTF-8" %>

<%@page import="artificial_insemenation_record.Artificial_insemenation_recordDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="artificial_insemenation_record.Artificial_insemenation_recordAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_LANGUAGE, loginDTO);
%>


<%
Artificial_insemenation_recordDTO row = (Artificial_insemenation_recordDTO)request.getAttribute("artificial_insemenation_recordDTO");
if(row == null)
{
	row = new Artificial_insemenation_recordDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = Artificial_insemenation_recordAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = Artificial_insemenation_recordAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_noOfAI'>");
											value = row.noOfAI + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_entryDate'>");
											value = row.entryDate + "";
											SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_entryDate = format_entryDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_entryDate);
				
			
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

