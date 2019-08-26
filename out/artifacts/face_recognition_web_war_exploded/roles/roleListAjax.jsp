<%@page import="com.google.gson.Gson"%>
<%@page import="role.RoleDAO"%>
<%@page import="java.util.List"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="role.RoleDTO"%>
<%@page import="java.util.ArrayList"%>
<%



	Logger logger = Logger.getLogger("roleListAjax");
   	response.setContentType("application/json");
   	String name = request.getParameter("name");
   	login.LoginDTO loginDTO = (login.LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
   	List<RoleDTO> roles = new RoleDAO().getRolesByPartialMatch(name);
    String searchList = new Gson().toJson(roles);
    response.getWriter().write(searchList);
				
%>