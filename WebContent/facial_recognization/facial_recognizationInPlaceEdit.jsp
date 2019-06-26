<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="facial_recognization.Facial_recognizationDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="geolocation.GeoLocationDAO2"%>

<%
Facial_recognizationDTO facial_recognizationDTO = (Facial_recognizationDTO)request.getAttribute("facial_recognizationDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(facial_recognizationDTO == null)
{
	facial_recognizationDTO = new Facial_recognizationDTO();
	
}
System.out.println("facial_recognizationDTO = " + facial_recognizationDTO);

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

%>



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.FACIAL_RECOGNIZATION_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_name'>")%>
			
	
	<div class="form-inline" id = 'name_div_<%=i%>'>
		<input type='text' class='form-control'  name='name' id = 'name_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.name + "'"):("''")%> required="required"
  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_address'>")%>
			
	
	<div class="form-inline" id = 'address_div_<%=i%>'>
		<div id ='address_geoDIV_<%=i%>'>
			<select class='form-control' name='address_active' id = 'address_geoSelectField_<%=i%>' onChange="addrselected(this.value, this.id, this.selectedIndex, this.name, 'address_geoDIV_<%=i%>', 'address_geolocation_<%=i%>')" required="required"  pattern="^((?!select division).)*$" title="address must be selected"
></select>
		</div>
		<input type='text' class='form-control' name='address_text' id = 'address_geoTextField_<%=i%>' value=<%=actionName.equals("edit")?("'" +  GeoLocationDAO2.parseDetails(facial_recognizationDTO.address)  + "'"):("''")%> placeholder='Road Number, House Number etc'>
		<input type='hidden' class='form-control'  name='address' id = 'address_geolocation_<%=i%>' value=<%=actionName.equals("edit")?("'" + "1" + "'"):("''")%>>
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_phone'>")%>
			
	
	<div class="form-inline" id = 'phone_div_<%=i%>'>
		<input type='text' class='form-control'  name='phone' id = 'phone_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.phone + "'"):("''")%> required="required"  pattern="880[0-9]{10}" title="phone must start with 880, then contain 10 digits"
  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_email'>")%>
			
	
	<div class="form-inline" id = 'email_div_<%=i%>'>
		<input type='text' class='form-control'  name='email' id = 'email_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.email + "'"):("''")%> required="required"  pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$" title="email must be a of valid email address format"
  />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_image'>")%>
			
	
	<div class="form-inline" id = 'image_div_<%=i%>'>
		<input type='file' class='form-control'  name='image' id = 'image_image_<%=i%>' value=<%=actionName.equals("edit")?("'" + facial_recognizationDTO.image + "'"):("'" + " " + "'")%>  />	
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + facial_recognizationDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
		