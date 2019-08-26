
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="pb_notifications.Pb_notificationsDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Pb_notificationsDTO pb_notificationsDTO;
pb_notificationsDTO = (Pb_notificationsDTO)request.getAttribute("pb_notificationsDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(pb_notificationsDTO == null)
{
	pb_notificationsDTO = new Pb_notificationsDTO();
	
}
System.out.println("pb_notificationsDTO = " + pb_notificationsDTO);

String actionName;
System.out.println("actionType = " + request.getParameter("actionType"));
if (request.getParameter("actionType").equalsIgnoreCase("getAddPage"))
{
	actionName = "add";
}
else
{
	actionName = "edit";
}
String formTitle;
if(actionName.equals("edit"))
{
	formTitle = LM.getText(LC.PB_NOTIFICATIONS_EDIT_PB_NOTIFICATIONS_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.PB_NOTIFICATIONS_ADD_PB_NOTIFICATIONS_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;
%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="Pb_notificationsServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	













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



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_ISSEEN, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_ISSEEN, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'isSeen_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='isSeen' id = 'isSeen_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.isSeen).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_ISHIDDEN, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_ISHIDDEN, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'isHidden_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='isHidden' id = 'isHidden_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.isHidden).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SOURCE, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SOURCE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'source_div_<%=i%>'>	
		<input type='text' class='form-control'  name='source' id = 'source_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.source + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_DESTINATION, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_DESTINATION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'destination_div_<%=i%>'>	
		<input type='text' class='form-control'  name='destination' id = 'destination_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.destination + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_FROMID, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_FROMID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'fromId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='fromId' id = 'fromId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.fromId + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_TOID, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_TOID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'toId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='toId' id = 'toId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.toId + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_TEXT, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_TEXT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'text_div_<%=i%>'>	
		<input type='text' class='form-control'  name='text' id = 'text_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.text + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_URL, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_URL, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'url_div_<%=i%>'>	
		<input type='url' class='form-control'  name='url' id = 'url_url_<%=i%>' value=<%=actionName.equals("edit")?("'" + pb_notificationsDTO.url + "'"):("'" + "" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_ENTRYDATE, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_ENTRYDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'entryDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SEENDATE, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SEENDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'seenDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SHOWINGDATE, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SHOWINGDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'showingDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDALARM, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SENDALARM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'sendAlarm_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='sendAlarm' id = 'sendAlarm_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendAlarm).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDSMS, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SENDSMS, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'sendSms_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='sendSms' id = 'sendSms_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendSms).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDMAIL, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SENDMAIL, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'sendMail_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='sendMail' id = 'sendMail_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendMail).equals("true"))?("checked"):""%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="sendMail must be a of valid email address format"
><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDPUSH, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SENDPUSH, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'sendPush_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='sendPush' id = 'sendPush_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.sendPush).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_MAILSENT, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_MAILSENT, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'mailSent_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='mailSent' id = 'mailSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.mailSent).equals("true"))?("checked"):""%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="mailSent must be a of valid email address format"
><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_SMSSENT, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_SMSSENT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'smsSent_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='smsSent' id = 'smsSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.smsSent).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PB_NOTIFICATIONS_EDIT_PUSHSENT, loginDTO)):(LM.getText(LC.PB_NOTIFICATIONS_ADD_PUSHSENT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'pushSent_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='pushSent' id = 'pushSent_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(pb_notificationsDTO.pushSent).equals("true"))?("checked"):""%> ><br>						


	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + pb_notificationsDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PB_NOTIFICATIONS_EDIT_PB_NOTIFICATIONS_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PB_NOTIFICATIONS_ADD_PB_NOTIFICATIONS_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PB_NOTIFICATIONS_EDIT_PB_NOTIFICATIONS_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PB_NOTIFICATIONS_ADD_PB_NOTIFICATIONS_SUBMIT_BUTTON, loginDTO));
					}
					%>
					</button>
				</div>
							
			</div>
		
		</form>

	</div>
</div>

<script src="nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">


function getLayer(layernum, layerID, childLayerID, selectedValue)
{
	console.log("getting layer");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText.includes('option'))
			{
				console.log("got response");
				document.getElementById(childLayerID).innerHTML = this.responseText ;
			}
			else
			{
				console.log("got errror response");
			}
			
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	xhttp.open("POST", "Pb_notificationsServlet?actionType=getGRSLayer&layernum=" + layernum + "&layerID=" 
			+ layerID + "&childLayerID=" + childLayerID + "&selectedValue=" + selectedValue, true);
	xhttp.send();
}

function layerselected(layernum, layerID, childLayerID, hiddenInput, hiddenInputForTopLayer)
{
	var layervalue = document.getElementById(layerID).value;
	console.log("layervalue = " + layervalue);
	document.getElementById(hiddenInput).value = layervalue;
	if(layernum == 0)
	{
		document.getElementById(hiddenInputForTopLayer).value = layervalue;
	}
	if(layernum == 0 || (layernum == 1 && document.getElementById(hiddenInputForTopLayer).value == 3))
	{
		document.getElementById(childLayerID).setAttribute("style", "display: inline;");
		getLayer(layernum, layerID, childLayerID, layervalue);
	}
	
}

function isNumeric(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function PreprocessBeforeSubmiting(row, validate)
{
	if(validate == "report")
	{
	}
	else
	{
		var empty_fields = "";
		var i = 0;
		if(!document.getElementById('sendMail_checkbox_' + row).checkValidity())
		{
			empty_fields += "'sendMail'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('mailSent_checkbox_' + row).checkValidity())
		{
			empty_fields += "'mailSent'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}


		if(empty_fields != "")
		{
			if(validate == "inplaceedit")
			{
				$('<input type="submit">').hide().appendTo($('#tableForm')).click().remove(); 
				return false;
			}
		}

	}

	if(document.getElementById('isSeen_checkbox_' + row).checked)
	{
		document.getElementById('isSeen_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('isSeen_checkbox_' + row).value = "false";
	}
	if(document.getElementById('isHidden_checkbox_' + row).checked)
	{
		document.getElementById('isHidden_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('isHidden_checkbox_' + row).value = "false";
	}
	console.log("found date = " + document.getElementById('entryDate_date_Date_' + row).value);
	document.getElementById('entryDate_date_' + row).value = new Date(document.getElementById('entryDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('seenDate_date_Date_' + row).value);
	document.getElementById('seenDate_date_' + row).value = new Date(document.getElementById('seenDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('showingDate_date_Date_' + row).value);
	document.getElementById('showingDate_date_' + row).value = new Date(document.getElementById('showingDate_date_Date_' + row).value).getTime();
	if(document.getElementById('sendAlarm_checkbox_' + row).checked)
	{
		document.getElementById('sendAlarm_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('sendAlarm_checkbox_' + row).value = "false";
	}
	if(document.getElementById('sendSms_checkbox_' + row).checked)
	{
		document.getElementById('sendSms_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('sendSms_checkbox_' + row).value = "false";
	}
	if(document.getElementById('sendMail_checkbox_' + row).checked)
	{
		document.getElementById('sendMail_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('sendMail_checkbox_' + row).value = "false";
	}
	if(document.getElementById('sendPush_checkbox_' + row).checked)
	{
		document.getElementById('sendPush_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('sendPush_checkbox_' + row).value = "false";
	}
	if(document.getElementById('mailSent_checkbox_' + row).checked)
	{
		document.getElementById('mailSent_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('mailSent_checkbox_' + row).value = "false";
	}
	if(document.getElementById('smsSent_checkbox_' + row).checked)
	{
		document.getElementById('smsSent_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('smsSent_checkbox_' + row).value = "false";
	}
	if(document.getElementById('pushSent_checkbox_' + row).checked)
	{
		document.getElementById('pushSent_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('pushSent_checkbox_' + row).value = "false";
	}
	return true;
}

function PostprocessAfterSubmiting(row)
{
}


function addHTML(id, HTML)
{
	document.getElementById(id).innerHTML += HTML;
}

function getRequests() 
{
    var s1 = location.search.substring(1, location.search.length).split('&'),
        r = {}, s2, i;
    for (i = 0; i < s1.length; i += 1) {
        s2 = s1[i].split('=');
        r[decodeURIComponent(s2[0]).toLowerCase()] = decodeURIComponent(s2[1]);
    }
    return r;
}

function Request(name){
    return getRequests()[name.toLowerCase()];
}

function ShowExcelParsingResult(suffix)
{
	var failureMessage = document.getElementById("failureMessage_" + suffix);
	if(failureMessage == null)
	{
		console.log("failureMessage_" + suffix + " not found");
	}
	console.log("value = " + failureMessage.value);
	if(failureMessage != null &&  failureMessage.value != "")
	{
		alert("Excel uploading result:" + failureMessage.value);
	}
}

function init(row)
{
}var row = 0;
bkLib.onDomLoaded(function() 
{	
});
	
window.onload =function ()
{
	init(row);
}

</script>






