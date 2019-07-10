
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="semen_requisition.Semen_requisitionDTO"%>
<%@page import="semen_requisition.Semen_requisitionDAO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>


<%@ page import="semen_requisition.Semen_requisitionAnotherDBDAO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String actionName = "edit";
String failureMessage = (String)request.getAttribute("failureMessage");
if(failureMessage == null || failureMessage.isEmpty())
{
	failureMessage = "";	
}
out.println("<input type='hidden' id='failureMessage_general' value='" + failureMessage + "'/>");
String value = "";
String Language = LM.getText(LC.SEMEN_REQUISITION_EDIT_LANGUAGE, loginDTO);
List<Integer> breedids = Semen_requisitionAnotherDBDAO.getIDs("breed", "id", "");
Semen_requisitionDAO semen_requisitionDAO = new Semen_requisitionDAO();
%>				
				
			
				
				<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.SEMEN_REQUISITION_EDIT_GROUPID, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_REQUISITION_EDIT_CENTRETYPE, loginDTO)%></th>
								<%
								for(int i = 0; i < breedids.size(); i ++)
								{
									out.print("<th>");
									out.print(Semen_requisitionAnotherDBDAO.getName(breedids.get(i), "breed", Language.equals("English")?"name_en":"name_bn", "id"));
									out.print("</th>");
								}
								%>
								<th><%=LM.getText(LC.SEMEN_REQUISITION_EDIT_REQUISITIONDATE, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_REQUISITION_EDIT_ISDELIVERED, loginDTO)%></th>
								<th><%=LM.getText(LC.SEMEN_REQUISITION_EDIT_DESCRIPTION, loginDTO)%></th>
								<th>DISTRIBUTE</th>
								<th><input type="submit" class="btn btn-xs btn-danger" value="
								<%out.print(LM.getText(LC.SEMEN_REQUISITION_SEARCH_SEMEN_REQUISITION_DELETE_BUTTON, loginDTO));%>
								" /></th>
								
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_SEMEN_REQUISITION);

								try
								{

									if (data != null) 
									{
										int size = data.size();										
										System.out.println("data not null and size = " + size + " data = " + data);
										for (int i = 0; i < size; i++) 
										{
											Semen_requisitionDTO row = (Semen_requisitionDTO) data.get(i);
											String deletedStyle="color:red";
											if(!row.isDeleted)deletedStyle = "";
											out.println("<tr id = 'tr_" + i + "'>");
											

											
		
											
											out.println("<td id = '" + i + "_groupId'>");
											value = row.groupId + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = Semen_requisitionAnotherDBDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											for(int j = 0; j < breedids.size(); j ++)
											{
												out.print("<td>");
												out.print(semen_requisitionDAO.getMaxDose(row.groupId, breedids.get(j)));
												out.print("</td>");
											}
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_requisitionDate'>");
											value = row.requisitionDate + "";
											SimpleDateFormat format_requisitionDate = new SimpleDateFormat("yyyy-MM-dd");
											String formatted_requisitionDate = format_requisitionDate.format(new Date(Long.parseLong(value))).toString();
											out.println(formatted_requisitionDate);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_isDelivered'>");
											value = row.isDelivered + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	



											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
	
											out.println("<a href='Semen_distributionServlet?actionType=getAddPage&groupID=" +row.groupId + "&centre=" + row.centreType + "'>Distribute</a>");
										
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
	String navigator2 = SessionConstants.NAV_SEMEN_REQUISITION;
	System.out.println("navigator2 = " + navigator2);
	RecordNavigator rn2 = (RecordNavigator)session.getAttribute(navigator2);
	System.out.println("rn2 = " + rn2);
	String pageno2 = ( rn2 == null ) ? "1" : "" + rn2.getCurrentPageNo();
	String totalpage2 = ( rn2 == null ) ? "1" : "" + rn2.getTotalPages();

%>
<input type="hidden" id="hidden_pageno" value="<%=pageno2%>" />
<input type="hidden" id="hidden_totalpage" value="<%=totalpage2%>" />


			