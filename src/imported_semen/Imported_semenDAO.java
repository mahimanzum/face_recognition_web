package imported_semen;

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


public class Imported_semenDAO  implements NavigationService{
	
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
		ps.setString(2,"imported_semen");
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
		ps.setString(2,"imported_semen");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Imported_semenDTO imported_semenDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = imported_semenDTO.iD;
		// userDTO.userName = imported_semenDTO.email;
		// userDTO.fullName = imported_semenDTO.name;
		// userDTO.password = imported_semenDTO.password;
		// userDTO.phoneNo = imported_semenDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = imported_semenDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Imported_semenDTO imported_semenDTO, UserDTO userDTO)
	{
		// userDTO.ID = imported_semenDTO.iD;
		// userDTO.fullName = imported_semenDTO.name;
		// userDTO.phoneNo = imported_semenDTO.phone;
		// userDTO.mailAddress = imported_semenDTO.email;

		return userDTO;
	}
		
		
	
	public void addImported_semen(Imported_semenDTO imported_semenDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			imported_semenDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Imported_semen");

			String sql = "INSERT INTO imported_semen";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "bull_tag";
			sql += ", ";
			sql += "date_of_birth";
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
			sql += "breed";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "received_amount";
			sql += ", ";
			sql += "distributed_amount";
			sql += ", ";
			sql += "to_whom_distributed";
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
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(imported_semenDTO));
			// imported_semenDTO.iD = userDAO.getUserDTOByUsername(imported_semenDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + imported_semenDTO.iD + " in index " + index);
			ps.setObject(index++,imported_semenDTO.iD);
			//System.out.println("Setting object" + imported_semenDTO.bullTag + " in index " + index);
			ps.setObject(index++,imported_semenDTO.bullTag);
			//System.out.println("Setting object" + imported_semenDTO.dateOfBirth + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dateOfBirth);
			//System.out.println("Setting object" + imported_semenDTO.dam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dam);
			//System.out.println("Setting object" + imported_semenDTO.sire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.sire);
			//System.out.println("Setting object" + imported_semenDTO.maternalGrandDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.maternalGrandDam);
			//System.out.println("Setting object" + imported_semenDTO.maternalGrandSire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.maternalGrandSire);
			//System.out.println("Setting object" + imported_semenDTO.paternalGrandDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.paternalGrandDam);
			//System.out.println("Setting object" + imported_semenDTO.paternalGrandSire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.paternalGrandSire);
			//System.out.println("Setting object" + imported_semenDTO.milkYieldOfDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.milkYieldOfDam);
			//System.out.println("Setting object" + imported_semenDTO.milkYieldOfSiresDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.milkYieldOfSiresDam);
			//System.out.println("Setting object" + imported_semenDTO.progenyMilkYield + " in index " + index);
			ps.setObject(index++,imported_semenDTO.progenyMilkYield);
			//System.out.println("Setting object" + imported_semenDTO.breed + " in index " + index);
			ps.setObject(index++,imported_semenDTO.breed);
			//System.out.println("Setting object" + imported_semenDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dateOfEntry);
			//System.out.println("Setting object" + imported_semenDTO.receivedAmount + " in index " + index);
			ps.setObject(index++,imported_semenDTO.receivedAmount);
			//System.out.println("Setting object" + imported_semenDTO.distributedAmount + " in index " + index);
			ps.setObject(index++,imported_semenDTO.distributedAmount);
			//System.out.println("Setting object" + imported_semenDTO.toWhomDistributed + " in index " + index);
			ps.setObject(index++,imported_semenDTO.toWhomDistributed);
			//System.out.println("Setting object" + imported_semenDTO.description + " in index " + index);
			ps.setObject(index++,imported_semenDTO.description);
			//System.out.println("Setting object" + imported_semenDTO.isDeleted + " in index " + index);
			ps.setObject(index++,imported_semenDTO.isDeleted);
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
		//Imported_semenRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Imported_semenDTO getImported_semenDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Imported_semenDTO imported_semenDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_tag";
			sql += ", ";
			sql += "date_of_birth";
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
			sql += "breed";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "received_amount";
			sql += ", ";
			sql += "distributed_amount";
			sql += ", ";
			sql += "to_whom_distributed";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM imported_semen";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				imported_semenDTO = new Imported_semenDTO();

				imported_semenDTO.iD = rs.getLong("ID");
				imported_semenDTO.bullTag = rs.getString("bull_tag");
				imported_semenDTO.dateOfBirth = rs.getLong("date_of_birth");
				imported_semenDTO.dam = rs.getString("dam");
				imported_semenDTO.sire = rs.getString("sire");
				imported_semenDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				imported_semenDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				imported_semenDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				imported_semenDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				imported_semenDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				imported_semenDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				imported_semenDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				imported_semenDTO.breed = rs.getString("breed");
				imported_semenDTO.dateOfEntry = rs.getLong("date_of_entry");
				imported_semenDTO.receivedAmount = rs.getInt("received_amount");
				imported_semenDTO.distributedAmount = rs.getInt("distributed_amount");
				imported_semenDTO.toWhomDistributed = rs.getString("to_whom_distributed");
				imported_semenDTO.description = rs.getString("description");
				imported_semenDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return imported_semenDTO;
	}
	
	public void updateImported_semen(Imported_semenDTO imported_semenDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE imported_semen";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "bull_tag=?";
			sql += ", ";
			sql += "date_of_birth=?";
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
			sql += "breed=?";
			sql += ", ";
			sql += "date_of_entry=?";
			sql += ", ";
			sql += "received_amount=?";
			sql += ", ";
			sql += "distributed_amount=?";
			sql += ", ";
			sql += "to_whom_distributed=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + imported_semenDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + imported_semenDTO.iD + " in index " + index);
			ps.setObject(index++,imported_semenDTO.iD);
			//System.out.println("Setting object" + imported_semenDTO.bullTag + " in index " + index);
			ps.setObject(index++,imported_semenDTO.bullTag);
			//System.out.println("Setting object" + imported_semenDTO.dateOfBirth + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dateOfBirth);
			//System.out.println("Setting object" + imported_semenDTO.dam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dam);
			//System.out.println("Setting object" + imported_semenDTO.sire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.sire);
			//System.out.println("Setting object" + imported_semenDTO.maternalGrandDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.maternalGrandDam);
			//System.out.println("Setting object" + imported_semenDTO.maternalGrandSire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.maternalGrandSire);
			//System.out.println("Setting object" + imported_semenDTO.paternalGrandDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.paternalGrandDam);
			//System.out.println("Setting object" + imported_semenDTO.paternalGrandSire + " in index " + index);
			ps.setObject(index++,imported_semenDTO.paternalGrandSire);
			//System.out.println("Setting object" + imported_semenDTO.milkYieldOfDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.milkYieldOfDam);
			//System.out.println("Setting object" + imported_semenDTO.milkYieldOfSiresDam + " in index " + index);
			ps.setObject(index++,imported_semenDTO.milkYieldOfSiresDam);
			//System.out.println("Setting object" + imported_semenDTO.progenyMilkYield + " in index " + index);
			ps.setObject(index++,imported_semenDTO.progenyMilkYield);
			//System.out.println("Setting object" + imported_semenDTO.breed + " in index " + index);
			ps.setObject(index++,imported_semenDTO.breed);
			//System.out.println("Setting object" + imported_semenDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,imported_semenDTO.dateOfEntry);
			//System.out.println("Setting object" + imported_semenDTO.receivedAmount + " in index " + index);
			ps.setObject(index++,imported_semenDTO.receivedAmount);
			//System.out.println("Setting object" + imported_semenDTO.distributedAmount + " in index " + index);
			ps.setObject(index++,imported_semenDTO.distributedAmount);
			//System.out.println("Setting object" + imported_semenDTO.toWhomDistributed + " in index " + index);
			ps.setObject(index++,imported_semenDTO.toWhomDistributed);
			//System.out.println("Setting object" + imported_semenDTO.description + " in index " + index);
			ps.setObject(index++,imported_semenDTO.description);
			//System.out.println("Setting object" + imported_semenDTO.isDeleted + " in index " + index);
			ps.setObject(index++,imported_semenDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(imported_semenDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(imported_semenDTO, userDTO);
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
		//Imported_semenRepository.getInstance().reload(false);
	}
	
	public void deleteImported_semenByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE imported_semen";
			
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
	private List<Imported_semenDTO> getImported_semenDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Imported_semenDTO imported_semenDTO = null;
		List<Imported_semenDTO> imported_semenDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_tag";
			sql += ", ";
			sql += "date_of_birth";
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
			sql += "breed";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "received_amount";
			sql += ", ";
			sql += "distributed_amount";
			sql += ", ";
			sql += "to_whom_distributed";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM imported_semen";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				imported_semenDTO = new Imported_semenDTO();
				imported_semenDTO.iD = rs.getLong("ID");
				imported_semenDTO.bullTag = rs.getString("bull_tag");
				imported_semenDTO.dateOfBirth = rs.getLong("date_of_birth");
				imported_semenDTO.dam = rs.getString("dam");
				imported_semenDTO.sire = rs.getString("sire");
				imported_semenDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				imported_semenDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				imported_semenDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				imported_semenDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				imported_semenDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				imported_semenDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				imported_semenDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				imported_semenDTO.breed = rs.getString("breed");
				imported_semenDTO.dateOfEntry = rs.getLong("date_of_entry");
				imported_semenDTO.receivedAmount = rs.getInt("received_amount");
				imported_semenDTO.distributedAmount = rs.getInt("distributed_amount");
				imported_semenDTO.toWhomDistributed = rs.getString("to_whom_distributed");
				imported_semenDTO.description = rs.getString("description");
				imported_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = imported_semenDTO.iD;
				while(i < imported_semenDTOList.size() && imported_semenDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				imported_semenDTOList.add(i,  imported_semenDTO);
				//imported_semenDTOList.add(imported_semenDTO);
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
		return imported_semenDTOList;
	}
	
	public List<Imported_semenDTO> getImported_semenDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getImported_semenDTOByColumn(filter);
	}
	
	public List<Imported_semenDTO> getImported_semenDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getImported_semenDTOByColumn(filter);
	}
	
	public List<Imported_semenDTO> getImported_semenDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getImported_semenDTOByColumn(filter);
	}
		
	
	
	public List<Imported_semenDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Imported_semenDTO imported_semenDTO = null;
		List<Imported_semenDTO> imported_semenDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return imported_semenDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_tag";
			sql += ", ";
			sql += "date_of_birth";
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
			sql += "breed";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "received_amount";
			sql += ", ";
			sql += "distributed_amount";
			sql += ", ";
			sql += "to_whom_distributed";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM imported_semen";
            
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
				imported_semenDTO = new Imported_semenDTO();
				imported_semenDTO.iD = rs.getLong("ID");
				imported_semenDTO.bullTag = rs.getString("bull_tag");
				imported_semenDTO.dateOfBirth = rs.getLong("date_of_birth");
				imported_semenDTO.dam = rs.getString("dam");
				imported_semenDTO.sire = rs.getString("sire");
				imported_semenDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				imported_semenDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				imported_semenDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				imported_semenDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				imported_semenDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				imported_semenDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				imported_semenDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				imported_semenDTO.breed = rs.getString("breed");
				imported_semenDTO.dateOfEntry = rs.getLong("date_of_entry");
				imported_semenDTO.receivedAmount = rs.getInt("received_amount");
				imported_semenDTO.distributedAmount = rs.getInt("distributed_amount");
				imported_semenDTO.toWhomDistributed = rs.getString("to_whom_distributed");
				imported_semenDTO.description = rs.getString("description");
				imported_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + imported_semenDTO);
				
				imported_semenDTOList.add(imported_semenDTO);

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
		return imported_semenDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM imported_semen";

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
	public List<Imported_semenDTO> getAllImported_semen (boolean isFirstReload)
    {
		List<Imported_semenDTO> imported_semenDTOList = new ArrayList<>();

		String sql = "SELECT * FROM imported_semen";
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
				Imported_semenDTO imported_semenDTO = new Imported_semenDTO();
				imported_semenDTO.iD = rs.getLong("ID");
				imported_semenDTO.bullTag = rs.getString("bull_tag");
				imported_semenDTO.dateOfBirth = rs.getLong("date_of_birth");
				imported_semenDTO.dam = rs.getString("dam");
				imported_semenDTO.sire = rs.getString("sire");
				imported_semenDTO.maternalGrandDam = rs.getString("maternal_grand_dam");
				imported_semenDTO.maternalGrandSire = rs.getString("maternal_grand_sire");
				imported_semenDTO.paternalGrandDam = rs.getString("paternal_grand_dam");
				imported_semenDTO.paternalGrandSire = rs.getString("paternal_grand_sire");
				imported_semenDTO.milkYieldOfDam = rs.getInt("milk_yield_of_dam");
				imported_semenDTO.milkYieldOfSiresDam = rs.getInt("milk_yield_of_sires_dam");
				imported_semenDTO.progenyMilkYield = rs.getInt("progeny_milk_yield");
				imported_semenDTO.breed = rs.getString("breed");
				imported_semenDTO.dateOfEntry = rs.getLong("date_of_entry");
				imported_semenDTO.receivedAmount = rs.getInt("received_amount");
				imported_semenDTO.distributedAmount = rs.getInt("distributed_amount");
				imported_semenDTO.toWhomDistributed = rs.getString("to_whom_distributed");
				imported_semenDTO.description = rs.getString("description");
				imported_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = imported_semenDTO.iD;
				while(i < imported_semenDTOList.size() && imported_semenDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				imported_semenDTOList.add(i,  imported_semenDTO);
				//imported_semenDTOList.add(imported_semenDTO);
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

		return imported_semenDTOList;
    }
	

	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{

			String sql = "SELECT distinct imported_semen.ID as ID FROM imported_semen ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Imported_semenMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Imported_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Imported_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Imported_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "imported_semen." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "imported_semen." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " imported_semen.isDeleted = false";
			
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by imported_semen.ID desc ";

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
	
	
	
		
}
	