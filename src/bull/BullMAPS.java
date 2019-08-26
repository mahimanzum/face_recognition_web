package bull;
import java.util.*; 


public class BullMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static BullMAPS self = null;
	
	private BullMAPS()
	{
		
		java_allfield_type_map.put("name_en".toLowerCase(), "String");
		java_allfield_type_map.put("name_bn".toLowerCase(), "String");
		java_allfield_type_map.put("date_of_birth".toLowerCase(), "Long");
		java_allfield_type_map.put("breed_type".toLowerCase(), "Long");
		java_allfield_type_map.put("status_type".toLowerCase(), "Long");
		java_allfield_type_map.put("dam".toLowerCase(), "String");
		java_allfield_type_map.put("sire".toLowerCase(), "String");
		java_allfield_type_map.put("maternal_grand_dam".toLowerCase(), "String");
		java_allfield_type_map.put("maternal_grand_sire".toLowerCase(), "String");
		java_allfield_type_map.put("paternal_grand_dam".toLowerCase(), "String");
		java_allfield_type_map.put("paternal_grand_sire".toLowerCase(), "String");
		java_allfield_type_map.put("milk_yield_of_dam".toLowerCase(), "Integer");
		java_allfield_type_map.put("milk_yield_of_sires_dam".toLowerCase(), "Integer");
		java_allfield_type_map.put("progeny_milk_yield".toLowerCase(), "Integer");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.date_of_birth".toLowerCase(), "Long");
		java_anyfield_search_map.put("breed.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("breed.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("status.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("status.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.dam".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.sire".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.maternal_grand_dam".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.maternal_grand_sire".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.paternal_grand_dam".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.paternal_grand_sire".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.milk_yield_of_dam".toLowerCase(), "Integer");
		java_anyfield_search_map.put("bull.milk_yield_of_sires_dam".toLowerCase(), "Integer");
		java_anyfield_search_map.put("bull.progeny_milk_yield".toLowerCase(), "Integer");
		java_anyfield_search_map.put("bull.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("nameEn".toLowerCase(), "nameEn".toLowerCase());
		java_DTO_map.put("nameBn".toLowerCase(), "nameBn".toLowerCase());
		java_DTO_map.put("dateOfBirth".toLowerCase(), "dateOfBirth".toLowerCase());
		java_DTO_map.put("breedType".toLowerCase(), "breedType".toLowerCase());
		java_DTO_map.put("statusType".toLowerCase(), "statusType".toLowerCase());
		java_DTO_map.put("dam".toLowerCase(), "dam".toLowerCase());
		java_DTO_map.put("sire".toLowerCase(), "sire".toLowerCase());
		java_DTO_map.put("maternalGrandDam".toLowerCase(), "maternalGrandDam".toLowerCase());
		java_DTO_map.put("maternalGrandSire".toLowerCase(), "maternalGrandSire".toLowerCase());
		java_DTO_map.put("paternalGrandDam".toLowerCase(), "paternalGrandDam".toLowerCase());
		java_DTO_map.put("paternalGrandSire".toLowerCase(), "paternalGrandSire".toLowerCase());
		java_DTO_map.put("milkYieldOfDam".toLowerCase(), "milkYieldOfDam".toLowerCase());
		java_DTO_map.put("milkYieldOfSiresDam".toLowerCase(), "milkYieldOfSiresDam".toLowerCase());
		java_DTO_map.put("progenyMilkYield".toLowerCase(), "progenyMilkYield".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("name_en".toLowerCase(), "nameEn".toLowerCase());
		java_SQL_map.put("name_bn".toLowerCase(), "nameBn".toLowerCase());
		java_SQL_map.put("date_of_birth".toLowerCase(), "dateOfBirth".toLowerCase());
		java_SQL_map.put("breed_type".toLowerCase(), "breedType".toLowerCase());
		java_SQL_map.put("status_type".toLowerCase(), "statusType".toLowerCase());
		java_SQL_map.put("dam".toLowerCase(), "dam".toLowerCase());
		java_SQL_map.put("sire".toLowerCase(), "sire".toLowerCase());
		java_SQL_map.put("maternal_grand_dam".toLowerCase(), "maternalGrandDam".toLowerCase());
		java_SQL_map.put("maternal_grand_sire".toLowerCase(), "maternalGrandSire".toLowerCase());
		java_SQL_map.put("paternal_grand_dam".toLowerCase(), "paternalGrandDam".toLowerCase());
		java_SQL_map.put("paternal_grand_sire".toLowerCase(), "paternalGrandSire".toLowerCase());
		java_SQL_map.put("milk_yield_of_dam".toLowerCase(), "milkYieldOfDam".toLowerCase());
		java_SQL_map.put("milk_yield_of_sires_dam".toLowerCase(), "milkYieldOfSiresDam".toLowerCase());
		java_SQL_map.put("progeny_milk_yield".toLowerCase(), "progenyMilkYield".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static BullMAPS GetInstance()
	{
		if(self == null)
		{
			self = new BullMAPS ();
		}
		return self;
	}
	

}