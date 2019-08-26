<%@page pageEncoding="UTF-8" %>

<%@page import="permissible_bulls_in_centre.Permissible_bulls_in_centreDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="permissible_bulls_in_centre.Permissible_bulls_in_centreAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_LANGUAGE, loginDTO);
%>


<%
Permissible_bulls_in_centreDTO row = (Permissible_bulls_in_centreDTO)request.getAttribute("permissible_bulls_in_centreDTO");
if(row == null)
{
	row = new Permissible_bulls_in_centreDTO();
	
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
											
											value = Permissible_bulls_in_centreAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = Permissible_bulls_in_centreAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dateOfEntry'>");
											value = row.dateOfEntry + "";
											SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfEntry = format_dateOfEntry.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfEntry);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dateOfExpiration'>");
											value = row.dateOfExpiration + "";
											SimpleDateFormat format_dateOfExpiration = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfExpiration = format_dateOfExpiration.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfExpiration);
				
			
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

