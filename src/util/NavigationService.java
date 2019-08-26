package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import login.LoginDTO;

public interface NavigationService
{
	public Collection getIDs(LoginDTO loginDTO) throws Exception;
	public Collection getIDsWithSearchCriteria(Hashtable searchCriteria, LoginDTO loginDTO) throws Exception;
	public Collection getDTOs(Collection recordIDs, LoginDTO loginDTO) throws Exception;
	
}