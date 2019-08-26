package bull;

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

import permissible_bulls_in_centre.*;


public class BullDAO  implements NavigationService{
	
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
		ps.setString(2,"bull");
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
		ps.setString(2,"bull");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(BullDTO bullDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = bullDTO.iD;
		// userDTO.userName = bullDTO.email;
		// userDTO.fullName = bullDTO.name;
		// userDTO.password = bullDTO.password;
		// userDTO.phoneNo = bullDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = bullDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(BullDTO bullDTO, UserDTO userDTO)
	{
		// userDTO.ID = bullDTO.iD;
		// userDTO.fullName = bullDTO.name;
		// userDTO.phoneNo = bullDTO.phone;
		// userDTO.mailAddress = bullDTO.email;

		return userDTO;
	}
		
		
	
	public void addBull(BullDTO bullDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			bullDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Bull");

			String sql = "INSERT INTO bull";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "date_of_birth";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "status_type";
			sql += ", ";
			sql += "dam";
			sql += ", ";
			sql += "sire";
			sql += ", ";
			sql += "maternal_grand_dam";
			sql += ", ";
			sql += "maternal_grand_sire";
			sql += ", ";
			sql += "paternal_grand_dam";
			sql += ", ";
			sql += "paternal_grand_sire";
			sql += ", ";
			sql += "milk_yield_of_dam";
			sql += ", ";
			sql += "milk_yield_of_sires_dam";
			sql += ", ";
			sql += "progeny_milk_yield";
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
			// userDAO.addUser(getUserDTO(bullDTO));
			// bullDTO.iD = userDAO.getUserDTOByUsername(bullDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + bullDTO.iD + " in index " + index);
			ps.setObject(index++,bullDTO.iD);
			//System.out.println("Setting object" + bullDTO.nameEn + " in index " + index);
			ps.setObject(index++,bullDTO.nameEn);
			//System.out.println("Setting object" + bullDTO.nameBn + " in index " + index);
			ps.setObject(index++,bullDTO.nameBn);
			//System.out.println("Setting object" + bullDTO.dateOfBirth + " in index " + index);
			ps.setObject(index++,bullDTO.dateOfBirth);
			//System.out.println("Setting object" + bullDTO.breedType + " in index " + index);
			ps.setObject(index++,bullDTO.breedType);
			//System.out.println("Setting object" + bullDTO.statusType + " in index " + index);
			ps.setObject(index++,bullDTO.statusType);
			//System.out.println("Setting object" + bullDTO.dam + " in index " + index);
			ps.setObject(index++,bullDTO.dam);
			//System.out.println("Setting object" + bullDTO.sire + " in index " + index);
			ps.setObject(index++,bullDTO.sire);
			//System.out.println("Setting object" + bullDTO.maternalGrandDam + " in index " + index);
			ps.setObject(index++,bullDTO.maternalGrandDam);
			//System.out.println("Setting object" + bullDTO.maternalGrandSire + " in index " + index);
			ps.setObject(index++,bullDTO.maternalGrandSire);
			//System.out.println("Setting object" + bullDTO.paternalGrandDam + " in index " + index);
			ps.setObject(index++,bullDTO.paternalGrandDam);
			//System.out.println("Setting object" + bullDTO.paternalGrandSire + " in index " + index);
			ps.setObject(index++,bullDTO.paternalGrandSire);
			//System.out.println("Setting object" + bullDTO.milkYieldOfDam + " in index " + index);
			ps.setObject(index++,bullDTO.milkYieldOfDam);
			//System.out.println("Setting object" + bullDTO.milkYieldOfSiresDam + " in index " + index);
			ps.setObject(index++,bullDTO.milkYieldOfSiresDam);
			//System.out.println("Setting object" + bullDTO.progenyMilkYield + " in index " + index);
			ps.setObject(index++,bullDTO.progenyMilkYield);
			//System.out.println("Setting object" + bullDTO.description + " in index " + index);
			ps.setObject(index++,bullDTO.description);
			//System.out.println("Setting object" + bullDTO.isDeleted + " in index " + index);
			ps.setObject(index++,bullDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
			permissible_bulls_in_centreDTO.bullType = (int) bullDTO.iD;
			permissible_bulls_in_centreDTO.centreType = 3;
			permissible_bulls_in_centreDTO.isDeleted = false;
			permissible_bulls_in_centreDTO.dateOfEntry = 0;
			permissible_bulls_in_centreDTO.dateOfExpiration = 0;
			
			Permissible_bulls_in_centreDAO permissible_bulls_in_centreDAO = new Permissible_bulls_in_centreDAO();
			permissible_bulls_in_centreDAO.addPermissible_bulls_in_centre(permissible_bulls_in_centreDTO);
			
			
			
			
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
		//BullRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public BullDTO getBullDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BullDTO bullDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "date_of_birth";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "status_type";
			sql += ", ";
			sql += "dam";
			sql += ", ";
			sql += "sire";
			sql += ", ";
			sql += "maternal_grand_dam";
			sql += ", ";
			sql += "maternal_grand_sire";
			sql += ", ";
			sql += "paternal_grand_dam";
			sql += ", ";
			sql += "paternal_grand_sire";
			sql += ", ";
			sql += "milk_yield_of_dam";
			sql += ", ";
			sql += "milk_yield_of_sires_dam";
			sql += ", ";
			sql += "progeny_milk_yield";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				bullDTO = new BullDTO();

				bullDTO.iD = rs.getLong("ID");
				bullDTO.nameEn = rs.getString("name_en");
				bullDTO.nameBn = rs.getString("name_bn");
				bullDTO.dateOfBirth = rs.getLong("date_of_birth");
				bullDTO.breedType = rs.getLong("breed_type");
				bullDTO.statusType = rs.getLong("status_type");
				bullDTO.dam = rs.getString("dam");
				bullDTO.sire = rs.getString("sire");
				bullDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				bullDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				bullDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				bullDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				bullDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				bullDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				bullDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				bullDTO.description = rs.getString("description");
				bullDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return bullDTO;
	}
	
	public void updateBull(BullDTO bullDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE bull";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name_en=?";
			sql += ", ";
			sql += "name_bn=?";
			sql += ", ";
			sql += "date_of_birth=?";
			sql += ", ";
			sql += "breed_type=?";
			sql += ", ";
			sql += "status_type=?";
			sql += ", ";
			sql += "dam=?";
			sql += ", ";
			sql += "sire=?";
			sql += ", ";
			sql += "maternal_grand_dam=?";
			sql += ", ";
			sql += "maternal_grand_sire=?";
			sql += ", ";
			sql += "paternal_grand_dam=?";
			sql += ", ";
			sql += "paternal_grand_sire=?";
			sql += ", ";
			sql += "milk_yield_of_dam=?";
			sql += ", ";
			sql += "milk_yield_of_sires_dam=?";
			sql += ", ";
			sql += "progeny_milk_yield=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + bullDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + bullDTO.iD + " in index " + index);
			ps.setObject(index++,bullDTO.iD);
			//System.out.println("Setting object" + bullDTO.nameEn + " in index " + index);
			ps.setObject(index++,bullDTO.nameEn);
			//System.out.println("Setting object" + bullDTO.nameBn + " in index " + index);
			ps.setObject(index++,bullDTO.nameBn);
			//System.out.println("Setting object" + bullDTO.dateOfBirth + " in index " + index);
			ps.setObject(index++,bullDTO.dateOfBirth);
			//System.out.println("Setting object" + bullDTO.breedType + " in index " + index);
			ps.setObject(index++,bullDTO.breedType);
			//System.out.println("Setting object" + bullDTO.statusType + " in index " + index);
			ps.setObject(index++,bullDTO.statusType);
			//System.out.println("Setting object" + bullDTO.dam + " in index " + index);
			ps.setObject(index++,bullDTO.dam);
			//System.out.println("Setting object" + bullDTO.sire + " in index " + index);
			ps.setObject(index++,bullDTO.sire);
			//System.out.println("Setting object" + bullDTO.maternalGrandDam + " in index " + index);
			ps.setObject(index++,bullDTO.maternalGrandDam);
			//System.out.println("Setting object" + bullDTO.maternalGrandSire + " in index " + index);
			ps.setObject(index++,bullDTO.maternalGrandSire);
			//System.out.println("Setting object" + bullDTO.paternalGrandDam + " in index " + index);
			ps.setObject(index++,bullDTO.paternalGrandDam);
			//System.out.println("Setting object" + bullDTO.paternalGrandSire + " in index " + index);
			ps.setObject(index++,bullDTO.paternalGrandSire);
			//System.out.println("Setting object" + bullDTO.milkYieldOfDam + " in index " + index);
			ps.setObject(index++,bullDTO.milkYieldOfDam);
			//System.out.println("Setting object" + bullDTO.milkYieldOfSiresDam + " in index " + index);
			ps.setObject(index++,bullDTO.milkYieldOfSiresDam);
			//System.out.println("Setting object" + bullDTO.progenyMilkYield + " in index " + index);
			ps.setObject(index++,bullDTO.progenyMilkYield);
			//System.out.println("Setting object" + bullDTO.description + " in index " + index);
			ps.setObject(index++,bullDTO.description);
			//System.out.println("Setting object" + bullDTO.isDeleted + " in index " + index);
			ps.setObject(index++,bullDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(bullDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(bullDTO, userDTO);
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
		//BullRepository.getInstance().reload(false);
	}
	
	public void deleteBullByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from bull WHERE ID = " + ID;
			
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
	private List<BullDTO> getBullDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BullDTO bullDTO = null;
		List<BullDTO> bullDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "date_of_birth";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "status_type";
			sql += ", ";
			sql += "dam";
			sql += ", ";
			sql += "sire";
			sql += ", ";
			sql += "maternal_grand_dam";
			sql += ", ";
			sql += "maternal_grand_sire";
			sql += ", ";
			sql += "paternal_grand_dam";
			sql += ", ";
			sql += "paternal_grand_sire";
			sql += ", ";
			sql += "milk_yield_of_dam";
			sql += ", ";
			sql += "milk_yield_of_sires_dam";
			sql += ", ";
			sql += "progeny_milk_yield";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				bullDTO = new BullDTO();
				bullDTO.iD = rs.getLong("ID");
				bullDTO.nameEn = rs.getString("name_en");
				bullDTO.nameBn = rs.getString("name_bn");
				bullDTO.dateOfBirth = rs.getLong("date_of_birth");
				bullDTO.breedType = rs.getLong("breed_type");
				bullDTO.statusType = rs.getLong("status_type");
				bullDTO.dam = rs.getString("dam");
				bullDTO.sire = rs.getString("sire");
				bullDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				bullDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				bullDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				bullDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				bullDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				bullDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				bullDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				bullDTO.description = rs.getString("description");
				bullDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = bullDTO.iD;
				while(i < bullDTOList.size() && bullDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bullDTOList.add(i,  bullDTO);
				//bullDTOList.add(bullDTO);
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
		return bullDTOList;
	}
	
	public List<BullDTO> getBullDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getBullDTOByColumn(filter);
	}
	
	public List<BullDTO> getBullDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getBullDTOByColumn(filter);
	}
	
	public List<BullDTO> getBullDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getBullDTOByColumn(filter);
	}
		
	public String getBullName(long id){
		String name = "";
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT name_en FROM bull";
			
			sql += " WHERE isDeleted = 0 and id = " + id;

				
			
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				name = rs.getString("name_en");
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
		return name;
	}
	
	public List<BullDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		BullDTO bullDTO = null;
		List<BullDTO> bullDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return bullDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "name_en";
			sql += ", ";
			sql += "name_bn";
			sql += ", ";
			sql += "date_of_birth";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "status_type";
			sql += ", ";
			sql += "dam";
			sql += ", ";
			sql += "sire";
			sql += ", ";
			sql += "maternal_grand_dam";
			sql += ", ";
			sql += "maternal_grand_sire";
			sql += ", ";
			sql += "paternal_grand_dam";
			sql += ", ";
			sql += "paternal_grand_sire";
			sql += ", ";
			sql += "milk_yield_of_dam";
			sql += ", ";
			sql += "milk_yield_of_sires_dam";
			sql += ", ";
			sql += "progeny_milk_yield";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull";
            
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
				bullDTO = new BullDTO();
				bullDTO.iD = rs.getLong("ID");
				bullDTO.nameEn = rs.getString("name_en");
				bullDTO.nameBn = rs.getString("name_bn");
				bullDTO.dateOfBirth = rs.getLong("date_of_birth");
				bullDTO.breedType = rs.getLong("breed_type");
				bullDTO.statusType = rs.getLong("status_type");
				bullDTO.dam = rs.getString("dam");
				bullDTO.sire = rs.getString("sire");
				bullDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				bullDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				bullDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				bullDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				bullDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				bullDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				bullDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				bullDTO.description = rs.getString("description");
				bullDTO.isDeleted = rs.getBoolean("isDeleted");
				
				bullDTOList.add(bullDTO);

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
		return bullDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM bull";

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
	public List<BullDTO> getAllBull (boolean isFirstReload)
    {
		List<BullDTO> bullDTOList = new ArrayList<>();

		String sql = "SELECT * FROM bull";
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
				BullDTO bullDTO = new BullDTO();
				bullDTO.iD = rs.getLong("ID");
				bullDTO.nameEn = rs.getString("name_en");
				bullDTO.nameBn = rs.getString("name_bn");
				bullDTO.dateOfBirth = rs.getLong("date_of_birth");
				bullDTO.breedType = rs.getLong("breed_type");
				bullDTO.statusType = rs.getLong("status_type");
				bullDTO.dam = rs.getString("dam");
				bullDTO.sire = rs.getString("sire");
				bullDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				bullDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				bullDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				bullDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				bullDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				bullDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				bullDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				bullDTO.description = rs.getString("description");
				bullDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = bullDTO.iD;
				while(i < bullDTOList.size() && bullDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bullDTOList.add(i,  bullDTO);
				//bullDTOList.add(bullDTO);
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

		return bullDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM bull";
			
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

			String sql = "SELECT distinct bull.ID as ID FROM bull ";
			sql += " join breed on bull.breed_type = breed.ID ";
			sql += " join status on bull.status_type = status.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = BullMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(BullMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !BullMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(BullMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "bull." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "bull." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " bull.isDeleted = false";				
			
			
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
		BullDTO bullDTO = null;
		List<BullDTO> bullDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return bullDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = BullMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				bullDTO = new BullDTO();
				bullDTO.iD = rs.getLong("ID");
				bullDTO.nameEn = rs.getString("name_en");
				bullDTO.nameBn = rs.getString("name_bn");
				bullDTO.dateOfBirth = rs.getLong("date_of_birth");
				bullDTO.breedType = rs.getLong("breed_type");
				bullDTO.statusType = rs.getLong("status_type");
				bullDTO.dam = rs.getString("dam");
				bullDTO.sire = rs.getString("sire");
				bullDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				bullDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				bullDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				bullDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				bullDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				bullDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				bullDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				bullDTO.description = rs.getString("description");
				bullDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = bullDTO.iD;
				while(i < bullDTOList.size() && bullDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bullDTOList.add(i,  bullDTO);

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
		return bullDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	