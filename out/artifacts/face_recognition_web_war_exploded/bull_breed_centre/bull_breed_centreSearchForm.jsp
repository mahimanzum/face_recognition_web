
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull_breed_centre.Bull_breed_centreDTO"%>
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
String Language = LM.getText(LC.BULL_BREED_CENTRE_EDIT_LANGUAGE, loginDTO);
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BREEDTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_CENTRETYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICER, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_INFOFILE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLIMAGE, loginDTO)%></th>
								<th><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_DESCRIPTION, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.BULL_BREED_CENTRE_SEARCH_BULL_BREED_CENTRE_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.BULL_BREED_CENTRE_SEARCH_BULL_BREED_CENTRE_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_BULL_BREED_CENTRE);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Bull_breed_centreDTO row = (Bull_breed_centreDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_breedType'>");
											value = row.breedType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_grsOffice'>");
											value = row.grsOffice + "";
											value = GRS_OFFICE_DAO.getOfficeName(Integer.parseInt(value), Language);										
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_grsOfficer'>");
											value = row.grsOfficer + "";
											GRS_OFFICER_DTO grsOfficerDTO_grsOfficer = GRS_OFFICER_DAO.getGRS_Officer_DTOByID(Long.parseLong(value));
											
											if(Language.equalsIgnoreCase("english"))
											{
												out.println(grsOfficerDTO_grsOfficer.name_eng + ", " + grsOfficerDTO_grsOfficer.designation_eng);
											}
											else
											{
												out.println(grsOfficerDTO_grsOfficer.name_bng + ", " + grsOfficerDTO_grsOfficer.designation_bng);
											}											
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_infoFile'>");
											value = row.infoFile + "";
											out.println("<a href = 'img2/" + value + "' download='" + value + "'>Download</a>");
											out.println("<a href='Bull_breed_centreServlet?actionType=getURL&URL=img2/" + value + "'>Open</a>");											
											out.println("<a href='#' data-toggle='modal' data-target='#infoFileModal_" + i + "'>View</a>");

											  


											out.println("<div class='modal fade' id='infoFileModal_" + i + "'>");
											  out.println("<div class='modal-dialog modal-lg' role='document'>");
											    out.println("<div class='modal-content'>");
											      out.println("<div class='modal-body'>");
											        out.println("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
											          out.println("<span aria-hidden='true'>&times;</span>");
											        out.println("</button>");
											        if(value.endsWith(".pdf"))
											        {
											        	 out.println("<object type='application/pdf' data='img2/" + value +  "' width='100%' height='500' style='height: 85vh;'>No Support</object>");
											        }
											        else
											        {
											        	 out.println("<object type='text/html' data='img2/" + value +  "' width='100%' height='500' style='height: 85vh;'>No Support</object>");
											        }
											      out.println("</div>");
											    out.println("</div>");
											  out.println("</div>");
											out.println("</div>");
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullImage'>");
											value = row.bullImage + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	

	

											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.BULL_BREED_CENTRE_SEARCH_BULL_BREED_CENTRE_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<div class='checker'>");
											out.println("<span id='chkEdit' ><input type='checkbox' name='ID' value='" + row.iD + "'/></span>");
											out.println("</div");
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
	String navigator2 = SessionConstants.NAV_BULL_BREED_CENTRE;
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



function getOfficer(officer_id, officer_select)
{
	console.log("getting officer");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText.includes('option'))
			{
				console.log("got response for officer");
				document.getElementById(officer_select).innerHTML = this.responseText ;
				
				if(document.getElementById(officer_select).length > 1)
				{
					document.getElementById(officer_select).removeAttribute("disabled");
				}	
			}
			else
			{
				console.log("got errror response for officer");
			}
			
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	xhttp.open("POST", "Bull_breed_centreServlet?actionType=getGRSOffice&officer_id=" + officer_id, true);
	xhttp.send();
}


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
	xhttp.open("POST", "Bull_breed_centreServlet?actionType=getGRSLayer&layernum=" + layernum + "&layerID=" 
			+ layerID + "&childLayerID=" + childLayerID + "&selectedValue=" + selectedValue, true);
	xhttp.send();
}

function layerselected(layernum, layerID, childLayerID, hiddenInput, hiddenInputForTopLayer, officerElement)
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
	
	if(officerElement !== null)
	{
		getOfficer(layervalue, officerElement);
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


		if(empty_fields != "")
		{
			if(validate == "inplaceedit")
			{
				$('<input type="submit">').hide().appendTo($('#tableForm')).click().remove(); 
				return false;
			}
		}

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
					+ "<div class='checker'>"
					+ "<span class='' id='chkEdit'><input type='checkbox' name='ID' value='" + id + "'/></span>"
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
	  
	  xhttp.open("Get", "Bull_breed_centreServlet?actionType=getEditPage" + params, true);
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
	formData.append('bullType', document.getElementById('bullType_select_' + i).value);
	formData.append('breedType', document.getElementById('breedType_select_' + i).value);
	formData.append('centreType', document.getElementById('centreType_select_' + i).value);
	formData.append('grsOffice', document.getElementById('grsOffice_grs_office_' + i).value);
	formData.append('grsOfficer', document.getElementById('grsOfficer_grs_officer_' + i).value);
	formData.append('infoFile', document.getElementById('infoFile_file_' + i).files[0]);
	formData.append('bullImage', document.getElementById('bullImage_image_' + i).files[0]);
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
	xhttp.open("POST", 'Bull_breed_centreServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
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
			