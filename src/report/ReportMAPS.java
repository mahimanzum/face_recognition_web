package report;
import java.util.*; 


public class ReportMAPS 
{

	public HashMap<String, String> java_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_custom_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map  = new HashMap<String, String>();
	
	private static ReportMAPS self = null;
	
	private ReportMAPS()
	{
		
		java_type_map.put("reporting_date".toLowerCase(), "Long");
		java_type_map.put("reporter_id".toLowerCase(), "Long");
		java_type_map.put("vehicle_id".toLowerCase(), "Long");
		java_type_map.put("lost_date".toLowerCase(), "Long");
		java_type_map.put("found_date".toLowerCase(), "Long");
		java_type_map.put("blog".toLowerCase(), "String");

		java_custom_search_map.put("reporting_date".toLowerCase(), "Long");
		java_custom_search_map.put("reporter_id".toLowerCase(), "Long");
		java_custom_search_map.put("vehicle_id".toLowerCase(), "Long");
		java_custom_search_map.put("lost_date".toLowerCase(), "Long");
		java_custom_search_map.put("found_date".toLowerCase(), "Long");
		java_custom_search_map.put("blog".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("reportingDate".toLowerCase(), "reportingDate".toLowerCase());
		java_DTO_map.put("reporterId".toLowerCase(), "reporterId".toLowerCase());
		java_DTO_map.put("vehicleId".toLowerCase(), "vehicleId".toLowerCase());
		java_DTO_map.put("lostDate".toLowerCase(), "lostDate".toLowerCase());
		java_DTO_map.put("foundDate".toLowerCase(), "foundDate".toLowerCase());
		java_DTO_map.put("statusId".toLowerCase(), "statusId".toLowerCase());
		java_DTO_map.put("thanaAddress".toLowerCase(), "thanaAddress".toLowerCase());
		java_DTO_map.put("blog".toLowerCase(), "blog".toLowerCase());
		java_DTO_map.put("image1".toLowerCase(), "image1".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());
			
	}
	
	public static ReportMAPS GetInstance()
	{
		if(self == null)
		{
			self = new ReportMAPS ();
		}
		return self;
	}
	

}