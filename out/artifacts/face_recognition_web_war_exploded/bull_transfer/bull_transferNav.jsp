<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="searchform.SearchForm"%>
<%@ page import="bull_transfer.Bull_transferAnotherDBDAO"%>
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

	String Language = LM.getText(LC.BULL_TRANSFER_EDIT_LANGUAGE, loginDTO);
	String Options;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String datestr = dateFormat.format(date);
%>






<!-- search control -->
<div class="portlet box portlet-btcl">
	<div>
		<!-- BEGIN FORM-->
		<div class="form-horizontal">
			<div class="form-body">
				<div class="row">

					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class="form-control" id="pagenumber" name="<%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%>" onchange='allfield_changed()'>
								<option value = '10'>10</option>
								<option value = '20'>20</option>
								<option value = '30'>30</option>
								<option value = '50'>50</option>
								<option value = '100'>30</option>
								<option value = '100000'>All</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_ANYFIELD, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="anyfield"
									placeholder="" name="anyfield" onKeyUp='anyfield_changed()'>
							</div>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_DATEOFTRANSFER, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="date_of_transfer" placeholder="" name="date_of_transfer" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_BULLTYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='bull_type' id = 'bull_type' onchange="allfield_changed()">
<%
			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + row, "form-control", "bullType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_FROMCENTRETYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='from_centre_type' id = 'from_centre_type' onchange="allfield_changed()">
<%
			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "fromCentreType_select_" + row, "form-control", "fromCentreType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_TOCENTRETYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='to_centre_type' id = 'to_centre_type' onchange="allfield_changed()">
<%
			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "centre", "toCentreType_select_" + row, "form-control", "toCentreType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_ENTRYDATE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="entry_date" placeholder="" name="entry_date" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_EXITDATE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="exit_date" placeholder="" name="exit_date" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_DESCRIPTION, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="description" placeholder="" name="description" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.BULL_TRANSFER_SEARCH_APPROVALSTATUSTYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='approval_status_type' id = 'approval_status_type' onchange="allfield_changed()">
<%
			
			Options = Bull_transferAnotherDBDAO.getOptions(Language, "select", "approval_status", "approvalStatusType_select_" + row, "form-control", "approvalStatusType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<!-- END FORM-->
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

