package user;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import centre.CentreDAO;
import common.RequestFailureException;
import language.LC;
import language.LM;
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import util.ActionTypeConstant;
import util.JSPConstant;
import util.PasswordUtil;
import util.RecordNavigationManager;
import util.ServletConstant;
import util.StringUtils;


/**
 * Servlet implementation class UserServlet
 */
/**
 * @author Kayesh Parvez
 *
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(UserServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);

		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
		if(actionType.equals(ActionTypeConstant.USER_GET_ADD_PAGE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_ADD)){
				getAddPage(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.USER_GET_EDIT_PAGE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_VIEW)){
				getEditPage(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}

		}
		else if(actionType.equals(ActionTypeConstant.USER_SEARCH))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_SEARCH)){
				searchUser(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}

		}
	}

	private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstant.ROLE_LIST, PermissionRepository.getAllRoles());//make Servlet constant
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPConstant.USER_EDIT);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);		

		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
		if(actionType.equals(ActionTypeConstant.USER_ADD))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_ADD)){
				addUser(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.USER_EDIT))
		{

			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_UPDATE)){
				updateUser(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.USER_DELETE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_DELETE)){
				deleteUser(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}			
		}
		else if(actionType.equals(ActionTypeConstant.USER_SEARCH))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.USER_SEARCH)){
				searchUser(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try {			
			UserDTO userDTO = new UserDTO();		
			userDTO.userName=request.getParameter(ServletConstant.USERNAME);
			userDTO.password= request.getParameter(ServletConstant.PASSWORD);
			userDTO.userType=Integer.parseInt(request.getParameter(ServletConstant.USER_TYPE));
			userDTO.roleID=Integer.parseInt(request.getParameter(ServletConstant.ROLE_NAME));
			userDTO.mailAddress=request.getParameter(ServletConstant.MAIL_ADDRESS);
			userDTO.fullName=request.getParameter(ServletConstant.FULL_NAME);
			userDTO.phoneNo=request.getParameter(ServletConstant.PHONE_NO);
			userDTO.centreType=Integer.parseInt(request.getParameter("CentreType"));
			if(!isUserDTOValid(userDTO, request, response))
			{
				request.getSession().setAttribute(ServletConstant.USER_DTO, userDTO);
				throw new RequestFailureException(LM.getText(LC.USER_ADD_ERROR_USER_ADD, loginDTO));
			}
			userDTO.password= PasswordUtil.getInstance().encrypt(userDTO.password);
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(userDTO);
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LC.USER_ADD_SUCCESS_USER_ADD);
			
			response.sendRedirect(JSPConstant.USER_SEARCH_SERVLET);
		} catch (Exception e) {
			logger.debug("",e);
			throw new RequestFailureException(LM.getText(LC.USER_ADD_ERROR_USER_ADD, loginDTO),e instanceof RequestFailureException?null:e);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try {
			
			String[] IDsToDelete = request.getParameterValues(ServletConstant.ID);
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new UserDAO().deleteUserByUserID(id);
			}		
			
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LC.USER_SEARCH_SUCCESS_USER_DELETE);			
			response.sendRedirect(JSPConstant.USER_SEARCH_SERVLET);			
			
		} catch (Exception ex) {
			logger.debug(ex);
			throw new RequestFailureException(LM.getText(LC.USER_SEARCH_ERROR_USER_DELETE, loginDTO),ex instanceof RequestFailureException?null:ex);
		}
		
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try {
			long userID = Long.parseLong(request.getParameter(ServletConstant.ID));
			UserDTO userDTORepo = UserRepository.getUserDTOByUserID(userID);
			if(userDTORepo == null || userDTORepo.isDeleted)
			{
				throw new RequestFailureException(LM.getText(LC.USER_ADD_ERROR_USER_NOT_FOUND, loginDTO));
			}
			UserDTO userDTO = new UserDTO();
			userDTO.ID = userID;
			userDTO.userName=request.getParameter(ServletConstant.USERNAME);
			userDTO.password=request.getParameter(ServletConstant.PASSWORD);
			userDTO.userType=Integer.parseInt(request.getParameter(ServletConstant.USER_TYPE));
			userDTO.roleID=Integer.parseInt(request.getParameter(ServletConstant.ROLE_NAME));
			userDTO.languageID=Integer.parseInt(request.getParameter(ServletConstant.LANGUAGE_ID));
			userDTO.mailAddress=request.getParameter(ServletConstant.MAIL_ADDRESS);
			userDTO.fullName=request.getParameter(ServletConstant.FULL_NAME);
			userDTO.phoneNo=request.getParameter(ServletConstant.PHONE_NO);
			userDTO.centreType=Integer.parseInt(request.getParameter("CentreType"));
			
			userDTO.otpSMS=request.getParameter("otpSMS") != null;
			userDTO.otpEmail=request.getParameter("otpEmail") != null;
			userDTO.otpPushNotification=request.getParameter("otpPushNotification") != null;
			
			if(!isUserDTOValid(userDTO, request, response))
			{
				request.getSession().setAttribute(ServletConstant.USER_DTO, userDTO);
				throw new RequestFailureException(LM.getText(LC.USER_ADD_ERROR_USER_EDIT, loginDTO));
			}
			if(!userDTO.password.equals(ServletConstant.DEFAULT_PASSWORD))
			{
				userDTO.password = PasswordUtil.getInstance().encrypt(userDTO.password);
			}
			else
			{
				userDTO.password = userDTORepo.password;
			}
			UserDAO userDAO = new UserDAO();
			userDAO.updateUser(userDTO);			
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LC.USER_ADD_SUCCESS_USER_EDIT);
			
			response.sendRedirect(JSPConstant.USER_SEARCH_SERVLET);	
		} catch (Exception e) {
			logger.fatal("",e);
			throw new RequestFailureException(LM.getText(LC.USER_ADD_ERROR_USER_EDIT, loginDTO),e instanceof RequestFailureException?null:e);
		}
				
	}

	private void getEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);		
		try {
			UserDTO userDTO = null;
			userDTO = UserRepository.getUserDTOByUserID(Long.parseLong(request.getParameter(ServletConstant.ID)));
			userDTO.roleName = PermissionRepository.getRoleDTOByRoleID(userDTO.roleID).roleName;
			request.setAttribute(ServletConstant.USER_DTO, userDTO);
			request.setAttribute(ServletConstant.ROLE_LIST, PermissionRepository.getAllRoles());
			RequestDispatcher rd = request.getRequestDispatcher(JSPConstant.USER_EDIT);
			rd.forward(request, response);
		} catch (Exception ex) {
			logger.fatal("",ex);
			throw new RequestFailureException(LM.getText(LC.USER_SEARCH_ERROR_USER_GET, loginDTO),ex instanceof RequestFailureException?null:ex);
		}
		
	}
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
        UserDAO userDAO = new UserDAO();
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_USER, request, userDAO, SessionConstants.VIEW_USER, SessionConstants.SEARCH_USER);
        try
        {
            rnManager.doJob(loginDTO);
    		RequestDispatcher rd = request.getRequestDispatcher(JSPConstant.USER_SEARCH);
    		rd.forward(request, response);
        }
        catch(Exception e)
        {
        	logger.fatal("",e);
        	throw new RequestFailureException(LM.getText(LC.USER_SEARCH_ERROR_USER_SEARCH, loginDTO),e instanceof RequestFailureException?null:e);
        }

	}
	
	private boolean isUserDTOValid(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response)
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		boolean valid = true;
		if(StringUtils.isBlank(userDTO.userName))
		{
			request.getSession().setAttribute(ServletConstant.USERNAME, LM.getText(LC.USER_ADD_ERROR_USERNAME_EMPTY, loginDTO));
			valid = false;
		}
		if(StringUtils.isBlank(userDTO.password))
		{
			request.getSession().setAttribute(ServletConstant.PASSWORD, LM.getText(LC.USER_ADD_ERROR_PASSWORD_EMPTY, loginDTO));
			valid = false;
		}
		else if(userDTO.password.length() < 5)
		{
			request.getSession().setAttribute(ServletConstant.PASSWORD, LM.getText(LC.USER_ADD_ERROR_PASSWORD_LENGTH_SHORT, loginDTO));
			valid = false;
		}
		if(StringUtils.isBlank(userDTO.mailAddress))
		{
			request.getSession().setAttribute(ServletConstant.MAIL_ADDRESS, LM.getText(LC.USER_ADD_ERROR_EMAIL_EMPTY, loginDTO));
			valid = false;
		}	
		return valid;
	}
	
}
