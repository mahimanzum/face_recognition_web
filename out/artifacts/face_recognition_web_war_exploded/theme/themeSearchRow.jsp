<%@page pageEncoding="UTF-8" %>

<%@page import="theme.ThemeDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%
ThemeDTO row = (ThemeDTO)request.getAttribute("themeDTO");
if(row == null)
{
	row = new ThemeDTO();
	
}
System.out.println("row = " + row);


int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");

											
											
											out.println("<td id = '" + i + "_themeName" +  "' style='"+ deletedStyle +"'>");
											out.println(row.themeName);
											out.println("</td>");
											
											out.println("<td id = '" + i + "_directory" +  "' style='"+ deletedStyle +"'>");
											out.println(row.directory);
											out.println("</td>");
											
											out.println("<td id = '" + i + "_isApplied" +  "' style='"+ deletedStyle +"'>");
											out.println(row.isApplied);
											out.println("</td>");
											



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";
											//System.out.println("\n\n onclickFunc= " + onclickFunc);											
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick="+ onclickFunc +">Edit</a>");
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");%>

