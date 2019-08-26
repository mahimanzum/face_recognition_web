package geolocation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import geolocation.GeoLocationDTO;

import databasemanager.DatabaseManager;

public class GeoLocationDAO2 {
	
	private static void printSql(String sql)
	{
		 System.out.println("sql: " + sql);
	}
	
	public static List<GeoLocationDTO>  getLocation (int parentID, int geoLevelID) throws Exception
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<GeoLocationDTO> GeoLocationDTOList = new ArrayList<GeoLocationDTO>();
		try
		{
			
			String sql = "select id, name_en from geo_location where parent_geo_id =" 
			+  parentID
			+ " and geo_level_id = " + geoLevelID;
			
			//printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			while(rs.next()){
				 //System.out.println(rs.getString("name_en") + "  " + rs.getInt("id"));	
				 GeoLocationDTO geoLocationDTO = new GeoLocationDTO();
				 geoLocationDTO.id = rs.getInt("id");
				 geoLocationDTO.name_en = rs.getString("name_en");
				 geoLocationDTO.geoLevelID = geoLevelID;
				 geoLocationDTO.parentID = parentID;
				 
				 GeoLocationDTOList.add(geoLocationDTO);

			}			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
			try
			{ 
				if (stmt != null) 
				{
					stmt.close();
				}
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			try
			{ 
				if (connection != null)
				{ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}
			catch(Exception ex2)
			{
				System.out.println(ex2);
			}
		}
		return GeoLocationDTOList;
	}
	
	public static String getLevelName(int geo_level_id)throws Exception
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String name = "Unknown";
		try{
			
			String sql = "select name from geo_location_type where id =" 
			+  geo_level_id;
			
			//printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
	
	
			rs = stmt.executeQuery(sql);
			
			
	
			if(rs.next()){				 
				name = rs.getString("name");
				//System.out.println("name = " + name);
			}			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
			try
			{ 
				if (stmt != null) 
				{
					stmt.close();
				}
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			try
			{ 
				if (connection != null)
				{ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}
			catch(Exception ex2)
			{
				System.out.println(ex2);
			}
		}
		return name;
	}
	
	public static int getLevel(int id) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int iLevel = -1;
		try{
			
			String sql = "select geo_level_id from geo_location where id =" 
			+  id;
			
			//printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			if(rs.next()){				 
				 iLevel = rs.getInt("geo_level_id");
				 //System.out.println("iLevel = " + iLevel);
			}			
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
			try
			{ 
				if (stmt != null) 
				{
					stmt.close();
				}
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			try
			{ 
				if (connection != null)
				{ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}
			catch(Exception ex2)
			{
				System.out.println(ex2);
			}
		}
		return iLevel;
	}
	
	public static String getLocationText (int ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<String> AddressList = new ArrayList<String>();
		String AddressText = "";
		try
		{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			while(ID != 1)
			{
			
				String sql = "select parent_geo_id, name_en from geo_location where id =" 
				+  ID;
				
				//printSql(sql);
				
				
				
		
				rs = stmt.executeQuery(sql);
							
	
				if(rs.next())
				{
					 //System.out.println(rs.getString("name_en") + "  " + rs.getInt("parent_geo_id"));	
					 
					 AddressList.add(rs.getString("name_en"));
					 ID =  rs.getInt("parent_geo_id");
				}
				else
				{
					break;
				}
			}
			int i = AddressList.size() - 1;
			while (i >= 0) 
			{
				AddressText += AddressList.get(i);
				if(i > 0)
				{
					AddressText +=  ", ";
				}
				i--;
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		finally
		{
			try
			{ 
				if (stmt != null) 
				{
					stmt.close();
				}
			} 
			catch (Exception e)
			{
				System.out.println(e);
			}
			
			try
			{ 
				if (connection != null)
				{ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}
			catch(Exception ex2)
			{
				System.out.println(ex2);
			}
		}
		return AddressText;
	}
	
	public static String  parse(String Datatype, String Value)
	{
		StringTokenizer tok3=new StringTokenizer(Value, ":");
		int i = 0;
		String id = "", text = "", details = "";
		while(tok3.hasMoreElements())
		{
			if(i == 0)
			{
				id = tok3.nextElement() + "";
			}
			else if (i == 1)
			{
				text = tok3.nextElement() + "";
			}
			else if (i == 2)
			{
				details = tok3.nextElement() + "";
			}

			i ++;
		}
		if(Datatype.equals("id"))
		{
			return id;
		}
		else if(Datatype.equals("text"))
		{
			return text;
		}
		else if(Datatype.equals("details"))
		{
			return details;
		}
		return "";
	}
	
	public static String  parseID(String Value)
	{
		return parse("id", Value);
	}
	
	public static String  parseText(String Value)
	{
		return parse("text", Value);
	}
	
	public static String  parseDetails(String Value)
	{
		return parse("details", Value);
	}
	
	public static String parseTextAndDetails(String Value)
	{
		String Str = (String) GeoLocationDAO2.parseText(Value);
		String Details = (String) GeoLocationDAO2.parseDetails(Value);
		if(!Details.equals(""))
		{
			Str += ", " + Details;
		}
		return Str;
	}


}
