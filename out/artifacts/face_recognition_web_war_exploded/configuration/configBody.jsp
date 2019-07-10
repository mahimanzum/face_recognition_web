<%@page import="config.GlobalConfigurationGroupRepository"%>
<%@page import="config.GlobalConfigGroupDTO"%>
<%@page import="permission.MenuConstants"%>
<%@page import="user.*"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>
<%@page import="role.PermissionRepository"%>
<%@page import="language.LC"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="config.GlobalConfigDTO"%>
<%@taglib uri="/WEB-INF/customtags.tld" prefix="m" %>
<%
	LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
%>
<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i> Global Settings
		</div>
	</div>
	<div class="portlet-body portlet-body-btcl form">
		<form action="GlobalConfigurationServlet?actionType=search" class="form-horizontal" method="post">
			<div class="form-body">
				<div class="form-group form-inline">
					<label class="col-sm-3 control-label">Group ID</label>
					<div class="col-sm-6">
					<select class="form-control select2" size="1" name="groupID" >
						<%-- <option  value="" <%if (menuID.equals("") ){%> selected='selected' <%}%>>All</option> --%>
						<%for(GlobalConfigGroupDTO globalConfigGroupDTO : GlobalConfigurationGroupRepository.getInstance().getAllGroups()){%>	 	
						<option value="<%=globalConfigGroupDTO.ID%>"><%=globalConfigGroupDTO.name%></option>
						<%}%>
					</select>
					</div>
					<input type="submit" class="btn btn-success" value="Go"/>
				</div>
			</div>			
		</form>
		<form role="form" action="GlobalConfigurationServlet?actionType=edit" class="form-horizontal" method="post">
			<div class="form-body">
			<jsp:include page='../common/flushActionStatus.jsp' />			

			<div class="table-responsive form-body">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Name</th>
								<th>Group</th>
								<th>Value</th>
								<th>Comments</th>
							</tr>
						</thead>
						<tbody>
			<%
			UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(loginDTO.userID);
				for(GlobalConfigDTO globalConfigDTO : GlobalConfigurationRepository.getAllConfigs()){
			%>							
							<tr>
							<input type="hidden" name="ID" value="<%=globalConfigDTO.ID%>">
							<td><%=globalConfigDTO.name%></td>							
							<%-- <td><input type="text" class="form-control col-md-3" name="groupID" value="<%=globalConfigDTO.groupID%>"></td> --%>
							<td>
							<select class="form-control select2" size="1" name="groupID" >						
								<%for(GlobalConfigGroupDTO globalConfigGroupDTO : GlobalConfigurationGroupRepository.getInstance().getAllGroups()){%>	 	
									<option value="<%=globalConfigDTO.groupID%>" <%if(globalConfigGroupDTO.ID == globalConfigDTO.groupID){%> selected <%}%>><%=globalConfigGroupDTO.name%></option>
								<%}%>
							</select>							
							</td>
							<td><input type="text" class="form-control col-md-3" name="value" value="<%=globalConfigDTO.value%>" required></td>
							<td><%=globalConfigDTO.comments == null ? "" : globalConfigDTO.comments%></td>
							</tr>
						<%}%>
						</tbody>
						</table>
						</div>
						
			</div>
			<div class="form-actions  text-center">										
					<%
					if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO == null?-1:userDTO.roleID, MenuConstants.GLOBAL_SETTINGS_UPDATE)){
															%>
						<input type="submit" class="btn btn-success" value="Update">  
					
					<% }%>
			</div>
		</form>
	</div>
</div>

