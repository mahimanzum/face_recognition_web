package permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;

public class MenuDAO {
	Logger logger = Logger.getLogger(MenuDAO.class);
	
	
	
	public void deleteMenuByIDList(List<Integer> menuIDList){
		
		if(menuIDList.isEmpty()){
			return;
		}
		
		String sql = "DELETE FROM menu WHERE menuID IN (";
		
		for(int i=0;i<menuIDList.size();i++){
			if(i!=0){
				sql+=",";
			}
			sql+=menuIDList.get(i);
		}
		sql+=")";
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

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
	
		MenuRepository.reload();
		
	}
	
	
	public void addMenuDTO(MenuDTO menuDTO){
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			menuDTO.menuID = (int)DatabaseManager.getInstance().getNextSequenceId("menu");

			String sql ="insert into menu(menuID,parentMenuID,menuName,hyperLink,languageTextID,orderIndex,selectedMenuID,isVisible,requestMethodType,icon,isAPI,menuNameBangla,constantName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,menuDTO.menuID);
			ps.setObject(index++,menuDTO.parentMenuID);
			ps.setObject(index++,menuDTO.menuName);
			ps.setObject(index++,menuDTO.hyperLink);
			ps.setObject(index++,menuDTO.languageTextID);
			ps.setObject(index++,menuDTO.orderIndex);
			ps.setObject(index++,menuDTO.selectedMenuID);
			ps.setObject(index++,menuDTO.isVisible);
			ps.setObject(index++,menuDTO.requestMethodType);
			ps.setObject(index++,menuDTO.icon);
			ps.setObject(index++,menuDTO.isAPI);
			ps.setObject(index++,menuDTO.menuNameBangla);
			ps.setObject(index++, menuDTO.constant);
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
	
		MenuRepository.reload();

	}
	
	
	public void updateMenuDTO(MenuDTO menuDTO){
	
		
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();
	
			String sql ="UPDATE menu SET parentMenuID=?,menuName=?,hyperLink=?,languageTextID=?,orderIndex=?,selectedMenuID=?,isVisible=?,requestMethodType=?,icon=?,isAPI=?,menuNameBangla=?,constantName=?  WHERE menuID = ?";
	
			ps = connection.prepareStatement(sql);
	
			int index = 1;
	
			ps.setObject(index++,menuDTO.parentMenuID);
			ps.setObject(index++,menuDTO.menuName);
			ps.setObject(index++,menuDTO.hyperLink);
			ps.setObject(index++,menuDTO.languageTextID);
			ps.setObject(index++,menuDTO.orderIndex);
			ps.setObject(index++,menuDTO.selectedMenuID);
			ps.setObject(index++,menuDTO.isVisible);
			ps.setObject(index++,menuDTO.requestMethodType);
			ps.setObject(index++,menuDTO.icon);
			ps.setObject(index++,menuDTO.isAPI);
			ps.setObject(index++,menuDTO.menuNameBangla);
			ps.setObject(index, menuDTO.constant);
			ps.setObject(index++,menuDTO.menuID);
			
	
			ps.executeUpdate();
	
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

	
	
    public List<MenuDTO> getAllMenuList(){
		List<MenuDTO> menuDTOList = new ArrayList<>();

		String sql = "SELECT * FROM menu ORDER BY orderIndex";

		Connection connection = null;
		Statement stmt = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				MenuDTO menuDTO = new MenuDTO();
				menuDTO.menuID = rs.getInt("menuID");
				menuDTO.parentMenuID = rs.getInt("parentMenuID");
				menuDTO.menuName = rs.getString("menuName");
				menuDTO.hyperLink = rs.getString("hyperLink");
				menuDTO.languageTextID = rs.getInt("languageTextID");
				menuDTO.orderIndex = rs.getInt("orderIndex");
				menuDTO.selectedMenuID = rs.getInt("selectedMenuID");
				menuDTO.isVisible = (rs.getInt("isVisible")==1);
				menuDTO.requestMethodType = rs.getInt("requestMethodType");
				menuDTO.icon = rs.getString("icon");
				menuDTO.isAPI = (rs.getInt("isAPI")==1);
				menuDTO.menuNameBangla = rs.getString("menuNameBangla");
				menuDTO.constant = rs.getString("constantName");
				menuDTOList.add(menuDTO);
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
		return menuDTOList;
	}
	
	
	
	
	public void updateMenuDTOs(List<MenuDTO> menuDTOList)
	{
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			String sql ="UPDATE menu SET parentMenuID=?,menuName=?,hyperLink=?,languageTextID=?,orderIndex=?,selectedMenuID=?,isVisible=?,requestMethodType=?,icon=?,isAPI=?,menuNameBangla=?,constantName=?  WHERE menuID = ?";
			ps = connection.prepareStatement(sql);
			for(MenuDTO menuDTO: menuDTOList){
				int index = 1;
				ps.setObject(index++,menuDTO.parentMenuID);
				ps.setObject(index++,menuDTO.menuName);
				ps.setObject(index++,menuDTO.hyperLink);
				ps.setObject(index++,menuDTO.languageTextID);
				ps.setObject(index++,menuDTO.orderIndex);
				ps.setObject(index++,menuDTO.selectedMenuID);
				ps.setObject(index++,menuDTO.isVisible);
				ps.setObject(index++,menuDTO.requestMethodType);
				ps.setObject(index++,menuDTO.icon);
				ps.setObject(index++,menuDTO.isAPI);
				ps.setObject(index++,menuDTO.menuNameBangla);
				ps.setObject(index++,menuDTO.constant);
				ps.setObject(index++,menuDTO.menuID);
				
				ps.executeUpdate();
			}
	
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
		
		MenuRepository.reload();
	}
}
