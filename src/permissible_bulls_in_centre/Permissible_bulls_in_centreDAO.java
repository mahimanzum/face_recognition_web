package permissible_bulls_in_centre;

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


public class Permissible_bulls_in_centreDAO  implements NavigationService{
	
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
		ps.setString(2,"permissible_bulls_in_centre");
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
		ps.setString(2,"permissible_bulls_in_centre");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = permissible_bulls_in_centreDTO.iD;
		// userDTO.userName = permissible_bulls_in_centreDTO.email;
		// userDTO.fullName = permissible_bulls_in_centreDTO.name;
		// userDTO.password = permissible_bulls_in_centreDTO.password;
		// userDTO.phoneNo = permissible_bulls_in_centreDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = permissible_bulls_in_centreDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO, UserDTO userDTO)
	{
		// userDTO.ID = permissible_bulls_in_centreDTO.iD;
		// userDTO.fullName = permissible_bulls_in_centreDTO.name;
		// userDTO.phoneNo = permissible_bulls_in_centreDTO.phone;
		// userDTO.mailAddress = permissible_bulls_in_centreDTO.email;

		return userDTO;
	}
		
		
	
	public void addPermissible_bulls_in_centre(Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			permissible_bulls_in_centreDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Permissible_bulls_in_centre");

			String sql = "INSERT INTO permissible_bulls_in_centre";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "date_of_expiration";
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
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			// UserDAO userDAO = new UserDAO();
			// userDAO.addUser(getUserDTO(permissible_bulls_in_centreDTO));
			// permissible_bulls_in_centreDTO.iD = userDAO.getUserDTOByUsername(permissible_bulls_in_centreDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.iD + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.iD);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.bullType + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.bullType);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.centreType + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.centreType);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.dateOfEntry);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.dateOfExpiration + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.dateOfExpiration);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.description + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.description);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.isDeleted + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.isDeleted);
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
		//Permissible_bulls_in_centreRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Permissible_bulls_in_centreDTO getPermissible_bulls_in_centreDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "date_of_expiration";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM permissible_bulls_in_centre";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();

				permissible_bulls_in_centreDTO.iD = rs.getLong("ID");
				permissible_bulls_in_centreDTO.bullType = rs.getInt("bull_type");
				permissible_bulls_in_centreDTO.centreType = rs.getInt("centre_type");
				permissible_bulls_in_centreDTO.dateOfEntry = rs.getLong("date_of_entry");
				permissible_bulls_in_centreDTO.dateOfExpiration = rs.getLong("date_of_expiration");
				permissible_bulls_in_centreDTO.description = rs.getString("description");
				permissible_bulls_in_centreDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return permissible_bulls_in_centreDTO;
	}
	
	public void updatePermissible_bulls_in_centre(Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE permissible_bulls_in_centre";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "date_of_entry=?";
			sql += ", ";
			sql += "date_of_expiration=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + permissible_bulls_in_centreDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.iD + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.iD);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.bullType + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.bullType);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.centreType + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.centreType);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.dateOfEntry);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.dateOfExpiration + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.dateOfExpiration);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.description + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.description);
			//System.out.println("Setting object" + permissible_bulls_in_centreDTO.isDeleted + " in index " + index);
			ps.setObject(index++,permissible_bulls_in_centreDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(permissible_bulls_in_centreDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(permissible_bulls_in_centreDTO, userDTO);
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
		//Permissible_bulls_in_centreRepository.getInstance().reload(false);
	}
	
	public void deletePermissible_bulls_in_centreByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			
			String sql = "delete from permissible_bulls_in_centre where ID = " + ID;
			
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
	private List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = null;
		List<Permissible_bulls_in_centreDTO> permissible_bulls_in_centreDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "date_of_expiration";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM permissible_bulls_in_centre";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
				permissible_bulls_in_centreDTO.iD = rs.getLong("ID");
				permissible_bulls_in_centreDTO.bullType = rs.getInt("bull_type");
				permissible_bulls_in_centreDTO.centreType = rs.getInt("centre_type");
				permissible_bulls_in_centreDTO.dateOfEntry = rs.getLong("date_of_entry");
				permissible_bulls_in_centreDTO.dateOfExpiration = rs.getLong("date_of_expiration");
				permissible_bulls_in_centreDTO.description = rs.getString("description");
				permissible_bulls_in_centreDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = permissible_bulls_in_centreDTO.iD;
				while(i < permissible_bulls_in_centreDTOList.size() && permissible_bulls_in_centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				permissible_bulls_in_centreDTOList.add(i,  permissible_bulls_in_centreDTO);
				//permissible_bulls_in_centreDTOList.add(permissible_bulls_in_centreDTO);
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
		return permissible_bulls_in_centreDTOList;
	}
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getPermissible_bulls_in_centreDTOByColumn(filter);
	}
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getPermissible_bulls_in_centreDTOByColumn(filter);
	}
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getPermissible_bulls_in_centreDTOByColumn(filter);
	}
		
	
	
	public List<Permissible_bulls_in_centreDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = null;
		List<Permissible_bulls_in_centreDTO> permissible_bulls_in_centreDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return permissible_bulls_in_centreDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "date_of_expiration";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM permissible_bulls_in_centre";
            
            sql += " WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=") order by id desc";
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
				permissible_bulls_in_centreDTO.iD = rs.getLong("ID");
				permissible_bulls_in_centreDTO.bullType = rs.getInt("bull_type");
				permissible_bulls_in_centreDTO.centreType = rs.getInt("centre_type");
				permissible_bulls_in_centreDTO.dateOfEntry = rs.getLong("date_of_entry");
				permissible_bulls_in_centreDTO.dateOfExpiration = rs.getLong("date_of_expiration");
				permissible_bulls_in_centreDTO.description = rs.getString("description");
				permissible_bulls_in_centreDTO.isDeleted = rs.getBoolean("isDeleted");
			
				permissible_bulls_in_centreDTOList.add( permissible_bulls_in_centreDTO);

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
		return permissible_bulls_in_centreDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM permissible_bulls_in_centre";

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
	public List<Permissible_bulls_in_centreDTO> getAllPermissible_bulls_in_centre (boolean isFirstReload)
    {
		List<Permissible_bulls_in_centreDTO> permissible_bulls_in_centreDTOList = new ArrayList<>();

		String sql = "SELECT * FROM permissible_bulls_in_centre";
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
				Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
				permissible_bulls_in_centreDTO.iD = rs.getLong("ID");
				permissible_bulls_in_centreDTO.bullType = rs.getInt("bull_type");
				permissible_bulls_in_centreDTO.centreType = rs.getInt("centre_type");
				permissible_bulls_in_centreDTO.dateOfEntry = rs.getLong("date_of_entry");
				permissible_bulls_in_centreDTO.dateOfExpiration = rs.getLong("date_of_expiration");
				permissible_bulls_in_centreDTO.description = rs.getString("description");
				permissible_bulls_in_centreDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = permissible_bulls_in_centreDTO.iD;
				while(i < permissible_bulls_in_centreDTOList.size() && permissible_bulls_in_centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				permissible_bulls_in_centreDTOList.add(i,  permissible_bulls_in_centreDTO);
				//permissible_bulls_in_centreDTOList.add(permissible_bulls_in_centreDTO);
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

		return permissible_bulls_in_centreDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM permissible_bulls_in_centre";
			
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

			String sql = "SELECT distinct permissible_bulls_in_centre.ID as ID FROM permissible_bulls_in_centre ";
			sql += " join bull on permissible_bulls_in_centre.bull_type = bull.ID ";
			sql += " join centre on permissible_bulls_in_centre.centre_type = centre.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Permissible_bulls_in_centreMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Permissible_bulls_in_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Permissible_bulls_in_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Permissible_bulls_in_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "permissible_bulls_in_centre." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "permissible_bulls_in_centre." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " permissible_bulls_in_centre.isDeleted = false";				
			
			
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
		Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO = null;
		List<Permissible_bulls_in_centreDTO> permissible_bulls_in_centreDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return permissible_bulls_in_centreDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Permissible_bulls_in_centreMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				permissible_bulls_in_centreDTO = new Permissible_bulls_in_centreDTO();
				permissible_bulls_in_centreDTO.iD = rs.getLong("ID");
				permissible_bulls_in_centreDTO.bullType = rs.getInt("bull_type");
				permissible_bulls_in_centreDTO.centreType = rs.getInt("centre_type");
				permissible_bulls_in_centreDTO.dateOfEntry = rs.getLong("date_of_entry");
				permissible_bulls_in_centreDTO.dateOfExpiration = rs.getLong("date_of_expiration");
				permissible_bulls_in_centreDTO.description = rs.getString("description");
				permissible_bulls_in_centreDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = permissible_bulls_in_centreDTO.iD;
				while(i < permissible_bulls_in_centreDTOList.size() && permissible_bulls_in_centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				permissible_bulls_in_centreDTOList.add(i,  permissible_bulls_in_centreDTO);

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
		return permissible_bulls_in_centreDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	