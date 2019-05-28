package theme;
import java.util.*; 


public class ThemeDTO {

    public long iD;
    public String themeName;
    public String directory;
    public boolean isApplied;
    public boolean isDeleted;

	public HashMap<String, String> java_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_custom_search_map = new HashMap<String, String>();
	
	public ThemeDTO()
	{		
		java_type_map.put("theme_name".toLowerCase(), "String");
		java_type_map.put("directory".toLowerCase(), "String");
		java_type_map.put("isApplied".toLowerCase(), "Boolean");

		java_custom_search_map.put("theme_name".toLowerCase(), "String");
		java_custom_search_map.put("directory".toLowerCase(), "String");
		java_custom_search_map.put("isApplied".toLowerCase(), "Boolean");
	}
	
    @Override
	public String toString() {
            return "$ThemeDTO[" +
            " iD = " + iD +
            " themeName = " + themeName +
            " directory = " + directory +
            " isApplied = " + isApplied +
            " isDeleted = " + isDeleted +
            "]";
    }

}