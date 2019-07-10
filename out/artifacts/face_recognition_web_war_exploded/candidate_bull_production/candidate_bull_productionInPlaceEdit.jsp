<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="candidate_bull_production.Candidate_bull_productionDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Candidate_bull_productionDTO candidate_bull_productionDTO = (Candidate_bull_productionDTO)request.getAttribute("candidate_bull_productionDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(candidate_bull_productionDTO == null)
{
	candidate_bull_productionDTO = new Candidate_bull_productionDTO();
	
}
System.out.println("candidate_bull_productionDTO = " + candidate_bull_productionDTO);

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
<%@ page import="candidate_bull_production.Candidate_bull_productionAnotherDBDAO"%>
<%
String Language = LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_productionDate'>")%>
			
	
	<div class="form-inline" id = 'productionDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='productionDate_Date_<%=i%>' id = 'productionDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_productionDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_productionDate = format_productionDate.format(new Date(candidate_bull_productionDTO.productionDate)).toString();
	out.println("'" + formatted_productionDate + "'");
}
else
{
	out.println("'" + datestr + "'");
}
%>
 >
		<input type='hidden' class='form-control'  name='productionDate' id = 'productionDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.productionDate + "'"):("'" + "0" + "'")%> >
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_numberOfCandidateBulls'>")%>
			
	
	<div class="form-inline" id = 'numberOfCandidateBulls_div_<%=i%>'>
		<input type='number' class='form-control'  name='numberOfCandidateBulls' id = 'numberOfCandidateBulls_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.numberOfCandidateBulls + "'"):("'" + 0 + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_sourceType'>")%>
			
	
	<div class="form-inline" id = 'sourceType_div_<%=i%>'>
		<select class='form-control'  name='sourceType' id = 'sourceType_select_<%=i%>' >
<%
if(actionName.equals("edit"))
{
			Options = Candidate_bull_productionAnotherDBDAO.getOptions(Language, "select", "source", "sourceType_select_" + i, "form-control", "sourceType", candidate_bull_productionDTO.sourceType + "");
}
else
{			
			Options = Candidate_bull_productionAnotherDBDAO.getOptions(Language, "select", "source", "sourceType_select_" + i, "form-control", "sourceType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + candidate_bull_productionDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(candidate_bull_productionDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
					
	