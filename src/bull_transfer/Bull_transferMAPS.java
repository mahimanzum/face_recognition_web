package bull_transfer;
import java.util.*; 


public class Bull_transferMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Bull_transferMAPS self = null;
	
	private Bull_transferMAPS()
	{
		
		java_allfield_type_map.put("date_of_transfer".toLowerCase(), "Long");
		java_allfield_type_map.put("bull_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("from_centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("to_centre_type".toLowerCase(), "Integer");
		java_allfield_type_map.put("entry_date".toLowerCase(), "Long");
		java_allfield_type_map.put("exit_date".toLowerCase(), "Long");
		java_allfield_type_map.put("description".toLowerCase(), "String");
		java_allfield_type_map.put("approval_status_type".toLowerCase(), "Integer");

		java_anyfield_search_map.put("bull_transfer.date_of_transfer".toLowerCase(), "Long");
		java_anyfield_search_map.put("bull.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("bull.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("bull_transfer.entry_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("bull_transfer.exit_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("bull_transfer.description".toLowerCase(), "String");
		java_anyfield_search_map.put("approval_status.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("approval_status.name_bn".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("dateOfTransfer".toLowerCase(), "dateOfTransfer".toLowerCase());
		java_DTO_map.put("bullType".toLowerCase(), "bullType".toLowerCase());
		java_DTO_map.put("fromCentreType".toLowerCase(), "fromCentreType".toLowerCase());
		java_DTO_map.put("toCentreType".toLowerCase(), "toCentreType".toLowerCase());
		java_DTO_map.put("entryDate".toLowerCase(), "entryDate".toLowerCase());
		java_DTO_map.put("exitDate".toLowerCase(), "exitDate".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());
		java_DTO_map.put("approvalStatusType".toLowerCase(), "approvalStatusType".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("date_of_transfer".toLowerCase(), "dateOfTransfer".toLowerCase());
		java_SQL_map.put("bull_type".toLowerCase(), "bullType".toLowerCase());
		java_SQL_map.put("from_centre_type".toLowerCase(), "fromCentreType".toLowerCase());
		java_SQL_map.put("to_centre_type".toLowerCase(), "toCentreType".toLowerCase());
		java_SQL_map.put("entry_date".toLowerCase(), "entryDate".toLowerCase());
		java_SQL_map.put("exit_date".toLowerCase(), "exitDate".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
		java_SQL_map.put("approval_status_type".toLowerCase(), "approvalStatusType".toLowerCase());
			
	}
	
	public static Bull_transferMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Bull_transferMAPS ();
		}
		return self;
	}
	

}