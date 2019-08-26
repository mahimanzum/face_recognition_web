<%@page import="util.CommonConstant"%>
<%@page import="user.UserRepository"%>
<%@page import="user.UserDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>
<%@page import="user.UserTypeRepository"%>
<%@page import="user.UserTypeDTO"%>
<%@ page language="java"%>
<%@ page import="java.util.*"%>
<%
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
	String userType = (String) session.getAttribute("userType");
	if (userType == null) {
		userType = "";
	}
%>
<div class="form-group">
	<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.USER_ADD_USER_TYPE, loginDTO) %></label>
	<div class="col-md-8 col-sm-8 col-xs-8">
			<select class="form-control select2" size="1" name="userType" >
			<option  value="" <%if (userType.equals("") ){%> selected='selected' <%}%>>All</option>			
			<%
			for(UserTypeDTO userTypeDTO: UserTypeRepository.getInstance().getAllUserType())
			{
			%>
				<option value="<%=userTypeDTO.ID%>" <%if (userType.equals(""+userTypeDTO.ID)){%> selected <%}%>><%=(userDTO.languageID == CommonConstant.Language_ID_Bangla) ? userTypeDTO.name_bn : userTypeDTO.name_en%></option>
			<%}
			%>			
		</select>
	</div>
</div>