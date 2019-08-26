package pb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databasemanager.DatabaseManager;

public  class GRS_OFFICE_DAO 
{
	public static  List<GRS_OFFICE_DTO> getGRS_DTO(int layerNum, int selectedValue)
	{
		//System.out.println("In GRS DAO layerNum = " + layerNum + " selectedValue = " + selectedValue);
		String sql = "";
		String name_en = "", name_bn = "";
		if(layerNum == 0)
		{
			if(selectedValue == 1 || selectedValue == 2)
			{
				sql = "select * from offices o where o.office_layer_id in (select id from office_layers ol where ol.layer_level=" + 
						selectedValue + " and ol.`status`=true) and o.`status`=true";
				name_en = "office_name_eng";
				name_bn = "office_name_bng";
			}
			else if (selectedValue == 3)
			{
				sql = "select * from office_custom_layers ocl where ocl.layer_level=3";
				name_en = "name";
				name_bn = "name";
			}
			else
			{
				sql = "select * from office_origins o where o.office_layer_id in "
						+ "(select id from office_layers ol where ol.custom_layer_id in "
						+ "(select id from office_custom_layers ocl where ocl.layer_level = "
						+ selectedValue
						+ ") and ol.`status`=true) and o.`status`=true;";
				name_en = "office_name_eng";
				name_bn = "office_name_bng";
			}
				
		}
		else
		{
			sql = "select * from offices o where o.office_layer_id in (select id from office_layers ol where ol.custom_layer_id="
					+ selectedValue
					+ " and ol.`status`=true) and o.`status`=true";
			name_en = "office_name_eng";
			name_bn = "office_name_bng";
		}
		List<GRS_OFFICE_DTO> gRS_DTO_List = new ArrayList<GRS_OFFICE_DTO>();
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next())
			{
				GRS_OFFICE_DTO gRS_DTO = new GRS_OFFICE_DTO();
				gRS_DTO.id = rs.getLong("ID");
				gRS_DTO.name = rs.getString(name_bn);
				
				gRS_DTO_List.add(gRS_DTO);

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

		return gRS_DTO_List;
	}
	
	public static String  getOfficeName(int id, String language)
	{
		//System.out.println("In GRS DAO getOfficeName = " + id);
		String sql = "SELECT office_name_eng, office_name_bng FROM offices where id = " + id;
		
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String name = "";
		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				if(language.equalsIgnoreCase("english"))
				{
					name = rs.getString("office_name_eng");
				}
				else
				{
					name = rs.getString("office_name_bng");
				}				

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

		return name;
	}

}
