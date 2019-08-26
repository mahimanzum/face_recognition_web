package image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import util.CollectionUtils;
import util.JSPConstant;

@WebServlet("/ImageServlet")
@MultipartConfig
public class ImageServlet extends HttpServlet {

	Logger logger = Logger.getLogger(getClass());
	
	ImageDAO imageDAO = new ImageDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(JSPConstant.IMAGE_SETTING_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		try{
			if("allImages".equals(actionType)){
				updateImage(request, response);
			}else if("add".equals(actionType)){
				addNewImage(request, response);
			}
		}catch(Exception ex){
			logger.fatal("", ex);
		}
		
		
		response.sendRedirect("ImageServlet?actionType=allImages");
	}
	
	
	
	private void updateImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ImageDTO> imageList = new ImageDAO().getAllImageDTOList();
		
		String[] idStringList = request.getParameterValues("ID");
		List<Integer> idList = CollectionUtils.convertToList(Integer.class, idStringList);
		String[] descriptionStringList = request.getParameterValues("URL");
		List<String> descriptions = CollectionUtils.convertToList(String.class, descriptionStringList);
		
		
		Map<Integer,String> mapOfDescriptionToId = new HashMap<>();
		
		
		if(idList.size()!= descriptions.size()){
			throw new RequestFailureException("IDList size and descriptionLlist size doest not match");
		}
		
		for(int i = 0;i<idList.size();i++){
			mapOfDescriptionToId.put(idList.get(i), descriptions.get(i));
		}
		
		
		for (ImageDTO imageDTO: imageList) {
			

			imageDTO.URL = mapOfDescriptionToId.getOrDefault(imageDTO.ID, "");
			
			imageDAO.updateImageDTO(imageDTO);
			
			
			Part part = request.getPart(imageDTO.fileName);
			String contentType = part.getContentType();
			
			if(!contentType.startsWith("image")){
				continue;
			}
			
			InputStream inputStream = part.getInputStream();

			int firtstByte = inputStream.read();

			if (firtstByte != -1) {
				String folderDirectory = ( imageDTO.filePath.startsWith("/")?"": getServletContext().getRealPath(imageDTO.filePath))+"/";

				String absoluteFilePath = folderDirectory+imageDTO.fileName;
				String backupdirectory = getServletContext().getRealPath("backup")+"/";
				String backupFileName = imageDTO.fileName+"_"+System.currentTimeMillis();
				String backupFileAbsolutePath = backupdirectory+backupFileName;
				
				File file = new File(absoluteFilePath);
				if (file.exists()) {
					Files.move(Paths.get(absoluteFilePath), Paths.get(backupFileAbsolutePath), StandardCopyOption.REPLACE_EXISTING);
				}
				
				
				OutputStream outputStream = new FileOutputStream(file);

				try{
					outputStream.write(firtstByte);
	
					int nextByte = 0;
					while ((nextByte = inputStream.read()) != -1) {
						outputStream.write(nextByte);
					}
				}catch(Exception ex){
					ex.printStackTrace();
					Files.move(Paths.get(backupFileAbsolutePath), Paths.get(absoluteFilePath), StandardCopyOption.REPLACE_EXISTING);
				}finally{
					outputStream.flush();
					outputStream.close();
				}

			}

			inputStream.close();

			
		}
		
		
		
		String[] deletedID = request.getParameterValues("deletedID");
		List<Integer> ids = CollectionUtils.convertToList(Integer.class, deletedID);
		imageDAO.deleteImageByImageIDList(ids);
		
	}
	
	private void addNewImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.fileName = request.getParameter("fileName");
		imageDTO.URL = request.getParameter("URL");
		imageDAO.addImage(imageDTO);
		
	}
	

}
