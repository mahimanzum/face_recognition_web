package theme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class ThemeRepository implements Repository {
	ThemeDAO themeDAO = new ThemeDAO();
	
	
	static Logger logger = Logger.getLogger(ThemeRepository.class);
	
	
	private String currentThemeDescription = null;

	static ThemeRepository instance = null;  
	private ThemeRepository(){

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static ThemeRepository getInstance(){
		if (instance == null){
			instance = new ThemeRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		currentThemeDescription = themeDAO.getAppliedThemeDescription();
	}
	
	public String getCurrentAppliedThemeDescriprion(){
		return currentThemeDescription;
	}
	
	@Override
	public String getTableName() {
		return "theme";
	}
}


