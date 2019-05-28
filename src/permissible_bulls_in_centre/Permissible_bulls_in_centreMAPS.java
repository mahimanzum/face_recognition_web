package permissible_bulls_in_centre;
import java.util.*; 


public class Permissible_bulls_in_centreMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Permissible_bulls_in_centreMAPS self = null;
	
	private Permissible_bulls_in_centreMAPS()
	{
		
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("date_of_entry".toLowerCase(), "Long");
		java_allfield_type_map.put("date_of_expiration".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("permissible_bulls_in_centre.date_of_entry".toLowerCase(), "Long");
		java_anyfield_search_map.put("permissible_bulls_in_centre.date_of_expiration".toLowerCase(), "Long");
		java_anyfield_search_map.put("permissible_bulls_in_centre.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("dateOfEntry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_DTO_map.put("dateOfExpiration".toLowerCase(), "dateOfExpiration".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("date_of_entry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_SQL_map.put("date_of_expiration".toLowerCase(), "dateOfExpiration".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Permissible_bulls_in_centreMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Permissible_bulls_in_centreMAPS ();
		}
		return self;
	}
	

}