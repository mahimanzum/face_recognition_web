<%@page import="permission.MenuConstants"%>
<%@page import="user.UserRepository"%>
<%@page import="user.UserDTO"%>
<%@page import="login.LoginDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="util.ServletConstant"%>
<%@page import="util.JSPConstant"%>
<%@ page language="java"%>

<%@ page import="java.util.ArrayList,
 				 sessionmanager.SessionConstants,
				 role.*"%>

<%@ page errorPage="failure.jsp"%>
<!-- <script>
var pieces = window.location.href.split("/");
alert(pieces[pieces.length-1]);
</script> -->
<%String context = "../../.."  + request.getContextPath() + "/";
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
%>
			<%
			
				String url = "RoleServlet?actionType=search";
				String navigator = SessionConstants.NAV_ROLE;
				String pageName = LM.getText(LC.ROLE_SEARCH_ROLE_SEARCH, loginDTO);
			%>
			<jsp:include page="../includes/nav.jsp" flush="true">
				<jsp:param name="url" value="<%=url%>" />
				<jsp:param name="pageName" value="<%=pageName%>" />
				<jsp:param name="navigator" value="<%=navigator%>" />
			</jsp:include>

		<div class="portlet box">
		<div class="portlet-body">
		<form action="RoleServlet?actionType=delete" method="POST" id="tableForm">
		<jsp:include page='../common/flushActionStatus.jsp' />
			<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
							<th><%=LM.getText(LC.ROLE_SEARCH_ROLE_NAME, loginDTO) %></th>
							<th><%=LM.getText(LC.ROLE_SEARCH_DESCRIPTION, loginDTO) %></th>
							<th><%=LM.getText(LC.ROLE_SEARCH_EDIT, loginDTO)%></th>		
							<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_DELETE)){ %>					
								<th><input type="submit" class="btn btn-danger" value="<%=LM.getText(LC.ROLE_SEARCH_DELETE, loginDTO)%>" /></th>
							<%} %>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_ROLE);

									if (data != null) {
										int size = data.size();

										for (int i = 0; i < size; i++) {

											RoleDTO row = (RoleDTO) data.get(i);
							%>
							<tr>
								<td><%=row.roleName%></td>
								<td><%=row.description%></td>
								<td><a href="<%=context%><%=JSPConstant.ROLE_GET_EDIT_PAGE_SERVLET%>&<%=ServletConstant.ID%>=<%=row.ID%>"><%=LM.getText(LC.ROLE_SEARCH_EDIT, loginDTO)%></a></td>
								
								<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_DELETE)){ %>
									<td><input type="checkbox" name="ID" value="<%=row.ID%>" /></td>
								<%} %>
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
	    	 bootbox.alert("Select role to delete!", function() { });
	    }else{
	    	 bootbox.confirm("Are you sure you want to delete the role (s)?", function(result) {
	             if (result) {
	                 currentForm.submit();
	             }
	         });
	    }
	});
})

</script>