<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="semen_collection.Semen_collectionDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Semen_collectionDTO semen_collectionDTO = (Semen_collectionDTO)request.getAttribute("semen_collectionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(semen_collectionDTO == null)
{
	semen_collectionDTO = new Semen_collectionDTO();
	
}
System.out.println("semen_collectionDTO = " + semen_collectionDTO);

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
<%@ page import="semen_collection.Semen_collectionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.SEMEN_COLLECTION_EDIT_LANGUAGE, loginDTO);
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
			Options = Semen_collectionAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", semen_collectionDTO.bullType + "");
}
else
{			
			Options = Semen_collectionAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_noOfDose'>")%>
			
	
	<div class="form-inline" id = 'noOfDose_div_<%=i%>'>
		<input type='number' class='form-control'  name='noOfDose' id = 'noOfDose_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + semen_collectionDTO.noOfDose + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_volume'>")%>
			
	
	<div class="form-inline" id = 'volume_div_<%=i%>'>
		<input type='text' class='form-control'  name='volume' id = 'volume_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_collectionDTO.volume + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_density'>")%>
			
	
	<div class="form-inline" id = 'density_div_<%=i%>'>
		<input type='text' class='form-control'  name='density' id = 'density_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_collectionDTO.density + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_progressiveMortality'>")%>
			
	
	<div class="form-inline" id = 'progressiveMortality_div_<%=i%>'>
		<input type='text' class='form-control'  name='progressiveMortality' id = 'progressiveMortality_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_collectionDTO.progressiveMortality + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_colorType'>")%>
			
	
	<div class="form-inline" id = 'colorType_div_<%=i%>'>
		<select class='form-control'  name='colorType' id = 'colorType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Semen_collectionAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + i, "form-control", "colorType", semen_collectionDTO.colorType + "");
}
else
{			
			Options = Semen_collectionAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + i, "form-control", "colorType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_transactionDate'>")%>
			
	
	<div class="form-inline" id = 'transactionDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='transactionDate_Date_<%=i%>' id = 'transactionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_transactionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_transactionDate = format_transactionDate.format(new Date(semen_collectionDTO.transactionDate)).toString();
	out.println("'" + formatted_transactionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='transactionDate' id = 'transactionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + semen_collectionDTO.transactionDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(semen_collectionDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + semen_collectionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	