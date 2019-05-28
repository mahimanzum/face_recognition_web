package candidate_bull_production;
import java.util.*; 


public class Candidate_bull_productionMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Candidate_bull_productionMAPS self = null;
	
	private Candidate_bull_productionMAPS()
	{
		
		java_allfield_type_map.put("production_date".toLowerCase(), "Long");
		java_allfield_type_map.put("number_of_candidate_bulls".toLowerCase(), "Integer");
		java_allfield_type_map.put("source_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("candidate_bull_production.production_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("candidate_bull_production.number_of_candidate_bulls".toLowerCase(), "Integer");
		java_anyfield_search_map.put("source.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("source.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("candidate_bull_production.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("productionDate".toLowerCase(), "productionDate".toLowerCase());
		java_DTO_map.put("numberOfCandidateBulls".toLowerCase(), "numberOfCandidateBulls".toLowerCase());
		java_DTO_map.put("sourceType".toLowerCase(), "sourceType".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());

		java_SQL_map.put("production_date".toLowerCase(), "productionDate".toLowerCase());
		java_SQL_map.put("number_of_candidate_bulls".toLowerCase(), "numberOfCandidateBulls".toLowerCase());
		java_SQL_map.put("source_type".toLowerCase(), "sourceType".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Candidate_bull_productionMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Candidate_bull_productionMAPS ();
		}
		return self;
	}
	

}