package artificial_insemenation_record;

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

import artificial_insemenation_record.Constants;




/**
 * Servlet implementation class Artificial_insemenation_recordServlet
 */
@WebServlet("/Artificial_insemenation_recordServlet")
@MultipartConfig
public class Artificial_insemenation_recordServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Artificial_insemenation_recordServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Artificial_insemenation_recordServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_UPDATE))
				{
					getArtificial_insemenation_record(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_SEARCH))
				{
					searchArtificial_insemenation_record(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("artificial_insemenation_record/artificial_insemenation_recordEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_ADD))
				{
					System.out.println("going to  addArtificial_insemenation_record ");
					addArtificial_insemenation_record(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addArtificial_insemenation_record ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_UPDATE))
				{
					addArtificial_insemenation_record(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteArtificial_insemenation_record(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.ARTIFICIAL_INSEMENATION_RECORD_SEARCH))
				{
					searchArtificial_insemenation_record(request, response);
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

	private void addArtificial_insemenation_record(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addArtificial_insemenation_record");
			Artificial_insemenation_recordDAO artificial_insemenation_recordDAO = new Artificial_insemenation_recordDAO();
			Artificial_insemenation_recordDTO artificial_insemenation_recordDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				artificial_insemenation_recordDTO = artificial_insemenation_recordDAO.getArtificial_insemenation_recordDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			if(addFlag == true)
			{
				String Value = request.getParameter("centreType");
				artificial_insemenation_recordDTO.centreType = Integer.parseInt(Value);
				Value = request.getParameter("entryDate");
				artificial_insemenation_recordDTO.entryDate = Long.parseLong(Value);
				Value = request.getParameter("description");
				artificial_insemenation_recordDTO.description = (Value);
				
				for(int j = 0; j < 50; j ++)
				{
					int noOfAI =  Integer.parseInt(request.getParameter("noOfAI_" + j));
					if(noOfAI == 0)
					{
						continue;
					}
					artificial_insemenation_recordDTO.noOfAI = noOfAI;
					
					int bullID = Integer.parseInt(request.getParameter("bullType_" + j));
					if(bullID < 0)
					{
						continue;
					}
					
					artificial_insemenation_recordDTO.bullType = bullID;
					artificial_insemenation_recordDTO.isDeleted = false;
					
					artificial_insemenation_recordDAO.addArtificial_insemenation_record(artificial_insemenation_recordDTO);
				}
			}
			else
			{
			
				String Value = "";
				Value = request.getParameter("iD");
				System.out.println("iD = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.iD = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("centreType");
				System.out.println("centreType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.centreType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("bullType");
				System.out.println("bullType = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.bullType = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("noOfAI");
				System.out.println("noOfAI = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.noOfAI = Integer.parseInt(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("entryDate");
				System.out.println("entryDate = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.entryDate = Long.parseLong(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("description");
				System.out.println("description = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.description = (Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				Value = request.getParameter("isDeleted");
				System.out.println("isDeleted = " + Value);
				if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
				{
					artificial_insemenation_recordDTO.isDeleted = Boolean.parseBoolean(Value);
				}
				else
				{
					System.out.println("FieldName has a null value, not updating" + " = " + Value);
				}
				
				System.out.println("Done adding  addArtificial_insemenation_record dto = " + artificial_insemenation_recordDTO);
				
				if(addFlag == true)
				{
					artificial_insemenation_recordDAO.addArtificial_insemenation_record(artificial_insemenation_recordDTO);
				}
				else
				{
					artificial_insemenation_recordDAO.updateArtificial_insemenation_record(artificial_insemenation_recordDTO);
				}
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getArtificial_insemenation_record(request, response);
			}
			else
			{
				response.sendRedirect("Artificial_insemenation_recordServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteArtificial_insemenation_record(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Artificial_insemenation_recordDAO().deleteArtificial_insemenation_recordByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Artificial_insemenation_recordServlet?actionType=search");
	}

	private void getArtificial_insemenation_record(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getArtificial_insemenation_record");
		Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = null;
		try 
		{
			artificial_insemenation_recordDTO = new Artificial_insemenation_recordDAO().getArtificial_insemenation_recordDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", artificial_insemenation_recordDTO.iD);
			request.setAttribute("artificial_insemenation_recordDTO",artificial_insemenation_recordDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "artificial_insemenation_record/artificial_insemenation_recordInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "artificial_insemenation_record/artificial_insemenation_recordSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "artificial_insemenation_record/artificial_insemenation_recordEdit.jsp?actionType=edit";
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
	
	private void searchArtificial_insemenation_record(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchArtificial_insemenation_record 1");
        Artificial_insemenation_recordDAO artificial_insemenation_recordDAO = new Artificial_insemenation_recordDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_ARTIFICIAL_INSEMENATION_RECORD, request, artificial_insemenation_recordDAO, SessionConstants.VIEW_ARTIFICIAL_INSEMENATION_RECORD, SessionConstants.SEARCH_ARTIFICIAL_INSEMENATION_RECORD);
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
        	System.out.println("Going to artificial_insemenation_record/artificial_insemenation_recordSearch.jsp");
        	rd = request.getRequestDispatcher("artificial_insemenation_record/artificial_insemenation_recordSearch.jsp");
        }
        else
        {
        	System.out.println("Going to artificial_insemenation_record/artificial_insemenation_recordSearchForm.jsp");
        	rd = request.getRequestDispatcher("artificial_insemenation_record/artificial_insemenation_recordSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

