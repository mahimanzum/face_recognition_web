
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="bull_breed_centre.Bull_breed_centreDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Bull_breed_centreDTO bull_breed_centreDTO;
bull_breed_centreDTO = (Bull_breed_centreDTO)request.getAttribute("bull_breed_centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(bull_breed_centreDTO == null)
{
	bull_breed_centreDTO = new Bull_breed_centreDTO();
	
}
System.out.println("bull_breed_centreDTO = " + bull_breed_centreDTO);

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
	formTitle = LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULL_BREED_CENTRE_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.BULL_BREED_CENTRE_ADD_BULL_BREED_CENTRE_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;

String value = "";
Bull_breed_centreDTO row = bull_breed_centreDTO;
%>



<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><i class="fa fa-gift"></i><%=formTitle%></h3>
	</div>
	<div class="box-body">
		<form class="form-horizontal" action="Bull_breed_centreServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.BULL_BREED_CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLTYPE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_BULLTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'bullType_div_<%=i%>'>	
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>'  onchange="fillDependentDiv('bullType_select_<%=i%>', 'bullType_dependent_div<%=i%>')"
>
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", bull_breed_centreDTO.bullType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
		<div id = 'bullType_dependent_div<%=i%>'>
		</div>	
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_BREEDTYPE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_BREEDTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'breedType_div_<%=i%>'>	
<%
if(actionName.equals("edit"))
{
%>
		<label class='control-label'  id = 'breedType_Label_<%=i%>' >
<%
											value = row.breedType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
%>
		</label>
		<input type='hidden' class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' value='<%=row.breedType%>'/>
<%		
}
else
{
%>
		<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>'  >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", bull_breed_centreDTO.breedType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType" );			
}
out.print(Options);
%>
		</select>
		
<%
}
%>
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_CENTRETYPE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_CENTRETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'centreType_div_<%=i%>'>	
		<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>'  >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", bull_breed_centreDTO.centreType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_GRSOFFICE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'grsOffice_div_<%=i%>'>	
		<select class='form-control'  name='grsOffice_<%=i%>_Layer_0' id = 'grsOffice_grs_office_<%=i%>_Layer_0' 
		onchange="layerselected(0, 'grsOffice_grs_office_<%=i%>_Layer_0', 'grsOffice_grs_office_<%=i%>_Layer_1', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "grs_top_layer", "grsOffice_grs_office_" + i + "_Layer_0", "form-control", "grsOffice_" + i + "_Layer_0", bull_breed_centreDTO.grsOffice + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "grs_top_layer", "grsOffice_grs_office_" + i + "_Layer_0", "form-control", "grsOffice_" + i + "_Layer_0", "any" );			
}
out.print(Options);
%>
		</select>
		<select class='form-control' style='display: none;'  name='grsOffice_<%=i%>_Layer_1' id = 'grsOffice_grs_office_<%=i%>_Layer_1'
		 onchange="layerselected(1, 'grsOffice_grs_office_<%=i%>_Layer_1', 'grsOffice_grs_office_<%=i%>_Layer_2', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
		</select>
		<select class='form-control' style='display: none;' name='grsOffice_<%=i%>_Layer_2' id = 'grsOffice_grs_office_<%=i%>_Layer_2'
		 onchange="layerselected(2, 'grsOffice_grs_office_<%=i%>_Layer_2', 'unknown', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
		</select>
		<input type = 'hidden' name='grsOffice_<%=i%>_TOP_HIDDEN' id = 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN' value='3'>
		<input type = 'hidden' class='form-control'  name='grsOffice' id = 'grsOffice_grs_office_<%=i%>' >
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICER, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_GRSOFFICER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'grsOfficer_div_<%=i%>'>	
		<select class='form-control'  name='grsOfficer' id = 'grsOfficer_grs_officer_<%=i%>' disabled>
		</select>
		
		
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_INFOFILE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_INFOFILE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'infoFile_div_<%=i%>'>	
		<input type='file' class='form-control'  name='infoFile' id = 'infoFile_file_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_breed_centreDTO.infoFile + "'"):("'" + " " + "'")%>  />	
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLIMAGE, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_BULLIMAGE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'bullImage_div_<%=i%>'>	
		<input type='file' class='form-control'  name='bullImage' id = 'bullImage_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_breed_centreDTO.bullImage + "'"):("'" + " " + "'")%>  />	
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_BREED_CENTRE_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.BULL_BREED_CENTRE_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>'  ><%=actionName.equals("edit")?(bull_breed_centreDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bull_breed_centreDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULL_BREED_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_BREED_CENTRE_ADD_BULL_BREED_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULL_BREED_CENTRE_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_BREED_CENTRE_ADD_BULL_BREED_CENTRE_SUBMIT_BUTTON, loginDTO));
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
	xhttp.open("POST", "Bull_breed_centreServlet?actionType=getGRSOffice&officer_id=" + officer_id, true);
	xhttp.send();
}

function fillDependentDiv(parentelement, dependentElement)
{
	console.log("getting Element");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{			
			document.getElementById(dependentElement).innerHTML = this.responseText ;						
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	var value= document.getElementById(parentelement).value;
	console.log("selected value = " + value);
	 
	xhttp.open("POST", "Bull_breed_centreServlet?actionType=getDependentDiv&Value=" + value, true);
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
}var row = 0;
bkLib.onDomLoaded(function() 
{	
});
	
window.onload =function ()
{
	init(row);
}

</script>






