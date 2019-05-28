package role;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import common.RequestFailureException;
import language.LC;
import language.LM;
import login.LoginDTO;
import permission.ColumnPermissionDTO;
import permission.MenuConstants;
import permission.MenuDTO;
import permission.MenuRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.ActionTypeConstant;
import util.CollectionUtils;
import util.Converter;
import util.JSPConstant;
import util.RecordNavigationManager;
import util.ServletConstant;
import util.StringUtils;

import java.util.*;

@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet{
	Logger logger = Logger.getLogger(RoleServlet.class);
	private static final long serialVersionUID = 1L;
	
	RoleDAO roleDAO = new RoleDAO();
	MenuPermissionDAO menuPermissionDAO = new MenuPermissionDAO();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);

		
		
		if(actionType.equals(ActionTypeConstant.ROLE_GET_ADD_PAGE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_ADD)){
				getAddPage(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.ROLE_GET_EDIT_PAGE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_VIEW)){
				getEditPage(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}					
		else if(actionType.equals(ActionTypeConstant.ROLE_SEARCH))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_SEARCH)){
				searchRoles(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		
	}

	private void searchRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );		
		RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_ROLE, request, roleDAO, SessionConstants.VIEW_ROLE, SessionConstants.SEARCH_ROLE);
        try
        {
            rnManager.doJob(loginDTO);
    		RequestDispatcher rd = request.getRequestDispatcher(JSPConstant.ROLE_SEARCH);
    		rd.forward(request, response);
        }
        catch(Exception e)
        {
			logger.debug("fatal",e);
			throw new RequestFailureException(LM.getText(LC.ROLE_SEARCH_ERROR_ROLE_SEARCH, loginDTO));
        }

	}

	private void getEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try
		{			
			String roleIDString = request.getParameter(ServletConstant.ID);
			long roleID = Long.parseLong(roleIDString);
			
			RoleDTO roleDTORepo = PermissionRepository.getRoleDTOByRoleID(roleID);
			if(roleDTORepo == null){
				throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_NOT_FOUND, loginDTO));
			}
			request.setAttribute(ServletConstant.ROLE_DTO, roleDTORepo);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPConstant.ROLE_EDIT);
			requestDispatcher.forward(request, response);
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
			throw new RequestFailureException(LM.getText(LC.ROLE_SEARCH_ERROR_ROLE_GET, loginDTO));
		}
	}

	private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPConstant.ROLE_EDIT);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		String actionType = request.getParameter(ActionTypeConstant.ACTION_TYPE);
		

		if(actionType.equals(ActionTypeConstant.ROLE_ADD))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_ADD)){
				addRole(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.ROLE_EDIT))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_EDIT)){
				updateRole(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.ROLE_DELETE))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_DELETE)){
				deleteRole(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
		else if(actionType.equals(ActionTypeConstant.ROLE_SEARCH))
		{
			if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ROLE_SEARCH)){
				searchRoles(request, response);
			}else{
				request.getRequestDispatcher(JSPConstant.ERROR_GLOBAL).forward(request, response);
			}
		}
			
	}

	private void deleteRole(HttpServletRequest request, HttpServletResponse response) {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try {
			String idStr[] =request.getParameterValues(ServletConstant.ID);
			long[] ids = Converter.StringArrayToLongArray(idStr);						
			new RoleDAO().dropRoles(ids);			
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LM.getText(LC.ROLE_SEARCH_SUCCESS_ROLE_DELETE,loginDTO));
			
			response.sendRedirect(JSPConstant.ROLE_SEARCH_SERVLET);
		} catch (Exception ex) {
			logger.debug("fatal",ex);
			throw new RequestFailureException(LM.getText(LC.ROLE_SEARCH_ERROR_ROLE_DELETE, loginDTO));
		}
	}
	
	
	private List<Integer> getMenuIDList(HttpServletRequest request){
		String[] menuIDStrings = request.getParameterValues(ServletConstant.MENU_ID);
		List<Integer> permittedMenuIDList = new ArrayList<>();
		if(menuIDStrings!=null){
			for(String permittedMenuIDString: menuIDStrings){
				permittedMenuIDList.add(Integer.parseInt(permittedMenuIDString));
			}
		}
		return permittedMenuIDList;
	}
	
	private void addRole(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try
		{
			RoleDTO roleDTO = new RoleDTO();		
			roleDTO.roleName = request.getParameter(ServletConstant.ROLE_NAME);
			roleDTO.description = request.getParameter(ServletConstant.DESCRIPTION);
			
			if(!isRoleDTOValid(roleDTO, request, response))
			{
				request.getSession().setAttribute(ServletConstant.ROLE_DTO, roleDTO);
				throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_ADD, loginDTO));
			}
			
			roleDAO.addRole(roleDTO);
			List<Integer> menuIDList = getMenuIDList(request);
			menuIDList = filterPermissionByHierarchy(menuIDList);
			menuPermissionDAO.updateMenuPermission(roleDTO.ID, menuIDList);

			String[] columnIDStrings = request.getParameterValues(ServletConstant.COLUMN_ID);
			List<Integer> columnIDs = CollectionUtils.convertToList(Integer.class, columnIDStrings);
			menuPermissionDAO.updateColumnPermission(roleDTO.ID, columnIDs);
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LM.getText(LC.ROLE_ADD_SUCCESS_ROLE_ADD, loginDTO));
			
			response.sendRedirect(JSPConstant.ROLE_SEARCH_SERVLET);
		}
		catch(Exception ex)
		{
			logger.debug("fatal",ex);
			throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_ADD, loginDTO));
		}
		
	}
	
	
	private List<Integer> filterPermissionByHierarchy(List<Integer> menuIDList){
		List<Integer> filteredList = new ArrayList<>();
		Set<Integer> menuIDSet = new HashSet<>();
		for(int menuID: menuIDList){
			menuIDSet.add(menuID);
		}
		
		for(MenuDTO menuDTO: MenuRepository.getPreorderedMenuList()){
			
			if(menuIDSet.contains(menuDTO.getMenuID())){
				if(menuDTO.getParentMenuID()==-1){
					filteredList.add(menuDTO.getMenuID());
				}else if(menuIDSet.contains(menuDTO.getParentMenuID())){
					filteredList.add(menuDTO.getMenuID());
				}else{
					menuIDSet.remove(menuDTO.getMenuID());
				}
				
			}
		}
		
		return filteredList;
	} 
	
	
	private void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		try
		{
			long roleID = Long.parseLong(request.getParameter(ServletConstant.ID));
			String roleNameString = request.getParameter(ServletConstant.ROLE_NAME);
			String description = request.getParameter(ServletConstant.DESCRIPTION);
			
			RoleDTO roleDTORepo = PermissionRepository.getRoleDTOByRoleID(roleID);
			
			if(roleDTORepo == null){
				throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_NOT_FOUND, loginDTO));
			}
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.ID = roleID;
			roleDTO.roleName = roleNameString;
			roleDTO.description = description;
			
			if(!isRoleDTOValid(roleDTO, request, response))
			{
				request.getSession().setAttribute(ServletConstant.ROLE_DTO, roleDTO);
				throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_EDIT, loginDTO));
			}
			
			String[] menuIDStrings = request.getParameterValues(ServletConstant.MENU_ID);
			String[] columnIDStrings = request.getParameterValues(ServletConstant.COLUMN_ID);			
			
			List<Integer> menuIDs = CollectionUtils.convertToList(Integer.class, menuIDStrings);
			menuIDs = filterPermissionByHierarchy(menuIDs);
			List<Integer> columnIDs = CollectionUtils.convertToList(Integer.class, columnIDStrings);

			roleDAO.updateRoleDTO(roleDTO);
			menuPermissionDAO.updateMenuPermission(roleID, menuIDs);
			menuPermissionDAO.updateColumnPermission(roleID, columnIDs);

			PermissionRepository.reload();
			
			request.getSession().setAttribute(ServletConstant.SUCCESSFUL_MSG, LC.ROLE_ADD_SUCCESS_ROLE_EDIT);
			
			response.sendRedirect(JSPConstant.ROLE_SEARCH_SERVLET);
		}
 		catch(Exception ex)
		{
 			logger.debug("fatal",ex);
 			throw new RequestFailureException(LM.getText(LC.ROLE_ADD_ERROR_ROLE_EDIT, loginDTO));
		}
		
		
	}
	
	private boolean isRoleDTOValid(RoleDTO roleDTO, HttpServletRequest request, HttpServletResponse response)
	{
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		boolean valid = true;
		if(StringUtils.isBlank(roleDTO.roleName))
		{
			request.getSession().setAttribute(ServletConstant.ROLE_NAME, LM.getText(LC.ROLE_ADD_ERROR_ROLENAME_EMPTY, loginDTO));
			valid = false;
		}		
		return valid;
	}
}
