package forgetPassword;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import annotation.Transactional;
import common.CommonActionStatusDTO;
import common.RequestFailureException;
import language.LC;
import language.LM;
import login.LoginDTO;
import mail.MailDTO;
import media.Notifier;
import media.email.EmailConstant;
import media.pushNotification.PushNotificationConstant;
import media.sms.SMSConstant;
import user.UserDAO;
import user.UserDTO;
import user.UserRepository;
import util.OTPResource;
import util.PasswordUtil;
import util.TokenGenerator;


public class VerificationService {

	public static Logger logger = Logger.getLogger(VerificationService.class); 
	Notifier notifier = new Notifier();
	UserDAO userDAO = new UserDAO();
	VerificationDAO verificationDAO = new VerificationDAO();
	
	@Transactional
	public void sendOTP(UserDTO loginUserDTO) throws Exception
	{
		VerificationDTO verificationDTO = getVerificationDTO(loginUserDTO, VerificationConstants.VERIFICATION_TYPE_OTP_AT_LOGIN, TokenGenerator.getOTP());
		verificationDAO.deleteVerificationDTO(loginUserDTO.userName, VerificationConstants.VERIFICATION_TYPE_OTP_AT_LOGIN);
		verificationDAO.add(verificationDTO);
    	sendVerificationOTP(verificationDTO, loginUserDTO);    
	}
	
	public VerificationDTO getVerificationDTO(UserDTO userDTO, int authPurpose, String token)
	{
		long currentTime = System.currentTimeMillis();
		VerificationDTO verificationDTO = new VerificationDTO();
		verificationDTO.setToken(token);
		verificationDTO.setUsername(userDTO.userName);
		verificationDTO.setLastModificationTime(currentTime);
		verificationDTO.setExpirationTime(currentTime + VerificationConstants.DELAY_TIME_IN_MILLIS);
		verificationDTO.setAuthPurpose(authPurpose);
		return verificationDTO;
	}
	
	private void sendVerificationOTP(VerificationDTO verificationDTO, UserDTO userDTO) throws Exception
	{
		OTPResource otpResource = getOTPResourceByUserDTO(userDTO);
		otpResource.setToken(verificationDTO.getToken());
		sendOTP(otpResource);		
	}	
	
	
	public OTPResource getOTPResourceByLoginDTO(LoginDTO loginDTO) throws Exception{
		return getOTPREsourceByUserID(loginDTO.userID);
	}
	
	
	private OTPResource getOTPResourceByClientID(long clientID) throws Exception {		
		OTPResource otpResource = new OTPResource();
		otpResource.setPhoneNumber(null);
		otpResource.setEmail(null);
		return otpResource;
	}
	
	private OTPResource getOTPREsourceByUserID(long userID) {
		UserDTO userDTO = UserRepository.getUserDTOByUserID(userID);
		return getOTPResourceByUserDTO(userDTO);
	}
	
	public OTPResource getOTPResourceByUserDTO(UserDTO userDTO)
	{
		OTPResource otpResource = new OTPResource();
		if(userDTO.otpEmail)otpResource.setEmail(userDTO.mailAddress);
		if(userDTO.otpSMS)otpResource.setPhoneNumber(userDTO.phoneNo);
		return otpResource;
	}

	public void sendOTP(OTPResource otpResource)
	{
		sendMail(otpResource.getEmail(), "OTP from System", "OTP: " + otpResource.getToken());
		sendSMS(otpResource.getPhoneNumber(), otpResource.getToken());
		
	}
	
	public void sendVerificationCode(String email, String phoneNumber, String token)
	{
		OTPResource otpResource = new OTPResource();
		otpResource.setEmail(email);
		otpResource.setPhoneNumber(phoneNumber);
		otpResource.setToken(token);
		sendOTP(otpResource);
	}

	public String getOTPSendMessage(OTPResource otpResource, UserDTO userDTO)
	{
		String phoneNumber = otpResource.getPhoneNumber();
		String email = otpResource.getEmail();
//		String message = "6 Digit OTP is sent to ";//<br> mobile number: " + phoneNumber + " <br> and email " + email;
		String message = LM.getText(LC.GLOBAL_OTP_IS_SENT_TO, userDTO);
		try
		{
			String phoneNumberEnd = phoneNumber.substring(phoneNumber.length() - 3, phoneNumber.length());
			phoneNumber = "*********" + phoneNumberEnd;
			message += "<br> " + LM.getText(LC.GLOBAL_MOBILE_NUMBER, userDTO) + ": " + phoneNumber;
		}
		catch(Exception ex)
		{
		}
		try
		{
			String emailStarting = email.substring(0, 2);
			String emailEnd = email.substring(email.indexOf("@") + 1);
			email = emailStarting + "*****@" + emailEnd;
			message += "<br> " + LM.getText(LC.GLOBAL_EMAIL, userDTO) + ": " + email;
		}
		catch(Exception ex)
		{			
		}
		return message;
	}
	
	public String getMailVerificationSendMessage(OTPResource otpResource)
	{
		String email = otpResource.getEmail();
		try
		{			
			String emailStarting = email.substring(0, 2);
			String emailEnd = email.substring(email.indexOf("@") + 1);
			email = emailStarting + "*****@" + emailEnd;
		}
		catch(Exception ex)
		{
			return "";
		}
		String message = "An Email verifcation link is sent to email " + email;
		return message;
	}
	
	private void sendPushNotification(String to, String msg, int OSType, String deviceToken)
	{
		try
		{
			notifier.sendPushNotification(to, PushNotificationConstant.RECEIVER_SYSTEM_USER, deviceToken, OSType, "OTP from BTCL", msg);
			logger.debug("push-notification pushed successfully...");
		}
		catch(Exception ex)
		{
			logger.fatal("", ex);
		}
	}
	
	private void sendMail(String to, String subject, String msg)
	{
		try{
			if(to == null) return;
			notifier.sendEmail(to, EmailConstant.RECEIVER_SYSTEM_USER,"",subject, msg);
			logger.debug("mail pushed successfully...");   
		}catch(Exception ex){
			logger.fatal("", ex);
		}
	}
	
	
	private void sendSMS(String to, String msg)
	{		
		try{
			if(to == null) return;
			notifier.sendSMS(to, SMSConstant.RECEIVER_SYSTEM_USER, msg);
			logger.debug("sms pushed successfully...");   
		}catch(Exception ex){
			logger.fatal("", ex);
		}
	}	
	
	@Transactional
	public void addVerification(VerificationDTO verificationDTO) throws Exception
	{
		verificationDAO.add(verificationDTO);
	}
	
	@Transactional
	public List<VerificationDTO> getVerificationDTOsAfterCurrentTime(String username,int authPurpose) throws Exception
	{
		return verificationDAO.getVerificationDTOsAfterCurrentTime(username, authPurpose);
	}
	
	@Transactional
	public VerificationDTO verifyToken(VerificationDTO verificationDTO) throws Exception 
	{				
		List<VerificationDTO> verificationList =  verificationDAO.getVerificationDTOsAfterCurrentTime(verificationDTO.getUsername(), verificationDTO.getAuthPurpose());
		
		if(verificationList.size() == 0) return null;
		if(verificationList.size() > 1) throw new RequestFailureException("Multiple token found");
		if(!verificationList.get(0).getToken().equals(verificationDTO.getToken())) return null;
		return verificationList.get(0);	
	}
	
	@Transactional
	public UserDTO checkInputForPasswordRetrieval(HttpServletRequest request)
	{		
		String username = request.getParameter("username");	
		int verificationType = Integer.parseInt(request.getParameter("verificationType"));
		UserDTO userDTO = null;
		if(verificationType == 1)
		{
			userDTO = UserRepository.getUserDTOByUserName(username);
			if(userDTO == null)
			{
				new CommonActionStatusDTO().setErrorMessage("User not found with given username", false, request);
				return null;				
			}
		}
		else
		{			
			UserDAO userDAO = new UserDAO();
			userDTO =  userDAO.getUserDTOByMail(username);
			if(userDTO == null)
			{
				new CommonActionStatusDTO().setErrorMessage("User not found with given mail address", false, request);
				return null;
			}
		}
		return userDTO;
	}

	private MailDTO getMailDTOForEmailVerification(VerificationDTO verificationDTO, HttpServletRequest request) throws Exception
	{
		UserDTO userDTO = UserRepository.getUserDTOByUserName(verificationDTO.getUsername());
		MailDTO mailDTO = new MailDTO();
		String systemURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String verificationUrl = systemURL + "/VerificationServlet?actionType=verify-email&token=" + verificationDTO.getToken() +	"&username=" + verificationDTO.getUsername() + "&authPurpose=" + verificationDTO.getAuthPurpose();
		mailDTO.toList = userDTO.mailAddress;		
		mailDTO.mailSubject = "Password reset link for BTCL";
		mailDTO.msgText = "To reset your password for username '" + userDTO.userName + 
						"' please visit this link \n\n" + verificationUrl +
						"\n\nIf you didn't request for anything like this, please ignore the message. " + 
						"Your password won't be changed.\n\n" +
						"This link will become inactive after " + VerificationConstants.DELAY_TIME_IN_MINUTES + " minutes\n\n" +
						"Don't reply to this mail. It's an auto generated mail";
		return mailDTO;
	}
	
	@Transactional
	public void sendVerificationToken(UserDTO userDTO, HttpServletRequest request) throws Exception 
	{		
		VerificationDTO verificationDTO = getVerificationDTO(userDTO, VerificationConstants.VERIFICATION_TYPE_FORGET_PASSWORD, TokenGenerator.getDoubleEncryptedToken());
		verificationDAO.deleteVerificationDTO(verificationDTO.getUsername(), verificationDTO.getAuthPurpose());
		verificationDAO.add(verificationDTO);
		MailDTO mailDTO = getMailDTOForEmailVerification(verificationDTO, request);
		
		notifier.sendEmail(mailDTO.toList, EmailConstant.RECEIVER_SYSTEM_USER, "", mailDTO.mailSubject, mailDTO.msgText);
	}

	@Transactional
	public VerificationDTO updatePassword(VerificationDTO verificationDTO, String password) throws Exception {
		VerificationDTO verificationDTODB = verificationDAO.getVerificationDTOByTokenID(verificationDTO.getId());
		if(verificationDTODB == null) return null;
		if(!verificationDTODB.getToken().equals(verificationDTO.getToken())) return null;
		UserDTO userDTO =  UserRepository.getUserDTOByUserName(verificationDTODB.getUsername());
		if(userDTO == null) return null;
		userDTO.password = PasswordUtil.getInstance().encrypt(password);
		userDAO.updateUser(userDTO);
		return verificationDTODB;
		//verificationDAO.updateVerificationDTO(verificationDTODB);
	}
}
