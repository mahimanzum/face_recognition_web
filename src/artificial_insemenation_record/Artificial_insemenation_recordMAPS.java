package artificial_insemenation_record;
import java.util.*; 


public class Artificial_insemenation_recordMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Artificial_insemenation_recordMAPS self = null;
	
	private Artificial_insemenation_recordMAPS()
	{
		
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("no_of_AI".toLowerCase(), "Integer");
		java_allfield_type_map.put("entry_date".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("artificial_insemenation_record.no_of_AI".toLowerCase(), "Integer");
		java_anyfield_search_map.put("artificial_insemenation_record.entry_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("artificial_insemenation_record.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("noOfAI".toLowerCase(), "noOfAI".toLowerCase());
		java_DTO_map.put("entryDate".toLowerCase(), "entryDate".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("no_of_AI".toLowerCase(), "noOfAI".toLowerCase());
		java_SQL_map.put("entry_date".toLowerCase(), "entryDate".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Artificial_insemenation_recordMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Artificial_insemenation_recordMAPS ();
		}
		return self;
	}
	

}