<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%
	System.out.println("Inside nav.jsp");
	String url = request.getParameter("url");
	String navigator = request.getParameter("navigator");
	String pageName = request.getParameter("pageName");
	if(pageName == null)pageName = "Search";
	String pageno = "";
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	RecordNavigator rn = (RecordNavigator)session.getAttribute(navigator);
	pageno = ( rn == null ) ? "1" : "" + rn.getCurrentPageNo();

	System.out.println("rn " + rn);
	
	String action = url;
	String context = "../../.."  + request.getContextPath() + "/";
	String link = context + url;
	String concat = "?"; 
	if(url.contains("?"))
	{
		concat = "&";
	}
	String searchFieldInfo[][] = rn.getSearchFieldInfo();
	String totalPage = "1";
	if(rn != null)
		totalPage = rn.getTotalPages() + "";

%>
<style>
.kay-group > input{
	margin:5px;
}
</style>


<!-- search control -->
<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-search-plus"></i><%=pageName%></div>
		<div class="tools"><a class="collapse" href="javascript:;" data-original-title="" title=""> </a></div>
	</div>
	
	<div class="portlet-body form">
		<!-- BEGIN FORM-->
		<form action="<%=action%>" method="POST" class="form-horizontal">
			<div class="form-body">
			<%if(searchFieldInfo != null && searchFieldInfo.length > 0){%>
			<%for(int i = 0; i < searchFieldInfo.length;i++){%>
 				<%if(i % 2 == 0){%>
 				<div class="row">
 				<%}%>
					<div class="col-md-6 col-sm-6">
					<%if (searchFieldInfo[i][0].endsWith(".jsp")){%>
						<jsp:include page="<%=searchFieldInfo[i][0]%>" flush="true"/>
					<%}else{%>
						<div class="form-group">
						<%
						String fieldName =  searchFieldInfo[i][0];
						if(StringUtils.isNumeric(fieldName)) fieldName = LM.getText(Long.parseLong( searchFieldInfo[i][0]), loginDTO);
						%>
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=fieldName%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
							    <input type="text" class="form-control" id="" placeholder="" name="<%=searchFieldInfo[i][1]%>"
					    <%String value = (String)session.getAttribute(searchFieldInfo[i][1]);
					    //session.removeAttribute(searchFieldInfo[i][1]);
					    if( value != null){%>value = "<%=value%>"<%}%>>
							</div>
						</div>
					<%}%>
					</div>

				<%if(i % 2 == 1){%>
				</div>
				<%}%>

			<%}						
			}%>

				<%if(searchFieldInfo != null && searchFieldInfo.length % 2 == 0){%>
				<div class="row">
				<%}%>
				</div>
				
			<div class=clearfix></div>
			</div>
			<div class="form-actions fluid">
				<div class="row">
					<div class="col-xs-offset-0 col-sm-offset-2 col-md-offset-3 col-xs-12 col-md-9">
						<label for="" class="control-label col-xs-3 col-sm-3 col-md-3"><%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%></label>
						<div class="col-xs-3  col-sm-3 col-md-2">
							<input type="text" class="form-control" name="<%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO) %>" id="" placeholder="" value="<%=rn.getPageSize()%>">
						</div>
						<div class="col-xs-6  col-sm-5  col-md-4">					
						 	<input type="hidden" name="search" value="yes" />
<!-- 				          	<input type="reset" class="btn  btn-sm btn btn-circle  grey-mint btn-outline sbold uppercase" value="Reset" > -->
					        <input type="submit" class="btn  btn-danger" value="<%=LM.getText(LC.GLOBAL_SEARCH, loginDTO) %>" >
				        </div>
					</div>
				</div>
			</div>
		</form>
		<!-- END FORM-->
	</div>
</div>


<div class="portlet box portlet-btcl light">
	<div class="portlet-body">
		<div class="row text-center">
			<form action="<%=action%>" method="POST" class="form-inline">
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
				    	<input type="text" class="form-control " name="pageno" value='<%=pageno%>' size="15"> <i class="hidden-xs"><%=LM.getText(LC.GLOBAL_OF, loginDTO) %></i>
						<%=rn.getTotalPages()%>
						<input type="hidden" name="go" value="yes" />
						<input type="hidden" name="mode" value="search" />
						<input type="submit" class="btn btn-danger" value="<%=LM.getText(LC.GLOBAL_GO, loginDTO)%>"/>
				    </li>
				  </ul>
				</nav>
			</form>
		</div>
	</div>
</div>

