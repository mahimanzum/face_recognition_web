
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="theme.ThemeDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
ThemeDTO themeDTO;
themeDTO = (ThemeDTO)request.getAttribute("themeDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(themeDTO == null)
{
	themeDTO = new ThemeDTO();
	
}
System.out.println("themeDTO = " + themeDTO);

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
	formTitle = LM.getText(LC.THEME_EDIT_THEME_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.THEME_ADD_THEME_ADD_FORMNAME, loginDTO);
}
String fieldError = "";
String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
%>


<style>
	.ul-box{
		width:65%;
		margin-left: 17%;
		margin-bottom: 1%;
		background-color: #eee;
		padding-top: 40px;
		padding-bottom: 30px;
	}
</style>

<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="ThemeServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST">
			<div class="form-body">
				
				
				
				<div class="form-group ">
						<div class="col-sm-6 ">
						<input type='hidden' class='form-control'  name='iD' value='<%=ID%>'/>
					</div>
						
					
				</div>
				<div class="form-group ">
						<label class="col-sm-3 control-label">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.THEME_EDIT_THEMENAME, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.THEME_ADD_THEMENAME, loginDTO));
					}
					%>
							<span class="required"> * </span>
							</label>

							<div class="col-sm-6 ">
										<input type='text' class='form-control'  name='themeName' id = 'themeName_text' value=
						<%
						if(actionName.equals("edit"))
						{
							out.print("'" + themeDTO.themeName + "'");
						}
						else
						{							
							out.print("'" + " " + "'");
						}
						%>/>
							</div>
					
				</div>
				<div class="form-group ">
						<label class="col-sm-3 control-label">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.THEME_EDIT_DIRECTORY, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.THEME_ADD_DIRECTORY, loginDTO));
					}
					%>
							</label>

							<div class="col-sm-6 ">
										<input type='text' class='form-control'  name='directory' id = 'directory_text' value=
						<%
						if(actionName.equals("edit"))
						{
							out.print("'" + themeDTO.directory + "'");
						}
						else
						{							
							out.print("'" + " " + "'");
						}
						%>/>
							</div>
					
				</div>
				<div class="form-group ">
						<label class="col-sm-3 control-label">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.THEME_EDIT_ISAPPLIED, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.THEME_ADD_ISAPPLIED, loginDTO));
					}
					%>
							</label>

							<div class="col-sm-6 ">
													
						<input type='checkbox' class='form-control'  name='isApplied' id = 'isApplied_checkbox' value='true' 
						<%
						if(actionName.equals("edit") && String.valueOf(themeDTO.isApplied).equals("true"))
						{
							out.print("checked");
						}
						%>
						><br>						
										</div>
						
					
				</div>
				<div class="form-group ">
						<div class="col-sm-6 ">
						<input type='hidden' class='form-control'  name='isDeleted' value='<%=themeDTO.isDeleted%>'/>
					</div>
						
					
				</div>
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.THEME_EDIT_THEME_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.THEME_ADD_THEME_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.THEME_EDIT_THEME_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.THEME_ADD_THEME_SUBMIT_BUTTON, loginDTO));
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

var geo_lastClickedValue = [];

function DoSubmit()
{
						return true;
}

function addrselected(value, htmlID, name, selectedIndex, tagname)
{
	try 
	{
		geo_lastClickedValue[tagname] = value;
		var elements = document.getElementsByClassName("form-control");
		var ids = '';
		for(var i = elements.length - 1; i >= 0; i--) {
			var elemID = elements[i].id;
			if(elemID.includes(tagname + "_geoSelectField") && elemID > htmlID)
			{
				ids += elements[i].id + ' ';
				
				for(var j = elements[i].options.length - 1; j >= 0; j--)
				{
				
					elements[i].options[j].remove();
				}
				elements[i].remove();
				
			}
		}


		var newid = htmlID + '_1';

		document.getElementById(tagname + "_geodiv").innerHTML += "<select class='form-control' name='" + tagname + "' id = '" + newid 
		+ "' onChange='addrselected(this.value, this.id, this.name, this.selectedIndex, this.name)'></select>";
		document.getElementById(htmlID).options[0].innerHTML = document.getElementById(htmlID).options[selectedIndex].innerHTML;
		document.getElementById(htmlID).options[0].value = document.getElementById(htmlID).options[selectedIndex].value;
		
		 var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() 
		  {
			if (this.readyState == 4 && this.status == 200) 
			{
				if(!this.responseText.includes('option'))
				{
					document.getElementById(newid).remove();
				}
				else
				{
					document.getElementById(newid).innerHTML = this.responseText ;
				}
				
			}
			else if(this.readyState == 4 && this.status != 200)
			{
				alert('failed ' + this.status);
			}
		  };
		 
		  var redirect = "geolocation/geoloc.jsp?myID="+value;
		  
		  xhttp.open("GET", redirect, true);
		  xhttp.send();
	}
	catch(err) 
	{
		alert("got error: " + err);
	}	  
	return;
}


window.onload =function ()
{
	if(<%=actionName.equals("edit")%>)
			{
			}
	else
			{
			}
		
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) 
	    {
						    }
	    else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	  };
	  xhttp.open("GET", "geolocation/geoloc.jsp?myID=1", true);
	  xhttp.send();
	
}
// bkLib.onDomLoaded(function() 
// {
// 	// // 	// // 	// // 	// // 	// // // });
</script>





