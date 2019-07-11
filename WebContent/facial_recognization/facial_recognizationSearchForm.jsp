
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="facial_recognization.Facial_recognizationDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.lang.String"%>


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
String Language = LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_LANGUAGE, loginDTO);
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_NAME, loginDTO)%></th>
								<th><%=LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_ADDRESS, loginDTO)%></th>
								<th><%=LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_PHONE, loginDTO)%></th>
								<th><%=LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_EMAIL, loginDTO)%></th>
								<th><%=LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_IMAGE, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.FACIAL_RECOGNIZATION_SEARCH_FACIAL_RECOGNIZATION_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.FACIAL_RECOGNIZATION_SEARCH_FACIAL_RECOGNIZATION_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_FACIAL_RECOGNIZATION);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Facial_recognizationDTO row = (Facial_recognizationDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
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
	String navigator2 = SessionConstants.NAV_FACIAL_RECOGNIZATION;
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
	xhttp.open("POST", "Facial_recognizationServlet?actionType=getGRSOffice&officer_id=" + officer_id, true);
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
	xhttp.open("POST", "Facial_recognizationServlet?actionType=getGRSLayer&layernum=" + layernum + "&layerID=" 
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
		if(!document.getElementById('name_text_' + row).checkValidity())
		{
			empty_fields += "'name'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('address_geolocation_' + row).checkValidity())
		{
			empty_fields += "'address'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('phone_text_' + row).checkValidity())
		{
			empty_fields += "'phone'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('email_text_' + row).checkValidity())
		{
			empty_fields += "'email'";
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

	document.getElementById('address_geolocation_' + row).value = document.getElementById('address_geolocation_' + row).value + ":" + document.getElementById('address_geoTextField_' + row).value;
	console.log("geo value = " + document.getElementById('address_geolocation_' + row).value);
	return true;
}

function PostprocessAfterSubmiting(row)
{
	document.getElementById('address_geolocation_' + row).value = "1";
}

function addrselected(value, htmlID, selectedIndex, tagname, geodiv, hiddenfield)
{
	console.log('geodiv = ' + geodiv + ' hiddenfield = ' + hiddenfield);
	try 
	{
		var elements, ids;
		elements = document.getElementById(geodiv).children;
		
		document.getElementById(hiddenfield).value = value;
		
		ids = '';
		for(var i = elements.length - 1; i >= 0; i--) 
		{
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
		//console.log('innerHTML= ' + document.getElementById(geodiv).innerHTML);
		document.getElementById(htmlID).options[0].innerHTML = document.getElementById(htmlID).options[selectedIndex].innerHTML;
		document.getElementById(htmlID).options[0].value = document.getElementById(htmlID).options[selectedIndex].value;
		//console.log('innerHTML again = ' + document.getElementById(geodiv).innerHTML);
		
		
		
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
		 
		xhttp.open("POST", "Facial_recognizationServlet?actionType=getGeo&myID="+value, true);
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
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
	    	document.getElementById('address_geoSelectField_' + row).innerHTML = this.responseText ;
		}
	    else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	 };
	xhttp.open("POST", "Facial_recognizationServlet?actionType=getGeo&myID=1", true);
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
	  
	  xhttp.open("Get", "Facial_recognizationServlet?actionType=getEditPage" + params, true);
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
	formData.append('name', document.getElementById('name_text_' + i).value);
	formData.append('address', document.getElementById('address_geolocation_' + i).value);
	formData.append('phone', document.getElementById('phone_text_' + i).value);
	formData.append('email', document.getElementById('email_text_' + i).value);
	formData.append('image', document.getElementById('image_image_' + i).files[0]);
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
	xhttp.open("POST", 'Facial_recognizationServlet?actionType=edit&inplacesubmit=true&deletedStyle=' + deletedStyle + '&rownum=' + i, true);
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
			