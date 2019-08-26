package apa_target;

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


public class Apa_targetDAO  implements NavigationService{
	
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
		ps.setString(2,"apa_target");
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
		ps.setString(2,"apa_target");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Apa_targetDTO apa_targetDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = apa_targetDTO.iD;
		// userDTO.userName = apa_targetDTO.email;
		// userDTO.fullName = apa_targetDTO.name;
		// userDTO.password = apa_targetDTO.password;
		// userDTO.phoneNo = apa_targetDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = apa_targetDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Apa_targetDTO apa_targetDTO, UserDTO userDTO)
	{
		// userDTO.ID = apa_targetDTO.iD;
		// userDTO.fullName = apa_targetDTO.name;
		// userDTO.phoneNo = apa_targetDTO.phone;
		// userDTO.mailAddress = apa_targetDTO.email;

		return userDTO;
	}
		
		
	
	public void addApa_target(Apa_targetDTO apa_targetDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			apa_targetDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Apa_target");

			String sql = "INSERT INTO apa_target";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "semen_collection";
			sql += ", ";
			sql += "artificial_insemenation";
			sql += ", ";
			sql += "progeny";
			sql += ", ";
			sql += "candidate_bull_production";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "entry_date";
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
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(apa_targetDTO));
			// apa_targetDTO.iD = userDAO.getUserDTOByUsername(apa_targetDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + apa_targetDTO.iD + " in index " + index);
			ps.setObject(index++,apa_targetDTO.iD);
			//System.out.println("Setting object" + apa_targetDTO.semenCollection + " in index " + index);
			ps.setObject(index++,apa_targetDTO.semenCollection);
			//System.out.println("Setting object" + apa_targetDTO.artificialInsemenation + " in index " + index);
			ps.setObject(index++,apa_targetDTO.artificialInsemenation);
			//System.out.println("Setting object" + apa_targetDTO.progeny + " in index " + index);
			ps.setObject(index++,apa_targetDTO.progeny);
			//System.out.println("Setting object" + apa_targetDTO.candidateBullProduction + " in index " + index);
			ps.setObject(index++,apa_targetDTO.candidateBullProduction);
			//System.out.println("Setting object" + apa_targetDTO.description + " in index " + index);
			ps.setObject(index++,apa_targetDTO.description);
			//System.out.println("Setting object" + apa_targetDTO.entryDate + " in index " + index);
			ps.setObject(index++,apa_targetDTO.entryDate);
			//System.out.println("Setting object" + apa_targetDTO.isDeleted + " in index " + index);
			ps.setObject(index++,apa_targetDTO.isDeleted);
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
		//Apa_targetRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Apa_targetDTO getApa_targetDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Apa_targetDTO apa_targetDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "semen_collection";
			sql += ", ";
			sql += "artificial_insemenation";
			sql += ", ";
			sql += "progeny";
			sql += ", ";
			sql += "candidate_bull_production";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM apa_target";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				apa_targetDTO = new Apa_targetDTO();

				apa_targetDTO.iD = rs.getLong("ID");
				apa_targetDTO.semenCollection = rs.getInt("semen_collection");
				apa_targetDTO.artificialInsemenation = rs.getInt("artificial_insemenation");
				apa_targetDTO.progeny = rs.getInt("progeny");
				apa_targetDTO.candidateBullProduction = rs.getInt("candidate_bull_production");
				apa_targetDTO.description = rs.getString("description");
				apa_targetDTO.entryDate = rs.getLong("entry_date");
				apa_targetDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return apa_targetDTO;
	}
	
	public void updateApa_target(Apa_targetDTO apa_targetDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE apa_target";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "semen_collection=?";
			sql += ", ";
			sql += "artificial_insemenation=?";
			sql += ", ";
			sql += "progeny=?";
			sql += ", ";
			sql += "candidate_bull_production=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "entry_date=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + apa_targetDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + apa_targetDTO.iD + " in index " + index);
			ps.setObject(index++,apa_targetDTO.iD);
			//System.out.println("Setting object" + apa_targetDTO.semenCollection + " in index " + index);
			ps.setObject(index++,apa_targetDTO.semenCollection);
			//System.out.println("Setting object" + apa_targetDTO.artificialInsemenation + " in index " + index);
			ps.setObject(index++,apa_targetDTO.artificialInsemenation);
			//System.out.println("Setting object" + apa_targetDTO.progeny + " in index " + index);
			ps.setObject(index++,apa_targetDTO.progeny);
			//System.out.println("Setting object" + apa_targetDTO.candidateBullProduction + " in index " + index);
			ps.setObject(index++,apa_targetDTO.candidateBullProduction);
			//System.out.println("Setting object" + apa_targetDTO.description + " in index " + index);
			ps.setObject(index++,apa_targetDTO.description);
			//System.out.println("Setting object" + apa_targetDTO.entryDate + " in index " + index);
			ps.setObject(index++,apa_targetDTO.entryDate);
			//System.out.println("Setting object" + apa_targetDTO.isDeleted + " in index " + index);
			ps.setObject(index++,apa_targetDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(apa_targetDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(apa_targetDTO, userDTO);
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
		//Apa_targetRepository.getInstance().reload(false);
	}
	
	public void deleteApa_targetByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from apa_target where ID = " + ID;
			
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
	private List<Apa_targetDTO> getApa_targetDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Apa_targetDTO apa_targetDTO = null;
		List<Apa_targetDTO> apa_targetDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "semen_collection";
			sql += ", ";
			sql += "artificial_insemenation";
			sql += ", ";
			sql += "progeny";
			sql += ", ";
			sql += "candidate_bull_production";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM apa_target";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				apa_targetDTO = new Apa_targetDTO();
				apa_targetDTO.iD = rs.getLong("ID");
				apa_targetDTO.semenCollection = rs.getInt("semen_collection");
				apa_targetDTO.artificialInsemenation = rs.getInt("artificial_insemenation");
				apa_targetDTO.progeny = rs.getInt("progeny");
				apa_targetDTO.candidateBullProduction = rs.getInt("candidate_bull_production");
				apa_targetDTO.description = rs.getString("description");
				apa_targetDTO.entryDate = rs.getLong("entry_date");
				apa_targetDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = apa_targetDTO.iD;
				while(i < apa_targetDTOList.size() && apa_targetDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				apa_targetDTOList.add(i,  apa_targetDTO);
				//apa_targetDTOList.add(apa_targetDTO);
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
		return apa_targetDTOList;
	}
	
	public List<Apa_targetDTO> getApa_targetDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getApa_targetDTOByColumn(filter);
	}
	
	public List<Apa_targetDTO> getApa_targetDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getApa_targetDTOByColumn(filter);
	}
	
	public List<Apa_targetDTO> getApa_targetDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getApa_targetDTOByColumn(filter);
	}
		
	
	
	public List<Apa_targetDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Apa_targetDTO apa_targetDTO = null;
		List<Apa_targetDTO> apa_targetDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return apa_targetDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "semen_collection";
			sql += ", ";
			sql += "artificial_insemenation";
			sql += ", ";
			sql += "progeny";
			sql += ", ";
			sql += "candidate_bull_production";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM apa_target";
            
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
				apa_targetDTO = new Apa_targetDTO();
				apa_targetDTO.iD = rs.getLong("ID");
				apa_targetDTO.semenCollection = rs.getInt("semen_collection");
				apa_targetDTO.artificialInsemenation = rs.getInt("artificial_insemenation");
				apa_targetDTO.progeny = rs.getInt("progeny");
				apa_targetDTO.candidateBullProduction = rs.getInt("candidate_bull_production");
				apa_targetDTO.description = rs.getString("description");
				apa_targetDTO.entryDate = rs.getLong("entry_date");
				apa_targetDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + apa_targetDTO);
				//apa_targetDTOList.add(apa_targetDTO);
				int i = 0;
				long primaryKey = apa_targetDTO.iD;
				while(i < apa_targetDTOList.size() && apa_targetDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				apa_targetDTOList.add(i,  apa_targetDTO);

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
		return apa_targetDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM apa_target";

		sql += " WHERE isDeleted = 0  order by ID desc ";
		
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
	public List<Apa_targetDTO> getAllApa_target (boolean isFirstReload)
    {
		List<Apa_targetDTO> apa_targetDTOList = new ArrayList<>();

		String sql = "SELECT * FROM apa_target";
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
				Apa_targetDTO apa_targetDTO = new Apa_targetDTO();
				apa_targetDTO.iD = rs.getLong("ID");
				apa_targetDTO.semenCollection = rs.getInt("semen_collection");
				apa_targetDTO.artificialInsemenation = rs.getInt("artificial_insemenation");
				apa_targetDTO.progeny = rs.getInt("progeny");
				apa_targetDTO.candidateBullProduction = rs.getInt("candidate_bull_production");
				apa_targetDTO.description = rs.getString("description");
				apa_targetDTO.entryDate = rs.getLong("entry_date");
				apa_targetDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = apa_targetDTO.iD;
				while(i < apa_targetDTOList.size() && apa_targetDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				apa_targetDTOList.add(i,  apa_targetDTO);
				//apa_targetDTOList.add(apa_targetDTO);
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

		return apa_targetDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM apa_target";
			
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

			String sql = "SELECT distinct apa_target.ID as ID FROM apa_target ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Apa_targetMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Apa_targetMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Apa_targetMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Apa_targetMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "apa_target." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "apa_target." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " apa_target.isDeleted = false";
			
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by apa_target.ID desc ";

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
		Apa_targetDTO apa_targetDTO = null;
		List<Apa_targetDTO> apa_targetDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return apa_targetDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Apa_targetMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				apa_targetDTO = new Apa_targetDTO();
				apa_targetDTO.iD = rs.getLong("ID");
				apa_targetDTO.semenCollection = rs.getInt("semen_collection");
				apa_targetDTO.artificialInsemenation = rs.getInt("artificial_insemenation");
				apa_targetDTO.progeny = rs.getInt("progeny");
				apa_targetDTO.candidateBullProduction = rs.getInt("candidate_bull_production");
				apa_targetDTO.description = rs.getString("description");
				apa_targetDTO.entryDate = rs.getLong("entry_date");
				apa_targetDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = apa_targetDTO.iD;
				while(i < apa_targetDTOList.size() && apa_targetDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				apa_targetDTOList.add(i,  apa_targetDTO);

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
		return apa_targetDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	