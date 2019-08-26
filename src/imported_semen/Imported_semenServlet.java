package imported_semen;

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

import imported_semen.Constants;




/**
 * Servlet implementation class Imported_semenServlet
 */
@WebServlet("/Imported_semenServlet")
@MultipartConfig
public class Imported_semenServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Imported_semenServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Imported_semenServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_UPDATE))
				{
					getImported_semen(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_SEARCH))
				{
					searchImported_semen(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("imported_semen/imported_semenEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_ADD))
				{
					System.out.println("going to  addImported_semen ");
					addImported_semen(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addImported_semen ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_UPDATE))
				{
					addImported_semen(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteImported_semen(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.IMPORTED_SEMEN_SEARCH))
				{
					searchImported_semen(request, response);
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

	private void addImported_semen(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addImported_semen");
			Imported_semenDAO imported_semenDAO = new Imported_semenDAO();
			Imported_semenDTO imported_semenDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				imported_semenDTO = new Imported_semenDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				imported_semenDTO = imported_semenDAO.getImported_semenDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			String Value = "";
			Value = request.getParameter("iD");
			System.out.println("iD = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.iD = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("bullTag");
			System.out.println("bullTag = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.bullTag = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("dateOfBirth");
			System.out.println("dateOfBirth = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.dateOfBirth = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("dam");
			System.out.println("dam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.dam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sire");
			System.out.println("sire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.sire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("maternalGrandDam");
			System.out.println("maternalGrandDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.maternalGrandDam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("maternalGrandSire");
			System.out.println("maternalGrandSire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.maternalGrandSire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("paternalGrandDam");
			System.out.println("paternalGrandDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.paternalGrandDam = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("paternalGrandSire");
			System.out.println("paternalGrandSire = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.paternalGrandSire = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("milkYieldOfDam");
			System.out.println("milkYieldOfDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.milkYieldOfDam = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("milkYieldOfSiresDam");
			System.out.println("milkYieldOfSiresDam = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.milkYieldOfSiresDam = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("progenyMilkYield");
			System.out.println("progenyMilkYield = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.progenyMilkYield = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("breed");
			System.out.println("breed = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.breed = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("dateOfEntry");
			System.out.println("dateOfEntry = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.dateOfEntry = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("receivedAmount");
			System.out.println("receivedAmount = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.receivedAmount = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("distributedAmount");
			System.out.println("distributedAmount = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.distributedAmount = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("toWhomDistributed");
			System.out.println("toWhomDistributed = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.toWhomDistributed = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("description");
			System.out.println("description = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.description = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isDeleted");
			System.out.println("isDeleted = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				imported_semenDTO.isDeleted = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			
			System.out.println("Done adding  addImported_semen dto = " + imported_semenDTO);
			
			if(addFlag == true)
			{
				imported_semenDAO.addImported_semen(imported_semenDTO);
			}
			else
			{
				imported_semenDAO.updateImported_semen(imported_semenDTO);
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getImported_semen(request, response);
			}
			else
			{
				response.sendRedirect("Imported_semenServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteImported_semen(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Imported_semenDAO().deleteImported_semenByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Imported_semenServlet?actionType=search");
	}

	private void getImported_semen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getImported_semen");
		Imported_semenDTO imported_semenDTO = null;
		try 
		{
			imported_semenDTO = new Imported_semenDAO().getImported_semenDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", imported_semenDTO.iD);
			request.setAttribute("imported_semenDTO",imported_semenDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "imported_semen/imported_semenInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "imported_semen/imported_semenSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "imported_semen/imported_semenEdit.jsp?actionType=edit";
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
	
	private void searchImported_semen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchImported_semen 1");
        Imported_semenDAO imported_semenDAO = new Imported_semenDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_IMPORTED_SEMEN, request, imported_semenDAO, SessionConstants.VIEW_IMPORTED_SEMEN, SessionConstants.SEARCH_IMPORTED_SEMEN);
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
        	System.out.println("Going to imported_semen/imported_semenSearch.jsp");
        	rd = request.getRequestDispatcher("imported_semen/imported_semenSearch.jsp");
        }
        else
        {
        	System.out.println("Going to imported_semen/imported_semenSearchForm.jsp");
        	rd = request.getRequestDispatcher("imported_semen/imported_semenSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

