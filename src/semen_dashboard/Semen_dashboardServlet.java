package semen_dashboard;

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
import user.UserTypeDTO;
import user.UserTypeRepository;


@WebServlet("/Semen_dashboardServlet")
public class Semen_dashboardServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(Semen_dashboardServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Semen_dashboardServlet() 
	{
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("In Semen_dashboardServlet");
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		UserTypeDTO userTypeDTO = UserTypeRepository.getInstance().getUserTypeByUserTypeID(userDTO.userType);
		
		try
		{
			String actionType = request.getParameter("actionType");
			if(actionType.equals("getDashboard"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DASHBOARD))
				{
					
					userTypeDTO.dashboard = "Facial_recognizationServlet?actionType=getAddPage";
					//userTypeDTO.dashboard = "Facial_recognizationServlet?actionType=search";
					System.out.println("usertype = " + userDTO.userType);
					System.out.println("user dashboard = " + userTypeDTO.dashboard);
					request.getRequestDispatcher(userTypeDTO.dashboard).forward(request, response);
				}
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}
}
