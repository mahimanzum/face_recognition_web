<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="centre.CentreDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>

<%
CentreDTO centreDTO = (CentreDTO)request.getAttribute("centreDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(centreDTO == null)
{
	centreDTO = new CentreDTO();
	
}
System.out.println("centreDTO = " + centreDTO);

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
<%
String Language = LM.getText(LC.CENTRE_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameEn'>")%>
			
	
	<div class="form-inline" id = 'nameEn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameEn' id = 'nameEn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.nameEn + "'"):("''")%> required="required"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_nameBn'>")%>
			
	
	<div class="form-inline" id = 'nameBn_div_<%=i%>'>
		<input type='text' class='form-control'  name='nameBn' id = 'nameBn_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.nameBn + "'"):("''")%> required="required"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_address'>")%>
			
	
	<div class="form-inline" id = 'address_div_<%=i%>'>
		<div id ='address_geoDIV_<%=i%>'>
			<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_geoDIV_<%=i%>', 'address_geolocation_<%=i%>')" required="required"  pattern="^((?!select division).)*$" title="address must be selected"
></select>
		</div>
		<input type='text' class='form-control' name='address_text' id = 'address_geoTextField_<%=i%>' value=<%=actionName.equals("edit")?("'" +  GeoLocationDAO2.parseDetails(centreDTO.address)  + "'"):("''")%> placeholder='Road Number, House Number etc'>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + "1" + "'"):("''")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_contactPerson'>")%>
			
	
	<div class="form-inline" id = 'contactPerson_div_<%=i%>'>
		<input type='text' class='form-control'  name='contactPerson' id = 'contactPerson_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.contactPerson + "'"):("'" + " " + "'")%> required="required"  pattern="880[0-9]{10}" title="contactPerson must start with 880, then contain 10 digits"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_phoneNumber'>")%>
			
	
	<div class="form-inline" id = 'phoneNumber_div_<%=i%>'>
		<input type='number' class='form-control'  name='phoneNumber' id = 'phoneNumber_number_<%=i%>' min='0' max='1000000' value=<%=actionName.equals("edit")?("'" + centreDTO.phoneNumber + "'"):("''")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_email'>")%>
			
	
	<div class="form-inline" id = 'email_div_<%=i%>'>
		<input type='text' class='form-control'  name='email' id = 'email_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + centreDTO.email + "'"):("''")%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="email must be a of valid email address format"
 />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + centreDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_description'>")%>
			
	
	<div class="form-inline" id = 'description_div_<%=i%>'>
		<textarea class='form-control'  name='description' id = 'description_textarea_<%=i%>' ><%=actionName.equals("edit")?(centreDTO.description):(" ")%></textarea>		
											
	</div>
				
<%=("</td>")%>
					
	