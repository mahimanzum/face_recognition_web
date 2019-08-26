
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="unvalidated_word.Unvalidated_wordDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
Unvalidated_wordDTO unvalidated_wordDTO;
unvalidated_wordDTO = (Unvalidated_wordDTO)request.getAttribute("unvalidated_wordDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(unvalidated_wordDTO == null)
{
	unvalidated_wordDTO = new Unvalidated_wordDTO();
	
}
System.out.println("unvalidated_wordDTO = " + unvalidated_wordDTO);

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
	formTitle = LM.getText(LC.UNVALIDATED_WORD_EDIT_UNVALIDATED_WORD_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.UNVALIDATED_WORD_ADD_UNVALIDATED_WORD_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;

String value = "";
Unvalidated_wordDTO row = unvalidated_wordDTO;
%>



<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"><i class="fa fa-gift"></i><%=formTitle%></h3>
	</div>
	<div class="box-body">
		<form class="form-horizontal" action="Unvalidated_wordServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.UNVALIDATED_WORD_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>



		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.UNVALIDATED_WORD_EDIT_WORD, loginDTO)):(LM.getText(LC.UNVALIDATED_WORD_ADD_WORD, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'word_div_<%=i%>'>	
		<input type='file' class='form-control'  name='word' id = 'word_database_<%=i%>' value=<%=actionName.equals("edit")?("'" + unvalidated_wordDTO.word + "'"):("'" + " " + "'")%>  />	
						
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.UNVALIDATED_WORD_EDIT_VERDICTCOUNT, loginDTO)):(LM.getText(LC.UNVALIDATED_WORD_ADD_VERDICTCOUNT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'verdictCount_div_<%=i%>'>	
		<input type='text' class='form-control'  name='verdictCount' id = 'verdictCount_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + unvalidated_wordDTO.verdictCount + "'"):("'" + "0" + "'")%>   />					
	</div>
</div>			
				
	
<label class="col-lg-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.UNVALIDATED_WORD_EDIT_ISCORRECT, loginDTO)):(LM.getText(LC.UNVALIDATED_WORD_ADD_ISCORRECT, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-lg-6 " id = 'isCorrect_div_<%=i%>'>	
		<input type='checkbox' class='form-control'  name='isCorrect' id = 'isCorrect_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(unvalidated_wordDTO.isCorrect).equals("true"))?("checked"):""%>  ><br>						


	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + unvalidated_wordDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.UNVALIDATED_WORD_EDIT_UNVALIDATED_WORD_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.UNVALIDATED_WORD_ADD_UNVALIDATED_WORD_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.UNVALIDATED_WORD_EDIT_UNVALIDATED_WORD_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.UNVALIDATED_WORD_ADD_UNVALIDATED_WORD_SUBMIT_BUTTON, loginDTO));
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


function getOfficer(officer_id, officer_select)
{
	console.log("getting officer");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText.includes('option'))
			{
				console.log("got response for officer");
				document.getElementById(officer_select).innerHTML = this.responseText ;
				
				if(document.getElementById(officer_select).length > 1)
				{
					document.getElementById(officer_select).removeAttribute("disabled");
				}	
			}
			else
			{
				console.log("got errror response for officer");
			}
			
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	xhttp.open("POST", "Unvalidated_wordServlet?actionType=getGRSOffice&officer_id=" + officer_id, true);
	xhttp.send();
}


function getLayer(layernum, layerID, childLayerID, selectedValue)
{
	console.log("getting layer");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText.includes('option'))
			{
				console.log("got response");
				document.getElementById(childLayerID).innerHTML = this.responseText ;
			}
			else
			{
				console.log("got errror response");
			}
			
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	};
	xhttp.open("POST", "Unvalidated_wordServlet?actionType=getGRSLayer&layernum=" + layernum + "&layerID=" 
			+ layerID + "&childLayerID=" + childLayerID + "&selectedValue=" + selectedValue, true);
	xhttp.send();
}

function layerselected(layernum, layerID, childLayerID, hiddenInput, hiddenInputForTopLayer, officerElement)
{
	var layervalue = document.getElementById(layerID).value;
	console.log("layervalue = " + layervalue);
	document.getElementById(hiddenInput).value = layervalue;
	if(layernum == 0)
	{
		document.getElementById(hiddenInputForTopLayer).value = layervalue;
	}
	if(layernum == 0 || (layernum == 1 && document.getElementById(hiddenInputForTopLayer).value == 3))
	{
		document.getElementById(childLayerID).setAttribute("style", "display: inline;");
		getLayer(layernum, layerID, childLayerID, layervalue);
	}
	
	if(officerElement !== null)
	{
		getOfficer(layervalue, officerElement);
	}
	
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

	if(document.getElementById('isCorrect_checkbox_' + row).checked)
	{
		document.getElementById('isCorrect_checkbox_' + row).value = "true";
	}
	else
	{
		document.getElementById('isCorrect_checkbox_' + row).value = "false";
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






