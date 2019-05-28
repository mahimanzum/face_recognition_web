<%@page pageEncoding="UTF-8" %>

<%@page import="bull_transfer.Bull_transferDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="bull_transfer.Bull_transferAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);

Bull_transferDTO row = (Bull_transferDTO)request.getAttribute("bull_transferDTO");
if(row == null)
{
	row = new Bull_transferDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_dateOfTransfer'>");
											value = row.dateOfTransfer + "";
											SimpleDateFormat format_dateOfTransfer = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_dateOfTransfer = format_dateOfTransfer.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_dateOfTransfer);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_fromCentreType'>");
											value = row.fromCentreType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_toCentreType'>");
											value = row.toCentreType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_entryDate'>");
											value = row.entryDate + "";
											SimpleDateFormat format_entryDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_entryDate = format_entryDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_entryDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_exitDate'>");
											value = row.exitDate + "";
											SimpleDateFormat format_exitDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_exitDate = format_exitDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_exitDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_approvalStatusType'>");
											value = row.approvalStatusType + "";
											
											value = Bull_transferAnotherDBDAO.getName(Integer.parseInt(value), "approval_status", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.BULL_TRANSFER_SEARCH_BULL_TRANSFER_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

