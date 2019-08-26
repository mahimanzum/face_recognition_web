<%@page import="permission.MenuUtil"%>
<%@page import="permission.MenuRepository"%>
<%@page import="permission.MenuDTO"%>
<%@page import="language.LM"%>
<%@ page language="java"%>
<%@ page import="java.util.ArrayList,java.util.*,sessionmanager.SessionConstants,databasemanager.*"%>

<%! 
MenuUtil menuUtil = new MenuUtil();
%>

<%
List<MenuDTO> allMenuList = menuUtil.getAlignMenuListAllSeparated();
%>
<%
	String menuID = (String) session.getAttribute("menuID");
	if (menuID == null) {
		menuID = "";
	}
	System.out.println("menuID=" + menuID);
%>
<div class="form-group">
	<label for="" class="control-label col-md-4 col-sm-4 col-xs-4">Menu</label>
	<div class="col-md-8 col-sm-8 col-xs-8">
			<select class="form-control select2" size="1" name="menuID" >
			<option  value="" <%if (menuID.equals("") ){%> selected='selected' <%}%>>All</option>
			<%for(MenuDTO menuDTO : allMenuList){%>	 	
			<option value="<%=menuDTO.menuID%>" <%if (menuID.equals(""+menuDTO.menuID) ){%> selected <%}%>><%=menuDTO.menuName%></option>
			<%}%>
		</select>
	</div>
</div>