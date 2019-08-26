package bull;

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
import java.util.UUID;

import bull.Constants;




/**
 * Servlet implementation class BullServlet
 */
@WebServlet("/BullServlet")
@MultipartConfig
public class BullServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(BullServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BullServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_UPDATE))
				{
					getBull(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_SEARCH))
				{
					searchBull(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("bull/bullEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_ADD))
				{
					System.out.println("going to  addBull ");
					addBull(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addBull ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_UPDATE))
				{
					addBull(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteBull(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_SEARCH))
				{
					searchBull(request, response);
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

	private void addBull(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addBull");
			BullDAO bullDAO = new BullDAO();
			BullDTO bullDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				bullDTO = new BullDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				bullDTO = bullDAO.getBullDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			String Value = "";
			Value = request.getParameter("iD");
			System.out.println("iD = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.iD = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("nameEn");
			System.out.println("nameEn = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.nameEn = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("nameBn");
			System.out.println("nameBn = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.nameBn = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("dateOfBirth");
			System.out.println("dateOfBirth = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.dateOfBirth = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("breedType");
			System.out.println("breedType = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.breedType = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("statusType");
			System.out.println("statusType = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.statusType = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("dam");
			System.out.println("dam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.dam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sire");
			System.out.println("sire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.sire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("maternalGrandDam");
			System.out.println("maternalGrandDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.maternalGrandDam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("maternalGrandSire");
			System.out.println("maternalGrandSire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.maternalGrandSire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("paternalGrandDam");
			System.out.println("paternalGrandDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.paternalGrandDam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("paternalGrandSire");
			System.out.println("paternalGrandSire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.paternalGrandSire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("milkYieldOfDam");
			System.out.println("milkYieldOfDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.milkYieldOfDam = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("milkYieldOfSiresDam");
			System.out.println("milkYieldOfSiresDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.milkYieldOfSiresDam = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("progenyMilkYield");
			System.out.println("progenyMilkYield = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.progenyMilkYield = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("description");
			System.out.println("description = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.description = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isDeleted");
			System.out.println("isDeleted = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bullDTO.isDeleted = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			
			System.out.println("Done adding  addBull dto = " + bullDTO);
			
			if(addFlag == true)
			{
				bullDAO.addBull(bullDTO);
			}
			else
			{
				bullDAO.updateBull(bullDTO);
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getBull(request, response);
			}
			else
			{
				response.sendRedirect("BullServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteBull(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new BullDAO().deleteBullByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("BullServlet?actionType=search");
	}

	private void getBull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getBull");
		BullDTO bullDTO = null;
		try 
		{
			bullDTO = new BullDAO().getBullDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", bullDTO.iD);
			request.setAttribute("bullDTO",bullDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "bull/bullInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "bull/bullSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "bull/bullEdit.jsp?actionType=edit";
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
	
	private void searchBull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchBull 1");
        BullDAO bullDAO = new BullDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_BULL, request, bullDAO, SessionConstants.VIEW_BULL, SessionConstants.SEARCH_BULL);
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
        	System.out.println("Going to bull/bullSearch.jsp");
        	rd = request.getRequestDispatcher("bull/bullSearch.jsp");
        }
        else
        {
        	System.out.println("Going to bull/bullSearchForm.jsp");
        	rd = request.getRequestDispatcher("bull/bullSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

