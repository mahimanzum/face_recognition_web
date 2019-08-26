package language;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
import login.LoginDTO;
import sessionmanager.SessionConstants;

public class PreferredLanguageFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isGlobalLoginEnabled = (GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1);
		long defaultUserID = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value;
		LoginDTO loginDTO = (LoginDTO)((HttpServletRequest)request).getSession().getAttribute(SessionConstants.USER_LOGIN);
		boolean isDefaultUser = (loginDTO!=null && loginDTO.userID == defaultUserID);
		
		if(isGlobalLoginEnabled && isDefaultUser){
			
			Integer preferredLanguageID = (Integer) ((HttpServletRequest)request).getSession().getAttribute(SessionConstants.PREFERRED_LANGUAGE);
			
			LanguagePreference.setPreferredLanguage(preferredLanguageID);
		}
		chain.doFilter(request, response);
		if(isGlobalLoginEnabled && isDefaultUser){
			LanguagePreference.removePreferredLanguage();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
