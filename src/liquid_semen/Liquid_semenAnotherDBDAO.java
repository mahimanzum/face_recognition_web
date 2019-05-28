package liquid_semen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import databasemanager.DatabaseManager;
import pbReport.DateUtils;


public class Liquid_semenAnotherDBDAO 
{
	public static List<Integer>  getIDs (String DatabaseName, String id, String whereColumn)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<Integer> Options = new ArrayList<Integer>();
		
		
		try
		{
			
			String sql = "select  " + id + "  from " + DatabaseName + " where isDeleted = false";
			
			if(!whereColumn.equals(""))
			{
				sql += " and " + whereColumn;
			}
			
			System.out.println(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			while(rs.next())
			{
				int Option = rs.getInt("id");
				//System.out.println("found Option = " + Option);					 
				Options.add(Option);

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
		return Options;
	}
	
	public static List<Integer>  getbullIDs (int centreID)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<Integer> Options = new ArrayList<Integer>();
		long lToday = DateUtils.getToday12AM();
		
		
		try
		{
			
			String sql = "SELECT \r\n" + 
					"    bull.id\r\n" + 
					"FROM\r\n" + 
					"    bull_transfer\r\n" + 
					"        JOIN\r\n" + 
					"    bull ON bull_transfer.bull_type = bull.id\r\n" + 
					"WHERE\r\n" + 
					"    entry_date <= " + lToday
					+ " AND exit_date >= " + lToday +
					"        AND bull_transfer.approval_status_type = 1\r\n" + 
					"        AND bull_transfer.to_centre_type = " + centreID;
			
			
			System.out.println(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			while(rs.next())
			{
				int Option = rs.getInt("id");
				//System.out.println("found Option = " + Option);					 
				Options.add(Option);

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
		return Options;
	}

	
	public static String  getName (int id, String DatabaseName, String name, String matchColumn)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String Option = "";
		
		try
		{
			
			String sql = "select " + name + " from " + DatabaseName + " where " + matchColumn + " = " + id; 
			
			System.out.println(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			if(rs.next())
			{
				 //System.out.println(rs.getString("name_en") + "  " + rs.getInt("id"));
				Option = rs.getString(name);
				//System.out.println("found Option = " + Option);					 

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
		return Option;
	}
	
	
	
	public static String  getOptions (String Language, String HtmlType, String DatabaseName, String ID, String htmlClass, String htmlName,
	String idColumn, String nameColumn, String DefaultValue, String whereColumn, int centreType)
	{
		if(nameColumn.equals(""))
		{
			if(Language.equals("English"))
			{
				nameColumn = "name_en";
			}
			else if(Language.equals("Bangla"))
			{
				nameColumn = "name_bn";
			}
		}
		
		if(idColumn.equals(""))
		{
			idColumn = "id";
		}
		
		List<Integer> Options;
		if(centreType == -1)
		{
			Options= getIDs (DatabaseName, idColumn, whereColumn);
		}
		else
		{
			Options= getbullIDs (centreType);
		}
		
		String sOptions = "";
		String DefaultValueOption = "";
		
		if(!DefaultValue.equals(""))
		{
			if(DefaultValue.equals("any"))
			{
				DefaultValueOption = "";
			}
			else
			{
				DefaultValueOption = getName(Integer.parseInt(DefaultValue), DatabaseName, nameColumn, idColumn);
			}
			String htmlID = ID + "_0";
			if(HtmlType.equals("radio"))
			{
				sOptions += "<input type='radio' class = '" + htmlClass + "' name = '" + htmlName + "' id = '" + htmlID + "' value = '" + DefaultValue + "'>";
				sOptions += DefaultValueOption;
				sOptions += " <br>";				
			}
			else if(HtmlType.equals("select"))
			{
				sOptions += "<option class = '" + htmlClass + "' value = '" + DefaultValue + "'>";
				sOptions += DefaultValueOption;
				sOptions += "</option>";
			}
			
		}
			
	
		if(!Options.isEmpty())
		{
			for(int i = 0; i <Options.size(); i ++)
			{
				int nOption = Options.get(i);
				String name = getName(nOption, DatabaseName, nameColumn, idColumn);
				String value;
				

				value = nOption + "";
				
				
				if(value.equals(DefaultValue))
				{
					continue;
				}
				
							
				String htmlID = ID + "_" + (nOption + 1);
				if(HtmlType.equals("radio"))
				{
					sOptions += "<input type='radio' class = '" + htmlClass + "' name = '" + htmlName + "' id = '" + htmlID + "' value = '" + value + "'>";
					sOptions += name;
					sOptions += " <br>";					
				}
				else if(HtmlType.equals("select"))
				{
					sOptions += "<option class = '" + htmlClass + "' value = '" + value + "'>";
					sOptions += name;
					sOptions += "</option>";
				}
				
			
			}
		}
		//System.out.println("Options: " + sOptions);
		return sOptions;	
	}
	
	public static String  getOptions (String Language, String HtmlType, String DatabaseName, String ID, String htmlClass, String htmlName)
	{
		return getOptions (Language, HtmlType, DatabaseName, ID, htmlClass, htmlName, "", "", "", "", -1);
	}
	
	public static String  getOptions (String Language, String HtmlType, String DatabaseName, String ID, String htmlClass, String htmlName, String DefaultValue)
	{
		return getOptions (Language, HtmlType, DatabaseName, ID, htmlClass, htmlName, "", "", DefaultValue, "", -1);
	}
	
	public static String  getBull (String Language, String HtmlType, String DatabaseName, String ID, String htmlClass, String htmlName, int centreType)
	{
		return getOptions (Language, HtmlType, DatabaseName, ID, htmlClass, htmlName, "", "", "", "", centreType);
	}
	
	public static String  parseName (String Language, String Value)
	{
		StringTokenizer tok3=new StringTokenizer(Value, ":");
		int i = 0;
		String name_en = "", name_bn = "";
		while(tok3.hasMoreElements())
		{
			if(i == 0)
			{
				name_en = tok3.nextElement() + "";
			}
			else
			{
				name_bn = tok3.nextElement() + "";
			}

			i ++;
		}
		if(Language.equals("English"))
		{
			return name_en;
		}
		else
		{
			return name_bn;
		}
	}

}
