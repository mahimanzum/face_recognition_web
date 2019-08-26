package login;

import java.io.IOException;
//import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import common.RequestFailureException;
import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
import forgetPassword.VerificationConstants;
//import jdk.nashorn.internal.objects.Global;
import sessionmanager.SessionConstants;
//import sessionmanager.SessionManager;
//import user.UserDAO;
import user.UserDTO;
import user.UserRepository;
import util.ServletConstant;
/**
 * @author Kayesh Parvez
 *
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		
		boolean isDefaultLoginEnabled = (GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1); 
		
		if(!isDefaultLoginEnabled){
			
			if(loginDTO==null){
				request.getRequestDispatcher("/home/login.jsp").forward(request, response);
			}else{
				response.sendRedirect(" ");
			}
			
		}else{
			
			// here logindto will never be  null
			
			long defaultUserID = GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value; 
			
			if(defaultUserID == loginDTO.userID){
				request.getRequestDispatcher("/home/login.jsp").forward(request, response);
			}else{
				response.sendRedirect(request.getContextPath());
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert login credentials

		LoginDTO loginDTOTemp = new LoginDTO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginIP = request.getRemoteAddr();
		loginDTOTemp.loginSourceIP = loginIP;
		logger.debug("userDTO " + loginDTOTemp);
		LoginService service = new LoginService();
		String loginUrl = "home/login.jsp";
		String homeUrl = "Semen_dashboardServlet?actionType=getDashboard";
		String otpUrl = "home/otpVerifier.jsp";
		
		try
		{
		   	request.getSession(true).setAttribute(VerificationConstants.CACHE_LOGIN_DTO, loginDTOTemp);
		   	request.getSession(true).setAttribute(VerificationConstants.CACHE_LOGIN_USERNAME, username);
		   	
			if(!service.capchaMatched(request)) 
			{
				response.sendRedirect(loginUrl);
				return;
			}
			if(service.hasRestriction(loginDTOTemp, username, request))
			{
				response.sendRedirect(loginUrl);
				return;
			}
			LoginDTO loginDTO = service.validateLogin(username, password, loginDTOTemp);
			if(loginDTO == null) 
			{
				service.processInvalidLogin(loginIP, username, request);
				response.sendRedirect(loginUrl);
				return;
			}
			else
			{	
				UserDTO loginUserDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
				if(service.needOTPCheck(loginUserDTO, request)) response.sendRedirect(otpUrl);
				else
				{	//valid login
					if(request.getParameter("stay_logged_in")!=null){
						Cookie cookie = new Cookie(ServletConstant.REMEMBER_ME_COOKIE_NAME, UUID.randomUUID().toString());
						cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
						new RememberMeOptionDAO().insertRememberMeOption(loginDTO.userID, cookie.getValue());
						response.addCookie(cookie);
					}
					
					service.processValidLogin(loginDTO, username, request);
					String lastVisitedUrl = service.getLastRequestedUrl(request);
					if(lastVisitedUrl != null && !lastVisitedUrl.endsWith("LogoutServlet")) response.sendRedirect(lastVisitedUrl);
					else response.sendRedirect(homeUrl);
				}
			}			
		}
		catch(Exception e)
		{
			logger.fatal("Exception during login", e);
			response.sendRedirect(loginUrl);
		}		
	}
}
