<%@page import="language.LC"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="language.LM"%>
<%@ page language="java" %>
<%@ page import="util.RecordNavigator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="searchform.SearchForm"%>
<%@ page import="liquid_semen.Liquid_semenAnotherDBDAO"%>
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

	String Language = LM.getText(LC.LIQUID_SEMEN_EDIT_LANGUAGE, loginDTO);
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
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_ANYFIELD, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="anyfield"
									placeholder="" name="anyfield" onKeyUp='anyfield_changed()'>
							</div>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_CENTRETYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='centre_type' id = 'centre_type' onchange="allfield_changed()">
<%
			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + row, "form-control", "centreType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_BULLTYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='bull_type' id = 'bull_type' onchange="allfield_changed()">
<%
			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + row, "form-control", "bullType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_NOOFDOSE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="number" class="form-control" id="no_of_dose" placeholder="" name="no_of_dose" onkeyup="allfield_changed()" onmouseup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_VOLUME, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="volume" placeholder="" name="volume" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_DENSITY, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="density" placeholder="" name="density" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_PROGRESSIVEMOTILITY, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="progressive_motility" placeholder="" name="progressive_motility" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_COLORTYPE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<select class='form-control'  name='color_type' id = 'color_type' onchange="allfield_changed()">
<%
			
			Options = Liquid_semenAnotherDBDAO.getOptions(Language, "select", "color", "colorType_select_" + row, "form-control", "colorType", "any" );			
			out.print(Options);
%>
</select>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_COLLECTIONDISTRIBUTIONDATE, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="collection_distribution_date" placeholder="" name="collection_distribution_date" onkeyup="allfield_changed()">
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-3">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4 col-xs-4"><%=LM.getText(LC.LIQUID_SEMEN_SEARCH_DESCRIPTION, loginDTO)%></label>
							<div class="col-md-8 col-sm-8 col-xs-8">
								<input type="text" class="form-control" id="description" placeholder="" name="description" onkeyup="allfield_changed()">
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

