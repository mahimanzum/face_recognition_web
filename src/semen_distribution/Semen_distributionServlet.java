package semen_distribution;

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
import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;
import semen_collection.Semen_collectionDAO;
import sessionmanager.SessionConstants;

import user.UserDTO;
import user.UserRepository;

import util.RecordNavigationManager;

import java.io.*;
import javax.servlet.http.*;

import java.util.List;
import java.util.UUID;

import semen_distribution.Constants;
import semen_requisition.Semen_requisitionAnotherDBDAO;
import semen_requisition.Semen_requisitionDAO;




/**
 * Servlet implementation class Semen_distributionServlet
 */
@WebServlet("/Semen_distributionServlet")
@MultipartConfig
public class Semen_distributionServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Semen_distributionServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Semen_distributionServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_UPDATE))
				{
					getSemen_distribution(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_SEARCH))
				{
					searchSemen_distribution(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("semen_distribution/semen_distributionEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_ADD))
				{
					System.out.println("going to  addSemen_distribution ");
					addSemen_distribution(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addSemen_distribution ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_UPDATE))
				{
					addSemen_distribution(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteSemen_distribution(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.SEMEN_DISTRIBUTION_SEARCH))
				{
					searchSemen_distribution(request, response);
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

	private void addSemen_distribution(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addSemen_distribution");
			Semen_distributionDAO semen_distributionDAO = new Semen_distributionDAO();
			Semen_collectionDAO semen_collectionDAO = new Semen_collectionDAO();
			Semen_distributionDTO semen_distributionDTO;
			BullDAO bullDAO = new BullDAO();
			if(addFlag == true)
			{
				semen_distributionDTO = new Semen_distributionDTO();
			}
			else
			{
				semen_distributionDTO = semen_distributionDAO.getSemen_distributionDTOByID(Long.parseLong(request.getParameter("identity")));
			}
			
			if(addFlag == true)
			{
				List<Integer> breedids = Semen_requisitionAnotherDBDAO.getIDs("breed", "id", "");
				semen_distributionDTO.transactionDate = Long.parseLong(request.getParameter("transactionDate"));
				semen_distributionDTO.description = request.getParameter("description");
				semen_distributionDTO.isDeleted = false;
				System.out.println("groupid = "  + request.getParameter("groupID") + " requisitionId = " + request.getParameter("requisitionId"));
				semen_distributionDTO.groupId = Integer.parseInt(request.getParameter("requisitionId"));
				int j = 0;

				for(; j < breedids.size(); j ++)
				{
					List<Long> bull_ids = bullDAO.getIDsWithSearchCriteria("breed_type", breedids.get(j));
					
					for(int k = 0; k < bull_ids.size(); k ++)
					{
						String Value = "";
						Value = request.getParameter("noOfDose_" + j + "_" + k);
						if(Value != null && !Value.equalsIgnoreCase(""))
						{
							try
							{
								int iNoOfDose = Integer.parseInt(Value);
								System.out.println("iNoOfDose = " + iNoOfDose);
								int remainingBullDose = semen_collectionDAO.getTotalDoseOfBull(bull_ids.get(k)) - iNoOfDose;
								if(iNoOfDose > 0 && remainingBullDose>= 0)
								{
									semen_distributionDTO.bullType = (bull_ids.get(k)).intValue();
															
									semen_distributionDTO.noOfDose = Integer.parseInt(Value);
								
									semen_distributionDAO.addSemen_distribution(semen_distributionDTO);
								}
							}
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
						}
					}					
					
				}
				
				
				if(j > 0)
				{
					Semen_requisitionDAO semenRequisitionDAO = new Semen_requisitionDAO();
					semenRequisitionDAO.deliverSemen_requisition(semen_distributionDTO.groupId);
				}
			}
			
			
			
			System.out.println("Done adding  addSemen_distribution dto = " + semen_distributionDTO);

			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getSemen_distribution(request, response);
			}
			else
			{
				response.sendRedirect("Semen_distribution_immediate_report_Servlet?actionType=reportPage&DistributionId=" + semen_distributionDTO.groupId);
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteSemen_distribution(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Semen_distributionDAO().deleteSemen_distributionByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Semen_distributionServlet?actionType=search");
	}

	private void getSemen_distribution(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getSemen_distribution");
		Semen_distributionDTO semen_distributionDTO = null;
		try 
		{
			semen_distributionDTO = new Semen_distributionDAO().getSemen_distributionDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", semen_distributionDTO.iD);
			request.setAttribute("semen_distributionDTO",semen_distributionDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "semen_distribution/semen_distributionInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "semen_distribution/semen_distributionSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "semen_distribution/semen_distributionEdit.jsp?actionType=edit";
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
	
	private void searchSemen_distribution(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchSemen_distribution 1");
        Semen_distributionDAO semen_distributionDAO = new Semen_distributionDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_SEMEN_DISTRIBUTION, request, semen_distributionDAO, SessionConstants.VIEW_SEMEN_DISTRIBUTION, SessionConstants.SEARCH_SEMEN_DISTRIBUTION);
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
        	System.out.println("Going to semen_distribution/semen_distributionSearch.jsp");
        	rd = request.getRequestDispatcher("semen_distribution/semen_distributionSearch.jsp");
        }
        else
        {
        	System.out.println("Going to semen_distribution/semen_distributionSearchForm.jsp");
        	rd = request.getRequestDispatcher("semen_distribution/semen_distributionSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

