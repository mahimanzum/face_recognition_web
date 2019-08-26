
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page errorPage="failure.jsp"%> --%>
<%
String url = "SourceServlet?actionType=search";
String navigator = SessionConstants.NAV_SOURCE;
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
%>
	<jsp:include page="./sourceNav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
		<jsp:param name="pageName" value="<%=LM.getText(LC.SOURCE_SEARCH_SOURCE_SEARCH_FORMNAME, loginDTO)%>" />
	</jsp:include>
<%String context = "../../.."  + request.getContextPath() + "/";%>

<div class="portlet box">
	<div class="portlet-body">
		<form action="SourceServlet?actionType=delete" method="POST" id="tableForm" enctype = "multipart/form-data">
			<jsp:include page="sourceSearchForm.jsp" flush="true">
				<jsp:param name="pageName" value="<%=LM.getText(LC.SOURCE_SEARCH_SOURCE_SEARCH_FORMNAME, loginDTO)%>" />
			</jsp:include>	
		</form>
	</div>
</div>
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
	    if(!selected){
	    	 bootbox.alert("Select user to delete!", function() { });
	    }else{
	    	 bootbox.confirm("Are you sure you want to delete the record(s)?", function(result) {
	             if (result) {
	                 currentForm.submit();
	             }
	         });
	    }
	});
})

</script>


