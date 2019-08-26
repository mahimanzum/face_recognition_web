<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="pb_notifications.Pb_notificationsDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Pb_notificationsDTO pb_notificationsDTO = (Pb_notificationsDTO)request.getAttribute("pb_notificationsDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(pb_notificationsDTO == null)
{
	pb_notificationsDTO = new Pb_notificationsDTO();
	
}
System.out.println("pb_notificationsDTO = " + pb_notificationsDTO);

String actionName = "edit";


String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");
%>
























	













<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.PB_NOTIFICATIONS_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isSeen'>")%>
			
	
	<div class="form-inline" id = 'isSeen_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='isSeen' id = 'isSeen_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.isSeen).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isHidden'>")%>
			
	
	<div class="form-inline" id = 'isHidden_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='isHidden' id = 'isHidden_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.isHidden).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_source'>")%>
			
	
	<div class="form-inline" id = 'source_div_<%=i%>'>
		<input type='text' class='form-control'  name='source' id = 'source_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.source + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_destination'>")%>
			
	
	<div class="form-inline" id = 'destination_div_<%=i%>'>
		<input type='text' class='form-control'  name='destination' id = 'destination_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.destination + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_fromId'>")%>
			
	
	<div class="form-inline" id = 'fromId_div_<%=i%>'>
		<input type='text' class='form-control'  name='fromId' id = 'fromId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.fromId + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_toId'>")%>
			
	
	<div class="form-inline" id = 'toId_div_<%=i%>'>
		<input type='text' class='form-control'  name='toId' id = 'toId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.toId + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_text'>")%>
			
	
	<div class="form-inline" id = 'text_div_<%=i%>'>
		<input type='text' class='form-control'  name='text' id = 'text_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.text + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_url'>")%>
			
	
	<div class="form-inline" id = 'url_div_<%=i%>'>
		<input type='url' class='form-control'  name='url' id = 'url_url_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.url + "'"):("'" + "" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_entryDate'>")%>
			
	
	<div class="form-inline" id = 'entryDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(pb_notificationsDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.entryDate + "'"):("'" + "0" + "'")%> >
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_seenDate'>")%>
			
	
	<div class="form-inline" id = 'seenDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='seenDate_Date_<%=i%>' id = 'seenDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_seenDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_seenDate = format_seenDate.format(new Date(pb_notificationsDTO.seenDate)).toString();
	out.println("'" + formatted_seenDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='seenDate' id = 'seenDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.seenDate + "'"):("'" + "0" + "'")%> >
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_showingDate'>")%>
			
	
	<div class="form-inline" id = 'showingDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='showingDate_Date_<%=i%>' id = 'showingDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_showingDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_showingDate = format_showingDate.format(new Date(pb_notificationsDTO.showingDate)).toString();
	out.println("'" + formatted_showingDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='showingDate' id = 'showingDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.showingDate + "'"):("'" + "0" + "'")%> >
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sendAlarm'>")%>
			
	
	<div class="form-inline" id = 'sendAlarm_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='sendAlarm' id = 'sendAlarm_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendAlarm).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sendSms'>")%>
			
	
	<div class="form-inline" id = 'sendSms_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='sendSms' id = 'sendSms_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendSms).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sendMail'>")%>
			
	
	<div class="form-inline" id = 'sendMail_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='sendMail' id = 'sendMail_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendMail).equals("true"))?("checked"):""%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="sendMail must be a of valid email address format"
><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sendPush'>")%>
			
	
	<div class="form-inline" id = 'sendPush_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='sendPush' id = 'sendPush_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendPush).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_mailSent'>")%>
			
	
	<div class="form-inline" id = 'mailSent_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='mailSent' id = 'mailSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.mailSent).equals("true"))?("checked"):""%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="mailSent must be a of valid email address format"
><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_smsSent'>")%>
			
	
	<div class="form-inline" id = 'smsSent_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='smsSent' id = 'smsSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.smsSent).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_pushSent'>")%>
			
	
	<div class="form-inline" id = 'pushSent_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='pushSent' id = 'pushSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.pushSent).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + pb_notificationsDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	