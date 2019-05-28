package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import databasemanager.DatabaseManager;
import login.LoginDTO;
import login.LoginServlet;
import repository.RepositoryManager;
import util.ConnectionUtil;
import util.NavigationService;
/**
 * @author Kayesh Parvez
 *
 */
public class UserDAO implements NavigationService{
	
	Logger logger = Logger.getLogger(getClass());	
	
	public void addUser(UserDTO userDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long currentTime = System.currentTimeMillis();
		userDTO.isDeleted = false;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			UserDTO existingUserDTO = UserRepository.getUserDTOByUserName(userDTO.userName);
			
			if(existingUserDTO != null && !existingUserDTO.isDeleted)
			{
				throw new RequestFailureException("Username already exists. Please use another username.");
			}
			
			userDTO.ID = DatabaseManager.getInstance().getNextSequenceId("user");
			
			String sql ="insert into user(ID,userName,password,userType,roleID,lastModificationTime,isDeleted,mailAddress,fullName,phoneNo,centre_type) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,userDTO.ID);
			ps.setObject(index++,userDTO.userName);
			ps.setObject(index++,userDTO.password);
			ps.setObject(index++,userDTO.userType);
			ps.setObject(index++,userDTO.roleID);
			ps.setObject(index++,currentTime);
			ps.setObject(index++,userDTO.isDeleted);
			ps.setObject(index++,userDTO.mailAddress);
			ps.setObject(index++,userDTO.fullName);
			ps.setObject(index++,userDTO.phoneNo);
			ps.setObject(index++,userDTO.centreType);

			ps.execute();
			ps.close();
			
			ConnectionUtil.updateVbSequencer("user", currentTime, connection, ps);
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		
	}
	

	public UserDTO getUserDTOByUserID(long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		UserDTO userDTO = null;
		try{
			
			String sql = "SELECT ID,userName,password,userType,roleID,languageID,lastModificationTime,isDeleted,mailAddress,fullName,phoneNo,centre_Type, otpSMS, otpEmail, otpPushNotification FROM user WHERE ID="+ID;

			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				userDTO = new UserDTO();
				userDTO.ID = rs.getLong("ID");
				userDTO.userName = rs.getString("userName");
				userDTO.password = rs.getString("password");
				userDTO.userType = rs.getInt("userType");
				userDTO.roleID = rs.getInt("roleID");
				userDTO.languageID = rs.getInt("languageID");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				userDTO.mailAddress = rs.getString("mailAddress");
				userDTO.fullName = rs.getString("fullName");
				userDTO.phoneNo = rs.getString("phoneNo");
				userDTO.centreType = rs.getInt("centre_Type");
				

				userDTO.otpSMS = rs.getBoolean("otpSMS");
				userDTO.otpEmail = rs.getBoolean("otpEmail");
				userDTO.otpPushNotification = rs.getBoolean("otpPushNotification");
			}
						
			
			sql = "select ID, userID, IP from login_ip where userID = " + ID;
			rs = stmt.executeQuery(sql);
			
			userDTO.loginIPs = new ArrayList<String>();

			while(rs.next()){
				userDTO.loginIPs.add(rs.getString("IP"));
			}
			
			sql = "select ID, name from user_type where ID = " + userDTO.userType;
			rs = stmt.executeQuery(sql);						

			if(rs.next()){
				userDTO.userTypeName = rs.getString("name");
			}
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, null, stmt);
		}
		return userDTO;
	}
	
	public void updateUser(UserDTO userDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long currentTime = System.currentTimeMillis();
		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			UserDTO existingUserDTO = UserRepository.getUserDTOByUserName(userDTO.userName);
			if(existingUserDTO != null && existingUserDTO.ID != userDTO.ID && !existingUserDTO.isDeleted)
			{
				throw new RequestFailureException("Username already exists. Please use another username.");
			}

			String sql ="UPDATE user SET userName=?,password=?,userType=?,roleID=?,languageID=?,lastModificationTime=?,isDeleted=?,mailAddress=?,fullName=?,phoneNo=?,centre_Type=?,otpSMS=?,otpEmail=?,otpPushNotification=? WHERE ID = ?";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,userDTO.userName);
			ps.setObject(index++,userDTO.password);
			ps.setObject(index++,userDTO.userType);
			ps.setObject(index++,userDTO.roleID);
			ps.setObject(index++,userDTO.languageID);
			ps.setObject(index++,currentTime);
			ps.setObject(index++,userDTO.isDeleted);
			ps.setObject(index++,userDTO.mailAddress);
			ps.setObject(index++,userDTO.fullName);
			ps.setObject(index++,userDTO.phoneNo);
			ps.setObject(index++,userDTO.centreType);
			
			ps.setObject(index++, userDTO.otpSMS);
			ps.setObject(index++, userDTO.otpEmail);
			ps.setObject(index++, userDTO.otpPushNotification);
			
			ps.setObject(index++,userDTO.ID);

			ps.executeUpdate();
			
			ps.close();
			
			ConnectionUtil.updateVbSequencer("user", currentTime, connection, ps);
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
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
	public void deleteUserByUserID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		long currentTime = System.currentTimeMillis();
		try{
			String sql = "UPDATE user SET isDeleted=1,lastModificationTime="+currentTime+" WHERE ID = "+ID;

			connection = DatabaseManager.getInstance().getConnection();
			stmt  = connection.createStatement();
			stmt.execute(sql);
			
			ConnectionUtil.updateVbSequencer("user", currentTime, stmt);
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
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
	
	
	public UserDTO getUserDTOByUsername(String userName){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		UserDTO userDTO = null;
		try{
			
			String sql = "SELECT ID,userName,password,userType,roleID, languageID, lastModificationTime,isDeleted,mailAddress,fullName,phoneNo,centre_Type FROM user WHERE userName='"+userName+"' order by ID desc";
			logger.debug(sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				userDTO = new UserDTO();
				userDTO.ID = rs.getLong("ID");
				userDTO.userName = rs.getString("userName");
				userDTO.password = rs.getString("password");
				userDTO.userType = rs.getInt("userType");
				userDTO.roleID = rs.getInt("roleID");
				userDTO.languageID = rs.getInt("languageID");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				userDTO.mailAddress = rs.getString("mailAddress");
				userDTO.fullName = rs.getString("fullName");
				userDTO.phoneNo = rs.getString("phoneNo");
				userDTO.centreType = rs.getInt("centre_Type");

			}
						
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
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
		return userDTO;
	}
	
		
	public UserDTO getUserDTOByMail(String mailAddress){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		UserDTO userDTO = null;
		try{
			
			String sql = "SELECT ID,userName,password,userType,roleID,lastModificationTime,isDeleted,mailAddress,fullName,phoneNo,centre_Type FROM user WHERE mailAddress='"+mailAddress+"'";

			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				userDTO = new UserDTO();
				userDTO.ID = rs.getLong("ID");
				userDTO.userName = rs.getString("userName");
				userDTO.password = rs.getString("password");
				userDTO.userType = rs.getInt("userType");
				userDTO.roleID = rs.getInt("roleID");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				userDTO.mailAddress = rs.getString("mailAddress");
				userDTO.fullName = rs.getString("fullName");
				userDTO.phoneNo = rs.getString("phoneNo");
				userDTO.centreType = rs.getInt("centre_Type");

			}
						
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
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
		return userDTO;
	} 

	
	
	public List<UserDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<UserDTO> userDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return userDTOList;
		}
		try{
			
			String sql = "SELECT ID,userName,password,userType,roleID,lastModificationTime,isDeleted,mailAddress,fullName,phoneNo,centre_Type FROM user WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=")";
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				UserDTO userDTO = new UserDTO();
				userDTO.ID = rs.getLong("ID");
				userDTO.userName = rs.getString("userName");
				userDTO.password = rs.getString("password");
				userDTO.userType = rs.getInt("userType");
				userDTO.roleID = rs.getInt("roleID");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				userDTO.mailAddress = rs.getString("mailAddress");
				userDTO.fullName = rs.getString("fullName");
				userDTO.phoneNo = rs.getString("phoneNo");
				userDTO.centreType = rs.getInt("centre_Type");
				
				userDTOList.add(userDTO);

			}
						
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw new RuntimeException();
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
		return userDTOList;
	
	}
	
    public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "select ID from user where isDeleted = 0";
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getString("ID")));
	
	        resultSet.close();
        }
        catch (Exception e)
        {
	    	logger.fatal("", e);
	    	throw new RuntimeException(e);
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

    public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) 
    {
    	Hashtable<String, String> searchCriteria = (Hashtable<String, String>)p_searchCriteria;
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			boolean conditionAdded = false;
			List<Object> objectList = new ArrayList<>();
			String sql = "SELECT ID FROM user where isDeleted = 0";
			conditionAdded = true;
			
			String userNameInputFromUI = searchCriteria.get("userName");
			if(userNameInputFromUI!=null && !userNameInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="userName LIKE ? ";
				objectList.add("%"+userNameInputFromUI+"%");
			}
		
			String userTypeInputFromUI = searchCriteria.get("userType");
			if(userTypeInputFromUI!=null && !userTypeInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="userType LIKE ? ";
				objectList.add("%"+userTypeInputFromUI+"%");
			}
		
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0;i<objectList.size();i++){
				ps.setObject(i+1, objectList.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong(1));
			}
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
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
    
    public List getAllUsers(boolean isFirstReload)
    {
		List<UserDTO> userDTOList = new ArrayList<>();

		String sql = "SELECT * FROM user WHERE ";

		if(isFirstReload){
			sql+=" isDeleted =  0";
		}
		if(!isFirstReload){
			sql+=" lastModificationTime >= " + RepositoryManager.lastModifyTime;		}
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Statement stmt2 = null;
		ResultSet rs2 = null;
		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			stmt2 = connection.createStatement();
			rs = stmt.executeQuery(sql);
			

			while(rs.next()){
				UserDTO userDTO = new UserDTO();
				userDTO.ID = rs.getLong("ID");
				userDTO.userName = rs.getString("userName");
				userDTO.password = rs.getString("password");
				userDTO.userType = rs.getInt("userType");
				userDTO.roleID = rs.getInt("roleID");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				userDTO.mailAddress = rs.getString("mailAddress");
				userDTO.languageID = rs.getInt("languageID");
				userDTO.fullName = rs.getString("fullName");
				userDTO.phoneNo = rs.getString("phoneNo");
				userDTO.centreType = rs.getInt("centre_Type");
				userDTO.isDeleted = rs.getBoolean("isDeleted");
				
				userDTO.otpSMS = rs.getBoolean("otpSMS");
				userDTO.otpEmail = rs.getBoolean("otpEmail");
				userDTO.otpPushNotification = rs.getBoolean("otpPushNotification");
				
				userDTOList.add(userDTO);
				
				sql = "select ID, userID, IP from login_ip where userID = " + userDTO.ID;
				rs2 = stmt2.executeQuery(sql);
				
				userDTO.loginIPs = new ArrayList<String>();

				while(rs2.next()){
					userDTO.loginIPs.add(rs2.getString("IP"));
				}
			}
			

			
		}catch(Exception ex){
			logger.fatal("",ex);
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

		return userDTOList;

    }


}
