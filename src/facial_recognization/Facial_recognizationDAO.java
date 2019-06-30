package facial_recognization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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


public class Facial_recognizationDAO  implements NavigationService{
	
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
		ps.setString(2,"facial_recognization");
		ps.execute();
		ps.close();
	}
	
	
	public void addFacial_recognization(Facial_recognizationDTO facial_recognizationDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			facial_recognizationDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Facial_recognization");

			String sql = "INSERT INTO facial_recognization";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "name";
			sql += ", ";
			sql += "address";
			sql += ", ";
			sql += "phone";
			sql += ", ";
			sql += "email";
			sql += ", ";
			sql += "image";
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
			
			
			

			int index = 1;

			ps.setObject(index++,facial_recognizationDTO.iD);
			ps.setObject(index++,facial_recognizationDTO.name);
			ps.setObject(index++,facial_recognizationDTO.address);
			ps.setObject(index++,facial_recognizationDTO.phone);
			ps.setObject(index++,facial_recognizationDTO.email);
			ps.setObject(index++,facial_recognizationDTO.image);
			ps.setObject(index++,facial_recognizationDTO.isDeleted);
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
	public Facial_recognizationDTO getFacial_recognizationDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Facial_recognizationDTO facial_recognizationDTO = null;
		try{
			
			String sql = "SELECT * ";

			sql += " FROM facial_recognization";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				facial_recognizationDTO = new Facial_recognizationDTO();

				facial_recognizationDTO.iD = rs.getLong("ID");
				facial_recognizationDTO.name = rs.getString("name");
				facial_recognizationDTO.address = rs.getString("address");
				facial_recognizationDTO.phone = rs.getString("phone");
				facial_recognizationDTO.email = rs.getString("email");
				facial_recognizationDTO.image = rs.getString("image");
				facial_recognizationDTO.isDeleted = rs.getBoolean("isDeleted");

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
		return facial_recognizationDTO;
	}
	
	public void updateFacial_recognization(Facial_recognizationDTO facial_recognizationDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE facial_recognization";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "name=?";
			sql += ", ";
			sql += "address=?";
			sql += ", ";
			sql += "phone=?";
			sql += ", ";
			sql += "email=?";
			sql += ", ";
			sql += "image=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + facial_recognizationDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			ps.setObject(index++,facial_recognizationDTO.iD);
			ps.setObject(index++,facial_recognizationDTO.name);
			ps.setObject(index++,facial_recognizationDTO.address);
			ps.setObject(index++,facial_recognizationDTO.phone);
			ps.setObject(index++,facial_recognizationDTO.email);
			ps.setObject(index++,facial_recognizationDTO.image);
			ps.setObject(index++,facial_recognizationDTO.isDeleted);
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
	
	public void deleteFacial_recognizationByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE facial_recognization";
			
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

	
	
	public List<Facial_recognizationDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Facial_recognizationDTO facial_recognizationDTO = null;
		List<Facial_recognizationDTO> facial_recognizationDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return facial_recognizationDTOList;
		}
		try{
			
			String sql = "SELECT * ";

			sql += " FROM facial_recognization";
            
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
				facial_recognizationDTO = new Facial_recognizationDTO();
				facial_recognizationDTO.iD = rs.getLong("ID");
				facial_recognizationDTO.name = rs.getString("name");
				facial_recognizationDTO.address = rs.getString("address");
				facial_recognizationDTO.phone = rs.getString("phone");
				facial_recognizationDTO.email = rs.getString("email");
				facial_recognizationDTO.image = rs.getString("image");
				facial_recognizationDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + facial_recognizationDTO);
				
				facial_recognizationDTOList.add(facial_recognizationDTO);

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
		return facial_recognizationDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) throws IOException 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	//here put the code
    	String csvFile = "C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\"+"data.csv";
    	String decision = "C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\"+"decision.csv";
    	FileWriter csvWriter = new FileWriter(decision);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String ans = "";
        boolean whole = true;
        System.out.println("hello");
		String currentDirectory = System.getProperty("user.dir");
	    System.out.println("The current working directory is " + currentDirectory);
        
        try {
        	br = new BufferedReader(new FileReader("C:\\Users\\REVE PC\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\dls2\\img2\\"+"test_data.csv"));
        	double test[] = new double[128];
        	while ((line = br.readLine()) != null) {
	            // use comma as separator
	            String[] td = line.split(cvsSplitBy);
	            for(int c = 1; c <td.length; c++) test[c-1] = Double.parseDouble(td[c]);
	           }
        	
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	            // use comma as separator
				double sm = 0.0;
				//double temp[] = new double[128];
	            String[] country = line.split(cvsSplitBy);
	            for(int c = 1; c <country.length; c++) sm = sm + ((Double.parseDouble(country[c]))-test[c-1])*((Double.parseDouble(country[c]))-test[c-1]);
	            if(sm<=0.4) {
	            	System.out.print(sm);
	            	System.out.println("");
	            	System.out.println(country[0]);
	            	ans = country[0];
	            	break;
	            }
	            else sm = 0.0;
	            }
			br = new BufferedReader(new FileReader(decision));
			while ((line = br.readLine()) != null) {
	            // use comma as separator
	            String[] td = line.split(cvsSplitBy);
	            if(td[0].equalsIgnoreCase("no")) {
	            	whole = false;
	            	
	            }
	           }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        csvWriter.append("no");  
        csvWriter.flush();  
        csvWriter.close();
        
        String sql = "SELECT ID FROM facial_recognization";

		sql += " WHERE isDeleted = 0  order by ID desc ";
        //if(!whole) 
        	
        sql = "SELECT ID FROM facial_recognization where name like "+"\"%"+ans+"%\"";
		printSql(sql);
		
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        resultSet = stmt.executeQuery(sql);
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
	public List<Facial_recognizationDTO> getAllFacial_recognization (boolean isFirstReload)
    {
		List<Facial_recognizationDTO> facial_recognizationDTOList = new ArrayList<>();

		String sql = "SELECT * FROM facial_recognization";
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
				Facial_recognizationDTO facial_recognizationDTO = new Facial_recognizationDTO();
				facial_recognizationDTO.iD = rs.getLong("ID");
				facial_recognizationDTO.name = rs.getString("name");
				facial_recognizationDTO.address = rs.getString("address");
				facial_recognizationDTO.phone = rs.getString("phone");
				facial_recognizationDTO.email = rs.getString("email");
				facial_recognizationDTO.image = rs.getString("image");
				facial_recognizationDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = facial_recognizationDTO.iD;
				while(i < facial_recognizationDTOList.size() && facial_recognizationDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				facial_recognizationDTOList.add(i,  facial_recognizationDTO);
				//facial_recognizationDTOList.add(facial_recognizationDTO);
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

		return facial_recognizationDTOList;
    }
	

	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{

			String sql = "SELECT distinct facial_recognization.ID as ID FROM facial_recognization ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Facial_recognizationMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
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
		        if(Facial_recognizationMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Facial_recognizationMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
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
		        	if(Facial_recognizationMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "facial_recognization." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "facial_recognization." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " facial_recognization.isDeleted = false";
			
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by facial_recognization.ID desc ";

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
	