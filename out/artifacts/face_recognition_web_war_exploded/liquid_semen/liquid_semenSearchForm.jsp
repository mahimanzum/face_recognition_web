
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="liquid_semen.Liquid_semenDTO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page import="liquid_semen.Liquid_semenAnotherDBDAO"%>
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
String Language = LM.getText(LC.LIQUID_SEMEN_EDIT_LANGUAGE, loginDTO);
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_CENTRETYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_BULLTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_NOOFDOSE, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_VOLUME, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_DENSITY, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_PROGRESSIVEMOTILITY, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_COLORTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_COLLECTIONDISTRIBUTIONDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.LIQUID_SEMEN_EDIT_DESCRIPTION, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.LIQUID_SEMEN_SEARCH_LIQUID_SEMEN_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.LIQUID_SEMEN_SEARCH_LIQUID_SEMEN_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_LIQUID_SEMEN);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Liquid_semenDTO row = (Liquid_semenDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = Liquid_semenAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = Liquid_semenAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_noOfDose'>");
											value = row.noOfDose + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_volume'>");
											value = row.volume + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_density'>");
											value = row.density + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_progressiveMotility'>");
											value = row.progressiveMotility + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_colorType'>");
											value = row.colorType + "";
											
											value = Liquid_semenAnotherDBDAO.getName(Integer.parseInt(value), "color", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_collectionDistributionDate'>");
											value = row.collectionDistributionDate + "";
											SimpleDateFormat format_collectionDistributionDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_collectionDistributionDate = format_collectionDistributionDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_collectionDistributionDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.LIQUID_SEMEN_SEARCH_LIQUID_SEMEN_EDIT_BUTTON, loginDTO) + "</a>");
										
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
	String navigator2 = SessionConstants.NAV_LIQUID_SEMEN;
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

	console.log("found date = " + document.getElementById('collectionDistributionDate_date_Date_' + row).value);
	document.getElementById('collectionDistributionDate_date_' + row).value = new Date(document.getElementById('collectionDistributionDate_date_Date_' + row).value).getTime();
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
	  
	  xhttp.open("Get", "Liquid_semenServlet?actionType=getEditPage" + params, true);
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
	formData.append('centreType', document.getElementById('centreType_select_' + i).value);
	formData.append('bullType', document.getElementById('bullType_select_' + i).value);
	formData.append('noOfDose', document.getElementById('noOfDose_number_' + i).value);
	formData.append('volume', document.getElementById('volume_text_' + i).value);
	formData.append('density', document.getElementById('density_text_' + i).value);
	formData.append('progressiveMotility', document.getElementById('progressiveMotility_text_' + i).value);
	formData.append('colorType', document.getElementById('colorType_select_' + i).value);
	formData.append('collectionDistributionDate', document.getElementById('collectionDistributionDate_date_' + i).value);
	formData.append('description', document.getElementById('description_textarea_' + i).value);
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
	xhttp.open("POST", 'Liquid_semenServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
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
			