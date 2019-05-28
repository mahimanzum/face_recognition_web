package grs_top_layer;
import java.util.*; 


public class Grs_top_layerMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Grs_top_layerMAPS self = null;
	
	private Grs_top_layerMAPS()
	{
		
		java_allfield_type_map.put("name_en".toLowerCase(), "String");
		java_allfield_type_map.put("name_bn".toLowerCase(), "String");
		java_allfield_type_map.put("layer_number".toLowerCase(), "Integer");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("grs_top_layer.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("grs_top_layer.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("grs_top_layer.layer_number".toLowerCase(), "Integer");
		java_anyfield_search_map.put("grs_top_layer.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("nameEn".toLowerCase(), "nameEn".toLowerCase());
		java_DTO_map.put("nameBn".toLowerCase(), "nameBn".toLowerCase());
		java_DTO_map.put("layerNumber".toLowerCase(), "layerNumber".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("name_en".toLowerCase(), "nameEn".toLowerCase());
		java_SQL_map.put("name_bn".toLowerCase(), "nameBn".toLowerCase());
		java_SQL_map.put("layer_number".toLowerCase(), "layerNumber".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Grs_top_layerMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Grs_top_layerMAPS ();
		}
		return self;
	}
	

}