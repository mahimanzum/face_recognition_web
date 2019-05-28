
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="pb_notifications.Pb_notificationsDTO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
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
String actionName = "edit";
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";	
}
out.println("<input type='hidden' id='failureMessage_general' value='" + failureMessage + "'/>");
String value = "";
String Language = LM.getText(LC.PB_NOTIFICATIONS_EDIT_LANGUAGE, loginDTO);
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_ISSEEN, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_ISHIDDEN, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SOURCE, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_DESTINATION, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_FROMID, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_TOID, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_TEXT, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_URL, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_ENTRYDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SEENDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SHOWINGDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDALARM, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDSMS, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDMAIL, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SENDPUSH, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_MAILSENT, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_SMSSENT, loginDTO)%></th>
								<th><%=LM.getText(LC.PB_NOTIFICATIONS_EDIT_PUSHSENT, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.PB_NOTIFICATIONS_SEARCH_PB_NOTIFICATIONS_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.PB_NOTIFICATIONS_SEARCH_PB_NOTIFICATIONS_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_PB_NOTIFICATIONS);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Pb_notificationsDTO row = (Pb_notificationsDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
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
											out.println("</td>");
											out.println("</tr>");
										}
										 
										System.out.println("printing done");
									}
									else
									{
										System.out.println("data  null");
									}
								}
								catch(Exception e)
								{
									System.out.println("JSP exception " + e);
								}
							%>



						</tbody>

					</table>
				</div>

<%
	String navigator2 = SessionConstants.NAV_PB_NOTIFICATIONS;
	System.out.println("navigator2 = " + navigator2);
	RecordNavigator rn2 = (RecordNavigator)session.getAttribute(navigator2);
	System.out.println("rn2 = " + rn2);
	String pageno2 = ( rn2 == null ) ? "1" : "" + rn2.getCurrentPageNo();
	String totalpage2 = ( rn2 == null ) ? "1" : "" + rn2.getTotalPages();

%>
<input type="hidden" id="hidden_pageno" value="<%=pageno2%>" />
<input type="hidden" id="hidden_totalpage" value="<%=totalpage2%>" />


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
}
function doEdit(params, i, id, deletedStyle)
{
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText !='')
			{
				var onclickFunc = "submitAjax(" + i + ",'" + deletedStyle + "')";
				document.getElementById('tr_' + i).innerHTML = this.responseText ;
				document.getElementById('tr_' + i).innerHTML += "<td id = '" + i + "_Submit'></td>";
				document.getElementById(i + '_Submit').innerHTML += "<a onclick=\""+ onclickFunc +"\">Submit</a>";				
				document.getElementById('tr_' + i).innerHTML += "<td>"
				+ "<input type='checkbox' name='ID' value='" + id + "'/>"
				+ "</td>";
				init(i);
			}
			else
			{
				document.getElementById('tr_' + i).innerHTML = 'NULL RESPONSE';
			}
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	  
	  xhttp.open("Get", "Pb_notificationsServlet?actionType=getEditPage" + params, true);
	  xhttp.send();	
}

function submitAjax(i, deletedStyle)
{
	console.log('submitAjax called');
	var isSubmittable = PreprocessBeforeSubmiting(i, "inplaceedit");
	if(isSubmittable == false)
	{
		return;
	}
	var formData = new FormData();
	var value;
	value = document.getElementById('iD_text_' + i).value;
	console.log('submitAjax i = ' + i + ' id = ' + value);
	formData.append('iD', value);
	formData.append("identity", value);
	formData.append("ID", value);
	formData.append('isSeen', document.getElementById('isSeen_checkbox_' + i).value);
	formData.append('isHidden', document.getElementById('isHidden_checkbox_' + i).value);
	formData.append('source', document.getElementById('source_text_' + i).value);
	formData.append('destination', document.getElementById('destination_text_' + i).value);
	formData.append('fromId', document.getElementById('fromId_text_' + i).value);
	formData.append('toId', document.getElementById('toId_text_' + i).value);
	formData.append('text', document.getElementById('text_text_' + i).value);
	formData.append('url', document.getElementById('url_url_' + i).value);
	formData.append('entryDate', document.getElementById('entryDate_date_' + i).value);
	formData.append('seenDate', document.getElementById('seenDate_date_' + i).value);
	formData.append('showingDate', document.getElementById('showingDate_date_' + i).value);
	formData.append('sendAlarm', document.getElementById('sendAlarm_checkbox_' + i).value);
	formData.append('sendSms', document.getElementById('sendSms_checkbox_' + i).value);
	formData.append('sendMail', document.getElementById('sendMail_checkbox_' + i).value);
	formData.append('sendPush', document.getElementById('sendPush_checkbox_' + i).value);
	formData.append('mailSent', document.getElementById('mailSent_checkbox_' + i).value);
	formData.append('smsSent', document.getElementById('smsSent_checkbox_' + i).value);
	formData.append('pushSent', document.getElementById('pushSent_checkbox_' + i).value);
	formData.append('isDeleted', document.getElementById('isDeleted_checkbox_' + i).value);

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText !='')
			{				
				document.getElementById('tr_' + i).innerHTML = this.responseText ;
				ShowExcelParsingResult(i);
			}
			else
			{
				console.log("No Response");
				document.getElementById('tr_' + i).innerHTML = 'NULL RESPONSE';
			}
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	  };
	xhttp.open("POST", 'Pb_notificationsServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
	xhttp.send(formData);
}

function fixedToEditable(i, deletedStyle, id)
{
	console.log('fixedToEditable called');
	var params = '&identity=' + id + '&inplaceedit=true' +  '&deletedStyle=' + deletedStyle + '&ID=' + id + '&rownum=' + i
	+ '&dummy=dummy';
	console.log('fixedToEditable i = ' + i + ' id = ' + id);
	doEdit(params, i, id, deletedStyle);

}
window.onload =function ()
{
	ShowExcelParsingResult('general');
}	
</script>
			