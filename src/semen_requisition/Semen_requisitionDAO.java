package semen_requisition;

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


public class Semen_requisitionDAO  implements NavigationService{
	
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
		ps.setString(2,"semen_requisition");
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
		ps.setString(2,"semen_requisition");
		ps.execute();
		ps.close();
	}
	
	
	public int getMaxGroupID() throws SQLException
	{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxGroupID = 0;
		try
		{
		
			connection = DatabaseManager.getInstance().getConnection();
			String sql = "select max(group_id) from semen_requisition where isDeleted = 0";
			Statement stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				maxGroupID = rs.getInt("max(group_id)");
				System.out.println("maxGroupID = " + maxGroupID);
		
			}			
		}
		catch(Exception ex)
		{
			System.out.println("ex = " + ex);
			System.out.println("Sql error: " + ex);
		}
		finally
		{
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
		return maxGroupID;
	}
	
	public UserDTO getUserDTO(Semen_requisitionDTO semen_requisitionDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = semen_requisitionDTO.iD;
		// userDTO.userName = semen_requisitionDTO.email;
		// userDTO.fullName = semen_requisitionDTO.name;
		// userDTO.password = semen_requisitionDTO.password;
		// userDTO.phoneNo = semen_requisitionDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = semen_requisitionDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Semen_requisitionDTO semen_requisitionDTO, UserDTO userDTO)
	{
		// userDTO.ID = semen_requisitionDTO.iD;
		// userDTO.fullName = semen_requisitionDTO.name;
		// userDTO.phoneNo = semen_requisitionDTO.phone;
		// userDTO.mailAddress = semen_requisitionDTO.email;

		return userDTO;
	}
		
		
	
	public void addSemen_requisition(Semen_requisitionDTO semen_requisitionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			semen_requisitionDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Semen_requisition");

			String sql = "INSERT INTO semen_requisition";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_date";
			sql += ", ";
			sql += "isDelivered";
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
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(semen_requisitionDTO));
			// semen_requisitionDTO.iD = userDAO.getUserDTOByUsername(semen_requisitionDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + semen_requisitionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.iD);
			//System.out.println("Setting object" + semen_requisitionDTO.groupId + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.groupId);
			//System.out.println("Setting object" + semen_requisitionDTO.centreType + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.centreType);
			//System.out.println("Setting object" + semen_requisitionDTO.breedType + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.breedType);
			//System.out.println("Setting object" + semen_requisitionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.noOfDose);
			//System.out.println("Setting object" + semen_requisitionDTO.requisitionDate + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.requisitionDate);
			//System.out.println("Setting object" + semen_requisitionDTO.isDelivered + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.isDelivered);
			//System.out.println("Setting object" + semen_requisitionDTO.description + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.description);
			//System.out.println("Setting object" + semen_requisitionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.isDeleted);
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
		//Semen_requisitionRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Semen_requisitionDTO getSemen_requisitionDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_requisitionDTO semen_requisitionDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_date";
			sql += ", ";
			sql += "isDelivered";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_requisition";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				semen_requisitionDTO = new Semen_requisitionDTO();

				semen_requisitionDTO.iD = rs.getLong("ID");
				semen_requisitionDTO.groupId = rs.getInt("group_id");
				semen_requisitionDTO.centreType = rs.getInt("centre_type");
				semen_requisitionDTO.breedType = rs.getInt("breed_type");
				semen_requisitionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_requisitionDTO.requisitionDate = rs.getLong("requisition_date");
				semen_requisitionDTO.isDelivered = rs.getBoolean("isDelivered");
				semen_requisitionDTO.description = rs.getString("description");
				semen_requisitionDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return semen_requisitionDTO;
	}
	
	public void updateSemen_requisition(Semen_requisitionDTO semen_requisitionDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE semen_requisition";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "group_id=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "breed_type=?";
			sql += ", ";
			sql += "no_of_dose=?";
			sql += ", ";
			sql += "requisition_date=?";
			sql += ", ";
			sql += "isDelivered=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + semen_requisitionDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + semen_requisitionDTO.iD + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.iD);
			//System.out.println("Setting object" + semen_requisitionDTO.groupId + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.groupId);
			//System.out.println("Setting object" + semen_requisitionDTO.centreType + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.centreType);
			//System.out.println("Setting object" + semen_requisitionDTO.breedType + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.breedType);
			//System.out.println("Setting object" + semen_requisitionDTO.noOfDose + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.noOfDose);
			//System.out.println("Setting object" + semen_requisitionDTO.requisitionDate + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.requisitionDate);
			//System.out.println("Setting object" + semen_requisitionDTO.isDelivered + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.isDelivered);
			//System.out.println("Setting object" + semen_requisitionDTO.description + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.description);
			//System.out.println("Setting object" + semen_requisitionDTO.isDeleted + " in index " + index);
			ps.setObject(index++,semen_requisitionDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(semen_requisitionDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(semen_requisitionDTO, userDTO);
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
		//Semen_requisitionRepository.getInstance().reload(false);
	}
	
	public void deliverSemen_requisition(long groupID) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE semen_requisition set isDelivered = 1 where group_ID = " + groupID;
			
			
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			System.out.println(ps);
			ps.executeUpdate();
			

			
			
			recordUpdateTime(connection, ps, lastModificationTime);
						
			
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
		//Semen_requisitionRepository.getInstance().reload(false);
	}
	
	public void deleteSemen_requisitionByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from semen_requisition WHERE ID = " + ID;
			
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
	private List<Semen_requisitionDTO> getSemen_requisitionDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_requisitionDTO semen_requisitionDTO = null;
		List<Semen_requisitionDTO> semen_requisitionDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_date";
			sql += ", ";
			sql += "isDelivered";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_requisition";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				semen_requisitionDTO = new Semen_requisitionDTO();
				semen_requisitionDTO.iD = rs.getLong("ID");
				semen_requisitionDTO.groupId = rs.getInt("group_id");
				semen_requisitionDTO.centreType = rs.getInt("centre_type");
				semen_requisitionDTO.breedType = rs.getInt("breed_type");
				semen_requisitionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_requisitionDTO.requisitionDate = rs.getLong("requisition_date");
				semen_requisitionDTO.isDelivered = rs.getBoolean("isDelivered");
				semen_requisitionDTO.description = rs.getString("description");
				semen_requisitionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_requisitionDTO.iD;
				while(i < semen_requisitionDTOList.size() && semen_requisitionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_requisitionDTOList.add(i,  semen_requisitionDTO);
				//semen_requisitionDTOList.add(semen_requisitionDTO);
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
		return semen_requisitionDTOList;
	}
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getSemen_requisitionDTOByColumn(filter);
	}
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getSemen_requisitionDTOByColumn(filter);
	}
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getSemen_requisitionDTOByColumn(filter);
	}
	
	
	public int getMaxDose(int group_id, int breed_type)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_requisitionDTO semen_requisitionDTO = null;
		int max_dose = 0;
		try{
			
			String sql = "SELECT max(no_of_dose) from semen_requisition where group_id = " + group_id + " and breed_type = " + breed_type + " group by group_id, breed_type";
		
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				max_dose = rs.getInt("max(no_of_dose)");				
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
		return max_dose;
	}
		
	
	
	public List<Semen_requisitionDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Semen_requisitionDTO semen_requisitionDTO = null;
		List<Semen_requisitionDTO> semen_requisitionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_requisitionDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "group_id";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "requisition_date";
			sql += ", ";
			sql += "isDelivered";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM semen_requisition";
            
            sql += " WHERE group_id IN ( ";

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
			
			long last_groupId = -1;

			while(rs.next()){
				semen_requisitionDTO = new Semen_requisitionDTO();
				semen_requisitionDTO.iD = rs.getLong("ID");
				semen_requisitionDTO.groupId = rs.getInt("group_id");
				semen_requisitionDTO.centreType = rs.getInt("centre_type");
				semen_requisitionDTO.breedType = rs.getInt("breed_type");
				semen_requisitionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_requisitionDTO.requisitionDate = rs.getLong("requisition_date");
				semen_requisitionDTO.isDelivered = rs.getBoolean("isDelivered");
				semen_requisitionDTO.description = rs.getString("description");
				semen_requisitionDTO.isDeleted = rs.getBoolean("isDeleted");
				
				if(semen_requisitionDTO.groupId != last_groupId)
				{
					semen_requisitionDTOList.add(semen_requisitionDTO);
					last_groupId = semen_requisitionDTO.groupId;
				}

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
		return semen_requisitionDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT group_id FROM semen_requisition";

		sql += " WHERE isDeleted = 0";
		
		printSql(sql);
		
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getString("group_id")));
	
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
	public List<Semen_requisitionDTO> getAllSemen_requisition (boolean isFirstReload)
    {
		List<Semen_requisitionDTO> semen_requisitionDTOList = new ArrayList<>();

		String sql = "SELECT * FROM semen_requisition";
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
				Semen_requisitionDTO semen_requisitionDTO = new Semen_requisitionDTO();
				semen_requisitionDTO.iD = rs.getLong("ID");
				semen_requisitionDTO.groupId = rs.getInt("group_id");
				semen_requisitionDTO.centreType = rs.getInt("centre_type");
				semen_requisitionDTO.breedType = rs.getInt("breed_type");
				semen_requisitionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_requisitionDTO.requisitionDate = rs.getLong("requisition_date");
				semen_requisitionDTO.isDelivered = rs.getBoolean("isDelivered");
				semen_requisitionDTO.description = rs.getString("description");
				semen_requisitionDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = semen_requisitionDTO.iD;
				while(i < semen_requisitionDTOList.size() && semen_requisitionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_requisitionDTOList.add(i,  semen_requisitionDTO);
				//semen_requisitionDTOList.add(semen_requisitionDTO);
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

		return semen_requisitionDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM semen_requisition";
			
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

			String sql = "SELECT distinct semen_requisition.group_id as ID FROM semen_requisition ";
			sql += " join centre on semen_requisition.centre_type = centre.ID ";
			sql += " join breed on semen_requisition.breed_type = breed.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Semen_requisitionMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Semen_requisitionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Semen_requisitionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Semen_requisitionMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "semen_requisition." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "semen_requisition." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " semen_requisition.isDeleted = false";				
			
			
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
		Semen_requisitionDTO semen_requisitionDTO = null;
		List<Semen_requisitionDTO> semen_requisitionDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return semen_requisitionDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Semen_requisitionMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				semen_requisitionDTO = new Semen_requisitionDTO();
				semen_requisitionDTO.iD = rs.getLong("ID");
				semen_requisitionDTO.groupId = rs.getInt("group_id");
				semen_requisitionDTO.centreType = rs.getInt("centre_type");
				semen_requisitionDTO.breedType = rs.getInt("breed_type");
				semen_requisitionDTO.noOfDose = rs.getInt("no_of_dose");
				semen_requisitionDTO.requisitionDate = rs.getLong("requisition_date");
				semen_requisitionDTO.isDelivered = rs.getBoolean("isDelivered");
				semen_requisitionDTO.description = rs.getString("description");
				semen_requisitionDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = semen_requisitionDTO.iD;
				while(i < semen_requisitionDTOList.size() && semen_requisitionDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				semen_requisitionDTOList.add(i,  semen_requisitionDTO);

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
		return semen_requisitionDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	