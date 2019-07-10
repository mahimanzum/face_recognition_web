<%@page pageEncoding="UTF-8" %>

<%@page import="apa_target.Apa_targetDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.APA_TARGET_EDIT_LANGUAGE, loginDTO);

Apa_targetDTO row = (Apa_targetDTO)request.getAttribute("apa_targetDTO");
if(row == null)
{
	row = new Apa_targetDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_semenCollection'>");
											value = row.semenCollection + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_artificialInsemenation'>");
											value = row.artificialInsemenation + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_progeny'>");
											value = row.progeny + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_candidateBullProduction'>");
											value = row.candidateBullProduction + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_entryDate'>");
											value = row.entryDate + "";
											SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_entryDate = format_entryDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_entryDate);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.APA_TARGET_SEARCH_APA_TARGET_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

