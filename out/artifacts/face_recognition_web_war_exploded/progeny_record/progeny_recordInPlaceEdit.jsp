<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="progeny_record.Progeny_recordDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Progeny_recordDTO progeny_recordDTO = (Progeny_recordDTO)request.getAttribute("progeny_recordDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(progeny_recordDTO == null)
{
	progeny_recordDTO = new Progeny_recordDTO();
	
}
System.out.println("progeny_recordDTO = " + progeny_recordDTO);

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
<%@ page import="progeny_record.Progeny_recordAnotherDBDAO"%>
<%
String Language = LM.getText(LC.PROGENY_RECORD_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_centreType'>")%>
			
	
	<div class="form-inline" id = 'centreType_div_<%=i%>'>
		<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", progeny_recordDTO.centreType + "");
}
else
{			
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullType'>")%>
			
	
	<div class="form-inline" id = 'bullType_div_<%=i%>'>
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", progeny_recordDTO.bullType + "");
}
else
{			
			Options = Progeny_recordAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_numberOfMaleCalfs'>")%>
			
	
	<div class="form-inline" id = 'numberOfMaleCalfs_div_<%=i%>'>
		<input type='number' class='form-control'  name='numberOfMaleCalfs' id = 'numberOfMaleCalfs_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.numberOfMaleCalfs + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_numberOfFemaleCalfs'>")%>
			
	
	<div class="form-inline" id = 'numberOfFemaleCalfs_div_<%=i%>'>
		<input type='number' class='form-control'  name='numberOfFemaleCalfs' id = 'numberOfFemaleCalfs_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.numberOfFemaleCalfs + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfEntry'>")%>
			
	
	<div class="form-inline" id = 'dateOfEntry_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfEntry_Date_<%=i%>' id = 'dateOfEntry_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfEntry = format_dateOfEntry.format(new Date(progeny_recordDTO.dateOfEntry)).toString();
	out.println("'" + formatted_dateOfEntry + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfEntry' id = 'dateOfEntry_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + progeny_recordDTO.dateOfEntry + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(progeny_recordDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + progeny_recordDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	