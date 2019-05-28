package artificial_insemenation_record;

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


public class Artificial_insemenation_recordDAO  implements NavigationService{
	
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
		ps.setString(2,"artificial_insemenation_record");
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
		ps.setString(2,"artificial_insemenation_record");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Artificial_insemenation_recordDTO artificial_insemenation_recordDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = artificial_insemenation_recordDTO.iD;
		// userDTO.userName = artificial_insemenation_recordDTO.email;
		// userDTO.fullName = artificial_insemenation_recordDTO.name;
		// userDTO.password = artificial_insemenation_recordDTO.password;
		// userDTO.phoneNo = artificial_insemenation_recordDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = artificial_insemenation_recordDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Artificial_insemenation_recordDTO artificial_insemenation_recordDTO, UserDTO userDTO)
	{
		// userDTO.ID = artificial_insemenation_recordDTO.iD;
		// userDTO.fullName = artificial_insemenation_recordDTO.name;
		// userDTO.phoneNo = artificial_insemenation_recordDTO.phone;
		// userDTO.mailAddress = artificial_insemenation_recordDTO.email;

		return userDTO;
	}
		
		
	
	public void addArtificial_insemenation_record(Artificial_insemenation_recordDTO artificial_insemenation_recordDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			artificial_insemenation_recordDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Artificial_insemenation_record");

			String sql = "INSERT INTO artificial_insemenation_record";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_AI";
			sql += ", ";
			sql += "entry_date";
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
			// userDAO.addUser(getUserDTO(artificial_insemenation_recordDTO));
			// artificial_insemenation_recordDTO.iD = userDAO.getUserDTOByUsername(artificial_insemenation_recordDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + artificial_insemenation_recordDTO.iD + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.iD);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.centreType + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.centreType);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.bullType + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.bullType);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.noOfAI + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.noOfAI);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.entryDate + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.entryDate);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.description + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.description);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.isDeleted + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.isDeleted);
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
		//Artificial_insemenation_recordRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Artificial_insemenation_recordDTO getArtificial_insemenation_recordDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_AI";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM artificial_insemenation_record";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();

				artificial_insemenation_recordDTO.iD = rs.getLong("ID");
				artificial_insemenation_recordDTO.centreType = rs.getInt("centre_type");
				artificial_insemenation_recordDTO.bullType = rs.getInt("bull_type");
				artificial_insemenation_recordDTO.noOfAI = rs.getInt("no_of_AI");
				artificial_insemenation_recordDTO.entryDate = rs.getLong("entry_date");
				artificial_insemenation_recordDTO.description = rs.getString("description");
				artificial_insemenation_recordDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return artificial_insemenation_recordDTO;
	}
	
	public void updateArtificial_insemenation_record(Artificial_insemenation_recordDTO artificial_insemenation_recordDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE artificial_insemenation_record";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "no_of_AI=?";
			sql += ", ";
			sql += "entry_date=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + artificial_insemenation_recordDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.iD + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.iD);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.centreType + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.centreType);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.bullType + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.bullType);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.noOfAI + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.noOfAI);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.entryDate + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.entryDate);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.description + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.description);
			//System.out.println("Setting object" + artificial_insemenation_recordDTO.isDeleted + " in index " + index);
			ps.setObject(index++,artificial_insemenation_recordDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(artificial_insemenation_recordDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(artificial_insemenation_recordDTO, userDTO);
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
		//Artificial_insemenation_recordRepository.getInstance().reload(false);
	}
	
	public void deleteArtificial_insemenation_recordByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			
			String sql = "delete from artificial_insemenation_record where ID = " + ID;
			
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
	private List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = null;
		List<Artificial_insemenation_recordDTO> artificial_insemenation_recordDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_AI";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM artificial_insemenation_record";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
				artificial_insemenation_recordDTO.iD = rs.getLong("ID");
				artificial_insemenation_recordDTO.centreType = rs.getInt("centre_type");
				artificial_insemenation_recordDTO.bullType = rs.getInt("bull_type");
				artificial_insemenation_recordDTO.noOfAI = rs.getInt("no_of_AI");
				artificial_insemenation_recordDTO.entryDate = rs.getLong("entry_date");
				artificial_insemenation_recordDTO.description = rs.getString("description");
				artificial_insemenation_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = artificial_insemenation_recordDTO.iD;
				while(i < artificial_insemenation_recordDTOList.size() && artificial_insemenation_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				artificial_insemenation_recordDTOList.add(i,  artificial_insemenation_recordDTO);
				//artificial_insemenation_recordDTOList.add(artificial_insemenation_recordDTO);
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
		return artificial_insemenation_recordDTOList;
	}
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getArtificial_insemenation_recordDTOByColumn(filter);
	}
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getArtificial_insemenation_recordDTOByColumn(filter);
	}
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getArtificial_insemenation_recordDTOByColumn(filter);
	}
		
	
	
	public List<Artificial_insemenation_recordDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = null;
		List<Artificial_insemenation_recordDTO> artificial_insemenation_recordDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return artificial_insemenation_recordDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "no_of_AI";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM artificial_insemenation_record";
            
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
				artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
				artificial_insemenation_recordDTO.iD = rs.getLong("ID");
				artificial_insemenation_recordDTO.centreType = rs.getInt("centre_type");
				artificial_insemenation_recordDTO.bullType = rs.getInt("bull_type");
				artificial_insemenation_recordDTO.noOfAI = rs.getInt("no_of_AI");
				artificial_insemenation_recordDTO.entryDate = rs.getLong("entry_date");
				artificial_insemenation_recordDTO.description = rs.getString("description");
				artificial_insemenation_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				
				artificial_insemenation_recordDTOList.add(artificial_insemenation_recordDTO);

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
		return artificial_insemenation_recordDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM artificial_insemenation_record";

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
	public List<Artificial_insemenation_recordDTO> getAllArtificial_insemenation_record (boolean isFirstReload)
    {
		List<Artificial_insemenation_recordDTO> artificial_insemenation_recordDTOList = new ArrayList<>();

		String sql = "SELECT * FROM artificial_insemenation_record";
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
				Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
				artificial_insemenation_recordDTO.iD = rs.getLong("ID");
				artificial_insemenation_recordDTO.centreType = rs.getInt("centre_type");
				artificial_insemenation_recordDTO.bullType = rs.getInt("bull_type");
				artificial_insemenation_recordDTO.noOfAI = rs.getInt("no_of_AI");
				artificial_insemenation_recordDTO.entryDate = rs.getLong("entry_date");
				artificial_insemenation_recordDTO.description = rs.getString("description");
				artificial_insemenation_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = artificial_insemenation_recordDTO.iD;
				while(i < artificial_insemenation_recordDTOList.size() && artificial_insemenation_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				artificial_insemenation_recordDTOList.add(i,  artificial_insemenation_recordDTO);
				//artificial_insemenation_recordDTOList.add(artificial_insemenation_recordDTO);
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

		return artificial_insemenation_recordDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM artificial_insemenation_record";
			
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

			String sql = "SELECT distinct artificial_insemenation_record.ID as ID FROM artificial_insemenation_record ";
			sql += " join centre on artificial_insemenation_record.centre_type = centre.ID ";
			sql += " join bull on artificial_insemenation_record.bull_type = bull.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Artificial_insemenation_recordMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Artificial_insemenation_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Artificial_insemenation_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Artificial_insemenation_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "artificial_insemenation_record." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "artificial_insemenation_record." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " artificial_insemenation_record.isDeleted = false";				
			
			
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
		Artificial_insemenation_recordDTO artificial_insemenation_recordDTO = null;
		List<Artificial_insemenation_recordDTO> artificial_insemenation_recordDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return artificial_insemenation_recordDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Artificial_insemenation_recordMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				artificial_insemenation_recordDTO = new Artificial_insemenation_recordDTO();
				artificial_insemenation_recordDTO.iD = rs.getLong("ID");
				artificial_insemenation_recordDTO.centreType = rs.getInt("centre_type");
				artificial_insemenation_recordDTO.bullType = rs.getInt("bull_type");
				artificial_insemenation_recordDTO.noOfAI = rs.getInt("no_of_AI");
				artificial_insemenation_recordDTO.entryDate = rs.getLong("entry_date");
				artificial_insemenation_recordDTO.description = rs.getString("description");
				artificial_insemenation_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = artificial_insemenation_recordDTO.iD;
				while(i < artificial_insemenation_recordDTOList.size() && artificial_insemenation_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				artificial_insemenation_recordDTOList.add(i,  artificial_insemenation_recordDTO);

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
		return artificial_insemenation_recordDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	