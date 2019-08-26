
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="progeny_record.Progeny_recordDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="user.*"%>
<%@page import="centre.CentreDAO"%>

<%
Progeny_recordDTO progeny_recordDTO;
progeny_recordDTO = (Progeny_recordDTO)request.getAttribute("progeny_recordDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(progeny_recordDTO == null)
{
	progeny_recordDTO = new Progeny_recordDTO();
	
}
System.out.println("progeny_recordDTO = " + progeny_recordDTO);

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
	formTitle = LM.getText(LC.PROGENY_RECORD_EDIT_PROGENY_RECORD_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.PROGENY_RECORD_ADD_PROGENY_RECORD_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
String userfullName = userDTO.fullName;
int userCentre = userDTO.centreType;
CentreDAO centreDAO = new CentreDAO();
String centreName = centreDAO.getCentreNameByCentreID((long)userCentre);
%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="Progeny_recordServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	












<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="progeny_record.Progeny_recordAnotherDBDAO"%>
<%
String Language = LM.getText(LC.PROGENY_RECORD_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_CENTRETYPE, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_CENTRETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'centreType_div_<%=i%>'>	
		<%
		if(userDTO.roleID != 1)
		{
			out.println(" <label class='col-lg-6 ' id = 'centreType_div_'" + i + "'>"	
					+ centreName
					+ "</label>"		
					+ "<input type='hidden' name='centreType' id='centreType' value='" + userCentre + "' />");
		}
		else
		{
			out.println("<select class='form-control'  name='centreType' id = 'centreType_select_" + i + "'>");
			if(actionName.equals("edit"))
			{
						Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", progeny_recordDTO.centreType + "");
			}
			else
			{			
						Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
			}
			out.print(Options);
			out.println("</select>");
		}
	%>	
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_BULLTYPE, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_BULLTYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'bullType_div_<%=i%>'>	
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", progeny_recordDTO.bullType + "");
}
else
{			
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_NUMBEROFMALECALFS, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_NUMBEROFMALECALFS, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'numberOfMaleCalfs_div_<%=i%>'>	
		<input type='number' class='form-control'  name='numberOfMaleCalfs' id = 'numberOfMaleCalfs_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.numberOfMaleCalfs + "'"):("'" + 0 + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_NUMBEROFFEMALECALFS, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_NUMBEROFFEMALECALFS, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'numberOfFemaleCalfs_div_<%=i%>'>	
		<input type='number' class='form-control'  name='numberOfFemaleCalfs' id = 'numberOfFemaleCalfs_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.numberOfFemaleCalfs + "'"):("'" + 0 + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_DATEOFENTRY, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_DATEOFENTRY, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'dateOfEntry_div_<%=i%>'>	
		<input type='date' class='form-control'  name='dateOfEntry_Date_<%=i%>' id = 'dateOfEntry_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfEntry = format_dateOfEntry.format(new Date(progeny_recordDTO.dateOfEntry)).toString();
	out.println("'" + formatted_dateOfEntry + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfEntry' id = 'dateOfEntry_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.dateOfEntry + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.PROGENY_RECORD_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.PROGENY_RECORD_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(progeny_recordDTO.description):(" ")%></textarea>		
											
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + progeny_recordDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PROGENY_RECORD_EDIT_PROGENY_RECORD_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PROGENY_RECORD_ADD_PROGENY_RECORD_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PROGENY_RECORD_EDIT_PROGENY_RECORD_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PROGENY_RECORD_ADD_PROGENY_RECORD_SUBMIT_BUTTON, loginDTO));
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






