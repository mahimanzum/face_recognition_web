package config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;

public class GlobalConfigurationRepository {
	Logger logger = Logger.getLogger(getClass());
	private static GlobalConfigurationRepository instance = null;
	private static HashMap<Integer, GlobalConfigDTO> GlobalConfigDTOByID;
	private static HashMap<Integer, ArrayList<GlobalConfigDTO>> GlobalConfigDTOByGroupID;
	private GlobalConfigurationRepository()
	{
		GlobalConfigDTOByID = new HashMap<Integer, GlobalConfigDTO>();
		GlobalConfigDTOByGroupID = new HashMap<>();
		reload();
	}
	public static GlobalConfigurationRepository getInstance()
	{
		if(instance == null)
		{
			createInstance();
		}
		return instance;
	}
	private synchronized static void createInstance()
	{
		if(instance == null)
		{
			instance = new GlobalConfigurationRepository();
		}
		
	}
	public static synchronized Collection<GlobalConfigDTO> getConfigsByGroupID(int groupID)
	{		
		return GlobalConfigDTOByGroupID.get(groupID);
	}
	public static synchronized Collection<GlobalConfigDTO> getAllConfigs()
	{		
		return GlobalConfigDTOByID.values();
	}	
	public static synchronized GlobalConfigDTO getGlobalConfigDTOByID(int configID)
	{
//		logger.debug("StateNameByStateID " + GlobalConfigDTOByID);
		if(GlobalConfigDTOByID.get(configID) == null)
		{
			GlobalConfigDTO globalConfigDTO = new GlobalConfigDTO();
			return globalConfigDTO;
		}
		return GlobalConfigDTOByID.get(configID);
	}
	
	public void reload()
	{
		Connection connection = null;
		Statement statement = null;
		String sql = null;
		ResultSet resultSet = null;
		
		
		try{
			
			GlobalConfigDTOByID.clear();
			GlobalConfigDTOByGroupID.clear();
			
			connection = DatabaseManager.getInstance().getConnection();
			statement = connection.createStatement();
			
			sql = "select * from global_config";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				GlobalConfigDTO globalConfigDTO = new GlobalConfigDTO();
				globalConfigDTO.ID = resultSet.getInt("ID");
				globalConfigDTO.name = resultSet.getString("name");
				globalConfigDTO.value = resultSet.getInt("value");
				globalConfigDTO.comments = resultSet.getString("comments");
				globalConfigDTO.groupID = resultSet.getInt("groupID");
				GlobalConfigDTOByID.put(globalConfigDTO.ID, globalConfigDTO);
				ArrayList<GlobalConfigDTO> list = GlobalConfigDTOByGroupID.get(globalConfigDTO.groupID); 
				if(list == null)
				{
					list = new ArrayList<GlobalConfigDTO>();
				}
				list.add(globalConfigDTO);
				GlobalConfigDTOByGroupID.put(globalConfigDTO.groupID, list);
			}
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
		}
		finally {
			try {if(statement != null){statement.close();}}catch(Exception e) {}			
			try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); }}catch(Exception e) {}			
		}

		
	}
}
