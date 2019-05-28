<%@page import="permission.MenuConstants"%>
<%@page import="user.UserRepository"%>
<%@page import="login.LoginDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="util.ActionTypeConstant"%>
<%@page import="util.JSPConstant"%>
<%@page import="role.PermissionRepository"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="user.UserDTO"%>
<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page errorPage="failure.jsp"%> --%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO loggedInUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
String url = "UserServlet?actionType=search";
String navigator = SessionConstants.NAV_USER;
String pageName = LM.getText(LC.USER_SEARCH_USER_SEARCH, loginDTO);
%>
	<jsp:include page="../includes/nav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="pageName" value="<%=pageName%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
	</jsp:include>
<%
String context = "../../.."  + request.getContextPath() + "/";
String editPageAction = JSPConstant.USER_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.USER_GET_EDIT_PAGE;
String deleteAction = JSPConstant.USER_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.USER_DELETE;
%>

<div class="portlet box">
	<div class="portlet-body">
		<form action="<%=deleteAction%>" method="POST" id="tableForm">
			<jsp:include page='../common/flushActionStatus.jsp' />
			<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th><%=LM.getText(LC.USER_SEARCH_USER_ID, loginDTO) %></th>
								<th><%=LM.getText(LC.USER_SEARCH_FULL_NAME, loginDTO) %></th>
								<th><%=LM.getText(LC.USER_SEARCH_ROLE, loginDTO) %></th>
								<th><%=LM.getText(LC.USER_SEARCH_PHONE_NO, loginDTO) %></th>
								<th><%=LM.getText(LC.USER_SEARCH_EDIT, loginDTO) %></th>
								<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(loggedInUserDTO.ID, MenuConstants.USER_DELETE)){ %>
								<th><input type="submit" class="btn btn-danger" value="<%=LM.getText(LC.USER_SEARCH_DELETE, loginDTO) %>" /></th>
								<%}%>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_USER);
									if (data != null) {
										int size = data.size();
										for (int i = 0; i < size; i++) {
											UserDTO row = (UserDTO) data.get(i);
							%>
							<tr >
								<td><%=row.userName%></td>
								<td><%=row.fullName%></td>
								<td><%=PermissionRepository.getRoleDTOByRoleID(row.roleID).roleName%></td>
								<td><%=row.phoneNo%></td>								
								<td><a href="<%=context%><%=editPageAction%>&ID=<%=row.ID%>"><%=LM.getText(LC.USER_SEARCH_EDIT, loginDTO)%></a></td>		
								<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(loggedInUserDTO.ID, MenuConstants.USER_DELETE)){ %>						
								<td><input type="checkbox" name="ID" value="<%=row.ID%>" /></td>												
								<%}%>												
							</tr>

							<%
								}
							%>
						</tbody>
						<%
							}
						%>

					</table>
				</div>			
		</form>
	</div>
</div>
<script src="<%=context%>/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#tableForm').submit(function(e) {
	    var currentForm = this;
	    var selected=false;
	    e.preventDefault();
	    var set = $('#tableData').find('tbody > tr > td:last-child input[type="checkbox"]');
	    $(set).each(function() {
	    	if($(this).prop('checked')){
	    		selected=true;
	    	}
	    });
	    if(!selected){
	    	 bootbox.alert("Select user to delete!", function() { });
	    }else{
	    	 bootbox.confirm("Are you sure you want to delete the record(s)?", function(result) {
	             if (result) {
	                 currentForm.submit();
	             }
	         });
	    }
	});
})

</script>


