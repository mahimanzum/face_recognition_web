<%@page import="language.LC"%>
<%@page import="language.LM"%>

<div class="portlet box portlet-btcl light navigator">
	<div class="portlet-body">
	
	<div class="row">
		<div class="col-lg-offset-5 col-xs-12 col-sm-12 col-md-12 col-lg-7">
			<div class="row">
			
			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 custom-align-content" style="margin-bottom: 10px;">
					<div class="row">
						<div class="col-xs-4 col-sm-4 col-md-4 col-lg-6 custom-align-content">
						   <label style="margin-top:6px"><%=LM.getText(LC.PBREPORT_OTHER_RECORD_PER_PAGE, loginDTO)%></label>
							
						</div>
						<div class="col-xs-5 col-sm-8 col-md-8 col-lg-5">
						 <input type="text" class="form-control" name="RECORDS_PER_PAGE"
										id="RECORDS_PER_PAGE" placeholder="" value="1000"
										onKeyUp="ajaxSubmit();">
						</div>
					</div>
					
				</div>
			<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4 custom-align-content">
				
				<nav aria-label="Page navigation">
						<ul class="pagination" style="margin: 0px 0px 0px 2px;">
							<li class="page-item"><a class="page-link"
								onclick="firstloadSubmit();" id="firstLoad" aria-label="First"
								title="Left"> <i class="fa fa-angle-double-left"
									aria-hidden="true"></i> <span class="sr-only"><%=LM.getText(LC.PBREPORT_OTHER_FIRST, loginDTO)%></span>
							</a></li>
							<li class="page-item"><a class="page-link"
								onclick="prevloadSubmit();" id="previousLoad"
								aria-label="Previous" title="Previous"> <i
									class="fa fa-angle-left" aria-hidden="true"></i> <span
									class="sr-only"><%=LM.getText(LC.PBREPORT_OTHER_PREVIOUS, loginDTO)%></span>
							</a></li>

							<li class="page-item"><a class="page-link"
								onclick="nextloadSubmit();" id="nextLoad" aria-label="Next"
								title="Next"> <i class="fa fa-angle-right" aria-hidden="true"></i>
									<span class="sr-only"><%=LM.getText(LC.PBREPORT_OTHER_NEXT, loginDTO)%></span>
							</a></li>
							<li class="page-item"><a class="page-link"
								onclick="lastloadSubmit();" id="lastLoad" aria-label="Last"
								title="Last"> <i class="fa fa-angle-double-right"
									aria-hidden="true"></i> <span class="sr-only"><%=LM.getText(LC.PBREPORT_OTHER_LAST, loginDTO)%></span>
							</a></li>
							
							
						</ul>
					</nav>
			</div>
			<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
				<input type="number" class="custom-from-control" style="width:43% !important;height: 37px !important;" name="pageno" ID="pageno" value="1" size="15" onKeyUp="ajaxSubmit();">
				<i class="hidden-xs"><%=LM.getText(LC.PBREPORT_OTHER_OF, loginDTO)%></i>
							<i class="hidden-xs" ID="tatalPageNo"></i> <input type="hidden"
							name="totalRecord" /> <input type="hidden" name="tatalPageNo" />
				
				</div>
		</div>
		
		</div>
	</div>

	
	</div>
</div>

