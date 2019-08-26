
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="semen_distribution.Semen_distributionDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@ page import="semen_requisition.Semen_requisitionAnotherDBDAO"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Semen_distributionDTO semen_distributionDTO;
semen_distributionDTO = (Semen_distributionDTO)request.getAttribute("semen_distributionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(semen_distributionDTO == null)
{
	semen_distributionDTO = new Semen_distributionDTO();
	
}
System.out.println("semen_distributionDTO = " + semen_distributionDTO);

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
	formTitle = LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_SEMEN_DISTRIBUTION_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.SEMEN_DISTRIBUTION_ADD_SEMEN_DISTRIBUTION_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;
String groupID = request.getParameter("groupID");
String centre = request.getParameter("centre");
String Language = LM.getText(LC.SEMEN_REQUISITION_EDIT_LANGUAGE, loginDTO);
String centreName = Semen_requisitionAnotherDBDAO.getName(Integer.parseInt(centre), "centre", Language.equals("English")?"name_en":"name_bn", "id");
%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i>Distribute Semen For <%=centreName%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="Semen_distributionServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,<%=actionName.equals("edit")?false:true%>)">
			<div class="form-body">
				
				
				
























	










<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="semen_distribution.Semen_distributionAnotherDBDAO"%>
<%@page import="semen_requisition.*"%>
<%@page import="semen_collection.*"%>
<%@page import="semen_distribution.*"%>
<%@page import="bull.*"%>
<%
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
Semen_collectionDAO semen_collectionDAO = new Semen_collectionDAO();
Semen_distributionDAO semen_distributionDAO = new Semen_distributionDAO();
BullDAO bullDAO = new BullDAO();
int iCentre = 0;
long dateInMillis = 0;
try
{
	dateInMillis= date.getTime();
	iCentre = Integer.parseInt(centre);
}
catch (Exception e) 
{
    e.printStackTrace();
}
%>


		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	


<%
List<Integer> breedids = Semen_requisitionAnotherDBDAO.getIDs("breed", "id", " breed.id in (select breed_type from semen_requisition where group_id = " + groupID +  ")");

out.print("<table id=\"tableData\" class=\"table table-bordered\">");

out.print("<thead>");
out.print("<tr class=\"success\">");

out.print("<th>");
out.print("<h4>Breed</h4>");
out.print("</th>");

out.print("<th>");
out.print("<h4>Stock</h4>");
out.print("</th>");

out.print("<th>");
out.print("<h4>Requested</h4>");
out.print("</th>");

out.print("</tr>");

out.print("</thead>");

for(int j = 0; j < breedids.size(); j ++)
{
	int remainingDose = semen_collectionDAO.getTotalDoseOfBreed(breedids.get(j), iCentre, dateInMillis) - semen_distributionDAO.getTotalDoseOfBreed(breedids.get(j), iCentre, dateInMillis);
	if(remainingDose <= 0)
	{
		continue;
	}
	
	
	out.print("<thead>");
	out.print("<tr class=\"info\">");
	
	out.print("<th><h5>");
	out.print(Semen_requisitionAnotherDBDAO.getName(breedids.get(j), "breed", Language.equals("English")?"name_en":"name_bn", "id"));
	out.print("</h5></th>");
	
	
	
	out.print("<th><h5>");
	out.print(remainingDose + " doses");
	out.print("</h5></th>");
	
	
	
	List<Integer> req_id = Semen_requisitionAnotherDBDAO.getIDs("semen_requisition", "id", " breed_type = " + breedids.get(j) + " and group_id = " + groupID);
	out.print("<th><h5>");
	out.print(Semen_requisitionAnotherDBDAO.getName(req_id.get(0), "semen_requisition", "no_of_dose", "id") + " doses");
	out.print("</h5></th>");
	
	out.print("</tr>");
	out.print("</thead>");
	
	out.print("<tbody>");
	
	
	List<Long> bull_ids = bullDAO.getIDsWithSearchCriteria("breed_type", breedids.get(j));
	int icount = 0;
	for(int k = 0; k < bull_ids.size(); k ++)
	{
		int remainingBullDose = semen_collectionDAO.getTotalDoseOfBull(bull_ids.get(k)) - semen_distributionDAO.getTotalDoseOfBull(bull_ids.get(k));
		if(remainingBullDose <= 0)
		{
			continue;
		}
		
		if(semen_collectionDAO.isBullAllowed(bull_ids.get(k), iCentre, dateInMillis) == false)
		{
			System.out.println("this bull is not allowed " + bull_ids.get(k));
			continue;
		}

		
		out.print("<tr>");
		out.print("<td>");
		out.print(bullDAO.getBullName(bull_ids.get(k)));
		out.print("</td>");
		
		out.print("<td>");
		out.print(remainingBullDose + " doses");
		out.print("</td>");
		
		
		out.print("<td>");
		out.print("<input type='number' class='form-control'  name='noOfDose_" + j + "_" + k + "' id = 'noOfDose_text_" + j + "_" + k + "' value='0' min = '0' max = '" + remainingBullDose +  "'/>	");
		out.print("</td>");
		out.print("</tr>");
		icount++;
	}
	
	if(icount == 0)
		
	
	out.print("</tbody>");
	



}

out.print("<thead>");
out.print("<tr>");

out.print("<th><h5>");
out.print("Add Date and Description");
out.print("</h5></th>");

out.print("<th><h5>");
%>
		<input type='date' class='form-control'  name='transactionDate_Date_<%=i%>' id = 'transactionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_transactionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_transactionDate = format_transactionDate.format(new Date(semen_distributionDTO.transactionDate)).toString();
	out.println("'" + formatted_transactionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
>
	<input type='hidden' class='form-control'  name='transactionDate' id = 'transactionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_distributionDTO.transactionDate + "'"):("'" + "0" + "'")%>>
</div>	
<% 
out.print("</h5></th>");

out.print("<th><h5>");
%>
		<input type = 'text' class='form-control'  name='description' id = 'description_textarea_<%=i%>'></input>		
<%
out.print("</h5></th>");

out.print("</tr>");
out.print("</thead>");

out.print("</table>");
out.print("</br>");
%>													
	
	
				
	<input type='hidden' class='form-control'  name='requisitionId' id = 'requisitionId_text_<%=i%>' value='<%=groupID%>'/>
		
				

	<input type='hidden' class='form-control'  name='groupId' id = 'groupId_hidden_<%=i%>' value='<%=groupID%>'/>
												
			
				

	<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + semen_distributionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_SEMEN_DISTRIBUTION_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.SEMEN_DISTRIBUTION_ADD_SEMEN_DISTRIBUTION_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_SEMEN_DISTRIBUTION_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.SEMEN_DISTRIBUTION_ADD_SEMEN_DISTRIBUTION_SUBMIT_BUTTON, loginDTO));
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

	console.log("found date = " + document.getElementById('transactionDate_date_Date_' + row).value);
	document.getElementById('transactionDate_date_' + row).value = new Date(document.getElementById('transactionDate_date_Date_' + row).value).getTime();
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






