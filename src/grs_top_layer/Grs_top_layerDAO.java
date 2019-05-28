package grs_top_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;
import login.LoginDTO;
import repository.RepositoryManager;
import util.NavigationService;

import user.UserDTO;
import user.UserDAO;
import user.UserRepository;


public class Grs_top_layerDAO  implements NavigationService{
	
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
		ps.setString(2,"grs_top_layer");
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
		ps.setString(2,"grs_top_layer");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Grs_top_layerDTO grs_top_layerDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = grs_top_layerDTO.iD;
		// userDTO.userName = grs_top_layerDTO.email;
		// userDTO.fullName = grs_top_layerDTO.name;
		// userDTO.password = grs_top_layerDTO.password;
		// userDTO.phoneNo = grs_top_layerDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = grs_top_layerDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Grs_top_layerDTO grs_top_layerDTO, UserDTO userDTO)
	{
		// userDTO.ID = grs_top_layerDTO.iD;
		// userDTO.fullName = grs_top_layerDTO.name;
		// userDTO.phoneNo = grs_top_layerDTO.phone;
		// userDTO.mailAddress = grs_top_layerDTO.email;

		return userDTO;
	}
		
		
	
	public void addGrs_top_layer(Grs_top_layerDTO grs_top_layerDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			grs_top_layerDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Grs_top_layer");

			String sql = "INSERT INTO grs_top_layer";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "layer_number";
			sql += ", ";
			sql += "description";
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
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(grs_top_layerDTO));
			// grs_top_layerDTO.iD = userDAO.getUserDTOByUsername(grs_top_layerDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + grs_top_layerDTO.iD + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.iD);
			//System.out.println("Setting object" + grs_top_layerDTO.nameEn + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.nameEn);
			//System.out.println("Setting object" + grs_top_layerDTO.nameBn + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.nameBn);
			//System.out.println("Setting object" + grs_top_layerDTO.layerNumber + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.layerNumber);
			//System.out.println("Setting object" + grs_top_layerDTO.description + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.description);
			//System.out.println("Setting object" + grs_top_layerDTO.isDeleted + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			recordUpdateTime(connection, ps, lastModificationTime);
			//recordUpdateTimeInUserTable(connection, ps, lastModificationTime);

		}catch(Exception ex){
			System.out.println("ex = " + ex);
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
		//Grs_top_layerRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Grs_top_layerDTO getGrs_top_layerDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Grs_top_layerDTO grs_top_layerDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "layer_number";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM grs_top_layer";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				grs_top_layerDTO = new Grs_top_layerDTO();

				grs_top_layerDTO.iD = rs.getLong("ID");
				grs_top_layerDTO.nameEn = rs.getString("name_en");
				grs_top_layerDTO.nameBn = rs.getString("name_bn");
				grs_top_layerDTO.layerNumber = rs.getInt("layer_number");
				grs_top_layerDTO.description = rs.getString("description");
				grs_top_layerDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return grs_top_layerDTO;
	}
	
	public void updateGrs_top_layer(Grs_top_layerDTO grs_top_layerDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE grs_top_layer";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "layer_number=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + grs_top_layerDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + grs_top_layerDTO.iD + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.iD);
			//System.out.println("Setting object" + grs_top_layerDTO.nameEn + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.nameEn);
			//System.out.println("Setting object" + grs_top_layerDTO.nameBn + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.nameBn);
			//System.out.println("Setting object" + grs_top_layerDTO.layerNumber + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.layerNumber);
			//System.out.println("Setting object" + grs_top_layerDTO.description + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.description);
			//System.out.println("Setting object" + grs_top_layerDTO.isDeleted + " in index " + index);
			ps.setObject(index++,grs_top_layerDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(grs_top_layerDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(grs_top_layerDTO, userDTO);
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
		//Grs_top_layerRepository.getInstance().reload(false);
	}
	
	public void deleteGrs_top_layerByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE grs_top_layer";
			
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
	private List<Grs_top_layerDTO> getGrs_top_layerDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Grs_top_layerDTO grs_top_layerDTO = null;
		List<Grs_top_layerDTO> grs_top_layerDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "layer_number";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM grs_top_layer";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				grs_top_layerDTO = new Grs_top_layerDTO();
				grs_top_layerDTO.iD = rs.getLong("ID");
				grs_top_layerDTO.nameEn = rs.getString("name_en");
				grs_top_layerDTO.nameBn = rs.getString("name_bn");
				grs_top_layerDTO.layerNumber = rs.getInt("layer_number");
				grs_top_layerDTO.description = rs.getString("description");
				grs_top_layerDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = grs_top_layerDTO.iD;
				while(i < grs_top_layerDTOList.size() && grs_top_layerDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				grs_top_layerDTOList.add(i,  grs_top_layerDTO);
				//grs_top_layerDTOList.add(grs_top_layerDTO);
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
		return grs_top_layerDTOList;
	}
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getGrs_top_layerDTOByColumn(filter);
	}
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getGrs_top_layerDTOByColumn(filter);
	}
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getGrs_top_layerDTOByColumn(filter);
	}
		
	
	
	public List<Grs_top_layerDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Grs_top_layerDTO grs_top_layerDTO = null;
		List<Grs_top_layerDTO> grs_top_layerDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return grs_top_layerDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "layer_number";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM grs_top_layer";
            
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
				grs_top_layerDTO = new Grs_top_layerDTO();
				grs_top_layerDTO.iD = rs.getLong("ID");
				grs_top_layerDTO.nameEn = rs.getString("name_en");
				grs_top_layerDTO.nameBn = rs.getString("name_bn");
				grs_top_layerDTO.layerNumber = rs.getInt("layer_number");
				grs_top_layerDTO.description = rs.getString("description");
				grs_top_layerDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + grs_top_layerDTO);
				//grs_top_layerDTOList.add(grs_top_layerDTO);
				int i = 0;
				long primaryKey = grs_top_layerDTO.iD;
				while(i < grs_top_layerDTOList.size() && grs_top_layerDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				grs_top_layerDTOList.add(i,  grs_top_layerDTO);

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
		return grs_top_layerDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM grs_top_layer";

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
	
	//add repository
	public List<Grs_top_layerDTO> getAllGrs_top_layer (boolean isFirstReload)
    {
		List<Grs_top_layerDTO> grs_top_layerDTOList = new ArrayList<>();

		String sql = "SELECT * FROM grs_top_layer";
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
				Grs_top_layerDTO grs_top_layerDTO = new Grs_top_layerDTO();
				grs_top_layerDTO.iD = rs.getLong("ID");
				grs_top_layerDTO.nameEn = rs.getString("name_en");
				grs_top_layerDTO.nameBn = rs.getString("name_bn");
				grs_top_layerDTO.layerNumber = rs.getInt("layer_number");
				grs_top_layerDTO.description = rs.getString("description");
				grs_top_layerDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = grs_top_layerDTO.iD;
				while(i < grs_top_layerDTOList.size() && grs_top_layerDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				grs_top_layerDTOList.add(i,  grs_top_layerDTO);
				//grs_top_layerDTOList.add(grs_top_layerDTO);
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

		return grs_top_layerDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM grs_top_layer";
			
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
		
		try{

			String sql = "SELECT distinct grs_top_layer.ID as ID FROM grs_top_layer ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Grs_top_layerMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Grs_top_layerMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Grs_top_layerMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
		        		&& !str.equalsIgnoreCase("AnyField")
		        		&& value != null && !value.equalsIgnoreCase(""))
		        {
					if(p_searchCriteria.get(str).equals("any"))
		        	{
		        		continue;
		        	}
					
		        	if( i > 0)
		        	{
		        		AllFieldSql+= " AND  ";
		        	}
		        	if(Grs_top_layerMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "grs_top_layer." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "grs_top_layer." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " grs_top_layer.isDeleted = false";				
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			

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
	
	
	public Collection getDTOs(Collection recordIDs, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception 
	{
		
		ArrayList<long[]> idList = (ArrayList<long[]>)recordIDs;
		Connection connection = null;
		PreparedStatement ps = null;
		Grs_top_layerDTO grs_top_layerDTO = null;
		List<Grs_top_layerDTO> grs_top_layerDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return grs_top_layerDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Grs_top_layerMAPS.GetInstance().java_SQL_map.entrySet().iterator();
			int i = 0;
			while(it.hasNext())
			{
				if( i > 0)
	        	{
					sql+= " ,  ";
	        	}
				Map.Entry pair = (Map.Entry)it.next();
				sql+= pair.getKey();
				i ++;
			}		
			sql += " FROM ";
			
			for(i = 0; i < table_names.size(); i ++)
			{
				if(i > 0)
				{
					sql += (" inner join ");
				}
				sql += table_names.get(i) + " ";
			}
			
			sql += " WHERE ";
			
			for(i = 0; i < idList.size(); i ++)
			{
				long[] ids = idList.get(i);
				if(i > 0)
				{
					sql += (" OR ");
				}
				sql += " ( ";
				for(int j = 0; j < table_names.size(); j ++)
				{
					if(j > 0)
					{
						sql += (" AND ");
					}
					sql += table_names.get(j) + ".ID = " + ids[j] + " AND " + table_names.get(j) + ".isDeleted = false";
				}
				sql += " ) ";
			}
			
			
			

			printSql(sql);
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				grs_top_layerDTO = new Grs_top_layerDTO();
				grs_top_layerDTO.iD = rs.getLong("ID");
				grs_top_layerDTO.nameEn = rs.getString("name_en");
				grs_top_layerDTO.nameBn = rs.getString("name_bn");
				grs_top_layerDTO.layerNumber = rs.getInt("layer_number");
				grs_top_layerDTO.description = rs.getString("description");
				grs_top_layerDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = grs_top_layerDTO.iD;
				while(i < grs_top_layerDTOList.size() && grs_top_layerDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				grs_top_layerDTOList.add(i,  grs_top_layerDTO);

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
		return grs_top_layerDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	