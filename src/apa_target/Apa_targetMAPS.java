package apa_target;
import java.util.*; 


public class Apa_targetMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Apa_targetMAPS self = null;
	
	private Apa_targetMAPS()
	{
		
		java_allfield_type_map.put("semen_collection".toLowerCase(), "Integer");
		java_allfield_type_map.put("artificial_insemenation".toLowerCase(), "Integer");
		java_allfield_type_map.put("progeny".toLowerCase(), "Integer");
		java_allfield_type_map.put("candidate_bull_production".toLowerCase(), "Integer");
		java_allfield_type_map.put("description".toLowerCase(), "String");
		java_allfield_type_map.put("entry_date".toLowerCase(), "Long");

		java_anyfield_search_map.put("apa_target.semen_collection".toLowerCase(), "Integer");
		java_anyfield_search_map.put("apa_target.artificial_insemenation".toLowerCase(), "Integer");
		java_anyfield_search_map.put("apa_target.progeny".toLowerCase(), "Integer");
		java_anyfield_search_map.put("apa_target.candidate_bull_production".toLowerCase(), "Integer");
		java_anyfield_search_map.put("apa_target.description".toLowerCase(), "String");
		java_anyfield_search_map.put("apa_target.entry_date".toLowerCase(), "Long");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("semenCollection".toLowerCase(), "semenCollection".toLowerCase());
		java_DTO_map.put("artificialInsemenation".toLowerCase(), "artificialInsemenation".toLowerCase());
		java_DTO_map.put("progeny".toLowerCase(), "progeny".toLowerCase());
		java_DTO_map.put("candidateBullProduction".toLowerCase(), "candidateBullProduction".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("entryDate".toLowerCase(), "entryDate".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("semen_collection".toLowerCase(), "semenCollection".toLowerCase());
		java_SQL_map.put("artificial_insemenation".toLowerCase(), "artificialInsemenation".toLowerCase());
		java_SQL_map.put("progeny".toLowerCase(), "progeny".toLowerCase());
		java_SQL_map.put("candidate_bull_production".toLowerCase(), "candidateBullProduction".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
		java_SQL_map.put("entry_date".toLowerCase(), "entryDate".toLowerCase());
			
	}
	
	public static Apa_targetMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Apa_targetMAPS ();
		}
		return self;
	}
	

}