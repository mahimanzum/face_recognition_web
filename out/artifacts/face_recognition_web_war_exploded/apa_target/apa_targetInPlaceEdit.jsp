<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="apa_target.Apa_targetDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Apa_targetDTO apa_targetDTO = (Apa_targetDTO)request.getAttribute("apa_targetDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(apa_targetDTO == null)
{
	apa_targetDTO = new Apa_targetDTO();
	
}
System.out.println("apa_targetDTO = " + apa_targetDTO);

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
<%
String Language = LM.getText(LC.APA_TARGET_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_semenCollection'>")%>
			
	
	<div class="form-inline" id = 'semenCollection_div_<%=i%>'>
		<input type='text' class='form-control'  name='semenCollection' id = 'semenCollection_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.semenCollection + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_artificialInsemenation'>")%>
			
	
	<div class="form-inline" id = 'artificialInsemenation_div_<%=i%>'>
		<input type='text' class='form-control'  name='artificialInsemenation' id = 'artificialInsemenation_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.artificialInsemenation + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_progeny'>")%>
			
	
	<div class="form-inline" id = 'progeny_div_<%=i%>'>
		<input type='text' class='form-control'  name='progeny' id = 'progeny_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.progeny + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_candidateBullProduction'>")%>
			
	
	<div class="form-inline" id = 'candidateBullProduction_div_<%=i%>'>
		<input type='text' class='form-control'  name='candidateBullProduction' id = 'candidateBullProduction_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.candidateBullProduction + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(apa_targetDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_entryDate'>")%>
			
	
	<div class="form-inline" id = 'entryDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(apa_targetDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + apa_targetDTO.entryDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + apa_targetDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	