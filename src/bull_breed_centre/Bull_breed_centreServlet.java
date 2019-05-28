package bull_breed_centre;

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

import java.util.List;
import javax.servlet.http.*;
import java.util.UUID;

import bull_breed_centre.Constants;



import pb.*;

/**
 * Servlet implementation class Bull_breed_centreServlet
 */
@WebServlet("/Bull_breed_centreServlet")
@MultipartConfig
public class Bull_breed_centreServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Bull_breed_centreServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bull_breed_centreServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_UPDATE))
				{
					getBull_breed_centre(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_SEARCH))
				{
					searchBull_breed_centre(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}				
			}
			else if(actionType.equals("view"))
			{
				System.out.println("view requested");
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.BULL_BREED_CENTRE_SEARCH))
				{
					viewBull_breed_centre(request, response);
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
	private void viewBull_breed_centre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String ID = request.getParameter("ID");
		String modal = request.getParameter("modal");
		RequestDispatcher rd;
		if(modal != null && modal.equalsIgnoreCase("1"))
		{
			 rd = request.getRequestDispatcher("bull_breed_centre/bull_breed_centreViewModal.jsp?ID=" + ID);
		}
		else
		{
			 rd = request.getRequestDispatcher("bull_breed_centre/bull_breed_centreView.jsp?ID=" + ID);
		}
		rd.forward(request, response);
		
	}

	private void getAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("ID", -1L);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("bull_breed_centre/bull_breed_centreEdit.jsp");
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
	
	
	private void UploadFile(HttpServletRequest request, HttpServletResponse response, Part filePart, String fileName)
	{

	    OutputStream out = null;
	    InputStream filecontent = null;

	    String path = getServletContext().getRealPath("/img2/");
	    
	    File dir=new File(path);
	    
	    if(!dir.exists())
        {
	    	dir.mkdir();
	    	System.out.println("created directory " + path);
        }

	    try 
		{
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) 
			{
	            out.write(bytes, 0, read);
	        }
	        System.out.println("New file " + fileName + " created at " + path);

	    }
		catch (IOException fne) 
		{
	    	System.out.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	    	System.out.println("ERROR: " + fne.getMessage());
	    }
		finally 
		{
	        if (out != null) 
			{
	            try 
				{
					out.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
	        }
	        if (filecontent != null) 
			{
	            try 
				{
					filecontent.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
	        }	       
	    }		  
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
			}
			else if(actionType.equals("getDependentDiv"))
			{
				
				String Value = request.getParameter("Value");
				System.out.println("going to get div  value = " + Value);
				request.getRequestDispatcher("bull_breed_centre/bull_breed_centreDependentDiv.jsp").forward(request, response);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.debug(ex);
		}
	}

	private void addBull_breed_centre(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addBull_breed_centre");
			Bull_breed_centreDAO bull_breed_centreDAO = new Bull_breed_centreDAO();
			Bull_breed_centreDTO bull_breed_centreDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				bull_breed_centreDTO = new Bull_breed_centreDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				bull_breed_centreDTO = bull_breed_centreDAO.getBull_breed_centreDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			String Value = "";
			Value = request.getParameter("iD");
			System.out.println("iD = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.iD = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("bullType");
			System.out.println("bullType = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.bullType = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("breedType");
			System.out.println("breedType = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.breedType = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("centreType");
			System.out.println("centreType = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.centreType = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("grsOffice");
			System.out.println("grsOffice = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.grsOffice = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("grsOfficer");
			System.out.println("grsOfficer = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.grsOfficer = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Part filePart_infoFile =  request.getPart("infoFile");
			Value = getFileName(filePart_infoFile);
			System.out.println("infoFile = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				if(Value != null && !Value.equalsIgnoreCase(""))
				{					
					String FileName = FileNamePrefix + "_" + "infoFile" + "_" + Value;
					bull_breed_centreDTO.infoFile = (FileName);
					UploadFile(request, response, filePart_infoFile, FileName);
				}	
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Part filePart_bullImage =  request.getPart("bullImage");
			Value = getFileName(filePart_bullImage);
			System.out.println("bullImage = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				if(Value.toLowerCase().endsWith(".jpg") || Value.toLowerCase().endsWith(".png") 
					|| Value.toLowerCase().endsWith(".gif") || Value.toLowerCase().endsWith(".bmp") || Value.toLowerCase().endsWith(".ico"))
				{
					String FileName = FileNamePrefix + "_" + "bullImage" + "_" + Value;
					bull_breed_centreDTO.bullImage = (FileName);
					UploadFile(request, response, filePart_bullImage, FileName);
				}
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("description");
			System.out.println("description = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.description = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isDeleted");
			System.out.println("isDeleted = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				bull_breed_centreDTO.isDeleted = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			
			System.out.println("Done adding  addBull_breed_centre dto = " + bull_breed_centreDTO);
			
			if(addFlag == true)
			{
				bull_breed_centreDAO.addBull_breed_centre(bull_breed_centreDTO);
			}
			else
			{
				bull_breed_centreDAO.updateBull_breed_centre(bull_breed_centreDTO);
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getBull_breed_centre(request, response);
			}
			else
			{
				response.sendRedirect("Bull_breed_centreServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deleteBull_breed_centre(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Bull_breed_centreDAO().deleteBull_breed_centreByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Bull_breed_centreServlet?actionType=search");
	}

	private void getBull_breed_centre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getBull_breed_centre");
		Bull_breed_centreDTO bull_breed_centreDTO = null;
		try 
		{
			bull_breed_centreDTO = new Bull_breed_centreDAO().getBull_breed_centreDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", bull_breed_centreDTO.iD);
			request.setAttribute("bull_breed_centreDTO",bull_breed_centreDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "bull_breed_centre/bull_breed_centreInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "bull_breed_centre/bull_breed_centreSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "bull_breed_centre/bull_breed_centreEdit.jsp?actionType=edit";
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
	
	private void searchBull_breed_centre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchBull_breed_centre 1");
        Bull_breed_centreDAO bull_breed_centreDAO = new Bull_breed_centreDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_BULL_BREED_CENTRE, request, bull_breed_centreDAO, SessionConstants.VIEW_BULL_BREED_CENTRE, SessionConstants.SEARCH_BULL_BREED_CENTRE);
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
        	System.out.println("Going to bull_breed_centre/bull_breed_centreSearch.jsp");
        	rd = request.getRequestDispatcher("bull_breed_centre/bull_breed_centreSearch.jsp");
        }
        else
        {
        	System.out.println("Going to bull_breed_centre/bull_breed_centreSearchForm.jsp");
        	rd = request.getRequestDispatcher("bull_breed_centre/bull_breed_centreSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

