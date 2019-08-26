package imported_semen;
import java.util.*; 


public class Imported_semenMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Imported_semenMAPS self = null;
	
	private Imported_semenMAPS()
	{
		
		java_allfield_type_map.put("bull_tag".toLowerCase(), "String");
		java_allfield_type_map.put("date_of_birth".toLowerCase(), "Long");
		java_allfield_type_map.put("dam".toLowerCase(), "String");
		java_allfield_type_map.put("sire".toLowerCase(), "String");
		java_allfield_type_map.put("maternal_grand_dam".toLowerCase(), "String");
		java_allfield_type_map.put("maternal_grand_sire".toLowerCase(), "String");
		java_allfield_type_map.put("paternal_grand_dam".toLowerCase(), "String");
		java_allfield_type_map.put("paternal_grand_sire".toLowerCase(), "String");
		java_allfield_type_map.put("milk_yield_of_dam".toLowerCase(), "Integer");
		java_allfield_type_map.put("milk_yield_of_sires_dam".toLowerCase(), "Integer");
		java_allfield_type_map.put("progeny_milk_yield".toLowerCase(), "Integer");
		java_allfield_type_map.put("breed".toLowerCase(), "String");
		java_allfield_type_map.put("date_of_entry".toLowerCase(), "Long");
		java_allfield_type_map.put("received_amount".toLowerCase(), "Integer");
		java_allfield_type_map.put("distributed_amount".toLowerCase(), "Integer");
		java_allfield_type_map.put("to_whom_distributed".toLowerCase(), "String");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("imported_semen.bull_tag".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.date_of_birth".toLowerCase(), "Long");
		java_anyfield_search_map.put("imported_semen.dam".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.sire".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.maternal_grand_dam".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.maternal_grand_sire".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.paternal_grand_dam".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.paternal_grand_sire".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.milk_yield_of_dam".toLowerCase(), "Integer");
		java_anyfield_search_map.put("imported_semen.milk_yield_of_sires_dam".toLowerCase(), "Integer");
		java_anyfield_search_map.put("imported_semen.progeny_milk_yield".toLowerCase(), "Integer");
		java_anyfield_search_map.put("imported_semen.breed".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.date_of_entry".toLowerCase(), "Long");
		java_anyfield_search_map.put("imported_semen.received_amount".toLowerCase(), "Integer");
		java_anyfield_search_map.put("imported_semen.distributed_amount".toLowerCase(), "Integer");
		java_anyfield_search_map.put("imported_semen.to_whom_distributed".toLowerCase(), "String");
		java_anyfield_search_map.put("imported_semen.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("bullTag".toLowerCase(), "bullTag".toLowerCase());
		java_DTO_map.put("dateOfBirth".toLowerCase(), "dateOfBirth".toLowerCase());
		java_DTO_map.put("dam".toLowerCase(), "dam".toLowerCase());
		java_DTO_map.put("sire".toLowerCase(), "sire".toLowerCase());
		java_DTO_map.put("maternalGrandDam".toLowerCase(), "maternalGrandDam".toLowerCase());
		java_DTO_map.put("maternalGrandSire".toLowerCase(), "maternalGrandSire".toLowerCase());
		java_DTO_map.put("paternalGrandDam".toLowerCase(), "paternalGrandDam".toLowerCase());
		java_DTO_map.put("paternalGrandSire".toLowerCase(), "paternalGrandSire".toLowerCase());
		java_DTO_map.put("milkYieldOfDam".toLowerCase(), "milkYieldOfDam".toLowerCase());
		java_DTO_map.put("milkYieldOfSiresDam".toLowerCase(), "milkYieldOfSiresDam".toLowerCase());
		java_DTO_map.put("progenyMilkYield".toLowerCase(), "progenyMilkYield".toLowerCase());
		java_DTO_map.put("breed".toLowerCase(), "breed".toLowerCase());
		java_DTO_map.put("dateOfEntry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_DTO_map.put("receivedAmount".toLowerCase(), "receivedAmount".toLowerCase());
		java_DTO_map.put("distributedAmount".toLowerCase(), "distributedAmount".toLowerCase());
		java_DTO_map.put("toWhomDistributed".toLowerCase(), "toWhomDistributed".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("bull_tag".toLowerCase(), "bullTag".toLowerCase());
		java_SQL_map.put("date_of_birth".toLowerCase(), "dateOfBirth".toLowerCase());
		java_SQL_map.put("dam".toLowerCase(), "dam".toLowerCase());
		java_SQL_map.put("sire".toLowerCase(), "sire".toLowerCase());
		java_SQL_map.put("maternal_grand_dam".toLowerCase(), "maternalGrandDam".toLowerCase());
		java_SQL_map.put("maternal_grand_sire".toLowerCase(), "maternalGrandSire".toLowerCase());
		java_SQL_map.put("paternal_grand_dam".toLowerCase(), "paternalGrandDam".toLowerCase());
		java_SQL_map.put("paternal_grand_sire".toLowerCase(), "paternalGrandSire".toLowerCase());
		java_SQL_map.put("milk_yield_of_dam".toLowerCase(), "milkYieldOfDam".toLowerCase());
		java_SQL_map.put("milk_yield_of_sires_dam".toLowerCase(), "milkYieldOfSiresDam".toLowerCase());
		java_SQL_map.put("progeny_milk_yield".toLowerCase(), "progenyMilkYield".toLowerCase());
		java_SQL_map.put("breed".toLowerCase(), "breed".toLowerCase());
		java_SQL_map.put("date_of_entry".toLowerCase(), "dateOfEntry".toLowerCase());
		java_SQL_map.put("received_amount".toLowerCase(), "receivedAmount".toLowerCase());
		java_SQL_map.put("distributed_amount".toLowerCase(), "distributedAmount".toLowerCase());
		java_SQL_map.put("to_whom_distributed".toLowerCase(), "toWhomDistributed".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Imported_semenMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Imported_semenMAPS ();
		}
		return self;
	}
	

}