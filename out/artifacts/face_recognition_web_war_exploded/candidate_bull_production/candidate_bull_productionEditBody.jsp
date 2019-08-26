
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="candidate_bull_production.Candidate_bull_productionDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Candidate_bull_productionDTO candidate_bull_productionDTO;
candidate_bull_productionDTO = (Candidate_bull_productionDTO)request.getAttribute("candidate_bull_productionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(candidate_bull_productionDTO == null)
{
	candidate_bull_productionDTO = new Candidate_bull_productionDTO();
	
}
System.out.println("candidate_bull_productionDTO = " + candidate_bull_productionDTO);

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
	formTitle = LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_CANDIDATE_BULL_PRODUCTION_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_CANDIDATE_BULL_PRODUCTION_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="Candidate_bull_productionServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="candidate_bull_production.Candidate_bull_productionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_PRODUCTIONDATE, loginDTO)):(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_PRODUCTIONDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'productionDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='productionDate_Date_<%=i%>' id = 'productionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_productionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_productionDate = format_productionDate.format(new Date(candidate_bull_productionDTO.productionDate)).toString();
	out.println("'" + formatted_productionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='productionDate' id = 'productionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.productionDate + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_NUMBEROFCANDIDATEBULLS, loginDTO)):(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_NUMBEROFCANDIDATEBULLS, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'numberOfCandidateBulls_div_<%=i%>'>	
		<input type='number' class='form-control'  name='numberOfCandidateBulls' id = 'numberOfCandidateBulls_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.numberOfCandidateBulls + "'"):("'" + 0 + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_SOURCETYPE, loginDTO)):(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_SOURCETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'sourceType_div_<%=i%>'>	
		<select class='form-control'  name='sourceType' id = 'sourceType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Candidate_bull_productionAnotherDBDAO.getOptions(Language, "select", "source", "sourceType_select_" + i, "form-control", "sourceType", candidate_bull_productionDTO.sourceType + "");
}
else
{			
			Options = Candidate_bull_productionAnotherDBDAO.getOptions(Language, "select", "source", "sourceType_select_" + i, "form-control", "sourceType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(candidate_bull_productionDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_CANDIDATE_BULL_PRODUCTION_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_CANDIDATE_BULL_PRODUCTION_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_CANDIDATE_BULL_PRODUCTION_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.CANDIDATE_BULL_PRODUCTION_ADD_CANDIDATE_BULL_PRODUCTION_SUBMIT_BUTTON, loginDTO));
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

	console.log("found date = " + document.getElementById('productionDate_date_Date_' + row).value);
	document.getElementById('productionDate_date_' + row).value = new Date(document.getElementById('productionDate_date_Date_' + row).value).getTime();
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






