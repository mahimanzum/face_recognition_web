
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="semen_requisition.Semen_requisitionDTO"%>
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
Semen_requisitionDTO semen_requisitionDTO;
semen_requisitionDTO = (Semen_requisitionDTO)request.getAttribute("semen_requisitionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(semen_requisitionDTO == null)
{
	semen_requisitionDTO = new Semen_requisitionDTO();
	
}
System.out.println("semen_requisitionDTO = " + semen_requisitionDTO);

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
	formTitle = LM.getText(LC.SEMEN_REQUISITION_EDIT_SEMEN_REQUISITION_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.SEMEN_REQUISITION_ADD_SEMEN_REQUISITION_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="Semen_requisitionServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,<%=actionName.equals("edit")?false:true%>)">
			<div class="form-body">
				
				
				
























	










<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="semen_requisition.Semen_requisitionAnotherDBDAO"%>
<%@ page import="java.util.*"%>
<%
String Language = LM.getText(LC.SEMEN_REQUISITION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												

		<input type='hidden' class='form-control'  name='groupId' id = 'groupId_hidden_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_requisitionDTO.groupId + "'"):("'" + "0" + "'")%>/>
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.SEMEN_REQUISITION_EDIT_CENTRETYPE, loginDTO)):(LM.getText(LC.SEMEN_REQUISITION_ADD_CENTRETYPE, loginDTO))%>
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
						Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", semen_requisitionDTO.centreType + "");
			}
			else
			{			
						Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
			}
			out.print(Options);
			out.println("</select>");
		}
	%>	

		
	</div>
</div>

<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.SEMEN_REQUISITION_EDIT_REQUISITIONDATE, loginDTO)):(LM.getText(LC.SEMEN_REQUISITION_ADD_REQUISITIONDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'requisitionDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='requisitionDate_Date_<%=i%>' id = 'requisitionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_requisitionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_requisitionDate = format_requisitionDate.format(new Date(semen_requisitionDTO.requisitionDate)).toString();
	out.println("'" + formatted_requisitionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
>
		<input type='hidden' class='form-control'  name='requisitionDate' id = 'requisitionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_requisitionDTO.requisitionDate + "'"):("'" + "0" + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.SEMEN_REQUISITION_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.SEMEN_REQUISITION_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>'><%=actionName.equals("edit")?(semen_requisitionDTO.description):(" ")%></textarea>		
											
	</div>
</div>		


<%
List<Integer> breedids = Semen_requisitionAnotherDBDAO.getIDs("breed", "id", "");
out.print("</br><span class=\"caption-subject bold uppercase\">Choose Dose</span>");
out.print("<div class=\"form-group \" >");
out.print("</div>");
for(int j = 0; j < breedids.size(); j ++)
{
	out.print("<label class=\"col-sm-3 control-label\">");
	out.print(Semen_requisitionAnotherDBDAO.getName(breedids.get(j), "breed", Language.equals("English")?"name_en":"name_bn", "id"));
	out.print("</label>");
	
	out.print("<div class=\"form-group \">");
	out.print("<div class=\"col-sm-6 \" id = \"noOfDose_div_" + i + "_" + j + "\">");
	out.print("<input type=\"number\" class=\"form-control\"  name=\"noOfDose_" + j + "\" min=\"0\" max=\"100000\" value = \"0\" />");
	out.print("</div>");
	out.print("</div>");
}
out.print("</br>");
%>		
				
	
	
	
		
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + semen_requisitionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.SEMEN_REQUISITION_EDIT_SEMEN_REQUISITION_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.SEMEN_REQUISITION_ADD_SEMEN_REQUISITION_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.SEMEN_REQUISITION_EDIT_SEMEN_REQUISITION_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.SEMEN_REQUISITION_ADD_SEMEN_REQUISITION_SUBMIT_BUTTON, loginDTO));
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
	if(validate)
	{
		var empty_fields = "";
		var i = 0;
		if(empty_fields != "")
		{
			alert(empty_fields + " cannot be empty.");
			return false;
		}
	}

	console.log("found date = " + document.getElementById('requisitionDate_date_Date_' + row).value);
	document.getElementById('requisitionDate_date_' + row).value = new Date(document.getElementById('requisitionDate_date_Date_' + row).value).getTime();
	
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






