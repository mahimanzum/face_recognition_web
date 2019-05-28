<%@page import="common.CommonActionStatusDTO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%
Logger logger=Logger.getLogger(getClass());
String failureMsg = null;

try{
	failureMsg=(String) session.getAttribute("failureMsg");
}catch(Exception ex){
	
}
session.removeAttribute("failureMsg");
String successMsg = null;
try{
	successMsg = (String) session.getAttribute("successMsg");
}catch(Exception ex){
	
}
session.removeAttribute("successMsg");
CommonActionStatusDTO dto=CommonActionStatusDTO.getCommonActionDTO(request, response);
CommonActionStatusDTO wDTO=CommonActionStatusDTO.getWarningActionDTO(request, response);

if(dto!=null){ %>
	<%logger.debug(dto); %>
	<div class="alert alert-danger alert-dismissable">
    		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
   		 	<%=dto.getMessage() %>
   	</div>
<% }%>
<%if(failureMsg!=null){ %>
	<div class="alert alert-danger alert-dismissable">
   		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
   		 <%=StringUtils.trimToEmpty(failureMsg) %>
   	</div>
<%} %>
<%if(successMsg!=null){ %>
	<div class="alert alert-success alert-dismissable">
   		<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
   		 <%=StringUtils.trimToEmpty(successMsg) %>
   	</div>
<%} %>

