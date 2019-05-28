package template;

import java.io.IOException;
import java.io.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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




import pb.*;

/**
 * Servlet implementation class Bull_breed_centreServlet
 */
@WebServlet("/TemplateServlet")
@MultipartConfig
public class TemplateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(TemplateServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemplateServlet() 
	{
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("In doget request = " + request);
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		try
		{
			String actionType = request.getParameter("actionType");
			if(actionType.equals("getGRSStartPage"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.TEMPLATES))
				{
					getTemplate(request, response,"GRSStartPage");
					System.out.println("in getGRSStartPage");
				}
				else
				{
					request.getRequestDispatcher(null).forward(request, response);
				}
			}
			else if(actionType.equals("getViewGrievances"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.TEMPLATES))
				{
					getTemplate(request, response,"ViewGrievances");
					System.out.println("in getGRSStartPage");
				}
				else
				{
					request.getRequestDispatcher(null).forward(request, response);
				}
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}

	private void getTemplate(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException 
	{
		request.setAttribute("ID", -1L);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("templates/" + name + ".jsp");
		requestDispatcher.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		System.out.println("doPost");
		
		try
		{
			String actionType = request.getParameter("actionType");
			System.out.println("actionType = " + actionType);
			/*if(actionType.equals("add"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_ADD))
				{
					System.out.println("going to  addBull_breed_centre ");
					addBull_breed_centre(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addBull_breed_centre ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_UPDATE))
				{
					addBull_breed_centre(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteBull_breed_centre(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_SEARCH))
				{
					searchBull_breed_centre(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("getGeo"))
			{
				System.out.println("going to geoloc ");
				request.getRequestDispatcher("geolocation/geoloc.jsp").forward(request, response);
			}
			else if(actionType.equals("getGRSLayer"))
			{
				System.out.println("getting GRS Layer");

				int layernum = Integer.parseInt(request.getParameter("layernum"));
				int selectedValue = Integer.parseInt(request.getParameter("selectedValue"));
				List<GRS_OFFICE_DTO> GRS_DTO_List = GRS_OFFICE_DAO.getGRS_DTO(layernum, selectedValue);
				request.setAttribute("GRS_DTO_List", GRS_DTO_List);
				request.getRequestDispatcher("pb/grs_layer.jsp").forward(request, response);
			}
			else if(actionType.equals("getGRSOffice"))
			{
				System.out.println("getting GRS Office");

				long officerID = Long.parseLong(request.getParameter("officer_id"));
				List<GRS_OFFICER_DTO> GRS_DTO_List = GRS_OFFICER_DAO.getGRS_Officer_DTO(officerID);
				request.setAttribute("GRS_OFFICER_DTO_List", GRS_DTO_List);
				request.getRequestDispatcher("pb/grs_officer.jsp").forward(request, response);
			}*/
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}

	
	
	
	


	
}

