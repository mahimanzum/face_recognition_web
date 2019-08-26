package role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;
import permission.ColumnPermissionDTO;

public class PermissionRepository {
	static Logger logger = Logger.getLogger(PermissionRepository.class);
	private static PermissionRepository instance = new PermissionRepository();
	private static Map<Long,Set<Integer>> mapOfMenuSetToRoleID;
	static Map<Long,Set<Integer>> mapOfColumnPermissionSetToRoleID;
	static Map<Long,RoleDTO> mapOfRoleDTOToRoleID;
	
	private PermissionRepository() {
		reload();
	}
	
	private static void reloadRole(){
		Connection connection = null;
		Statement stmt = null;
		try{
			String sql = "SELECT * FROM role ";
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<RoleDTO> roleList = new ArrayList<>();
			while(rs.next()){
				RoleDTO roleDTO = new RoleDTO();
				roleDTO.ID = rs.getLong("ID");
				roleDTO.roleName = rs.getString("roleName");
				roleDTO.description = rs.getString("description");
				roleDTO.isDeleted = (rs.getInt("isDeleted")==1);
				roleList.add(roleDTO);
			}
			mapOfRoleDTOToRoleID = new HashMap<>();
			for(RoleDTO roleDTO: roleList){
				if(!roleDTO.isDeleted){
					mapOfRoleDTOToRoleID.put(roleDTO.ID, roleDTO);
				}
			}
			
		}catch(Exception ex){
			logger.fatal("",ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
	}
	
	public static RoleDTO getRoleDTOByRoleID(long roleID){
		return mapOfRoleDTOToRoleID.get(roleID);
	}
	public static ArrayList<RoleDTO> getAllRoles()
	{
		return new ArrayList<RoleDTO>(mapOfRoleDTOToRoleID.values());
	}
	private static void reloadMenuPermission(){
		List<MenuPermissionDTO> menuPermissionDTOList = new ArrayList<>();
		mapOfMenuSetToRoleID = new HashMap<>();
		

		Connection connection = null;
		Statement stmt = null;
		try{
			String sql = "SELECT * FROM menu_permission ";
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				MenuPermissionDTO menuPermissionDTO = new MenuPermissionDTO();
				menuPermissionDTO.roleID = rs.getLong("roleID");
				menuPermissionDTO.menuID = rs.getInt("menuID");
				menuPermissionDTOList.add(menuPermissionDTO);
			}
			
			for(MenuPermissionDTO menuPermissionDTO: menuPermissionDTOList){
				Set<Integer> menuIDSet = mapOfMenuSetToRoleID.getOrDefault(menuPermissionDTO.roleID,new HashSet<>());
				menuIDSet.add(menuPermissionDTO.menuID);
				mapOfMenuSetToRoleID.put(menuPermissionDTO.roleID, menuIDSet);
			}
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
	}
	
	
	private static void reloadColumnPermission(){
		List<ColumnPermissionDTO> columnPermissionDTOList = new ArrayList<>();

		String sql = "SELECT * FROM column_permission ";
		
		mapOfColumnPermissionSetToRoleID = new HashMap<>();
		
		Connection connection = null;
		Statement stmt = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				ColumnPermissionDTO columnPermissionDTO = new ColumnPermissionDTO();
				columnPermissionDTO.roleID = rs.getLong("roleID");
				columnPermissionDTO.columnID = rs.getInt("columnID");
				columnPermissionDTOList.add(columnPermissionDTO);
			}
		}catch(Exception ex){
			logger.fatal("",ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
		
		
		for(ColumnPermissionDTO columnPermissionDTO: columnPermissionDTOList){
			Set<Integer> columnIDSet = mapOfColumnPermissionSetToRoleID.getOrDefault(columnPermissionDTO.roleID, new HashSet<>());
			columnIDSet.add(columnPermissionDTO.columnID);
			mapOfColumnPermissionSetToRoleID.put(columnPermissionDTO.roleID, columnIDSet);
		}

	}
	
	public static void reload(){
		reloadRole();
		reloadMenuPermission();
		reloadColumnPermission();
	}
	
	public static boolean checkPermissionByRoleIDAndMenuID(long roleID,int menuID){
		return mapOfMenuSetToRoleID
				.getOrDefault(roleID, Collections.emptySet())
				.contains(menuID);
	}
	
	public static boolean checkPermissionByRoleIDAndColumnID(long roleID,int columnID){
		return mapOfColumnPermissionSetToRoleID
				.getOrDefault(roleID, Collections.emptySet())
				.contains(columnID);
	}
	
	
	public static PermissionRepository getInstance(){
		return instance;
	}
}
