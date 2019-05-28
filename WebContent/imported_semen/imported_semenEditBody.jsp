
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="imported_semen.Imported_semenDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Imported_semenDTO imported_semenDTO;
imported_semenDTO = (Imported_semenDTO)request.getAttribute("imported_semenDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(imported_semenDTO == null)
{
	imported_semenDTO = new Imported_semenDTO();
	
}
System.out.println("imported_semenDTO = " + imported_semenDTO);

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
	formTitle = LM.getText(LC.IMPORTED_SEMEN_EDIT_IMPORTED_SEMEN_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.IMPORTED_SEMEN_ADD_IMPORTED_SEMEN_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="Imported_semenServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
String Language = LM.getText(LC.IMPORTED_SEMEN_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_BULLTAG, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_BULLTAG, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'bullTag_div_<%=i%>'>	
		<input type='text' class='form-control'  name='bullTag' id = 'bullTag_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.bullTag + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_DATEOFBIRTH, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_DATEOFBIRTH, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'dateOfBirth_div_<%=i%>'>	
		<input type='date' class='form-control'  name='dateOfBirth_Date_<%=i%>' id = 'dateOfBirth_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfBirth = format_dateOfBirth.format(new Date(imported_semenDTO.dateOfBirth)).toString();
	out.println("'" + formatted_dateOfBirth + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfBirth' id = 'dateOfBirth_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dateOfBirth + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_DAM, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_DAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'dam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='dam' id = 'dam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_SIRE, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_SIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'sire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='sire' id = 'sire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.sire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_MATERNALGRANDDAM, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_MATERNALGRANDDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'maternalGrandDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='maternalGrandDam' id = 'maternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.maternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_MATERNALGRANDSIRE, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_MATERNALGRANDSIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'maternalGrandSire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='maternalGrandSire' id = 'maternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.maternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_PATERNALGRANDDAM, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_PATERNALGRANDDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'paternalGrandDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='paternalGrandDam' id = 'paternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.paternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_PATERNALGRANDSIRE, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_PATERNALGRANDSIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'paternalGrandSire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='paternalGrandSire' id = 'paternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.paternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_MILKYIELDOFDAM, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_MILKYIELDOFDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'milkYieldOfDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='milkYieldOfDam' id = 'milkYieldOfDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.milkYieldOfDam + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_MILKYIELDOFSIRESDAM, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_MILKYIELDOFSIRESDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'milkYieldOfSiresDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='milkYieldOfSiresDam' id = 'milkYieldOfSiresDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.milkYieldOfSiresDam + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_PROGENYMILKYIELD, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_PROGENYMILKYIELD, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'progenyMilkYield_div_<%=i%>'>	
		<input type='text' class='form-control'  name='progenyMilkYield' id = 'progenyMilkYield_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.progenyMilkYield + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_BREED, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_BREED, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'breed_div_<%=i%>'>	
		<input type='text' class='form-control'  name='breed' id = 'breed_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.breed + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_DATEOFENTRY, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_DATEOFENTRY, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'dateOfEntry_div_<%=i%>'>	
		<input type='date' class='form-control'  name='dateOfEntry_Date_<%=i%>' id = 'dateOfEntry_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfEntry = format_dateOfEntry.format(new Date(imported_semenDTO.dateOfEntry)).toString();
	out.println("'" + formatted_dateOfEntry + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfEntry' id = 'dateOfEntry_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dateOfEntry + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_RECEIVEDAMOUNT, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_RECEIVEDAMOUNT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'receivedAmount_div_<%=i%>'>	
		<input type='number' class='form-control'  name='receivedAmount' id = 'receivedAmount_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.receivedAmount + "'"):("'" + 0 + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_DISTRIBUTEDAMOUNT, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_DISTRIBUTEDAMOUNT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'distributedAmount_div_<%=i%>'>	
		<input type='number' class='form-control'  name='distributedAmount' id = 'distributedAmount_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.distributedAmount + "'"):("'" + 0 + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_TOWHOMDISTRIBUTED, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_TOWHOMDISTRIBUTED, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'toWhomDistributed_div_<%=i%>'>	
		<input type='text' class='form-control'  name='toWhomDistributed' id = 'toWhomDistributed_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.toWhomDistributed + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.IMPORTED_SEMEN_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.IMPORTED_SEMEN_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(imported_semenDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + imported_semenDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.IMPORTED_SEMEN_EDIT_IMPORTED_SEMEN_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.IMPORTED_SEMEN_ADD_IMPORTED_SEMEN_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.IMPORTED_SEMEN_EDIT_IMPORTED_SEMEN_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.IMPORTED_SEMEN_ADD_IMPORTED_SEMEN_SUBMIT_BUTTON, loginDTO));
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


		if(empty_fields != "")
		{
			if(validate == "inplaceedit")
			{
				$('<input type="submit">').hide().appendTo($('#tableForm')).click().remove(); 
				return false;
			}
		}

	}

	console.log("found date = " + document.getElementById('dateOfBirth_date_Date_' + row).value);
	document.getElementById('dateOfBirth_date_' + row).value = new Date(document.getElementById('dateOfBirth_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('dateOfEntry_date_Date_' + row).value);
	document.getElementById('dateOfEntry_date_' + row).value = new Date(document.getElementById('dateOfEntry_date_Date_' + row).value).getTime();
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






