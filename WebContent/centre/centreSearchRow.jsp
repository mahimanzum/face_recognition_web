<%@page pageEncoding="UTF-8" %>

<%@page import="centre.CentreDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>



<%
CentreDTO row = (CentreDTO)request.getAttribute("centreDTO");
if(row == null)
{
	row = new CentreDTO();
	
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
		
											
											out.println("<td id = '" + i + "_address'>");
											value = row.address + "";
											out.println(GeoLocationDAO2.parseText(value));
											{
												String addressdetails = GeoLocationDAO2.parseDetails(value);
												if(!addressdetails.equals(""))
												{
													out.println(", " + addressdetails);
												}
											}
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_contactPerson'>");
											value = row.contactPerson + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_phoneNumber'>");
											value = row.phoneNumber + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_email'>");
											value = row.email + "";
														
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

