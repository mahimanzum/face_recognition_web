package common;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;

public class PerformanceLogDAO {

	Logger logger = Logger.getLogger(getClass());
	
	public void insert(PerformanceLog activityLog){
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			activityLog.ID = DatabaseManager.getInstance().getNextSequenceId("performance_log");

			String sql ="insert into performance_log(ID,URI,requestTime,ipAddress,portNumber,totalServiceTime,userID) VALUES(?,?,?,?,?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,activityLog.ID);
			ps.setObject(index++,activityLog.URI);
			ps.setObject(index++,activityLog.requestTime);
			ps.setObject(index++,activityLog.ipAddress);
			ps.setObject(index++,activityLog.portNumber);
			ps.setObject(index++,activityLog.totalServiceTime);
			ps.setObject(index++,activityLog.userID);

			ps.execute();

		}catch(Exception ex){
			logger.fatal("",ex);
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
