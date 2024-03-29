package facial_recognization;

import java.io.IOException;
import java.nio.file.Paths;
import java.io.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;


import org.apache.log4j.Logger;
import org.apache.poi.sl.draw.geom.Path;

import login.LoginDTO;
import permission.MenuConstants;
import role.PermissionRepository;


import sessionmanager.SessionConstants;

import user.UserDTO;
import user.UserRepository;

import util.RecordNavigationManager;

import java.util.*;
import javax.servlet.http.*;

import facial_recognization.Constants;



import pb.*;

/**
 * Servlet implementation class Facial_recognizationServlet
 */
@WebServlet("/Facial_recognizationServlet")
@MultipartConfig
public class Facial_recognizationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(Facial_recognizationServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Facial_recognizationServlet()
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_ADD))
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_UPDATE))
				{
					getFacial_recognization(request, response);
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
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_SEARCH))
				{
					searchFacial_recognization(request, response);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("search_face"))
			{
				System.out.println("search face requested");
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_SEARCH))
				{
					searchFc_recognization (request, response);

					//searchFacial_recognization
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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("facial_recognization/facial_recognizationEdit.jsp");
		requestDispatcher.forward(request, response);
	}
	private String getFileName(final Part part)
	{
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("Part Header = new part {0}" +  partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}



	private void uploadFile(Part filePart, String fileName, String forwhat)
	{

		OutputStream out = null;
		InputStream filecontent = null;

		String path = getServletContext().getRealPath("/img2");
		path = path + '/';
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
			System.out.println("#########file name  "+ fileName);
			System.out.println("New file " + fileName + " created at " + path);


			Process p;
			//String conda = "C:/Users/mahim/Anaconda3/envs/workshop/python";
			//String conda = "C:/Users/REVE PC/Anaconda3/envs/workshop/python";

			//String conda = "/home/mahim/anaconda3/envs/workshop/bin/python";
			String conda = "/root/anaconda3/envs/workshop/bin/python";
			//Path pa = (Path) Paths.get("c:", "Users","REVE PC", "Anaconda3", "envs", "workshop", "python");
			//String conda = pa.toString();
			System.out.println("Conda path = "+conda);
			if(forwhat.equalsIgnoreCase("test")) {
				//String val =  conda+" "+path+"process.py"+" "+path+" "+path+"test.jpg";
				//val = val.replace('\\', '/');
				//System.out.println("Executing command coming now" + val);
				//p = Runtime.getRuntime().exec(val);
				String[] a = new String[] {
						conda,
						path+"process.py",
						path,
						"test.jpg"};
				p = Runtime.getRuntime().exec(a);
				String decision = path+"decision.txt";
				//write yes in the deicison file
				try{
					FileWriter fileWriter = new FileWriter(decision);
					String fileContent = "0";
					System.out.println("lINE 202 IN SERVE LET FILE CONTENT "+	fileContent);
					fileWriter.write(fileContent);
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Decision file not found error "
							+ "$$$$$$$$");
				}

				//p = Runtime.getRuntime().exec("C:\\Users\\\"REVE PC\"\\Anaconda3\\envs\\workshop\\python C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\process.py C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\  C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\test.jpg");
			}
			else {
				//String val =  conda+" "+path+"train.py"+" "+path+" "+path+fileName;
				//val = val.replace('\\', '/');
				//System.out.println("Executing command coming now2" + val);
				String[] a = new String[] {
						conda,
						path+"train.py",
						path,
						fileName};
				p = Runtime.getRuntime().exec(a);
				//p = Runtime.getRuntime().exec(val);
			}

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			//System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}



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

	private void uploadImage(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException
	{
		System.out.println("%%%% ajax upload called");
		Part filePart_bullDatabase;
		try
		{
			filePart_bullDatabase = request.getPart("image_to_search");
			String Value = getFileName(filePart_bullDatabase);
			System.out.println("bullDatabase = " + Value);
			if( addFlag == true ||(Value != null && !Value.equalsIgnoreCase("")))
			{
				if(Value != null && !Value.equalsIgnoreCase(""))
				{
					String FileName = "test.jpg";

					uploadFile(filePart_bullDatabase, FileName, "test");
					//request.setAttribute("ajax", true);
					//searchFacial_recognization(request, response);
					response.sendRedirect("Facial_recognizationServlet?actionType=search_face");
					//response.setHeader("Refresh", "0; URL=http://localhost:8080/dls2/Facial_recognizationServlet?actionType=search");
				}
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_ADD))
				{
					System.out.println("going to new addFacial_recognization ");
					addFacial_recognization(request, response, true);
				}
				else
				{
					System.out.println("Not going to  addFacial_recognization ");
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}

			}
			else if(actionType.equals("upload"))
			{

				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_ADD))
				{
					uploadImage(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("edit"))
			{

				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_UPDATE))
				{
					addFacial_recognization(request, response, false);
				}
				else
				{
					request.getRequestDispatcher(Constants.ERROR_PAGE).forward(request, response);
				}
			}
			else if(actionType.equals("delete"))
			{
				deleteFacial_recognization(request, response);
			}
			else if(actionType.equals("search"))
			{
				if(PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, MenuConstants.FACIAL_RECOGNIZATION_SEARCH))
				{
					//searchFacial_recognization(request, response);
					searchFc_recognization(request, response);
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
	private void addFacial_recognization(HttpServletRequest request, HttpServletResponse response, Boolean addFlag) throws IOException
	{
		// TODO Auto-generated method stub
		try
		{
			request.setAttribute("failureMessage", "");
			System.out.println("%%%% addFacial_recognization");
			Facial_recognizationDAO facial_recognizationDAO = new Facial_recognizationDAO();
			Facial_recognizationDTO facial_recognizationDTO;
			String FileNamePrefix;
			if(addFlag == true)
			{
				facial_recognizationDTO = new Facial_recognizationDTO();
				FileNamePrefix = UUID.randomUUID().toString().substring(0, 10);
			}
			else
			{
				facial_recognizationDTO = facial_recognizationDAO.getFacial_recognizationDTOByID(Long.parseLong(request.getParameter("identity")));
				FileNamePrefix = request.getParameter("identity");
			}

			String Value = "";
			Value = request.getParameter("iD");
			System.out.println("iD = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.iD = Long.parseLong(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("name");
			System.out.println("name = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.name = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("address");
			System.out.println("address = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.address = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("phone");
			System.out.println("phone = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.phone = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("email");
			System.out.println("email = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.email = (Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Part filePart_image =  request.getPart("image");
			Value = getFileName(filePart_image);
			System.out.println("new image for marking is image = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				if(Value.toLowerCase().endsWith(".jpg") || Value.toLowerCase().endsWith(".png")
						|| Value.toLowerCase().endsWith(".gif") || Value.toLowerCase().endsWith(".bmp") || Value.toLowerCase().endsWith(".ico"))
				{
					//edited
					//String FileName = FileNamePrefix + "_" + "image" + "_" + Value;
					String FileName = Value;
					facial_recognizationDTO.image = FileName;
					System.out.println("%%%%%passed file name" + " = " +FileName );
					uploadFile(filePart_image, FileName, "train");
				}
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}
			Value = request.getParameter("isDeleted");
			System.out.println("isDeleted = " + Value);
			if(Value != null && !Value.equalsIgnoreCase(""))
			{
				facial_recognizationDTO.isDeleted = Boolean.parseBoolean(Value);
			}
			else
			{
				System.out.println("FieldName has a null value, not updating" + " = " + Value);
			}

			System.out.println("Done adding  addFacial_recognization dto = " + facial_recognizationDTO);

			if(addFlag == true)
			{
				facial_recognizationDAO.addFacial_recognization(facial_recognizationDTO);
			}
			else
			{
				facial_recognizationDAO.updateFacial_recognization(facial_recognizationDTO);

			}








			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");

			if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				getFacial_recognization(request, response);
			}
			else
			{
				response.sendRedirect("Facial_recognizationServlet?actionType=search");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}









	private void deleteFacial_recognization(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
		{
			String[] IDsToDelete = request.getParameterValues("ID");
			for(int i = 0; i < IDsToDelete.length; i ++)
			{
				long id = Long.parseLong(IDsToDelete[i]);
				System.out.println("###DELETING " + IDsToDelete[i]);
				new Facial_recognizationDAO().deleteFacial_recognizationByID(id);
			}
		}
		catch (Exception ex)
		{
			logger.debug(ex);
		}
		response.sendRedirect("Facial_recognizationServlet?actionType=search");
	}

	private void getFacial_recognization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in getFacial_recognization");
		Facial_recognizationDTO facial_recognizationDTO = null;
		try
		{
			facial_recognizationDTO = new Facial_recognizationDAO().getFacial_recognizationDTOByID(Long.parseLong(request.getParameter("ID")));
			request.setAttribute("ID", facial_recognizationDTO.iD);
			request.setAttribute("facial_recognizationDTO",facial_recognizationDTO);

			String URL= "";

			String inPlaceEdit = (String)request.getParameter("inplaceedit");
			String inPlaceSubmit = (String)request.getParameter("inplacesubmit");

			if(inPlaceEdit != null && !inPlaceEdit.equalsIgnoreCase(""))
			{
				URL = "facial_recognization/facial_recognizationInPlaceEdit.jsp";
				request.setAttribute("inplaceedit","");
			}
			else if(inPlaceSubmit != null && !inPlaceSubmit.equalsIgnoreCase(""))
			{
				URL = "facial_recognization/facial_recognizationSearchRow.jsp";
				request.setAttribute("inplacesubmit","");
			}
			else
			{
				URL = "facial_recognization/facial_recognizationEdit.jsp?actionType=edit";
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

	private void searchFacial_recognization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in searchFacial_recognization 1");
		Facial_recognizationDAO facial_recognizationDAO = new Facial_recognizationDAO();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);

		RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_FACIAL_RECOGNIZATION, request, facial_recognizationDAO, SessionConstants.VIEW_FACIAL_RECOGNIZATION, SessionConstants.SEARCH_FACIAL_RECOGNIZATION);
		try
		{
			System.out.println("trying to dojob");
			rnManager.doJob(loginDTO);
		}
		catch(Exception e)
		{
			System.out.println("failed to dojob" + e);
		}
		//hasAjax = true;
		RequestDispatcher rd;
		if(hasAjax == false)
		{
			System.out.println("Going to facial_recognization/facial_recognizationSearch.jsp");
			rd = request.getRequestDispatcher("facial_recognization/facial_recognizationSearch.jsp");
		}
		else
		{
			System.out.println("Going to facial_recognization/facial_recognizationSearchForm.jsp");
			rd = request.getRequestDispatcher("facial_recognization/facial_recognizationSearchForm.jsp");
		}
		//response.setHeader("Refresh", "0; URL=http://localhost:8080/dls2/Facial_recognizationServlet?actionType=search");
		rd.forward(request, response);
	}


//my variations

	private void searchFc_recognization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in searchFacial_recognization 1");
		Facial_recognizationDAO facial_recognizationDAO = new Facial_recognizationDAO();
		List<Facial_recognizationDTO> lst = new ArrayList<>();
		LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute( SessionConstants.USER_LOGIN );
		try {
			//lst = facial_recognizationDAO.getImageIDDTOLiist();
			lst = facial_recognizationDAO.getImageIDDTOLiistSorted();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession(true).setAttribute(SessionConstants.VIEW_FACIAL_RECOGNIZATION, lst);
		/*
		String ajax = (String)request.getParameter("ajax");
		boolean hasAjax = false;
		if(ajax != null && !ajax.equalsIgnoreCase(""))
		{
			hasAjax = true;
		}
		System.out.println("ajax = " + ajax + " hasajax = " + hasAjax);

		RecordNavigationManager rnManager = new RecordNavigationManager(SessionConstants.NAV_FACIAL_RECOGNIZATION, request, facial_recognizationDAO, SessionConstants.VIEW_FACIAL_RECOGNIZATION, SessionConstants.SEARCH_FACIAL_RECOGNIZATION);
		try
		{
			System.out.println("trying to dojob");
			rnManager.doJob(loginDTO);
		}
		catch(Exception e)
		{
			System.out.println("failed to dojob" + e);
		}
		//hasAjax = true;
		*/
		RequestDispatcher rd;
		/*
		if(hasAjax == false)
		{
			System.out.println("Going to facial_recognization/facial_recognizationSearch.jsp");
			rd = request.getRequestDispatcher("facial_recognization/facial_recognizationSearch.jsp");
		}
		else
		{
			System.out.println("Going to facial_recognization/facial_recognizationSearchForm.jsp");
			rd = request.getRequestDispatcher("facial_recognization/facial_recognizationSearchForm.jsp");
		}
		*/
		//response.setHeader("Refresh", "0; URL=http://localhost:8080/dls2/Facial_recognizationServlet?actionType=search");
		rd = request.getRequestDispatcher("facial_recognization/facial_recognizationSearchForm.jsp");
		rd.forward(request, response);
	}

}

