package language;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import login.LoginDTO;
import permission.MenuConstants;
import permission.MenuDTO;
import permission.MenuRepository;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.Converter;
import util.JSPConstant;
import util.RecordNavigationManager;
import util.ServletConstant;
import util.StringUtils;

/**
 * @author Kayesh Parvez
 *
 */

@WebServlet("/LanguageServlet")
public class LanguageServlet extends HttpServlet {
	Logger logger = Logger.getLogger(LanguageServlet.class);
	private static final long serialVersionUID = 1L;
    
    public LanguageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		try
		{
			String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);

			if(actionType.equals(ActionTypeConstant.LANGUAGE_SEARCH))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LANGUAGE_SETTINGS_VIEW)){
					searchLanguage(request, response);
				}else{
					request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
				}
			}
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException("Error processing language request");
		}
	}

	private void searchLanguage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		LanguageDAO languageDAO = new LanguageDAO();
		RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_LANGUAGE, request, languageDAO, SessionConstants.VIEW_LANGUAGE, SessionConstants.SEARCH_LANGUAGE);
        try
        {
            rnManager.doJob(loginDTO);
    		RequestDispatcher rd = request.getRequestDispatcher(JSPConstant.LANGUAGE_SEARCH);
    		rd.forward(request, response);				
        }
        catch(Exception e)
        {
            logger.fatal("",e);
            throw new RequestFailureException("Error found while searching language texts");
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		try
		{
			String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
			if(actionType.equals(ActionTypeConstant.LANGUAGE_ADD))
			{

				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LANGUAGE_TEXT_ADD)){
					addLanguageText(request, response);
				}else{
					request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
				}
			}		
			else if(actionType.equals(ActionTypeConstant.LANGUAGE_EDIT))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LANGUAGE_TEXT_EDIT)){
					updateLanguage(request, response);
					
				}else{
					request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
				}
			}
			else if(actionType.equals(ActionTypeConstant.LANGUAGE_SEARCH))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LANGUAGE_SETTINGS_VIEW)){
					searchLanguage(request, response);
				}else{
					request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
				}
			}
		}
		catch(Exception ex)
		{			
			logger.fatal("",ex);
			throw new RequestFailureException("Error processing language request");
		}
	}

	private void addLanguageText(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try
		{
			LanguageDAO languageDAO = new LanguageDAO();
			
			LanguageTextDTO languageTextDTO = new LanguageTextDTO();
			languageTextDTO.menuID=Long.parseLong(request.getParameter(ServletConstant.MENU_ID));
			languageTextDTO.languageTextEnglish=request.getParameter(ServletConstant.LANGUAGE_TEXT_ENGLISH);
			languageTextDTO.languageTextBangla=request.getParameter(ServletConstant.LANGUAGE_TEXT_BANGLA);
			languageTextDTO.languageConstantPrefix=request.getParameter(ServletConstant.LANGUAGE_CONSTANT_PREFIX);
			languageTextDTO.languageConstant=request.getParameter(ServletConstant.LANGUAGE_CONSTANT);
			
			languageDAO.addLanguageText(languageTextDTO);
		
			request.setAttribute(ServletConstant.LANGUAGE_TEXT_MENU_SELECTED, ""+languageTextDTO.menuID);
			request.setAttribute(ServletConstant.LANGUAGE_CONSTANT_PREFIX_SELECTED, ""+languageTextDTO.languageConstantPrefix);
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, "Language text successfully created.");
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPConstant.LANGUAGE_SEARCH_SERVLET);
			requestDispatcher.forward(request, response);
		}
		catch(Exception ex)
		{
			logger.debug(""+ex);
			throw new RequestFailureException("Error found in adding language text");
		}
	}


	private void updateLanguage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			LanguageDAO languageDAO = new LanguageDAO();
			long languageTextID [] = Converter.StringArrayToLongArray(request.getParameterValues(ServletConstant.ID));
			long menuID [] = Converter.StringArrayToLongArray(request.getParameterValues(ServletConstant.MENU_ID));
			String languageTextEnglish[] = request.getParameterValues(ServletConstant.LANGUAGE_TEXT_ENGLISH);
			String languageTextBangla[] = request.getParameterValues(ServletConstant.LANGUAGE_TEXT_BANGLA);
			String languageTextConstantPrefix[] = request.getParameterValues(ServletConstant.LANGUAGE_CONSTANT_PREFIX);
			String languageTextConstant[] = request.getParameterValues(ServletConstant.LANGUAGE_CONSTANT);
			String deleteList[] = request.getParameterValues(ServletConstant.DELETE_ID);
			
			ArrayList<LanguageTextDTO> languageTextDTOList = new ArrayList<>();

			for( int i=0;i< languageTextID.length;i++)
			{				
				LanguageTextDTO languageTextDTO = new LanguageTextDTO();
				try{
					languageTextDTO.ID = languageTextID[i];
					languageTextDTO.menuID = menuID[i];
					languageTextDTO.languageTextEnglish = languageTextEnglish[i];
					languageTextDTO.languageTextBangla = languageTextBangla[i];
					languageTextDTO.languageConstant = languageTextConstant[i];
					languageTextDTO.languageConstantPrefix = languageTextConstantPrefix[i];
				}catch(Exception ex){
					logger.debug("",ex);
				}
				languageTextDTOList.add(languageTextDTO);
			}
			
			languageDAO.updateLanguage(languageTextDTOList);
			languageDAO.dropLanguages(deleteList);
			LM.getInstance().reload(false);
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, "Language text successfully updated.");
			
			

			if(!"null".equals(request.getParameter("backLinkEnabled"))){
				String menuIDString = request.getParameter("backLinkEnabled");
				try{
					int backMenuID = Integer.parseInt(menuIDString);
					MenuDTO menuDTO = MenuRepository.getMenuDTOByMenuID(backMenuID);
					if(menuDTO!=null){
						response.sendRedirect(menuDTO.hyperLink);
					}else{
						response.sendRedirect(JSPConstant.LANGUAGE_SEARCH_SERVLET);
					}
					return;
				}catch(Exception ex){}
			}
			
			
			response.sendRedirect(JSPConstant.LANGUAGE_SEARCH_SERVLET);
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException("Error in updating language text");
		}
		
		
	}

}
