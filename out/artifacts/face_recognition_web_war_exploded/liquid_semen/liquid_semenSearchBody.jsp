
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@ page import="util.RecordNavigator"%>

<%@ page language="java"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page errorPage="failure.jsp"%> --%>
<%
String url = "Liquid_semenServlet?actionType=search";
String navigator = SessionConstants.NAV_LIQUID_SEMEN;
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

String pageno = "";

RecordNavigator rn = (RecordNavigator) session.getAttribute(navigator);
pageno = (rn == null) ? "1" : "" + rn.getCurrentPageNo();

System.out.println("rn " + rn);

String action = url;
String context = "../../.." + request.getContextPath() + "/";
String link = context + url;
String concat = "?";
if (url.contains("?")) {
	concat = "&";
}
%>
	<jsp:include page="./liquid_semenNav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
		<jsp:param name="pageName" value="<%=LM.getText(LC.LIQUID_SEMEN_SEARCH_LIQUID_SEMEN_SEARCH_FORMNAME, loginDTO)%>" />
	</jsp:include>


<div class="portlet box">
	<div class="portlet-body">
		<form action="Liquid_semenServlet?actionType=delete" method="POST" id="tableForm" enctype = "multipart/form-data">
			<jsp:include page="liquid_semenSearchForm.jsp" flush="true">
				<jsp:param name="pageName" value="<%=LM.getText(LC.LIQUID_SEMEN_SEARCH_LIQUID_SEMEN_SEARCH_FORMNAME, loginDTO)%>" />
			</jsp:include>	
		</form>
	</div>
</div>

<%@include file="../common/pagination_with_go.jsp"%>
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


