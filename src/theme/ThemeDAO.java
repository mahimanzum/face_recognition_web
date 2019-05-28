package theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import connection.DatabaseConnection;
import databasemanager.DatabaseManager;
import login.LoginDTO;
import repository.RepositoryManager;
import util.NavigationService;

import user.UserDTO;
import user.UserDAO;
import user.UserRepository;


public class ThemeDAO  implements NavigationService{
	
	Logger logger = Logger.getLogger(getClass());
	
	private void printSql(String sql)
	{
		 System.out.println("sql: " + sql);
	}
	
	private void printSqlUpdated(String sql)
	{
		 System.out.println("Updated sql: " + sql);
	}

	private void recordUpdateTime(Connection connection, PreparedStatement ps, long lastModificationTime) throws SQLException
	{
		String query = "UPDATE vbSequencer SET table_LastModificationTime=? WHERE table_name=?";
		ps = connection.prepareStatement(query);
		ps.setLong(1,lastModificationTime);
		ps.setString(2,"theme");
		ps.execute();
		ps.close();
	}
	
	private void recordUpdateTimeInUserTable(Connection connection, PreparedStatement ps, long lastModificationTime) throws SQLException
	{
		String query = "UPDATE vbSequencer SET table_LastModificationTime=? WHERE table_name=?";
		ps = connection.prepareStatement(query);
		ps.setLong(1,lastModificationTime);
		ps.setString(2,"user");
		ps.execute();
		ps.close();
	}
	
	
	private void addLastIDTovbSequencer(Connection connection, PreparedStatement ps, long id) throws SQLException
	{
		String query = "UPDATE vbSequencer SET next_id=? WHERE table_name=?";
		ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		ps.setString(2,"theme");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(ThemeDTO themeDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = themeDTO.iD;
		// userDTO.userName = themeDTO.email;
		// userDTO.fullName = themeDTO.name;
		// userDTO.password = themeDTO.password;
		// userDTO.phoneNo = themeDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = themeDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(ThemeDTO themeDTO, UserDTO userDTO)
	{
		// userDTO.ID = themeDTO.iD;
		// userDTO.fullName = themeDTO.name;
		// userDTO.phoneNo = themeDTO.phone;
		// userDTO.mailAddress = themeDTO.email;

		return userDTO;
	}
		
		
	
	public void addTheme(ThemeDTO themeDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			
			
			if(themeDTO.isApplied){
				unApplyAllThemes(connection, lastModificationTime);
			}
			
			

			themeDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Theme");

			String sql = "INSERT INTO theme";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "theme_name";
			sql += ", ";
			sql += "directory";
			sql += ", ";
			sql += "isApplied";
			sql += ", ";
			sql += "isDeleted";
			sql += ", lastModificationTime)";
			
			
            sql += " VALUES(";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(themeDTO));
			// themeDTO.iD = userDAO.getUserDTOByUsername(themeDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + themeDTO.iD + " in index " + index);
			ps.setObject(index++,themeDTO.iD);
			//System.out.println("Setting object" + themeDTO.themeName + " in index " + index);
			ps.setObject(index++,themeDTO.themeName);
			//System.out.println("Setting object" + themeDTO.directory + " in index " + index);
			ps.setObject(index++,themeDTO.directory);
			//System.out.println("Setting object" + themeDTO.isApplied + " in index " + index);
			ps.setObject(index++,themeDTO.isApplied);
			//System.out.println("Setting object" + themeDTO.isDeleted + " in index " + index);
			ps.setObject(index++,themeDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			recordUpdateTime(connection, ps, lastModificationTime);
			//recordUpdateTimeInUserTable(connection, ps, lastModificationTime);

		}catch(Exception ex){
			logger.debug("debug",ex);
			connection.rollback();
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
		ThemeRepository.getInstance().reload(false);		
	}
	
	
	private void unApplyAllThemes(Connection connection,long lastModificationTime) throws Exception{
		String sql = "UPDATE theme set isApplied=0 and lastModificationTime = "+lastModificationTime+" where isDeleted = 0";
		connection.createStatement().execute(sql);
	}
	
	//need another getter for repository
	public ThemeDTO getThemeDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		ThemeDTO themeDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "theme_name";
			sql += ", ";
			sql += "directory";
			sql += ", ";
			sql += "isApplied";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM theme";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				themeDTO = new ThemeDTO();

				themeDTO.iD = rs.getLong("ID");
				themeDTO.themeName = rs.getString("theme_name");
				themeDTO.directory = rs.getString("directory");
				themeDTO.isApplied = rs.getBoolean("isApplied");
				themeDTO.isDeleted = rs.getBoolean("isDeleted");

			}			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
		return themeDTO;
	}
	
	public void updateTheme(ThemeDTO themeDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			
			if(themeDTO.isApplied){
				unApplyAllThemes(connection, lastModificationTime);
			}

			
			
			String sql = "UPDATE theme";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "theme_name=?";
			sql += ", ";
			sql += "directory=?";
			sql += ", ";
			sql += "isApplied=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + themeDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + themeDTO.iD + " in index " + index);
			ps.setObject(index++,themeDTO.iD);
			//System.out.println("Setting object" + themeDTO.themeName + " in index " + index);
			ps.setObject(index++,themeDTO.themeName);
			//System.out.println("Setting object" + themeDTO.directory + " in index " + index);
			ps.setObject(index++,themeDTO.directory);
			//System.out.println("Setting object" + themeDTO.isApplied + " in index " + index);
			ps.setObject(index++,themeDTO.isApplied);
			//System.out.println("Setting object" + themeDTO.isDeleted + " in index " + index);
			ps.setObject(index++,themeDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(themeDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(themeDTO, userDTO);
				// System.out.println(userDTO);
				// userDAO.updateUser(userDTO);
			// }
			
			
			recordUpdateTime(connection, ps, lastModificationTime);
			// recordUpdateTimeInUserTable(connection, ps, lastModificationTime);
						
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
		ThemeRepository.getInstance().reload(false);
	}
	
	public void deleteThemeByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE theme";
			
			sql += " SET isDeleted=1,lastModificationTime="+ lastModificationTime +" WHERE ID = " + ID;
			
			printSql(sql);

			connection = DatabaseManager.getInstance().getConnection();
			stmt  = connection.createStatement();
			stmt.execute(sql);
			
			// UserDAO userDAO = new UserDAO();			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(ID);
			// userDAO.deleteUserByUserID(ID);
			
			recordUpdateTime(connection, ps, lastModificationTime);
			// recordUpdateTimeInUserTable(connection, ps, lastModificationTime);
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
	//2 versions, big table and small table
	//also a repo version
	//Returns a single DTO
	private List<ThemeDTO> getThemeDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		ThemeDTO themeDTO = null;
		List<ThemeDTO> themeDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "theme_name";
			sql += ", ";
			sql += "directory";
			sql += ", ";
			sql += "isApplied";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM theme";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next()){
				themeDTO = new ThemeDTO();
				themeDTO.iD = rs.getLong("ID");
				themeDTO.themeName = rs.getString("theme_name");
				themeDTO.directory = rs.getString("directory");
				themeDTO.isApplied = rs.getBoolean("isApplied");
				themeDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = themeDTO.iD;
				while(i < themeDTOList.size() && themeDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				themeDTOList.add(i,  themeDTO);
				//themeDTOList.add(themeDTO);
				// INSERTion sort

			}
						
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
		return themeDTOList;
	}
	
	public List<ThemeDTO> getThemeDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getThemeDTOByColumn(filter);
	}
	
	public List<ThemeDTO> getThemeDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getThemeDTOByColumn(filter);
	}
	
	public List<ThemeDTO> getThemeDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getThemeDTOByColumn(filter);
	}
		
	
	
	public List<ThemeDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		ThemeDTO themeDTO = null;
		List<ThemeDTO> themeDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return themeDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "theme_name";
			sql += ", ";
			sql += "directory";
			sql += ", ";
			sql += "isApplied";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM theme";
            
            sql += " WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=")";
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				themeDTO = new ThemeDTO();
				themeDTO.iD = rs.getLong("ID");
				themeDTO.themeName = rs.getString("theme_name");
				themeDTO.directory = rs.getString("directory");
				themeDTO.isApplied = rs.getBoolean("isApplied");
				themeDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + themeDTO);
				//themeDTOList.add(themeDTO);
				int i = 0;
				long primaryKey = themeDTO.iD;
				while(i < themeDTOList.size() && themeDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				themeDTOList.add(i,  themeDTO);

			}			
			
		}catch(Exception ex){
			System.out.println("got this database error: " + ex);
			System.out.println("Sql error: " + ex);
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
		return themeDTOList;
	
	}

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM theme";

		sql += " WHERE isDeleted = 0";
		
		printSql(sql);
		
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getString("ID")));
	
	        resultSet.close();
        }
        catch (Exception e)
        {
	    	logger.fatal("DAO " + e.toString(), e);
        }
	    finally
        {
	    	try
            {
          	  if(resultSet!= null && !resultSet.isClosed())
          	  {
          		  resultSet.close();
          	  }
            }
            catch(Exception ex)
            {
          	  
            }
          try{if (stmt != null){stmt.close();}}catch (Exception e){}
          try{if (connection != null){DatabaseManager.getInstance().freeConnection(connection);}}
          catch (Exception e){logger.fatal("DAO finally :" + e.toString());}
        }
        return data;
    }
	
	
	public String getAppliedThemeDescription() {
		
		String appliedThemeDescription = null;
		String sql = "SELECT directory FROM theme where isDeleted = 0 and isApplied = 1 limit 1";
				printSql(sql);
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			

			if(rs.next()){
				appliedThemeDescription = rs.getString(1);
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
		return appliedThemeDescription;
	}
	
	//add repository
	public List<ThemeDTO> getAllTheme (boolean isFirstReload)
    {
		List<ThemeDTO> themeDTOList = new ArrayList<>();

		String sql = "SELECT * FROM theme";
		sql += " WHERE ";
	

		if(isFirstReload){
			sql+=" isDeleted =  0";
		}
		if(!isFirstReload){
			sql+=" lastModificationTime >= " + RepositoryManager.lastModifyTime;		
		}
		printSql(sql);
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			

			while(rs.next()){
				ThemeDTO themeDTO = new ThemeDTO();
				themeDTO.iD = rs.getLong("ID");
				themeDTO.themeName = rs.getString("theme_name");
				themeDTO.directory = rs.getString("directory");
				themeDTO.isApplied = rs.getBoolean("isApplied");
				themeDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = themeDTO.iD;
				while(i < themeDTOList.size() && themeDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				themeDTOList.add(i,  themeDTO);
				//themeDTOList.add(themeDTO);
			}			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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

		return themeDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM theme";
			
			sql += " WHERE isDeleted = 0";

				
			sql+= " AND  ";  
			sql+= filter;

			printSql(sql);
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong("ID"));
			}
			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				} 
			}catch(Exception ex2){}
		}
		return idList;
	}
	
	public List<Long> getIDsWithSearchCriteria(String column, String value){
		String filter = column + " LIKE '" + value + "'";
		return getIDsWithSearchCriteria(filter);
	}
	
	public List<Long> getIDsWithSearchCriteria(String column, int value){
		String filter = column + " = " + value;
		return getIDsWithSearchCriteria(filter);
	}
	
	public List<Long> getIDsWithSearchCriteria(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getIDsWithSearchCriteria(filter);
	}
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		
		ThemeDTO themeDTO = new ThemeDTO();
		try{

			String sql = "SELECT ID FROM theme";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = themeDTO.java_custom_search_map.entrySet().iterator();
				while(it.hasNext())
				{
					if( i > 0)
		        	{
						AnyfieldSql+= " OR  ";
		        	}
					Map.Entry pair = (Map.Entry)it.next();
					AnyfieldSql+= pair.getKey() + " like '%" + p_searchCriteria.get("AnyField").toString() + "%'";
					i ++;
				}						
			}
			AnyfieldSql += ")";
			System.out.println("AnyfieldSql = " + AnyfieldSql);
			
			String AllFieldSql = "(";
			int i = 0;
			while(names.hasMoreElements())
			{				
				str = (String) names.nextElement();
				value = (String)p_searchCriteria.get(str);
		        System.out.println(str + ": " + value);
		        if(themeDTO.java_type_map.get(str.toLowerCase()) != null &&  !themeDTO.java_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
		        		&& !str.equalsIgnoreCase("AnyField")
		        		&& value != null && !value.equalsIgnoreCase(""))
		        {
		        	if( i > 0)
		        	{
		        		AllFieldSql+= " AND  ";
		        	}
		        	if(themeDTO.java_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " isDeleted = false";				
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				if(!AllFieldSql.equals("()"))
				{
					sql += " AND ";
				}
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += AllFieldSql;
			}
			
			sql += " AND isDeleted = false";

			printSql(sql);
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong("ID"));
			}
			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				} 
			}catch(Exception ex2){}
		}
		return idList;
	}



		
}
	