package stick;
import java.util.*; 


public class StickMAPS 
{

	public HashMap<String, String> java_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_custom_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static StickMAPS self = null;
	
	private StickMAPS()
	{
		
		java_type_map.put("name_en".toLowerCase(), "String");
		java_type_map.put("name_bn".toLowerCase(), "String");

		java_custom_search_map.put("name_en".toLowerCase(), "String");
		java_custom_search_map.put("name_bn".toLowerCase(), "String");
		java_custom_search_map.put("semen_collection_id".toLowerCase(), "Long");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("nameEn".toLowerCase(), "nameEn".toLowerCase());
		java_DTO_map.put("nameBn".toLowerCase(), "nameBn".toLowerCase());
		java_DTO_map.put("semenCollectionId".toLowerCase(), "semenCollectionId".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("name_en".toLowerCase(), "nameEn".toLowerCase());
		java_SQL_map.put("name_bn".toLowerCase(), "nameBn".toLowerCase());
		java_SQL_map.put("semen_collection_id".toLowerCase(), "semenCollectionId".toLowerCase());
			
	}
	
	public static StickMAPS GetInstance()
	{
		if(self == null)
		{
			self = new StickMAPS ();
		}
		return self;
	}
	

}