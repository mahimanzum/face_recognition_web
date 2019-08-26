<%@page import="config.GlobalConfigConstants"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="user.UserRepository"%>
<%@page import="util.CommonConstant"%>
<%@page import="permission.MenuConstants"%>
<%@page import="role.PermissionRepository"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="util.ServletConstant"%>
<%@page import="util.ActionTypeConstant"%>
<%@page import="util.JSPConstant"%>
<%@page import="language.LM"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>
<%@page import="language.LC"%>
<%@page import="role.RoleDTO"%>
<%@page import="user.UserTypeDTO"%>
<%@page import="user.UserTypeRepository"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserDTO"%>
<%@page pageEncoding="UTF-8" %>
<%@page import="org.apache.log4j.Logger"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="pb.*"%>



<%
String context = "../../.."  + request.getContextPath() + "/";
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID); 
UserDTO userDTO = (UserDTO)request.getAttribute(ServletConstant.USER_DTO);
ArrayList<RoleDTO> roleList = (ArrayList<RoleDTO>)request.getAttribute(ServletConstant.ROLE_LIST); 
String action = JSPConstant.USER_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=";
String formTitle = "";
String password = "";

if(userDTO == null)
{
	formTitle = LM.getText(LC.USER_ADD_USER_ADD, loginDTO);
	action = action + ActionTypeConstant.USER_ADD;
	userDTO = (UserDTO)request.getSession().getAttribute(ServletConstant.USER_DTO);
	if(userDTO == null)userDTO = new UserDTO();
	else request.getSession().removeAttribute(ServletConstant.USER_DTO);	
	
}
else
{
	formTitle = LM.getText(LC.USER_ADD_USER_EDIT, loginDTO);
	action = action + ActionTypeConstant.USER_EDIT;
	password = ServletConstant.DEFAULT_PASSWORD;
}
System.out.println("actionType = " + request.getParameter("actionType"));
String actionName;
if (request.getParameter("actionType").equalsIgnoreCase("getAddPage"))
{
	actionName = "add";
}
else
{
	actionName = "edit";
}
String fieldError = "";
%>
<%
String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);
System.out.println("Language = " + Language);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
date.setYear(date.getYear() + 2);
String futuredatestr = dateFormat.format(date);
%>

<style>
	.ul-box{
		width:65%;
		margin-left: 17%;
		margin-bottom: 1%;
		background-color: #eee;
		padding-top: 40px;
		padding-bottom: 30px;
	}
</style>

<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-gift"></i><%=formTitle%>
		</div>

	</div>
	<div class="portlet-body form">
		<form class="form-horizontal" action="<%=action%>" method="POST" onsubmit="return validate();">
			<div class="form-body">
				
				<input type="hidden" name="ID" value="<%=userDTO.ID%>"/> 

				<jsp:include page='../common/flushActionStatus.jsp' />
				<div class="form-group ">
					<label class="col-sm-3 control-label">					
					<%=LM.getText(LC.USER_ADD_USER_NAME, loginDTO)%><span class="required"> * </span>
					</label>

					<div class="col-sm-6 ">
						<input type="text" class="form-control"  name="userName" value='<%=userDTO.userName%>' required="required"  pattern="^[A-Za-z0-9]{5,}" title="Username must contain at least 5 letters"/>
					</div>
										
					<%
					fieldError = (String)request.getSession().getAttribute(ServletConstant.USERNAME); 
					if(fieldError != null){
						request.getSession().removeAttribute(ServletConstant.USERNAME);
					%>
					<label><span class="label label-danger" id="usernameError"><%=fieldError%></span></label>					
					<%}%>
					
					<label id='availability' class="user-name-availability"  ></label>
					
				</div>				
				
				<div class="form-group">

					<label for="password" class="col-sm-3 control-label">						
						<%=LM.getText(LC.USER_ADD_PASSWORD, loginDTO)%><span class="required"> * </span>
					</label>

					<div class="col-sm-6">
						<input type="password" class="form-control" name="password" id="password" value="<%=password%>"/>
						<div  class="pwstrength_viewport_progress"></div>
					</div>
					<%
					fieldError = (String)request.getSession().getAttribute(ServletConstant.PASSWORD); 
					if(fieldError != null){
						request.getSession().removeAttribute(ServletConstant.PASSWORD);
					%>					
					<span class="label label-danger"><%=fieldError%></span>
					<%}%>
				</div>
				
				<div class="form-group ">
					<label for="confirmPassowrd" class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_CONFIRM_PASSWORD, loginDTO)%><span class="required"> * </span>
					</label>

					<div class="col-sm-6">
						<input type="password" class="form-control" name="repassword" value="<%=password%>"/>
					</div>
				</div>
				
				<div class="form-group ">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_USER_TYPE, loginDTO) %>
					</label>

					<div class="col-sm-6 ">
							<select class="form-control pull-right" name="userType" >
								<%
								for(UserTypeDTO userTypeDTO: UserTypeRepository.getInstance().getAllUserType())
								{
								%>
									<option value="<%=userTypeDTO.ID%>" <%if (userTypeDTO.ID == userDTO.userType){%> selected <%}%>><%=(loginUserDTO.languageID == CommonConstant.Language_ID_Bangla) ? userTypeDTO.name_bn : userTypeDTO.name_en%></option>
								<%}
								%>																
								
							</select>
					</div>
				</div>

				<div class="form-group ">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_ROLE, loginDTO) %>
					</label>

					<div class="col-sm-6 ">
							<select class="form-control pull-right" name="roleName" >
								<%
								for(RoleDTO roleDTO: roleList)
								{%>
									<option value="<%=roleDTO.ID%>" <%if (roleDTO.ID == userDTO.roleID){%> selected <%}%>><%=roleDTO.roleName%></option>
								<%}
								%>																
								
							</select>
					</div>
				</div>

				<div class="form-group ">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_LANGUAGE, loginDTO) %>
					</label>

					<div class="col-sm-6 ">
							<select class="form-control pull-right" name="languageID" >
							<option value="1" <%if (1== userDTO.languageID){%> selected <%}%>>English</option>
							<option value="2" <%if (2== userDTO.languageID){%> selected <%}%>>বাংলা</option>
								
							</select>
					</div>
				</div>					

				<div class="form-group ">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_MAIL_ADDRESS, loginDTO) %><span class="required"> * </span>
					</label>

					<div class="col-sm-6">
						<input type="email" class="form-control" styleId="mailAddress" name="mailAddress" value='<%=userDTO.mailAddress%>' required="required"  title="Please enter a correct mail address"/>
					</div>
					<%
					fieldError = (String)request.getSession().getAttribute(ServletConstant.MAIL_ADDRESS); 
					if(fieldError != null){
						request.getSession().removeAttribute(ServletConstant.MAIL_ADDRESS);
					%>					
					<span class="label label-danger"><%=fieldError%></span>
					<%}%>					
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_LOGIN_IP, loginDTO) %>
					</label>
					<div class="col-sm-6">
						<input type=text class="form-control" id="LoginIP">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input type="button" onClick="addLoginIP();" value="<%=LM.getText(LC.USER_ADD_ADD, loginDTO) %>" name="cmdAdd" class="btn btn-success" > 
						<input type="button" onClick="removeLoginIP();" value="<%=LM.getText(LC.USER_ADD_REMOVE, loginDTO) %>" name="cmdAdd" class="btn btn-danger">
					</div>
				</div>

				<div class="form-group ">
					<label class="col-sm-3 control-label">
					<%=LM.getText(LC.USER_ADD_LOGIN_IPS, loginDTO) %>
					</label>
					<div class="col-sm-6">
						<select name="loginIPs" class="ip-box" multiple="true">
							<%for(String ip: userDTO.loginIPs){ 
								if("".equals(ip)){
									continue;
								}
							%>
								<option value="<%=ip %>"><%=ip %></option>
							<%} %>
						</select>
					</div>
				</div>
								
								
				<div class="form-group">
					<div class="portlet light">
						<div class="col-sm-7 portlet-title">
							<div class="caption">
								 <span class="caption-subject  font-grey-gallery "><%=LM.getText(LC.USER_ADD_ADDITIONAL_INFORMATION, loginDTO)%></span>
							</div>
						</div>
						<div class="portlet-body form">
							<div class="form-body">
								<div class="form-group ">
								<br>
									<label class="col-sm-3 control-label">
									<%=LM.getText(LC.USER_ADD_FULL_NAME, loginDTO) %>
									</label>

									<div class="col-sm-6">
										<input type="text" class="form-control" id="fullName"
											name="fullName" value="<%=userDTO.fullName %>" />
									</div>
								</div>
								
								
								
								<div class="form-group ">
									<label class="col-sm-3 control-label">
									<%=LM.getText(LC.USER_ADD_PHONE_NO, loginDTO) %>
									</label>

									<div class="col-sm-6">
										<input type="text" class="form-control" id="phoneNo"
											name="phoneNo" value="<%=userDTO.phoneNo %>"
											>
									</div>
								</div>
								
								<label class="col-sm-3 control-label">
	Centre
</label>
<div class="form-group ">					
	<div class="col-sm-6 " id = 'toCentreType_div'>	
		<select class='form-control'  name='CentreType' id = 'CentreType_select' >
<%
if(actionName.equals("edit"))
{
			Options = CommonDAO.getOptions(Language, "select", "centre", "toCentreType_select", "form-control", "toCentreType", userDTO.centreType + "");
}
else
{			
			Options = CommonDAO.getOptions(Language, "select", "centre", "toCentreType_select", "form-control", "toCentreType" );			
}
out.print(Options);
%>
		</select>
		
	</div>
</div>		
								
								
								<div class="form-group ">
									<label class="col-sm-3 control-label">
									<%=LM.getText(LC.USER_ADD_OTP_RECEIVE_VIA, loginDTO) %>
									</label>

									<%
										boolean otpViaSMSEnabled = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_VIA_SMS).value > 0;
										boolean otpViaEmailEnabled = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_VIA_EMAIL).value > 0;
										boolean otpViaPushNotification = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_VIA_PUSH_NOTIFICATION).value > 0;
									%>
									<div class="col-sm-6">
									<%if(otpViaSMSEnabled){%>																			
										<input type="checkbox" class="form-control" id="otpSMS" <%if(userDTO.otpSMS){%>checked<%}%>	name="otpSMS" value="<%=userDTO.otpSMS %>"><label><%=LM.getText(LC.USER_ADD_SMS, loginDTO)%></label>
									<%}%>
									<%if(otpViaEmailEnabled){%>
										<input type="checkbox" class="form-control" id="otpEmail" <%if(userDTO.otpEmail){%>checked<%}%>	name="otpEmail" value="<%=userDTO.otpEmail %>"><label><%=LM.getText(LC.USER_ADD_EMAIL, loginDTO)%></label>
									<%}%>
									<%if(otpViaPushNotification){%>
										<input type="checkbox" class="form-control" id="otpPushNotification" <%if(userDTO.otpPushNotification){%>checked<%}%> name="otpPushNotification" value="<%=userDTO.otpPushNotification %>"><label><%=LM.getText(LC.USER_ADD_PUSH_NOTIFICATION, loginDTO)%></label>
									<%} %>
									</div>
								</div>	
							</div>
						</div>
					</div>
				</div>								

							
			</div>
		<%if(PermissionRepository.checkPermissionByRoleIDAndMenuID(loginUserDTO.roleID, MenuConstants.USER_ADD)) {%>
			<div class="form-actions text-center">
				<a class="btn btn-danger" href="<%=request.getHeader("referer")%>"><%=LM.getText(LC.USER_ADD_CANCEL, loginDTO)%></a>
				<button class="btn btn-success" type="submit"><%=LM.getText(LC.USER_ADD_SUBMIT, loginDTO)%></button>
			</div>
		<%} %>	
		</form>

	</div>
</div>

<script  src="<%=context%>scripts/util.js"></script>
<script  src="<%=context%>users/user.js"></script>
<%-- <script src="<%=context%>assets/global/plugins/bootstrap-pwstrength/pwstrength-bootstrap.min.js" type="text/javascript"></script> --%>
<script>
$(document).ready(function() {
	$("input[name='userName']").keyup(function(){
		if($(this).val().length>0){
			data = {};
			data.userName = $(this).val();
			callAjax(context + "UtilServlet?actionType=checkUserAvailable&username="+data.userName, data, (data)=>{
			$('#usernameError').hide();
			if(data == true)
			{
				$('#availability').html('available');
				$('#availability').css('color', 'green');
			}
			else if(data == false)
			{
				$('#availability').html('unavailable');
				$('#availability').css('color', 'red');	
			}
			else
			{
				toastr.error("Error");
			}				
			},  "POST")
		}else {
			$('#availability').html('');
		}
	});
	
	"use strict";
	var options = {};
	options.ui = {
	   container: "#pwd-container",
	   showVerdictsInsideProgressBar: true,
	   showStatus: true,
	   viewports: {
	       progress: ".pwstrength_viewport_progress"
	   },
	   progressBarExtraCssClasses: "progress-bar-striped active",
	   showPopover: false,
	showErrors: true,
	showProgressBar: true
	};
	options.rules = {
	activated: {
	   wordTwoCharacterClasses: true,
	   wordRepetitions: true
	}
	};
	options.common = {
	};
	$('#password').pwstrength(options);

})
</script>


