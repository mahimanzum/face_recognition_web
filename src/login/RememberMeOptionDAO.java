package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import databasemanager.DatabaseManager;
import util.ConnectionUtil;

public class RememberMeOptionDAO {
	
	Logger logger = Logger.getLogger(getClass());
	
	public void insertRememberMeOption(long userID, String cookieValue) {
		Connection connection = null;
		PreparedStatement ps = null;

		long currentTime = System.currentTimeMillis();

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql ="insert into remember_me_option(userID,cookieValue) VALUES(?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,userID);
			ps.setObject(index++,cookieValue);
			ps.execute();
			ps.close();
			
		}catch(Exception ex){
			logger.fatal("",ex);
			//throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
	}
	public Long getUserIDByCookieValue(String cookieValue){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long currentTime = System.currentTimeMillis();
		Long userID = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql ="select userID from remember_me_option where cookieValue = ?";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,cookieValue);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userID = rs.getLong("userID");
			}
			
			ps.close();
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
			//throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		return userID;
	}
	public void removeRememberMeOptionByUserID(long userID){
		Connection connection = null;
		PreparedStatement ps = null;
		long currentTime = System.currentTimeMillis();
		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql ="delete from remember_me_option where userID = ?";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,userID);
			ps.execute();
			ps.close();
			
		}catch(Exception ex){
			logger.fatal("",ex);
			//throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
	}
}
