<%@page pageEncoding="UTF-8" %>

<%@page import="theme.ThemeDTO"%>
<%@page import="java.util.UUID"%>


<%
ThemeDTO themeDTO = (ThemeDTO)request.getAttribute("themeDTO");

if(themeDTO == null)
{
	themeDTO = new ThemeDTO();
	
}
System.out.println("themeDTO = " + themeDTO);

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

	







			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			
					
					
		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
					
					
<%=("</td>")%>


			
<%=("<td id = '" + i + "_themeName" +  "' style='"+ deletedStyle +"'>")%>
			
					
	<div id = 'themeName_div_<%=i%>'>


		<input type='text' class='form-control'  name='themeName' id = 'themeName_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + themeDTO.themeName + "'"):("'" + " " + "'")%>/>					
	</div>
				
					
<%=("</td>")%>


			
<%=("<td id = '" + i + "_directory" +  "' style='"+ deletedStyle +"'>")%>
			
					
	<div id = 'directory_div_<%=i%>'>


		<input type='text' class='form-control'  name='directory' id = 'directory_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + themeDTO.directory + "'"):("'" + " " + "'")%>/>					
	</div>
				
					
<%=("</td>")%>


			
<%=("<td id = '" + i + "_isApplied" +  "' style='"+ deletedStyle +"'>")%>
			
					
	<div id = 'isApplied_div_<%=i%>'>


		<input type='checkbox' class='form-control'  name='isApplied' id = 'isApplied_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(themeDTO.isApplied).equals("true"))?("checked"):""%>><br>						

	</div>
				
					
<%=("</td>")%>


			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			
					
					
		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + themeDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
					
<%=("</td>")%>
					
	