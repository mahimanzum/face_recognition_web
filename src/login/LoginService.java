package login;
import org.apache.log4j.Logger;

import common.CommonActionStatusDTO;
import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.DatabaseConnection;
import forgetPassword.VerificationService;
import forgetPassword.VerificationConstants;
import forgetPassword.VerificationDTO;
import ipRestriction.IPRestrictionDTO;
import ipRestriction.IPRestrictionService;
import nl.captcha.Captcha;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.CurrentTimeFactory;
import util.OTPResource;
import util.ServiceDAOFactory;

public class LoginService {
	Logger logger = Logger.getLogger(getClass());
	IPRestrictionService ipRestrictionService = ServiceDAOFactory.getService(IPRestrictionService.class);
	VerificationService verificationService = ServiceDAOFactory.getService(VerificationService.class);
	
	public LoginDTO validateLogin(String username, String password, LoginDTO loginDTO) throws Exception {
		LoginDAO dao = new LoginDAO();
		return dao.validateLogin(username, password, loginDTO);
	}	
	
	public boolean capchaMatched(HttpServletRequest request) throws UnsupportedEncodingException
	{
		if(GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.CAPTCHA_IN_LOGIN_ENABLED).value == 1)
        {
        	Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        	request.setCharacterEncoding("UTF-8");		
        	String answer = request.getParameter("answer");
        	logger.debug("captcha " + captcha.getAnswer() + " answer " + answer);		

        	if(!captcha.isCorrect(answer))
        	{
        		new CommonActionStatusDTO().setErrorMessage("Captcha doesn't match", false, request);
        		return false;
        	}
        }
		return true;
	}
	
	public String getLastRequestedUrl(HttpServletRequest request)
	{
        Object lastRequestedUrlObj=request.getSession(true).getAttribute("lastRequestedURL");
        String lastRequestedURL =null;
	    if(lastRequestedUrlObj!=null){
	    	lastRequestedURL=lastRequestedUrlObj.toString();
	    	if(lastRequestedURL.contains("ForgetPassword.do") || lastRequestedURL.contains("terms-and-conditions.jsp") || lastRequestedURL.contains("otpVerifier.jsp") || lastRequestedURL.contains("verify-otp.do")){
	    		lastRequestedURL = null;
	    	}
	    }
	    return lastRequestedURL;
	}
	
    public boolean needOTPCheck(UserDTO loginUserDTO, HttpServletRequest request) throws Exception
    {
    	if(getOTPApplicable(loginUserDTO) == false) return false;
        
    	request.getSession(true).setAttribute(VerificationConstants.CACHE_OBJECT, loginUserDTO);
    	request.getSession(true).setAttribute(VerificationConstants.CACHE_OTP_VERIFICATION_URL, "VerificationServlet?actionType=verify-otp");
    	request.getSession(true).setAttribute(VerificationConstants.CACHE_FORWARD, "home/index.jsp");
    	
		OTPResource otpResource = verificationService.getOTPResourceByUserDTO(loginUserDTO);
		VerificationDTO verificationDTO = new VerificationDTO();
		verificationDTO.setUsername(loginUserDTO.userName);
		verificationDTO.setExpirationTime(CurrentTimeFactory.getCurrentTime() + VerificationConstants.DELAY_TIME_IN_MILLIS);
		verificationDTO.setAuthPurpose(VerificationConstants.VERIFICATION_TYPE_FORGET_PASSWORD);
		
		verificationService.sendOTP(loginUserDTO);
		
		request.getSession(true).setAttribute(VerificationConstants.CACHE_PHONE_NUMBER, otpResource.getPhoneNumber());
    	request.getSession(true).setAttribute(VerificationConstants.CACHE_EMAIL, otpResource.getEmail());
		new CommonActionStatusDTO().setSuccessMessage(verificationService.getOTPSendMessage(otpResource, loginUserDTO), false, request);
		return true;        
    }
    
    public boolean getOTPApplicable(UserDTO loginUserDTO)
    {
    	return GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.OTP_IN_LOGIN_ENABLED_FOR_ADMIN).value == 1;
    } 
    
    public String processInvalidLogin(String ip, String username, HttpServletRequest request) throws Exception
    {
    	logger.info("Invalid Login");    	    
    	addIPHit(ip, username);    	
    	IPRestrictionDTO ipRestrictionDTO = ipRestrictionService.getIPRestrictionDTOByIPAndUser(ip, username);    	
    	
        new CommonActionStatusDTO().setErrorMessage("Invalid Username or Password." + getIPRestrictionMessage(ipRestrictionDTO), false, request);
        return "actionError";
    }
    
    public String processValidLogin(LoginDTO loginDTO, String username, HttpServletRequest request) throws Exception
    {   
    	logger.debug("Processing valid Login " + loginDTO);
    	ipRestrictionService.deleteIPRestrictionDTOByIPAndUser(loginDTO.loginSourceIP, username);
    	HttpSession session = request.getSession(true); 
        session.setAttribute(SessionConstants.USER_LOGIN, loginDTO);
        return "home";
    }

    public void addIPHit(String ip, String username) throws Exception
    {
    	IPRestrictionDTO ipRestrictionDTO = new IPRestrictionDTO();        
    	ipRestrictionDTO.setIP(ip);
    	ipRestrictionDTO.setUsername(username);            	
    	ipRestrictionService.updateOrInsert(ipRestrictionDTO);
    }
    
	public boolean hasRestriction(LoginDTO loginDTO, String username, HttpServletRequest request) throws Exception {
		IPRestrictionDTO ipRestrictionDTO = ipRestrictionService.getIPRestrictionDTOByIPAndUser(loginDTO.loginSourceIP, username);
		if(ipRestrictionDTO != null)
		{					
			if(ipRestrictionDTO.getNextHitTimeAfter() > System.currentTimeMillis())
			{
				addIPHit(loginDTO.loginSourceIP, username);
				new CommonActionStatusDTO().setErrorMessage(getIPRestrictionMessage(ipRestrictionDTO), false, request);
				return true;
			}
		}
		return false;
	}
	
	public String getBlockMessage(IPRestrictionDTO ipRestrictionDTO)
	{
		return "This IP is blocked for unusual hit from this IP "+ ipRestrictionDTO.getIP()+". Please contact with system admin for help.";
	}
	
	public String getDelayMessage(IPRestrictionDTO ipRestrictionDTO)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
		return "System has detected unusual hit from this IP "+ ipRestrictionDTO.getIP()+". Please wait until " + simpleDateFormat.format(ipRestrictionDTO.getNextHitTimeAfter());
	}
	
	public String getIPRestrictionMessage(IPRestrictionDTO ipRestrictionDTO)
	{
		int hitCountToBlock = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.HIT_COUNT_TO_BLOCK_LOGIN).value;
		int hitCountToDelay = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.HIT_COUNT_TO_START_DELAY_LOGIN).value;
		if(ipRestrictionDTO.getNextHitTimeAfter() == Long.MAX_VALUE)
		{
			return getBlockMessage(ipRestrictionDTO);
		}
		else if(ipRestrictionDTO.getHitCount() >= hitCountToDelay)
		{
			return getDelayMessage(ipRestrictionDTO);
		}
		
		return "You have failed " + ipRestrictionDTO.getHitCount() + " times to login the system.";
		
	}
}