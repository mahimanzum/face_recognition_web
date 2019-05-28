package semen_distribution;

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


public class Semen_distributionDAO  implements NavigationService{
	
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
		ps.setString(2,"semen_distribution");
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
		ps.setString(2,"semen_distribution");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Semen_distributionDTO semen_distributionDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = semen_distributionDTO.iD;
		// userDTO.userName = semen_distributionDTO.email;
		// userDTO.fullName = semen_distributionDTO.name;
		// userDTO.password = semen_distributionDTO.password;
		// userDTO.phoneNo = semen_distributionDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = semen_distributionDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Semen_distributionDTO semen_distributionDTO, UserDTO userDTO)
	{
		// userDTO.ID = semen_distributionDTO.iD;
		// userDTO.fullName = semen_distributionDTO.name;
		// userDTO.phoneNo = semen_distributionDTO.phone;
		// userDTO.mailAddress = semen_distributionDTO.email;

		return userDTO;
	}
		
		
	
	public void addSemen_distribution(Semen_distributionDTO semen_distributionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			semen_distributionDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Semen_distribution");

			String sql = "INSERT INTO semen_distribution";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_id";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "transaction_date";
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
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(semen_distributionDTO));
			// semen_distributionDTO.iD = userDAO.getUserDTOByUsername(semen_distributionDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + semen_distributionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.iD);
			//System.out.println("Setting object" + semen_distributionDTO.bullType + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.bullType);
			//System.out.println("Setting object" + semen_distributionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.noOfDose);
			//System.out.println("Setting object" + semen_distributionDTO.requisitionId + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.requisitionId);
			//System.out.println("Setting object" + semen_distributionDTO.groupId + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.groupId);
			//System.out.println("Setting object" + semen_distributionDTO.transactionDate + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.transactionDate);
			//System.out.println("Setting object" + semen_distributionDTO.description + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.description);
			//System.out.println("Setting object" + semen_distributionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.isDeleted);
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
		//Semen_distributionRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Semen_distributionDTO getSemen_distributionDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_distributionDTO semen_distributionDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_id";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_distribution";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				semen_distributionDTO = new Semen_distributionDTO();

				semen_distributionDTO.iD = rs.getLong("ID");
				semen_distributionDTO.bullType = rs.getInt("bull_type");
				semen_distributionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_distributionDTO.requisitionId = rs.getLong("requisition_id");
				semen_distributionDTO.groupId = rs.getInt("group_id");
				semen_distributionDTO.transactionDate = rs.getLong("transaction_date");
				semen_distributionDTO.description = rs.getString("description");
				semen_distributionDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return semen_distributionDTO;
	}
	
	public void updateSemen_distribution(Semen_distributionDTO semen_distributionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE semen_distribution";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "no_of_dose=?";
			sql += ", ";
			sql += "requisition_id=?";
			sql += ", ";
			sql += "group_id=?";
			sql += ", ";
			sql += "transaction_date=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + semen_distributionDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + semen_distributionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.iD);
			//System.out.println("Setting object" + semen_distributionDTO.bullType + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.bullType);
			//System.out.println("Setting object" + semen_distributionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.noOfDose);
			//System.out.println("Setting object" + semen_distributionDTO.requisitionId + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.requisitionId);
			//System.out.println("Setting object" + semen_distributionDTO.groupId + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.groupId);
			//System.out.println("Setting object" + semen_distributionDTO.transactionDate + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.transactionDate);
			//System.out.println("Setting object" + semen_distributionDTO.description + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.description);
			//System.out.println("Setting object" + semen_distributionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_distributionDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(semen_distributionDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(semen_distributionDTO, userDTO);
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
		//Semen_distributionRepository.getInstance().reload(false);
	}
	
	public int getTotalDoseOfBull(long bull_id)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		int totalDose = 0;
		try{
			
			String sql = "SELECT \r\n" + 
					"    SUM(no_of_dose)\r\n" + 
					"FROM\r\n" + 
					"    semen_distribution\r\n" + 
					"        INNER JOIN\r\n" + 
					"    bull ON semen_distribution.bull_type = bull.id\r\n" + 
					"       \r\n" + 
					"WHERE\r\n" + 
					"    bull_type = " + bull_id + "\r\n" + 
					"GROUP BY bull_type";
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next())
			{
				totalDose = rs.getInt("SUM(no_of_dose)");				
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
		return totalDose;
	}
	
	public int getTotalDoseOfBreed(int breedType, int centre, long expiration_date)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		int totalDose = 0;
		try{
			
			String sql = "SELECT \r\n" + 
					"    breed_type, bull_type, SUM(no_of_dose)\r\n" + 
					"FROM\r\n" + 
					"    semen_distribution\r\n" + 
					"        INNER JOIN\r\n" + 
					"    bull ON semen_distribution.bull_type = bull.id\r\n" + 
					"        INNER JOIN\r\n" + 
					"    breed ON bull.breed_type = breed.id\r\n" + 
					"WHERE\r\n" + 
					"    breed.id = " + breedType + "\r\n" + 
					"        AND bull.id IN (SELECT \r\n" + 
					"            bull_type\r\n" + 
					"        FROM\r\n" + 
					"            permissible_bulls_in_centre\r\n" + 
					"        WHERE\r\n" + 
					"            centre_type = " + centre + "\r\n" + 
					"                AND date_of_expiration >= " + expiration_date + ")\r\n" + 
					"GROUP BY breed.id;";
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next())
			{
				totalDose = rs.getInt("SUM(no_of_dose)");				
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
		return totalDose;
	}
	
	public void deleteSemen_distributionByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from semen_distribution WHERE ID = " + ID;
			
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
	private List<Semen_distributionDTO> getSemen_distributionDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_distributionDTO semen_distributionDTO = null;
		List<Semen_distributionDTO> semen_distributionDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_id";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_distribution";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				semen_distributionDTO = new Semen_distributionDTO();
				semen_distributionDTO.iD = rs.getLong("ID");
				semen_distributionDTO.bullType = rs.getInt("bull_type");
				semen_distributionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_distributionDTO.requisitionId = rs.getLong("requisition_id");
				semen_distributionDTO.groupId = rs.getInt("group_id");
				semen_distributionDTO.transactionDate = rs.getLong("transaction_date");
				semen_distributionDTO.description = rs.getString("description");
				semen_distributionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_distributionDTO.iD;
				while(i < semen_distributionDTOList.size() && semen_distributionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_distributionDTOList.add(i,  semen_distributionDTO);
				//semen_distributionDTOList.add(semen_distributionDTO);
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
		return semen_distributionDTOList;
	}
	
	public List<Semen_distributionDTO> getSemen_distributionDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getSemen_distributionDTOByColumn(filter);
	}
	
	public List<Semen_distributionDTO> getSemen_distributionDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getSemen_distributionDTOByColumn(filter);
	}
	
	public List<Semen_distributionDTO> getSemen_distributionDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getSemen_distributionDTOByColumn(filter);
	}
		
	
	
	public List<Semen_distributionDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_distributionDTO semen_distributionDTO = null;
		List<Semen_distributionDTO> semen_distributionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_distributionDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_id";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_distribution";
            
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
				semen_distributionDTO = new Semen_distributionDTO();
				semen_distributionDTO.iD = rs.getLong("ID");
				semen_distributionDTO.bullType = rs.getInt("bull_type");
				semen_distributionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_distributionDTO.requisitionId = rs.getLong("requisition_id");
				semen_distributionDTO.groupId = rs.getInt("group_id");
				semen_distributionDTO.transactionDate = rs.getLong("transaction_date");
				semen_distributionDTO.description = rs.getString("description");
				semen_distributionDTO.isDeleted = rs.getBoolean("isDeleted");
				
				semen_distributionDTOList.add(semen_distributionDTO);

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
		return semen_distributionDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM semen_distribution";

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
	public List<Semen_distributionDTO> getAllSemen_distribution (boolean isFirstReload)
    {
		List<Semen_distributionDTO> semen_distributionDTOList = new ArrayList<>();

		String sql = "SELECT * FROM semen_distribution";
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
				Semen_distributionDTO semen_distributionDTO = new Semen_distributionDTO();
				semen_distributionDTO.iD = rs.getLong("ID");
				semen_distributionDTO.bullType = rs.getInt("bull_type");
				semen_distributionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_distributionDTO.requisitionId = rs.getLong("requisition_id");
				semen_distributionDTO.groupId = rs.getInt("group_id");
				semen_distributionDTO.transactionDate = rs.getLong("transaction_date");
				semen_distributionDTO.description = rs.getString("description");
				semen_distributionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_distributionDTO.iD;
				while(i < semen_distributionDTOList.size() && semen_distributionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_distributionDTOList.add(i,  semen_distributionDTO);
				//semen_distributionDTOList.add(semen_distributionDTO);
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

		return semen_distributionDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM semen_distribution";
			
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

			String sql = "SELECT distinct semen_distribution.ID as ID FROM semen_distribution ";
			sql += " join bull on semen_distribution.bull_type = bull.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Semen_distributionMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Semen_distributionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Semen_distributionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Semen_distributionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "semen_distribution." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "semen_distribution." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " semen_distribution.isDeleted = false";				
			
			
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
		Semen_distributionDTO semen_distributionDTO = null;
		List<Semen_distributionDTO> semen_distributionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_distributionDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Semen_distributionMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				semen_distributionDTO = new Semen_distributionDTO();
				semen_distributionDTO.iD = rs.getLong("ID");
				semen_distributionDTO.bullType = rs.getInt("bull_type");
				semen_distributionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_distributionDTO.requisitionId = rs.getLong("requisition_id");
				semen_distributionDTO.groupId = rs.getInt("group_id");
				semen_distributionDTO.transactionDate = rs.getLong("transaction_date");
				semen_distributionDTO.description = rs.getString("description");
				semen_distributionDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = semen_distributionDTO.iD;
				while(i < semen_distributionDTOList.size() && semen_distributionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_distributionDTOList.add(i,  semen_distributionDTO);

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
		return semen_distributionDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	