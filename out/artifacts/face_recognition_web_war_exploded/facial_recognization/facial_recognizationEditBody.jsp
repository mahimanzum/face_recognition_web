
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="facial_recognization.Facial_recognizationDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="geolocation.GeoLocationDAO2"%>

<%
Facial_recognizationDTO facial_recognizationDTO;
facial_recognizationDTO = (Facial_recognizationDTO)request.getAttribute("facial_recognizationDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(facial_recognizationDTO == null)
{
	facial_recognizationDTO = new Facial_recognizationDTO();
	
}
System.out.println("facial_recognizationDTO = " + facial_recognizationDTO);

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
	formTitle = LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_FACIAL_RECOGNIZATION_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.FACIAL_RECOGNIZATION_ADD_FACIAL_RECOGNIZATION_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;

String value = "";

%>



<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><i class="fa fa-gift"></i><%=formTitle%></h3>
	</div>
	<div class="box-body">
		<form class="form-horizontal" action="Facial_recognizationServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_NAME, loginDTO)):(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_NAME, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'name_div_<%=i%>'>	
		<input type='text' class='form-control'  name='name' id = 'name_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.name + "'"):("''")%> required="required"
  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_ADDRESS, loginDTO)):(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_ADDRESS, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'address_div_<%=i%>'>	
		<div id ='address_geoDIV_<%=i%>'>
			<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_geoDIV_<%=i%>', 'address_geolocation_<%=i%>')" required="required"  pattern="^((?!select division).)*$" title="address must be selected"
></select>
		</div>
		<input type='text' class='form-control' name='address_text' id = 'address_geoTextField_<%=i%>' value=<%=actionName.equals("edit")?("'" +  GeoLocationDAO2.parseDetails(facial_recognizationDTO.address)  + "'"):("''")%> placeholder='Road Number, House Number etc'>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + "1" + "'"):("''")%>>
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_PHONE, loginDTO)):(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_PHONE, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'phone_div_<%=i%>'>	
		<input type='text' class='form-control'  name='phone' id = 'phone_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.phone + "'"):("''")%> required="required"  pattern="880[0-9]{10}" title="phone must start with 880, then contain 10 digits"
  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_EMAIL, loginDTO)):(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_EMAIL, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'email_div_<%=i%>'>	
		<input type='text' class='form-control'  name='email' id = 'email_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.email + "'"):("''")%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="email must be a of valid email address format"
  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_IMAGE, loginDTO)):(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_IMAGE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'image_div_<%=i%>'>	
		<input type='file' class='form-control'  name='image' id = 'image_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.image + "'"):("'" + " " + "'")%>  />	
						
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + facial_recognizationDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_FACIAL_RECOGNIZATION_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_FACIAL_RECOGNIZATION_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_FACIAL_RECOGNIZATION_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.FACIAL_RECOGNIZATION_ADD_FACIAL_RECOGNIZATION_SUBMIT_BUTTON, loginDTO));
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
}var row = 0;
bkLib.onDomLoaded(function() 
{	
});
	
window.onload =function ()
{
	init(row);
}

</script>






