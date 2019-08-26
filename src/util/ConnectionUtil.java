package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import databasemanager.DatabaseManager;
/**
 * @author Kayesh Parvez
 *
 */
public class ConnectionUtil 
{
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, Statement statement)
	{
		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch(Exception e){}
		try{
			if (statement != null) {
				statement.close();
			}
		} catch(Exception e){}		
		try{
			if(connection != null){
				connection.setAutoCommit(true);
				DatabaseManager.getInstance().freeConnection(connection);
			}
		}catch(Exception ex2){}
	}
	
	public static void updateVbSequencer(String tableName, long currentTime, Connection connection, PreparedStatement preparedStatement) throws SQLException
	{
		preparedStatement = connection.prepareStatement("update vbSequencer set table_LastModificationTime = ? where table_name = ? ");
		int index = 1;
		preparedStatement.setLong(index++, currentTime);
		preparedStatement.setString(index++, tableName);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public static void updateVbSequencer(String tableName, long currentTime, Statement statement) throws SQLException
	{
		String sql = "update vbSequencer set table_LastModificationTime = "+currentTime+" where table_name = '"+ tableName + "'";
		
		statement.executeUpdate(sql);
	}
}
