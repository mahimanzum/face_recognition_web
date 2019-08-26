
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
String url = "Facial_recognizationServlet?actionType=search";
String navigator = SessionConstants.NAV_FACIAL_RECOGNIZATION;
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
	<jsp:include page="./facial_recognizationNav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
		<jsp:param name="pageName" value="<%=LM.getText(LC.FACIAL_RECOGNIZATION_SEARCH_FACIAL_RECOGNIZATION_SEARCH_FORMNAME, loginDTO)%>" />
	</jsp:include>


<div class="portlet box">
	<div class="portlet-body">
		<form action="Facial_recognizationServlet?actionType=delete" method="POST" id="tableForm" enctype = "multipart/form-data">
			<jsp:include page="facial_recognizationSearchForm.jsp" flush="true">
				<jsp:param name="pageName" value="<%=LM.getText(LC.FACIAL_RECOGNIZATION_SEARCH_FACIAL_RECOGNIZATION_SEARCH_FORMNAME, loginDTO)%>" />
			</jsp:include>	
		</form>
	</div>
</div>


<%@include file="../common/pagination_with_go.jsp"%>

<script src="<%=context%>/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#image_to_search").change(function(){
		var input = this;
		if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    
	    reader.onload = function(e) {
	      $('#searchImg').attr('src', e.target.result);
	      $('#searchImg').show();
	    }
	    
	    reader.readAsDataURL(input.files[0]);
	  }
	});
	
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
});
$(document).on("click",'#chkEdit',function(){
	debugger;
	$("#chkEdit").toggleClass("checked");
});
$(document).on("click",'input[type="checkbox"]',function(e){
	debugger;
	e.classList.toggle("checked");
});

</script>


