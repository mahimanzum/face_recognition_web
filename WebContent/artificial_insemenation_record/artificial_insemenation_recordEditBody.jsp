
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="artificial_insemenation_record.Artificial_insemenation_recordDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bull.BullDAO"%>
<%@page import="bull.BullAnotherDBDAO"%>
<%@page import="bull.BullDTO"%>
<%@page import="breed.BreedDAO"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="user.*"%>
<%@page import="centre.CentreDAO"%>

<%
Artificial_insemenation_recordDTO artificial_insemenation_recordDTO;
artificial_insemenation_recordDTO = (Artificial_insemenation_recordDTO)request.getAttribute("artificial_insemenation_recordDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(artificial_insemenation_recordDTO == null)
{
	artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
	
}
System.out.println("artificial_insemenation_recordDTO = " + artificial_insemenation_recordDTO);

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
	formTitle = LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_ARTIFICIAL_INSEMENATION_RECORD_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_ARTIFICIAL_INSEMENATION_RECORD_ADD_FORMNAME, loginDTO);
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
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
String userfullName = userDTO.fullName;
int userCentre = userDTO.centreType;
CentreDAO centreDAO = new CentreDAO();
String centreName = centreDAO.getCentreNameByCentreID((long)userCentre);
%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="artificial_insemenation_record.Artificial_insemenation_recordAnotherDBDAO"%>
<%
String Language = LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>





<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="Artificial_insemenation_recordServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
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
				

























	














		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_CENTRETYPE, loginDTO)):(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_CENTRETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'centreType_div_<%=i%>'>	
		<%
		if(userDTO.roleID != 1)
		{
			out.println(" <label class='col-sm-3 control-label' style = 'text-align: left;' id = 'centreType_div_'" + i + "'>"	
					+ centreName
					+ "</label>"		
					+ "<input type='hidden' name='centreType' id='centreType' value='" + userCentre + "' />");
		}
		else
		{
			out.println("<select class='form-control'  name='centreType' id = 'centreType_select_" + i + "'>");
			if(actionName.equals("edit"))
			{
						Options = Artificial_insemenation_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", artificial_insemenation_recordDTO.centreType + "");
			}
			else
			{			
						Options = Artificial_insemenation_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
			}
			out.print(Options);
			out.println("</select>");
		}
	%>	
		
	</div>
</div>	


<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_ENTRYDATE, loginDTO)):(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_ENTRYDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'entryDate_div_<%=i%>'>	
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(artificial_insemenation_recordDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + artificial_insemenation_recordDTO.entryDate + "'"):("'" + "0" + "'")%> >
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_DESCRIPTION, loginDTO)):(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_DESCRIPTION, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'description_div_<%=i%>'>	
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(artificial_insemenation_recordDTO.description):(" ")%></textarea>		
											
	</div>
</div>	
<div class="table-responsive">

					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Number</th>	
								<th><%=LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_BULLTYPE, loginDTO)%></th>
								<th>Breed</th>								
								<th><%=LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_NOOFAI, loginDTO)%></th>								
								
							</tr>
						</thead>
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
									Options = BullAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select__" + j, "form-control", "colorType" );
									out.println("<option value='-1'>Select Bull</option>");
									out.println(Options);
									out.println("</select>");
									out.println("</td>");
									
									out.println("<td id = 'bull_breed_label_" + j + "'>");
									out.println("unknown");
									out.println("</td>");
									
									out.println("<td>");
									out.println("<input type='number' class='form-control'  name='noOfAI_" + j + "' id = 'noOfAI_number_" + j + "' min='0' max='1000000' value='0'>");									
									out.println("</td>");
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
				
	
		
				
	
		
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + artificial_insemenation_recordDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_ARTIFICIAL_INSEMENATION_RECORD_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_ARTIFICIAL_INSEMENATION_RECORD_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_EDIT_ARTIFICIAL_INSEMENATION_RECORD_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.ARTIFICIAL_INSEMENATION_RECORD_ADD_ARTIFICIAL_INSEMENATION_RECORD_SUBMIT_BUTTON, loginDTO));
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


function setBullBreed(j)
{
	var e = document.getElementById("bullType_select_"  + j);
	var selectedBullID = e.value;
	console.log("selectedBullID = " + selectedBullID);
	document.getElementById("bull_breed_label_" + j).innerHTML = document.getElementById("bull_breed_" +  selectedBullID).value;
}


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






