<%@page pageEncoding="UTF-8" %>

<%@page import="facial_recognization.Facial_recognizationDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>
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
String Language = LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_LANGUAGE, loginDTO);

Facial_recognizationDTO row = (Facial_recognizationDTO)request.getAttribute("facial_recognizationDTO");
if(row == null)
{
	row = new Facial_recognizationDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_name'>");
											value = row.name + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_address'>");
											value = row.address + "";
											out.println(GeoLocationDAO2.parseText(value));
											{
												String addressdetails = GeoLocationDAO2.parseDetails(value);
												if(!addressdetails.equals(""))
												{
													out.println(", " + addressdetails);
												}
											}
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_phone'>");
											value = row.phone + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_email'>");
											value = row.email + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_image'>");
											value = row.image + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
											out.println("</td>");
		
											
		
	

	

											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.FACIAL_RECOGNIZATION_SEARCH_FACIAL_RECOGNIZATION_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<div class='checker'>");
											out.println("<span id='chkEdit' ><input type='checkbox' name='ID' value='" + row.iD + "'/></span>");
											out.println("</div");
											out.println("</td>");%>

