package theme;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;


import sessionmanager.SessionConstants;

import user.UserDTO;
import user.UserRepository;

import util.RecordNavigationManager;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.UUID;

import theme.Constants;


@WebServlet("/ThemeServlet")
public class ThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(ThemeServlet.class);
    
	ThemeDAO themeDAO = new ThemeDAO();
	
	public ThemeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doget request = " + request);
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		try
		{
			String actionType = request.getParameter("actionType");
			if(actionType.equals("getAddPage"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_ADD)){
					getAddPage(request, response);
				}else{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("getEditPage"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_UPDATE)){
					getTheme(request, response);
				}else{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("search"))
			{
				System.out.println("search requested");
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_SEARCH)){
					searchTheme(request, response);
				}else{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}

	private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ID", -1L);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("theme/themeEdit.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		System.out.println("doPost");
		
		try
		{
			String actionType = request.getParameter("actionType");
			System.out.println("actionType = " + actionType);
			if(actionType.equals("add"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_ADD))
				{
					System.out.println("going to  addTheme ");
					addTheme(request, response);
				}
				else
				{
					System.out.println("Not going to  addTheme ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_UPDATE)){
					editTheme(request, response);
				}else{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteTheme(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.THEME_SEARCH)){
					searchTheme(request, response);
				}else{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}
	
	private void editTheme(HttpServletRequest request, HttpServletResponse response ) throws Exception{
		ThemeDTO themeDTO = createThemeDTO(request);
		themeDAO.updateTheme(themeDTO);
		response.sendRedirect("ThemeServlet?actionType=search");
	}
	
	
	private ThemeDTO createThemeDTO(HttpServletRequest request) throws Exception{
		ThemeDTO themeDTO = new ThemeDTO();
		String IDString = request.getParameter("iD");
		if(IDString!=null){
			themeDTO.iD = Long.parseLong(IDString);
		}
		themeDTO.directory = request.getParameter("directory");
		themeDTO.themeName = request.getParameter("themeName");
		if(request.getParameter("isApplied")!=null){
			themeDTO.isApplied = true;
		}
		return themeDTO;
	}

	private void addTheme(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		ThemeDTO themeDTO = createThemeDTO(request);
		themeDAO.addTheme(themeDTO);
		
		response.sendRedirect("ThemeServlet?actionType=search");
	}

	private void deleteTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {				
		try {
			long id = Long.parseLong(request.getParameter("ID"));		
			new ThemeDAO().deleteThemeByID(id);			
		} catch (Exception ex) {
			logger.debug(ex);
		}
		response.sendRedirect("ThemeServlet?actionType=search");
	}

	private void getTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ThemeDTO themeDTO = null;
		try {
			themeDTO = new ThemeDAO().getThemeDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", themeDTO.iD);
			request.setAttribute("themeDTO",themeDTO);
			
			RequestDispatcher rd = request.getRequestDispatcher("theme/themeEdit.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchTheme 1");
        ThemeDAO themeDAO = new ThemeDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_THEME, request, themeDAO, SessionConstants.VIEW_THEME, SessionConstants.SEARCH_THEME);
        try
        {
            rnManager.doJob(loginDTO);
        }
        catch(Exception e)
        {
			logger.fatal("",e);
        }

        RequestDispatcher rd;
        if(hasAjax == false)
        {
        	rd = request.getRequestDispatcher("theme/themeSearch.jsp");
        }
        else
        {
        	rd = request.getRequestDispatcher("theme/themeSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

