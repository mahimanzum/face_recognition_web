package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;

/**
 * @author Kayesh Parvez
 *
 */
public class UserTypeRepository {
	Logger logger = Logger.getLogger(UserTypeRepository.class);
	public static UserTypeRepository instance;
	private ArrayList<UserTypeDTO> userTypeDTOList;
	private HashMap<Integer, UserTypeDTO> mapUserTypeDTOToUserTypeID;
	
	private UserTypeRepository() {
		// TODO Auto-generated constructor stub
		userTypeDTOList = new ArrayList<>();
		mapUserTypeDTOToUserTypeID = new HashMap<>();
		reload();
	}
	public synchronized static UserTypeRepository getInstance(){
		if (instance == null){
			instance = new UserTypeRepository();			
		}
		return instance;
	}
	
	public List<UserTypeDTO> getAllUserType()
	{		
		return userTypeDTOList;
	}
	public UserTypeDTO getUserTypeByUserTypeID(int userTypeID)
	{
		return mapUserTypeDTOToUserTypeID.get(userTypeID);
	}
	public void reload()
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			
			String sql = "select * from user_type";
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);			

			while(rs.next()){
				UserTypeDTO userTypeDTO = new UserTypeDTO();
				userTypeDTO.ID = rs.getInt("ID");
				userTypeDTO.name_en = rs.getString("name_en");
				userTypeDTO.name_bn = rs.getString("name_bn");
				userTypeDTO.dashboard = rs.getString("dashboard");
				userTypeDTOList.add(userTypeDTO);
				mapUserTypeDTOToUserTypeID.put(userTypeDTO.ID, userTypeDTO);
			}
			
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
		}
		finally{
			try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
			try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); }}catch (Exception e){}
		}
	}
}
