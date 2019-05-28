<%@page import="theme.ThemeRepository"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="config.GlobalConfigConstants"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" %>
<%
	LM.getInstance();
	String loginURL=request.getRequestURL().toString();
	String context = "../../.."  + request.getContextPath() + "/";
	
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>Welcome to Project Builder | Admin Login</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/styles/styles.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="<%=request.getContextPath()%>/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=request.getContextPath()%>/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <%String themeDescription = ThemeRepository.getInstance().getCurrentAppliedThemeDescriprion();
		if(themeDescription!=null){
		%>
		<link href="<%=context%>assets/Templates/<%=themeDescription%>/style.css" rel="stylesheet" type="text/css" />
		<%} %>
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="<%=request.getContextPath()%>/assets/pages/css/login.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" />
        <style type="text/css">
        	.login{
				background-color: #fff !important;
			}
			.login .copyright {
  				color: black !important;
  			}
  			.login .content h3{
  				color: black !important;
  			}
  			@media only screen and (min-width: 760px) {
	  			.quick-links span{
	  			  font-size: 18px !important;
	  			}
		     	.quick-links a{
		            font-size: 20px !important;
		            margin-left: 10px;
		            text-decoration: underline;
		      	}
			}
        </style>
         </head>
    <!-- END HEAD -->
		
    <body class=" login">
        <div class="menu-toggler sidebar-toggler"></div>
        <!-- END SIDEBAR TOGGLER BUTTON -->
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="<%=request.getContextPath()%>">
                <img  class="img-responsive login-page-logo" src="<%=request.getContextPath()%>/images/company-name.png" alt="" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" action="<%=request.getContextPath()%>/LoginServlet" method="post">
            	<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
                <h3 class="form-title font-purple"><%=LM.getText(LC.GLOBAL_SIGN_IN) %></h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> Enter any username and password. </span>
                </div>
                <jsp:include page='../common/flushActionStatus.jsp' />
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">Username</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username" /> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Password</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" /> 
                </div>
                
                <%if(GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.CAPTCHA_IN_LOGIN_ENABLED).value == 1){%>
                <div class="form-group">
					<label class="control-label visible-ie8 visible-ie9">Captcha</label>
					<div class="col-xs-8 col-md-10" >
						<img class="img-thumbnail" style="width: 100%; max-height: 34px; padding: 2px;" id="captcha" src="<%=request.getContextPath()%>/simpleCaptchaServlet" alt="loading captcha..."> 
					</div>
					<div class="col-xs-4 col-md-2" style="line-height: 34px;">
						 <i id="reloadCaptcha" title="Refresh Captcha" class="fa fa-refresh" aria-hidden="true"></i>
					</div> 
                </div>                
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Captcha Input</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Captcha Input" name="answer" /> 
                </div>
                <%}%>                
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-success btn-block"><%=LM.getText(LC.GLOBAL_LOG_IN) %></button>
                </div>
                
                <%if(GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.REMEMBER_ME).value == 1){%>
                <div class="form-group">
                	<label class="control-label"><%=LM.getText(LC.GLOBAL_STAY_LOGGED_IN) %></label>
                    <input class="form-control" type="checkbox" checked name = "stay_logged_in" value="1"/>
                </div>
                <%}%>
                
                 <div class="form-actions">
	                 <a href="<%=request.getContextPath() %>/forgotPassword/forgotPassword.jsp" > <%=LM.getText(LC.GLOBAL_FORGOT_PASSWORD) %> </a> &nbsp; &nbsp; &nbsp;
                     <a href="<%=context%>Client/Registration.do"> Create an account </a>
                </div>
	             
            </form>
            <!-- END LOGIN FORM -->
        </div>         
        </div>
        <div class="copyright"> <%=Calendar.getInstance().get(Calendar.YEAR) %> @ REVE Systems Limited </div>
       
        <!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/assetsglobal/plugins/respond.min.js"></script>
<script src="<%=request.getContextPath()%>/assetsglobal/plugins/excanvas.min.js"></script> 
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="<%=request.getContextPath()%>/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="<%=request.getContextPath()%>/assets/pages/scripts/login.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
				<script>
				$("#reloadCaptcha").click(function() {				
					$("#captcha").attr('src', $("#captcha").attr('src') + '?' + Math.random());					
				});
				</script>          
    </body>

</html>