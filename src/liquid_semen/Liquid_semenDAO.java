package liquid_semen;

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


public class Liquid_semenDAO  implements NavigationService{
	
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
		ps.setString(2,"liquid_semen");
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
		ps.setString(2,"liquid_semen");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Liquid_semenDTO liquid_semenDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = liquid_semenDTO.iD;
		// userDTO.userName = liquid_semenDTO.email;
		// userDTO.fullName = liquid_semenDTO.name;
		// userDTO.password = liquid_semenDTO.password;
		// userDTO.phoneNo = liquid_semenDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = liquid_semenDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Liquid_semenDTO liquid_semenDTO, UserDTO userDTO)
	{
		// userDTO.ID = liquid_semenDTO.iD;
		// userDTO.fullName = liquid_semenDTO.name;
		// userDTO.phoneNo = liquid_semenDTO.phone;
		// userDTO.mailAddress = liquid_semenDTO.email;

		return userDTO;
	}
		
		
	
	public void addLiquid_semen(Liquid_semenDTO liquid_semenDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			liquid_semenDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Liquid_semen");

			String sql = "INSERT INTO liquid_semen";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_motility";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "collection_distribution_date";
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
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(liquid_semenDTO));
			// liquid_semenDTO.iD = userDAO.getUserDTOByUsername(liquid_semenDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + liquid_semenDTO.iD + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.iD);
			//System.out.println("Setting object" + liquid_semenDTO.centreType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.centreType);
			//System.out.println("Setting object" + liquid_semenDTO.bullType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.bullType);
			//System.out.println("Setting object" + liquid_semenDTO.noOfDose + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.noOfDose);
			//System.out.println("Setting object" + liquid_semenDTO.volume + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.volume);
			//System.out.println("Setting object" + liquid_semenDTO.density + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.density);
			//System.out.println("Setting object" + liquid_semenDTO.progressiveMotility + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.progressiveMotility);
			//System.out.println("Setting object" + liquid_semenDTO.colorType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.colorType);
			//System.out.println("Setting object" + liquid_semenDTO.collectionDistributionDate + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.collectionDistributionDate);
			//System.out.println("Setting object" + liquid_semenDTO.description + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.description);
			//System.out.println("Setting object" + liquid_semenDTO.isDeleted + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.isDeleted);
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
		//Liquid_semenRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Liquid_semenDTO getLiquid_semenDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Liquid_semenDTO liquid_semenDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_motility";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "collection_distribution_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM liquid_semen";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				liquid_semenDTO = new Liquid_semenDTO();

				liquid_semenDTO.iD = rs.getLong("ID");
				liquid_semenDTO.centreType = rs.getInt("centre_type");
				liquid_semenDTO.bullType = rs.getInt("bull_type");
				liquid_semenDTO.noOfDose = rs.getInt("no_of_dose");
				liquid_semenDTO.volume = rs.getInt("volume");
				liquid_semenDTO.density = rs.getInt("density");
				liquid_semenDTO.progressiveMotility = rs.getInt("progressive_motility");
				liquid_semenDTO.colorType = rs.getInt("color_type");
				liquid_semenDTO.collectionDistributionDate = rs.getLong("collection_distribution_date");
				liquid_semenDTO.description = rs.getString("description");
				liquid_semenDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return liquid_semenDTO;
	}
	
	public void updateLiquid_semen(Liquid_semenDTO liquid_semenDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE liquid_semen";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "no_of_dose=?";
			sql += ", ";
			sql += "volume=?";
			sql += ", ";
			sql += "density=?";
			sql += ", ";
			sql += "progressive_motility=?";
			sql += ", ";
			sql += "color_type=?";
			sql += ", ";
			sql += "collection_distribution_date=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + liquid_semenDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + liquid_semenDTO.iD + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.iD);
			//System.out.println("Setting object" + liquid_semenDTO.centreType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.centreType);
			//System.out.println("Setting object" + liquid_semenDTO.bullType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.bullType);
			//System.out.println("Setting object" + liquid_semenDTO.noOfDose + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.noOfDose);
			//System.out.println("Setting object" + liquid_semenDTO.volume + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.volume);
			//System.out.println("Setting object" + liquid_semenDTO.density + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.density);
			//System.out.println("Setting object" + liquid_semenDTO.progressiveMotility + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.progressiveMotility);
			//System.out.println("Setting object" + liquid_semenDTO.colorType + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.colorType);
			//System.out.println("Setting object" + liquid_semenDTO.collectionDistributionDate + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.collectionDistributionDate);
			//System.out.println("Setting object" + liquid_semenDTO.description + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.description);
			//System.out.println("Setting object" + liquid_semenDTO.isDeleted + " in index " + index);
			ps.setObject(index++,liquid_semenDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(liquid_semenDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(liquid_semenDTO, userDTO);
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
		//Liquid_semenRepository.getInstance().reload(false);
	}
	
	public void deleteLiquid_semenByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{

			
			String sql = "delete from liquid_semen where ID = " + ID;
			
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
	private List<Liquid_semenDTO> getLiquid_semenDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Liquid_semenDTO liquid_semenDTO = null;
		List<Liquid_semenDTO> liquid_semenDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_motility";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "collection_distribution_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM liquid_semen";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				liquid_semenDTO = new Liquid_semenDTO();
				liquid_semenDTO.iD = rs.getLong("ID");
				liquid_semenDTO.centreType = rs.getInt("centre_type");
				liquid_semenDTO.bullType = rs.getInt("bull_type");
				liquid_semenDTO.noOfDose = rs.getInt("no_of_dose");
				liquid_semenDTO.volume = rs.getInt("volume");
				liquid_semenDTO.density = rs.getInt("density");
				liquid_semenDTO.progressiveMotility = rs.getInt("progressive_motility");
				liquid_semenDTO.colorType = rs.getInt("color_type");
				liquid_semenDTO.collectionDistributionDate = rs.getLong("collection_distribution_date");
				liquid_semenDTO.description = rs.getString("description");
				liquid_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = liquid_semenDTO.iD;
				while(i < liquid_semenDTOList.size() && liquid_semenDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				liquid_semenDTOList.add(i,  liquid_semenDTO);
				//liquid_semenDTOList.add(liquid_semenDTO);
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
		return liquid_semenDTOList;
	}
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getLiquid_semenDTOByColumn(filter);
	}
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getLiquid_semenDTOByColumn(filter);
	}
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getLiquid_semenDTOByColumn(filter);
	}
		
	
	
	public List<Liquid_semenDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Liquid_semenDTO liquid_semenDTO = null;
		List<Liquid_semenDTO> liquid_semenDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return liquid_semenDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_dose";
			sql += ", ";
			sql += "volume";
			sql += ", ";
			sql += "density";
			sql += ", ";
			sql += "progressive_motility";
			sql += ", ";
			sql += "color_type";
			sql += ", ";
			sql += "collection_distribution_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM liquid_semen";
            
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
				liquid_semenDTO = new Liquid_semenDTO();
				liquid_semenDTO.iD = rs.getLong("ID");
				liquid_semenDTO.centreType = rs.getInt("centre_type");
				liquid_semenDTO.bullType = rs.getInt("bull_type");
				liquid_semenDTO.noOfDose = rs.getInt("no_of_dose");
				liquid_semenDTO.volume = rs.getInt("volume");
				liquid_semenDTO.density = rs.getInt("density");
				liquid_semenDTO.progressiveMotility = rs.getInt("progressive_motility");
				liquid_semenDTO.colorType = rs.getInt("color_type");
				liquid_semenDTO.collectionDistributionDate = rs.getLong("collection_distribution_date");
				liquid_semenDTO.description = rs.getString("description");
				liquid_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				
				liquid_semenDTOList.add(liquid_semenDTO);

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
		return liquid_semenDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM liquid_semen";

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
	public List<Liquid_semenDTO> getAllLiquid_semen (boolean isFirstReload)
    {
		List<Liquid_semenDTO> liquid_semenDTOList = new ArrayList<>();

		String sql = "SELECT * FROM liquid_semen";
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
				Liquid_semenDTO liquid_semenDTO = new Liquid_semenDTO();
				liquid_semenDTO.iD = rs.getLong("ID");
				liquid_semenDTO.centreType = rs.getInt("centre_type");
				liquid_semenDTO.bullType = rs.getInt("bull_type");
				liquid_semenDTO.noOfDose = rs.getInt("no_of_dose");
				liquid_semenDTO.volume = rs.getInt("volume");
				liquid_semenDTO.density = rs.getInt("density");
				liquid_semenDTO.progressiveMotility = rs.getInt("progressive_motility");
				liquid_semenDTO.colorType = rs.getInt("color_type");
				liquid_semenDTO.collectionDistributionDate = rs.getLong("collection_distribution_date");
				liquid_semenDTO.description = rs.getString("description");
				liquid_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = liquid_semenDTO.iD;
				while(i < liquid_semenDTOList.size() && liquid_semenDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				liquid_semenDTOList.add(i,  liquid_semenDTO);
				//liquid_semenDTOList.add(liquid_semenDTO);
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

		return liquid_semenDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM liquid_semen";
			
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

			String sql = "SELECT distinct liquid_semen.ID as ID FROM liquid_semen ";
			sql += " join centre on liquid_semen.centre_type = centre.ID ";
			sql += " join bull on liquid_semen.bull_type = bull.ID ";
			sql += " join color on liquid_semen.color_type = color.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Liquid_semenMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Liquid_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Liquid_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Liquid_semenMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "liquid_semen." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "liquid_semen." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " liquid_semen.isDeleted = false";				
			
			
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
		Liquid_semenDTO liquid_semenDTO = null;
		List<Liquid_semenDTO> liquid_semenDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return liquid_semenDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Liquid_semenMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				liquid_semenDTO = new Liquid_semenDTO();
				liquid_semenDTO.iD = rs.getLong("ID");
				liquid_semenDTO.centreType = rs.getInt("centre_type");
				liquid_semenDTO.bullType = rs.getInt("bull_type");
				liquid_semenDTO.noOfDose = rs.getInt("no_of_dose");
				liquid_semenDTO.volume = rs.getInt("volume");
				liquid_semenDTO.density = rs.getInt("density");
				liquid_semenDTO.progressiveMotility = rs.getInt("progressive_motility");
				liquid_semenDTO.colorType = rs.getInt("color_type");
				liquid_semenDTO.collectionDistributionDate = rs.getLong("collection_distribution_date");
				liquid_semenDTO.description = rs.getString("description");
				liquid_semenDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = liquid_semenDTO.iD;
				while(i < liquid_semenDTOList.size() && liquid_semenDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				liquid_semenDTOList.add(i,  liquid_semenDTO);

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
		return liquid_semenDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	