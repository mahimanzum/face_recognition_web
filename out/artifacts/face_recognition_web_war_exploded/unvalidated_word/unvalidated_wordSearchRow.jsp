<%@page pageEncoding="UTF-8" %>

<%@page import="unvalidated_word.Unvalidated_wordDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page import="pb.*"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.UNVALIDATED_WORD_EDIT_LANGUAGE, loginDTO);

Unvalidated_wordDTO row = (Unvalidated_wordDTO)request.getAttribute("unvalidated_wordDTO");
if(row == null)
{
	row = new Unvalidated_wordDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_word'>");
											value = row.word + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_verdictCount'>");
											value = row.verdictCount + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_isCorrect'>");
											value = row.isCorrect + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	

	

											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.UNVALIDATED_WORD_SEARCH_UNVALIDATED_WORD_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<div class='checker'>");
											out.println("<span id='chkEdit' ><input type='checkbox' name='ID' value='" + row.iD + "'/></span>");
											out.println("</div");
											out.println("</td>");%>

