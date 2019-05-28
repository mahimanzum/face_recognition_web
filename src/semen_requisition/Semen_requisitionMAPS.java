package semen_requisition;
import java.util.*; 


public class Semen_requisitionMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Semen_requisitionMAPS self = null;
	
	private Semen_requisitionMAPS()
	{
		
		java_allfield_type_map.put("group_id".toLowerCase(), "Integer");
		java_allfield_type_map.put("centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("breed_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("no_of_dose".toLowerCase(), "Integer");
		java_allfield_type_map.put("requisition_date".toLowerCase(), "Long");
		java_allfield_type_map.put("isDelivered".toLowerCase(), "Boolean");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("semen_requisition.group_id".toLowerCase(), "Integer");
		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("breed.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("breed.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("semen_requisition.no_of_dose".toLowerCase(), "Integer");
		java_anyfield_search_map.put("semen_requisition.requisition_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("semen_requisition.isDelivered".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("semen_requisition.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("groupId".toLowerCase(), "groupId".toLowerCase());
		java_DTO_map.put("centreType".toLowerCase(), "centreType".toLowerCase());
		java_DTO_map.put("breedType".toLowerCase(), "breedType".toLowerCase());
		java_DTO_map.put("noOfDose".toLowerCase(), "noOfDose".toLowerCase());
		java_DTO_map.put("requisitionDate".toLowerCase(), "requisitionDate".toLowerCase());
		java_DTO_map.put("isDelivered".toLowerCase(), "isDelivered".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("group_id".toLowerCase(), "groupId".toLowerCase());
		java_SQL_map.put("centre_type".toLowerCase(), "centreType".toLowerCase());
		java_SQL_map.put("breed_type".toLowerCase(), "breedType".toLowerCase());
		java_SQL_map.put("no_of_dose".toLowerCase(), "noOfDose".toLowerCase());
		java_SQL_map.put("requisition_date".toLowerCase(), "requisitionDate".toLowerCase());
		java_SQL_map.put("isDelivered".toLowerCase(), "isDelivered".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static Semen_requisitionMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Semen_requisitionMAPS ();
		}
		return self;
	}
	

}