package pb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import databasemanager.DatabaseManager;


public class CommonDAO 
{
	public static List<Integer>  getIDs (String TableName, String id, String whereColumn)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		List<Integer> Options = new ArrayList<Integer>();
		
		
		try
		{
			
			String sql = "select  " + id + "  from " + TableName + " where isDeleted = false";
			
			if(!whereColumn.equals(""))
			{
				sql += " and " + whereColumn;
			}
			
			//System.out.println(sql);
			
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

	
	public static String  getName (int id, String TableName, String name, String matchColumn)
	{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String Option = "";
		
		try
		{
			
			String sql = "select " + name + " from " + TableName + " where " + matchColumn + " = " + id; 
			
			//System.out.println(sql);
			
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
	
	
	
	public static String  getOptions (String Language, String HtmlType, String TableName, String rawHtmlID, String htmlClass, String htmlName,
	String idColumn, String nameColumn, String DefaultValue, String whereColumn)
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
		
		List<Integer> Options = getIDs (TableName, idColumn, whereColumn);
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
				DefaultValueOption = getName(Integer.parseInt(DefaultValue), TableName, nameColumn, idColumn);
			}
			String htmlID = rawHtmlID + "_0";
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
				String name = getName(nOption, TableName, nameColumn, idColumn);
				String value;
				

				value = nOption + "";
				
				
				if(value.equals(DefaultValue))
				{
					continue;
				}
				
							
				String htmlID = rawHtmlID + "_" + (nOption + 1);
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
	
	public static String  getOptions (String Language, String HtmlType, String TableName, String rawHtmlID, String htmlClass, String htmlName)
	{
		return getOptions (Language, HtmlType, TableName, rawHtmlID, htmlClass, htmlName, "", "", "", "");
	}
	
	public static String  getOptions (String Language, String HtmlType, String TableName, String rawHtmlID, String htmlClass, String htmlName, String DefaultValue)
	{
		return getOptions (Language, HtmlType, TableName, rawHtmlID, htmlClass, htmlName, "", "", DefaultValue, "");
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
