package semen_collection;

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

import stick.StickDTO;
import stick.StickDAO;


public class Semen_collectionDAO  implements NavigationService{
	
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
		ps.setString(2,"semen_collection");
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
		ps.setString(2,"semen_collection");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Semen_collectionDTO semen_collectionDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = semen_collectionDTO.iD;
		// userDTO.userName = semen_collectionDTO.email;
		// userDTO.fullName = semen_collectionDTO.name;
		// userDTO.password = semen_collectionDTO.password;
		// userDTO.phoneNo = semen_collectionDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = semen_collectionDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Semen_collectionDTO semen_collectionDTO, UserDTO userDTO)
	{
		// userDTO.ID = semen_collectionDTO.iD;
		// userDTO.fullName = semen_collectionDTO.name;
		// userDTO.phoneNo = semen_collectionDTO.phone;
		// userDTO.mailAddress = semen_collectionDTO.email;

		return userDTO;
	}
		
		
	
	public void addSemen_collection(Semen_collectionDTO semen_collectionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			semen_collectionDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Semen_collection");

			String sql = "INSERT INTO semen_collection";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_mortality";
			sql += ", ";
			sql += "color_type";
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
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(semen_collectionDTO));
			// semen_collectionDTO.iD = userDAO.getUserDTOByUsername(semen_collectionDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + semen_collectionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.iD);
			//System.out.println("Setting object" + semen_collectionDTO.bullType + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.bullType);
			//System.out.println("Setting object" + semen_collectionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.noOfDose);
			//System.out.println("Setting object" + semen_collectionDTO.volume + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.volume);
			//System.out.println("Setting object" + semen_collectionDTO.density + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.density);
			//System.out.println("Setting object" + semen_collectionDTO.progressiveMortality + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.progressiveMortality);
			//System.out.println("Setting object" + semen_collectionDTO.colorType + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.colorType);
			//System.out.println("Setting object" + semen_collectionDTO.transactionDate + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.transactionDate);
			//System.out.println("Setting object" + semen_collectionDTO.description + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.description);
			//System.out.println("Setting object" + semen_collectionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			/*
			//Insertion into sticks
			StickDTO stickDTO = new StickDTO();
			StickDAO stickDAO = new StickDAO();
			for(int i = 0; i < semen_collectionDTO.noOfDose; i ++)
			{
				stickDTO.semenCollectionId = semen_collectionDTO.iD;
				stickDTO.nameEn = semen_collectionDTO.iD + "_" + i;
				stickDAO.addStick(stickDTO);
			}*/
			
			
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
		//Semen_collectionRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Semen_collectionDTO getSemen_collectionDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_collectionDTO semen_collectionDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_mortality";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_collection";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				semen_collectionDTO = new Semen_collectionDTO();

				semen_collectionDTO.iD = rs.getLong("ID");
				semen_collectionDTO.bullType = rs.getInt("bull_type");
				semen_collectionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_collectionDTO.volume = rs.getInt("volume");
				semen_collectionDTO.density = rs.getInt("density");
				semen_collectionDTO.progressiveMortality = rs.getInt("progressive_mortality");
				semen_collectionDTO.colorType = rs.getInt("color_type");
				semen_collectionDTO.transactionDate = rs.getLong("transaction_date");
				semen_collectionDTO.description = rs.getString("description");
				semen_collectionDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return semen_collectionDTO;
	}
	
	public void updateSemen_collection(Semen_collectionDTO semen_collectionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE semen_collection";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "no_of_dose=?";
			sql += ", ";
			sql += "volume=?";
			sql += ", ";
			sql += "density=?";
			sql += ", ";
			sql += "progressive_mortality=?";
			sql += ", ";
			sql += "color_type=?";
			sql += ", ";
			sql += "transaction_date=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + semen_collectionDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + semen_collectionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.iD);
			//System.out.println("Setting object" + semen_collectionDTO.bullType + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.bullType);
			//System.out.println("Setting object" + semen_collectionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.noOfDose);
			//System.out.println("Setting object" + semen_collectionDTO.volume + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.volume);
			//System.out.println("Setting object" + semen_collectionDTO.density + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.density);
			//System.out.println("Setting object" + semen_collectionDTO.progressiveMortality + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.progressiveMortality);
			//System.out.println("Setting object" + semen_collectionDTO.colorType + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.colorType);
			//System.out.println("Setting object" + semen_collectionDTO.transactionDate + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.transactionDate);
			//System.out.println("Setting object" + semen_collectionDTO.description + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.description);
			//System.out.println("Setting object" + semen_collectionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_collectionDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(semen_collectionDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(semen_collectionDTO, userDTO);
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
		//Semen_collectionRepository.getInstance().reload(false);
	}
	
	public void deleteSemen_collectionByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from semen_collection WHERE ID = " + ID;
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
					"    semen_collection\r\n" + 
					"        INNER JOIN\r\n" + 
					"    bull ON semen_collection.bull_type = bull.id\r\n" + 
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
	
	public int getTotalDoseOfBreed(int breedType, int centre, long current_date)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		int totalDose = 0;
		try{
			
			String sql = "SELECT \r\n" + 
					"    breed_type, bull_type, SUM(no_of_dose)\r\n" + 
					"FROM\r\n" + 
					"    semen_collection\r\n" + 
					"        INNER JOIN\r\n" + 
					"    bull ON semen_collection.bull_type = bull.id\r\n" + 
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
					"                AND date_of_expiration >= " + current_date 
					+ ")\r\n" + 
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
	
	public boolean isBullAllowed(long bullType, int centre, long current_date)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		boolean isPerssible = false;
	
		try{
			
			String sql = "SELECT * FROM permissible_bulls_in_centre where bull_type = " + bullType 
					+ " and centre_type = " + centre 
					+ " and date_of_expiration >= " + current_date;
					//+ " and date_of_entry <= " + current_date;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			if(rs.next())
			{
				isPerssible = true;				
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
		return isPerssible;
	}
	
	//2 versions, big table and small table
	//also a repo version
	//Returns a single DTO
	private List<Semen_collectionDTO> getSemen_collectionDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_collectionDTO semen_collectionDTO = null;
		List<Semen_collectionDTO> semen_collectionDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_mortality";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_collection";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				semen_collectionDTO = new Semen_collectionDTO();
				semen_collectionDTO.iD = rs.getLong("ID");
				semen_collectionDTO.bullType = rs.getInt("bull_type");
				semen_collectionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_collectionDTO.volume = rs.getInt("volume");
				semen_collectionDTO.density = rs.getInt("density");
				semen_collectionDTO.progressiveMortality = rs.getInt("progressive_mortality");
				semen_collectionDTO.colorType = rs.getInt("color_type");
				semen_collectionDTO.transactionDate = rs.getLong("transaction_date");
				semen_collectionDTO.description = rs.getString("description");
				semen_collectionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_collectionDTO.iD;
				while(i < semen_collectionDTOList.size() && semen_collectionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_collectionDTOList.add(i,  semen_collectionDTO);
				//semen_collectionDTOList.add(semen_collectionDTO);
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
		return semen_collectionDTOList;
	}
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getSemen_collectionDTOByColumn(filter);
	}
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getSemen_collectionDTOByColumn(filter);
	}
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getSemen_collectionDTOByColumn(filter);
	}
		
	
	
	public List<Semen_collectionDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_collectionDTO semen_collectionDTO = null;
		List<Semen_collectionDTO> semen_collectionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_collectionDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_mortality";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "transaction_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_collection";
            
            sql += " WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=") order by ID desc";
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				semen_collectionDTO = new Semen_collectionDTO();
				semen_collectionDTO.iD = rs.getLong("ID");
				semen_collectionDTO.bullType = rs.getInt("bull_type");
				semen_collectionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_collectionDTO.volume = rs.getInt("volume");
				semen_collectionDTO.density = rs.getInt("density");
				semen_collectionDTO.progressiveMortality = rs.getInt("progressive_mortality");
				semen_collectionDTO.colorType = rs.getInt("color_type");
				semen_collectionDTO.transactionDate = rs.getLong("transaction_date");
				semen_collectionDTO.description = rs.getString("description");
				semen_collectionDTO.isDeleted = rs.getBoolean("isDeleted");
			
				semen_collectionDTOList.add(semen_collectionDTO);

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
		return semen_collectionDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM semen_collection ";

		sql += " WHERE isDeleted = 0  order by ID desc";
		
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
	public List<Semen_collectionDTO> getAllSemen_collection (boolean isFirstReload)
    {
		List<Semen_collectionDTO> semen_collectionDTOList = new ArrayList<>();

		String sql = "SELECT * FROM semen_collection";
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
				Semen_collectionDTO semen_collectionDTO = new Semen_collectionDTO();
				semen_collectionDTO.iD = rs.getLong("ID");
				semen_collectionDTO.bullType = rs.getInt("bull_type");
				semen_collectionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_collectionDTO.volume = rs.getInt("volume");
				semen_collectionDTO.density = rs.getInt("density");
				semen_collectionDTO.progressiveMortality = rs.getInt("progressive_mortality");
				semen_collectionDTO.colorType = rs.getInt("color_type");
				semen_collectionDTO.transactionDate = rs.getLong("transaction_date");
				semen_collectionDTO.description = rs.getString("description");
				semen_collectionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_collectionDTO.iD;
				while(i < semen_collectionDTOList.size() && semen_collectionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_collectionDTOList.add(i,  semen_collectionDTO);
				//semen_collectionDTOList.add(semen_collectionDTO);
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

		return semen_collectionDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM semen_collection";
			
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

			String sql = "SELECT distinct semen_collection.ID as ID FROM semen_collection ";
			sql += " join bull on semen_collection.bull_type = bull.ID ";
			sql += " join color on semen_collection.color_type = color.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Semen_collectionMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Semen_collectionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Semen_collectionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Semen_collectionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "semen_collection." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "semen_collection." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " semen_collection.isDeleted = false";				
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by semen_collection.ID desc ";

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
		Semen_collectionDTO semen_collectionDTO = null;
		List<Semen_collectionDTO> semen_collectionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_collectionDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Semen_collectionMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				semen_collectionDTO = new Semen_collectionDTO();
				semen_collectionDTO.iD = rs.getLong("ID");
				semen_collectionDTO.bullType = rs.getInt("bull_type");
				semen_collectionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_collectionDTO.volume = rs.getInt("volume");
				semen_collectionDTO.density = rs.getInt("density");
				semen_collectionDTO.progressiveMortality = rs.getInt("progressive_mortality");
				semen_collectionDTO.colorType = rs.getInt("color_type");
				semen_collectionDTO.transactionDate = rs.getLong("transaction_date");
				semen_collectionDTO.description = rs.getString("description");
				semen_collectionDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = semen_collectionDTO.iD;
				while(i < semen_collectionDTOList.size() && semen_collectionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_collectionDTOList.add(i,  semen_collectionDTO);

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
		return semen_collectionDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	