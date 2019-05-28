package semen_dashboard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import pbReport.*;

import databasemanager.DatabaseManager;


public class Semen_dashboardDAO 
{
	
	public Semen_dashboardDTO  getTop5ProgenyBulls(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "SELECT \r\n" + 
					"    bull_name,\r\n" + 
					"    grand_total_number_of_calfs\r\n" + 
					"FROM\r\n" + 
					"    (SELECT DISTINCT\r\n" + 
					"            bull_name,\r\n" + 
					"            (total_number_of_male_calfs + total_number_of_female_calfs) AS grand_total_number_of_calfs\r\n" + 
					"    FROM\r\n" + 
					"        (SELECT \r\n" + 
					"            bull.name_en AS bull_name,\r\n" + 
					"            (SELECT \r\n" + 
					"                    IFNULL(SUM(number_of_male_calfs), 0)\r\n" + 
					"                FROM\r\n" + 
					"                    progeny_record\r\n" + 
					"                WHERE\r\n" + 
					"                    bull.id = progeny_record.bull_type) AS total_number_of_male_calfs,\r\n" + 
					"            (SELECT \r\n" + 
					"                    IFNULL(SUM(number_of_female_calfs), 0)\r\n" + 
					"                FROM\r\n" + 
					"                    progeny_record\r\n" + 
					"                WHERE\r\n" + 
					"                    bull.id = progeny_record.bull_type) AS total_number_of_female_calfs,\r\n" + 
					"            date_of_entry\r\n" + 
					"    FROM\r\n" + 
					"        progeny_record\r\n" + 
					"    LEFT OUTER JOIN bull ON progeny_record.bull_type = bull.id) AS derived) AS outer_derived\r\n" + 
					"ORDER BY grand_total_number_of_calfs DESC , bull_name ASC "
					+ "LIMIT 5";

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			int i = 0;
			while(rs.next())
			{
				semen_dashboardDTO.top_5_progeny_bulls[i] = rs.getString("bull_name");
				semen_dashboardDTO.top_5_progeny_bulls_calfs[i] = rs.getInt("grand_total_number_of_calfs");	
				i ++;
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 1: " + ex);
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
		return semen_dashboardDTO;
	}
	
	
	public Semen_dashboardDTO getTop5ProgenyCentres(Semen_dashboardDTO semen_dashboardDTO) throws Exception
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "SELECT \r\n" + 
					"    centre_name,\r\n" + 
					"    grand_total_number_of_calfs\r\n" + 
					"FROM\r\n" + 
					"    (SELECT DISTINCT\r\n" + 
					"            centre_name,\r\n" + 
					"            (total_number_of_male_calfs + total_number_of_female_calfs) AS grand_total_number_of_calfs\r\n" + 
					"    FROM\r\n" + 
					"        (SELECT \r\n" + 
					"            centre.name_en AS centre_name,\r\n" + 
					"            (SELECT \r\n" + 
					"                    IFNULL(SUM(number_of_male_calfs), 0)\r\n" + 
					"                FROM\r\n" + 
					"                    progeny_record\r\n" + 
					"                WHERE\r\n" + 
					"                    centre.id = progeny_record.centre_type) AS total_number_of_male_calfs,\r\n" + 
					"            (SELECT \r\n" + 
					"                    IFNULL(SUM(number_of_female_calfs), 0)\r\n" + 
					"                FROM\r\n" + 
					"                    progeny_record\r\n" + 
					"                WHERE\r\n" + 
					"                    centre.id = progeny_record.centre_type) AS total_number_of_female_calfs,\r\n" + 
					"            date_of_entry\r\n" + 
					"    FROM\r\n" + 
					"        progeny_record\r\n" + 
					"    LEFT OUTER JOIN centre ON progeny_record.centre_type = centre.id) AS derived) AS outer_derived\r\n" + 
					"ORDER BY grand_total_number_of_calfs DESC , centre_name ASC \r\n" + 
					"LIMIT 5";

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			int i = 0;
			while(rs.next())
			{
				semen_dashboardDTO.top_5_progeny_centers[i] = rs.getString("centre_name");
				semen_dashboardDTO.top_5_progeny_centers_calfs[i] = rs.getInt("grand_total_number_of_calfs");
				i ++;
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 2: " + ex);
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
		return semen_dashboardDTO;
	}
	
	
	public Semen_dashboardDTO  getAPATragets(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "SELECT \r\n" + 
					"    *\r\n" + 
					"FROM\r\n" + 
					"    apa_target\r\n" + 
					"WHERE\r\n" + 
					"    id = (SELECT \r\n" + 
					"            MAX(ID)\r\n" + 
					"        FROM\r\n" + 
					"            apa_target)";

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				semen_dashboardDTO.apa_ai = rs.getInt("artificial_insemenation");	
				semen_dashboardDTO.apa_cb = rs.getInt("candidate_bull_production");
				semen_dashboardDTO.apa_progeny = rs.getInt("progeny");
				semen_dashboardDTO.apa_semen_collection = rs.getInt("semen_collection");
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 3: " + ex);
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
		return semen_dashboardDTO;
	}
	
	public Semen_dashboardDTO  getActualSemenCollection(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "select sum(no_of_dose) from semen_collection where transaction_date > " + DateUtils.get1stDayOfJuly();

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				semen_dashboardDTO.actual_semen_collection = rs.getInt("sum(no_of_dose)");	
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 3: " + ex);
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
		return semen_dashboardDTO;
	}
	
	public Semen_dashboardDTO  getActualAI(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "select sum(no_of_AI) from artificial_insemenation_record where entry_date > " + DateUtils.get1stDayOfJuly();

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				semen_dashboardDTO.actual_ai = rs.getInt("sum(no_of_AI)");	
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 3: " + ex);
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
		return semen_dashboardDTO;
	}
	
	public Semen_dashboardDTO  getActualProgeny(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "select sum(number_of_male_calfs), sum(number_of_female_calfs) from progeny_record where date_of_entry > " + DateUtils.get1stDayOfJuly();

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				int total_male_calfs = rs.getInt("sum(number_of_male_calfs)");
				int total_female_calfs = rs.getInt("sum(number_of_female_calfs)");	
				semen_dashboardDTO.actual_progeny = total_male_calfs + total_female_calfs;	
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 3: " + ex);
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
		return semen_dashboardDTO;
	}
	
	public Semen_dashboardDTO  getActualCBProduction(Semen_dashboardDTO semen_dashboardDTO) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try{
			
			String sql = "select sum(number_of_candidate_bulls) from candidate_bull_production where production_date > " + DateUtils.get1stDayOfJuly();

			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next())
			{	
				semen_dashboardDTO.actual_cb = rs.getInt("sum(number_of_candidate_bulls)");
			}			
			
		}catch(Exception ex){
			System.out.println("Sql error 3: " + ex);
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
		return semen_dashboardDTO;
	}
	
	public Semen_dashboardDTO getDashboardDTO()
	{
		Semen_dashboardDTO semen_dashboardDTO = new Semen_dashboardDTO();
		try 
		{
			semen_dashboardDTO = getTop5ProgenyBulls(semen_dashboardDTO);
			semen_dashboardDTO = getTop5ProgenyCentres(semen_dashboardDTO);
			semen_dashboardDTO = getAPATragets(semen_dashboardDTO);
			semen_dashboardDTO = getActualSemenCollection(semen_dashboardDTO);
			semen_dashboardDTO = getActualAI(semen_dashboardDTO);
			semen_dashboardDTO = getActualProgeny(semen_dashboardDTO);
			semen_dashboardDTO = getActualCBProduction(semen_dashboardDTO);
			
			semen_dashboardDTO.semen_collection_percentage = semen_dashboardDTO.actual_semen_collection * 100.0 / semen_dashboardDTO.apa_semen_collection;
			semen_dashboardDTO.ai_percentage = semen_dashboardDTO.actual_ai * 100.0 / semen_dashboardDTO.apa_ai;
			semen_dashboardDTO.progeny_percentage = semen_dashboardDTO.actual_progeny * 100.0 / semen_dashboardDTO.apa_progeny;
			semen_dashboardDTO.cb_percentage = semen_dashboardDTO.actual_cb* 100.0 / semen_dashboardDTO.apa_cb;
			
			System.out.println(semen_dashboardDTO);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return semen_dashboardDTO;
	}

}
