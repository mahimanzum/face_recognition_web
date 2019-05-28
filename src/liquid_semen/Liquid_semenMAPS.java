package liquid_semen;
import java.util.*; 


public class Liquid_semenMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Liquid_semenMAPS self = null;
	
	private Liquid_semenMAPS()
	{
		
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("no_of_dose".toLowerCase(), "Integer");
		java_allfield_type_map.put("volume".toLowerCase(), "Integer");
		java_allfield_type_map.put("density".toLowerCase(), "Integer");
		java_allfield_type_map.put("progressive_motility".toLowerCase(), "Integer");
		java_allfield_type_map.put("color_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("collection_distribution_date".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("liquid_semen.no_of_dose".toLowerCase(), "Integer");
		java_anyfield_search_map.put("liquid_semen.volume".toLowerCase(), "Integer");
		java_anyfield_search_map.put("liquid_semen.density".toLowerCase(), "Integer");
		java_anyfield_search_map.put("liquid_semen.progressive_motility".toLowerCase(), "Integer");
		java_anyfield_search_map.put("color.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("color.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("liquid_semen.collection_distribution_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("liquid_semen.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("noOfDose".toLowerCase(), "noOfDose".toLowerCase());
		java_DTO_map.put("volume".toLowerCase(), "volume".toLowerCase());
		java_DTO_map.put("density".toLowerCase(), "density".toLowerCase());
		java_DTO_map.put("progressiveMotility".toLowerCase(), "progressiveMotility".toLowerCase());
		java_DTO_map.put("colorType".toLowerCase(), "colorType".toLowerCase());
		java_DTO_map.put("collectionDistributionDate".toLowerCase(), "collectionDistributionDate".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("no_of_dose".toLowerCase(), "noOfDose".toLowerCase());
		java_SQL_map.put("volume".toLowerCase(), "volume".toLowerCase());
		java_SQL_map.put("density".toLowerCase(), "density".toLowerCase());
		java_SQL_map.put("progressive_motility".toLowerCase(), "progressiveMotility".toLowerCase());
		java_SQL_map.put("color_type".toLowerCase(), "colorType".toLowerCase());
		java_SQL_map.put("collection_distribution_date".toLowerCase(), "collectionDistributionDate".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Liquid_semenMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Liquid_semenMAPS ();
		}
		return self;
	}
	

}