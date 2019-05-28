package bull_breed_centre;
import java.util.*; 


public class Bull_breed_centreMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Bull_breed_centreMAPS self = null;
	
	private Bull_breed_centreMAPS()
	{
		
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("breed_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("grs_office".toLowerCase(), "Integer");
		java_allfield_type_map.put("grs_officer".toLowerCase(), "Long");
		java_allfield_type_map.put("info_file".toLowerCase(), "String");
		java_allfield_type_map.put("bull_image".toLowerCase(), "String");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("breed.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("breed.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull_breed_centre.grs_office".toLowerCase(), "Integer");
		java_anyfield_search_map.put("bull_breed_centre.grs_officer".toLowerCase(), "Long");
		java_anyfield_search_map.put("bull_breed_centre.info_file".toLowerCase(), "String");
		java_anyfield_search_map.put("bull_breed_centre.bull_image".toLowerCase(), "String");
		java_anyfield_search_map.put("bull_breed_centre.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("breedType".toLowerCase(), "breedType".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("grsOffice".toLowerCase(), "grsOffice".toLowerCase());
		java_DTO_map.put("grsOfficer".toLowerCase(), "grsOfficer".toLowerCase());
		java_DTO_map.put("infoFile".toLowerCase(), "infoFile".toLowerCase());
		java_DTO_map.put("bullImage".toLowerCase(), "bullImage".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("breed_type".toLowerCase(), "breedType".toLowerCase());
		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("grs_office".toLowerCase(), "grsOffice".toLowerCase());
		java_SQL_map.put("grs_officer".toLowerCase(), "grsOfficer".toLowerCase());
		java_SQL_map.put("info_file".toLowerCase(), "infoFile".toLowerCase());
		java_SQL_map.put("bull_image".toLowerCase(), "bullImage".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Bull_breed_centreMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Bull_breed_centreMAPS ();
		}
		return self;
	}
	

}