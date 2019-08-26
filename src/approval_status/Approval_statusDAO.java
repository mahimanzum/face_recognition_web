package approval_status;

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


public class Approval_statusDAO  implements NavigationService{
	
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
		ps.setString(2,"approval_status");
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
		ps.setString(2,"approval_status");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Approval_statusDTO approval_statusDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = approval_statusDTO.iD;
		// userDTO.userName = approval_statusDTO.email;
		// userDTO.fullName = approval_statusDTO.name;
		// userDTO.password = approval_statusDTO.password;
		// userDTO.phoneNo = approval_statusDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = approval_statusDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Approval_statusDTO approval_statusDTO, UserDTO userDTO)
	{
		// userDTO.ID = approval_statusDTO.iD;
		// userDTO.fullName = approval_statusDTO.name;
		// userDTO.phoneNo = approval_statusDTO.phone;
		// userDTO.mailAddress = approval_statusDTO.email;

		return userDTO;
	}
		
		
	
	public void addApproval_status(Approval_statusDTO approval_statusDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			approval_statusDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Approval_status");

			String sql = "INSERT INTO approval_status";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
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
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(approval_statusDTO));
			// approval_statusDTO.iD = userDAO.getUserDTOByUsername(approval_statusDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + approval_statusDTO.iD + " in index " + index);
			ps.setObject(index++,approval_statusDTO.iD);
			//System.out.println("Setting object" + approval_statusDTO.nameEn + " in index " + index);
			ps.setObject(index++,approval_statusDTO.nameEn);
			//System.out.println("Setting object" + approval_statusDTO.nameBn + " in index " + index);
			ps.setObject(index++,approval_statusDTO.nameBn);
			//System.out.println("Setting object" + approval_statusDTO.isDeleted + " in index " + index);
			ps.setObject(index++,approval_statusDTO.isDeleted);
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
		//Approval_statusRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Approval_statusDTO getApproval_statusDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Approval_statusDTO approval_statusDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM approval_status";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				approval_statusDTO = new Approval_statusDTO();

				approval_statusDTO.iD = rs.getLong("ID");
				approval_statusDTO.nameEn = rs.getString("name_en");
				approval_statusDTO.nameBn = rs.getString("name_bn");
				approval_statusDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return approval_statusDTO;
	}
	
	public void updateApproval_status(Approval_statusDTO approval_statusDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE approval_status";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + approval_statusDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + approval_statusDTO.iD + " in index " + index);
			ps.setObject(index++,approval_statusDTO.iD);
			//System.out.println("Setting object" + approval_statusDTO.nameEn + " in index " + index);
			ps.setObject(index++,approval_statusDTO.nameEn);
			//System.out.println("Setting object" + approval_statusDTO.nameBn + " in index " + index);
			ps.setObject(index++,approval_statusDTO.nameBn);
			//System.out.println("Setting object" + approval_statusDTO.isDeleted + " in index " + index);
			ps.setObject(index++,approval_statusDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(approval_statusDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(approval_statusDTO, userDTO);
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
		//Approval_statusRepository.getInstance().reload(false);
	}
	
	public void deleteApproval_statusByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			
			String sql = "delete from approval_status where ID = " + ID;
			
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
	private List<Approval_statusDTO> getApproval_statusDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Approval_statusDTO approval_statusDTO = null;
		List<Approval_statusDTO> approval_statusDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM approval_status";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				approval_statusDTO = new Approval_statusDTO();
				approval_statusDTO.iD = rs.getLong("ID");
				approval_statusDTO.nameEn = rs.getString("name_en");
				approval_statusDTO.nameBn = rs.getString("name_bn");
				approval_statusDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = approval_statusDTO.iD;
				while(i < approval_statusDTOList.size() && approval_statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				approval_statusDTOList.add(i,  approval_statusDTO);
				//approval_statusDTOList.add(approval_statusDTO);
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
		return approval_statusDTOList;
	}
	
	public List<Approval_statusDTO> getApproval_statusDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getApproval_statusDTOByColumn(filter);
	}
	
	public List<Approval_statusDTO> getApproval_statusDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getApproval_statusDTOByColumn(filter);
	}
	
	public List<Approval_statusDTO> getApproval_statusDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getApproval_statusDTOByColumn(filter);
	}
		
	
	
	public List<Approval_statusDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Approval_statusDTO approval_statusDTO = null;
		List<Approval_statusDTO> approval_statusDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return approval_statusDTOList;
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
			sql += " FROM approval_status";
            
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
				approval_statusDTO = new Approval_statusDTO();
				approval_statusDTO.iD = rs.getLong("ID");
				approval_statusDTO.nameEn = rs.getString("name_en");
				approval_statusDTO.nameBn = rs.getString("name_bn");
				approval_statusDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + approval_statusDTO);
				//approval_statusDTOList.add(approval_statusDTO);
				int i = 0;
				long primaryKey = approval_statusDTO.iD;
				while(i < approval_statusDTOList.size() && approval_statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				approval_statusDTOList.add(i,  approval_statusDTO);

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
		return approval_statusDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM approval_status";

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
	public List<Approval_statusDTO> getAllApproval_status (boolean isFirstReload)
    {
		List<Approval_statusDTO> approval_statusDTOList = new ArrayList<>();

		String sql = "SELECT * FROM approval_status";
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
				Approval_statusDTO approval_statusDTO = new Approval_statusDTO();
				approval_statusDTO.iD = rs.getLong("ID");
				approval_statusDTO.nameEn = rs.getString("name_en");
				approval_statusDTO.nameBn = rs.getString("name_bn");
				approval_statusDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = approval_statusDTO.iD;
				while(i < approval_statusDTOList.size() && approval_statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				approval_statusDTOList.add(i,  approval_statusDTO);
				//approval_statusDTOList.add(approval_statusDTO);
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

		return approval_statusDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM approval_status";
			
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

			String sql = "SELECT distinct approval_status.ID as ID FROM approval_status ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Approval_statusMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Approval_statusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Approval_statusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Approval_statusMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "approval_status." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "approval_status." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " approval_status.isDeleted = false";				
			
			
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
		Approval_statusDTO approval_statusDTO = null;
		List<Approval_statusDTO> approval_statusDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return approval_statusDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Approval_statusMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				approval_statusDTO = new Approval_statusDTO();
				approval_statusDTO.iD = rs.getLong("ID");
				approval_statusDTO.nameEn = rs.getString("name_en");
				approval_statusDTO.nameBn = rs.getString("name_bn");
				approval_statusDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = approval_statusDTO.iD;
				while(i < approval_statusDTOList.size() && approval_statusDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				approval_statusDTOList.add(i,  approval_statusDTO);

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
		return approval_statusDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	