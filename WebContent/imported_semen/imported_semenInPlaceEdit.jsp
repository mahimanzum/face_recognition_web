<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="imported_semen.Imported_semenDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Imported_semenDTO imported_semenDTO = (Imported_semenDTO)request.getAttribute("imported_semenDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(imported_semenDTO == null)
{
	imported_semenDTO = new Imported_semenDTO();
	
}
System.out.println("imported_semenDTO = " + imported_semenDTO);

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
String Language = LM.getText(LC.IMPORTED_SEMEN_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullTag'>")%>
			
	
	<div class="form-inline" id = 'bullTag_div_<%=i%>'>
		<input type='text' class='form-control'  name='bullTag' id = 'bullTag_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.bullTag + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfBirth'>")%>
			
	
	<div class="form-inline" id = 'dateOfBirth_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfBirth_Date_<%=i%>' id = 'dateOfBirth_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfBirth = format_dateOfBirth.format(new Date(imported_semenDTO.dateOfBirth)).toString();
	out.println("'" + formatted_dateOfBirth + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfBirth' id = 'dateOfBirth_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dateOfBirth + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dam'>")%>
			
	
	<div class="form-inline" id = 'dam_div_<%=i%>'>
		<input type='text' class='form-control'  name='dam' id = 'dam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sire'>")%>
			
	
	<div class="form-inline" id = 'sire_div_<%=i%>'>
		<input type='text' class='form-control'  name='sire' id = 'sire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.sire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_maternalGrandDam'>")%>
			
	
	<div class="form-inline" id = 'maternalGrandDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='maternalGrandDam' id = 'maternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.maternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_maternalGrandSire'>")%>
			
	
	<div class="form-inline" id = 'maternalGrandSire_div_<%=i%>'>
		<input type='text' class='form-control'  name='maternalGrandSire' id = 'maternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.maternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_paternalGrandDam'>")%>
			
	
	<div class="form-inline" id = 'paternalGrandDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='paternalGrandDam' id = 'paternalGrandDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.paternalGrandDam + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_paternalGrandSire'>")%>
			
	
	<div class="form-inline" id = 'paternalGrandSire_div_<%=i%>'>
		<input type='text' class='form-control'  name='paternalGrandSire' id = 'paternalGrandSire_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.paternalGrandSire + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_milkYieldOfDam'>")%>
			
	
	<div class="form-inline" id = 'milkYieldOfDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='milkYieldOfDam' id = 'milkYieldOfDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.milkYieldOfDam + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_milkYieldOfSiresDam'>")%>
			
	
	<div class="form-inline" id = 'milkYieldOfSiresDam_div_<%=i%>'>
		<input type='text' class='form-control'  name='milkYieldOfSiresDam' id = 'milkYieldOfSiresDam_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.milkYieldOfSiresDam + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_progenyMilkYield'>")%>
			
	
	<div class="form-inline" id = 'progenyMilkYield_div_<%=i%>'>
		<input type='text' class='form-control'  name='progenyMilkYield' id = 'progenyMilkYield_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.progenyMilkYield + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_breed'>")%>
			
	
	<div class="form-inline" id = 'breed_div_<%=i%>'>
		<input type='text' class='form-control'  name='breed' id = 'breed_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.breed + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_dateOfEntry'>")%>
			
	
	<div class="form-inline" id = 'dateOfEntry_div_<%=i%>'>
		<input type='date' class='form-control'  name='dateOfEntry_Date_<%=i%>' id = 'dateOfEntry_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_dateOfEntry = format_dateOfEntry.format(new Date(imported_semenDTO.dateOfEntry)).toString();
	out.println("'" + formatted_dateOfEntry + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='dateOfEntry' id = 'dateOfEntry_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.dateOfEntry + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_receivedAmount'>")%>
			
	
	<div class="form-inline" id = 'receivedAmount_div_<%=i%>'>
		<input type='number' class='form-control'  name='receivedAmount' id = 'receivedAmount_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.receivedAmount + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_distributedAmount'>")%>
			
	
	<div class="form-inline" id = 'distributedAmount_div_<%=i%>'>
		<input type='number' class='form-control'  name='distributedAmount' id = 'distributedAmount_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.distributedAmount + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_toWhomDistributed'>")%>
			
	
	<div class="form-inline" id = 'toWhomDistributed_div_<%=i%>'>
		<input type='text' class='form-control'  name='toWhomDistributed' id = 'toWhomDistributed_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + imported_semenDTO.toWhomDistributed + "'"):("'" + " " + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(imported_semenDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + imported_semenDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	