<%@page pageEncoding="UTF-8" %>

<%@page import="imported_semen.Imported_semenDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.IMPORTED_SEMEN_EDIT_LANGUAGE, loginDTO);

Imported_semenDTO row = (Imported_semenDTO)request.getAttribute("imported_semenDTO");
if(row == null)
{
	row = new Imported_semenDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_bullTag'>");
											value = row.bullTag + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dateOfBirth'>");
											value = row.dateOfBirth + "";
											SimpleDateFormat format_dateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfBirth = format_dateOfBirth.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfBirth);
				
			
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
		
											
											out.println("<td id = '" + i + "_breed'>");
											value = row.breed + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_dateOfEntry'>");
											value = row.dateOfEntry + "";
											SimpleDateFormat format_dateOfEntry = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfEntry = format_dateOfEntry.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfEntry);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_receivedAmount'>");
											value = row.receivedAmount + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_distributedAmount'>");
											value = row.distributedAmount + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_toWhomDistributed'>");
											value = row.toWhomDistributed + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.IMPORTED_SEMEN_SEARCH_IMPORTED_SEMEN_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

