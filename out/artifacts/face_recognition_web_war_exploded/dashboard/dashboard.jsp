<%@page import="sessionmanager.SessionConstants"%>
<%@page import="user.UserTypeDTO"%>
<%@page import="user.UserDTO"%>
<%@page import="user.UserTypeRepository"%>
<%@page import="user.UserRepository"%>
<%@page import="login.LoginDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%try{ %>
	<%
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	System.out.println("loginDTO " + loginDTO);
	UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(loginDTO.userID);
	//UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(loginDTO.userID);
	System.out.println("userDTO " + userDTO);
	UserTypeDTO userTypeDTO = UserTypeRepository.getInstance().getUserTypeByUserTypeID(userDTO.userType);
	System.out.println("userTypeDTO " + userTypeDTO);
	request.setAttribute("dashboard", "../dashboard/" + userTypeDTO.dashboard);
	%>	
	<%-- <jsp:include page="../dashboard/<%=userTypeDTO.dashboard%>" flush="true">
	<jsp:param name="title" value="Dashboard"/>
	</jsp:include> --%>
	<jsp:include page="${dashboard}"></jsp:include>
	<%}catch(Exception e){
		e.printStackTrace();
	}
	%>
	