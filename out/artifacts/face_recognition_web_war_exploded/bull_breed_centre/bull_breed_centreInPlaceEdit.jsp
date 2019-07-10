<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="bull_breed_centre.Bull_breed_centreDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Bull_breed_centreDTO bull_breed_centreDTO = (Bull_breed_centreDTO)request.getAttribute("bull_breed_centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(bull_breed_centreDTO == null)
{
	bull_breed_centreDTO = new Bull_breed_centreDTO();
	
}
System.out.println("bull_breed_centreDTO = " + bull_breed_centreDTO);

String actionName = "edit";


String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");

String value = "";
Bull_breed_centreDTO row = bull_breed_centreDTO;
%>



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.BULL_BREED_CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullType'>")%>
			
	
	<div class="form-inline" id = 'bullType_div_<%=i%>'>
		<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>'  >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", bull_breed_centreDTO.bullType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_breedType'>")%>
			
	
	<div class="form-inline" id = 'breedType_div_<%=i%>'>
		<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>'  >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", bull_breed_centreDTO.breedType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_centreType'>")%>
			
	
	<div class="form-inline" id = 'centreType_div_<%=i%>'>
		<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>'  >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", bull_breed_centreDTO.centreType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_grsOffice'>")%>
			
	
	<div class="form-inline" id = 'grsOffice_div_<%=i%>'>
		<select class='form-control'  name='grsOffice_<%=i%>_Layer_0' id = 'grsOffice_grs_office_<%=i%>_Layer_0' 
		onchange="layerselected(0, 'grsOffice_grs_office_<%=i%>_Layer_0', 'grsOffice_grs_office_<%=i%>_Layer_1', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "grs_top_layer", "grsOffice_grs_office_" + i + "_Layer_0", "form-control", "grsOffice_" + i + "_Layer_0", bull_breed_centreDTO.grsOffice + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "grs_top_layer", "grsOffice_grs_office_" + i + "_Layer_0", "form-control", "grsOffice_" + i + "_Layer_0", "any" );			
}
out.print(Options);
%>
		</select>
		<select class='form-control' style='display: none;'  name='grsOffice_<%=i%>_Layer_1' id = 'grsOffice_grs_office_<%=i%>_Layer_1'
		 onchange="layerselected(1, 'grsOffice_grs_office_<%=i%>_Layer_1', 'grsOffice_grs_office_<%=i%>_Layer_2', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
		</select>
		<select class='form-control' style='display: none;' name='grsOffice_<%=i%>_Layer_2' id = 'grsOffice_grs_office_<%=i%>_Layer_2'
		 onchange="layerselected(2, 'grsOffice_grs_office_<%=i%>_Layer_2', 'unknown', 'grsOffice_grs_office_<%=i%>', 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN', 'grsOfficer_grs_officer_<%=i%>')">
		</select>
		<input type = 'hidden' name='grsOffice_<%=i%>_TOP_HIDDEN' id = 'grsOffice_grs_office_<%=i%>_TOP_HIDDEN' value='3'>
		<input type = 'hidden' class='form-control'  name='grsOffice' id = 'grsOffice_grs_office_<%=i%>' >
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_grsOfficer'>")%>
			
	
	<div class="form-inline" id = 'grsOfficer_div_<%=i%>'>
		<select class='form-control'  name='grsOfficer' id = 'grsOfficer_grs_officer_<%=i%>' disabled>
		</select>
		
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_infoFile'>")%>
			
	
	<div class="form-inline" id = 'infoFile_div_<%=i%>'>
		<input type='file' class='form-control'  name='infoFile' id = 'infoFile_file_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_breed_centreDTO.infoFile + "'"):("'" + " " + "'")%>  />	
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_bullImage'>")%>
			
	
	<div class="form-inline" id = 'bullImage_div_<%=i%>'>
		<input type='file' class='form-control'  name='bullImage' id = 'bullImage_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + bull_breed_centreDTO.bullImage + "'"):("'" + " " + "'")%>  />	
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>'  ><%=actionName.equals("edit")?(bull_breed_centreDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + bull_breed_centreDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
		