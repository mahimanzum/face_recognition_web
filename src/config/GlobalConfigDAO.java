package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;
/**
 * @author Kayesh Parvez
 *
 */
public class GlobalConfigDAO {
	Logger logger = Logger.getLogger(GlobalConfigDAO.class);
	
	public void updateGlobalConfiguration(ArrayList<GlobalConfigDTO> globalConfigurationDTOList)
	{
		Connection connection = null;
		PreparedStatement ps = null;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql ="UPDATE global_config SET value=? WHERE ID = ?";

			ps = connection.prepareStatement(sql);
			
			for(GlobalConfigDTO globalConfigDTO: globalConfigurationDTOList)
			{				
				int index = 1;

				ps.setObject(index++,globalConfigDTO.value);
				ps.setObject(index++,globalConfigDTO.ID);
				
				ps.addBatch();
			}

			ps.executeBatch();
			
			GlobalConfigurationRepository.getInstance().reload();
			
		}catch(Exception ex){
			logger.fatal("",ex);
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
}
