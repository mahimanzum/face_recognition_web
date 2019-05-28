package semen_collection;

import java.io.IOException;


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

import java.io.*;
import javax.servlet.http.*;

import java.util.List;
import java.util.UUID;

import semen_collection.Constants;
import semen_requisition.Semen_requisitionAnotherDBDAO;




/**
 * Servlet implementation class Semen_collectionServlet
 */
@WebServlet("/Semen_collectionServlet")
@MultipartConfig
public class Semen_collectionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Semen_collectionServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Semen_collectionServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_UPDATE))
				{
					getSemen_collection(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_SEARCH))
				{
					searchSemen_collection(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("semen_collection/semen_collectionEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_ADD))
				{
					System.out.println("going to  addSemen_collection ");
					addSemen_collection(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addSemen_collection ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_UPDATE))
				{
					addSemen_collection(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteSemen_collection(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_COLLECTION_SEARCH))
				{
					searchSemen_collection(request, response);
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

	private void addSemen_collection(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addSemen_collection");
			Semen_collectionDAO semen_collectionDAO = new Semen_collectionDAO();
			Semen_collectionDTO semen_collectionDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				semen_collectionDTO = new Semen_collectionDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				semen_collectionDTO = semen_collectionDAO.getSemen_collectionDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			if(addFlag == true)
			{
				
				for(int j = 0; j < 50; j ++)
				{
					int noOfDose =  Integer.parseInt(request.getParameter("noOfDose_" + j));
					if(noOfDose == 0)
					{
						continue;
					}
					
					int bullID = Integer.parseInt(request.getParameter("bullType_" + j));
					if(bullID < 0)
					{
						continue;
					}
					
					semen_collectionDTO.bullType = bullID;
					semen_collectionDTO.colorType = Integer.parseInt(request.getParameter("colorType_" + j));
					semen_collectionDTO.density = Integer.parseInt(request.getParameter("density_" + j));
					semen_collectionDTO.description = request.getParameter("description_" + j);
					if(semen_collectionDTO.description == null)
					{
						semen_collectionDTO.description = "";
					}
					semen_collectionDTO.isDeleted = false;
					semen_collectionDTO.noOfDose = Integer.parseInt(request.getParameter("noOfDose_" + j));
					semen_collectionDTO.progressiveMortality = Integer.parseInt(request.getParameter("progressiveMortality_" + j));
					semen_collectionDTO.transactionDate = Long.parseLong(request.getParameter("transactionDate_" + j));
					
					semen_collectionDAO.addSemen_collection(semen_collectionDTO);
					
				}
				
			}
			else
			{
			
				String Value = "";
				Value = request.getParameter("iD");
				System.out.println("iD = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.iD = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("bullType");
				System.out.println("bullType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.bullType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("noOfDose");
				System.out.println("noOfDose = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.noOfDose = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("volume");
				System.out.println("volume = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.volume = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("density");
				System.out.println("density = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.density = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("progressiveMortality");
				System.out.println("progressiveMortality = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.progressiveMortality = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("colorType");
				System.out.println("colorType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.colorType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("transactionDate");
				System.out.println("transactionDate = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.transactionDate = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("description");
				System.out.println("description = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.description = (Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("isDeleted");
				System.out.println("isDeleted = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					semen_collectionDTO.isDeleted = Boolean.parseBoolean(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				
				System.out.println("Done adding  addSemen_collection dto = " + semen_collectionDTO);
				
				if(addFlag == true)
				{
					semen_collectionDAO.addSemen_collection(semen_collectionDTO);
				}
				else
				{
					semen_collectionDAO.updateSemen_collection(semen_collectionDTO);
				}
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getSemen_collection(request, response);
			}
			else
			{
				response.sendRedirect("Semen_collectionServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void deleteSemen_collection(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Semen_collectionDAO().deleteSemen_collectionByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Semen_collectionServlet?actionType=search");
	}

	private void getSemen_collection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getSemen_collection");
		Semen_collectionDTO semen_collectionDTO = null;
		try 
		{
			semen_collectionDTO = new Semen_collectionDAO().getSemen_collectionDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", semen_collectionDTO.iD);
			request.setAttribute("semen_collectionDTO",semen_collectionDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "semen_collection/semen_collectionInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "semen_collection/semen_collectionSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "semen_collection/semen_collectionEdit.jsp?actionType=edit";
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
	
	private void searchSemen_collection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchSemen_collection 1");
        Semen_collectionDAO semen_collectionDAO = new Semen_collectionDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_SEMEN_COLLECTION, request, semen_collectionDAO, SessionConstants.VIEW_SEMEN_COLLECTION, SessionConstants.SEARCH_SEMEN_COLLECTION);
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
        	System.out.println("Going to semen_collection/semen_collectionSearch.jsp");
        	rd = request.getRequestDispatcher("semen_collection/semen_collectionSearch.jsp");
        }
        else
        {
        	System.out.println("Going to semen_collection/semen_collectionSearchForm.jsp");
        	rd = request.getRequestDispatcher("semen_collection/semen_collectionSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

