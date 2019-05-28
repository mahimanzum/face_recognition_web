<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="permissible_bulls_in_centre.Permissible_bulls_in_centreDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = (Permissible_bulls_in_centreDTO)request.getAttribute("permissible_bulls_in_centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(permissible_bulls_in_centreDTO == null)
{
	permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
	
}
System.out.println("permissible_bulls_in_centreDTO = " + permissible_bulls_in_centreDTO);

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
<%@ page import="permissible_bulls_in_centre.Permissible_bulls_in_centreAnotherDBDAO"%>
<%
String Language = LM.getText(LC.PERMISSIBLE_BULLS_IN_CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullType'>")%>
			
	
	<div class="form-inline" id = 'bullType_div_<%=i%>'>
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' >
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
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_centreType'>")%>
			
	
	<div class="form-inline" id = 'centreType_div_<%=i%>'>
		<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Permissible_bulls_in_centreAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", permissible_bulls_in_centreDTO.centreType + "");
}
else
{			
			Options = Permissible_bulls_in_centreAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfEntry'>")%>
			
	
	<div class="form-inline" id = 'dateOfEntry_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfEntry_Date_<%=i%>' id = 'dateOfEntry_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfEntry = format_dateOfEntry.format(new Date(permissible_bulls_in_centreDTO.dateOfEntry)).toString();
	out.println("'" + formatted_dateOfEntry + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfEntry' id = 'dateOfEntry_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + permissible_bulls_in_centreDTO.dateOfEntry + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfExpiration'>")%>
			
	
	<div class="form-inline" id = 'dateOfExpiration_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfExpiration_Date_<%=i%>' id = 'dateOfExpiration_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfExpiration = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfExpiration = format_dateOfExpiration.format(new Date(permissible_bulls_in_centreDTO.dateOfExpiration)).toString();
	out.println("'" + formatted_dateOfExpiration + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfExpiration' id = 'dateOfExpiration_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + permissible_bulls_in_centreDTO.dateOfExpiration + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(permissible_bulls_in_centreDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + permissible_bulls_in_centreDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	