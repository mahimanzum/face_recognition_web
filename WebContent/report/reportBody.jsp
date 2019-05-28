<%@page import="user.UserDTO"%>
<%@page import="user.UserRepository"%>
<%@page import="login.LoginDTO"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="permission.MenuConstants"%>
<%@page import="role.PermissionRepository"%>
<%@page import="util.ActionTypeConstant"%>
<html:base />


<%


LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID); 

boolean hasTemplateUpdatePermission = PermissionRepository.checkPermissionByRoleIDAndMenuID(loginUserDTO.roleID, MenuConstants.REPORT_PERFORMANCE_LOG_TEMPLATE_UPDATE);


%>

				<div  class="row col-md-12"  >
						<div class="col-md-12" id="tables">
							
							
							<input class="dtoName" name="dtoName" value="">
							<button id="get-dto-fields-button" type="button" class="btn blue">
								<i class="fa fa-save"></i> Fetch Columns
							</button>
							<button id="save-dto-fields-button" type="button" class="btn blue">
								<i class="fa fa-save"></i> Save Columns
							</button>
							<button id="get-saved-dto-fields-button" type="button" class="btn blue">
								<i class="fa fa-save"></i> Get Columns
							</button>
						</div>
						<div class="col-md-12" id="sql">
						</div>
						
				</div>

<form class="form-horizontal" id="ReportForm"
			action="<%=request.getContextPath()%>/Report/performanceLogServlet?actionType=<%=ActionTypeConstant.REPORT_RESULT%>">
<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cubes"></i>Report Generator
		</div>
	</div>
	<div class="portlet-body form">
		
			<input type="hidden" id="countURL" name="countURL" value="Report/performanceLogServlet?actionType=<%=ActionTypeConstant.REPORT_COUNT%>">
			<div class="form-body">
				<div class="row" <%=(hasTemplateUpdatePermission?"":"style=\"display:none\"" )%>>
<!--  					<div class="col-md-6">
						<div class="col-md-8">
							<select class="form-control load-template"></select>
						</div>
						<div class="col-md-4">
							<button id="load-template-button" type="button"
								class="btn blue-hoki" disabled>
								<i class="fa fa-upload"></i> Load
							</button>
						</div>
					</div> -->
					<div class="col-md-6">
						<!-- <div class="col-md-8">
							<input class="form-control save-template" type="text"
								placeholder="Save this Template">
						</div> -->
						<div class="col-md-4">
							<button id="save-template-button" type="button" class="btn blue">
								<i class="fa fa-save"></i> Save
							</button>
						</div>
						
						<div class="col-md-5">
							<label class="checkbox">
								<span>
									<input id="isSinglePageReport" type="checkbox" class="input-checkbox-display" name="isSinglePageReport" value="1" >
								</span>Single Page Report
							</label>
						</div>
					</div>
				
				
				
 				</div>
				<div class="row" <%=(hasTemplateUpdatePermission?"":"style=\"display:none\"" )%>>
					<hr>
				</div>
				<div  class="row" <%=(hasTemplateUpdatePermission?"":"style=\"display:none\"" )%> >
						<div class="col-md-3" id="criteria"></div>
						<div class="col-md-6" id="display"><%@include file="reportDisplayDiv.jsp"%></div>
						<div class="col-md-3" id="orderby"></div>
				</div>
				<div id="searchCriteria"></div>


				<div class="custom-form-action">
					<div>
						<div class="text-center">
							<button type="reset" class="btn btn-danger">Reset</button>
							<button id="defaultLoad" type="submit" class="btn btn-success">Submit</button>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>

<div class="portlet box portlet-btcl light navigator">
	<div class="portlet-body">	
		<div class="row text-center">
				<nav aria-label="Page navigation">
					<ul class="pagination" style="margin: 0px;">
						<li style="float:left;"><i class="hidden-xs">Record Per Page</i>&nbsp;&nbsp;
							<input type="text" class="custom-form-control  black-text" name="RECORDS_PER_PAGE" style="height: 34px;" placeholder="" value="100" />&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li class="page-item"><a class="page-link" href="" id="firstLoad"
							aria-label="First" title="Left"> <i
								class="fa fa-angle-double-left" aria-hidden="true"></i> <span
								class="sr-only">First</span>
						</a></li>
						<li class="page-item"><a class="page-link" id="previousLoad"
							href="" aria-label="Previous" title="Previous"> <i
								class="fa fa-angle-left" aria-hidden="true"></i> <span
								class="sr-only">Previous</span>
						</a></li>

						<li class="page-item"><a class="page-link" href="" id="nextLoad"
							aria-label="Next" title="Next"> <i class="fa fa-angle-right"
								aria-hidden="true"></i> <span class="sr-only">Next</span>
						</a></li>
						<li class="page-item"><a class="page-link" href="" id="lastLoad"
							aria-label="Last" title="Last"> <i
								class="fa fa-angle-double-right" aria-hidden="true"></i> <span
								class="sr-only">Last</span>
						</a></li>
						<li>&nbsp;&nbsp;<i class="hidden-xs">Page </i><input
							type="text" class="custom-form-control  black-text" name="pageno" value='' style="height: 34px;"
							size="15"> <i class="hidden-xs">of</i>&nbsp;&nbsp;<input
							type="text" class="custom-form-control  black-text" name="tatalPageNo" value='' style="height: 34px;"
							size="15" disabled> <input type="hidden" name="totalRecord"><input type="submit" id="forceLoad"
							class="btn btn-success"
							value="GO" />
						</li>
					</ul>
				</nav>
		</div>
	</div>
</div>
</form>
<div class="portlet box portlet-btcl light">
	<div class="portlet-body">
		<!-- Dynamically loaded report table -->
		<div class="row" id="report-div">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title">
						<div class="caption font-dark">
							<i class="icon-settings font-dark"></i> <span
								class="caption-subject bold uppercase">Report</span>
						</div>
						<div class="tools"></div>
					</div>
					<div class="portlet-body">
						<div class="table-responsive">
							<table class="table table-bordered table-striped" id="reportTable"
								style="width: 100%">

								<thead>
									<tr>
										<th></th>
									</tr>
								</thead>

								<tbody>
								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Dynamically loaded report table -->
	</div>
</div>

