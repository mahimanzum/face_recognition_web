<%@page import="pb.*"%>
<%@page import="java.util.*"%>

<%

System.out.println("in jsp");
List<GRS_OFFICE_DTO> GRS_DTOList = (List<GRS_OFFICE_DTO>)request.getAttribute("GRS_DTO_List");
String returnstr = "";
returnstr += "<option class='form-control";

returnstr += "' value='";
returnstr += "-1";
returnstr += "'>" ;	
returnstr += "Select";
returnstr += "</option>" ;	
for (int i = 0; i < GRS_DTOList.size(); i++) 
{
	GRS_OFFICE_DTO gRS_DTO = GRS_DTOList.get(i);
	returnstr += "<option class='form-control";
	
	returnstr += "' value='";
	returnstr += gRS_DTO.id;
	returnstr += "'>" ;	
	returnstr += gRS_DTO.name;
	returnstr += "</option>" ;			
}
out.println(returnstr);
%>