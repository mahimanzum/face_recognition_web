

<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull_breed_centre.*"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="pb.*"%>
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
String Language = LM.getText(LC.BULL_BREED_CENTRE_EDIT_LANGUAGE, loginDTO);

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
long id = Long.parseLong(ID);
System.out.println("ID = " + ID);
Bull_breed_centreDAO bull_breed_centreDAO = new Bull_breed_centreDAO();
Bull_breed_centreDTO row = bull_breed_centreDAO.getBull_breed_centreDTOByID(id);
String Value = "";
int i = 0;
%>


<div class="modal-content">
            <div class="modal-header">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-9 col-sm-12">
                            <h5 class="modal-title"><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULL_BREED_CENTRE_EDIT_FORMNAME, loginDTO)%></h5>
                        </div>
                        <div class="col-md-3 col-sm-12">
                            <div class="row">
                                <div class="col-md-6">
                                    <a href="javascript:" style="display: none" class="btn btn-success app_register" data-id="419637"> Register </a>
                                </div>
                                <div class="col-md-6">
                                    <a href="javascript:" style="display: none" class="btn btn-danger app_reject" data-id="419637"> Reject </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">Ã</span>
                </button>
            </div>

            <div class="modal-body container">

			
                <div class="row div_border office-div">

                    <div class="col-md-12">
                        <h5>Info Group 1</h5>
                    </div>
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLTYPE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="bullType">
						<%
											value = row.bullType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "bull", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BREEDTYPE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="breedType">
						<%
											value = row.breedType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "breed", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_CENTRETYPE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="centreType">
						<%
											value = row.centreType + "";
											
											value = CommonDAO.getName(Integer.parseInt(value), "centre", Language.equals("English")?"name_en":"name_bn", "id");
														
											out.println(value);
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="grsOffice">
						<%
											value = row.grsOffice + "";
											value = GRS_OFFICE_DAO.getOfficeName(Integer.parseInt(value), Language);										
														
											out.println(value);
				
			
						%>
                        </label>
                    </div>

                </div>
				

			
                <div class="row div_border office-div">

                    <div class="col-md-12">
                        <h5>Info Group 1</h5>
                    </div>
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_GRSOFFICER, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="grsOfficer">
						<%
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
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_INFOFILE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="infoFile">
						<%
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
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_BULLIMAGE, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="bullImage">
						<%
											value = row.bullImage + "";
											out.println("<img src='img2/" + value +"' style='width:100px' >");
				
			
						%>
                        </label>
                    </div>

				

			
				
                    <div class="col-md-6">
                        <label class="col-md-5" style="padding-right: 0px;"><b><span><%=LM.getText(LC.BULL_BREED_CENTRE_EDIT_DESCRIPTION, loginDTO)%></span><span style="float:right;">:</span></b></label>
                        <label id="description">
						<%
											value = row.description + "";
														
											out.println(value);
				
			
						%>
                        </label>
                    </div>

                </div>
				

			
			
		

	
				

                <div class="row div_border attachement-div">
                    <div class="col-md-12">
                        <h5>Attachment</h5>
                    </div>
                    <div class="col-md-6">
                        <ul id="attachment_list"></ul>
                    </div>
                </div>
                

               

            <div class="modal-footer" style="justify-content:flex-start">



                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

            </div>

        </div>