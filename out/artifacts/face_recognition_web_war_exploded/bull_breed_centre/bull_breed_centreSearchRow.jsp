<%@page pageEncoding="UTF-8" %>

<%@page import="bull_breed_centre.Bull_breed_centreDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page import="pb.*"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
String Language = LM.getText(LC.BULL_BREED_CENTRE_EDIT_LANGUAGE, loginDTO);

Bull_breed_centreDTO row = (Bull_breed_centreDTO)request.getAttribute("bull_breed_centreDTO");
if(row == null)
{
	row = new Bull_breed_centreDTO();
	
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


											
		
											
											out.println("<td id = '" + i + "_bullType'>");
											value = row.bullType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_breedType'>");
											value = row.breedType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_centreType'>");
											value = row.centreType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_grsOffice'>");
											value = row.grsOffice + "";
											value = GRS_OFFICE_DAO.getOfficeName(Integer.parseInt(value), Language);										
														
											out.println(value);
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_grsOfficer'>");
											value = row.grsOfficer + "";
											GRS_OFFICER_DTO grsOfficerDTO_grsOfficer = GRS_OFFICER_DAO.getGRS_Officer_DTOByID(Long.parseLong(value));
											
											if(Language.equalsIgnoreCase("english"))
											{
												out.println(grsOfficerDTO_grsOfficer.name_eng + ", " + grsOfficerDTO_grsOfficer.designation_eng);
											}
											else
											{
												out.println(grsOfficerDTO_grsOfficer.name_bng + ", " + grsOfficerDTO_grsOfficer.designation_bng);
											}											
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_infoFile'>");
											value = row.infoFile + "";
											out.println("<a href = 'img2/" + value + "' download='" + value + "'>Download</a>");
											out.println("<a href='Bull_breed_centreServlet?actionType=getURL&URL=img2/" + value + "'>Open</a>");											
											out.println("<a href='#' data-toggle='modal' data-target='#infoFileModal_" + i + "'>View</a>");

											  


											out.println("<div class='modal fade' id='infoFileModal_" + i + "'>");
											  out.println("<div class='modal-dialog modal-lg' role='document'>");
											    out.println("<div class='modal-content'>");
											      out.println("<div class='modal-body'>");
											        out.println("<button type='button' class='close' data-dismiss='modal' aria-label='Close'>");
											          out.println("<span aria-hidden='true'>&times;</span>");
											        out.println("</button>");
											        if(value.endsWith(".pdf"))
											        {
											        	 out.println("<object type='application/pdf' data='img2/" + value +  "' width='100%' height='500' style='height: 85vh;'>No Support</object>");
											        }
											        else
											        {
											        	 out.println("<object type='text/html' data='img2/" + value +  "' width='100%' height='500' style='height: 85vh;'>No Support</object>");
											        }
											      out.println("</div>");
											    out.println("</div>");
											  out.println("</div>");
											out.println("</div>");
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_bullImage'>");
											value = row.bullImage + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
											out.println("</td>");
		
											
											out.println("<td id = '" + i + "_description'>");
											value = row.description + "";
														
											out.println(value);
				
			
											out.println("</td>");
		
											
		
	

	

											
											String onclickFunc = "\"fixedToEditable(" + i + ",'" + deletedStyle + "', '" + row.iD + "' )\"";										
	
											out.println("<td id = '" + i + "_Edit'>");										
											out.println("<a onclick=" + onclickFunc + ">" + LM.getText(LC.BULL_BREED_CENTRE_SEARCH_BULL_BREED_CENTRE_EDIT_BUTTON, loginDTO) + "</a>");
										
											out.println("</td>");
											
											
											
											out.println("<td>");
											out.println("<div class='checker'>");
											out.println("<span id='chkEdit' ><input type='checkbox' name='ID' value='" + row.iD + "'/></span>");
											out.println("</div");
											out.println("</td>");%>

