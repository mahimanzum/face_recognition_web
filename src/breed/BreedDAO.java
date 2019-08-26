package breed;

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


public class BreedDAO  implements NavigationService{
	
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
		ps.setString(2,"breed");
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
		ps.setString(2,"breed");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(BreedDTO breedDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = breedDTO.iD;
		// userDTO.userName = breedDTO.email;
		// userDTO.fullName = breedDTO.name;
		// userDTO.password = breedDTO.password;
		// userDTO.phoneNo = breedDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = breedDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(BreedDTO breedDTO, UserDTO userDTO)
	{
		// userDTO.ID = breedDTO.iD;
		// userDTO.fullName = breedDTO.name;
		// userDTO.phoneNo = breedDTO.phone;
		// userDTO.mailAddress = breedDTO.email;

		return userDTO;
	}
		
		
	
	public void addBreed(BreedDTO breedDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			breedDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Breed");

			String sql = "INSERT INTO breed";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
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
			// userDAO.addUser(getUserDTO(breedDTO));
			// breedDTO.iD = userDAO.getUserDTOByUsername(breedDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + breedDTO.iD + " in index " + index);
			ps.setObject(index++,breedDTO.iD);
			//System.out.println("Setting object" + breedDTO.nameEn + " in index " + index);
			ps.setObject(index++,breedDTO.nameEn);
			//System.out.println("Setting object" + breedDTO.nameBn + " in index " + index);
			ps.setObject(index++,breedDTO.nameBn);
			//System.out.println("Setting object" + breedDTO.isDeleted + " in index " + index);
			ps.setObject(index++,breedDTO.isDeleted);
			//System.out.println("Setting object" + breedDTO.description + " in index " + index);
			ps.setObject(index++,breedDTO.description);
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
		//BreedRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public BreedDTO getBreedDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BreedDTO breedDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM breed";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				breedDTO = new BreedDTO();

				breedDTO.iD = rs.getLong("ID");
				breedDTO.nameEn = rs.getString("name_en");
				breedDTO.nameBn = rs.getString("name_bn");
				breedDTO.isDeleted = rs.getBoolean("isDeleted");
				breedDTO.description = rs.getString("description");

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
		return breedDTO;
	}
	
	public void updateBreed(BreedDTO breedDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE breed";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", ";
			sql += "description=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + breedDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + breedDTO.iD + " in index " + index);
			ps.setObject(index++,breedDTO.iD);
			//System.out.println("Setting object" + breedDTO.nameEn + " in index " + index);
			ps.setObject(index++,breedDTO.nameEn);
			//System.out.println("Setting object" + breedDTO.nameBn + " in index " + index);
			ps.setObject(index++,breedDTO.nameBn);
			//System.out.println("Setting object" + breedDTO.isDeleted + " in index " + index);
			ps.setObject(index++,breedDTO.isDeleted);
			//System.out.println("Setting object" + breedDTO.description + " in index " + index);
			ps.setObject(index++,breedDTO.description);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(breedDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(breedDTO, userDTO);
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
		//BreedRepository.getInstance().reload(false);
	}
	
	public void deleteBreedByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from breed WHERE ID = " + ID;
			
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
	
	public String getBreedNameByBullID(long bull_id){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		String breed_name = "";
		try{
			
			String sql = "select breed.name_en from bull join breed on bull.breed_type = breed.id where bull.id = " + bull_id;
			
			
			
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next()){
				breed_name = rs.getString("breed.name_en");

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
		return breed_name;
	}
	//2 versions, big table and small table
	//also a repo version
	//Returns a single DTO
	private List<BreedDTO> getBreedDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BreedDTO breedDTO = null;
		List<BreedDTO> breedDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM breed";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				breedDTO = new BreedDTO();
				breedDTO.iD = rs.getLong("ID");
				breedDTO.nameEn = rs.getString("name_en");
				breedDTO.nameBn = rs.getString("name_bn");
				breedDTO.isDeleted = rs.getBoolean("isDeleted");
				breedDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = breedDTO.iD;
				while(i < breedDTOList.size() && breedDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				breedDTOList.add(i,  breedDTO);
				//breedDTOList.add(breedDTO);
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
		return breedDTOList;
	}
	
	public List<BreedDTO> getBreedDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getBreedDTOByColumn(filter);
	}
	
	public List<BreedDTO> getBreedDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getBreedDTOByColumn(filter);
	}
	
	public List<BreedDTO> getBreedDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getBreedDTOByColumn(filter);
	}
		
	
	
	public List<BreedDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BreedDTO breedDTO = null;
		List<BreedDTO> breedDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return breedDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM breed";
            
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
				breedDTO = new BreedDTO();
				breedDTO.iD = rs.getLong("ID");
				breedDTO.nameEn = rs.getString("name_en");
				breedDTO.nameBn = rs.getString("name_bn");
				breedDTO.isDeleted = rs.getBoolean("isDeleted");
				breedDTO.description = rs.getString("description");
				System.out.println("got this DTO: " + breedDTO);
				//breedDTOList.add(breedDTO);
				int i = 0;
				long primaryKey = breedDTO.iD;
				while(i < breedDTOList.size() && breedDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				breedDTOList.add(i,  breedDTO);

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
		return breedDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM breed";

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
	public List<BreedDTO> getAllBreed (boolean isFirstReload)
    {
		List<BreedDTO> breedDTOList = new ArrayList<>();

		String sql = "SELECT * FROM breed";
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
				BreedDTO breedDTO = new BreedDTO();
				breedDTO.iD = rs.getLong("ID");
				breedDTO.nameEn = rs.getString("name_en");
				breedDTO.nameBn = rs.getString("name_bn");
				breedDTO.isDeleted = rs.getBoolean("isDeleted");
				breedDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = breedDTO.iD;
				while(i < breedDTOList.size() && breedDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				breedDTOList.add(i,  breedDTO);
				//breedDTOList.add(breedDTO);
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

		return breedDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM breed";
			
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

			String sql = "SELECT distinct breed.ID as ID FROM breed ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = BreedMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(BreedMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !BreedMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(BreedMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "breed." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "breed." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " breed.isDeleted = false";				
			
			
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
		BreedDTO breedDTO = null;
		List<BreedDTO> breedDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return breedDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = BreedMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				breedDTO = new BreedDTO();
				breedDTO.iD = rs.getLong("ID");
				breedDTO.nameEn = rs.getString("name_en");
				breedDTO.nameBn = rs.getString("name_bn");
				breedDTO.isDeleted = rs.getBoolean("isDeleted");
				breedDTO.description = rs.getString("description");
				i = 0;
				long primaryKey = breedDTO.iD;
				while(i < breedDTOList.size() && breedDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				breedDTOList.add(i,  breedDTO);

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
		return breedDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	