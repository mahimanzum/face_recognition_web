package bull_transfer;

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


public class Bull_transferDAO  implements NavigationService{
	
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
		ps.setString(2,"bull_transfer");
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
		ps.setString(2,"bull_transfer");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Bull_transferDTO bull_transferDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = bull_transferDTO.iD;
		// userDTO.userName = bull_transferDTO.email;
		// userDTO.fullName = bull_transferDTO.name;
		// userDTO.password = bull_transferDTO.password;
		// userDTO.phoneNo = bull_transferDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = bull_transferDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Bull_transferDTO bull_transferDTO, UserDTO userDTO)
	{
		// userDTO.ID = bull_transferDTO.iD;
		// userDTO.fullName = bull_transferDTO.name;
		// userDTO.phoneNo = bull_transferDTO.phone;
		// userDTO.mailAddress = bull_transferDTO.email;

		return userDTO;
	}
		
		
	
	public void addBull_transfer(Bull_transferDTO bull_transferDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			bull_transferDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Bull_transfer");

			String sql = "INSERT INTO bull_transfer";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "date_of_transfer";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "from_centre_type";
			sql += ", ";
			sql += "to_centre_type";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "exit_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "approval_status_type";
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
			// userDAO.addUser(getUserDTO(bull_transferDTO));
			// bull_transferDTO.iD = userDAO.getUserDTOByUsername(bull_transferDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + bull_transferDTO.iD + " in index " + index);
			ps.setObject(index++,bull_transferDTO.iD);
			//System.out.println("Setting object" + bull_transferDTO.dateOfTransfer + " in index " + index);
			ps.setObject(index++,bull_transferDTO.dateOfTransfer);
			//System.out.println("Setting object" + bull_transferDTO.bullType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.bullType);
			//System.out.println("Setting object" + bull_transferDTO.fromCentreType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.fromCentreType);
			//System.out.println("Setting object" + bull_transferDTO.toCentreType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.toCentreType);
			//System.out.println("Setting object" + bull_transferDTO.entryDate + " in index " + index);
			ps.setObject(index++,bull_transferDTO.entryDate);
			//System.out.println("Setting object" + bull_transferDTO.exitDate + " in index " + index);
			ps.setObject(index++,bull_transferDTO.exitDate);
			//System.out.println("Setting object" + bull_transferDTO.description + " in index " + index);
			ps.setObject(index++,bull_transferDTO.description);
			//System.out.println("Setting object" + bull_transferDTO.approvalStatusType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.approvalStatusType);
			//System.out.println("Setting object" + bull_transferDTO.isDeleted + " in index " + index);
			ps.setObject(index++,bull_transferDTO.isDeleted);
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
		//Bull_transferRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Bull_transferDTO getBull_transferDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Bull_transferDTO bull_transferDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "date_of_transfer";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "from_centre_type";
			sql += ", ";
			sql += "to_centre_type";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "exit_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "approval_status_type";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull_transfer";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				bull_transferDTO = new Bull_transferDTO();

				bull_transferDTO.iD = rs.getLong("ID");
				bull_transferDTO.dateOfTransfer = rs.getLong("date_of_transfer");
				bull_transferDTO.bullType = rs.getInt("bull_type");
				bull_transferDTO.fromCentreType = rs.getInt("from_centre_type");
				bull_transferDTO.toCentreType = rs.getInt("to_centre_type");
				bull_transferDTO.entryDate = rs.getLong("entry_date");
				bull_transferDTO.exitDate = rs.getLong("exit_date");
				bull_transferDTO.description = rs.getString("description");
				bull_transferDTO.approvalStatusType = rs.getInt("approval_status_type");
				bull_transferDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return bull_transferDTO;
	}
	
	public void updateBull_transfer(Bull_transferDTO bull_transferDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE bull_transfer";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "date_of_transfer=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "from_centre_type=?";
			sql += ", ";
			sql += "to_centre_type=?";
			sql += ", ";
			sql += "entry_date=?";
			sql += ", ";
			sql += "exit_date=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "approval_status_type=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + bull_transferDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + bull_transferDTO.iD + " in index " + index);
			ps.setObject(index++,bull_transferDTO.iD);
			//System.out.println("Setting object" + bull_transferDTO.dateOfTransfer + " in index " + index);
			ps.setObject(index++,bull_transferDTO.dateOfTransfer);
			//System.out.println("Setting object" + bull_transferDTO.bullType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.bullType);
			//System.out.println("Setting object" + bull_transferDTO.fromCentreType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.fromCentreType);
			//System.out.println("Setting object" + bull_transferDTO.toCentreType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.toCentreType);
			//System.out.println("Setting object" + bull_transferDTO.entryDate + " in index " + index);
			ps.setObject(index++,bull_transferDTO.entryDate);
			//System.out.println("Setting object" + bull_transferDTO.exitDate + " in index " + index);
			ps.setObject(index++,bull_transferDTO.exitDate);
			//System.out.println("Setting object" + bull_transferDTO.description + " in index " + index);
			ps.setObject(index++,bull_transferDTO.description);
			//System.out.println("Setting object" + bull_transferDTO.approvalStatusType + " in index " + index);
			ps.setObject(index++,bull_transferDTO.approvalStatusType);
			//System.out.println("Setting object" + bull_transferDTO.isDeleted + " in index " + index);
			ps.setObject(index++,bull_transferDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(bull_transferDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(bull_transferDTO, userDTO);
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
		//Bull_transferRepository.getInstance().reload(false);
	}
	
	public void deleteBull_transferByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{

			
			String sql = "delete from bull_transfer where ID = " + ID;
			
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
	private List<Bull_transferDTO> getBull_transferDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Bull_transferDTO bull_transferDTO = null;
		List<Bull_transferDTO> bull_transferDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "date_of_transfer";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "from_centre_type";
			sql += ", ";
			sql += "to_centre_type";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "exit_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "approval_status_type";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull_transfer";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				bull_transferDTO = new Bull_transferDTO();
				bull_transferDTO.iD = rs.getLong("ID");
				bull_transferDTO.dateOfTransfer = rs.getLong("date_of_transfer");
				bull_transferDTO.bullType = rs.getInt("bull_type");
				bull_transferDTO.fromCentreType = rs.getInt("from_centre_type");
				bull_transferDTO.toCentreType = rs.getInt("to_centre_type");
				bull_transferDTO.entryDate = rs.getLong("entry_date");
				bull_transferDTO.exitDate = rs.getLong("exit_date");
				bull_transferDTO.description = rs.getString("description");
				bull_transferDTO.approvalStatusType = rs.getInt("approval_status_type");
				bull_transferDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = bull_transferDTO.iD;
				while(i < bull_transferDTOList.size() && bull_transferDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bull_transferDTOList.add(i,  bull_transferDTO);
				//bull_transferDTOList.add(bull_transferDTO);
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
		return bull_transferDTOList;
	}
	
	public List<Bull_transferDTO> getBull_transferDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getBull_transferDTOByColumn(filter);
	}
	
	public List<Bull_transferDTO> getBull_transferDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getBull_transferDTOByColumn(filter);
	}
	
	public List<Bull_transferDTO> getBull_transferDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getBull_transferDTOByColumn(filter);
	}
		
	
	
	public List<Bull_transferDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Bull_transferDTO bull_transferDTO = null;
		List<Bull_transferDTO> bull_transferDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return bull_transferDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "date_of_transfer";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "from_centre_type";
			sql += ", ";
			sql += "to_centre_type";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "exit_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "approval_status_type";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM bull_transfer";
            
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
				bull_transferDTO = new Bull_transferDTO();
				bull_transferDTO.iD = rs.getLong("ID");
				bull_transferDTO.dateOfTransfer = rs.getLong("date_of_transfer");
				bull_transferDTO.bullType = rs.getInt("bull_type");
				bull_transferDTO.fromCentreType = rs.getInt("from_centre_type");
				bull_transferDTO.toCentreType = rs.getInt("to_centre_type");
				bull_transferDTO.entryDate = rs.getLong("entry_date");
				bull_transferDTO.exitDate = rs.getLong("exit_date");
				bull_transferDTO.description = rs.getString("description");
				bull_transferDTO.approvalStatusType = rs.getInt("approval_status_type");
				bull_transferDTO.isDeleted = rs.getBoolean("isDeleted");
				
				bull_transferDTOList.add( bull_transferDTO);

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
		return bull_transferDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM bull_transfer";

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
	public List<Bull_transferDTO> getAllBull_transfer (boolean isFirstReload)
    {
		List<Bull_transferDTO> bull_transferDTOList = new ArrayList<>();

		String sql = "SELECT * FROM bull_transfer";
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
				Bull_transferDTO bull_transferDTO = new Bull_transferDTO();
				bull_transferDTO.iD = rs.getLong("ID");
				bull_transferDTO.dateOfTransfer = rs.getLong("date_of_transfer");
				bull_transferDTO.bullType = rs.getInt("bull_type");
				bull_transferDTO.fromCentreType = rs.getInt("from_centre_type");
				bull_transferDTO.toCentreType = rs.getInt("to_centre_type");
				bull_transferDTO.entryDate = rs.getLong("entry_date");
				bull_transferDTO.exitDate = rs.getLong("exit_date");
				bull_transferDTO.description = rs.getString("description");
				bull_transferDTO.approvalStatusType = rs.getInt("approval_status_type");
				bull_transferDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = bull_transferDTO.iD;
				while(i < bull_transferDTOList.size() && bull_transferDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bull_transferDTOList.add(i,  bull_transferDTO);
				//bull_transferDTOList.add(bull_transferDTO);
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

		return bull_transferDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM bull_transfer";
			
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

			String sql = "SELECT distinct bull_transfer.ID as ID FROM bull_transfer ";
			sql += " join bull on bull_transfer.bull_type = bull.ID ";
			sql += " join centre on bull_transfer.from_centre_type = centre.ID ";
			sql += " join centre on bull_transfer.to_centre_type = centre.ID ";
			sql += " join approval_status on bull_transfer.approval_status_type = approval_status.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Bull_transferMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Bull_transferMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Bull_transferMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Bull_transferMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "bull_transfer." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "bull_transfer." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " bull_transfer.isDeleted = false";				
			
			
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
		Bull_transferDTO bull_transferDTO = null;
		List<Bull_transferDTO> bull_transferDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return bull_transferDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Bull_transferMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				bull_transferDTO = new Bull_transferDTO();
				bull_transferDTO.iD = rs.getLong("ID");
				bull_transferDTO.dateOfTransfer = rs.getLong("date_of_transfer");
				bull_transferDTO.bullType = rs.getInt("bull_type");
				bull_transferDTO.fromCentreType = rs.getInt("from_centre_type");
				bull_transferDTO.toCentreType = rs.getInt("to_centre_type");
				bull_transferDTO.entryDate = rs.getLong("entry_date");
				bull_transferDTO.exitDate = rs.getLong("exit_date");
				bull_transferDTO.description = rs.getString("description");
				bull_transferDTO.approvalStatusType = rs.getInt("approval_status_type");
				bull_transferDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = bull_transferDTO.iD;
				while(i < bull_transferDTOList.size() && bull_transferDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bull_transferDTOList.add(i,  bull_transferDTO);

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
		return bull_transferDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	