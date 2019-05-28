package permission;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import databasemanager.DatabaseManager;

public class ColumnRepository {
	Logger logger = Logger.getLogger(getClass());
	List<ColumnDTO> columnList = new ArrayList<>();
	private static ColumnRepository instance = null;
	private ColumnRepository()
	{
		reload();
	}
	public static ColumnRepository getInstance()
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
			instance = new ColumnRepository();
		}
		
	}

	
	public List<ColumnDTO> getAllColumnList(){
		List<ColumnDTO> columnDTOList = new ArrayList<>();
		for(ColumnDTO columnDTO: this.columnList){
			ColumnDTO columnDTOClone = cloneColumnDTO(columnDTO);
			columnDTOList.add(columnDTOClone);
		}
		return columnDTOList;
	}
	
	private ColumnDTO cloneColumnDTO(ColumnDTO columnDTO){
		ColumnDTO columnDTOClone = new ColumnDTO();
		
		columnDTOClone.columnID = columnDTO.columnID;
		columnDTOClone.columnName = columnDTO.columnName;
		
		return columnDTOClone;
	}
	
	public void reload()
	{
		List<ColumnDTO> columnDTOList = new ArrayList<>();

		String sql = "SELECT * FROM `column` ";

		Connection connection = null;
		Statement stmt = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				ColumnDTO columnDTO = new ColumnDTO();
				columnDTO.columnID = rs.getInt("columnID");
				columnDTO.columnName = rs.getString("columnName");
				columnDTOList.add(columnDTO);
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


		this.columnList = columnDTOList;
	}
}

