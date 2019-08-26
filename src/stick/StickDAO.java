package stick;

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


public class StickDAO  implements NavigationService{
	
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
		ps.setString(2,"stick");
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
		ps.setString(2,"stick");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(StickDTO stickDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = stickDTO.iD;
		// userDTO.userName = stickDTO.email;
		// userDTO.fullName = stickDTO.name;
		// userDTO.password = stickDTO.password;
		// userDTO.phoneNo = stickDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = stickDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(StickDTO stickDTO, UserDTO userDTO)
	{
		// userDTO.ID = stickDTO.iD;
		// userDTO.fullName = stickDTO.name;
		// userDTO.phoneNo = stickDTO.phone;
		// userDTO.mailAddress = stickDTO.email;

		return userDTO;
	}
		
		
	
	public void addStick(StickDTO stickDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			stickDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Stick");

			String sql = "INSERT INTO stick";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "semen_collection_id";
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
				
			//printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(stickDTO));
			// stickDTO.iD = userDAO.getUserDTOByUsername(stickDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + stickDTO.iD + " in index " + index);
			ps.setObject(index++,stickDTO.iD);
			//System.out.println("Setting object" + stickDTO.nameEn + " in index " + index);
			ps.setObject(index++,stickDTO.nameEn);
			//System.out.println("Setting object" + stickDTO.nameBn + " in index " + index);
			ps.setObject(index++,stickDTO.nameBn);
			//System.out.println("Setting object" + stickDTO.semenCollectionId + " in index " + index);
			ps.setObject(index++,stickDTO.semenCollectionId);
			//System.out.println("Setting object" + stickDTO.isDeleted + " in index " + index);
			ps.setObject(index++,stickDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			//System.out.println(ps);
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
		//StickRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public StickDTO getStickDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StickDTO stickDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "semen_collection_id";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM stick";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				stickDTO = new StickDTO();

				stickDTO.iD = rs.getLong("ID");
				stickDTO.nameEn = rs.getString("name_en");
				stickDTO.nameBn = rs.getString("name_bn");
				stickDTO.semenCollectionId = rs.getLong("semen_collection_id");
				stickDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return stickDTO;
	}
	
	public void updateStick(StickDTO stickDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE stick";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "semen_collection_id=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + stickDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + stickDTO.iD + " in index " + index);
			ps.setObject(index++,stickDTO.iD);
			//System.out.println("Setting object" + stickDTO.nameEn + " in index " + index);
			ps.setObject(index++,stickDTO.nameEn);
			//System.out.println("Setting object" + stickDTO.nameBn + " in index " + index);
			ps.setObject(index++,stickDTO.nameBn);
			//System.out.println("Setting object" + stickDTO.semenCollectionId + " in index " + index);
			ps.setObject(index++,stickDTO.semenCollectionId);
			//System.out.println("Setting object" + stickDTO.isDeleted + " in index " + index);
			ps.setObject(index++,stickDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(stickDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(stickDTO, userDTO);
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
		//StickRepository.getInstance().reload(false);
	}
	
	public void deleteStickByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE stick";
			
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
	private List<StickDTO> getStickDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StickDTO stickDTO = null;
		List<StickDTO> stickDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "semen_collection_id";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM stick";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				stickDTO = new StickDTO();
				stickDTO.iD = rs.getLong("ID");
				stickDTO.nameEn = rs.getString("name_en");
				stickDTO.nameBn = rs.getString("name_bn");
				stickDTO.semenCollectionId = rs.getLong("semen_collection_id");
				stickDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = stickDTO.iD;
				while(i < stickDTOList.size() && stickDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				stickDTOList.add(i,  stickDTO);
				//stickDTOList.add(stickDTO);
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
		return stickDTOList;
	}
	
	public List<StickDTO> getStickDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getStickDTOByColumn(filter);
	}
	
	public List<StickDTO> getStickDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getStickDTOByColumn(filter);
	}
	
	public List<StickDTO> getStickDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getStickDTOByColumn(filter);
	}
		
	
	
	public List<StickDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StickDTO stickDTO = null;
		List<StickDTO> stickDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return stickDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "semen_collection_id";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM stick";
            
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
				stickDTO = new StickDTO();
				stickDTO.iD = rs.getLong("ID");
				stickDTO.nameEn = rs.getString("name_en");
				stickDTO.nameBn = rs.getString("name_bn");
				stickDTO.semenCollectionId = rs.getLong("semen_collection_id");
				stickDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + stickDTO);
				//stickDTOList.add(stickDTO);
				int i = 0;
				long primaryKey = stickDTO.iD;
				while(i < stickDTOList.size() && stickDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				stickDTOList.add(i,  stickDTO);

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
		return stickDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM stick";

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
	public List<StickDTO> getAllStick (boolean isFirstReload)
    {
		List<StickDTO> stickDTOList = new ArrayList<>();

		String sql = "SELECT * FROM stick";
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
				StickDTO stickDTO = new StickDTO();
				stickDTO.iD = rs.getLong("ID");
				stickDTO.nameEn = rs.getString("name_en");
				stickDTO.nameBn = rs.getString("name_bn");
				stickDTO.semenCollectionId = rs.getLong("semen_collection_id");
				stickDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = stickDTO.iD;
				while(i < stickDTOList.size() && stickDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				stickDTOList.add(i,  stickDTO);
				//stickDTOList.add(stickDTO);
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

		return stickDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM stick";
			
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

			String sql = "SELECT ID FROM stick";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = StickMAPS.GetInstance().java_custom_search_map.entrySet().iterator();
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
		        if(StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()) != null &&  !StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
		        		&& !str.equalsIgnoreCase("AnyField")
		        		&& value != null && !value.equalsIgnoreCase(""))
		        {
		        	if( i > 0)
		        	{
		        		AllFieldSql+= " AND  ";
		        	}
		        	if(StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()).equals("String")) 
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
		StickDTO stickDTO = null;
		List<StickDTO> stickDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return stickDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = StickMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				stickDTO = new StickDTO();
				stickDTO.iD = rs.getLong("ID");
				stickDTO.nameEn = rs.getString("name_en");
				stickDTO.nameBn = rs.getString("name_bn");
				stickDTO.semenCollectionId = rs.getLong("semen_collection_id");
				stickDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = stickDTO.iD;
				while(i < stickDTOList.size() && stickDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				stickDTOList.add(i,  stickDTO);

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
		return stickDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		long[] ids = new long[table_names.size()];
		List<long[]> idList = new ArrayList<long[]>();
		Connection connection = null;
		PreparedStatement ps = null;
		

		try{

			String sql = "SELECT ";
			for(int i = 0; i < table_names.size(); i ++)
			{
				if(i > 0)
				{
					sql += (" , ");
				}
				sql += table_names.get(i) + ".ID as " + table_names.get(i)+ "_ID";
			}
			sql +=  table_names.get(0) + ".ID ";
			sql += " FROM ";
			
			for(int i = 0; i < table_names.size(); i ++)
			{
				if(i > 0)
				{
					sql += (" INNER JOIN ");
				}
				sql += table_names.get(i) + " ";
				sql += "ON " + table_names.get(0) + "." + table_names.get(i) + "_ID" +  " = " + table_names.get(i) + ".ID";
			}
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = StickMAPS.GetInstance().java_custom_search_map.entrySet().iterator();
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
		        if(StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()) != null &&  !StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
		        		&& !str.equalsIgnoreCase("AnyField")
		        		&& value != null && !value.equalsIgnoreCase(""))
		        {
		        	if( i > 0)
		        	{
		        		AllFieldSql+= " AND  ";
		        	}
		        	if(StickMAPS.GetInstance().java_type_map.get(str.toLowerCase()).equals("String")) 
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
			
			for(int j = 0; j < table_names.size(); j ++)
			{
				if(j > 0)
				{
					sql += (" AND ");
				}
				sql += table_names.get(j) + ".isDeleted = false";
			}				
			
			
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
			while(rs.next())
			{
				for(int j = 0; j < table_names.size(); j ++)
				{
					ids[j] = rs.getLong(table_names.get(j) + "_ID");
				}
				idList.add(ids);
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
	