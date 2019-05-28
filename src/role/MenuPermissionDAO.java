package role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;
import util.ConnectionUtil;
// insert into menu_permission(roleID,menuID) select 1,menuID from menu where menuID not in (select menuID from menu_permission where roleID = 1)
public class MenuPermissionDAO {
	
	Logger logger = Logger.getLogger(getClass());
	
	public void updateMenuPermission(long roleID,List<Integer> menuIDList){
		
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql = "DELETE FROM menu_permission WHERE roleID = "+roleID;

			ps = connection.prepareStatement(sql);

			ps.execute();
			ps.close();
			
			sql = "INSERT INTO menu_permission(roleID,menuID) VALUES(?,?)";
			
			ps = connection.prepareStatement(sql);
			
			for(int menuID:menuIDList){
				ps.setObject(1, roleID);
				ps.setObject(2, menuID);
				ps.execute();
			}

		}catch(Exception ex){
			logger.debug("fatal",ex);
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		PermissionRepository.reload();

	}
	
	public void addMenuPermissionByRoleIDAndMenuID(long roleID,int menuID){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql = "INSERT INTO menu_permission(roleID,menuID) VALUES(?,?)";
			
			ps = connection.prepareStatement(sql);
		
			ps.setObject(1, roleID);
			ps.setObject(2, menuID);
			ps.execute();

		}catch(Exception ex){
			logger.debug("fatal",ex);
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		PermissionRepository.reload();
	}
	
	public void updateColumnPermission(long roleID,List<Integer> columnIDList){
		
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql = "DELETE FROM column_permission WHERE roleID = "+roleID;

			ps = connection.prepareStatement(sql);

			ps.execute();
			ps.close();
			
			sql = "INSERT INTO column_permission(roleID,columnID) VALUES(?,?)";
			
			ps = connection.prepareStatement(sql);
			
			for(int columnID:columnIDList){
				ps.setObject(1, roleID);
				ps.setObject(2, columnID);
				ps.execute();
			}
			PermissionRepository.reload();

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
