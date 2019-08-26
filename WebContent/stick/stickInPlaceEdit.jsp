<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="stick.StickDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
StickDTO stickDTO = (StickDTO)request.getAttribute("stickDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(stickDTO == null)
{
	stickDTO = new StickDTO();
	
}
System.out.println("stickDTO = " + stickDTO);

String actionName = "edit";


String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");
%>
























	











<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameEn'>")%>
			
	
	<div class="form-inline" id = 'nameEn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.nameEn + "'"):("''")%> />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameBn'>")%>
			
	
	<div class="form-inline" id = 'nameBn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.nameBn + "'"):("''")%> />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_semenCollectionId'>")%>
			
	
	<div class="form-inline" id = 'semenCollectionId_div_<%=i%>'>
		<input type='text' class='form-control'  name='semenCollectionId' id = 'semenCollectionId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + stickDTO.semenCollectionId + "'"):("'" + "0" + "'")%> />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + stickDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	