
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull_transfer.Bull_transferDTO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page import="bull_transfer.Bull_transferAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%@page import= "user.UserDTO"%>
<%@page import= "user.UserRepository"%>

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
String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_DATEOFTRANSFER, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_BULLTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_FROMCENTRETYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_TOCENTRETYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_ENTRYDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_EXITDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_DESCRIPTION, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_TRANSFER_EDIT_APPROVALSTATUSTYPE, loginDTO)%></th>
								<%
								if(userDTO.roleID == 1)
								{
									out.print("<th>" + LM.getText(LC.BULL_TRANSFER_SEARCH_BULL_TRANSFER_EDIT_BUTTON, loginDTO) + "</th>");
									out.print("<th><input type=\"submit\" class=\"btn btn-xs btn-danger\" value=\"" + LM.getText(LC.BULL_TRANSFER_SEARCH_BULL_TRANSFER_DELETE_BUTTON, loginDTO) + "\"/></th>");
								
								}

								%>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_BULL_TRANSFER);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Bull_transferDTO row = (Bull_transferDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
											out.println("<td id = '" + i + "_dateOfTransfer'>");
											value = row.dateOfTransfer + "";
											SimpleDateFormat format_dateOfTransfer = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfTransfer = format_dateOfTransfer.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfTransfer);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_fromCentreType'>");
											value = row.fromCentreType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_toCentreType'>");
											value = row.toCentreType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_entryDate'>");
											value = row.entryDate + "";
											SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_entryDate = format_entryDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_entryDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_exitDate'>");
											value = row.exitDate + "";
											SimpleDateFormat format_exitDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_exitDate = format_exitDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_exitDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_approvalStatusType'>");
											value = row.approvalStatusType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "approval_status", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";	
											
											
											if(userDTO.roleID == 1)
											{
	
												out.println("<td id = '" + i + "_Edit'>");										
												out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.BULL_TRANSFER_SEARCH_BULL_TRANSFER_EDIT_BUTTON, loginDTO) + "</a>");
											
												out.println("</td>");
												
												
												
												out.println("<td>");
												out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
												out.println("</td>");
												out.println("</tr>");
											}
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
	String navigator2 = SessionConstants.NAV_BULL_TRANSFER;
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


		if(empty_fields != "")
		{
			if(validate == "inplaceedit")
			{
				$('<input type="submit">').hide().appendTo($('#tableForm')).click().remove(); 
				return false;
			}
		}

	}

	console.log("found date = " + document.getElementById('dateOfTransfer_date_Date_' + row).value);
	document.getElementById('dateOfTransfer_date_' + row).value = new Date(document.getElementById('dateOfTransfer_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('entryDate_date_Date_' + row).value);
	document.getElementById('entryDate_date_' + row).value = new Date(document.getElementById('entryDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('exitDate_date_Date_' + row).value);
	document.getElementById('exitDate_date_' + row).value = new Date(document.getElementById('exitDate_date_Date_' + row).value).getTime();
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
	  
	  xhttp.open("Get", "Bull_transferServlet?actionType=getEditPage" + params, true);
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
	formData.append('dateOfTransfer', document.getElementById('dateOfTransfer_date_' + i).value);
	formData.append('bullType', document.getElementById('bullType_select_' + i).value);
	formData.append('fromCentreType', document.getElementById('fromCentreType_select_' + i).value);
	formData.append('toCentreType', document.getElementById('toCentreType_select_' + i).value);
	formData.append('entryDate', document.getElementById('entryDate_date_' + i).value);
	formData.append('exitDate', document.getElementById('exitDate_date_' + i).value);
	formData.append('description', document.getElementById('description_textarea_' + i).value);
	formData.append('approvalStatusType', document.getElementById('approvalStatusType_select_' + i).value);
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
	xhttp.open("POST", 'Bull_transferServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
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
			