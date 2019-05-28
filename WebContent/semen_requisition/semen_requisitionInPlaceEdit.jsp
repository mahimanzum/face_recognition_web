<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="semen_requisition.Semen_requisitionDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Semen_requisitionDTO semen_requisitionDTO = (Semen_requisitionDTO)request.getAttribute("semen_requisitionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(semen_requisitionDTO == null)
{
	semen_requisitionDTO = new Semen_requisitionDTO();
	
}
System.out.println("semen_requisitionDTO = " + semen_requisitionDTO);

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
<%@ page import="semen_requisition.Semen_requisitionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.SEMEN_REQUISITION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_groupId'>")%>
			
	
	<div class="form-inline" id = 'groupId_div_<%=i%>'>
		<input type='text' class='form-control'  name='groupId' id = 'groupId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_requisitionDTO.groupId + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_centreType'>")%>
			
	
	<div class="form-inline" id = 'centreType_div_<%=i%>'>
		<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", semen_requisitionDTO.centreType + "");
}
else
{			
			Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_breedType'>")%>
			
	
	<div class="form-inline" id = 'breedType_div_<%=i%>'>
		<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", semen_requisitionDTO.breedType + "");
}
else
{			
			Options = Semen_requisitionAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_noOfDose'>")%>
			
	
	<div class="form-inline" id = 'noOfDose_div_<%=i%>'>
		<input type='number' class='form-control'  name='noOfDose' id = 'noOfDose_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + semen_requisitionDTO.noOfDose + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_requisitionDate'>")%>
			
	
	<div class="form-inline" id = 'requisitionDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='requisitionDate_Date_<%=i%>' id = 'requisitionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_requisitionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_requisitionDate = format_requisitionDate.format(new Date(semen_requisitionDTO.requisitionDate)).toString();
	out.println("'" + formatted_requisitionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='requisitionDate' id = 'requisitionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_requisitionDTO.requisitionDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDelivered'>")%>
			
	
	<div class="form-inline" id = 'isDelivered_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='isDelivered' id = 'isDelivered_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(semen_requisitionDTO.isDelivered).equals("true"))?("checked"):""%> ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(semen_requisitionDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + semen_requisitionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	