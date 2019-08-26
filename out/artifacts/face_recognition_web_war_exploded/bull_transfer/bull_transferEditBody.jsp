
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="bull_transfer.Bull_transferDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Bull_transferDTO bull_transferDTO;
bull_transferDTO = (Bull_transferDTO)request.getAttribute("bull_transferDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(bull_transferDTO == null)
{
	bull_transferDTO = new Bull_transferDTO();
	
}
System.out.println("bull_transferDTO = " + bull_transferDTO);

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
	formTitle = LM.getText(LC.BULL_TRANSFER_EDIT_BULL_TRANSFER_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.BULL_TRANSFER_ADD_BULL_TRANSFER_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="Bull_transferServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="bull_transfer.Bull_transferAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
date.setYear(date.getYear() + 2);
String futuredatestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_DATEOFTRANSFER, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_DATEOFTRANSFER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'dateOfTransfer_div_<%=i%>'>	
		<input type='date' class='form-control'  name='dateOfTransfer_Date_<%=i%>' id = 'dateOfTransfer_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfTransfer = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfTransfer = format_dateOfTransfer.format(new Date(bull_transferDTO.dateOfTransfer)).toString();
	out.println("'" + formatted_dateOfTransfer + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfTransfer' id = 'dateOfTransfer_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.dateOfTransfer + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_BULLTYPE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_BULLTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'bullType_div_<%=i%>'>	
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", bull_transferDTO.bullType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_FROMCENTRETYPE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_FROMCENTRETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'fromCentreType_div_<%=i%>'>	
		<select class='form-control'  name='fromCentreType' id = 'fromCentreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "fromCentreType_select_" + i, "form-control", "fromCentreType", bull_transferDTO.fromCentreType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "fromCentreType_select_" + i, "form-control", "fromCentreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_TOCENTRETYPE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_TOCENTRETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'toCentreType_div_<%=i%>'>	
		<select class='form-control'  name='toCentreType' id = 'toCentreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "toCentreType_select_" + i, "form-control", "toCentreType", bull_transferDTO.toCentreType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "toCentreType_select_" + i, "form-control", "toCentreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_ENTRYDATE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_ENTRYDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'entryDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(bull_transferDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.entryDate + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_EXITDATE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_EXITDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'exitDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='exitDate_Date_<%=i%>' id = 'exitDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_exitDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_exitDate = format_exitDate.format(new Date(bull_transferDTO.exitDate)).toString();
	out.println("'" + formatted_exitDate + "'");
}
else
{
	out.println("'" + futuredatestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='exitDate' id = 'exitDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.exitDate + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(bull_transferDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.BULL_TRANSFER_EDIT_APPROVALSTATUSTYPE, loginDTO)):(LM.getText(LC.BULL_TRANSFER_ADD_APPROVALSTATUSTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'approvalStatusType_div_<%=i%>'>	
		<select class='form-control'  name='approvalStatusType' id = 'approvalStatusType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "approval_status", "approvalStatusType_select_" + i, "form-control", "approvalStatusType", bull_transferDTO.approvalStatusType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "approval_status", "approvalStatusType_select_" + i, "form-control", "approvalStatusType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bull_transferDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_TRANSFER_EDIT_BULL_TRANSFER_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_TRANSFER_ADD_BULL_TRANSFER_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.BULL_TRANSFER_EDIT_BULL_TRANSFER_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.BULL_TRANSFER_ADD_BULL_TRANSFER_SUBMIT_BUTTON, loginDTO));
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
}var row = 0;
bkLib.onDomLoaded(function() 
{	
});
	
window.onload =function ()
{
	init(row);
}

</script>






