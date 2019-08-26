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
<%@page import="config.GlobalConfigDTO"%>
<%@page import="java.util.*"%>


<%!private boolean isAnsestorByRootAndNode(MenuDTO root,MenuDTO node){
	if(node == null){
		return false;
	}
	if(node.menuID == root.menuID){
		return true;
	}
	if(node.parentMenuID == -1){
		return false;
	}
	MenuDTO parentMenu = MenuRepository.getMenuDTOByMenuID(node.parentMenuID);
	return isAnsestorByRootAndNode(root, parentMenu);
}


private List<MenuDTO> alignMenuNames(MenuDTO menuDTO,String prefix){

	List<MenuDTO> resultList = new ArrayList<MenuDTO>();
	resultList.add(menuDTO);
	
		menuDTO.menuName = prefix+menuDTO.menuName;
		menuDTO.menuNameBangla = prefix+menuDTO.menuNameBangla;
		prefix = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+prefix;

	
	
	if(menuDTO.getChildMenuList()!=null){
		for(MenuDTO childMenuDTO:menuDTO.getChildMenuList()){
			resultList.addAll(  alignMenuNames(childMenuDTO, prefix));
		}
	}
	
	
	return resultList;
	
}%>

<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
System.out.println("userDTO "+ userDTO);
List<MenuDTO> rootMenuList = new ArrayList<MenuDTO>(MenuRepository.getRootMenuList());

List<MenuDTO> allMenuList = new ArrayList<MenuDTO>();
for(MenuDTO menu: rootMenuList){
	allMenuList.addAll(  alignMenuNames(menu, menu.isVisible? "|--->":""));
}

RoleDTO roleDTO = (RoleDTO)request.getAttribute(ServletConstant.ROLE_DTO);
String action = JSPConstant.ROLE_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=";
String formTitle = "";
long roleID = 0;
if(roleDTO == null)
{
	formTitle = LM.getText(LC.ROLE_ADD_ROLL_ADD, loginDTO);
	action = action + ActionTypeConstant.ROLE_ADD;
	roleDTO = (RoleDTO)request.getSession().getAttribute(ServletConstant.ROLE_DTO);
	if(roleDTO == null)roleDTO = new RoleDTO();
	else request.getSession().removeAttribute(ServletConstant.ROLE_DTO);
		
}
else
{
	formTitle = LM.getText(LC.ROLE_ADD_ROLE_EDIT, loginDTO);
	action = action + ActionTypeConstant.ROLE_EDIT;
	roleID = roleDTO.ID;
}
String fieldError = "";
%>



<div class="portlet box portlet-btcl ">
	<div class="portlet-title portlet-title-btcl">
		<div class="caption">
			<i class="fa fa-cogs"></i><%=formTitle %>
		</div>
	</div>
	
	
	
	
	<div class="portlet-body portlet-body-btcl form">
	
	
	
		<form role="form" action="<%=action%>" class="form-horizontal" method="post">
			<div class="form-body">
			<jsp:include page='../common/flushActionStatus.jsp' />
			
			<div class="form-group">
				<label class="col-md-4 control-label">
				<%=LM.getText(LC.ROLE_ADD_ROLE_NAME, loginDTO) %>
				<span class="required"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="roleName" class="form-control" value="<%=roleDTO.roleName%>"/>
					<input type="hidden" name="parentRoleID"/>
					<input type="hidden" name="ID" value="<%=request.getParameter("ID")%>"/>
					
				</div>
				<%
				fieldError = (String)request.getSession().getAttribute(ServletConstant.ROLE_NAME); 
				if(fieldError != null){
					request.getSession().removeAttribute(ServletConstant.ROLE_NAME);
				%>
				<label><span class="label label-danger" id="rolenameError"><%=fieldError%></span></label>					
				<%}%>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label">
				<%=LM.getText(LC.ROLE_ADD_DESCRIPTION, loginDTO) %>
				</label>				
				<div class="col-md-4">
					<input type="text" name="description" class="form-control" value="<%=roleDTO.description%>"/>
				</div>
			</div>
	
			
			
				<div class="table-responsive">
					<input type="hidden" name="roleID" value="<%=roleID%>"/>
					
					<%
											int orderIndex = 0;
													
													for(int isAPI = 0;isAPI<=1;isAPI++){
										%>
							
					
					
						
						
						
						
						<table id="tableData" class="table table-bordered table-striped">
						
							  <col width="80%">
							  <col width="20%">
							<thead>
								<tr >
									<td ><%=(isAPI == 0?LM.getText(LC.ROLE_ADD_MENU, loginDTO):LM.getText(LC.ROLE_ADD_API, loginDTO))%></td>
									<td><%=LM.getText(LC.ROLE_ADD_PERMISSION, loginDTO) %></td>
								</tr>
							</thead>
							<tbody>						
							
							
				
							<%for(MenuDTO menuDTO : allMenuList){
								if(menuDTO.isAPI != (isAPI==1?true:false)){ 
									continue;
								}
								
								
								String menuName = "";
								if(userDTO.languageID == CommonConstant.Language_ID_Bangla)
								{
									menuName = menuDTO.menuNameBangla.replace(">", ">"+"<i class=\""+menuDTO.icon+"\"></i> ");
								}
								else
								{
									menuName = menuDTO.menuName.replace(">", ">"+"<i class=\""+menuDTO.icon+"\"></i> ");
								}
								++orderIndex;
								%>
							
							
									<tr>
											
										<td>   
											<%=menuName%>
										</td>
								
										
										
										
										<td  ><input id= "<%=menuDTO.menuID%>" <%=(PermissionRepository.checkPermissionByRoleIDAndMenuID(roleID, menuDTO.menuID)?"checked":"")%> type="checkbox" name="menuID" value="<%=menuDTO.menuID%>"  
										onchange="checkMenuNode(this);"  
										
										subtree-size="<%=MenuRepository.getSubtreeMenuIDListByMenuID(menuDTO.menuID).size()%>"
										parent-id="<%=menuDTO.parentMenuID%>"
										></td>
										
									
									</tr>
							
						
							<%
																					}
																				%>
							
							
							
														
							
												
						</tbody>
					</table>
				
				
				
				
				
				
				
				
				
				
				<%
																																													}
																																												%>


					<table id="tableData" class="table table-bordered table-striped">
							<col width="80%">
							<col width="20%">
						<thead>
							<tr>
								<td><%=LM.getText(LC.ROLE_ADD_COLUMN, loginDTO) %></td>
								<td><%=LM.getText(LC.ROLE_ADD_PERMISSION, loginDTO) %></td>
							</tr>
						</thead>
						<tbody>
							<%
								List<ColumnDTO> columnList = ColumnRepository.getInstance().getAllColumnList();
													
													for (ColumnDTO columnDTO : columnList) {
														boolean hasColumnPermission = PermissionRepository.checkPermissionByRoleIDAndColumnID(roleID, columnDTO.columnID);
							%>
							<tr>

								<td>
									<%=columnDTO.columnName%>

								</td>




								<td><input id="<%=columnDTO.columnID%>"
									<%=(hasColumnPermission ? "checked" : "")%> type="checkbox"
									name="columnID" value="<%=columnDTO.columnID%>"></td>


							</tr>

							<%
								}
							%>


						</tbody>
					</table>



				</div>
			<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_EDIT)){ %>
			<div class="form-actions text-center">
				<a class="btn btn-danger" href="<%=request.getHeader("referer")%>"><%=LM.getText(LC.ROLE_ADD_CANCEL, loginDTO)%></a>
				<button class="btn btn-success" type="submit"><%=LM.getText(LC.ROLE_ADD_SUBMIT, loginDTO)%></button>
			</div>
			<%} %>
			</div>
		</form>
		

		
	</div>

	
</div>






<<script type="text/javascript">
function checkMenuNode(element){
	debugger;
	if(element.checked==true){
		checkUpToRoot(element);
	}
	var descendentIDs = $(element).attr("descendentids");
	var span = element.parentElement;
	var div = span.parentElement;
	var td = div.parentElement;
	var tr = td.parentElement;
	var subtreeSize = $(element).attr("subtree-size");
	for(var i=1;i<subtreeSize;i++){
		tr = tr.nextElementSibling;
		var checkbox = tr.children[1].children[0].children[0].children[0];
		var spn = checkbox.parentElement;
		if($(span).attr("class")=="checked"){
			$(spn).addClass("checked");
			checkbox.checked=true;
		}else{
			$(spn).removeClass("checked");
			checkbox.checked=false;
		}
	}
}

function checkUpToRoot(element){
	var parentID = $(element).attr("parent-id");
	if(parentID==-1){
		return;
	}
	var currentCheckBox = element;
	while($(currentCheckBox).attr("id")!=parentID){
		var span = currentCheckBox.parentElement;
		var div = span.parentElement;
		var td = div.parentElement;
		var tr = td.parentElement;
		var prevTr = tr.previousElementSibling;
		var prevTd = prevTr.children[1];
		var prevDiv = prevTd.children[0];
		var prevSpan = prevDiv.children[0];
		var prevCheckbox = prevSpan.children[0];
		
		currentCheckBox = prevCheckbox;
	}
	$(currentCheckBox.parentElement).addClass("checked");
	currentCheckBox.checked=true;
	checkUpToRoot(currentCheckBox);
}

</script>
