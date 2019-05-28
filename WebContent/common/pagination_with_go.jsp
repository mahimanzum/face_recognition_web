<%@page import="language.LC"%>
<%@page import="language.LM"%>
<div class="portlet box portlet-btcl light" style="border-top:none; box-shadow:unset !important;">

	<div class="row">
		<div class="col-lg-offset-5 col-xs-12 col-sm-12 col-md-12 col-lg-7">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 custom-align-content" style="margin:0px 0px 10px 7px;">
					<div class="row">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-7">
							<label style="margin: 6px;font-size: 15px;"><%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO)%></label>
						</div>
						<div class="col-xs-6 col-sm-8 col-md-8 col-lg-5">
						<input type="text" class="form-control" name="<%=LM.getText(LC.GLOBAL_RECORD_PER_PAGE, loginDTO) %>" id="pagenumber" placeholder="" value="<%=rn.getPageSize()%>">
						</div>
					</div>
					
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4 custom-align-content">
					<form action="<%=action%>" method="POST" id='searchform' class="form-inline">
					<nav aria-label="Page navigation" >
					  <ul class="pagination" style="margin: 0px;">
					   <li class="page-item" style="height:34px">
					      <a class="page-link" href="<%=link%><%=concat%>id=first" aria-label="First"  title="Left">
					        <i class="fa fa-angle-double-left" aria-hidden="true"></i>
					        <span class="sr-only">First</span>
					      </a>
					    </li>
					    <li class="page-item" style="height:34px">
					      <a class="page-link" href="<%=link%><%=concat%>id=previous" aria-label="Previous" title="Previous">
					         <i class="fa fa-angle-left" aria-hidden="true"></i>
					        <span class="sr-only">Previous</span>
					      </a>
					    </li>
					
					     <li class="page-item" style="height:34px">
					      <a class="page-link" href="<%=link%><%=concat%>id=next" aria-label="Next" title="Next">
					         <i class="fa fa-angle-right" aria-hidden="true"></i>
					        <span class="sr-only">Next</span>
					      </a>
					    </li>
					    <li class="page-item" style="height:34px">
					      <a class="page-link" href="<%=link%><%=concat%>id=last" aria-label="Last"  title="Last">
					        <i class="fa fa-angle-double-right" aria-hidden="true"></i>
					        <span class="sr-only">Last</span>
					      </a>
					    </li>
					   
					  </ul>
					</nav>
				   </form>
				</div>
				
				<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
					<div>
						<i><%=LM.getText(LC.GLOBAL_PAGE, loginDTO) %> </i>
							<input type="text" name="pageno" id="pageno" value='<%=pageno%>' size="15" class='custom-from-control'> <i class="hidden-xs"><%=LM.getText(LC.GLOBAL_OF, loginDTO) %></i>
						<i id='totalpage'>
							<%=rn.getTotalPages()%>
						</i>
				
						<input type="hidden" name="go" value="yes" />
						<input type="hidden" name="mode" value="search" />
						<input type="submit" class="btn btn-sm green-haze btn-outline sbold uppercase" value="<%=LM.getText(LC.GLOBAL_GO, loginDTO)%>" style="height: 34px;margin-bottom: 3px;"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
