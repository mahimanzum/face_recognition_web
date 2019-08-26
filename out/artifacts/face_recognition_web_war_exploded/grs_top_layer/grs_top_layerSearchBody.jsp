
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
String url = "Grs_top_layerServlet?actionType=search";
String navigator = SessionConstants.NAV_GRS_TOP_LAYER;
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
	<jsp:include page="./grs_top_layerNav.jsp" flush="true">
		<jsp:param name="url" value="<%=url%>" />
		<jsp:param name="navigator" value="<%=navigator%>" />
		<jsp:param name="pageName" value="<%=LM.getText(LC.GRS_TOP_LAYER_SEARCH_GRS_TOP_LAYER_SEARCH_FORMNAME, loginDTO)%>" />
	</jsp:include>


<div class="portlet box">
	<div class="portlet-body">
		<form action="Grs_top_layerServlet?actionType=delete" method="POST" id="tableForm" enctype = "multipart/form-data">
			<jsp:include page="grs_top_layerSearchForm.jsp" flush="true">
				<jsp:param name="pageName" value="<%=LM.getText(LC.GRS_TOP_LAYER_SEARCH_GRS_TOP_LAYER_SEARCH_FORMNAME, loginDTO)%>" />
			</jsp:include>	
		</form>
	</div>
</div>

<div class="portlet box portlet-btcl light">
	<div class="portlet-body">
		<div class="row text-center">
			<form action="<%=action%>" method="POST" id='searchform' class="form-inline">
				<nav aria-label="Page navigation" >
				  <ul class="pagination" style="margin: 0px;">
				   <li class="page-item">
				      <a class="page-link" href="<%=link%><%=concat%>id=first" aria-label="First"  title="Left">
				        <i class="fa fa-angle-double-left" aria-hidden="true"></i>
				        <span class="sr-only">First</span>
				      </a>
				    </li>
				    <li class="page-item">
				      <a class="page-link" href="<%=link%><%=concat%>id=previous" aria-label="Previous" title="Previous">
				         <i class="fa fa-angle-left" aria-hidden="true"></i>
				        <span class="sr-only">Previous</span>
				      </a>
				    </li>
				
				     <li class="page-item">
				      <a class="page-link" href="<%=link%><%=concat%>id=next" aria-label="Next" title="Next">
				         <i class="fa fa-angle-right" aria-hidden="true"></i>
				        <span class="sr-only">Next</span>
				      </a>
				    </li>
				    <li class="page-item">
				      <a class="page-link" href="<%=link%><%=concat%>id=last" aria-label="Last"  title="Last">
				        <i class="fa fa-angle-double-right" aria-hidden="true"></i>
				        <span class="sr-only">Last</span>
				      </a>
				    </li>
				    <li>
				    	&nbsp;&nbsp;<i class="hidden-xs"><%=LM.getText(LC.GLOBAL_PAGE, loginDTO) %> </i>
				    	<input type="text" class="custom-form-control " name="pageno" id="pageno" value='<%=pageno%>' size="15"> <i class="hidden-xs"><%=LM.getText(LC.GLOBAL_OF, loginDTO) %></i>
						<i id='totalpage'>
							<%=rn.getTotalPages()%>
						</i>
						<input type="hidden" name="go" value="yes" />
						<input type="hidden" name="mode" value="search" />
						<input type="submit" class="btn btn-circle  btn-sm green-haze btn-outline sbold uppercase" value="<%=LM.getText(LC.GLOBAL_GO, loginDTO)%>"/>
				    </li>
				  </ul>
				</nav>
			</form>
		</div>
	</div>
</div><script src="<%=context%>/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
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


