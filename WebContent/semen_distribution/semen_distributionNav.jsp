<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="searchform.SearchForm"%>
<%@ page import="semen_distribution.Semen_distributionAnotherDBDAO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

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
	int row = 0;
	
	String Language = LM.getText(LC.SEMEN_DISTRIBUTION_EDIT_LANGUAGE, loginDTO);
	String Options;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String datestr = dateFormat.format(date);

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
		<div class="form-horizontal">			
			<div class="form-body">
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_BULLTYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='bull_type' id = 'bull_type' >
<%
			
			Options = Semen_distributionAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + row, "form-control", "bullType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_NOOFDOSE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="number" class="form-control" id="no_of_dose" placeholder="" name="no_of_dose">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_REQUISITIONID, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="requisition_id" placeholder="" name="requisition_id">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_GROUPID, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="group_id" placeholder="" name="group_id">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_TRANSACTIONDATE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="transaction_date" placeholder="" name="transaction_date">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.SEMEN_DISTRIBUTION_SEARCH_DESCRIPTION, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="description" placeholder="" name="description">
							</div>
						</div>
					</div>
							
				</div>
			</div>	


			<div class=clearfix></div>

			<div class="form-actions fluid">
				<div class="row">
					<div
						class="col-xs-offset-0 col-sm-offset-2 col-md-offset-3 col-xs-12 col-md-9">
						<label for="" class="control-label col-xs-3 col-sm-3 col-md-3"><%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%></label>
						<div class="col-xs-3  col-sm-3 col-md-2">
							<input type="text" class="custom-form-control"
								name="<%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO) %>"
								id="pagenumber" placeholder="" value="<%=rn.getPageSize()%>">
						</div>
						<div class="col-xs-6  col-sm-5  col-md-4">
							<input type="hidden" name="search" value="yes" />
							<!-- 				          	<input type="reset" class="btn  btn-sm btn btn-circle  grey-mint btn-outline sbold uppercase" value="Reset" > -->
							<input type="submit" onclick="allfield_changed()"
								class="btn  btn-sm btn btn-circle btn-sm green-meadow btn-outline sbold uppercase"
								value="<%=LM.getText(LC.GLOBAL_SEARCH, loginDTO) %>">
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END FORM-->
	</div>
</div>


<%@include file="../common/pagination_with_go.jsp"%>

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

