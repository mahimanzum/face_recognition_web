
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="theme.ThemeDTO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

%>				
				
				
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.THEME_EDIT_THEMENAME, loginDTO)%></th>
								<th><%=LM.getText(LC.THEME_EDIT_DIRECTORY, loginDTO)%></th>
								<th><%=LM.getText(LC.THEME_EDIT_ISAPPLIED, loginDTO)%></th>
								<th><%out.print(LM.getText(LC.THEME_SEARCH_THEME_EDIT_BUTTON, loginDTO));%></th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.THEME_SEARCH_THEME_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_THEME);

								try
								{

									if (data != null) 
									{
										int size = data.size();
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											ThemeDTO row = (ThemeDTO) data.get(i);
											out.println("<tr>");
											out.println("<td>" + row.themeName + "</td>");
											out.println("<td>" + row.directory + "</td>");
											out.println("<td>" + row.isApplied + "</td>");
											out.println("<td>");
											out.println("<a href='ThemeServlet?actionType=getEditPage&ID=" +row.iD + "'>Edit</a>");
											out.println("</td>");
											out.println("<td>");
											out.println("<input type='checkbox' name='ID' value='" + row.iD + "'/>");
											out.println("</td>");
											out.println("</tr>");
										}
										System.out.println("printing done");
									}
									else
									{
										System.out.println("data  null");
									}
								}
								catch(Exception e)
								{
									System.out.println("JSP exception " + e);
								}
							%>



						</tbody>

					</table>
				</div>

<%
	String navigator2 = SessionConstants.NAV_THEME;
	System.out.println("navigator2 = " + navigator2);
	RecordNavigator rn2 = (RecordNavigator)session.getAttribute(navigator2);
	System.out.println("rn2 = " + rn2);
	String pageno2 = ( rn2 == null ) ? "1" : "" + rn2.getCurrentPageNo();
	String totalpage2 = ( rn2 == null ) ? "1" : "" + rn2.getTotalPages();

%>
<input type="hidden" id="hidden_pageno" value="<%=pageno2%>" />
<input type="hidden" id="hidden_totalpage" value="<%=totalpage2%>" />				