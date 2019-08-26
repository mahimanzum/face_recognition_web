<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="report.ReportDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
ReportDTO reportDTO = (ReportDTO)request.getAttribute("reportDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(reportDTO == null)
{
	reportDTO = new ReportDTO();
	
}
System.out.println("reportDTO = " + reportDTO);

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
<%@ page import="report.ReportAnotherDBDAO"%>

			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_name'>")%>
			
	
	<div id = 'name_div_<%=i%>'>
		<input type='text' class='form-control'  name='name' id = 'name_text_<%=i%>' value=<%=("''")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nid'>")%>
			
	
	<div id = 'nid_div_<%=i%>'>
		<input type='text' class='form-control'  name='nid' id = 'nid_text_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_phoneNumber'>")%>
			
	
	<div id = 'phoneNumber_div_<%=i%>'>
		<input type='text' class='form-control'  name='phoneNumber' id = 'phoneNumber_text_<%=i%>' value=<%=("''")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_address'>")%>
			
	
	<div id = 'address_div_<%=i%>'>
		<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_div_<%=i%>', 'address_geolocation_<%=i%>')"></select>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=("''")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_addressDetails'>")%>
			
	
	<div id = 'addressDetails_div_<%=i%>'>
		<textarea class='form-control'  name='addressDetails' id = 'addressDetails_textarea_<%=i%>'><%=("")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_mailId'>")%>
			
	
	<div id = 'mailId_div_<%=i%>'>
		<input type='text' class='form-control'  name='mailId' id = 'mailId_text_<%=i%>' value=<%=("''")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_typeId'>")%>
			
	
	<div id = 'typeId_div_<%=i%>'>
		<select class='form-control'  name='typeId' id = 'typeId_select_<%=i%>'>
<%
			String Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			String Options = ReportAnotherDBDAO.getOptions(Language, "select", "vehicle_type", "typeId_select_" + i, "form-control", "typeId" );
			out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_modelName'>")%>
			
	
	<div id = 'modelName_div_<%=i%>'>
		<input type='text' class='form-control'  name='modelName' id = 'modelName_text_<%=i%>' value=<%=("''")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_color'>")%>
			
	
	<div id = 'color_div_<%=i%>'>
		<input type='color' class='form-control'  name='color' id = 'color_color_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_engineNumber'>")%>
			
	
	<div id = 'engineNumber_div_<%=i%>'>
		<input type='text' class='form-control'  name='engineNumber' id = 'engineNumber_text_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_engineType'>")%>
			
	
	<div id = 'engineType_div_<%=i%>'>
		<select class='form-control'  name='engineType' id = 'engineType_select_<%=i%>'>
<%
			 Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			 Options = ReportAnotherDBDAO.getOptions(Language, "select", "engine_type", "engineType_select_" + i, "form-control", "engineType" );
			out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_chassisNumber'>")%>
			
	
	<div id = 'chassisNumber_div_<%=i%>'>
		<input type='text' class='form-control'  name='chassisNumber' id = 'chassisNumber_text_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_engineCc'>")%>
			
	
	<div id = 'engineCc_div_<%=i%>'>
		<input type='text' class='form-control'  name='engineCc' id = 'engineCc_text_<%=i%>' value=<%=("'" + "0" + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_registrationNumber'>")%>
			
	
	<div id = 'registrationNumber_div_<%=i%>'>
		<input type='text' class='form-control'  name='registrationNumber' id = 'registrationNumber_text_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_manufacturer'>")%>
			
	
	<div id = 'manufacturer_div_<%=i%>'>
		<input type='text' class='form-control'  name='manufacturer' id = 'manufacturer_text_<%=i%>' value=<%=("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_manufacturingYear'>")%>
			
	
	<div id = 'manufacturingYear_div_<%=i%>'>
		<input type='number' class='form-control'  name='manufacturingYear' id = 'manufacturingYear_number_<%=i%>' min='[1900, 2018].get(1)' max='[1900, 2018].get(2)' value=<%=("'" + "${col.getDefaultValue()}.get(1)" + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_moreDetails'>")%>
			
	
	<div id = 'moreDetails_div_<%=i%>'>
		<textarea class='form-control'  name='moreDetails' id = 'moreDetails_textarea_<%=i%>'><%=(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_reportingDate'>")%>
			
	
	<div id = 'reportingDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='reportingDate_Date_<%=i%>' id = 'reportingDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_reportingDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_reportingDate = format_reportingDate.format(new Date(reportDTO.reportingDate)).toString();
	out.println("'" + formatted_reportingDate + "'");
}
else
{
	out.println("'" + "1970-01-01" + "'");
}
%>
>
		<input type='hidden' class='form-control'  name='reportingDate' id = 'reportingDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.reportingDate + "'"):("'" + "0" + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_reporterId'>")%>
			
	
	<div id = 'reporterId_div_<%=i%>'>
		<input type='text' class='form-control'  name='reporterId' id = 'reporterId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.reporterId + "'"):("'" + "0" + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_vehicleId'>")%>
			
	
	<div id = 'vehicleId_div_<%=i%>'>
		<input type='text' class='form-control'  name='vehicleId' id = 'vehicleId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.vehicleId + "'"):("'" + "0" + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_lostDate'>")%>
			
	
	<div id = 'lostDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='lostDate_Date_<%=i%>' id = 'lostDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_lostDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_lostDate = format_lostDate.format(new Date(reportDTO.lostDate)).toString();
	out.println("'" + formatted_lostDate + "'");
}
else
{
	out.println("'" + "1970-01-01" + "'");
}
%>
>
		<input type='hidden' class='form-control'  name='lostDate' id = 'lostDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.lostDate + "'"):("'" + "0" + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_foundDate'>")%>
			
	
	<div id = 'foundDate_div_<%=i%>'>
		<input type='date' class='form-control'  name='foundDate_Date_<%=i%>' id = 'foundDate_date_Date_<%=i%>' value=<%
if(actionName.equals("edit"))
{
	SimpleDateFormat format_foundDate = new SimpleDateFormat("yyyy-MM-dd");
	String formatted_foundDate = format_foundDate.format(new Date(reportDTO.foundDate)).toString();
	out.println("'" + formatted_foundDate + "'");
}
else
{
	out.println("'" + "1970-01-01" + "'");
}
%>
>
		<input type='hidden' class='form-control'  name='foundDate' id = 'foundDate_date_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.foundDate + "'"):("'" + "0" + "'")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_statusId'>")%>
			
	
	<div id = 'statusId_div_<%=i%>'>
		<select class='form-control'  name='statusId' id = 'statusId_select_<%=i%>'>
<%
			 Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			 Options = ReportAnotherDBDAO.getOptions(Language, "select", "status", "statusId_select_" + i, "form-control", "statusId" );
			out.print(Options);
%>
		</select>
		
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_thanaAddress'>")%>
			
	
	<div id = 'thanaAddress_div_<%=i%>'>
		<select class='form-control' name='thanaAddress_active' id = 'thanaAddress_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'thanaAddress_div_<%=i%>', 'thanaAddress_geolocation_<%=i%>')"></select>
		<input type='hidden' class='form-control'  name='thanaAddress' id = 'thanaAddress_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.thanaAddress + "'"):("''")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_blog'>")%>
			
	
	<div id = 'blog_div_<%=i%>'>
		<input type='text' class='form-control'  name='blog' id = 'blog_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.blog + "'"):("'" + " " + "'")%>/>					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_image1'>")%>
			
	
	<div id = 'image1_div_<%=i%>'>
		<input type='file' class='form-control'  name='image1' id = 'image1_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.image1 + "'"):("'" + " " + "'")%>/>	
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + reportDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
	