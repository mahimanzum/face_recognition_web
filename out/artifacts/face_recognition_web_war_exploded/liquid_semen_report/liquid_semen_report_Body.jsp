<%@page import="util.ActionTypeConstant"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>
<%@page import="user.*"%>
<%@page import="centre.CentreDAO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
String userfullName = userDTO.fullName;
int userCentre = userDTO.centreType;
CentreDAO centreDAO = new CentreDAO();
String centreName = centreDAO.getCentreNameByCentreID((long)userCentre);
%>




<form class="form-horizontal" id="ReportForm"
			action="<%=request.getContextPath()%>/Liquid_semen_report_Servlet?actionType=<%=ActionTypeConstant.REPORT_RESULT%>">
<div class="portlet box portlet-btcl">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cubes"></i><%=LM.getText(LC.LIQUID_SEMEN_REPORT_OTHER_REPORT_GENERATION, loginDTO)%>
		</div>
	</div>
	<div class="portlet-body form">
		
			<input type="hidden" id="countURL" name="countURL" value="/Liquid_semen_report_Servlet?actionType=<%=ActionTypeConstant.REPORT_COUNT%>">
			<div class="form-body">
				<div class="row">
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

 				</div>
				<div class="row">
					<hr>
				</div>
				<div id="searchCriteria" hide = "false">
					<%@include file="liquid_semen_report_SearchDiv.jsp"%>
				</div>


				
			</div>
	</div>
</div>

<div class="portlet box portlet-btcl light">
	<div class="portlet-body">
		<!-- Dynamically loaded report table -->
		<div class="row" id="report-div">
			<div class="col-md-12">
				<div class="portlet light bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-settings"></i> <span id = "report-name"
								class="caption-subject bold uppercase">
								<%=LM.getText(LC.LIQUID_SEMEN_REPORT_OTHER_LIQUID_SEMEN_REPORT, loginDTO)%>  by <%=userfullName%>, <%=centreName%>
								</span>
						</div>
						<div class="tools"></div>
					</div>
					<div class="portlet-body">
						<div class="table">
							<table class="table table-striped " id="reportTable"
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

<%@include file="../pbreport/pagination.jsp"%>


</form>