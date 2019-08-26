package common;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;

public class ExceptionLogDAO {
	Logger logger = Logger.getLogger(getClass());
	public void insertException(ExceptionDTO exceptionDTO){
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			exceptionDTO.ID = DatabaseManager.getInstance().getNextSequenceId("exception_log");

			String sql ="insert into exception_log(ID,stacktrace,time) VALUES(?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,exceptionDTO.ID);
			ps.setObject(index++,exceptionDTO.stacktrace);
			ps.setObject(index++,System.currentTimeMillis());

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
