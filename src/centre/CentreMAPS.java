package centre;
import java.util.*; 


public class CentreMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static CentreMAPS self = null;
	
	private CentreMAPS()
	{
		
		java_allfield_type_map.put("name_en".toLowerCase(), "String");
		java_allfield_type_map.put("name_bn".toLowerCase(), "String");
		java_allfield_type_map.put("address".toLowerCase(), "String");
		java_allfield_type_map.put("contact_person".toLowerCase(), "String");
		java_allfield_type_map.put("phone_number".toLowerCase(), "String");
		java_allfield_type_map.put("email".toLowerCase(), "String");
		java_allfield_type_map.put("description".toLowerCase(), "String");

		java_anyfield_search_map.put("centre.name_en".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.name_bn".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.address".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.contact_person".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.phone_number".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.email".toLowerCase(), "String");
		java_anyfield_search_map.put("centre.description".toLowerCase(), "String");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("nameEn".toLowerCase(), "nameEn".toLowerCase());
		java_DTO_map.put("nameBn".toLowerCase(), "nameBn".toLowerCase());
		java_DTO_map.put("address".toLowerCase(), "address".toLowerCase());
		java_DTO_map.put("contactPerson".toLowerCase(), "contactPerson".toLowerCase());
		java_DTO_map.put("phoneNumber".toLowerCase(), "phoneNumber".toLowerCase());
		java_DTO_map.put("email".toLowerCase(), "email".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());
		java_DTO_map.put("description".toLowerCase(), "description".toLowerCase());

		java_SQL_map.put("name_en".toLowerCase(), "nameEn".toLowerCase());
		java_SQL_map.put("name_bn".toLowerCase(), "nameBn".toLowerCase());
		java_SQL_map.put("address".toLowerCase(), "address".toLowerCase());
		java_SQL_map.put("contact_person".toLowerCase(), "contactPerson".toLowerCase());
		java_SQL_map.put("phone_number".toLowerCase(), "phoneNumber".toLowerCase());
		java_SQL_map.put("email".toLowerCase(), "email".toLowerCase());
		java_SQL_map.put("description".toLowerCase(), "description".toLowerCase());
			
	}
	
	public static CentreMAPS GetInstance()
	{
		if(self == null)
		{
			self = new CentreMAPS ();
		}
		return self;
	}
	

}