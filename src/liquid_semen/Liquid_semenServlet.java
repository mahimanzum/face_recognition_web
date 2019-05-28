package liquid_semen;

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

import liquid_semen.Constants;




/**
 * Servlet implementation class Liquid_semenServlet
 */
@WebServlet("/Liquid_semenServlet")
@MultipartConfig
public class Liquid_semenServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Liquid_semenServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Liquid_semenServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_UPDATE))
				{
					getLiquid_semen(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_SEARCH))
				{
					searchLiquid_semen(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("liquid_semen/liquid_semenEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_ADD))
				{
					System.out.println("going to  addLiquid_semen ");
					addLiquid_semen(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addLiquid_semen ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_UPDATE))
				{
					addLiquid_semen(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteLiquid_semen(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.LIQUID_SEMEN_SEARCH))
				{
					searchLiquid_semen(request, response);
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

	private void addLiquid_semen(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addLiquid_semen");
			Liquid_semenDAO liquid_semenDAO = new Liquid_semenDAO();
			Liquid_semenDTO liquid_semenDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				liquid_semenDTO = new Liquid_semenDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				liquid_semenDTO = liquid_semenDAO.getLiquid_semenDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			if(addFlag == true)
			{
				liquid_semenDTO.centreType = Integer.parseInt(request.getParameter("centreType"));
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
					
					liquid_semenDTO.bullType = bullID;
					liquid_semenDTO.colorType = Integer.parseInt(request.getParameter("colorType_" + j));
					liquid_semenDTO.density = Integer.parseInt(request.getParameter("density_" + j));
					liquid_semenDTO.description = request.getParameter("description_" + j);
					if(liquid_semenDTO.description == null)
					{
						liquid_semenDTO.description = "";
					}
					liquid_semenDTO.isDeleted = false;
					liquid_semenDTO.noOfDose = Integer.parseInt(request.getParameter("noOfDose_" + j));
					liquid_semenDTO.progressiveMotility = Integer.parseInt(request.getParameter("progressiveMotility_" + j));
					liquid_semenDTO.collectionDistributionDate = Long.parseLong(request.getParameter("transactionDate_" + j));
					
					liquid_semenDAO.addLiquid_semen(liquid_semenDTO);
					
				}
			}
			else
			{
			
				String Value = "";
				Value = request.getParameter("iD");
				System.out.println("iD = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.iD = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("centreType");
				System.out.println("centreType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.centreType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("bullType");
				System.out.println("bullType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.bullType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("noOfDose");
				System.out.println("noOfDose = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.noOfDose = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("volume");
				System.out.println("volume = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.volume = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("density");
				System.out.println("density = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.density = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("progressiveMotility");
				System.out.println("progressiveMotility = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.progressiveMotility = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("colorType");
				System.out.println("colorType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.colorType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("collectionDistributionDate");
				System.out.println("collectionDistributionDate = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.collectionDistributionDate = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("description");
				System.out.println("description = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.description = (Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("isDeleted");
				System.out.println("isDeleted = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					liquid_semenDTO.isDeleted = Boolean.parseBoolean(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				
				System.out.println("Done adding  addLiquid_semen dto = " + liquid_semenDTO);
				
				if(addFlag == true)
				{
					liquid_semenDAO.addLiquid_semen(liquid_semenDTO);
				}
				else
				{
					liquid_semenDAO.updateLiquid_semen(liquid_semenDTO);
				}
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getLiquid_semen(request, response);
			}
			else
			{
				response.sendRedirect("Liquid_semenServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void deleteLiquid_semen(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Liquid_semenDAO().deleteLiquid_semenByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Liquid_semenServlet?actionType=search");
	}

	private void getLiquid_semen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getLiquid_semen");
		Liquid_semenDTO liquid_semenDTO = null;
		try 
		{
			liquid_semenDTO = new Liquid_semenDAO().getLiquid_semenDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", liquid_semenDTO.iD);
			request.setAttribute("liquid_semenDTO",liquid_semenDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "liquid_semen/liquid_semenInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "liquid_semen/liquid_semenSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "liquid_semen/liquid_semenEdit.jsp?actionType=edit";
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
	
	private void searchLiquid_semen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchLiquid_semen 1");
        Liquid_semenDAO liquid_semenDAO = new Liquid_semenDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_LIQUID_SEMEN, request, liquid_semenDAO, SessionConstants.VIEW_LIQUID_SEMEN, SessionConstants.SEARCH_LIQUID_SEMEN);
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
        	System.out.println("Going to liquid_semen/liquid_semenSearch.jsp");
        	rd = request.getRequestDispatcher("liquid_semen/liquid_semenSearch.jsp");
        }
        else
        {
        	System.out.println("Going to liquid_semen/liquid_semenSearchForm.jsp");
        	rd = request.getRequestDispatcher("liquid_semen/liquid_semenSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

