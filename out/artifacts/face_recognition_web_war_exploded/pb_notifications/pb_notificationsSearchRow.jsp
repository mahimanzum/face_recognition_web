<%@page pageEncoding="UTF-8" %>

<%@page import="pb_notifications.Pb_notificationsDTO"%>
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
String Language = LM.getText(LC.PB_NOTIFICATIONS_EDIT_LANGUAGE, loginDTO);

Pb_notificationsDTO row = (Pb_notificationsDTO)request.getAttribute("pb_notificationsDTO");
if(row == null)
{
	row = new Pb_notificationsDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_isSeen'>");
											value = row.isSeen + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_isHidden'>");
											value = row.isHidden + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_source'>");
											value = row.source + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_destination'>");
											value = row.destination + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_fromId'>");
											value = row.fromId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_toId'>");
											value = row.toId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_text'>");
											value = row.text + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_url'>");
											value = row.url + "";
											out.println("<a href='Pb_notificationsServlet?actionType=getURL&URL=" + value + "'>Link</a>");
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_entryDate'>");
											value = row.entryDate + "";
											SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_entryDate = format_entryDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_entryDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_seenDate'>");
											value = row.seenDate + "";
											SimpleDateFormat format_seenDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_seenDate = format_seenDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_seenDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_showingDate'>");
											value = row.showingDate + "";
											SimpleDateFormat format_showingDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_showingDate = format_showingDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_showingDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sendAlarm'>");
											value = row.sendAlarm + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sendSms'>");
											value = row.sendSms + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sendMail'>");
											value = row.sendMail + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sendPush'>");
											value = row.sendPush + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_mailSent'>");
											value = row.mailSent + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_smsSent'>");
											value = row.smsSent + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_pushSent'>");
											value = row.pushSent + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.PB_NOTIFICATIONS_SEARCH_PB_NOTIFICATIONS_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

