<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>

<%
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	long id = 0;//Long.parseLong(request.getAttribute("ID").toString());
	boolean permissionForAddPage = false, permissionForEditPage = false, isAddPage = false;
	if(id == -1){
		isAddPage = true;
		//permissionForAddPage = ( loginDTO.getMenuPermission( PermissionConstants.ROLE_ADD )>PermissionConstants.PERMISSION_READ );
	}else {
		//permissionForEditPage = (loginDTO.getMenuPermission(PermissionConstants.ROLE_ADD) >= PermissionConstants.PERMISSION_MODIFY);
	}
	
	
	
	String title = isAddPage?"Add Role": "Edit Role";
%>
<jsp:include page="../common/layout.jsp" flush="true">
<jsp:param name="title" value="<%=title%>"/> 
	<jsp:param name="body" value="../roles/roleEditBody.jsp" />
</jsp:include> 