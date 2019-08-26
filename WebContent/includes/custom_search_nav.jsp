<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="searchform.SearchForm"%>

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




<!-- search control -->
<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-search-plus"></i><%=pageName%></div>
		<div class="tools"><a class="expand" href="javascript:;" data-original-title="" title=""> </a></div>
		<%
			
			out.println("<input type='text' class='form-control' onKeyUp='anyfield_changed()' id='anyfield'  name='"+ searchFieldInfo[searchFieldInfo.length - 1][1] +"' ");
			String value = (String)session.getAttribute(searchFieldInfo[searchFieldInfo.length - 1][1]);
			
			if( value != null)
			{
				out.println("value = '" + value + "'");
			}
			
			out.println (" /><br />");
		%>
		
		
		
	</div>
	
	<div class="portlet-body form collapse">
		<!-- BEGIN FORM-->
		<div  class="form-horizontal">
			<div class="form-body">
			<%
			
			
			out.println(SearchForm.GetFormBody(Arrays.copyOfRange(searchFieldInfo, 0, searchFieldInfo.length - 1), loginDTO, session));
				
			%>
			</div>
			
				
			<div class=clearfix></div>
			
			<div class="form-actions fluid">
				<div class="row">
					<div class="col-xs-offset-0 col-sm-offset-2 col-md-offset-3 col-xs-12 col-md-9">
						<label for="" class="control-label col-xs-3 col-sm-3 col-md-3"><%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%></label>
						<div class="col-xs-3  col-sm-3 col-md-2">
							<input type="text" class="custom-form-control" name="<%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO) %>" id="pagenumber" placeholder="" value="<%=rn.getPageSize()%>">
						</div>
						<div class="col-xs-6  col-sm-5  col-md-4">					
						 	<input type="hidden" name="search" value="yes" />
<!-- 				          	<input type="reset" class="btn  btn-sm btn btn-circle  grey-mint btn-outline sbold uppercase" value="Reset" > -->
					        <input type="submit" onclick="allfield_changed()" class="btn  btn-sm btn btn-circle btn-sm green-meadow btn-outline sbold uppercase" value="<%=LM.getText(LC.GLOBAL_SEARCH, loginDTO) %>" >
				        </div>
					</div>
				</div>
			</div>
		</div>
		<!-- END FORM-->
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
</div>

<script type="text/javascript">

	function setPageNo(res)
	{
		document.getElementById('pageno').value = document.getElementById('hidden_pageno').value;
		document.getElementById('totalpage').innerHTML= document.getElementById('hidden_totalpage').value;
	}
	function dosubmit(params)
	{
		//alert(params);
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) 
		    {
		    	document.getElementById('tableForm').innerHTML = this.responseText ;
				setPageNo(this);
			}
		    else if(this.readyState == 4 && this.status != 200)
			{
				alert('failed ' + this.status);
			}
		  };
		  
		  xhttp.open("Get", "<%=action%>&" + params, true);
		  xhttp.send();
		
	}
	
	function anyfield_changed()
	{	
		var params = 'AnyField=' + document.getElementById('anyfield').value + '&search=true&ajax=true&RECORDS_PER_PAGE=' + document.getElementById('pagenumber').value;
		dosubmit(params);
	}
	
	function allfield_changed()
	{
		 var params = 'AnyField=' + document.getElementById('anyfield').value;
		  <%
		  for(int i = 0; i < searchFieldInfo.length - 1; i ++)
		  {
			  out.println("params += '&" +  searchFieldInfo[i][1] + "='+document.getElementById('" 
		  		+ searchFieldInfo[i][1] + "').value");
		  }
		  %>
		  params +=  '&search=true&ajax=true&RECORDS_PER_PAGE=' + document.getElementById('pagenumber').value;
		  dosubmit(params);
	
	}

</script>

