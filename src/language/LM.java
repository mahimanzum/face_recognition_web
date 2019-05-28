package language;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.sql.*;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.*;

import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
import repository.Repository;
import repository.RepositoryManager;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;
import util.CommonConstant;
import databasemanager.*;
import login.LoginDTO;



public class LM implements Repository
{
	private static LM m_LM = null;
	static Logger logger = Logger.getLogger(LM.class);
	static HashMap<Long, LanguageTextDTO> languageTextToID = new HashMap<>();

	public LM()
	{
		RepositoryManager.getInstance().addRepository(this);
	}
	public static LM getInstance()
	{
		if( m_LM == null)
			return createLM();

		return m_LM;
	}
	private synchronized  static LM createLM()
	{
		if( m_LM == null)
			m_LM = new LM();

		return m_LM;

	}
	
	public static String getText(long languageTextID, LoginDTO loginDTO)
	{
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);		
		return getText(languageTextID, userDTO);				
	}
	
	public static String getText(long languageTextID, UserDTO userDTO)
	{
		LanguageTextDTO languageTextDTO =  languageTextToID.get(languageTextID);
		
		if(languageTextDTO == null){
			return "No constant found";
		}
		
		int languageID = getLanguageIDByUserDTO(userDTO);
		
		return languageID == CommonConstant.Language_ID_English?languageTextDTO.languageTextEnglish: languageTextDTO.languageTextBangla; 
	}
	
	
	public static int getLanguageIDByUserDTO(UserDTO  userDTO){
		
		
		boolean isGlobalLoginEnabled = (GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1);
		long defaultUserID = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value;
		boolean isDefaultUser = (userDTO == null || userDTO.ID == defaultUserID);
	
		if(isGlobalLoginEnabled && isDefaultUser){
			Integer preferredLanguage = LanguagePreference.getPreferredLanguage();
			if(preferredLanguage!=null && preferredLanguage == CommonConstant.Language_ID_English){
				return CommonConstant.Language_ID_English;
			}else{
				return CommonConstant.Language_ID_Bangla;
			}
		}else{
			if(userDTO == null ||  userDTO.languageID == CommonConstant.Language_ID_English)
			{
				return CommonConstant.Language_ID_English;
			}else
			{
				return CommonConstant.Language_ID_Bangla;
			}
		}
		
	}
	
	
	public static String getText(long languageTextID)
	{
		LanguageTextDTO languageTextDTO =  languageTextToID.get(languageTextID);
		
		if(languageTextDTO == null){
			return "No constant found";
		}
		if(GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_LANGUAGE).value == CommonConstant.Language_ID_English)
		{
			return languageTextDTO.languageTextEnglish;
		}
		else
		{
			return languageTextDTO.languageTextBangla;
		}
	}
	
	@Override
	public synchronized void reload(boolean realoadAll) {


		String text = null;
		Connection connection = null;
		Statement stmt = null;
		try
		{
			String sql = "select ID, menuID, languageTextEnglish, languageTextBangla, languageConstantPrefix, languageConstant from language_text";
			logger.debug("language repo sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();

			languageTextToID.clear();
			ResultSet r = stmt.executeQuery(sql);
			while(r.next())
			{
				LanguageTextDTO languageTextDTO = new LanguageTextDTO();
				languageTextDTO.ID = r.getLong(1);
				languageTextDTO.menuID = r.getLong(2);
				languageTextDTO.languageTextEnglish = r.getString(3);
				languageTextDTO.languageTextBangla = r.getString(4);
				languageTextDTO.languageConstantPrefix = r.getString(5);
				languageTextDTO.languageConstant = r.getString(6);
				languageTextToID.put(languageTextDTO.ID, languageTextDTO);
			}

		}catch(Exception ex)
		{
			logger.fatal("Error occured while loading  Language Text",ex);
		}
		finally
		{
			try{if(stmt != null)stmt.close();}catch(Exception ex){}
			try{if(connection != null)DatabaseManager.getInstance().freeConnection(connection);}catch(Exception ex){}
		}
		return ;
	}
	
	
	public String getTableName() {

		return "language_text";
	}

}
