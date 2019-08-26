<%@page import="geolocation.GeoLocationDAO2"%>
<%@page import="geolocation.GeoLocationDTO"%>
<%@page import="java.util.*"%>

<%
GeoLocationDAO2 geoLocDao = new GeoLocationDAO2();
int iParentID = Integer.parseInt(request.getParameter("myID"));
int iLevel = geoLocDao.getLevel(iParentID) + 1;
List<GeoLocationDTO> GeoLocationDTOList = geoLocDao.getLocation(iParentID, iLevel);
String sLevelName = geoLocDao.getLevelName(iLevel);
String returnstr = "";
if(GeoLocationDTOList.size() > 0)
{
	returnstr += "<option class='form-control'><strong>Select " + sLevelName + "</strong><br>";
}
for (int i = 0; i < GeoLocationDTOList.size(); i++) 
{
	GeoLocationDTO geoLocationDTO = GeoLocationDTOList.get(i);
	returnstr += "<option class='form-control";
	returnstr += "' name = '";
	returnstr += geoLocationDTO.name_en;
	returnstr += "' id = '";
	returnstr += "loc_" + "" + geoLocationDTO.id;
	returnstr += "' value='";
	returnstr += geoLocationDTO.id;
	returnstr += "' level='";
	returnstr += geoLocationDTO.geoLevelID;
	returnstr += "'>" ;	
	returnstr += geoLocationDTO.name_en;
	returnstr += "<br>" ;			
}
out.println(returnstr);
%>