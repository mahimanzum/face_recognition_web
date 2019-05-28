<%@page import="language.LM"%>
<%@page import="util.CommonConstant"%>
<%@page import="user.*"%>
<%@page import="language.LC"%>
<%@page import="role.PermissionRepository"%>
<%@page import="role.MenuPermissionDTO"%>
<%@page import="role.PermissionRepository"%>
<%@page import="permission.MenuRepository"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="role.RoleDAO"%>
<%@page import="permission.MenuDTO"%>
<%@page import="java.util.*"%>
<%@page import="java.io.PrintWriter"%>

<%!private boolean hasChildMenuPermissionByMenuDTOAndRoleID(MenuDTO menuDTO,long roleID){
	
	
	boolean hasChildMenuPermission = false;
	
	if(menuDTO.getChildMenuList()!=null){
		for(MenuDTO childMenuDTO: menuDTO.getChildMenuList()){
			
			boolean hasMenuPermission = PermissionRepository.checkPermissionByRoleIDAndMenuID(roleID, childMenuDTO.menuID);
			if(hasMenuPermission){
				hasChildMenuPermission = true;
				break;
			}
		}
	}
	return hasChildMenuPermission;
}%>




<%!private boolean hasAnyVisibleChild(MenuDTO menuDTO){
	
	boolean hasAnyVisibleChild = false;
	
	for(MenuDTO childMenuDTO: menuDTO.getChildMenuList()){
		if(childMenuDTO.isVisible){
			hasAnyVisibleChild = true;
			break;
		}
	}
	
	return hasAnyVisibleChild;
}%>



<%!public void printMenu(StringBuilder out,  MenuDTO menuDTO,String context, LoginDTO menuLoginDTO){
		UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(menuLoginDTO.userID);
		
		long roleID = (userDTO==null?-1:userDTO.roleID);
		int languageID = (userDTO==null?CommonConstant.Language_ID_English:userDTO.languageID);
		
		boolean hasPermission = PermissionRepository.checkPermissionByRoleIDAndMenuID(roleID, menuDTO.menuID);
		
		if(!hasPermission || menuDTO.isVisible == false){
			return ;
		}
		
		
		if(menuDTO.getHyperLink()!=null && menuDTO.getHyperLink().trim().length()!=0){
			out.append("<li id=\""+menuDTO.getMenuID()+"\" class=\"wordwrap\">");
			out.append("<a href=\""+context+menuDTO.getHyperLink()+"\">");
		}else{
			out.append("<li class=\"treeview\" id=\""+menuDTO.getMenuID()+"\">");
			out.append("<a href=\"javascript:;\">");
		}
		out.append("<i class=\""+menuDTO.icon+"\"></i> ");
		out.append("<span class=\"title\">"+  (LM.getLanguageIDByUserDTO(userDTO) == CommonConstant.Language_ID_English? menuDTO.getMenuName():menuDTO.menuNameBangla)+"</span>");
		
		boolean hasAnyVisibleChild = hasAnyVisibleChild(menuDTO);
		
		if(hasAnyVisibleChild){

			boolean hasChildMenuPermission = hasChildMenuPermissionByMenuDTOAndRoleID(menuDTO, roleID);
			
			
			if(hasChildMenuPermission){
				
				if(menuDTO.getChildMenuList()!=null &&  !menuDTO.getChildMenuList().isEmpty()){
					out.append("<span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span>");
				}
			}
			
			out.append("</a>");
			
			
		
			if(hasChildMenuPermission){
			
				out.append("<ul class=\"treeview-menu\">");
				for(MenuDTO childMenuDTO: menuDTO.getChildMenuList()){
					
					printMenu(out,childMenuDTO, context, menuLoginDTO);
					
				}
				out.append("</ul>");
			}
		
		}else{
			out.append("</a>");
		}
		
		out.append("</li>");
	}%>
		

















				<%
				
					List<MenuDTO> rootMenuList = MenuRepository.getVisibleRootMenuList();
					LoginDTO menuLoginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
					
					StringBuilder htmlContent = new StringBuilder();
					
					for(MenuDTO menuDTO: rootMenuList){
					//	menuDTO.setChildMenuList(new ArrayList<>());
						printMenu(htmlContent,menuDTO, context, menuLoginDTO);
						
					}
					
					pageContext.getOut().write(htmlContent.toString());
					//response.getWriter().write(htmlContent);
				%>








<%-- 

			<li class="nav-item  " id="userAndRoleManagement">
				<a href="javascript:;" class="nav-link nav-toggle"> 
					<i class="fa fa-users"></i> 
					<span class="title">User</span> 
					<span class="arrow"></span>
				</a>
				
				
				<ul class="sub-menu">
					
					<li class="nav-item" id="userManagement" >
						<a href="javascript:;" class="nav-link nav-toggle"> 
							<i class="fa fa-user-plus"></i> 
							<span class="title">User Management</span> 
							<span class="arrow"></span>
						</a>
						
						<ul class="sub-menu">
							
							<li class="nav-item" id="userAdd">
								<a href="<%=context%>UserServlet?actionType=getAddPage" class="nav-link ">
									<i class="fa fa-plus"></i> 
									<span class="title">Add</span>
								</a>
							</li>

							
						
							<li class="nav-item" id="userSearch">
								<a href="<%=context%>UserServlet?actionType=search" class="nav-link ">
									<i class="fa fa-search"></i> 
									<span class="title">Search</span>
								</a>
							</li>
							
						</ul>
					</li>
					
				
					
					<li class="nav-item" id="roleManagement">
						<a href="javascript:;" class="nav-link nav-toggle"> 
							<i class="fa icon-key"></i>
							<span class="title">Role Management</span> 
							<span class="arrow"></span>
						</a>
						
						<ul class="sub-menu">
							
							<li class="nav-item  " id="roleAdd">
								<a href="<%=context%>RoleServlet?actionType=getAddPage" class="nav-link "> 
									<i class="fa fa-plus"></i>
									<span class="title"> Add</span>
								</a>
							</li>
							
							<li class="nav-item  " id="roleSearch">
								<a href="<%=context%>RoleServlet?actionType=search" class="nav-link ">
									<i class="fa fa-search"></i> 
									<span class="title"> Search</span> 
								</a>
							</li>
							
						</ul></li>
					</ul>
					
					

</li>











<li class="nav-item" id="roleManagement"><a href="javascript:;"
						class="nav-link nav-toggle"> <i class="fa icon-key"></i>
							<span class="title">Language Management</span> <span class="arrow"></span>
					</a>
						<ul class="sub-menu">
							
							<li class="nav-item  " id="languageAdd"><a href="<%=context%>LanguageServlet?actionType=getAddPage"
								class="nav-link "> <i class="fa fa-plus"></i><span
									class="title"> Add</span>
							</a></li>
							
							<li class="nav-item  " id="languageSearch"><a href="<%=context%>LanguageServlet?actionType=search"
								class="nav-link "><i class="fa fa-search"></i> <span
									class="title"> Search</span> </a></li>
							
						</ul></li> --%>
							

























	
					
				
				
				

					
								
			
		
		
		
		
		
		
		
		
				