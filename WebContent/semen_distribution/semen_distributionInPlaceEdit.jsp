<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="semen_distribution.Semen_distributionDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Semen_distributionDTO semen_distributionDTO = (Semen_distributionDTO)request.getAttribute("semen_distributionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(semen_distributionDTO == null)
{
	semen_distributionDTO = new Semen_distributionDTO();
	
}
System.out.println("semen_distributionDTO = " + semen_distributionDTO);

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
<%@ page import="semen_distribution.Semen_distributionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_LANGUAGE, loginDTO);
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
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>'>
<%
if(actionName.equals("edit"))
{
			Options = Semen_distributionAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", semen_distributionDTO.bullType + "");
}
else
{			
			Options = Semen_distributionAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_noOfDose'>")%>
			
	
	<div class="form-inline" id = 'noOfDose_div_<%=i%>'>
		<input type='number' class='form-control'  name='noOfDose' id = 'noOfDose_number_<%=i%>' min='0' max='10000' value=<%=actionName.equals("edit")?("'" + semen_distributionDTO.noOfDose + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_requisitionId'>")%>
			
	
	<div class="form-inline" id = 'requisitionId_div_<%=i%>'>
		<input type='text' class='form-control'  name='requisitionId' id = 'requisitionId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_distributionDTO.requisitionId + "'"):("'" + "0" + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_groupId'>")%>
			
	
	<div class="form-inline" id = 'groupId_div_<%=i%>'>
		<input type='text' class='form-control'  name='groupId' id = 'groupId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_distributionDTO.groupId + "'"):("'" + "0" + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_transactionDate'>")%>
			
	
	<div class="form-inline" id = 'transactionDate_div_<%=i%>'>
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
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>'><%=actionName.equals("edit")?(semen_distributionDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + semen_distributionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	