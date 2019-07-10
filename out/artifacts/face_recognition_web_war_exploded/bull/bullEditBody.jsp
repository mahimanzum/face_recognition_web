
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="bull.BullDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
BullDTO bullDTO;
bullDTO = (BullDTO)request.getAttribute("bullDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(bullDTO == null)
{
	bullDTO = new BullDTO();
	
}
System.out.println("bullDTO = " + bullDTO);

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
	formTitle = LM.getText(LC.BULL_EDIT_BULL_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.BULL_ADD_BULL_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="BullServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="bull.BullAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULL_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_NAMEEN, loginDTO)):(LM.getText(LC.BULL_ADD_NAMEEN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameEn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.nameEn + "'"):("''")%> required="required"
 />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_NAMEBN, loginDTO)):(LM.getText(LC.BULL_ADD_NAMEBN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameBn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.nameBn + "'"):("''")%> required="required"
 />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_DATEOFBIRTH, loginDTO)):(LM.getText(LC.BULL_ADD_DATEOFBIRTH, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'dateOfBirth_div_<%=i%>'>	
		<input type='date' class='form-control'  name='dateOfBirth_Date_<%=i%>' id = 'dateOfBirth_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfBirth = format_dateOfBirth.format(new Date(bullDTO.dateOfBirth)).toString();
	out.println("'" + formatted_dateOfBirth + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfBirth' id = 'dateOfBirth_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.dateOfBirth + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_BREEDTYPE, loginDTO)):(LM.getText(LC.BULL_ADD_BREEDTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'breedType_div_<%=i%>'>	
		<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = BullAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", bullDTO.breedType + "");
}
else
{			
			Options = BullAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_STATUSTYPE, loginDTO)):(LM.getText(LC.BULL_ADD_STATUSTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'statusType_div_<%=i%>'>	
		<select class='form-control'  name='statusType' id = 'statusType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = BullAnotherDBDAO.getOptions(Language, "select", "status", "statusType_select_" + i, "form-control", "statusType", bullDTO.statusType + "");
}
else
{			
			Options = BullAnotherDBDAO.getOptions(Language, "select", "status", "statusType_select_" + i, "form-control", "statusType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_DAM, loginDTO)):(LM.getText(LC.BULL_ADD_DAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'dam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='dam' id = 'dam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.dam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_SIRE, loginDTO)):(LM.getText(LC.BULL_ADD_SIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'sire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='sire' id = 'sire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.sire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_MATERNALGRANDDAM, loginDTO)):(LM.getText(LC.BULL_ADD_MATERNALGRANDDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'maternalGrandDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='maternalGrandDam' id = 'maternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.maternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_MATERNALGRANDSIRE, loginDTO)):(LM.getText(LC.BULL_ADD_MATERNALGRANDSIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'maternalGrandSire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='maternalGrandSire' id = 'maternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.maternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_PATERNALGRANDDAM, loginDTO)):(LM.getText(LC.BULL_ADD_PATERNALGRANDDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'paternalGrandDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='paternalGrandDam' id = 'paternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.paternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_PATERNALGRANDSIRE, loginDTO)):(LM.getText(LC.BULL_ADD_PATERNALGRANDSIRE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'paternalGrandSire_div_<%=i%>'>	
		<input type='text' class='form-control'  name='paternalGrandSire' id = 'paternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.paternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_MILKYIELDOFDAM, loginDTO)):(LM.getText(LC.BULL_ADD_MILKYIELDOFDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'milkYieldOfDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='milkYieldOfDam' id = 'milkYieldOfDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.milkYieldOfDam + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_MILKYIELDOFSIRESDAM, loginDTO)):(LM.getText(LC.BULL_ADD_MILKYIELDOFSIRESDAM, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'milkYieldOfSiresDam_div_<%=i%>'>	
		<input type='text' class='form-control'  name='milkYieldOfSiresDam' id = 'milkYieldOfSiresDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.milkYieldOfSiresDam + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_PROGENYMILKYIELD, loginDTO)):(LM.getText(LC.BULL_ADD_PROGENYMILKYIELD, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'progenyMilkYield_div_<%=i%>'>	
		<input type='text' class='form-control'  name='progenyMilkYield' id = 'progenyMilkYield_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.progenyMilkYield + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.BULL_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(bullDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bullDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_EDIT_BULL_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_ADD_BULL_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_EDIT_BULL_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_ADD_BULL_SUBMIT_BUTTON, loginDTO));
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






