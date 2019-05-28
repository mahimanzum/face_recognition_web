package progeny_record;
import java.util.*; 


public class Progeny_recordMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Progeny_recordMAPS self = null;
	
	private Progeny_recordMAPS()
	{
		
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("number_of_male_calfs".toLowerCase(), "Integer");
		java_allfield_type_map.put("number_of_female_calfs".toLowerCase(), "Integer");
		java_allfield_type_map.put("date_of_entry".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("progeny_record.number_of_male_calfs".toLowerCase(), "Integer");
		java_anyfield_search_map.put("progeny_record.number_of_female_calfs".toLowerCase(), "Integer");
		java_anyfield_search_map.put("progeny_record.date_of_entry".toLowerCase(), "Long");
		java_anyfield_search_map.put("progeny_record.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("numberOfMaleCalfs".toLowerCase(), "numberOfMaleCalfs".toLowerCase());
		java_DTO_map.put("numberOfFemaleCalfs".toLowerCase(), "numberOfFemaleCalfs".toLowerCase());
		java_DTO_map.put("dateOfEntry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("number_of_male_calfs".toLowerCase(), "numberOfMaleCalfs".toLowerCase());
		java_SQL_map.put("number_of_female_calfs".toLowerCase(), "numberOfFemaleCalfs".toLowerCase());
		java_SQL_map.put("date_of_entry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Progeny_recordMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Progeny_recordMAPS ();
		}
		return self;
	}
	

}