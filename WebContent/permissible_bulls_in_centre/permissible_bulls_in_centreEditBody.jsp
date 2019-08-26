
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page
	import="permissible_bulls_in_centre.Permissible_bulls_in_centreDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%@page import="bull.BullDAO"%>
<%@page import="bull.BullAnotherDBDAO"%>
<%@page import="bull.BullDTO"%>
<%@page import="breed.BreedDAO"%>

<%@page import="centre.CentreDAO"%>
<%@page import="centre.CentreDTO"%>


<%
Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO;
permissible_bulls_in_centreDTO = (Permissible_bulls_in_centreDTO)request.getAttribute("permissible_bulls_in_centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(permissible_bulls_in_centreDTO == null)
{
	permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
	
}
System.out.println("permissible_bulls_in_centreDTO = " + permissible_bulls_in_centreDTO);

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
	formTitle = LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_PERMISSIBLE_BULLS_IN_CENTRE_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_ADD_PERMISSIBLE_BULLS_IN_CENTRE_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;

BullDAO bullDAO = new BullDAO();
ArrayList<String>  bullIDs = (ArrayList<String>)bullDAO.getIDs( loginDTO);

CentreDAO centreDAO = new CentreDAO();
ArrayList<String>  centreIDs = (ArrayList<String>)centreDAO.getIDs( loginDTO);

%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal"
			action="Permissible_bulls_in_centreServlet?actionType=<%=actionName%>&identity=<%=ID%>"
			id="bigform" name="bigform" method="POST"
			enctype="multipart/form-data"
			onsubmit="return PreprocessBeforeSubmiting(0,<%=actionName.equals("edit")?false:true%>)">
			<div class="form-body">






































				<%@ page import="java.text.SimpleDateFormat"%>
				<%@ page import="java.util.Date"%>
				<%@ page
					import="permissible_bulls_in_centre.Permissible_bulls_in_centreAnotherDBDAO"%>
				<%
String Language = LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
date.setYear(date.getYear() + 2);
String futuredatestr = dateFormat.format(date);
%>


				<input type='hidden' class='form-control' name='iD'
					id='iD_text_<%=i%>' value='<%=ID%>' /> 
				<label
					class="col-sm-3 control-label"> <%=(actionName.equals("edit"))?(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_BULLTYPE, loginDTO)):(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_ADD_BULLTYPE, loginDTO))%>
				</label>
				<div class="form-group ">
					<div class="col-sm-6 " id='bullType_div_<%=i%>'>
						<select class='form-control' name='bullType'
							id='bullType_select_<%=i%>' onchange = 'setBullBreed()'>
							<option value="-1">Select</option>
							<%
if(actionName.equals("edit"))
{
			Options = Permissible_bulls_in_centreAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", permissible_bulls_in_centreDTO.bullType + "");
}
else
{			
			Options = Permissible_bulls_in_centreAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
						</select>
					</div>
				</div>
				
				<label
					class="col-sm-3 control-label"> Breed
				</label>
				<div class="form-group ">
					<div class="col-sm-6 " >
						<label  class='form-control' id='selected_breed'>Unknown</label>

					</div>
				</div>
				
				

				<%
					BreedDAO breedDAO = new BreedDAO();
					for(int j = 0; j <bullIDs.size(); j ++)
					{
						long bullID =  Long.parseLong(bullIDs.get(j));
						out.println("<input type='hidden' class='form-control'  name='bull_breed_" + bullID +"' id = 'bull_breed_" + bullID +
								"' value='" + breedDAO.getBreedNameByBullID(bullID) + "'  >");
					}
					out.println("<input type='hidden' class='form-control'  name='centre_ids' id = 'centre_ids' value='" + centreIDs.size() + "'  >");
				%>

				<table id="tableData" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Number</th>
							<th><%=LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_CENTRETYPE, loginDTO)%></th>
							<th><%=LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_DATEOFENTRY, loginDTO)%></th>
							<th><%=LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_DATEOFEXPIRATION, loginDTO)%></th>
							<th><%=LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_DESCRIPTION, loginDTO)%></th>
							<th>Check</th>

						</tr>
					</thead>
					<tbody>
						<%
						for(int j = 0; j <centreIDs.size(); j ++)
						{
							try
							{
								long lCentreID = Long.parseLong(centreIDs.get(j));
								out.println("<tr>");
								
								out.println("<td id = 'number_" + j + "'>");
								out.println(j + 1);
								out.println("</td>");
								
								out.println("<td name = 'centreType_" + j + "' id = 'centreType_select_" + j + "'>");
								out.println(centreDAO.getCentreNameByCentreID(lCentreID));
								out.println("</td>");
								
								out.println("<td>");
								out.println("<input type='date' class='form-control'  name='dateOfEntry_Real_" + j + "' id = 'dateOfEntry_Real_" + j
										+ "' value='" +  datestr + "'>");
								out.println("<input type='hidden' class='form-control'  name='dateOfEntry_" + j + "'  id = 'dateOfEntry_Date_" + j +
										"' value=' 0'  >");
								out.println("</td>");
								
								out.println("<td>");
								out.println("<input type='date' class='form-control'  name='dateOfExpiration_Real_" + j + "' id = 'dateOfExpiration_Real_" + j
										+ "' value='" +  futuredatestr + "'>");
								out.println("<input type='hidden' class='form-control'  name='dateOfExpiration_" + j + "'  id = 'dateOfExpiration_Date_" + j +
										"' value=' 0'  >");
								out.println("</td>");
								
								out.println("<td>");
								out.println("<input type='text' class='form-control'  name='description_" + j + "' id = 'description_" + j + "'>");	
								out.println("</td>");
								
								out.println("<td>");
								out.println("<input type='checkbox' class='form-control'  name='isChecked_" + j + "' id = 'isChecked_" + j + "' value = '1'>");	
								out.println("</td>");
								
								
								out.println("</tr>");
							}
							catch(Exception e)
							{
								
							}
						}
						%>
					</tbody>
				</table>





				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
						<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_PERMISSIBLE_BULLS_IN_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_ADD_PERMISSIBLE_BULLS_IN_CENTRE_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
						<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_PERMISSIBLE_BULLS_IN_CENTRE_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_ADD_PERMISSIBLE_BULLS_IN_CENTRE_SUBMIT_BUTTON, loginDTO));
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

function setBullBreed()
{
	var e = document.getElementById("bullType_select_0");
	var selectedBullID = e.value;
	console.log("selectedBullID = " + selectedBullID);
	document.getElementById("selected_breed").innerHTML = document.getElementById("bull_breed_" +  selectedBullID).value;
}


function isNumeric(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function PreprocessBeforeSubmiting(row, validate)
{
	for(row = 0; row < document.getElementById('centre_ids').value; row ++)
	{
		if(document.getElementById("isChecked_" + row).checked)
		{
			console.log("found entry date = " + document.getElementById('dateOfEntry_Real_' + row).value);
			document.getElementById('dateOfEntry_Date_' + row).value = new Date(document.getElementById('dateOfEntry_Real_' + row).value).getTime();
			
			console.log("found expiry date = " + document.getElementById('dateOfExpiration_Real_' + row).value);
			document.getElementById('dateOfExpiration_Date_' + row).value = new Date(document.getElementById('dateOfExpiration_Real_' + row).value).getTime();
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






