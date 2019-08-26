package filter;

import java.util.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.RequestFailureException;
import login.LoginDTO;
import permission.MenuDTO;
import permission.MenuRepository;
import role.PermissionRepository;
import sessionmanager.SessionConstants;
import user.UserDTO;
import user.UserRepository;

/**
 * Servlet Filter implementation class MenuPermissionFilter
 */
public class MenuPermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MenuPermissionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	/*
	private boolean checkPermission(HttpServletRequest request) throws Exception{
		String requestMethod = request.getMethod();
		String URI = request.getRequestURI();
		String queryString = request.getQueryString();
		
		String hyperlink = URI+"/"+queryString;
		
		MenuDTO menuDTO = MenuRepository.getMenuDTOByHyperlink(hyperlink);
		
		if(menuDTO == null){
			return false;
		}
		
		LoginDTO loginDTO = (LoginDTO)request.getAttribute("login_DTO");
		
		
		if(loginDTO == null){
			
			// this filter will be invoked after login filter. so if no 
			//login dto is found that means this is non login page . so no permissio checking is necessay
			
			return true;
		}
		
		
		long roleID = loginDTO.roleID;
		
		PermissionDTO permissionDTO = PermissionRepository.getMenuPermissionDTOByMenuIDAndRoleID(menuDTO.menuID, roleID);
		
		if(permissionDTO == null){
			return false;
		}
		
		if(requestMethod.toUpperCase().equals("GET")&&permissionDTO.isGetRequestPermitted){
			return true;
		}else if(requestMethod.toUpperCase().equals("POST")&&permissionDTO.isPostRequestPermitted){
			return true;
		}else if(requestMethod.toUpperCase().equals("PUT")&&permissionDTO.isPutRequestPermitted){
			return true;
		}else if(requestMethod.toUpperCase().equals("DELETE")&&permissionDTO.isDeleteRequestPermitted){
			return true;
		}
		
		return false;
		
	}*/
	
	
	
	private List<MenuDTO> getMenuPathByHyperlinkAndRequestType(String hyperlink,int requestMethodType){
		
		MenuDTO menuDTO = MenuRepository.getMenuDTOByHyperlinkAndRequestType(hyperlink, requestMethodType);
		
		if(menuDTO == null){
			return Collections.emptyList();
		}
		
		if(!menuDTO.isVisible){
			menuDTO = MenuRepository.getMenuDTOByMenuID(menuDTO.selectedMenuID);
		}
		
		if(menuDTO==null){
			return Collections.emptyList();
		}
		
		
		
		
		List<MenuDTO> menuPath = new ArrayList<>();
		
		
		while(menuDTO!=null && menuDTO.parentMenuID!=1){
			menuPath.add(menuDTO);
			menuDTO = MenuRepository.getMenuDTOByMenuID(menuDTO.parentMenuID);
		}
		
		if(menuDTO!=null){
			menuPath.add(menuDTO);
		}
		
		
		Collections.reverse(menuPath);
		
		return menuPath;
		
	}
	
	
	private boolean checkPermission(HttpServletRequest request,HttpServletResponse response ,String URLWithoutContext,int requestMethodType) throws Exception{
		
		MenuDTO menuDTO = MenuRepository.getMenuDTOByHyperlinkAndRequestType(URLWithoutContext, requestMethodType);
		if(menuDTO == null){
			return true;
		}
		LoginDTO loginDTO = (login.LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
		if(loginDTO == null){
			return false;
		}
		UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(loginDTO.userID);
		
		if(userDTO == null){
			throw new Exception("No user found with userID "+loginDTO.userID);
		}
		boolean permissionExists = PermissionRepository.checkPermissionByRoleIDAndMenuID(userDTO.roleID, menuDTO.menuID);
		return true;
		
	}
	
	
	
	
	private void setLeftSideMenuSelected(HttpServletRequest request,HttpServletResponse response,String URLWithoutContext,int requestMethodType,FilterChain chain) throws Exception{

		List<MenuDTO> menuPath = getMenuPathByHyperlinkAndRequestType(URLWithoutContext, requestMethodType);
		
		List<Integer> menuIDPath = new ArrayList<>();
		for(MenuDTO menuDTO:menuPath){
			menuIDPath.add(menuDTO.getMenuID());
		}
		
		request.setAttribute("menuIDPath", menuIDPath);
		
		for(int i = 0;i<menuPath.size();i++){
			MenuDTO menuDTO = menuPath.get(menuPath.size()-1-i);
			if(i == 0){
				request.setAttribute("menu", ""+menuDTO.getMenuID());
			}else{
				request.setAttribute("subMenu"+i, ""+menuDTO.getMenuID());
			}
		}
		
		
		
	}
	
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		

		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		String context = httpServletRequest.getContextPath();
		String URL = httpServletRequest.getRequestURL().toString();
		
		
		
		String URLWithoutContext = URL.replaceFirst(".*"+context+"/", "");
		String queryString = httpServletRequest.getQueryString();
		if(queryString!=null && queryString.trim().length()!=0){
			URLWithoutContext+="?"+queryString;
		}
		
		String requestMethodName = httpServletRequest.getMethod();
		
		int requestMethodType = -1;
		
		if("GET".equals(requestMethodName.toUpperCase())){
			requestMethodType = MenuDTO.GET;
		}
		
		if("POST".equals(requestMethodName.toUpperCase())){
			requestMethodType = MenuDTO.POST;
		}
		
		try {
			
			setLeftSideMenuSelected(httpServletRequest, httpServletResponse, URLWithoutContext, requestMethodType, chain);
			if(checkPermission(httpServletRequest, httpServletResponse, URLWithoutContext, requestMethodType)){
				
				chain.doFilter(request, response);
			}else{
				request.getRequestDispatcher("/common/error_page.jsp").forward(httpServletRequest, httpServletResponse);
			}
			
		} catch (Exception e) {
			
			if(e instanceof RequestFailureException) {
				throw (RequestFailureException)e;
			}else {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
