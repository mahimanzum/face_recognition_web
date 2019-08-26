package status;

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


public class StatusDAO  implements NavigationService{
	
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
		ps.setString(2,"status");
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
		ps.setString(2,"status");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(StatusDTO statusDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = statusDTO.iD;
		// userDTO.userName = statusDTO.email;
		// userDTO.fullName = statusDTO.name;
		// userDTO.password = statusDTO.password;
		// userDTO.phoneNo = statusDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = statusDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(StatusDTO statusDTO, UserDTO userDTO)
	{
		// userDTO.ID = statusDTO.iD;
		// userDTO.fullName = statusDTO.name;
		// userDTO.phoneNo = statusDTO.phone;
		// userDTO.mailAddress = statusDTO.email;

		return userDTO;
	}
		
		
	
	public void addStatus(StatusDTO statusDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			statusDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Status");

			String sql = "INSERT INTO status";
			
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
			// userDAO.addUser(getUserDTO(statusDTO));
			// statusDTO.iD = userDAO.getUserDTOByUsername(statusDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + statusDTO.iD + " in index " + index);
			ps.setObject(index++,statusDTO.iD);
			//System.out.println("Setting object" + statusDTO.nameEn + " in index " + index);
			ps.setObject(index++,statusDTO.nameEn);
			//System.out.println("Setting object" + statusDTO.nameBn + " in index " + index);
			ps.setObject(index++,statusDTO.nameBn);
			//System.out.println("Setting object" + statusDTO.isDeleted + " in index " + index);
			ps.setObject(index++,statusDTO.isDeleted);
			//System.out.println("Setting object" + statusDTO.description + " in index " + index);
			ps.setObject(index++,statusDTO.description);
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
		//StatusRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public StatusDTO getStatusDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StatusDTO statusDTO = null;
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
			sql += " FROM status";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				statusDTO = new StatusDTO();

				statusDTO.iD = rs.getLong("ID");
				statusDTO.nameEn = rs.getString("name_en");
				statusDTO.nameBn = rs.getString("name_bn");
				statusDTO.isDeleted = rs.getBoolean("isDeleted");
				statusDTO.description = rs.getString("description");

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
		return statusDTO;
	}
	
	public void updateStatus(StatusDTO statusDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE status";
			
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
            sql += " WHERE ID = " + statusDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + statusDTO.iD + " in index " + index);
			ps.setObject(index++,statusDTO.iD);
			//System.out.println("Setting object" + statusDTO.nameEn + " in index " + index);
			ps.setObject(index++,statusDTO.nameEn);
			//System.out.println("Setting object" + statusDTO.nameBn + " in index " + index);
			ps.setObject(index++,statusDTO.nameBn);
			//System.out.println("Setting object" + statusDTO.isDeleted + " in index " + index);
			ps.setObject(index++,statusDTO.isDeleted);
			//System.out.println("Setting object" + statusDTO.description + " in index " + index);
			ps.setObject(index++,statusDTO.description);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(statusDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(statusDTO, userDTO);
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
		//StatusRepository.getInstance().reload(false);
	}
	
	public void deleteStatusByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from status WHERE ID = " + ID;
			
			printSql(sql);

			connection = DatabaseManager.getInstance().getConnection();
			stmt  = connection.createStatement();
			stmt.execute(sql);
			
			sql = "delete from bull WHERE status_type = " + ID;
			

			stmt.execute(sql);
			
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
	private List<StatusDTO> getStatusDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StatusDTO statusDTO = null;
		List<StatusDTO> statusDTOList = new ArrayList<>();
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
			sql += " FROM status";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				statusDTO = new StatusDTO();
				statusDTO.iD = rs.getLong("ID");
				statusDTO.nameEn = rs.getString("name_en");
				statusDTO.nameBn = rs.getString("name_bn");
				statusDTO.isDeleted = rs.getBoolean("isDeleted");
				statusDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = statusDTO.iD;
				while(i < statusDTOList.size() && statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				statusDTOList.add(i,  statusDTO);
				//statusDTOList.add(statusDTO);
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
		return statusDTOList;
	}
	
	public List<StatusDTO> getStatusDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getStatusDTOByColumn(filter);
	}
	
	public List<StatusDTO> getStatusDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getStatusDTOByColumn(filter);
	}
	
	public List<StatusDTO> getStatusDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getStatusDTOByColumn(filter);
	}
		
	
	
	public List<StatusDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		StatusDTO statusDTO = null;
		List<StatusDTO> statusDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return statusDTOList;
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
			sql += " FROM status";
            
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
				statusDTO = new StatusDTO();
				statusDTO.iD = rs.getLong("ID");
				statusDTO.nameEn = rs.getString("name_en");
				statusDTO.nameBn = rs.getString("name_bn");
				statusDTO.isDeleted = rs.getBoolean("isDeleted");
				statusDTO.description = rs.getString("description");
				System.out.println("got this DTO: " + statusDTO);
				//statusDTOList.add(statusDTO);
				int i = 0;
				long primaryKey = statusDTO.iD;
				while(i < statusDTOList.size() && statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				statusDTOList.add(i,  statusDTO);

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
		return statusDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM status";

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
	public List<StatusDTO> getAllStatus (boolean isFirstReload)
    {
		List<StatusDTO> statusDTOList = new ArrayList<>();

		String sql = "SELECT * FROM status";
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
				StatusDTO statusDTO = new StatusDTO();
				statusDTO.iD = rs.getLong("ID");
				statusDTO.nameEn = rs.getString("name_en");
				statusDTO.nameBn = rs.getString("name_bn");
				statusDTO.isDeleted = rs.getBoolean("isDeleted");
				statusDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = statusDTO.iD;
				while(i < statusDTOList.size() && statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				statusDTOList.add(i,  statusDTO);
				//statusDTOList.add(statusDTO);
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

		return statusDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM status";
			
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

			String sql = "SELECT distinct status.ID as ID FROM status ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = StatusMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(StatusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !StatusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(StatusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "status." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "status." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " status.isDeleted = false";				
			
			
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
		StatusDTO statusDTO = null;
		List<StatusDTO> statusDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return statusDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = StatusMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				statusDTO = new StatusDTO();
				statusDTO.iD = rs.getLong("ID");
				statusDTO.nameEn = rs.getString("name_en");
				statusDTO.nameBn = rs.getString("name_bn");
				statusDTO.isDeleted = rs.getBoolean("isDeleted");
				statusDTO.description = rs.getString("description");
				i = 0;
				long primaryKey = statusDTO.iD;
				while(i < statusDTOList.size() && statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				statusDTOList.add(i,  statusDTO);

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
		return statusDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	