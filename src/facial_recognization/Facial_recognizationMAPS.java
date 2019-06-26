package facial_recognization;
import java.util.*; 


public class Facial_recognizationMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Facial_recognizationMAPS self = null;
	
	private Facial_recognizationMAPS()
	{
		
		java_allfield_type_map.put("name".toLowerCase(), "String");
		java_allfield_type_map.put("address".toLowerCase(), "String");
		java_allfield_type_map.put("phone".toLowerCase(), "String");
		java_allfield_type_map.put("email".toLowerCase(), "String");
		java_allfield_type_map.put("image".toLowerCase(), "String");

		java_anyfield_search_map.put("facial_recognization.name".toLowerCase(), "String");
		java_anyfield_search_map.put("facial_recognization.address".toLowerCase(), "String");
		java_anyfield_search_map.put("facial_recognization.phone".toLowerCase(), "String");
		java_anyfield_search_map.put("facial_recognization.email".toLowerCase(), "String");
		java_anyfield_search_map.put("facial_recognization.image".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("name".toLowerCase(), "name".toLowerCase());
		java_DTO_map.put("address".toLowerCase(), "address".toLowerCase());
		java_DTO_map.put("phone".toLowerCase(), "phone".toLowerCase());
		java_DTO_map.put("email".toLowerCase(), "email".toLowerCase());
		java_DTO_map.put("image".toLowerCase(), "image".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("name".toLowerCase(), "name".toLowerCase());
		java_SQL_map.put("address".toLowerCase(), "address".toLowerCase());
		java_SQL_map.put("phone".toLowerCase(), "phone".toLowerCase());
		java_SQL_map.put("email".toLowerCase(), "email".toLowerCase());
		java_SQL_map.put("image".toLowerCase(), "image".toLowerCase());
			
	}
	
	public static Facial_recognizationMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Facial_recognizationMAPS ();
		}
		return self;
	}
	

}