package candidate_bull_production;

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


public class Candidate_bull_productionDAO  implements NavigationService{
	
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
		ps.setString(2,"candidate_bull_production");
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
		ps.setString(2,"candidate_bull_production");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Candidate_bull_productionDTO candidate_bull_productionDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = candidate_bull_productionDTO.iD;
		// userDTO.userName = candidate_bull_productionDTO.email;
		// userDTO.fullName = candidate_bull_productionDTO.name;
		// userDTO.password = candidate_bull_productionDTO.password;
		// userDTO.phoneNo = candidate_bull_productionDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = candidate_bull_productionDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Candidate_bull_productionDTO candidate_bull_productionDTO, UserDTO userDTO)
	{
		// userDTO.ID = candidate_bull_productionDTO.iD;
		// userDTO.fullName = candidate_bull_productionDTO.name;
		// userDTO.phoneNo = candidate_bull_productionDTO.phone;
		// userDTO.mailAddress = candidate_bull_productionDTO.email;

		return userDTO;
	}
		
		
	
	public void addCandidate_bull_production(Candidate_bull_productionDTO candidate_bull_productionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			candidate_bull_productionDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Candidate_bull_production");

			String sql = "INSERT INTO candidate_bull_production";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "production_date";
			sql += ", ";
			sql += "number_of_candidate_bulls";
			sql += ", ";
			sql += "source_type";
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
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(candidate_bull_productionDTO));
			// candidate_bull_productionDTO.iD = userDAO.getUserDTOByUsername(candidate_bull_productionDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + candidate_bull_productionDTO.iD + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.iD);
			//System.out.println("Setting object" + candidate_bull_productionDTO.productionDate + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.productionDate);
			//System.out.println("Setting object" + candidate_bull_productionDTO.numberOfCandidateBulls + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.numberOfCandidateBulls);
			//System.out.println("Setting object" + candidate_bull_productionDTO.sourceType + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.sourceType);
			//System.out.println("Setting object" + candidate_bull_productionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.isDeleted);
			//System.out.println("Setting object" + candidate_bull_productionDTO.description + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.description);
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
		//Candidate_bull_productionRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Candidate_bull_productionDTO getCandidate_bull_productionDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Candidate_bull_productionDTO candidate_bull_productionDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "production_date";
			sql += ", ";
			sql += "number_of_candidate_bulls";
			sql += ", ";
			sql += "source_type";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM candidate_bull_production";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				candidate_bull_productionDTO = new Candidate_bull_productionDTO();

				candidate_bull_productionDTO.iD = rs.getLong("ID");
				candidate_bull_productionDTO.productionDate = rs.getLong("production_date");
				candidate_bull_productionDTO.numberOfCandidateBulls = rs.getInt("number_of_candidate_bulls");
				candidate_bull_productionDTO.sourceType = rs.getInt("source_type");
				candidate_bull_productionDTO.isDeleted = rs.getBoolean("isDeleted");
				candidate_bull_productionDTO.description = rs.getString("description");

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
		return candidate_bull_productionDTO;
	}
	
	public void updateCandidate_bull_production(Candidate_bull_productionDTO candidate_bull_productionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE candidate_bull_production";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "production_date=?";
			sql += ", ";
			sql += "number_of_candidate_bulls=?";
			sql += ", ";
			sql += "source_type=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", ";
			sql += "description=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + candidate_bull_productionDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + candidate_bull_productionDTO.iD + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.iD);
			//System.out.println("Setting object" + candidate_bull_productionDTO.productionDate + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.productionDate);
			//System.out.println("Setting object" + candidate_bull_productionDTO.numberOfCandidateBulls + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.numberOfCandidateBulls);
			//System.out.println("Setting object" + candidate_bull_productionDTO.sourceType + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.sourceType);
			//System.out.println("Setting object" + candidate_bull_productionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.isDeleted);
			//System.out.println("Setting object" + candidate_bull_productionDTO.description + " in index " + index);
			ps.setObject(index++,candidate_bull_productionDTO.description);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(candidate_bull_productionDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(candidate_bull_productionDTO, userDTO);
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
		//Candidate_bull_productionRepository.getInstance().reload(false);
	}
	
	public void deleteCandidate_bull_productionByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			
			String sql = "delete from candidate_bull_production where ID = " + ID;
			
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
	private List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Candidate_bull_productionDTO candidate_bull_productionDTO = null;
		List<Candidate_bull_productionDTO> candidate_bull_productionDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "production_date";
			sql += ", ";
			sql += "number_of_candidate_bulls";
			sql += ", ";
			sql += "source_type";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM candidate_bull_production";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				candidate_bull_productionDTO = new Candidate_bull_productionDTO();
				candidate_bull_productionDTO.iD = rs.getLong("ID");
				candidate_bull_productionDTO.productionDate = rs.getLong("production_date");
				candidate_bull_productionDTO.numberOfCandidateBulls = rs.getInt("number_of_candidate_bulls");
				candidate_bull_productionDTO.sourceType = rs.getInt("source_type");
				candidate_bull_productionDTO.isDeleted = rs.getBoolean("isDeleted");
				candidate_bull_productionDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = candidate_bull_productionDTO.iD;
				while(i < candidate_bull_productionDTOList.size() && candidate_bull_productionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				candidate_bull_productionDTOList.add(i,  candidate_bull_productionDTO);
				//candidate_bull_productionDTOList.add(candidate_bull_productionDTO);
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
		return candidate_bull_productionDTOList;
	}
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getCandidate_bull_productionDTOByColumn(filter);
	}
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getCandidate_bull_productionDTOByColumn(filter);
	}
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getCandidate_bull_productionDTOByColumn(filter);
	}
		
	
	
	public List<Candidate_bull_productionDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Candidate_bull_productionDTO candidate_bull_productionDTO = null;
		List<Candidate_bull_productionDTO> candidate_bull_productionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return candidate_bull_productionDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "production_date";
			sql += ", ";
			sql += "number_of_candidate_bulls";
			sql += ", ";
			sql += "source_type";
			sql += ", ";
			sql += "isDeleted";
			sql += ", ";
			sql += "description";
			sql += " FROM candidate_bull_production";
            
            sql += " WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=")  order by ID desc";
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				candidate_bull_productionDTO = new Candidate_bull_productionDTO();
				candidate_bull_productionDTO.iD = rs.getLong("ID");
				candidate_bull_productionDTO.productionDate = rs.getLong("production_date");
				candidate_bull_productionDTO.numberOfCandidateBulls = rs.getInt("number_of_candidate_bulls");
				candidate_bull_productionDTO.sourceType = rs.getInt("source_type");
				candidate_bull_productionDTO.isDeleted = rs.getBoolean("isDeleted");
				candidate_bull_productionDTO.description = rs.getString("description");
				System.out.println("got this DTO: " + candidate_bull_productionDTO);
				
				candidate_bull_productionDTOList.add(candidate_bull_productionDTO);

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
		return candidate_bull_productionDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM candidate_bull_production";

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
	public List<Candidate_bull_productionDTO> getAllCandidate_bull_production (boolean isFirstReload)
    {
		List<Candidate_bull_productionDTO> candidate_bull_productionDTOList = new ArrayList<>();

		String sql = "SELECT * FROM candidate_bull_production";
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
				Candidate_bull_productionDTO candidate_bull_productionDTO = new Candidate_bull_productionDTO();
				candidate_bull_productionDTO.iD = rs.getLong("ID");
				candidate_bull_productionDTO.productionDate = rs.getLong("production_date");
				candidate_bull_productionDTO.numberOfCandidateBulls = rs.getInt("number_of_candidate_bulls");
				candidate_bull_productionDTO.sourceType = rs.getInt("source_type");
				candidate_bull_productionDTO.isDeleted = rs.getBoolean("isDeleted");
				candidate_bull_productionDTO.description = rs.getString("description");
				int i = 0;
				long primaryKey = candidate_bull_productionDTO.iD;
				while(i < candidate_bull_productionDTOList.size() && candidate_bull_productionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				candidate_bull_productionDTOList.add(i,  candidate_bull_productionDTO);
				//candidate_bull_productionDTOList.add(candidate_bull_productionDTO);
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

		return candidate_bull_productionDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM candidate_bull_production";
			
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

			String sql = "SELECT distinct candidate_bull_production.ID as ID FROM candidate_bull_production ";
			sql += " join source on candidate_bull_production.source_type = source.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Candidate_bull_productionMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Candidate_bull_productionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Candidate_bull_productionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Candidate_bull_productionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "candidate_bull_production." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "candidate_bull_production." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " candidate_bull_production.isDeleted = false";				
			
			
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
		Candidate_bull_productionDTO candidate_bull_productionDTO = null;
		List<Candidate_bull_productionDTO> candidate_bull_productionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return candidate_bull_productionDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Candidate_bull_productionMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				candidate_bull_productionDTO = new Candidate_bull_productionDTO();
				candidate_bull_productionDTO.iD = rs.getLong("ID");
				candidate_bull_productionDTO.productionDate = rs.getLong("production_date");
				candidate_bull_productionDTO.numberOfCandidateBulls = rs.getInt("number_of_candidate_bulls");
				candidate_bull_productionDTO.sourceType = rs.getInt("source_type");
				candidate_bull_productionDTO.isDeleted = rs.getBoolean("isDeleted");
				candidate_bull_productionDTO.description = rs.getString("description");
				i = 0;
				long primaryKey = candidate_bull_productionDTO.iD;
				while(i < candidate_bull_productionDTOList.size() && candidate_bull_productionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				candidate_bull_productionDTOList.add(i,  candidate_bull_productionDTO);

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
		return candidate_bull_productionDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	