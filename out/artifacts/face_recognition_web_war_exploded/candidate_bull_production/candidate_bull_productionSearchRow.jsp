<%@page pageEncoding="UTF-8" %>

<%@page import="candidate_bull_production.Candidate_bull_productionDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="candidate_bull_production.Candidate_bull_productionAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.CANDIDATE_BULL_PRODUCTION_EDIT_LANGUAGE, loginDTO);

Candidate_bull_productionDTO row = (Candidate_bull_productionDTO)request.getAttribute("candidate_bull_productionDTO");
if(row == null)
{
	row = new Candidate_bull_productionDTO();
	
}
System.out.println("row = " + row);


int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";
}
out.println("<td style='display:none;'><input type='hidden' id='failureMessage_" + i + "' value='" + failureMessage + "'/></td>");

String value = "";


											
		
											
											out.println("<td id = '" + i + "_productionDate'>");
											value = row.productionDate + "";
											SimpleDateFormat format_productionDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_productionDate = format_productionDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_productionDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_numberOfCandidateBulls'>");
											value = row.numberOfCandidateBulls + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sourceType'>");
											value = row.sourceType + "";
											
											value = Candidate_bull_productionAnotherDBDAO.getName(Integer.parseInt(value), "source", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.CANDIDATE_BULL_PRODUCTION_SEARCH_CANDIDATE_BULL_PRODUCTION_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

