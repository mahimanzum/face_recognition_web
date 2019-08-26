
<%@page import="citizen.CitizenDAO"%>
<%@page import="vehicle.VehicleDAO"%>
<%@page import="citizen.CitizenDTO"%>
<%@page import="vehicle.VehicleDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%@page import="report.ReportDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>

<%
ReportDTO reportDTO;
VehicleDTO vehicleDTO=null;
CitizenDTO citizenDTO=null;
reportDTO = (ReportDTO)request.getAttribute("reportDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
if(reportDTO == null)
{
	reportDTO = new ReportDTO();
	
	
}else{
	vehicleDTO = new VehicleDAO().getVehicleDTOByID(reportDTO.vehicleId);
	citizenDTO = new CitizenDAO().getCitizenDTOByID(reportDTO.reporterId);
	
	if(vehicleDTO==null)vehicleDTO = new VehicleDTO();
	if(citizenDTO==null)citizenDTO = new CitizenDTO();
}
System.out.println("reportDTO = " + reportDTO);

String actionName;
System.out.println("actionType = " + request.getParameter("actionType"));
if (request.getParameter("actionType").equalsIgnoreCase("getAddPage"))
{
	actionName = "add";
}
else
{
	actionName = "edit";
}
String formTitle;
if(actionName.equals("edit"))
{
	formTitle = LM.getText(LC.REPORT_EDIT_REPORT_EDIT_FORMNAME, loginDTO);
}
else
{
	formTitle = LM.getText(LC.REPORT_ADD_REPORT_ADD_FORMNAME, loginDTO);
}

String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = 0;
%>



<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="ReportServlet?actionType=<%=actionName%>&identity=<%=ID%>"
		id="bigform" name="bigform"  method="POST" enctype = "multipart/form-data"
		onsubmit="return PreprocessBeforeSubmiting(0,<%=actionName.equals("edit")?false:true%>)">
			<div class="form-body">
				
				
				








	

























<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="report.ReportAnotherDBDAO"%>


		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_NAME, loginDTO)):(LM.getText(LC.REPORT_ADD_NAME, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'name_div_<%=i%>'>	
		<input type='text' class='form-control'  name='name' id = 'name_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + citizenDTO.name + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_NID, loginDTO)):(LM.getText(LC.REPORT_ADD_NID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'nid_div_<%=i%>'>	
		<input type='text' class='form-control'  name='nid' id = 'nid_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + citizenDTO.nid + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_PHONENUMBER, loginDTO)):(LM.getText(LC.REPORT_ADD_PHONENUMBER, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'phoneNumber_div_<%=i%>'>	
		<input type='text' class='form-control'  name='phoneNumber' id = 'phoneNumber_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + citizenDTO.phoneNumber + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_ADDRESS, loginDTO)):(LM.getText(LC.REPORT_ADD_ADDRESS, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'address_div_<%=i%>'>	
		<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_div_<%=i%>', 'address_geolocation_<%=i%>')"></select>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + citizenDTO.address + "'"):("'" + " " + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_ADDRESSDETAILS, loginDTO)):(LM.getText(LC.REPORT_ADD_ADDRESSDETAILS, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'addressDetails_div_<%=i%>'>	
		<textarea class='form-control'  name='addressDetails' id = 'addressDetails_textarea_<%=i%>'><%=actionName.equals("edit")?(citizenDTO.addressDetails):( " " )%></textarea>		
											
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_MAILID, loginDTO)):(LM.getText(LC.REPORT_ADD_MAILID, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'mailId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='mailId' id = 'mailId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + citizenDTO.mailId + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_TYPEID, loginDTO)):(LM.getText(LC.REPORT_ADD_TYPEID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'typeId_div_<%=i%>'>	
		<select class='form-control'  name='typeId' id = 'typeId_select_<%=i%>'>
<%
			String Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			String Options = ReportAnotherDBDAO.getOptions(Language, "select", "vehicle_type", "typeId_select_" + i, "form-control", "typeId" );
			out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_MODELNAME, loginDTO)):(LM.getText(LC.REPORT_ADD_MODELNAME, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'modelName_div_<%=i%>'>	
		<input type='text' class='form-control'  name='modelName' id = 'modelName_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.modelName + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_COLOR, loginDTO)):(LM.getText(LC.REPORT_ADD_COLOR, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'color_div_<%=i%>'>	
		<input type='color' class='form-control'  name='color' id = 'color_color_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.color + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_ENGINENUMBER, loginDTO)):(LM.getText(LC.REPORT_ADD_ENGINENUMBER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'engineNumber_div_<%=i%>'>	
		<input type='text' class='form-control'  name='engineNumber' id = 'engineNumber_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.engineNumber + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_ENGINETYPE, loginDTO)):(LM.getText(LC.REPORT_ADD_ENGINETYPE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'engineType_div_<%=i%>'>	
		<select class='form-control'  name='engineType' id = 'engineType_select_<%=i%>'>
<%
			 Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			 Options = ReportAnotherDBDAO.getOptions(Language, "select", "engine_type", "engineType_select_" + i, "form-control", "engineType" );
			out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_CHASSISNUMBER, loginDTO)):(LM.getText(LC.REPORT_ADD_CHASSISNUMBER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'chassisNumber_div_<%=i%>'>	
		<input type='text' class='form-control'  name='chassisNumber' id = 'chassisNumber_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.chassisNumber + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_ENGINECC, loginDTO)):(LM.getText(LC.REPORT_ADD_ENGINECC, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'engineCc_div_<%=i%>'>	
		<input type='text' class='form-control'  name='engineCc' id = 'engineCc_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.engineCc + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_REGISTRATIONNUMBER, loginDTO)):(LM.getText(LC.REPORT_ADD_REGISTRATIONNUMBER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'registrationNumber_div_<%=i%>'>	
		<input type='text' class='form-control'  name='registrationNumber' id = 'registrationNumber_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.registrationNumber + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_MANUFACTURER, loginDTO)):(LM.getText(LC.REPORT_ADD_MANUFACTURER, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'manufacturer_div_<%=i%>'>	
		<input type='text' class='form-control'  name='manufacturer' id = 'manufacturer_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + vehicleDTO.manufacturer + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_MANUFACTURINGYEAR, loginDTO)):(LM.getText(LC.REPORT_ADD_MANUFACTURINGYEAR, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'manufacturingYear_div_<%=i%>'>	
		<input type='number' class='form-control'  name='manufacturingYear' id = 'manufacturingYear_number_<%=i%>' min='[1900, 2018].get(1)' max='[1900, 2018].get(2)' value=<%=actionName.equals("edit")?("'" + vehicleDTO.manufacturingYear + "'"):("'" + "${col.getDefaultValue()}.get(1)" + "'")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_MOREDETAILS, loginDTO)):(LM.getText(LC.REPORT_ADD_MOREDETAILS, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'moreDetails_div_<%=i%>'>	
		<textarea class='form-control'  name='moreDetails' id = 'moreDetails_textarea_<%=i%>'><%=actionName.equals("edit")?(vehicleDTO.moreDetails):(" ")%></textarea>		
											
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_REPORTINGDATE, loginDTO)):(LM.getText(LC.REPORT_ADD_REPORTINGDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'reportingDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_REPORTERID, loginDTO)):(LM.getText(LC.REPORT_ADD_REPORTERID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'reporterId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='reporterId' id = 'reporterId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.reporterId + "'"):("'" + "0" + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_VEHICLEID, loginDTO)):(LM.getText(LC.REPORT_ADD_VEHICLEID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'vehicleId_div_<%=i%>'>	
		<input type='text' class='form-control'  name='vehicleId' id = 'vehicleId_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.vehicleId + "'"):("'" + "0" + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_LOSTDATE, loginDTO)):(LM.getText(LC.REPORT_ADD_LOSTDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'lostDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_FOUNDDATE, loginDTO)):(LM.getText(LC.REPORT_ADD_FOUNDDATE, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'foundDate_div_<%=i%>'>	
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
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_STATUSID, loginDTO)):(LM.getText(LC.REPORT_ADD_STATUSID, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'statusId_div_<%=i%>'>	
		<select class='form-control'  name='statusId' id = 'statusId_select_<%=i%>'>
<%
			 Language = LM.getText(LC.REPORT_EDIT_LANGUAGE, loginDTO);
			 Options = ReportAnotherDBDAO.getOptions(Language, "select", "status", "statusId_select_" + i, "form-control", "statusId" );
			out.print(Options);
%>
		</select>
		
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_THANAADDRESS, loginDTO)):(LM.getText(LC.REPORT_ADD_THANAADDRESS, loginDTO))%>
	<span class="required"> * </span>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'thanaAddress_div_<%=i%>'>	
		<select class='form-control' name='thanaAddress_active' id = 'thanaAddress_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'thanaAddress_div_<%=i%>', 'thanaAddress_geolocation_<%=i%>')"></select>
		<input type='hidden' class='form-control'  name='thanaAddress' id = 'thanaAddress_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.thanaAddress + "'"):("''")%>>
						
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_BLOG, loginDTO)):(LM.getText(LC.REPORT_ADD_BLOG, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'blog_div_<%=i%>'>	
		<input type='text' class='form-control'  name='blog' id = 'blog_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.blog + "'"):("'" + " " + "'")%>/>					
	</div>
</div>			
				
	
<label class="col-sm-3 control-label">
	<%=(actionName.equals("edit"))?(LM.getText(LC.REPORT_EDIT_IMAGE1, loginDTO)):(LM.getText(LC.REPORT_ADD_IMAGE1, loginDTO))%>
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'image1_div_<%=i%>'>	
		<input type='file' class='form-control' multiple="multiple"  name='image1' id = 'image1_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + reportDTO.image1 + "'"):("'" + " " + "'")%>/>	
						
	</div>
</div>			
				

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + reportDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
					
	
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.REPORT_EDIT_REPORT_CANCEL_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.REPORT_ADD_REPORT_CANCEL_BUTTON, loginDTO));
					}
					
					%>
					</a>
					<button class="btn btn-success" type="submit">
					<%
					if(actionName.equals("edit"))
					{
						out.print(LM.getText(LC.REPORT_EDIT_REPORT_SUBMIT_BUTTON, loginDTO));
					}
					else
					{
						out.print(LM.getText(LC.REPORT_ADD_REPORT_SUBMIT_BUTTON, loginDTO));
					}
					%>
					</button>
				</div>
							
			</div>
		
		</form>

	</div>
</div>

<script src="nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">


function PreprocessBeforeSubmiting(row, validate)
{
	console.log("found date = " + document.getElementById('reportingDate_date_Date_' + row).value);
	document.getElementById('reportingDate_date_' + row).value = new Date(document.getElementById('reportingDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('lostDate_date_Date_' + row).value);
	document.getElementById('lostDate_date_' + row).value = new Date(document.getElementById('lostDate_date_Date_' + row).value).getTime();
	console.log("found date = " + document.getElementById('foundDate_date_Date_' + row).value);
	document.getElementById('foundDate_date_' + row).value = new Date(document.getElementById('foundDate_date_Date_' + row).value).getTime();
	if(validate)
	{
		var empty_fields = "";
		var i = 0;
		if(document.getElementById('name_text_' + row).value == "")
		{
			empty_fields += "'name'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('phoneNumber_text_' + row).value == "")
		{
			empty_fields += "'phoneNumber'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('address_geolocation_' + row).value == "")
		{
			empty_fields += "'address'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('addressDetails_textarea_' + row).value == "")
		{
			empty_fields += "'addressDetails'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('mailId_text_' + row).value == "")
		{
			empty_fields += "'mailId'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('modelName_text_' + row).value == "")
		{
			empty_fields += "'modelName'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(document.getElementById('thanaAddress_geolocation_' + row).value == "")
		{
			empty_fields += "'thanaAddress'";
			if(i > 0)
			{
				empty_fields += ", ";
			}
			i ++;
		}
		if(empty_fields != "")
		{
			alert(empty_fields + " cannot be empty.");
			return false;
		}
	}
	return true;
}
function addrselected(value, htmlID, selectedIndex, tagname, geodiv, hiddenfield)
{
	console.log('geodiv = ' + geodiv + ' hiddenfield = ' + hiddenfield);
	try 
	{
		document.getElementById(hiddenfield).value = value;
		var elements = document.getElementById(geodiv).children;
		var ids = '';
		for(var i = elements.length - 1; i >= 0; i--) {
			var elemID = elements[i].id;
			if(elemID.includes(htmlID) && elemID > htmlID)
			{
				ids += elements[i].id + ' ';
				
				for(var j = elements[i].options.length - 1; j >= 0; j--)
				{
				
					elements[i].options[j].remove();
				}
				elements[i].remove();
				
			}
		}


		var newid = htmlID + '_1';

		document.getElementById(geodiv).innerHTML += "<select class='form-control' name='" + tagname + "' id = '" + newid 
		+ "' onChange=\"addrselected(this.value, this.id, this.selectedIndex, this.name, '" + geodiv +"', '" + hiddenfield +"')\"></select>";
		console.log('innerHTML= ' + document.getElementById(geodiv).innerHTML);
		document.getElementById(htmlID).options[0].innerHTML = document.getElementById(htmlID).options[selectedIndex].innerHTML;
		document.getElementById(htmlID).options[0].value = document.getElementById(htmlID).options[selectedIndex].value;
		console.log('innerHTML again = ' + document.getElementById(geodiv).innerHTML);
		
		 var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() 
		  {
			if (this.readyState == 4 && this.status == 200) 
			{
				if(!this.responseText.includes('option'))
				{
					document.getElementById(newid).remove();
				}
				else
				{
					document.getElementById(newid).innerHTML = this.responseText ;
				}
				
			}
			else if(this.readyState == 4 && this.status != 200)
			{
				alert('failed ' + this.status);
			}
		  };
		 
		  var redirect = "geolocation/geoloc.jsp?myID="+value;
		  
		  xhttp.open("GET", redirect, true);
		  xhttp.send();
	}
	catch(err) 
	{
		alert("got error: " + err);
	}	  
	return;
}

function addHTML(id, HTML)
{
	document.getElementById(id).innerHTML += HTML;
}

function getRequests() 
{
    var s1 = location.search.substring(1, location.search.length).split('&'),
        r = {}, s2, i;
    for (i = 0; i < s1.length; i += 1) {
        s2 = s1[i].split('=');
        r[decodeURIComponent(s2[0]).toLowerCase()] = decodeURIComponent(s2[1]);
    }
    return r;
}

function Request(name){
    return getRequests()[name.toLowerCase()];
}

function ShowExcelParsingResult(suffix)
{
	var failureMessage = document.getElementById("failureMessage_" + suffix);
	if(failureMessage == null)
	{
		console.log("failureMessage_" + suffix + " not found");
	}
	console.log("value = " + failureMessage.value);
	if(failureMessage != null &&  failureMessage.value != "")
	{
		alert("Excel uploading result:" + failureMessage.value);
	}
}

function init(row)
{
		
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) 
	    {
	    	document.getElementById('address_geoSelectField_' + row).innerHTML = this.responseText ;
	    	document.getElementById('thanaAddress_geoSelectField_' + row).innerHTML = this.responseText ;
	    }
	    else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	  };
	  xhttp.open("GET", "geolocation/geoloc.jsp?myID=1", true);
	  xhttp.send();
}var row = 0;
bkLib.onDomLoaded(function() 
{	
});
	
window.onload =function ()
{
	init(row);
}

</script>






