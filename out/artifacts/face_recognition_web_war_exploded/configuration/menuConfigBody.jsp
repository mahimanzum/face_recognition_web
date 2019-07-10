<%@page import="permission.MenuConstants"%>
<%@page import="permission.MenuUtil"%>
<%@page import="permission.MenuRepository"%>
<%@page import="permission.MenuDTO"%>
<%@page import="role.PermissionRepository"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="config.GlobalConfigDTO"%>
<%@page import="java.util.*"%>
<%@page pageEncoding="UTF-8" %>
<%@page import="login.LoginDTO"%>
<%@page import="user.*"%>
<%@page import="sessionmanager.SessionConstants"%>
<%

MenuUtil menuUtil = new MenuUtil();

LoginDTO loginDTO = (LoginDTO) request .getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);



List<MenuDTO> allMenuList = menuUtil.getAlignMenuListAll();
%>

<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i>Menu Settings
		</div>
	</div>
	<div class="portlet-body portlet-body-btcl form">
		<form role="form" action="MenuConfigurationServlet?actionType=edit" class="form-horizontal" method="post">
			<div class="form-body">
				<div class="table-responsive">
					
					<%
					for(int isAPI = 0;isAPI<2;isAPI++){ 
							
					
					int orderIndex = 0;
					
					%>
						
						<table id="tableData" class="table table-bordered table-striped">
							<thead>
								<tr >
									<td>ID</td>
									<td ><%=(isAPI == 1?"API ":"Menu") %></td>
									<td>Menu Name Bangla</td>
									<%
									if(isAPI == 0) {
									%>
									<td>Parent Menu</td>
									<%} %>
									
									
									<td>HyperLink</td>
									
									<td>Request</td>
									
									<%if(isAPI == 0) {%>
									<td>Selected Menu</td>		
									<%} %>			
									<td>isVisible</td>
									<td>isAPI</td>
									<td>Delete</td>
									
									<td>Order</td>			
									
									<%if(isAPI == 0) {%>
									<td>Icon</td>			
									<%}%>
									<td>Constant Name</td>
								</tr>
							</thead>
							<tbody>						
							
							
				
							<%
							
							for(MenuDTO menuDTO : allMenuList){
								if(menuDTO.isAPI != (isAPI==1?true:false)){ 
									continue;
								}
								
								
								menuDTO.menuName = menuDTO.menuName.replace(">", ">"+"<i class=\""+menuDTO.icon+"\"></i> ");
								
								MenuDTO menuDTOFromRepo = MenuRepository.getMenuDTOByMenuID(menuDTO.menuID);
								
								++orderIndex;
								String menuName = MenuRepository.getMenuDTOByMenuID(menuDTO.menuID).menuName;
								
							%>
							
									<tr>
										<td><%=menuDTO.menuID %></td>
										<td class="menu-style">   <%=menuDTO.menuName.replace(menuDTOFromRepo.menuName, "")%><input type="hidden" name="menuID" value="<%=menuDTO.menuID%>"/>
										
										 <input  type="text" size="<%=menuName.length()%>" name="menuName" class="black-text" value="<%= menuName%>" required="required">
										</td>
										<td>
											<input type="text" size="<%=menuDTO.menuNameBangla.length()%>" name="menuNameBangla" class="black-text" value="<%= menuDTO.menuNameBangla%>" required="required">
										</td>
										<td width = "2%" <%=(isAPI==1? "class=\"invisible\" ":"" )%>       >
											<select name="parentMenuID" class="black-text">
												<option value="-1">No Parent</option>
												
												<%
												
													for(MenuDTO dropDownMenu: allMenuList){
														
														if(dropDownMenu.isAPI == true){
															continue;
														}

														if( menuUtil.isAncestorByRootAndNode(menuDTO, dropDownMenu)){
															continue;
														}
														
														%>
														
															<option  <%=( dropDownMenu.menuID==menuDTO.parentMenuID?"Selected":"" )%>     value="<%=dropDownMenu.menuID%>"><%=dropDownMenu.menuName %></option>		
														
														<% 
													}
												
												%>
												
												
											</select>
											
										
										</td>
										
										
										
										<td  ><input size="<%= (""+menuDTO.hyperLink).length()%>" type="text" class="black-text" name="hyperLink" value="<%=menuDTO.hyperLink%>"></td>
										<td  >
										
											<select name= "requestMethodType" class="black-text">
											
												<option  <%=(menuDTO.requestMethodType == 1?"Selected":"") %> value = "1" >GET</option>
												<option <%=(menuDTO.requestMethodType == 2?"Selected":"") %> value = 2>POST</option>
											</select>
										
										</td>
										
										
										<td  <%=(isAPI==1? "class=\"invisible\" ":"" ) %>   >
											<select name = "selectedMenuID" class="black-text">
												<option value="-1">Select Menu</option>
												
												<%
												
													for(MenuDTO dropDownMenu: allMenuList){
														
														if(dropDownMenu.isAPI == true){
															continue;
														}
														
														if(dropDownMenu.isVisible == false){
															continue;
														}
														
														
														%>
														
															<option  <%=( dropDownMenu.menuID==menuDTO.selectedMenuID?"Selected":"" )%>     value="<%=dropDownMenu.menuID %>"><%=dropDownMenu.menuName %></option>		
														
														<% 
													}
												
												%>
												
												
											</select>
											
										
										</td>
										


										<td>   
											<input <%= menuDTO.isVisible?"checked":""%> type = "checkbox" name="isVisible" value ="<%=menuDTO.menuID %>"  > 
										</td>
										
										<td>   
											<input <%= menuDTO.isAPI?"checked":""%> type = "checkbox" name="isAPI" value ="<%=menuDTO.menuID %>"  > 
										</td>
										<td>   
											<input  type = "checkbox" name="isDeleted" value ="<%=menuDTO.menuID %>" > 
										</td>
										<td width="3%" >
											 <input class="form-control" type="text" name="orderIndex" value="<%=orderIndex%>">
										</td>
										
										
										<td  <%=(isAPI==1? "class=\"invisible\" ":"" ) %>   >
											<input type="text" name="icon" class="black-text" value="<%=menuDTO.icon%>">												
										</td>
										

										<td>
											<input type="text" name="menuConstantName" class="black-text" value="<%=menuDTO.constant%>" required="required">												
										</td>

									</tr>
							
						
							<%}%>					
						</tbody>
					</table>
				
				
				<%} %>
			</div>
			<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.MENU_SETTINGS_UPDATE)){ %>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-offset-4 col-md-8">
						<input type="submit" class="btn btn-success" value="Update">
					</div>
				</div>

			</div>
			<% }%>
			</div>
		</form>
	</div>

</div>

<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.MENU_ADD)){ %>

<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i>Add New Menu
		</div>
	</div>
	<div class="portlet-body portlet-body-btcl form">
		<form role="form" action="MenuConfigurationServlet?actionType=add"
			class="form-horizontal" method="post">


			<input type="hidden" name="menuID" value="0" /><br>

			<div class="form-group ">
				<label class="col-sm-3 control-label">Menu Name</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="menuName"
						name="menuName" value="" required="required">
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Menu Name Bangla</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="menuNameBangla"
						name="menuNameBangla" value="" required="required">
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Parent Menu</label>
				<div class="col-sm-6">
					<select name="parentMenuID" class="black-text">
						<option value="-1">No Parent</option>

						<%
													
														for(MenuDTO dropDownMenu: allMenuList){
															
															if(dropDownMenu.isAPI == true){
																continue;
															}
	
															
															%>

						<option value="<%=dropDownMenu.menuID%>"><%=dropDownMenu.menuName %></option>

						<% 
														}
													
													%>


					</select>
				</div>
			</div>

			<div class="form-group ">
				<label class="col-sm-3 control-label">Hyperlink</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="hyperLink"
						name="hyperLink" value="">
				</div>
			</div>



			<div class="form-group ">
				<label class="col-sm-3 control-label">Request Method Type</label>
				<div class="col-sm-6">
					<select name="requestMethodType" class="black-text">
						<option value="1">GET</option>
						<option value=2>POST</option>
					</select>
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Selected Menu</label>

				<div class="col-sm-6">

					<select name="selectedMenuID" class="black-text">
						<option value="-1">Select Menu</option>

						<%
													
														for(MenuDTO dropDownMenu: allMenuList){
															
															if(dropDownMenu.isAPI == true){
																continue;
															}
															if(dropDownMenu.isVisible == false){
																continue;
															}
															%>

						<option value="<%=dropDownMenu.menuID %>"><%=dropDownMenu.menuName %></option>

						<% 
														}
													
													%>


					</select>
				</div>

			</div>



			<div class="form-group ">
				<label class="col-sm-3 control-label">Visibility</label>
				<div class="col-sm-6">
					<input type="checkbox" class="form-control" id="isVisible"
						name="isVisible">
				</div>
			</div>

			<div class="form-group ">
				<label class="col-sm-3 control-label">Is API</label>
				<div class="col-sm-6">
					<input type="checkbox" class="form-control" id="isAPI" name="isAPI">
				</div>
			</div>


			<input type="hidden" name="isDeleted" value="0"> <br>




			<div class="form-group ">
				<label class="col-sm-3 control-label">Order Index</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="orderIndex"
						name="orderIndex" value="0">
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Icon</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="icon"
						name="icon" value="">
				</div>
			</div>


			<div class="form-group ">
				<label class="col-sm-3 control-label">Constant Name</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="menuConstantName" value="" required="required">
				</div>
			</div>



			<div class="form-actions text-center">
				<button class="btn btn-success" type="submit">Submit</button>
			</div>


		</form>

	</div>
</div>

<%} %>








