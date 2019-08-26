<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull_transfer.Bull_transferDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Bull_transferDTO bull_transferDTO = (Bull_transferDTO)request.getAttribute("bull_transferDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(bull_transferDTO == null)
{
	bull_transferDTO = new Bull_transferDTO();
	
}
System.out.println("bull_transferDTO = " + bull_transferDTO);

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
<%@ page import="bull_transfer.Bull_transferAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfTransfer'>")%>
			
	
	<div class="form-inline" id = 'dateOfTransfer_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfTransfer_Date_<%=i%>' id = 'dateOfTransfer_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfTransfer = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfTransfer = format_dateOfTransfer.format(new Date(bull_transferDTO.dateOfTransfer)).toString();
	out.println("'" + formatted_dateOfTransfer + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfTransfer' id = 'dateOfTransfer_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.dateOfTransfer + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullType'>")%>
			
	
	<div class="form-inline" id = 'bullType_div_<%=i%>'>
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", bull_transferDTO.bullType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_fromCentreType'>")%>
			
	
	<div class="form-inline" id = 'fromCentreType_div_<%=i%>'>
		<select class='form-control'  name='fromCentreType' id = 'fromCentreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "fromCentreType_select_" + i, "form-control", "fromCentreType", bull_transferDTO.fromCentreType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "fromCentreType_select_" + i, "form-control", "fromCentreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_toCentreType'>")%>
			
	
	<div class="form-inline" id = 'toCentreType_div_<%=i%>'>
		<select class='form-control'  name='toCentreType' id = 'toCentreType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "toCentreType_select_" + i, "form-control", "toCentreType", bull_transferDTO.toCentreType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "toCentreType_select_" + i, "form-control", "toCentreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_entryDate'>")%>
			
	
	<div class="form-inline" id = 'entryDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='entryDate_Date_<%=i%>' id = 'entryDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_entryDate = format_entryDate.format(new Date(bull_transferDTO.entryDate)).toString();
	out.println("'" + formatted_entryDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='entryDate' id = 'entryDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.entryDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_exitDate'>")%>
			
	
	<div class="form-inline" id = 'exitDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='exitDate_Date_<%=i%>' id = 'exitDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_exitDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_exitDate = format_exitDate.format(new Date(bull_transferDTO.exitDate)).toString();
	out.println("'" + formatted_exitDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='exitDate' id = 'exitDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_transferDTO.exitDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(bull_transferDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_approvalStatusType'>")%>
			
	
	<div class="form-inline" id = 'approvalStatusType_div_<%=i%>'>
		<select class='form-control'  name='approvalStatusType' id = 'approvalStatusType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "approval_status", "approvalStatusType_select_" + i, "form-control", "approvalStatusType", bull_transferDTO.approvalStatusType + "");
}
else
{			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "approval_status", "approvalStatusType_select_" + i, "form-control", "approvalStatusType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bull_transferDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	