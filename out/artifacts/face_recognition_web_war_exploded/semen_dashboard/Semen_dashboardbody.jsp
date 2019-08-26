









<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@page import="login.LoginDTO"%>
<%@page import="semen_dashboard.Semen_dashboardDTO"%>
<%@page import="semen_dashboard.Semen_dashboardDAO"%>
<%
	LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	Semen_dashboardDAO semen_dashboardDAO = new Semen_dashboardDAO();
	Semen_dashboardDTO semen_dashboardDTO = semen_dashboardDAO.getDashboardDTO();
	if (semen_dashboardDTO == null) {
		System.out.println("making new dto");
		semen_dashboardDTO = new Semen_dashboardDTO();
	}
%>
<div class="portlet box portlet-btcl light">
	<div class="portlet-title">
		<div class="caption">
			<i class="icon-settings"></i> <span id="report-name"
				class="caption-subject bold uppercase">DLS Dashboard</span>
		</div>
		<div class="tools"></div>
	</div>
	<div class="portlet-body">
		<div class="row">
			<div class="col-md-12">
				<div class="lg-rectbox">
					<div class="lg-rectbox-header"></div>
					<div class="lg-rectbox-body">
						<h3 class="text-align-center widget-counter">
							<span></span>Achievements
						</h3>
						<div class="row">
							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Semen Collection
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_1_0_0">Actual Collection: </span><span
															class="counter"><%=semen_dashboardDTO.actual_semen_collection%></span>
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_2_0_1">Percentage Achieved: </span><span
															class="counter"><%=(int) semen_dashboardDTO.semen_collection_percentage%>%</span>
													</h4>
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Artificial Insemenation
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_1_0_0">Actual AI: </span><span
															class="counter"><%=semen_dashboardDTO.actual_ai%></span>
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_2_0_1">Percentage Achieved: </span><span
															class="counter"><%=(int) semen_dashboardDTO.ai_percentage%>%</span>
													</h4>
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Progeny
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_1_0_0">Actual Progeny: </span><span
															class="counter"><%=semen_dashboardDTO.actual_progeny%></span>
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_2_0_1">Percentage Achieved: </span><span
															class="counter"><%=(int) semen_dashboardDTO.progeny_percentage%>%</span>
													</h4>
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>

							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Candidate Bull Production
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_1_0_0">Actual Production: </span><span
															class="counter"><%=semen_dashboardDTO.actual_cb%></span>
													</h4>

													<h4 class="custom-h4">
														<span id="TEXT_2_0_1">Percentage Achieved: </span><span
															class="counter"><%=(int) semen_dashboardDTO.cb_percentage%>%</span>
													</h4>
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>

							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Most Productive Bulls
													</h4>

													<%
														for (int i = 0; i < 5; i++) {
															String ithProgenyBull = (semen_dashboardDTO.top_5_progeny_bulls[i] == null) ? ""
																	: semen_dashboardDTO.top_5_progeny_bulls[i];
															String ithProgenyBullCalf = (semen_dashboardDTO.top_5_progeny_bulls[i] == null) ? ""
																	: semen_dashboardDTO.top_5_progeny_bulls_calfs[i] + "";

															out.println("<h4 class=\"custom-h4\">");
															out.println("<span>" + (i + 1) + " . " + ithProgenyBull + ":</span>");
															out.println("<span class= 'counter'>" + ithProgenyBullCalf + "</span>");
															out.println("</h4>");
														}
													%>
												
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>

							<div class="col-md-6 col-sm-6 col-12 py-2">

								<div
									class="col-md-12 col-sm-12 widget-holder widget-full-height">
									<div class="widget-bg bg-custom-text-black">
										<div class="widget-body clearfix">
											<div class="widget-counter">
												<h3 class="widget-counter">
													Most Productive Centres
													</h4>

													<%
														for (int i = 0; i < 5; i++) {
															String ithProgenyCenter = (semen_dashboardDTO.top_5_progeny_centers[i] == null) ? ""
																	: semen_dashboardDTO.top_5_progeny_centers[i];
															String ithProgenyCentreCalf = (semen_dashboardDTO.top_5_progeny_centers[i] == null) ? ""
																	: semen_dashboardDTO.top_5_progeny_centers_calfs[i] + "";

															out.println("<h4 class=\"custom-h4\">");
															out.println("<span>" + (i + 1) + " . " + ithProgenyCenter + ":</span>");
															out.println("<span class= 'counter'>" + ithProgenyCentreCalf + "</span>");
															out.println("</h4>");
														}
													%>
												
											</div>
											<!-- /.widget-counter -->
										</div>
										<!-- /.widget-body -->
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<br> <br>


	</div>

</div>

<!-- 

<div>
	<div class="row">
		<div class="col-md-12">
			<div class="lg-rectbox">
				<div class="lg-rectbox-header"></div>
				<div class="lg-rectbox-body">
					<h3 class="text-align-center lg-rectbox-heading">
						<span></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
					<div class="row">
						<div class="col-md-4 col-sm-6 col-12 py-2">

							<h3 class="text-align-center md-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
							<div class="col-md-12 col-sm-12 col-12 h-100 text-align-center">
								<div class="row py-2">
									<div
										class="col-md-6 col-sm-6 col-9 col-xs-9 sm-box-1 text-align-center sm-min-height1">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
									</div>
									<div
										class="col-md-6 col-sm-6 col-3 col-xs-3 sm-box-1 text-align-center sm-min-height1">
										<button type="button" id="TEXT_1_0_0" class="btn btn-type1">1</button>
									</div>
								</div>

								<div class="row">

									<div
										class="col-md-6 col-sm-6 col-12  text-align-center padding-right-5 xs-2">
										<div class="sm-box-1 h-100">
											<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
											<button type="button" id="TEXT_2_0_1" class="btn">1</button>
										</div>
									</div>
									<div
										class="col-md-6 col-sm-6 col-12  text-align-center padding-left-5 xs-2">
										<div class="sm-box-1 h-100">
											<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
											<button type="button" id="TEXT_3_0_2" class="btn">1</button>
										</div>
									</div>

								</div>

							</div>
						</div>
						<div class="col-md-4 col-sm-6 col-12 py-2">

							<h3 class="text-align-center md-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
							<div class="col-md-12 col-sm-12 col-12 h-100 text-align-center">
								<div class="row py-2">
									<div
										class="col-md-6 col-sm-6 col-9 col-xs-9 sm-box-1 text-align-center sm-min-height1">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
									</div>
									<div
										class="col-md-6 col-sm-6 col-3 col-xs-3 sm-box-1 text-align-center sm-min-height1">
										<button type="button" id="TEXT_1_1_0" class="btn btn-type1">2</button>
									</div>
								</div>

								<div class="col-md-12 col-sm-12 col-12 h-100 text-align-center">
									<div class="row py-2">
										<div
											class="col-md-6 col-sm-6 col-9 col-xs-9 sm-box-1 text-align-center sm-min-height1">
											<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
										</div>
										<div
											class="col-md-6 col-sm-6 col-3 col-xs-3 sm-box-1 text-align-center sm-min-height1">
											<button type="button" id="TEXT_2_1_1" class="btn btn-type1"><%=semen_dashboardDTO.d_Text_2_1_1%></button>
										</div>
									</div>

								</div>

							</div>
						</div>
						<div class="col-md-4 col-sm-6 col-12 py-2">

							<div class="row">
								<div class="col-md-12 col-sm-12 col-12">
									<div class="list-group right-two-list-group">
										<div class="list-group-item-grs">
											<span class="list-icon"><img
												src="../../../rootstock3/assets/global/img/dashboard/grievance-resolve-rate.png"></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%><span
												class="item-count">(<span id="TEXT_1_2_0"><%=semen_dashboardDTO.d_Text_1_2_0%></span>%)
											</span>
										</div>
										<div class="list-group-item-grs">
											<span class="list-icon"><img
												src="../../../rootstock3/assets/global/img/dashboard/grievance-resolve-rate.png"></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%><span
												class="item-count">(<span id="TEXT_2_2_1"><%=semen_dashboardDTO.d_Text_2_2_1%></span>%)
											</span>
										</div>
									</div>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div>
    <div class="row">
        <div class="col-md-12">
            <div class="lg-rectbox">
                <div class="lg-rectbox-header"></div>
                <div class="lg-rectbox-body">
                    <h3 class="text-align-center lg-rectbox-heading"><span></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
					<div class="row">
						<div class="col-md-4 col-sm-6 col-12 py-2">
				
								<h3 class="text-align-center md-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
								<div class="row py-2">
																	
								<div class="col-md-6 col-sm-6 col-9 col-xs-9 sm-box-1 text-align-center sm-min-height1">
									<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
								</div>
								<div class="col-md-6 col-sm-6 col-3 col-xs-3 sm-box-1 text-align-center sm-min-height1">
									<button type="button" id="TEXT_1_0_0" class="btn btn-type1"><%=semen_dashboardDTO.d_Text_1_0_0%></button>
								</div>
                                
                                </div>
                                <div class="row">
                                    
								<div class="col-md-6 col-sm-6 col-12  text-align-center padding-right-5 xs-2">
									<div class="sm-box-1 h-100">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
										<button type="button" id="TEXT_2_0_1" class="btn"><%=semen_dashboardDTO.d_Text_2_0_1%></button>
									</div>
								</div>
																	<div class="col-md-6 col-sm-6 col-12  text-align-center padding-left-5 xs-2">
									<div class="sm-box-1 h-100">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
										<button type="button" id="TEXT_3_0_2" class="btn"><%=semen_dashboardDTO.d_Text_3_0_2%></button>
									</div>
								</div>
                                </div>
		


	
				
				
						</div>
						<div class="col-md-4 col-sm-6 col-12 py-2">
				
								<h3 class="text-align-center md-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
								<div class="row py-2">
									
								<div class="col-md-6 col-sm-6 col-12  text-align-center padding-right-5 xs-2">
									<div class="sm-box-1 h-100">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
										<button type="button" id="TEXT_1_1_0" class="btn"><%=semen_dashboardDTO.d_Text_1_1_0%></button>
									</div>
								</div>
																	<div class="col-md-6 col-sm-6 col-12  text-align-center padding-left-5 xs-2">
									<div class="sm-box-1 h-100">
										<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
										<button type="button" id="TEXT_2_1_1" class="btn"><%=semen_dashboardDTO.d_Text_2_1_1%></button>
									</div>
								</div>
                                </div>
                                <div class="row">
                                    								
								<div class="col-md-6 col-sm-6 col-9 col-xs-9 sm-box-1 text-align-center sm-min-height1">
									<h3 class="text-align-center sm-heading"><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%></h3>
								</div>
								<div class="col-md-6 col-sm-6 col-3 col-xs-3 sm-box-1 text-align-center sm-min-height1">
									<button type="button" id="TEXT_3_1_2" class="btn btn-type1"><%=666%></button>
								</div>
                                
                                </div>
		


	
				
				
						</div>
						<div class="col-md-4 col-sm-6 col-12 py-2">
				
								<div class="row">
                                    <div class="col-md-12 col-sm-12 col-12">
                                        <div class="list-group right-two-list-group">
																						<div class="list-group-item-grs">
                                                <span class="list-icon"><img src="../../../rootstock3/assets/global/img/dashboard/grievance-resolve-rate.png" ></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%><span class="item-count">(<span id="TEXT_1_2_0"><%=semen_dashboardDTO.d_Text_1_2_0%></span>%)</span>
                                            </div>
																						<div class="list-group-item-grs">
                                                <span class="list-icon"><img src="../../../rootstock3/assets/global/img/dashboard/grievance-resolve-rate.png" ></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%><span class="item-count">(<span id="TEXT_2_2_1"><%=semen_dashboardDTO.d_Text_2_2_1%></span>%)</span>
                                            </div>
											
										</div>
									</div>
								</div>

								
	
								<div class="row">
                                    <div class="col-md-12 col-sm-12 col-12">
                                        <div class="list-group right-two-list-group">
																						<div class="list-group-item-grs">
                                                <span class="list-icon"><img src="../../../rootstock3/assets/global/img/dashboard/grievance-resolve-rate.png" ></span><%=LM.getText(LC.SEMEN_DASHBOARD_STATIC_SEMEN_DASHBOARD, loginDTO)%><span class="item-count">(<span id="TEXT_1_2_0"><%=semen_dashboardDTO.d_Text_1_2_0%></span>%)</span>
                                            </div>
											
										</div>
									</div>
								</div>

								
	
				
				
						</div>
			
					</div>
																
                </div>
            </div>
        </div>
    </div>
</div>

 -->