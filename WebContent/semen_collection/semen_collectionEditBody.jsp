
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="semen_collection.Semen_collectionDTO"%>
<%@page import="semen_collection.Semen_collectionDAO"%>
<%@page import="bull.BullDAO"%>
<%@page import="bull.BullAnotherDBDAO"%>
<%@page import="bull.BullDTO"%>
<%@page import="breed.BreedDAO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Semen_collectionDTO semen_collectionDTO;
semen_collectionDTO = (Semen_collectionDTO)request.getAttribute("semen_collectionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(semen_collectionDTO == null)
{
	semen_collectionDTO = new Semen_collectionDTO();
	
}
System.out.println("semen_collectionDTO = " + semen_collectionDTO);

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
	formTitle = LM.getText(LC.SEMEN_COLLECTION_EDIT_SEMEN_COLLECTION_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.SEMEN_COLLECTION_ADD_SEMEN_COLLECTION_ADD_FORMNAME, loginDTO);
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
%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	
				
				
























	











<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="semen_collection.Semen_collectionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.SEMEN_COLLECTION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>




<form class="form-horizontal" action="Semen_collectionServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting()">
<div class="table-responsive">
					<%
					BreedDAO breedDAO = new BreedDAO();
					for(int j = 0; j <bullIDs.size(); j ++)
					{
						long bullID =  Long.parseLong(bullIDs.get(j));
						out.println("<input type='hidden' class='form-control'  name='bull_breed_" + bullID +"' id = 'bull_breed_" + bullID +
								"' value='" + breedDAO.getBreedNameByBullID(bullID) + "'  >");
					}
					%>
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Number</th>	
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_BULLTYPE, loginDTO)%></th>
								<th>Breed</th>								
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_VOLUME, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_COLORTYPE, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_DENSITY, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_PROGRESSIVEMORTALITY, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_NOOFDOSE, loginDTO)%></th>								
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_TRANSACTIONDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_COLLECTION_ADD_DESCRIPTION, loginDTO)%></th>
								
								
							</tr>
						</thead>
						<tbody>
						<%
							
							
							for(int j = 0; j <50; j ++)
							{
								try
								{
									
									out.println("<tr>");
									
									out.println("<td id = 'number_" + j + "'>");
									out.println(j + 1);
									out.println("</td>");
									
									out.println("<td>");
									out.println("<select onchange = 'setBullBreed(" + j + ")' class='form-control'  name='bullType_" + j + "' id = 'bullType_select_" + j + "'>");
									Options = BullAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select__" + j, "form-control", "bullType" );
									out.println("<option value='-1'>Select Bull</option>");
									out.println(Options);
									out.println("</select>");
									out.println("</td>");
									
									out.println("<td id = 'bull_breed_label_" + j + "'>");
									out.println("unknown");
									out.println("</td>");
									
									out.println("<td>");
									out.println("<input type='number' class='form-control'  name='volume_" + j + "' id = 'volume_text_" + j + "' min='0' max='1000000' value='0'>");									
									out.println("</td>");
									
									out.println("<td>");
									out.println("<select class='form-control'  name='colorType_" + j + "' id = 'colorType_select_" + j + "'>");
									Options = Semen_collectionAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + j, "form-control", "colorType" );	
									out.println(Options);
									out.println("</select>");
									out.println("</td>");
									
									out.println("<td>");
									out.println("<input type='number' class='form-control'  name='density_" + j + "' id = 'density_text_" + j + "' min='0' max='1000000' value='0'>");									
									out.println("</td>");
									
									
									out.println("<td>");
									out.println("<input type='number' class='form-control'  name='progressiveMortality_" + j + "' id = 'progressiveMortality_text_" + j + "' min='0' max='1000000' value='0'>");									
									out.println("</td>");
									
									out.println("<td>");
									out.println("<input type='number' class='form-control'  name='noOfDose_" + j + "' id = 'noOfDose_number_" + j + "' min='0' max='1000000' value='0'>");									
									out.println("</td>");
									
									
									
									
									
									
									
									
									
									out.println("<td>");
									out.println("<input type='date' class='form-control'  name='transactionDate_Real_" + j + "' id = 'transactionDate_Real_" + j
											+ "' value='" +  datestr + "'>");
									out.println("<input type='hidden' class='form-control'  name='transactionDate_" + j + "'  id = 'transactionDate_Date_" + j +
											"' value=' 0'  >");
									out.println("</td>");
									
									out.println("<td>");
									out.println("<input type='text' class='form-control'  name='description_" + j + "' id = 'description_" + j + "'>");	
									out.println("</td>");
									
									out.println("</tr>");
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
						%>
						</tbody>
					</table>
</div>
<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.println(LM.getText(LC.SEMEN_COLLECTION_EDIT_SEMEN_COLLECTION_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.println(LM.getText(LC.SEMEN_COLLECTION_ADD_SEMEN_COLLECTION_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.println(LM.getText(LC.SEMEN_COLLECTION_EDIT_SEMEN_COLLECTION_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.println(LM.getText(LC.SEMEN_COLLECTION_ADD_SEMEN_COLLECTION_SUBMIT_BUTTON, loginDTO));
					}
					%>
					</button>
</div>
</form>



<script src="nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">

function setBullBreed(j)
{
	var e = document.getElementById("bullType_select_"  + j);
	var selectedBullID = e.value;
	console.log("selectedBullID = " + selectedBullID);
	document.getElementById("bull_breed_label_" + j).innerHTML = document.getElementById("bull_breed_" +  selectedBullID).value;
}
function isNumeric(n) 
{
  return !isNaN(parseFloat(n)) && isFinite(n);
}

function PreprocessBeforeSubmiting()
{
	try
	{
		for(row = 0; row < 50; row ++)
		{
			console.log("found date = " + document.getElementById('transactionDate_Real_' + row).value);
			document.getElementById('transactionDate_Date_' + row).value = new Date(document.getElementById('transactionDate_Real_' + row).value).getTime();
		}
	}
	catch(e)
	{
		console.log(e);
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






