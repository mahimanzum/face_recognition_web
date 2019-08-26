<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="liquid_semen.Liquid_semenDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Liquid_semenDTO liquid_semenDTO = (Liquid_semenDTO)request.getAttribute("liquid_semenDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(liquid_semenDTO == null)
{
	liquid_semenDTO = new Liquid_semenDTO();
	
}
System.out.println("liquid_semenDTO = " + liquid_semenDTO);

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
<%@ page import="liquid_semen.Liquid_semenAnotherDBDAO"%>
<%
String Language = LM.getText(LC.LIQUID_SEMEN_EDIT_LANGUAGE, loginDTO);
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
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", liquid_semenDTO.centreType + "");
}
else
{			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
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
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", liquid_semenDTO.bullType + "");
}
else
{			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_noOfDose'>")%>
			
	
	<div class="form-inline" id = 'noOfDose_div_<%=i%>'>
		<input type='number' class='form-control'  name='noOfDose' id = 'noOfDose_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + liquid_semenDTO.noOfDose + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_volume'>")%>
			
	
	<div class="form-inline" id = 'volume_div_<%=i%>'>
		<input type='text' class='form-control'  name='volume' id = 'volume_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + liquid_semenDTO.volume + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_density'>")%>
			
	
	<div class="form-inline" id = 'density_div_<%=i%>'>
		<input type='text' class='form-control'  name='density' id = 'density_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + liquid_semenDTO.density + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_progressiveMotility'>")%>
			
	
	<div class="form-inline" id = 'progressiveMotility_div_<%=i%>'>
		<input type='text' class='form-control'  name='progressiveMotility' id = 'progressiveMotility_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + liquid_semenDTO.progressiveMotility + "'"):("'" + "0" + "'")%>  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_colorType'>")%>
			
	
	<div class="form-inline" id = 'colorType_div_<%=i%>'>
		<select class='form-control'  name='colorType' id = 'colorType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + i, "form-control", "colorType", liquid_semenDTO.colorType + "");
}
else
{			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + i, "form-control", "colorType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_collectionDistributionDate'>")%>
			
	
	<div class="form-inline" id = 'collectionDistributionDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='collectionDistributionDate_Date_<%=i%>' id = 'collectionDistributionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_collectionDistributionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_collectionDistributionDate = format_collectionDistributionDate.format(new Date(liquid_semenDTO.collectionDistributionDate)).toString();
	out.println("'" + formatted_collectionDistributionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='collectionDistributionDate' id = 'collectionDistributionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + liquid_semenDTO.collectionDistributionDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(liquid_semenDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + liquid_semenDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	