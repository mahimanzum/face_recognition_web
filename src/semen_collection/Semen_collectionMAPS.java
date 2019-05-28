package semen_collection;
import java.util.*; 


public class Semen_collectionMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Semen_collectionMAPS self = null;
	
	private Semen_collectionMAPS()
	{
		
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("no_of_dose".toLowerCase(), "Integer");
		java_allfield_type_map.put("volume".toLowerCase(), "Integer");
		java_allfield_type_map.put("density".toLowerCase(), "Integer");
		java_allfield_type_map.put("progressive_mortality".toLowerCase(), "Integer");
		java_allfield_type_map.put("color_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("transaction_date".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("semen_collection.no_of_dose".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_collection.volume".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_collection.density".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_collection.progressive_mortality".toLowerCase(), "Integer");
		java_anyfield_search_map.put("color.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("color.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("semen_collection.transaction_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("semen_collection.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("noOfDose".toLowerCase(), "noOfDose".toLowerCase());
		java_DTO_map.put("volume".toLowerCase(), "volume".toLowerCase());
		java_DTO_map.put("density".toLowerCase(), "density".toLowerCase());
		java_DTO_map.put("progressiveMortality".toLowerCase(), "progressiveMortality".toLowerCase());
		java_DTO_map.put("colorType".toLowerCase(), "colorType".toLowerCase());
		java_DTO_map.put("transactionDate".toLowerCase(), "transactionDate".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("no_of_dose".toLowerCase(), "noOfDose".toLowerCase());
		java_SQL_map.put("volume".toLowerCase(), "volume".toLowerCase());
		java_SQL_map.put("density".toLowerCase(), "density".toLowerCase());
		java_SQL_map.put("progressive_mortality".toLowerCase(), "progressiveMortality".toLowerCase());
		java_SQL_map.put("color_type".toLowerCase(), "colorType".toLowerCase());
		java_SQL_map.put("transaction_date".toLowerCase(), "transactionDate".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Semen_collectionMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Semen_collectionMAPS ();
		}
		return self;
	}
	

}