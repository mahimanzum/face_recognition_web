<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="searchform.SearchForm"%>
<%@ page import="pb.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%
	System.out.println("Inside nav.jsp");
	String url = request.getParameter("url");
	String navigator = request.getParameter("navigator");
	String pageName = request.getParameter("pageName");
	if (pageName == null)
		pageName = "Search";
	String pageno = "";
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
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
	String searchFieldInfo[][] = rn.getSearchFieldInfo();
	String totalPage = "1";
	if (rn != null)
		totalPage = rn.getTotalPages() + "";
	int row = 0;

	String Language = LM.getText(LC.PB_NOTIFICATIONS_EDIT_LANGUAGE, loginDTO);
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
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_ISSEEN, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="is_seen" placeholder="" name="is_seen">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_ISHIDDEN, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="is_hidden" placeholder="" name="is_hidden">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SOURCE, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="source" placeholder="" name="source">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_DESTINATION, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="destination" placeholder="" name="destination">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_FROMID, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="from_id" placeholder="" name="from_id">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_TOID, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="to_id" placeholder="" name="to_id">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_TEXT, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="text" placeholder="" name="text">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_URL, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="url" placeholder="" name="url">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_ENTRYDATE, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="entry_date" placeholder="" name="entry_date">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SEENDATE, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="seen_date" placeholder="" name="seen_date">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SHOWINGDATE, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="showing_date" placeholder="" name="showing_date">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SENDALARM, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="send_alarm" placeholder="" name="send_alarm">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SENDSMS, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="send_sms" placeholder="" name="send_sms">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SENDMAIL, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="send_mail" placeholder="" name="send_mail">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SENDPUSH, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="send_push" placeholder="" name="send_push">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_MAILSENT, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="mail_sent" placeholder="" name="mail_sent">
							</div>
						</div>
					</div>
							
				</div>
				<div class="row">
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_SMSSENT, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="sms_sent" placeholder="" name="sms_sent">
							</div>
						</div>
					</div>
							
					<div class="col-md-6 col-sm-6">
						<div class="row">
							<label for="" class="control-label col-4"><%=LM.getText(LC.PB_NOTIFICATIONS_SEARCH_PUSHSENT, loginDTO)%></label>
							<div class="col-8">
								<input type="text" class="form-control" id="push_sent" placeholder="" name="push_sent">
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

