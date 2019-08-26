package filter;



import static util.HttpRequestUtils.loginIgnoreExtList;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
import login.LoginDTO;
import login.RememberMeOptionDAO;
import permission.MenuDTO;
import permission.MenuRepository;
import sessionmanager.SessionConstants;
import util.ServletConstant;

public class LoginFilter implements Filter{
	Logger logger = Logger.getLogger(LoginFilter.class);
	public static final int REDIRECT_TO_BASE_URL = 4;
	public static final int REDIRECT_TO_HOME = 3;
	public static final int REDIRECT_TO_LOGIN = 2;
	public static final int CONTINUE_CHAIN = 1;
	String contextPath = "";
	String loginURI = "";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		contextPath = httpServletRequest.getSession().getServletContext().getContextPath();
		loginURI = contextPath + "/";
		
		int result = 0;
		String URI = httpServletRequest.getRequestURI(); 
		/*if(URI.matches(".*\\.jsp.*")&&!URI.endsWith("/home/otpVerifier.jsp")&&!URI.endsWith("/forgotPassword/forgotPassword.jsp")){
			httpServletResponse.sendRedirect(contextPath);
			return;
		}*/
		if(skipLoginCheck(httpServletRequest))
		{
			result = CONTINUE_CHAIN;
			processResult(result, chain, httpServletRequest, httpServletResponse);
			return;
		}		
		if(ignoreHit(httpServletRequest))
		{
			result = CONTINUE_CHAIN;
			processResult(result, chain, httpServletRequest, httpServletResponse);
			return;
		}
		if(isLoggedIn(httpServletRequest))
		{
			result = handleLoggedIn(httpServletRequest, httpServletResponse, chain);
		}
		else
		{
			result = handleNotLoggedIn(httpServletRequest, httpServletResponse);
		}
		logger.debug("result " + result);
		processResult(result, chain, httpServletRequest, httpServletResponse);
	}
	
	private void processResult(int result, FilterChain chain, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

		switch(result)
		{
			case REDIRECT_TO_BASE_URL:{
				httpServletResponse.sendRedirect("");
				break;
			}
			case CONTINUE_CHAIN:
			{
				chain.doFilter(httpServletRequest, httpServletResponse);
				break;
			}
			case REDIRECT_TO_HOME:
			{/*
				String homePageURI = contextPath + "/home/index.jsp";
				httpServletResponse.sendRedirect(homePageURI);*/
				
				LoginDTO loginDTO = (LoginDTO)httpServletRequest.getSession().getAttribute(SessionConstants.USER_LOGIN);
				
				
				if(isGetRequest(httpServletRequest)){
					if(GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1
							&&
							loginDTO!=null
							&&
						GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value == loginDTO.userID	
							){
						
						
						int menuID = GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_LANDING_MENU).value;
						MenuDTO menuDTO = MenuRepository.getMenuDTOByMenuID(menuID);
						if(menuDTO == null){
							throw new RuntimeException("No default landing menu found with menuID "+menuID);
						}
						httpServletResponse.sendRedirect(contextPath+"/"+menuDTO.hyperLink);
						
					}else{
						httpServletRequest.getRequestDispatcher("/Semen_dashboardServlet?actionType=getDashboard").forward(httpServletRequest, httpServletResponse);
					}
				}else{
					httpServletResponse.sendRedirect("");
				}
				break;
			}
			case REDIRECT_TO_LOGIN:
			{
				//httpServletResponse.sendRedirect(loginURI);
				if(isGetRequest(httpServletRequest)){
					httpServletRequest.getRequestDispatcher("/home/login.jsp").forward(httpServletRequest, httpServletResponse);
				}else{
					httpServletResponse.sendRedirect("");
				}
				break;
			}
		}
	}
	
	private boolean skipLoginCheck(HttpServletRequest httpServletRequest)
	{
		String requestedURI = httpServletRequest.getRequestURI();
		String[] loginCheckIgnoreList = new String[] {".*forgotPassword.jsp", ".*otpVerifier.jsp",
				".*/VerificationServlet.*", ".*resetPassword.jsp",".*simpleCaptchaServlet",".*txt",".*pdf"};
		boolean skipLoginCheck = false;
		for (String ignorePath : loginCheckIgnoreList) {
			if (requestedURI.matches(ignorePath)) {
				skipLoginCheck = true;
				break;
			}
		}
		return skipLoginCheck;
	}
	
	private boolean redirectToHome(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		
		
		
		String requestedURI = httpServletRequest.getRequestURI();
		boolean redirectToHome = false;
		
		if(GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1
				&&
				requestedURI.endsWith("LoginServlet")
				){
			return false;
		}
		
		
		if(requestedURI.equals(loginURI) || requestedURI.endsWith("LoginServlet") || requestedURI.endsWith("login.jsp"))			
		{
			redirectToHome = true;
		}
		else if(!(requestedURI.endsWith("Servlet") || requestedURI.endsWith("jsp")))
		{
			redirectToHome = true;
		}
		return redirectToHome;
	}
	
	private int handleNotLoggedIn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) 
	{
		setLastRequestedURI(httpServletRequest);
		String requestedURI = httpServletRequest.getRequestURI();
		if(requestedURI.equals(loginURI) || requestedURI.endsWith("LoginServlet") || requestedURI.endsWith("login.jsp"))			
		{
			return CONTINUE_CHAIN;
		}
		return REDIRECT_TO_LOGIN;
	}
	
	private boolean isGetRequest(HttpServletRequest httpServletRequest)	
	{
		String methodName = httpServletRequest.getMethod();		
		return methodName.equalsIgnoreCase("get");
	}
	
	private boolean ignoreHit(HttpServletRequest httpServletRequest)
	{
		String requestedURI = httpServletRequest.getRequestURI();
		boolean ignore = false;
		for (String loginIgnoreExtension : loginIgnoreExtList) {
			if (requestedURI.endsWith(loginIgnoreExtension)) {
				ignore = true;
				break;
			}
		}
		return ignore;
	}
		
	private int handleLoggedIn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain)throws IOException, ServletException 
	{	
		if(!isFromOriginalIP(httpServletRequest))
		{
			return REDIRECT_TO_LOGIN;
		}
		if(!isGetRequest(httpServletRequest))
		{
			return CONTINUE_CHAIN;
		}
		if(redirectToHome(httpServletRequest, httpServletResponse))
		{			
			return REDIRECT_TO_HOME;
		}
		else
		{
			return CONTINUE_CHAIN;
		}
	}

	private boolean isFromOriginalIP(HttpServletRequest httpServletRequest)
	{
		LoginDTO loginDTO = (login.LoginDTO) httpServletRequest.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
		logger.debug("loginDTO " + loginDTO + " httpServletRequest.getRemoteAddr() " + httpServletRequest.getRemoteAddr());
		
		return loginDTO!=null && httpServletRequest.getRemoteAddr().equals(loginDTO.loginSourceIP);		
	}

	private String getRememberMeOptionCookie(HttpServletRequest httpServletRequest) {
		
		Cookie[] cookies = httpServletRequest.getCookies();
		
		if(cookies!=null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(ServletConstant.REMEMBER_ME_COOKIE_NAME)){
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}

	private boolean isLoggedIn(HttpServletRequest httpServletRequest) {
		LoginDTO loginDTO = (login.LoginDTO) httpServletRequest.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
		if(loginDTO!=null){
			return true;
		}
		String cookieValue = getRememberMeOptionCookie(httpServletRequest);
		
		boolean isDefaultLoginEnabled = (GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.ENABLE_DEFAULT_LOGIN).value == 1); 
		
		Long userID = null;
		
		if(isDefaultLoginEnabled){
			
			if(cookieValue != null){
				userID = new RememberMeOptionDAO().getUserIDByCookieValue(cookieValue);
				if(userID == null){
					userID = (long)GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value;
				}
			}else{
				userID = (long)GlobalConfigurationRepository.getInstance().getGlobalConfigDTOByID(GlobalConfigConstants.DEFAULT_USER_ID).value;
			}
			
			
		}else{
			if(cookieValue == null){
				return false;
			}
			userID = new RememberMeOptionDAO().getUserIDByCookieValue(cookieValue);
			if(userID==null){
				return false;
			}
		}
	
		loginDTO = new LoginDTO();
		loginDTO.userID = userID;
		loginDTO.loginSourceIP = httpServletRequest.getRemoteAddr();
		HttpSession session = httpServletRequest.getSession(true);
		session.setAttribute(SessionConstants.USER_LOGIN, loginDTO);
		return true;
	}
	
	private void setLastRequestedURI(HttpServletRequest httpServletRequest) 
	{
		String requestedURI = httpServletRequest.getRequestURI();
		boolean setLastRequestedURI = true;
		
		if(!isGetRequest(httpServletRequest))
		{
			setLastRequestedURI = false;
		}
		else if(requestedURI.equals(loginURI) || requestedURI.endsWith("LoginServlet") || requestedURI.endsWith("login.jsp"))			
		{
			setLastRequestedURI = false;
		}
		else if(!(requestedURI.endsWith("Servlet") || requestedURI.endsWith("jsp")))
		{
			setLastRequestedURI = false;
		} 
		if(setLastRequestedURI)
		{
			String lastRequestedURL = requestedURI;
			if (httpServletRequest.getQueryString() != null) {
				lastRequestedURL += "?" + httpServletRequest.getQueryString();
			}
			httpServletRequest.getSession(true).setAttribute("lastRequestedURL", lastRequestedURL);
		}
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
