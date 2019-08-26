
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="apa_target.Apa_targetDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Apa_targetDTO apa_targetDTO;
apa_targetDTO = (Apa_targetDTO)request.getAttribute("apa_targetDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(apa_targetDTO == null)
{
	apa_targetDTO = new Apa_targetDTO();
	
}
System.out.println("apa_targetDTO = " + apa_targetDTO);

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
	formTitle = LM.getText(LC.APA_TARGET_EDIT_APA_TARGET_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.APA_TARGET_ADD_APA_TARGET_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="Apa_targetServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
String Language = LM.getText(LC.APA_TARGET_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_SEMENCOLLECTION, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_SEMENCOLLECTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'semenCollection_div_<%=i%>'>	
		<input type='text' class='form-control'  name='semenCollection' id = 'semenCollection_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.semenCollection + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_ARTIFICIALINSEMENATION, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_ARTIFICIALINSEMENATION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'artificialInsemenation_div_<%=i%>'>	
		<input type='text' class='form-control'  name='artificialInsemenation' id = 'artificialInsemenation_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.artificialInsemenation + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_PROGENY, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_PROGENY, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'progeny_div_<%=i%>'>	
		<input type='text' class='form-control'  name='progeny' id = 'progeny_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.progeny + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_CANDIDATEBULLPRODUCTION, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_CANDIDATEBULLPRODUCTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'candidateBullProduction_div_<%=i%>'>	
		<input type='text' class='form-control'  name='candidateBullProduction' id = 'candidateBullProduction_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.candidateBullProduction + "'"):("'" + "0" + "'")%>  />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(apa_targetDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.APA_TARGET_EDIT_ENTRYDATE, loginDTO)):(LM.getText(LC.APA_TARGET_ADD_ENTRYDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'entryDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(apa_targetDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.entryDate + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + apa_targetDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.APA_TARGET_EDIT_APA_TARGET_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.APA_TARGET_ADD_APA_TARGET_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.APA_TARGET_EDIT_APA_TARGET_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.APA_TARGET_ADD_APA_TARGET_SUBMIT_BUTTON, loginDTO));
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

	console.log("found date = " + document.getElementById('entryDate_date_Date_' + row).value);
	document.getElementById('entryDate_date_' + row).value = new Date(document.getElementById('entryDate_date_Date_' + row).value).getTime();
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






