<%@page pageEncoding="UTF-8" %>

<%@page import="bull.BullDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="bull.BullAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.BULL_EDIT_LANGUAGE, loginDTO);
%>


<%
BullDTO row = (BullDTO)request.getAttribute("bullDTO");
if(row == null)
{
	row = new BullDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_nameEn'>");
											value = row.nameEn + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_nameBn'>");
											value = row.nameBn + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dateOfBirth'>");
											value = row.dateOfBirth + "";
											SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfBirth = format_dateOfBirth.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfBirth);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_breedType'>");
											value = row.breedType + "";
											
											value = BullAnotherDBDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_statusType'>");
											value = row.statusType + "";
											
											value = BullAnotherDBDAO.getName(Integer.parseInt(value), "status", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dam'>");
											value = row.dam + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_sire'>");
											value = row.sire + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_maternalGrandDam'>");
											value = row.maternalGrandDam + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_maternalGrandSire'>");
											value = row.maternalGrandSire + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_paternalGrandDam'>");
											value = row.paternalGrandDam + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_paternalGrandSire'>");
											value = row.paternalGrandSire + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_milkYieldOfDam'>");
											value = row.milkYieldOfDam + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_milkYieldOfSiresDam'>");
											value = row.milkYieldOfSiresDam + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_progenyMilkYield'>");
											value = row.progenyMilkYield + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick="+ onclickFunc +">Edit</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

