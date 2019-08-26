<%@page import="permission.MenuConstants"%>
<%@page import="role.PermissionRepository"%>
<%@page import="user.UserRepository"%>
<%@page import="user.UserDTO"%>
<%@page import="login.LoginDTO"%>
<%@page import="util.ActionTypeConstant"%>
<%@page import="util.JSPConstant"%>
<%@page import="util.ServletConstant"%>
<%@page import="java.util.List"%>
<%@page import="permission.MenuDTO"%>
<%@page import="permission.MenuRepository"%>
<%@page import="permission.MenuUtil"%>
<%@page import="language.LM"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageDTO"%>
<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%!
MenuUtil menuUtil = new MenuUtil();
%>
<%
String url = "LanguageServlet?actionType=search";
String servletName = "LanguageServlet";
String navigator = SessionConstants.NAV_LANGUAGE;
String pageName = "Language Search";
%>
	<jsp:include page="../includes/nav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
		<jsp:param name="pageName" value="<%=pageName%>" />
	</jsp:include>
<%
String context = "../../.."  + request.getContextPath() + "/";
String action = JSPConstant.LANGUAGE_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.LANGUAGE_EDIT;
LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
UserDTO loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);

%>

<div class="portlet box">
	<div class="portlet-body form">
		<form action="<%=action%>" method="POST" id="tableForm">
		<input type="hidden" name="backLinkEnabled" value="<%=request.getParameter("backLinkEnabled")%>"/>
			<jsp:include page='../common/flushActionStatus.jsp' />
			<div class="table-responsive form-body">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Menu Name</th>
								<th>English Text</th>
								<th>Bangla Text</th>
								<th>ConstantPrefix</th>
								<th>Constant</th>
								<th>Delete</th>							
								
							</tr>
						</thead>
						<tbody>
							<%
									ArrayList data = (ArrayList) session.getAttribute(SessionConstants.VIEW_LANGUAGE);
									if (data != null) {
										int size = data.size();
										for (int i = 0; i < size; i++) {
											LanguageTextDTO row = (LanguageTextDTO) data.get(i);											
							%>
							<tr>
								<input type="hidden" name="ID" value="<%=row.ID%>"/>
								<input type="hidden" name="menuID" value="<%=row.menuID%>"/>
								<td><%=menuUtil.getAllAncestorMenus((int)row.menuID)%></td>
								<td>
									<input type="text" class="form-control" id="languageTextEnglish" name="<%=ServletConstant.LANGUAGE_TEXT_ENGLISH%>" value="<%=row.languageTextEnglish%>">								
								</td>
								<td>
									<input type="text" class="form-control" id="languageTextBangla" name="<%=ServletConstant.LANGUAGE_TEXT_BANGLA%>" value="<%=row.languageTextBangla%>">								
								</td>
								<td>
									<input type="text" class="form-control" id="languageConstantPrefix" name="<%=ServletConstant.LANGUAGE_CONSTANT_PREFIX%>" value="<%=row.languageConstantPrefix%>">								
								</td>
								<td>
									<input type="text" class="form-control" id="languageConstant" name="<%=ServletConstant.LANGUAGE_CONSTANT%>" value="<%=row.languageConstant%>">								
								</td>
								<td><input type="checkbox" name="<%=ServletConstant.DELETE_ID%>" value="<%=row.ID%>" /></td>																								
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
				<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(loginUserDTO.roleID, MenuConstants.LANGUAGE_TEXT_EDIT)){ %>
				<div class="form-actions text-center">
					<a class="btn btn-danger" href="<%=request.getHeader("referer")%>">Cancel</a>
					<button class="btn btn-success" type="submit">Submit</button>
				</div>
				<%} %>
		</form>
	</div>
</div>

<%
	String menuID = (String) request.getAttribute(ServletConstant.LANGUAGE_TEXT_MENU_SELECTED);
	if (menuID == null)menuID = "";	
	else request.removeAttribute(ServletConstant.LANGUAGE_TEXT_MENU_SELECTED);
	
	String constantPrefiex = (String)request.getAttribute(ServletConstant.LANGUAGE_CONSTANT_PREFIX_SELECTED);
	if(constantPrefiex == null) constantPrefiex = "";
	else request.removeAttribute(ServletConstant.LANGUAGE_CONSTANT_PREFIX_SELECTED);
			
	List<MenuDTO> allMenuList = menuUtil.getAlignMenuListAllSeparated();
%>


<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(loginUserDTO.roleID, MenuConstants.LANGUAGE_TEXT_ADD)){ %>
<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-search-plus"></i>New Text</div>
	</div>
	
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="<%=servletName%>?actionType=add" method="POST" class="form-horizontal">
		<div class="form-body">
			<div class="form-group ">
				<label class="col-sm-3 control-label">Menu</label>
				<div class="col-sm-6">
				<select class="form-control select2" size="1" name="menuID" >
					<option  value="" <%if (menuID.equals("") ){%> selected='selected' <%}%>>All</option>
					<%for(MenuDTO menuDTO : allMenuList){%>	 	
					<option value="<%=menuDTO.menuID%>" <%if (menuID.equals(""+menuDTO.menuID) ){%> selected <%}%>><%=menuDTO.menuName%></option>
					<%}%>
				</select>
				</div>
			</div>
			
			<div class="form-group ">
				<label class="col-sm-3 control-label">English Text</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="languageTextEnglish" name="languageTextEnglish" value="">
				</div>
			</div>
			
			<div class="form-group ">
				<label class="col-sm-3 control-label">Bangla Text</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="languageTextBangla" name="languageTextBangla" value="">
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-3 control-label">Constant Prefix</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="languageConstantPrefix" name="languageConstantPrefix" value="<%=constantPrefiex%>">
				</div>
			</div>			
			<div class="form-group ">
				<label class="col-sm-3 control-label">Constant</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="languageConstant" name="languageConstant" value="">
				</div>
			</div>
						
				</div>
			<div class="form-actions text-center">
				<button class="btn btn-success" type="submit">Submit</button>
			</div>		
		</form>
	</div>
</div>
<%} %>
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
	    currentForm.submit();
	});
})

</script>


