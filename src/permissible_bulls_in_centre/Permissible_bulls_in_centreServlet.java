package permissible_bulls_in_centre;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bull.BullDAO;
import centre.CentreDAO;
import login.LoginDTO;
import pbReport.DateUtils;
import pb_notifications.Pb_notificationsDAO;
import permission.MenuConstants;
import role.PermissionRepository;


import sessionmanager.SessionConstants;

import user.UserDTO;
import user.UserRepository;

import util.RecordNavigationManager;

import java.io.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.UUID;

import permissible_bulls_in_centre.Constants;




/**
 * Servlet implementation class Permissible_bulls_in_centreServlet
 */
@WebServlet("/Permissible_bulls_in_centreServlet")
@MultipartConfig
public class Permissible_bulls_in_centreServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Permissible_bulls_in_centreServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Permissible_bulls_in_centreServlet() 
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
			if(actionType.equals("getAddPage"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_ADD))
				{
					getAddPage(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("getEditPage"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_UPDATE))
				{
					getPermissible_bulls_in_centre(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("search"))
			{
				System.out.println("search requested");
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH))
				{
					searchPermissible_bulls_in_centre(request, response);
				}
				else
				{
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

	private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("ID", -1L);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("permissible_bulls_in_centre/permissible_bulls_in_centreEdit.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private String getFileName(final Part part) 
	{
	    final String partHeader = part.getHeader("content-disposition");
	    System.out.println("Part Header = {0}" +  partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
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
			if(actionType.equals("add"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_ADD))
				{
					System.out.println("going to  addPermissible_bulls_in_centre ");
					addPermissible_bulls_in_centre(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addPermissible_bulls_in_centre ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_UPDATE))
				{
					addPermissible_bulls_in_centre(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deletePermissible_bulls_in_centre(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH))
				{
					searchPermissible_bulls_in_centre(request, response);
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
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}

	private void addPermissible_bulls_in_centre(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addPermissible_bulls_in_centre");
			Permissible_bulls_in_centreDAO permissible_bulls_in_centreDAO = new Permissible_bulls_in_centreDAO();
			Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO;
			LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
			String FileNamePrefix;
			if(addFlag == true)
			{
				permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				permissible_bulls_in_centreDTO = permissible_bulls_in_centreDAO.getPermissible_bulls_in_centreDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			if(addFlag == true)
			{
				String bullType = request.getParameter("bullType");
				if(bullType.equalsIgnoreCase("-1"))
				{
					System.out.println("Invalid Bull");
				}
				else
				{
					permissible_bulls_in_centreDTO.bullType = Integer.parseInt(bullType);
					CentreDAO centreDAO = new CentreDAO();
					ArrayList<String>  centreIDs = (ArrayList<String>)centreDAO.getIDs( loginDTO);
					System.out.println("in pbs adding bulls");
					for(int j = 0; j <centreIDs.size(); j ++)
					{
						String isChecked = request.getParameter("isChecked_" + j);
						if(isChecked != null)
						{
							System.out.println("in pbs j = " + j);
							permissible_bulls_in_centreDTO.centreType = Integer.parseInt(centreIDs.get(j));
							permissible_bulls_in_centreDTO.dateOfEntry = Long.parseLong(request.getParameter("dateOfEntry_" + j));
							permissible_bulls_in_centreDTO.dateOfExpiration = Long.parseLong(request.getParameter("dateOfExpiration_" + j));
							permissible_bulls_in_centreDTO.description = request.getParameter("description_" + j);
							
							permissible_bulls_in_centreDAO.addPermissible_bulls_in_centre(permissible_bulls_in_centreDTO);
							System.out.println("in pbs j = " + j + " done");
							
							
							BullDAO bullDAO = new BullDAO();
							String bullName = bullDAO.getBullName(permissible_bulls_in_centreDTO.bullType);

							String centreName = centreDAO.getCentreNameByCentreID(permissible_bulls_in_centreDTO.centreType);
							long showingDate = DateUtils.addMonth(permissible_bulls_in_centreDTO.dateOfExpiration, -1);
							Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
							pb_notificationsDAO.addPb_notifications(request, 1, 1, showingDate,"Permissible_bulls_in_centreServlet?actionType=getAddPage", "Change action plan for " + bullName + ", " + centreName); //role 1 means admin
							pb_notificationsDAO.addPb_notifications(request, 1, 6003, showingDate,"Permissible_bulls_in_centreServlet?actionType=getAddPage", "Change action plan for " + bullName + ", " + centreName);   //role 6003 means mid level operator
							
						}
					}
				}
			}
			else
			{
				
				String Value = "";
				Value = request.getParameter("iD");
				System.out.println("iD = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.iD = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("bullType");
				System.out.println("bullType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.bullType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("centreType");
				System.out.println("centreType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.centreType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("dateOfEntry");
				System.out.println("dateOfEntry = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.dateOfEntry = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("dateOfExpiration");
				System.out.println("dateOfExpiration = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.dateOfExpiration = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("description");
				System.out.println("description = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.description = (Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("isDeleted");
				System.out.println("isDeleted = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					permissible_bulls_in_centreDTO.isDeleted = Boolean.parseBoolean(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				
				System.out.println("Done adding  addPermissible_bulls_in_centre dto = " + permissible_bulls_in_centreDTO);
				
				if(addFlag == true)
				{
					permissible_bulls_in_centreDAO.addPermissible_bulls_in_centre(permissible_bulls_in_centreDTO);
				}
				else
				{
					permissible_bulls_in_centreDAO.updatePermissible_bulls_in_centre(permissible_bulls_in_centreDTO);
				}
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getPermissible_bulls_in_centre(request, response);
			}
			else
			{
				response.sendRedirect("Permissible_bulls_in_centreServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void deletePermissible_bulls_in_centre(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Permissible_bulls_in_centreDAO().deletePermissible_bulls_in_centreByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Permissible_bulls_in_centreServlet?actionType=search");
	}

	private void getPermissible_bulls_in_centre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getPermissible_bulls_in_centre");
		Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = null;
		try 
		{
			permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDAO().getPermissible_bulls_in_centreDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", permissible_bulls_in_centreDTO.iD);
			request.setAttribute("permissible_bulls_in_centreDTO",permissible_bulls_in_centreDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "permissible_bulls_in_centre/permissible_bulls_in_centreInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "permissible_bulls_in_centre/permissible_bulls_in_centreSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "permissible_bulls_in_centre/permissible_bulls_in_centreEdit.jsp?actionType=edit";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
		}
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void searchPermissible_bulls_in_centre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchPermissible_bulls_in_centre 1");
        Permissible_bulls_in_centreDAO permissible_bulls_in_centreDAO = new Permissible_bulls_in_centreDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_PERMISSIBLE_BULLS_IN_CENTRE, request, permissible_bulls_in_centreDAO, SessionConstants.VIEW_PERMISSIBLE_BULLS_IN_CENTRE, SessionConstants.SEARCH_PERMISSIBLE_BULLS_IN_CENTRE);
        try
        {
			System.out.println("trying to dojob");
            rnManager.doJob(loginDTO);
        }
        catch(Exception e)
        {
			System.out.println("failed to dojob" + e);
        }

        RequestDispatcher rd;
        if(hasAjax == false)
        {
        	System.out.println("Going to permissible_bulls_in_centre/permissible_bulls_in_centreSearch.jsp");
        	rd = request.getRequestDispatcher("permissible_bulls_in_centre/permissible_bulls_in_centreSearch.jsp");
        }
        else
        {
        	System.out.println("Going to permissible_bulls_in_centre/permissible_bulls_in_centreSearchForm.jsp");
        	rd = request.getRequestDispatcher("permissible_bulls_in_centre/permissible_bulls_in_centreSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

