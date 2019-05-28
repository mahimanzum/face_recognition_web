<%@page import="image.ImageDAO"%>
<%@page import="util.CommonConstant"%>
<%@page import="org.apache.tomcat.util.bcel.classfile.ConstantLong"%>
<%@page import="user.UserRepository"%>
<%@page import="user.UserDTO"%>
<%@page import="util.ServletConstant"%>
<%@page import="util.ActionTypeConstant"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="util.JSPConstant"%>
<%@page import="login.LoginDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="permission.ColumnRepository"%>
<%@page import="util.CollectionUtils"%>
<%@page import="role.PermissionRepository"%>
<%@page import="role.*"%>
<%@page import="permission.MenuRepository"%>
<%@page import="permission.*"%>
<%@page import="role.PermissionRepository"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="image.*"%>
<%@page import="java.util.*"%>
<%


String action = "";
LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
%>




<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i>Image Setting
		</div>
	</div>




	<div class="portlet-body portlet-body-btcl form">



		<form role="form" action="<%=action%>" class="form-horizontal"
			method="post" enctype="multipart/form-data">
			<div class="form-body">
				<jsp:include page='../common/flushActionStatus.jsp' />




				<div class="table-responsive">


					<table id="tableData" class="table table-bordered table-striped">
						<col width="5%">
						<col width="20%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<thead>
							<tr>
								<td>ID</td>
								<td>Description</td>
								<td>Image</td>
								<td>preview</td>
								<td>Upload</td>
								<td>Delete</td>
							</tr>
						</thead>
						<tbody>
							<%
							
								List<ImageDTO> imageDTOs = new ImageDAO().getAllImageDTOList();
								int index = 0;
								for(ImageDTO imageDTO: imageDTOs){
							%>
							<tr>
								<td><%=imageDTO.ID%></td>
								<td><input type="text" name="URL" class="black-text" value="<%=imageDTO.URL%>">  <input type="hidden" name="ID" value="<%=imageDTO.ID%>">  </td>
								<td> 
									<img src="<%=request.getContextPath()%>/images/<%=imageDTO.fileName %>" alt="hh" height="100" />   
								</td>
								<td><img id="<%=index%>" alt="image"  height="100" /></td>
								<td><input type="file" name="<%=imageDTO.fileName%>" accept="image/*" onchange="document.getElementById('<%=index%>').src=window.URL.createObjectURL(this.files[0])"  ></td>
								<td><input type="checkbox" name="deletedID" value="<%=imageDTO.ID%>"> </td>
							</tr>
							<% index++;}%>

						</tbody>
					</table>


				</div>
				<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMAGE_SETTING_UPDATE)){ %>
				<div class="form-actions text-center">
					<a class="btn btn-danger"
						href="<%=request.getHeader("referer")%>"><%=LM.getText(LC.ROLE_ADD_CANCEL, loginDTO)%></a>
					<button class="btn btn-success" type="submit"><%=LM.getText(LC.ROLE_ADD_SUBMIT, loginDTO)%></button>
				</div>
				<%} %>
			</div>
		</form>



	</div>


</div>



<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i>Add New Image
		</div>
	</div>
	<div class="portlet-body portlet-body-btcl form">
		<form role="form" action="ImageServlet?actionType=add"
			class="form-horizontal" method="post">


			<input type="hidden" name="menuID" value="0" /><br>

			<div class="form-group ">
				<label class="col-sm-3 control-label">File Name</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="fileName"
						name="fileName" value="" required="required">
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Description</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="menuNameBangla"
						name="description" value="" required="required">
				</div>
			</div>




			<div class="form-group ">
				<label class="col-sm-3 control-label">File Path</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="hyperLink"
						name="filePath" value="">
				</div>
			</div>

			<div class="form-actions text-center">
					<button class="btn btn-success" type="submit"><%=LM.getText(LC.ROLE_ADD_SUBMIT, loginDTO)%></button>
				</div>
		</form>

	</div>
</div>