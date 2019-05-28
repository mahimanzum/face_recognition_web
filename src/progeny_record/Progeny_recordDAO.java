package progeny_record;

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


public class Progeny_recordDAO  implements NavigationService{
	
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
		ps.setString(2,"progeny_record");
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
		ps.setString(2,"progeny_record");
		ps.execute();
		ps.close();
	}
	
	public UserDTO getUserDTO(Progeny_recordDTO progeny_recordDTO)
	{
		UserDTO userDTO = new UserDTO();
		// userDTO.ID = progeny_recordDTO.iD;
		// userDTO.userName = progeny_recordDTO.email;
		// userDTO.fullName = progeny_recordDTO.name;
		// userDTO.password = progeny_recordDTO.password;
		// userDTO.phoneNo = progeny_recordDTO.phone;
		// userDTO.roleID = 6003;
		// userDTO.mailAddress = progeny_recordDTO.email;
		// userDTO.userType = 4;

		return userDTO;
	}
	
	public UserDTO fillUserDTO(Progeny_recordDTO progeny_recordDTO, UserDTO userDTO)
	{
		// userDTO.ID = progeny_recordDTO.iD;
		// userDTO.fullName = progeny_recordDTO.name;
		// userDTO.phoneNo = progeny_recordDTO.phone;
		// userDTO.mailAddress = progeny_recordDTO.email;

		return userDTO;
	}
		
		
	
	public void addProgeny_record(Progeny_recordDTO progeny_recordDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			progeny_recordDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Progeny_record");

			String sql = "INSERT INTO progeny_record";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "number_of_male_calfs";
			sql += ", ";
			sql += "number_of_female_calfs";
			sql += ", ";
			sql += "date_of_entry";
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
			// userDAO.addUser(getUserDTO(progeny_recordDTO));
			// progeny_recordDTO.iD = userDAO.getUserDTOByUsername(progeny_recordDTO.email).ID;
			

			int index = 1;

			//System.out.println("Setting object" + progeny_recordDTO.iD + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.iD);
			//System.out.println("Setting object" + progeny_recordDTO.centreType + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.centreType);
			//System.out.println("Setting object" + progeny_recordDTO.bullType + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.bullType);
			//System.out.println("Setting object" + progeny_recordDTO.numberOfMaleCalfs + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.numberOfMaleCalfs);
			//System.out.println("Setting object" + progeny_recordDTO.numberOfFemaleCalfs + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.numberOfFemaleCalfs);
			//System.out.println("Setting object" + progeny_recordDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.dateOfEntry);
			//System.out.println("Setting object" + progeny_recordDTO.description + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.description);
			//System.out.println("Setting object" + progeny_recordDTO.isDeleted + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.isDeleted);
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
		//Progeny_recordRepository.getInstance().reload(false);		
	}
	
	//need another getter for repository
	public Progeny_recordDTO getProgeny_recordDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Progeny_recordDTO progeny_recordDTO = null;
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "number_of_male_calfs";
			sql += ", ";
			sql += "number_of_female_calfs";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM progeny_record";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				progeny_recordDTO = new Progeny_recordDTO();

				progeny_recordDTO.iD = rs.getLong("ID");
				progeny_recordDTO.centreType = rs.getInt("centre_type");
				progeny_recordDTO.bullType = rs.getInt("bull_type");
				progeny_recordDTO.numberOfMaleCalfs = rs.getInt("number_of_male_calfs");
				progeny_recordDTO.numberOfFemaleCalfs = rs.getInt("number_of_female_calfs");
				progeny_recordDTO.dateOfEntry = rs.getLong("date_of_entry");
				progeny_recordDTO.description = rs.getString("description");
				progeny_recordDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return progeny_recordDTO;
	}
	
	public void updateProgeny_record(Progeny_recordDTO progeny_recordDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE progeny_record";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "number_of_male_calfs=?";
			sql += ", ";
			sql += "number_of_female_calfs=?";
			sql += ", ";
			sql += "date_of_entry=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + progeny_recordDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			//System.out.println("Setting object" + progeny_recordDTO.iD + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.iD);
			//System.out.println("Setting object" + progeny_recordDTO.centreType + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.centreType);
			//System.out.println("Setting object" + progeny_recordDTO.bullType + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.bullType);
			//System.out.println("Setting object" + progeny_recordDTO.numberOfMaleCalfs + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.numberOfMaleCalfs);
			//System.out.println("Setting object" + progeny_recordDTO.numberOfFemaleCalfs + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.numberOfFemaleCalfs);
			//System.out.println("Setting object" + progeny_recordDTO.dateOfEntry + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.dateOfEntry);
			//System.out.println("Setting object" + progeny_recordDTO.description + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.description);
			//System.out.println("Setting object" + progeny_recordDTO.isDeleted + " in index " + index);
			ps.setObject(index++,progeny_recordDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			

			// UserDAO userDAO = new UserDAO();
			
			// UserDTO userDTO = UserRepository.getUserDTOByUserID(progeny_recordDTO.iD);
			// if(userDTO == null)
			// {
				// System.out.println("null userdto");
			// }
			// else
			// {
				// userDTO = fillUserDTO(progeny_recordDTO, userDTO);
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
		//Progeny_recordRepository.getInstance().reload(false);
	}
	
	public void deleteProgeny_recordByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "delete from projeny_record WHERE ID = " + ID;
			
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
	private List<Progeny_recordDTO> getProgeny_recordDTOByColumn(String filter){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Progeny_recordDTO progeny_recordDTO = null;
		List<Progeny_recordDTO> progeny_recordDTOList = new ArrayList<>();
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "number_of_male_calfs";
			sql += ", ";
			sql += "number_of_female_calfs";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM progeny_record";
			
			
			sql += " WHERE " +  filter;
			
			printSql(sql);
		
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(sql);
			


			while(rs.next()){
				progeny_recordDTO = new Progeny_recordDTO();
				progeny_recordDTO.iD = rs.getLong("ID");
				progeny_recordDTO.centreType = rs.getInt("centre_type");
				progeny_recordDTO.bullType = rs.getInt("bull_type");
				progeny_recordDTO.numberOfMaleCalfs = rs.getInt("number_of_male_calfs");
				progeny_recordDTO.numberOfFemaleCalfs = rs.getInt("number_of_female_calfs");
				progeny_recordDTO.dateOfEntry = rs.getLong("date_of_entry");
				progeny_recordDTO.description = rs.getString("description");
				progeny_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = progeny_recordDTO.iD;
				while(i < progeny_recordDTOList.size() && progeny_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				progeny_recordDTOList.add(i,  progeny_recordDTO);
				//progeny_recordDTOList.add(progeny_recordDTO);
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
		return progeny_recordDTOList;
	}
	
	public List<Progeny_recordDTO> getProgeny_recordDTOByColumn(String column, String value){
		String filter = column + " = '" + value + "'";
		return getProgeny_recordDTOByColumn(filter);
	}
	
	public List<Progeny_recordDTO> getProgeny_recordDTOByColumn(String column, int value){
		String filter = column + " = " + value;
		return getProgeny_recordDTOByColumn(filter);
	}
	
	public List<Progeny_recordDTO> getProgeny_recordDTOByColumn(String column, double valueBase, double valueDelta){
		String filter = column + " BETWEEN " + (valueBase - valueDelta) + " AND " + (valueBase + valueDelta);
		return getProgeny_recordDTOByColumn(filter);
	}
		
	
	
	public List<Progeny_recordDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Progeny_recordDTO progeny_recordDTO = null;
		List<Progeny_recordDTO> progeny_recordDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return progeny_recordDTOList;
		}
		try{
			
			String sql = "SELECT ";
			sql += "ID";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "number_of_male_calfs";
			sql += ", ";
			sql += "number_of_female_calfs";
			sql += ", ";
			sql += "date_of_entry";
			sql += ", ";
			sql += "description";
			sql += ", ";
			sql += "isDeleted";
			sql += " FROM progeny_record";
            
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
				progeny_recordDTO = new Progeny_recordDTO();
				progeny_recordDTO.iD = rs.getLong("ID");
				progeny_recordDTO.centreType = rs.getInt("centre_type");
				progeny_recordDTO.bullType = rs.getInt("bull_type");
				progeny_recordDTO.numberOfMaleCalfs = rs.getInt("number_of_male_calfs");
				progeny_recordDTO.numberOfFemaleCalfs = rs.getInt("number_of_female_calfs");
				progeny_recordDTO.dateOfEntry = rs.getLong("date_of_entry");
				progeny_recordDTO.description = rs.getString("description");
				progeny_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + progeny_recordDTO);
				
				progeny_recordDTOList.add( progeny_recordDTO);

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
		return progeny_recordDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM progeny_record";

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
	public List<Progeny_recordDTO> getAllProgeny_record (boolean isFirstReload)
    {
		List<Progeny_recordDTO> progeny_recordDTOList = new ArrayList<>();

		String sql = "SELECT * FROM progeny_record";
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
				Progeny_recordDTO progeny_recordDTO = new Progeny_recordDTO();
				progeny_recordDTO.iD = rs.getLong("ID");
				progeny_recordDTO.centreType = rs.getInt("centre_type");
				progeny_recordDTO.bullType = rs.getInt("bull_type");
				progeny_recordDTO.numberOfMaleCalfs = rs.getInt("number_of_male_calfs");
				progeny_recordDTO.numberOfFemaleCalfs = rs.getInt("number_of_female_calfs");
				progeny_recordDTO.dateOfEntry = rs.getLong("date_of_entry");
				progeny_recordDTO.description = rs.getString("description");
				progeny_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = progeny_recordDTO.iD;
				while(i < progeny_recordDTOList.size() && progeny_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				progeny_recordDTOList.add(i,  progeny_recordDTO);
				//progeny_recordDTOList.add(progeny_recordDTO);
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

		return progeny_recordDTOList;
    }
	
	//normal table, transaction table, int, float
	private List<Long> getIDsWithSearchCriteria(String filter){
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{

			String sql = "SELECT ID FROM progeny_record";
			
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

			String sql = "SELECT distinct progeny_record.ID as ID FROM progeny_record ";
			sql += " join centre on progeny_record.centre_type = centre.ID ";
			sql += " join bull on progeny_record.bull_type = bull.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Progeny_recordMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Progeny_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Progeny_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Progeny_recordMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "progeny_record." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "progeny_record." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " progeny_record.isDeleted = false";				
			
			
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
		Progeny_recordDTO progeny_recordDTO = null;
		List<Progeny_recordDTO> progeny_recordDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return progeny_recordDTOList;
		}

		try{

			String sql = "SELECT ";
			Iterator it = Progeny_recordMAPS.GetInstance().java_SQL_map.entrySet().iterator();
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
				progeny_recordDTO = new Progeny_recordDTO();
				progeny_recordDTO.iD = rs.getLong("ID");
				progeny_recordDTO.centreType = rs.getInt("centre_type");
				progeny_recordDTO.bullType = rs.getInt("bull_type");
				progeny_recordDTO.numberOfMaleCalfs = rs.getInt("number_of_male_calfs");
				progeny_recordDTO.numberOfFemaleCalfs = rs.getInt("number_of_female_calfs");
				progeny_recordDTO.dateOfEntry = rs.getLong("date_of_entry");
				progeny_recordDTO.description = rs.getString("description");
				progeny_recordDTO.isDeleted = rs.getBoolean("isDeleted");
				i = 0;
				long primaryKey = progeny_recordDTO.iD;
				while(i < progeny_recordDTOList.size() && progeny_recordDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				progeny_recordDTOList.add(i,  progeny_recordDTO);

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
		return progeny_recordDTOList;
	}
	
	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
		return null;
	}
		
}
	