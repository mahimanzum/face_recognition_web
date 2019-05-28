package color;
import java.util.*; 


public class ColorMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static ColorMAPS self = null;
	
	private ColorMAPS()
	{
		
		java_allfield_type_map.put("name_en".toLowerCase(), "String");
		java_allfield_type_map.put("name_bn".toLowerCase(), "String");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("color.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("color.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("color.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("nameEn".toLowerCase(), "nameEn".toLowerCase());
		java_DTO_map.put("nameBn".toLowerCase(), "nameBn".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());

		java_SQL_map.put("name_en".toLowerCase(), "nameEn".toLowerCase());
		java_SQL_map.put("name_bn".toLowerCase(), "nameBn".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static ColorMAPS GetInstance()
	{
		if(self == null)
		{
			self = new ColorMAPS ();
		}
		return self;
	}
	

}