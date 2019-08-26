package language;

/**
 * @author Kayesh Parvez
 *
 */
public class LanguageTextDTO {
	public long ID;
	public long menuID;
	public String languageTextEnglish = "";	
	public String languageTextBangla = "";
	public String languageConstantPrefix = "";
	public String languageConstant = "";
	@Override
	public String toString() {
		return "LanguageTextDTO [ID=" + ID + ", languageGroupID=" + menuID + ", languageTextEnglish="
				+ languageTextEnglish + ", languageTextBangla=" + languageTextBangla + ", languageConstant="
				+ languageConstant + "]";
	}

	
	
}
