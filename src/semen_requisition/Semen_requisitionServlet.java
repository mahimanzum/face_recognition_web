package semen_requisition;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mysql.cj.util.StringUtils;

import centre.CentreDAO;
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;


import sessionmanager.SessionConstants;

import user.UserDTO;
import user.UserRepository;

import util.RecordNavigationManager;

import java.io.*;
import javax.servlet.http.*;

import java.util.List;
import java.util.UUID;

import semen_requisition.Constants;

import pb_notifications.*;




/**
 * Servlet implementation class Semen_requisitionServlet
 */
@WebServlet("/Semen_requisitionServlet")
@MultipartConfig
public class Semen_requisitionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Semen_requisitionServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Semen_requisitionServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_UPDATE))
				{
					getSemen_requisition(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}				
			}
			else if(actionType.equals("getURL"))
			{
				String URL = request.getParameter("URL");
				System.out.println("URL = " + URL);
				response.sendRedirect(URL);			
			}
			else if(actionType.equals("search"))
			{
				System.out.println("search requested");
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_SEARCH))
				{
					searchSemen_requisition(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("semen_requisition/semen_requisitionEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_ADD))
				{
					System.out.println("going to  addSemen_requisition ");
					addSemen_requisition(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addSemen_requisition ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_UPDATE))
				{
					addSemen_requisition(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteSemen_requisition(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_REQUISITION_SEARCH))
				{
					searchSemen_requisition(request, response);
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

	private void addSemen_requisition(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addSemen_requisition");
			Semen_requisitionDAO semen_requisitionDAO = new Semen_requisitionDAO();
			Semen_requisitionDTO semen_requisitionDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				semen_requisitionDTO = new Semen_requisitionDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				semen_requisitionDTO = semen_requisitionDAO.getSemen_requisitionDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			if(addFlag == true)
			{
				List<Integer> breedids = Semen_requisitionAnotherDBDAO.getIDs("breed", "id", "");
				semen_requisitionDTO.requisitionDate = Long.parseLong(request.getParameter("requisitionDate"));
				semen_requisitionDTO.groupId = semen_requisitionDAO.getMaxGroupID() + 1;
				semen_requisitionDTO.centreType = Integer.parseInt(request.getParameter("centreType"));
				for(int j = 0; j < breedids.size(); j ++)
				{
					String Value = "";
					Value = request.getParameter("noOfDose_" + j);
					if(Value != null && !Value.equalsIgnoreCase(""))
					{
						try
						{
							semen_requisitionDTO.breedType = breedids.get(j);														
							semen_requisitionDTO.noOfDose = Integer.parseInt(Value);
							semen_requisitionDTO.isDelivered = false;
							
							semen_requisitionDAO.addSemen_requisition(semen_requisitionDTO);
						}
						catch(Exception ex)
						{
							
						}
					}
				}
				CentreDAO centreDAO = new CentreDAO();
				String centreName = centreDAO.getCentreNameByCentreID(semen_requisitionDTO.centreType);
				Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
				pb_notificationsDAO.addPb_notifications(request, 1, 1, "Semen_requisitionServlet?actionType=search", "Semen indent from " + centreName); //role 1 means admin
				pb_notificationsDAO.addPb_notifications(request, 1, 6003, "Semen_requisitionServlet?actionType=search", "Semen indent from " + centreName); //role 6003 means mid level operator
			}
			
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getSemen_requisition(request, response);
			}
			else
			{
				response.sendRedirect("Semem_indent_immediate_report_Servlet?actionType=reportPage&requisitionID=" + semen_requisitionDTO.groupId);
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteSemen_requisition(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Semen_requisitionDAO().deleteSemen_requisitionByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Semen_requisitionServlet?actionType=search");
	}

	private void getSemen_requisition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getSemen_requisition");
		Semen_requisitionDTO semen_requisitionDTO = null;
		try 
		{
			semen_requisitionDTO = new Semen_requisitionDAO().getSemen_requisitionDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", semen_requisitionDTO.iD);
			request.setAttribute("semen_requisitionDTO",semen_requisitionDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "semen_requisition/semen_requisitionInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "semen_requisition/semen_requisitionSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "semen_requisition/semen_requisitionEdit.jsp?actionType=edit";
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
	
	private void searchSemen_requisition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchSemen_requisition 1");
        Semen_requisitionDAO semen_requisitionDAO = new Semen_requisitionDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_SEMEN_REQUISITION, request, semen_requisitionDAO, SessionConstants.VIEW_SEMEN_REQUISITION, SessionConstants.SEARCH_SEMEN_REQUISITION);
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
        	System.out.println("Going to semen_requisition/semen_requisitionSearch.jsp");
        	rd = request.getRequestDispatcher("semen_requisition/semen_requisitionSearch.jsp");
        }
        else
        {
        	System.out.println("Going to semen_requisition/semen_requisitionSearchForm.jsp");
        	rd = request.getRequestDispatcher("semen_requisition/semen_requisitionSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

