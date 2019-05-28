package forgetPassword;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import common.CommonActionStatusDTO;
import common.RequestFailureException;
import ipRestriction.IPRestrictionService;
import language.LC;
import language.LM;
import login.LoginDTO;
import repository.RepositoryManager;
import sessionmanager.SessionConstants;
import user.UserDAO;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.CurrentTimeFactory;
import util.OTPResource;
import util.ServiceDAOFactory;


@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(VerificationServlet.class);
	
	IPRestrictionService ipRestrictionService = ServiceDAOFactory.getService(IPRestrictionService.class);
	VerificationService verificationService = ServiceDAOFactory.getService(VerificationService.class);
	
	String homeUrl = "Semen_dashboardServlet?actionType=getDashboard";
	String otpUrl = "home/otpVerifier.jsp";
	String loginUrl = "home/login.jsp";
	String passwordResetUrl = "forgotPassword/resetPassword.jsp";
	String forgotPasswordUrl = "forgotPassword/forgotPasswordPage.jsp";
	long delayTime = 2 * 60 * 1000;
    public VerificationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = null;
		UserDTO loginUserDTO = null;
		loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		if(loginDTO == null)
		{
			loginDTO = (LoginDTO)request.getSession().getAttribute(VerificationConstants.CACHE_LOGIN_DTO);
			
		}
		else
		{
			loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		}

		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
		if(actionType.equals("verify-email"))
		{
			verifyEmail(loginUserDTO, request, response);
		}
	}

	private void verifyOTP(UserDTO loginUserDTO, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			VerificationDTO verificationDTO = new VerificationDTO();
			verificationDTO.setToken(request.getParameter("token"));
			verificationDTO.setUsername(loginUserDTO.userName);
			verificationDTO.setAuthPurpose(VerificationConstants.VERIFICATION_TYPE_OTP_AT_LOGIN);
			
			VerificationDTO verificationDTO2 = verificationService.verifyToken(verificationDTO);

			if(verificationDTO2 == null)
			{
				new CommonActionStatusDTO().setErrorMessage("OTP Mismatch", false, request);
				response.sendRedirect(otpUrl);
				return;
			}

			String redirectUrl = processSuccessfulOTP(loginUserDTO, request);
			response.sendRedirect(redirectUrl);
			return;
			
		}catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException("Error found in veriying OTP",ex instanceof RequestFailureException?null:ex);
		}
	}
	
	public String  processSuccessfulOTP(UserDTO loginUserDTO, HttpServletRequest request) throws Exception
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute(VerificationConstants.CACHE_LOGIN_DTO);
		request.getSession(true).setAttribute("user_login", loginDTO);
		String forwardTo = (String)request.getSession(true).getAttribute(VerificationConstants.CACHE_FORWARD); 
		removeCacheForOTP(request);
		
		ipRestrictionService.deleteIPRestrictionDTOByIPAndUser(loginDTO.loginSourceIP, loginUserDTO.userName);
        
        CommonActionStatusDTO commonActionStatusDTO = new CommonActionStatusDTO();
		commonActionStatusDTO.setStatusCode( CommonActionStatusDTO.SUCCESS_STATUS_CODE );
		commonActionStatusDTO.setMessage("OTP Verified");
		return forwardTo;		
	}
	
	
	public void removeCacheForOTP(HttpServletRequest request)
	{
		request.getSession().removeAttribute(VerificationConstants.CACHE_FORWARD);
		request.getSession().removeAttribute(VerificationConstants.CACHE_OBJECT);
		request.getSession().removeAttribute(VerificationConstants.CACHE_LOGIN_DTO);
		request.getSession().removeAttribute(VerificationConstants.CACHE_LOGIN_USERNAME);
		request.getSession().removeAttribute(VerificationConstants.CACHE_FORWARD);
		request.getSession().removeAttribute(VerificationConstants.CACHE_PHONE_NUMBER);
		request.getSession().removeAttribute(VerificationConstants.CACHE_EMAIL);
	}
	
	public void resendOTP(UserDTO loginUserDTO, HttpServletRequest request, HttpServletResponse response){
		try
		{
			verificationService.sendOTP(loginUserDTO);
			OTPResource otpResource = verificationService.getOTPResourceByUserDTO(loginUserDTO);
			new CommonActionStatusDTO().setSuccessMessage(verificationService.getOTPSendMessage(otpResource, loginUserDTO), false, request);
			response.sendRedirect(otpUrl);
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException("Error to send OTP",ex instanceof RequestFailureException?null:ex);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		LoginDTO loginDTO = null;
		UserDTO loginUserDTO = null;
		loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		if(loginDTO == null)
		{
			String username = (String)request.getSession().getAttribute(VerificationConstants.CACHE_LOGIN_USERNAME);
			if(username != null) loginUserDTO = UserRepository.getInstance().getUserDTOByUserName(username);			
		}
		else
		{
			loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		}

		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
		if(actionType.equals("verify-otp"))
		{
			verifyOTP(loginUserDTO, request, response);
		}
		else if(actionType.equals("resend-otp"))
		{
			resendOTP(loginUserDTO, request, response);
		}
		else if(actionType.equals("check-user"))
		{
			checkUser(request, response);
		}
		else if(actionType.equals("verify-email"))
		{
			verifyEmail(loginUserDTO, request, response);
		}
		else if(actionType.equals("reset-password"))
		{
			resetPassword(loginUserDTO, request, response);
		}
	}

	private void checkUser(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			UserDTO userDTO = verificationService.checkInputForPasswordRetrieval(request);
			if(userDTO == null)
			{
				new CommonActionStatusDTO().setErrorMessage("User not found with given information", false, request);
				response.sendRedirect(loginUrl);
				return;
			}
			else
			{
				request.getSession().setAttribute(VerificationConstants.CACHE_LOGIN_USERNAME, userDTO.userName);
				
				verificationService.sendVerificationToken(userDTO, request);
				
				OTPResource otpResource = verificationService.getOTPResourceByUserDTO(userDTO);
				new CommonActionStatusDTO().setSuccessMessage(verificationService.getMailVerificationSendMessage(otpResource), false, request);
				request.getRequestDispatcher(passwordResetUrl).forward(request, response);
				return;
			}
		}
		catch(Exception ex)
		{
			logger.fatal("", ex);
			throw new RequestFailureException("Error found in verifying Email",ex instanceof RequestFailureException?null:ex);
		}
	}

	private void resetPassword(UserDTO loginUserDTO, HttpServletRequest request, HttpServletResponse response) 
	{		
		try
		{
			
			String password=StringUtils.trimToEmpty(request.getParameter("password"));
			String rePassword=StringUtils.trimToEmpty(request.getParameter("rePassword"));
			
			
			if(!password.equals(rePassword))
			{
				new CommonActionStatusDTO().setErrorMessage("Confirmation Password doesn't match", false, request);
				request.getRequestDispatcher(passwordResetUrl).forward(request, response);
				return;
			}
			
			String token = request.getParameter("token");
			long id = Long.parseLong(request.getParameter( "id" ));
			
			VerificationDTO verificationDTO = new VerificationDTO();
			verificationDTO.setId(id);
			verificationDTO.setToken(token);
			verificationService.updatePassword(verificationDTO, password);
			
			new CommonActionStatusDTO().setSuccessMessage("Password is updated. You can login with new password", false, request);
			response.sendRedirect(loginUrl);
		}
		catch( Exception e ){
			throw new RequestFailureException("Error found in password reset operation", e  instanceof RequestFailureException?null:e);
		}
	}
	
	private void verifyEmail(UserDTO loginUserDTO, HttpServletRequest request, HttpServletResponse response) 
	{
		try
		{
			String token = request.getParameter( "token" );
			String username = request.getParameter( "username" );
			int authPurpose = Integer.parseInt(request.getParameter("authPurpose"));
			
			VerificationDTO verificationDTO = new VerificationDTO();
			verificationDTO.setUsername(username);
			verificationDTO.setAuthPurpose(authPurpose);
			verificationDTO.setToken(token);
			
			VerificationDTO verificationDTO2 = verificationService.verifyToken(verificationDTO);
			
			if(verificationDTO2 == null)
			{
				new CommonActionStatusDTO().setErrorMessage("Email Verification Failed", false, request);
				response.sendRedirect(loginUrl);
			}
			else
			{
				request.setAttribute("emailVerified", "emailVerified");
				request.setAttribute("id", verificationDTO2.getId());
				request.setAttribute("token", token);
				request.getRequestDispatcher(passwordResetUrl).forward(request, response);				
			}
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException("Error found in verifying Email",ex instanceof RequestFailureException?null:ex);
		}
	}

}
