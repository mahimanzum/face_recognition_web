
<%@page import="citizen.CitizenDAO"%>
<%@page import="vehicle.VehicleDAO"%>
<%@page import="citizen.CitizenRepository"%>
<%@page import="citizen.CitizenDTO"%>
<%@page import="vehicle.VehicleDTO"%>
<%@page import="vehicle.VehicleRepository"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="report.ReportDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="vehicle.VehicleAnotherDBDAO"%>
<%@ page import="report.ReportAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
GeoLocationDAO2 geoLocationDAO = new GeoLocationDAO2();
String actionName = "edit";
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";	
}
out.println("<input type='hidden' id='failureMessage_general' value='" + failureMessage + "'/>");
String value = "";
String Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
VehicleDAO vehicleDAO = new VehicleDAO();
CitizenDAO citizenDAO = new CitizenDAO();

%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.REPORT_EDIT_NAME, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_NID, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_PHONENUMBER, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_ADDRESS, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_ADDRESSDETAILS, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_MAILID, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_TYPEID, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_MODELNAME, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_COLOR, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_ENGINENUMBER, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_ENGINETYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_CHASSISNUMBER, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_ENGINECC, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_REGISTRATIONNUMBER, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_MANUFACTURER, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_MANUFACTURINGYEAR, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_MOREDETAILS, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_REPORTINGDATE, loginDTO)%></th>
								<!--  <th><%=LM.getText(LC.REPORT_EDIT_REPORTERID, loginDTO)%></th> -->
								<!-- <th><%=LM.getText(LC.REPORT_EDIT_VEHICLEID, loginDTO)%></th> -->
								<th><%=LM.getText(LC.REPORT_EDIT_LOSTDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_FOUNDDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_STATUSID, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_THANAADDRESS, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_BLOG, loginDTO)%></th>
								<th><%=LM.getText(LC.REPORT_EDIT_IMAGE1, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.REPORT_SEARCH_REPORT_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.REPORT_SEARCH_REPORT_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_REPORT);
								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											ReportDTO row = (ReportDTO) data.get(i);
											
											VehicleDTO vehicleDTO = vehicleDAO.getVehicleDTOByID(row.vehicleId);		
											CitizenDTO citizenDTO = citizenDAO.getCitizenDTOByID(row.reporterId);
											
											System.out.println(row.vehicleId+" -- "+row.reporterId);
											if(vehicleDTO!=null &&citizenDTO!=null){
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
											out.println("<td id = '" + i + "_name'>");
											out.println(citizenDTO.name);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_nid'>");
											out.println(citizenDTO.nid);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_phoneNumber'>");
											out.println(citizenDTO.phoneNumber);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_address'>");
				
											out.println(geoLocationDAO.getLocationText(citizenDTO.address));
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_addressDetails'>");
											out.println(citizenDTO.addressDetails);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_mailId'>");
											out.println(citizenDTO.mailId);
											
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_typeId'>");
											out.println(VehicleAnotherDBDAO.getName (Language, vehicleDTO.typeId , "vehicle_type"));
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_modelName'>");
											out.println(vehicleDTO.modelName);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_color'>");
											out.println(vehicleDTO.color);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineNumber'>");
											out.println(vehicleDTO.engineNumber);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineType'>");
											out.println(VehicleAnotherDBDAO.getName (Language, (int)vehicleDTO.engineType , "engine_type"));
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_chassisNumber'>");
											out.println(vehicleDTO.chassisNumber);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_engineCc'>");
											out.println(vehicleDTO.engineCc);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_registrationNumber'>");
											out.println(vehicleDTO.registrationNumber);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_manufacturer'>");
											out.println(vehicleDTO.manufacturer);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_manufacturingYear'>");
											out.println(vehicleDTO.manufacturingYear);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_moreDetails'>");
											out.println(vehicleDTO.moreDetails);
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_reportingDate'>");
											value = row.reportingDate + "";
											SimpleDateFormat format_reportingDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_reportingDate = format_reportingDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_reportingDate);
				
			
											out.println("</td>");
		
											
											/*out.println("<td id = '" + i + "_reporterId'>");
											value = row.reporterId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_vehicleId'>");
											value = row.vehicleId + "";
														
											out.println(value);
				
			
											out.println("</td>");*/
		
											
											out.println("<td id = '" + i + "_lostDate'>");
											value = row.lostDate + "";
											SimpleDateFormat format_lostDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_lostDate = format_lostDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_lostDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_foundDate'>");
											value = row.foundDate + "";
											SimpleDateFormat format_foundDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_foundDate = format_foundDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_foundDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_statusId'>");
											value = row.statusId + "";
											
											value = ReportAnotherDBDAO.getName (Language, Integer.parseInt(value) , "status");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_thanaAddress'>");
											value = row.thanaAddress + "";
											out.println(geoLocationDAO.getLocationText(Integer.parseInt(value)));
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_blog'>");
											value = row.blog + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_image1'>");
											value = row.image1 + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a href=\"ReportServlet?actionType=getEditPage&ID=" +row.iD+"\">Edit</a>");
										
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
	String navigator2 = SessionConstants.NAV_REPORT;
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



function PreprocessBeforeSubmiting(row, validate)
{
	console.log("found date = " + document.getElementById('reportingDate_date_Date_' + row).value);
	document.getElementById('reportingDate_date_' + row).value = new Date(document.getElementById('reportingDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('lostDate_date_Date_' + row).value);
	document.getElementById('lostDate_date_' + row).value = new Date(document.getElementById('lostDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('foundDate_date_Date_' + row).value);
	document.getElementById('foundDate_date_' + row).value = new Date(document.getElementById('foundDate_date_Date_' + row).value).getTime();
	if(validate)
	{
		var empty_fields = "";
		var i = 0;
		if(document.getElementById('name_text_' + row).value == "")
		{
			empty_fields += "'name'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('phoneNumber_text_' + row).value == "")
		{
			empty_fields += "'phoneNumber'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('address_geolocation_' + row).value == "")
		{
			empty_fields += "'address'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('addressDetails_textarea_' + row).value == "")
		{
			empty_fields += "'addressDetails'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('mailId_text_' + row).value == "")
		{
			empty_fields += "'mailId'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('modelName_text_' + row).value == "")
		{
			empty_fields += "'modelName'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('thanaAddress_geolocation_' + row).value == "")
		{
			empty_fields += "'thanaAddress'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(empty_fields != "")
		{
			alert(empty_fields + " cannot be empty.");
			return false;
		}
	}
	return true;
}
function addrselected(value, htmlID, selectedIndex, tagname, geodiv, hiddenfield)
{
	console.log('geodiv = ' + geodiv + ' hiddenfield = ' + hiddenfield);
	try 
	{
		document.getElementById(hiddenfield).value = value;
		var elements = document.getElementById(geodiv).children;
		var ids = '';
		for(var i = elements.length - 1; i >= 0; i--) {
			var elemID = elements[i].id;
			if(elemID.includes(htmlID) && elemID > htmlID)
			{
				ids += elements[i].id + ' ';
				
				for(var j = elements[i].options.length - 1; j >= 0; j--)
				{
				
					elements[i].options[j].remove();
				}
				elements[i].remove();
				
			}
		}


		var newid = htmlID + '_1';

		document.getElementById(geodiv).innerHTML += "<select class='form-control' name='" + tagname + "' id = '" + newid 
		+ "' onChange=\"addrselected(this.value, this.id, this.selectedIndex, this.name, '" + geodiv +"', '" + hiddenfield +"')\"></select>";
		console.log('innerHTML= ' + document.getElementById(geodiv).innerHTML);
		document.getElementById(htmlID).options[0].innerHTML = document.getElementById(htmlID).options[selectedIndex].innerHTML;
		document.getElementById(htmlID).options[0].value = document.getElementById(htmlID).options[selectedIndex].value;
		console.log('innerHTML again = ' + document.getElementById(geodiv).innerHTML);
		
		 var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() 
		  {
			if (this.readyState == 4 && this.status == 200) 
			{
				if(!this.responseText.includes('option'))
				{
					document.getElementById(newid).remove();
				}
				else
				{
					document.getElementById(newid).innerHTML = this.responseText ;
				}
				
			}
			else if(this.readyState == 4 && this.status != 200)
			{
				alert('failed ' + this.status);
			}
		  };
		 
		  var redirect = "geolocation/geoloc.jsp?myID="+value;
		  
		  xhttp.open("GET", redirect, true);
		  xhttp.send();
	}
	catch(err) 
	{
		alert("got error: " + err);
	}	  
	return;
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
		
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) 
	    {
	    	document.getElementById('address_geoSelectField_' + row).innerHTML = this.responseText ;
	    	document.getElementById('thanaAddress_geoSelectField_' + row).innerHTML = this.responseText ;
	    }
	    else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	  };
	  xhttp.open("GET", "geolocation/geoloc.jsp?myID=1", true);
	  xhttp.send();
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
	  
	  xhttp.open("Get", "ReportServlet?actionType=getEditPage" + params, true);
	  xhttp.send();	
}

function submitAjax(i, deletedStyle)
{
	console.log('submitAjax called');
	PreprocessBeforeSubmiting(i, false);
	var formData = new FormData();
	var value;
	value = document.getElementById('iD_text_' + i).value;
	console.log('submitAjax i = ' + i + ' id = ' + value);
	formData.append('iD', value);
	formData.append("identity", value);
	formData.append("ID", value);
	formData.append('name', document.getElementById('name_text_' + i).value);
	formData.append('nid', document.getElementById('nid_text_' + i).value);
	formData.append('phoneNumber', document.getElementById('phoneNumber_text_' + i).value);
	formData.append('address', document.getElementById('address_geolocation_' + i).value);
	formData.append('addressDetails', document.getElementById('addressDetails_textarea_' + i).value);
	formData.append('mailId', document.getElementById('mailId_text_' + i).value);
	formData.append('typeId', document.getElementById('typeId_select_' + i).value);
	formData.append('modelName', document.getElementById('modelName_text_' + i).value);
	formData.append('color', document.getElementById('color_color_' + i).value);
	formData.append('engineNumber', document.getElementById('engineNumber_text_' + i).value);
	formData.append('engineType', document.getElementById('engineType_select_' + i).value);
	formData.append('chassisNumber', document.getElementById('chassisNumber_text_' + i).value);
	formData.append('engineCc', document.getElementById('engineCc_text_' + i).value);
	formData.append('registrationNumber', document.getElementById('registrationNumber_text_' + i).value);
	formData.append('manufacturer', document.getElementById('manufacturer_text_' + i).value);
	formData.append('manufacturingYear', document.getElementById('manufacturingYear_number_' + i).value);
	formData.append('moreDetails', document.getElementById('moreDetails_textarea_' + i).value);
	formData.append('reportingDate', document.getElementById('reportingDate_date_' + i).value);
	formData.append('reporterId', document.getElementById('reporterId_text_' + i).value);
	formData.append('vehicleId', document.getElementById('vehicleId_text_' + i).value);
	formData.append('lostDate', document.getElementById('lostDate_date_' + i).value);
	formData.append('foundDate', document.getElementById('foundDate_date_' + i).value);
	formData.append('statusId', document.getElementById('statusId_select_' + i).value);
	formData.append('thanaAddress', document.getElementById('thanaAddress_geolocation_' + i).value);
	formData.append('blog', document.getElementById('blog_text_' + i).value);
	formData.append('image1', document.getElementById('image1_image_' + i).files[0]);
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
	xhttp.open("POST", 'ReportServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
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
			