<%@page import="org.apache.log4j.Logger"%>
<%@page import="forgetPassword.VerificationConstants"%>
<%@page import="config.GlobalConfigConstants"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" %>
<%
	Logger otpLogger = Logger.getLogger("otpVerifier_jsp");
	String loginURL=request.getRequestURL().toString();
	String context = "../../.."  + request.getContextPath() + "/";
	boolean pageValid = true;
	if(session.getAttribute(VerificationConstants.CACHE_OBJECT) == null)
	{
		otpLogger.debug("cache object not found. so redirecting to home");
		pageValid = false;		
	}
	
	if(GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_IN_LOGIN_ENABLED_FOR_CLIENT).value == 0
		&& GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_IN_LOGIN_ENABLED_FOR_ADMIN).value == 0)
	{
		otpLogger.debug("otp not enabled. so redirecting to home");
		pageValid = false;	
	}
	if(!pageValid)
	{
		response.sendRedirect(context);
	}
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
        <title>Welcome to BTCL Bangladesh Limited | OTP Verifier</title>
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
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="<%=request.getContextPath()%>/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=request.getContextPath()%>/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
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
            <form id="otp-form" class="login-form" action="<%=request.getContextPath()%>/<%=session.getAttribute(VerificationConstants.CACHE_OTP_VERIFICATION_URL)%>" method="post">
            	<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
            	<input type="hidden" name="<%=VerificationConstants.CACHE_FORWARD%>" value="<%=session.getAttribute(VerificationConstants.CACHE_FORWARD)%>"/>
                <h3 class="form-title font-purple">OTP Verification</h3>             
                <jsp:include page='../common/flushActionStatus.jsp' />

                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">OTP</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="OTP" name="token" /> </div>               
                
                <div class="form-actions">                	
                    <button type="submit" class="btn green-jungle  btn-block uppercase">Submit</button>
                    <button type="button" id="resendOTP" class="btn grey  btn-block uppercase">Resend OTP</button>
                </div>
            </form>
            <!-- END LOGIN FORM -->
        </div>
         
        <div class="copyright"> <%=Calendar.getInstance().get(Calendar.YEAR) %> @ BTCL Bangladesh Limited </div>
	       
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
        
        <script src="<%=request.getContextPath()%>/assets/scripts/common/otpVerifier.js" type="text/javascript"></script>
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