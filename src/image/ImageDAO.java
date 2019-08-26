package image;

import org.apache.log4j.Logger;

import common.StringUtils;
import databasemanager.DatabaseManager;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ImageDAO {
	Logger logger = Logger.getLogger(getClass());

	public List<ImageDTO> getAllImageDTOList() {
		List<ImageDTO> imageDTOs = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "select * from image";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.fileName = rs.getString("fileName");
				imageDTO.filePath = rs.getString("filePath");
				imageDTO.ID = rs.getInt("ID");
				imageDTO.URL = rs.getString("URL");
				imageDTOs.add(imageDTO);
			}

			ps.close();

		} catch (Exception ex) {
			logger.fatal("", ex);
		} finally {
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		return imageDTOs;
	}
	
	public void updateImageDTO(ImageDTO imageDTO){
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "update image set filePath = ?, URL = ? where ID = ?";

			ps = connection.prepareStatement(sql);

			ps.setObject(1, imageDTO.filePath);
			ps.setObject(2, imageDTO.URL);
			ps.setObject(3, imageDTO.ID);
			
		    ps.execute();

			ps.close();

		} catch (Exception ex) {
			logger.fatal("", ex);
		} finally {
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		
	}

	public void addImage(ImageDTO imageDTO) {
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			imageDTO.ID = (int)DatabaseManager.getInstance().getNextSequenceId("image");

			String sql ="insert into image(ID,fileName,URL,filePath) VALUES(?,?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,imageDTO.ID);
			ps.setObject(index++,imageDTO.fileName);
			ps.setObject(index++,imageDTO.URL);
			ps.setObject(index++,imageDTO.filePath);
			ps.execute();

		}catch(Exception ex){
			logger.debug("fatal",ex);
		}finally{
			try{
				if (ps != null) {
					ps.close();
				}
			} catch(Exception e){}
			try{
				if(connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				}
			}catch(Exception ex2){}
		}
	
	}
	
	
	public void deleteImageByImageIDList(List<Integer> idList){
		
		if(idList.isEmpty()){
			return;
		}
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();


			String sql ="delete from image where ID in "+StringUtils.getCommaSeparatedString(idList);
			
			logger.debug(sql);
			ps = connection.prepareStatement(sql);

			ps.execute();

		}catch(Exception ex){
			logger.debug("fatal",ex);
		}finally{
			try{
				if (ps != null) {
					ps.close();
				}
			} catch(Exception e){}
			try{
				if(connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				}
			}catch(Exception ex2){}
		}
	}


}