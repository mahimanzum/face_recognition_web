
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="centre.CentreDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="geolocation.GeoLocationDAO2"%>

<%
CentreDTO centreDTO;
centreDTO = (CentreDTO)request.getAttribute("centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(centreDTO == null)
{
	centreDTO = new CentreDTO();
	
}
System.out.println("centreDTO = " + centreDTO);

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
	formTitle = LM.getText(LC.CENTRE_EDIT_CENTRE_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.CENTRE_ADD_CENTRE_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="CentreServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
String Language = LM.getText(LC.CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_NAMEEN, loginDTO)):(LM.getText(LC.CENTRE_ADD_NAMEEN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameEn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.nameEn + "'"):("''")%> required="required"
 />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_NAMEBN, loginDTO)):(LM.getText(LC.CENTRE_ADD_NAMEBN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameBn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.nameBn + "'"):("''")%> required="required"
 />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_ADDRESS, loginDTO)):(LM.getText(LC.CENTRE_ADD_ADDRESS, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'address_div_<%=i%>'>	
		<div id ='address_geoDIV_<%=i%>'>
			<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_geoDIV_<%=i%>', 'address_geolocation_<%=i%>')" required="required"  pattern="^((?!select division).)*$" title="address must be selected"
></select>
		</div>
		<input type='text' class='form-control' name='address_text' id = 'address_geoTextField_<%=i%>' value=<%=actionName.equals("edit")?("'" +  GeoLocationDAO2.parseDetails(centreDTO.address)  + "'"):("''")%> placeholder='Road Number, House Number etc'>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + "1" + "'"):("''")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_CONTACTPERSON, loginDTO)):(LM.getText(LC.CENTRE_ADD_CONTACTPERSON, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'contactPerson_div_<%=i%>'>	
		<input type='text' class='form-control'  name='contactPerson' id = 'contactPerson_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.contactPerson + "'"):("'" + " " + "'")%> required="required"  pattern="880[0-9]{10}" title="contactPerson must start with 880, then contain 10 digits"
 />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_PHONENUMBER, loginDTO)):(LM.getText(LC.CENTRE_ADD_PHONENUMBER, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'phoneNumber_div_<%=i%>'>	
		<input type='number' class='form-control'  name='phoneNumber' id = 'phoneNumber_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + centreDTO.phoneNumber + "'"):("''")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_EMAIL, loginDTO)):(LM.getText(LC.CENTRE_ADD_EMAIL, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'email_div_<%=i%>'>	
		<input type='text' class='form-control'  name='email' id = 'email_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.email + "'"):("''")%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="email must be a of valid email address format"
 />					
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + centreDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CENTRE_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.CENTRE_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(centreDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.CENTRE_EDIT_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.CENTRE_ADD_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.CENTRE_EDIT_CENTRE_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.CENTRE_ADD_CENTRE_SUBMIT_BUTTON, loginDTO));
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
		if(!document.getElementById('nameEn_text_' + row).checkValidity())
		{
			empty_fields += "'nameEn'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('nameBn_text_' + row).checkValidity())
		{
			empty_fields += "'nameBn'";
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
		if(!document.getElementById('phoneNumber_number_' + row).checkValidity())
		{
			empty_fields += "'phoneNumber'";
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
		 
		xhttp.open("POST", "CentreServlet?actionType=getGeo&myID="+value, true);
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
	xhttp.open("POST", "CentreServlet?actionType=getGeo&myID=1", true);
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






