package centre;

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


public class CentreDAO  implements NavigationService{
	
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
		ps.setString(2,"centre");
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
		ps.setString(2,"centre");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(CentreDTO centreDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = centreDTO.iD;
		// userDTO.userName = centreDTO.email;
		// userDTO.fullName = centreDTO.name;
		// userDTO.password = centreDTO.password;
		// userDTO.phoneNo = centreDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = centreDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(CentreDTO centreDTO, UserDTO userDTO)
	{
		// userDTO.ID = centreDTO.iD;
		// userDTO.fullName = centreDTO.name;
		// userDTO.phoneNo = centreDTO.phone;
		// userDTO.mailAddress = centreDTO.email;

		return userDTO;
	}
		
		
	
	public void addCentre(CentreDTO centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			centreDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Centre");

			String sql = "INSERT INTO centre";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "address";
			sql += ", ";
			sql += "contact_person";
			sql += ", ";
			sql += "phone_number";
			sql += ", ";
			sql += "email";
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
			// userDAO.addUser(getUserDTO(centreDTO));
			// centreDTO.iD = userDAO.getUserDTOByUsername(centreDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + centreDTO.iD + " in index " + index);
			ps.setObject(index++,centreDTO.iD);
			//System.out.println("Setting object" + centreDTO.nameEn + " in index " + index);
			ps.setObject(index++,centreDTO.nameEn);
			//System.out.println("Setting object" + centreDTO.nameBn + " in index " + index);
			ps.setObject(index++,centreDTO.nameBn);
			//System.out.println("Setting object" + centreDTO.address + " in index " + index);
			ps.setObject(index++,centreDTO.address);
			//System.out.println("Setting object" + centreDTO.contactPerson + " in index " + index);
			ps.setObject(index++,centreDTO.contactPerson);
			//System.out.println("Setting object" + centreDTO.phoneNumber + " in index " + index);
			ps.setObject(index++,centreDTO.phoneNumber);
			//System.out.println("Setting object" + centreDTO.email + " in index " + index);
			ps.setObject(index++,centreDTO.email);
			//System.out.println("Setting object" + centreDTO.isDeleted + " in index " + index);
			ps.setObject(index++,centreDTO.isDeleted);
			//System.out.println("Setting object" + centreDTO.description + " in index " + index);
			ps.setObject(index++,centreDTO.description);
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
		//CentreRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public CentreDTO getCentreDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		CentreDTO centreDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "address";
			sql += ", ";
			sql += "contact_person";
			sql += ", ";
			sql += "phone_number";
			sql += ", ";
			sql += "email";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM centre";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				centreDTO = new CentreDTO();

				centreDTO.iD = rs.getLong("ID");
				centreDTO.nameEn = rs.getString("name_en");
				centreDTO.nameBn = rs.getString("name_bn");
				centreDTO.address = rs.getString("address");
				centreDTO.contactPerson = rs.getString("contact_person");
				centreDTO.phoneNumber = rs.getString("phone_number");
				centreDTO.email = rs.getString("email");
				centreDTO.isDeleted = rs.getBoolean("isDeleted");
				centreDTO.description = rs.getString("description");

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
		return centreDTO;
	}
	
	public void updateCentre(CentreDTO centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE centre";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "address=?";
			sql += ", ";
			sql += "contact_person=?";
			sql += ", ";
			sql += "phone_number=?";
			sql += ", ";
			sql += "email=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", ";
			sql += "description=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + centreDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + centreDTO.iD + " in index " + index);
			ps.setObject(index++,centreDTO.iD);
			//System.out.println("Setting object" + centreDTO.nameEn + " in index " + index);
			ps.setObject(index++,centreDTO.nameEn);
			//System.out.println("Setting object" + centreDTO.nameBn + " in index " + index);
			ps.setObject(index++,centreDTO.nameBn);
			//System.out.println("Setting object" + centreDTO.address + " in index " + index);
			ps.setObject(index++,centreDTO.address);
			//System.out.println("Setting object" + centreDTO.contactPerson + " in index " + index);
			ps.setObject(index++,centreDTO.contactPerson);
			//System.out.println("Setting object" + centreDTO.phoneNumber + " in index " + index);
			ps.setObject(index++,centreDTO.phoneNumber);
			//System.out.println("Setting object" + centreDTO.email + " in index " + index);
			ps.setObject(index++,centreDTO.email);
			//System.out.println("Setting object" + centreDTO.isDeleted + " in index " + index);
			ps.setObject(index++,centreDTO.isDeleted);
			//System.out.println("Setting object" + centreDTO.description + " in index " + index);
			ps.setObject(index++,centreDTO.description);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(centreDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(centreDTO, userDTO);
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
		//CentreRepository.getInstance().reload(false);
	}
	
	public void deleteCentreByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from centre WHERE ID = " + ID;
			
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
	private List<CentreDTO> getCentreDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		CentreDTO centreDTO = null;
		List<CentreDTO> centreDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "address";
			sql += ", ";
			sql += "contact_person";
			sql += ", ";
			sql += "phone_number";
			sql += ", ";
			sql += "email";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM centre";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				centreDTO = new CentreDTO();
				centreDTO.iD = rs.getLong("ID");
				centreDTO.nameEn = rs.getString("name_en");
				centreDTO.nameBn = rs.getString("name_bn");
				centreDTO.address = rs.getString("address");
				centreDTO.contactPerson = rs.getString("contact_person");
				centreDTO.phoneNumber = rs.getString("phone_number");
				centreDTO.email = rs.getString("email");
				centreDTO.isDeleted = rs.getBoolean("isDeleted");
				centreDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = centreDTO.iD;
				while(i < centreDTOList.size() && centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				centreDTOList.add(i,  centreDTO);
				//centreDTOList.add(centreDTO);
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
		return centreDTOList;
	}
	
	public List<CentreDTO> getCentreDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getCentreDTOByColumn(filter);
	}
	
	public List<CentreDTO> getCentreDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getCentreDTOByColumn(filter);
	}
	
	public List<CentreDTO> getCentreDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getCentreDTOByColumn(filter);
	}
		
	
	
	public List<CentreDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		CentreDTO centreDTO = null;
		List<CentreDTO> centreDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return centreDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "address";
			sql += ", ";
			sql += "contact_person";
			sql += ", ";
			sql += "phone_number";
			sql += ", ";
			sql += "email";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM centre";
            
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
				centreDTO = new CentreDTO();
				centreDTO.iD = rs.getLong("ID");
				centreDTO.nameEn = rs.getString("name_en");
				centreDTO.nameBn = rs.getString("name_bn");
				centreDTO.address = rs.getString("address");
				centreDTO.contactPerson = rs.getString("contact_person");
				centreDTO.phoneNumber = rs.getString("phone_number");
				centreDTO.email = rs.getString("email");
				centreDTO.isDeleted = rs.getBoolean("isDeleted");
				centreDTO.description = rs.getString("description");
				System.out.println("got this DTO: " + centreDTO);
				//centreDTOList.add(centreDTO);
				int i = 0;
				long primaryKey = centreDTO.iD;
				while(i < centreDTOList.size() && centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				centreDTOList.add(i,  centreDTO);

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
		return centreDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM centre";

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
	
	public String getCentreNameByCentreID(long centre_id){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		String centre_name = "";
		try{
			
			String sql = "select centre.name_en from centre where centre.id = " + centre_id;
			
			
			
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next()){
				centre_name = rs.getString("centre.name_en");

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
		return centre_name;
	}
	
	//add repository
	public List<CentreDTO> getAllCentre (boolean isFirstReload)
    {
		List<CentreDTO> centreDTOList = new ArrayList<>();

		String sql = "SELECT * FROM centre";
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
				CentreDTO centreDTO = new CentreDTO();
				centreDTO.iD = rs.getLong("ID");
				centreDTO.nameEn = rs.getString("name_en");
				centreDTO.nameBn = rs.getString("name_bn");
				centreDTO.address = rs.getString("address");
				centreDTO.contactPerson = rs.getString("contact_person");
				centreDTO.phoneNumber = rs.getString("phone_number");
				centreDTO.email = rs.getString("email");
				centreDTO.isDeleted = rs.getBoolean("isDeleted");
				centreDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = centreDTO.iD;
				while(i < centreDTOList.size() && centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				centreDTOList.add(i,  centreDTO);
				//centreDTOList.add(centreDTO);
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

		return centreDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM centre";
			
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

			String sql = "SELECT distinct centre.ID as ID FROM centre ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = CentreMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(CentreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !CentreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(CentreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "centre." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "centre." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " centre.isDeleted = false";				
			
			
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
		CentreDTO centreDTO = null;
		List<CentreDTO> centreDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return centreDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = CentreMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				centreDTO = new CentreDTO();
				centreDTO.iD = rs.getLong("ID");
				centreDTO.nameEn = rs.getString("name_en");
				centreDTO.nameBn = rs.getString("name_bn");
				centreDTO.address = rs.getString("address");
				centreDTO.contactPerson = rs.getString("contact_person");
				centreDTO.phoneNumber = rs.getString("phone_number");
				centreDTO.email = rs.getString("email");
				centreDTO.isDeleted = rs.getBoolean("isDeleted");
				centreDTO.description = rs.getString("description");
				i = 0;
				long primaryKey = centreDTO.iD;
				while(i < centreDTOList.size() && centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				centreDTOList.add(i,  centreDTO);

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
		return centreDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	