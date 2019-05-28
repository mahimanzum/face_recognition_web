package language;

public class LanguagePreference {
	private static ThreadLocal<Integer> preferredLanguage = new ThreadLocal<>();
	static Integer getPreferredLanguage(){
		return preferredLanguage.get();
	}
	static void setPreferredLanguage(Integer languageID){
		preferredLanguage.set(languageID);
	}
	static void removePreferredLanguage(){
		preferredLanguage.remove();
	}
}
