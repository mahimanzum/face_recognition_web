package language;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
//import jdk.nashorn.internal.runtime.GlobalConstants;
import login.LoginDTO;
import sessionmanager.SessionConstants;
import user.UserDAO;
import user.UserDTO;
import user.UserRepository;
import util.CommonConstant;

@WebServlet("/languageChangeServlet")
public class LanguageChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(LanguageChangeServlet.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		
		boolean isDefaultLoginEnabled = (GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1);
		
		
		
		
		if(loginDTO!=null){
			
			
			if(isDefaultLoginEnabled){
				trogglePreferredLanguage(loginDTO.userID, request);
			}
			
			UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
			userDTO.languageID = (userDTO.languageID == 1?2:1);
			try{
				new UserDAO().updateUser(userDTO);
			}catch(Exception ex){
				if(ex instanceof RequestFailureException){
					throw (RequestFailureException)ex;
				}
			}
		}
		
		String reffer = request.getHeader("referer");
		if(reffer!=null){
			response.sendRedirect(reffer);
		}else{
			response.sendRedirect("");
		}
	}
	
	
	private void trogglePreferredLanguage(long userID, HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		Integer preferredLanguageID = (Integer)session.getAttribute(SessionConstants.PREFERRED_LANGUAGE);
		session.removeAttribute(SessionConstants.PREFERRED_LANGUAGE);
		session.setAttribute(SessionConstants.PREFERRED_LANGUAGE, Objects.equals(CommonConstant.Language_ID_English , preferredLanguageID)?CommonConstant.Language_ID_Bangla: CommonConstant.Language_ID_English);
		
	}


}
