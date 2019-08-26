package pb_notifications;

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

import java.util.List;
import javax.servlet.http.*;
import java.util.UUID;

import pb_notifications.Constants;



import pb.*;

/**
 * Servlet implementation class Pb_notificationsServlet
 */
@WebServlet("/Pb_notificationsServlet")
@MultipartConfig
public class Pb_notificationsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public static Logger logger = Logger.getLogger(Pb_notificationsServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pb_notificationsServlet() 
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_UPDATE))
				{
					getPb_notifications(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_SEARCH))
				{
					searchPb_notifications(request, response);
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("pb_notifications/pb_notificationsEdit.jsp");
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
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_ADD))
				{
					System.out.println("going to  addPb_notifications ");
					addPb_notifications(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addPb_notifications ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
				
			}
			else if(actionType.equals("edit"))
			{
				
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_UPDATE))
				{
					addPb_notifications(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("makeseen"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_UPDATE))
				{
					
					long seenID = Long.parseLong(request.getParameter("id"));
					System.out.println("seeing noti " + seenID);
					Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
					pb_notificationsDAO.seePb_notification(seenID);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deletePb_notifications(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.PB_NOTIFICATIONS_SEARCH))
				{
					searchPb_notifications(request, response);
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

	private void addPb_notifications(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addPb_notifications");
			Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
			Pb_notificationsDTO pb_notificationsDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				pb_notificationsDTO = new Pb_notificationsDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				pb_notificationsDTO = pb_notificationsDAO.getPb_notificationsDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}
			
			String Value = "";
			Value = request.getParameter("iD");
			System.out.println("iD = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.iD = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isSeen");
			System.out.println("isSeen = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.isSeen = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isHidden");
			System.out.println("isHidden = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.isHidden = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("source");
			System.out.println("source = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.source = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("destination");
			System.out.println("destination = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.destination = Integer.parseInt(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("fromId");
			System.out.println("fromId = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.fromId = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("toId");
			System.out.println("toId = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.toId = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("text");
			System.out.println("text = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.text = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("url");
			System.out.println("url = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.url = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("entryDate");
			System.out.println("entryDate = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.entryDate = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("seenDate");
			System.out.println("seenDate = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.seenDate = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("showingDate");
			System.out.println("showingDate = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.showingDate = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sendAlarm");
			System.out.println("sendAlarm = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.sendAlarm = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sendSms");
			System.out.println("sendSms = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.sendSms = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sendMail");
			System.out.println("sendMail = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.sendMail = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("sendPush");
			System.out.println("sendPush = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.sendPush = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("mailSent");
			System.out.println("mailSent = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.mailSent = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("smsSent");
			System.out.println("smsSent = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.smsSent = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("pushSent");
			System.out.println("pushSent = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.pushSent = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isDeleted");
			System.out.println("isDeleted = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				pb_notificationsDTO.isDeleted = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			
			System.out.println("Done adding  addPb_notifications dto = " + pb_notificationsDTO);
			
			if(addFlag == true)
			{
				pb_notificationsDAO.addPb_notifications(pb_notificationsDTO);
			}
			else
			{
				pb_notificationsDAO.updatePb_notifications(pb_notificationsDTO);
			}
			
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getPb_notifications(request, response);
			}
			else
			{
				response.sendRedirect("Pb_notificationsServlet?actionType=search");
			}
					
		}
		catch (Exception e) 
		{
			logger.debug(e);
		}
	}

	private void deletePb_notifications(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{				
		try 
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);				
				new Pb_notificationsDAO().deletePb_notificationsByID(id);
			}			
		}
		catch (Exception ex) 
		{
			logger.debug(ex);
		}
		response.sendRedirect("Pb_notificationsServlet?actionType=search");
	}

	private void getPb_notifications(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getPb_notifications");
		Pb_notificationsDTO pb_notificationsDTO = null;
		try 
		{
			pb_notificationsDTO = new Pb_notificationsDAO().getPb_notificationsDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", pb_notificationsDTO.iD);
			request.setAttribute("pb_notificationsDTO",pb_notificationsDTO);
			
			String URL= "";
			
			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");
			
			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "pb_notifications/pb_notificationsInPlaceEdit.jsp";	
				request.setAttribute("inplaceedit","");				
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "pb_notifications/pb_notificationsSearchRow.jsp";
				request.setAttribute("inplacesubmit","");					
			}
			else
			{
				URL = "pb_notifications/pb_notificationsEdit.jsp?actionType=edit";
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
	
	private void searchPb_notifications(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in  searchPb_notifications 1");
        Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);
		
        RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_PB_NOTIFICATIONS, request, pb_notificationsDAO, SessionConstants.VIEW_PB_NOTIFICATIONS, SessionConstants.SEARCH_PB_NOTIFICATIONS);
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
        	System.out.println("Going to pb_notifications/pb_notificationsSearch.jsp");
        	rd = request.getRequestDispatcher("pb_notifications/pb_notificationsSearch.jsp");
        }
        else
        {
        	System.out.println("Going to pb_notifications/pb_notificationsSearchForm.jsp");
        	rd = request.getRequestDispatcher("pb_notifications/pb_notificationsSearchForm.jsp");
        }
		rd.forward(request, response);
	}
	
}

