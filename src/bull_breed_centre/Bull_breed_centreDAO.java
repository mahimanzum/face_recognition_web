package bull_breed_centre;

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


public class Bull_breed_centreDAO  implements NavigationService{
	
	Logger logger = Logger.getLogger(getClass());
	
	
	private void printSql(String sql)
	{
		 System.out.println("sql: " + sql);
	}
	

	private void recordUpdateTime(Connection connection, PreparedStatement ps, long lastModificationTime) throws SQLException
	{
		String query = "UPDATE vbSequencer SET table_LastModificationTime=? WHERE table_name=?";
		ps = connection.prepareStatement(query);
		ps.setLong(1,lastModificationTime);
		ps.setString(2,"bull_breed_centre");
		ps.execute();
		ps.close();
	}
	
	
	public void addBull_breed_centre(Bull_breed_centreDTO bull_breed_centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			bull_breed_centreDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Bull_breed_centre");

			String sql = "INSERT INTO bull_breed_centre";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "bull_type";
			sql += ", ";
			sql += "breed_type";
			sql += ", ";
			sql += "centre_type";
			sql += ", ";
			sql += "grs_office";
			sql += ", ";
			sql += "grs_officer";
			sql += ", ";
			sql += "info_file";
			sql += ", ";
			sql += "bull_image";
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
			
			
			

			int index = 1;

			ps.setObject(index++,bull_breed_centreDTO.iD);
			ps.setObject(index++,bull_breed_centreDTO.bullType);
			ps.setObject(index++,bull_breed_centreDTO.breedType);
			ps.setObject(index++,bull_breed_centreDTO.centreType);
			ps.setObject(index++,bull_breed_centreDTO.grsOffice);
			ps.setObject(index++,bull_breed_centreDTO.grsOfficer);
			ps.setObject(index++,bull_breed_centreDTO.infoFile);
			ps.setObject(index++,bull_breed_centreDTO.bullImage);
			ps.setObject(index++,bull_breed_centreDTO.description);
			ps.setObject(index++,bull_breed_centreDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			recordUpdateTime(connection, ps, lastModificationTime);

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
	}
	
	//need another getter for repository
	public Bull_breed_centreDTO getBull_breed_centreDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Bull_breed_centreDTO bull_breed_centreDTO = null;
		try{
			
			String sql = "SELECT * ";

			sql += " FROM bull_breed_centre";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				bull_breed_centreDTO = new Bull_breed_centreDTO();

				bull_breed_centreDTO.iD = rs.getLong("ID");
				bull_breed_centreDTO.bullType = rs.getInt("bull_type");
				bull_breed_centreDTO.breedType = rs.getInt("breed_type");
				bull_breed_centreDTO.centreType = rs.getInt("centre_type");
				bull_breed_centreDTO.grsOffice = rs.getInt("grs_office");
				bull_breed_centreDTO.grsOfficer = rs.getLong("grs_officer");
				bull_breed_centreDTO.infoFile = rs.getString("info_file");
				bull_breed_centreDTO.bullImage = rs.getString("bull_image");
				bull_breed_centreDTO.description = rs.getString("description");
				bull_breed_centreDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return bull_breed_centreDTO;
	}
	
	public void updateBull_breed_centre(Bull_breed_centreDTO bull_breed_centreDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE bull_breed_centre";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "bull_type=?";
			sql += ", ";
			sql += "breed_type=?";
			sql += ", ";
			sql += "centre_type=?";
			sql += ", ";
			sql += "grs_office=?";
			sql += ", ";
			sql += "grs_officer=?";
			sql += ", ";
			sql += "info_file=?";
			sql += ", ";
			sql += "bull_image=?";
			sql += ", ";
			sql += "description=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + bull_breed_centreDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			ps.setObject(index++,bull_breed_centreDTO.iD);
			ps.setObject(index++,bull_breed_centreDTO.bullType);
			ps.setObject(index++,bull_breed_centreDTO.breedType);
			ps.setObject(index++,bull_breed_centreDTO.centreType);
			ps.setObject(index++,bull_breed_centreDTO.grsOffice);
			ps.setObject(index++,bull_breed_centreDTO.grsOfficer);
			ps.setObject(index++,bull_breed_centreDTO.infoFile);
			ps.setObject(index++,bull_breed_centreDTO.bullImage);
			ps.setObject(index++,bull_breed_centreDTO.description);
			ps.setObject(index++,bull_breed_centreDTO.isDeleted);
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

	}
	
	public void deleteBull_breed_centreByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE bull_breed_centre";
			
			sql += " SET isDeleted=1,lastModificationTime="+ lastModificationTime +" WHERE ID = " + ID;
			
			printSql(sql);

			connection = DatabaseManager.getInstance().getConnection();
			stmt  = connection.createStatement();
			stmt.execute(sql);
			

			
			recordUpdateTime(connection, ps, lastModificationTime);

			
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

	
	
	public List<Bull_breed_centreDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Bull_breed_centreDTO bull_breed_centreDTO = null;
		List<Bull_breed_centreDTO> bull_breed_centreDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return bull_breed_centreDTOList;
		}
		try{
			
			String sql = "SELECT * ";

			sql += " FROM bull_breed_centre";
            
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
				bull_breed_centreDTO = new Bull_breed_centreDTO();
				bull_breed_centreDTO.iD = rs.getLong("ID");
				bull_breed_centreDTO.bullType = rs.getInt("bull_type");
				bull_breed_centreDTO.breedType = rs.getInt("breed_type");
				bull_breed_centreDTO.centreType = rs.getInt("centre_type");
				bull_breed_centreDTO.grsOffice = rs.getInt("grs_office");
				bull_breed_centreDTO.grsOfficer = rs.getLong("grs_officer");
				bull_breed_centreDTO.infoFile = rs.getString("info_file");
				bull_breed_centreDTO.bullImage = rs.getString("bull_image");
				bull_breed_centreDTO.description = rs.getString("description");
				bull_breed_centreDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + bull_breed_centreDTO);
				
				bull_breed_centreDTOList.add(bull_breed_centreDTO);

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
		return bull_breed_centreDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM bull_breed_centre";

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
	public List<Bull_breed_centreDTO> getAllBull_breed_centre (boolean isFirstReload)
    {
		List<Bull_breed_centreDTO> bull_breed_centreDTOList = new ArrayList<>();

		String sql = "SELECT * FROM bull_breed_centre";
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
				Bull_breed_centreDTO bull_breed_centreDTO = new Bull_breed_centreDTO();
				bull_breed_centreDTO.iD = rs.getLong("ID");
				bull_breed_centreDTO.bullType = rs.getInt("bull_type");
				bull_breed_centreDTO.breedType = rs.getInt("breed_type");
				bull_breed_centreDTO.centreType = rs.getInt("centre_type");
				bull_breed_centreDTO.grsOffice = rs.getInt("grs_office");
				bull_breed_centreDTO.grsOfficer = rs.getLong("grs_officer");
				bull_breed_centreDTO.infoFile = rs.getString("info_file");
				bull_breed_centreDTO.bullImage = rs.getString("bull_image");
				bull_breed_centreDTO.description = rs.getString("description");
				bull_breed_centreDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = bull_breed_centreDTO.iD;
				while(i < bull_breed_centreDTOList.size() && bull_breed_centreDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				bull_breed_centreDTOList.add(i,  bull_breed_centreDTO);
				//bull_breed_centreDTOList.add(bull_breed_centreDTO);
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

		return bull_breed_centreDTOList;
    }
	

	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{

			String sql = "SELECT distinct bull_breed_centre.ID as ID FROM bull_breed_centre ";
			sql += " join bull on bull_breed_centre.bull_type = bull.ID ";
			sql += " join breed on bull_breed_centre.breed_type = breed.ID ";
			sql += " join centre on bull_breed_centre.centre_type = centre.ID ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Bull_breed_centreMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Bull_breed_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Bull_breed_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Bull_breed_centreMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "bull_breed_centre." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "bull_breed_centre." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " bull_breed_centre.isDeleted = false";
			
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by bull_breed_centre.ID desc ";

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
	