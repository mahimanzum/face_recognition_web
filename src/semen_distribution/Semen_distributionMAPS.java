package semen_distribution;
import java.util.*; 


public class Semen_distributionMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Semen_distributionMAPS self = null;
	
	private Semen_distributionMAPS()
	{
		
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("no_of_dose".toLowerCase(), "Integer");
		java_allfield_type_map.put("requisition_id".toLowerCase(), "Long");
		java_allfield_type_map.put("group_id".toLowerCase(), "Integer");
		java_allfield_type_map.put("transaction_date".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("semen_distribution.no_of_dose".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_distribution.requisition_id".toLowerCase(), "Long");
		java_anyfield_search_map.put("semen_distribution.group_id".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_distribution.transaction_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("semen_distribution.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("noOfDose".toLowerCase(), "noOfDose".toLowerCase());
		java_DTO_map.put("requisitionId".toLowerCase(), "requisitionId".toLowerCase());
		java_DTO_map.put("groupId".toLowerCase(), "groupId".toLowerCase());
		java_DTO_map.put("transactionDate".toLowerCase(), "transactionDate".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("no_of_dose".toLowerCase(), "noOfDose".toLowerCase());
		java_SQL_map.put("requisition_id".toLowerCase(), "requisitionId".toLowerCase());
		java_SQL_map.put("group_id".toLowerCase(), "groupId".toLowerCase());
		java_SQL_map.put("transaction_date".toLowerCase(), "transactionDate".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Semen_distributionMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Semen_distributionMAPS ();
		}
		return self;
	}
	

}