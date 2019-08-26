
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="stick.StickDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
StickDTO stickDTO;
stickDTO = (StickDTO)request.getAttribute("stickDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(stickDTO == null)
{
	stickDTO = new StickDTO();
	
}
System.out.println("stickDTO = " + stickDTO);

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
	formTitle = LM.getText(LC.STICK_EDIT_STICK_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.STICK_ADD_STICK_ADD_FORMNAME, loginDTO);
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
		<form class="form-horizontal" action="StickServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,'<%=actionName%>')">
			<div class="form-body">
				
				
				
























	











<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.STICK_EDIT_NAMEEN, loginDTO)):(LM.getText(LC.STICK_ADD_NAMEEN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameEn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.nameEn + "'"):("''")%> />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.STICK_EDIT_NAMEBN, loginDTO)):(LM.getText(LC.STICK_ADD_NAMEBN, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nameBn_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.nameBn + "'"):("''")%> />					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.STICK_EDIT_SEMENCOLLECTIONID, loginDTO)):(LM.getText(LC.STICK_ADD_SEMENCOLLECTIONID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'semenCollectionId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='semenCollectionId' id = 'semenCollectionId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.semenCollectionId + "'"):("'" + "0" + "'")%> />					
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + stickDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.STICK_EDIT_STICK_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.STICK_ADD_STICK_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.STICK_EDIT_STICK_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.STICK_ADD_STICK_SUBMIT_BUTTON, loginDTO));
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
		if(!document.getElementById('nameEn_text_' + row).checkValidity())
		{
			empty_fields += "'nameEn'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(!document.getElementById('nameBn_text_' + row).checkValidity())
		{
			empty_fields += "'nameBn'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}


		if(empty_fields != "")
		{
			if(validate == "inplaceedit")
			{
				$('<input type="submit">').hide().appendTo($('#tableForm')).click().remove(); 
				return false;
			}
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






