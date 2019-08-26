package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databasemanager.DatabaseManager;


public class ReportAnotherDBDAO 
{
	public static List<Integer>  getIDs (String DatabaseName)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<Integer> Options = new ArrayList<Integer>();
		
		try
		{
			
			String sql = "select  id  from " + DatabaseName; 
			
			System.out.println(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			while(rs.next())
			{
				int Option = rs.getInt("id");
				System.out.println("found Option = " + Option);					 
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
	
	public static String  getName (String Language, int id, String DatabaseName)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String Option = "";
		String name = "";
		if(Language.equals("English"))
		{
			name = "name_en";
		}
		else
		{
			name = "name_bn";
		}
		try
		{
			
			String sql = "select " + name + " from " + DatabaseName + " where id = " + id; 
			
			System.out.println(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);
			
			

			if(rs.next())
			{
				 //System.out.println(rs.getString("name_en") + "  " + rs.getInt("id"));
				Option = rs.getString(name);
				System.out.println("found Option = " + Option);					 

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
	
	
	public static String  getOptions (String Language, String HtmlType, String DatabaseName, String ID, String htmlClass, String htmlName)
	{
		List<Integer> Options = getIDs (DatabaseName);
		String sOptions = "";
	
		if(!Options.isEmpty())
		{
			for(int i = 0; i <Options.size(); i ++)
			{
				int nOption = Options.get(i);
				String name = getName(Language, nOption, DatabaseName);
				String htmlID = ID + "_" + nOption;
				if(HtmlType.equals("radio"))
				{
					sOptions+= "<input type='radio' class = '" + htmlClass + "' name = '" + htmlName + "' id = '" + htmlID + "' value = '" + nOption + "'>";
					sOptions += name;
					sOptions += " <br>";
				}
				else if(HtmlType.equals("select"))
				{
					sOptions+= "<option class = '" + htmlClass + "' value = '" + nOption + "'>" + name + " <br>";
				}
			
			}
		}
		return sOptions;	
	}

}
