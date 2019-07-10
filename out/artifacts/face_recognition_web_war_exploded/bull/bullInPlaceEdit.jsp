<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull.BullDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
BullDTO bullDTO = (BullDTO)request.getAttribute("bullDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(bullDTO == null)
{
	bullDTO = new BullDTO();
	
}
System.out.println("bullDTO = " + bullDTO);

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
<%@ page import="bull.BullAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULL_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameEn'>")%>
			
	
	<div class="form-inline" id = 'nameEn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.nameEn + "'"):("''")%> required="required"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameBn'>")%>
			
	
	<div class="form-inline" id = 'nameBn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.nameBn + "'"):("''")%> required="required"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfBirth'>")%>
			
	
	<div class="form-inline" id = 'dateOfBirth_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfBirth_Date_<%=i%>' id = 'dateOfBirth_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfBirth = format_dateOfBirth.format(new Date(bullDTO.dateOfBirth)).toString();
	out.println("'" + formatted_dateOfBirth + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfBirth' id = 'dateOfBirth_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.dateOfBirth + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_breedType'>")%>
			
	
	<div class="form-inline" id = 'breedType_div_<%=i%>'>
		<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = BullAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", bullDTO.breedType + "");
}
else
{			
			Options = BullAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_statusType'>")%>
			
	
	<div class="form-inline" id = 'statusType_div_<%=i%>'>
		<select class='form-control'  name='statusType' id = 'statusType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = BullAnotherDBDAO.getOptions(Language, "select", "status", "statusType_select_" + i, "form-control", "statusType", bullDTO.statusType + "");
}
else
{			
			Options = BullAnotherDBDAO.getOptions(Language, "select", "status", "statusType_select_" + i, "form-control", "statusType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dam'>")%>
			
	
	<div class="form-inline" id = 'dam_div_<%=i%>'>
		<input type='text' class='form-control'  name='dam' id = 'dam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.dam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sire'>")%>
			
	
	<div class="form-inline" id = 'sire_div_<%=i%>'>
		<input type='text' class='form-control'  name='sire' id = 'sire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.sire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_maternalGrandDam'>")%>
			
	
	<div class="form-inline" id = 'maternalGrandDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='maternalGrandDam' id = 'maternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.maternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_maternalGrandSire'>")%>
			
	
	<div class="form-inline" id = 'maternalGrandSire_div_<%=i%>'>
		<input type='text' class='form-control'  name='maternalGrandSire' id = 'maternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.maternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_paternalGrandDam'>")%>
			
	
	<div class="form-inline" id = 'paternalGrandDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='paternalGrandDam' id = 'paternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.paternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_paternalGrandSire'>")%>
			
	
	<div class="form-inline" id = 'paternalGrandSire_div_<%=i%>'>
		<input type='text' class='form-control'  name='paternalGrandSire' id = 'paternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.paternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_milkYieldOfDam'>")%>
			
	
	<div class="form-inline" id = 'milkYieldOfDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='milkYieldOfDam' id = 'milkYieldOfDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.milkYieldOfDam + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_milkYieldOfSiresDam'>")%>
			
	
	<div class="form-inline" id = 'milkYieldOfSiresDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='milkYieldOfSiresDam' id = 'milkYieldOfSiresDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.milkYieldOfSiresDam + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_progenyMilkYield'>")%>
			
	
	<div class="form-inline" id = 'progenyMilkYield_div_<%=i%>'>
		<input type='text' class='form-control'  name='progenyMilkYield' id = 'progenyMilkYield_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + bullDTO.progenyMilkYield + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(bullDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bullDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	