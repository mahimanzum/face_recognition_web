package pb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databasemanager.DatabaseManager;

public  class GRS_OFFICER_DAO 
{
	public static  List<GRS_OFFICER_DTO> getGRS_Officer_DTO(long officeID)
	{
		//System.out.println("In GRS DAO layerNum = " + layerNum + " selectedValue = " + selectedValue);
		String sql = "SELECT * FROM projapoti_db.employee_offices where office_id = " + officeID + " and status = 1";
	
		List<GRS_OFFICER_DTO> gRS_DTO_List = new ArrayList<GRS_OFFICER_DTO>();
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
				GRS_OFFICER_DTO gRS_DTO = new GRS_OFFICER_DTO();
				gRS_DTO.id = rs.getLong("id");
				gRS_DTO.office_unit_organogram_id = rs.getLong("office_unit_organogram_id");
				gRS_DTO.employee_record_id = rs.getLong("employee_record_id");
				gRS_DTO.office_unit_id = rs.getLong("office_unit_id");
				
				
				gRS_DTO = getEmployeeInfo(gRS_DTO.employee_record_id, gRS_DTO);
				gRS_DTO = getUnitInfo(gRS_DTO.office_unit_id, gRS_DTO);
				gRS_DTO = getOrganogramInfo(gRS_DTO.office_unit_organogram_id, gRS_DTO);
				
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
	
	public static  GRS_OFFICER_DTO getGRS_Officer_DTOByID(long id)
	{
		//System.out.println("In GRS DAO layerNum = " + layerNum + " selectedValue = " + selectedValue);
		String sql = "SELECT * FROM projapoti_db.employee_offices where id = " + id + " and status = 1";
	
		GRS_OFFICER_DTO gRS_DTO = new GRS_OFFICER_DTO();
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				gRS_DTO.id = rs.getLong("id");
				gRS_DTO.office_unit_organogram_id = rs.getLong("office_unit_organogram_id");
				gRS_DTO.employee_record_id = rs.getLong("employee_record_id");
				gRS_DTO.office_unit_id = rs.getLong("office_unit_id");
				
				
				gRS_DTO = getEmployeeInfo(gRS_DTO.employee_record_id, gRS_DTO);
				gRS_DTO = getUnitInfo(gRS_DTO.office_unit_id, gRS_DTO);
				gRS_DTO = getOrganogramInfo(gRS_DTO.office_unit_organogram_id, gRS_DTO);
				

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

		return gRS_DTO;
	}
	
	public static GRS_OFFICER_DTO  getEmployeeInfo (long employee_record_id, GRS_OFFICER_DTO gRS_DTO)
	{
		//System.out.println("In GRS DAO getOfficeName = " + id);
		String sql = "SELECT * FROM projapoti_db.employee_records where id = " + employee_record_id;
		
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				gRS_DTO.name_bng = rs.getString("name_eng");
				gRS_DTO.name_bng = rs.getString("name_bng");
				gRS_DTO.personal_email = rs.getString("personal_email");
				gRS_DTO.personal_mobile = rs.getString("personal_mobile");

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

		return gRS_DTO;
	}
	
	public static GRS_OFFICER_DTO  getUnitInfo (long office_unit_id, GRS_OFFICER_DTO gRS_DTO)
	{
		//System.out.println("In GRS DAO getOfficeName = " + id);
		String sql = "SELECT * FROM projapoti_db.office_units where id = " + office_unit_id;
		
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				gRS_DTO.unit_name_bng = rs.getString("unit_name_bng");
				gRS_DTO.unit_name_eng = rs.getString("unit_name_eng");

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

		return gRS_DTO;
	}
	
	public static GRS_OFFICER_DTO  getOrganogramInfo (long office_unit_organogram_id, GRS_OFFICER_DTO gRS_DTO)
	{
		//System.out.println("In GRS DAO getOfficeName = " + id);
		String sql = "SELECT * FROM projapoti_db.office_unit_organograms where id = " + office_unit_organogram_id;
		
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;

		try{
					
			
			//System.out.println("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				gRS_DTO.designation_bng = rs.getString("designation_bng");
				gRS_DTO.designation_eng = rs.getString("designation_eng");
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

		return gRS_DTO;
	}

}
