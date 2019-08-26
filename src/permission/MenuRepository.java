package permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;

import role.RoleDAO;
import user.UserTypeDTO;


public class MenuRepository {
	static Logger logger = Logger.getLogger(MenuRepository.class);
	public static MenuRepository instance = new MenuRepository();
    private static Map<Integer,MenuDTO> mapOfMenuToMenuID;
    private static List<MenuDTO> preorderedMenuList;
    
    public static List<Integer> getRootToMenuByMenuID(int menuID){
    	List<Integer> pathFromMenuToRoot = new ArrayList<>();
    	MenuDTO menuDTO = getMenuDTOByMenuID(menuID);
    	while(menuDTO!=null){
    		pathFromMenuToRoot.add(menuDTO.getMenuID());
    		menuDTO = getMenuDTOByMenuID(menuDTO.getParentMenuID());
    	}
    	Collections.reverse(pathFromMenuToRoot);
    	return pathFromMenuToRoot;
    }
    
    
    private static MenuDTO cloneMenu(MenuDTO menuDTO){
		MenuDTO menuDTOClone = new MenuDTO();
		menuDTOClone.hyperLink = menuDTO.hyperLink;
		menuDTOClone.languageTextID = menuDTO.languageTextID;
		menuDTOClone.menuID = menuDTO.menuID;
		menuDTOClone.menuName = menuDTO.menuName;
		menuDTOClone.parentMenuID = menuDTO.parentMenuID;
		menuDTOClone.languageTextID = menuDTO.languageTextID;
		menuDTOClone.isVisible = menuDTO.isVisible;
		menuDTOClone.orderIndex = menuDTO.orderIndex;
		menuDTOClone.requestMethodType = menuDTO.requestMethodType;
		menuDTOClone.selectedMenuID = menuDTO.selectedMenuID;
		menuDTOClone.icon = menuDTO.icon;
		menuDTOClone.isAPI = menuDTO.isAPI;
		menuDTOClone.menuNameBangla = menuDTO.menuNameBangla;
		menuDTOClone.constant = menuDTO.constant;
		return menuDTOClone;
    }
    
    
    public static List<Integer> getSubtreeMenuIDListByMenuID(int menuID){
    	List<Integer> menuIDList = new ArrayList<>();
    	
    	MenuDTO menuDTO = mapOfMenuToMenuID.get(menuID);
    	
    	if(menuDTO!=null){
    		menuIDList.add(menuID);
    	}else{
    		return menuIDList;
    	}
    	
    	for(MenuDTO childMenuDTO: menuDTO.getChildMenuList()){
    		menuIDList.addAll(getSubtreeMenuIDListByMenuID(childMenuDTO.menuID));
    	}
    	
    	return menuIDList;
    }
    
    
    public static void main(String[] args){
    	//System.out.println(normalizeHyperlink("http://localhost:8080/Project_Builder/LanguageServlet?actionType=search&search=true&menuID=100003"));
    	System.out.println("http://localhost:8080/Project_Builder/LanguageServlet?actionType=search&menuID=100022&&search=true&RECORDS_PER_PAGE=30".matches("http://localhost:8080/Project_Builder/LanguageServlet?actionType=search.*"));
    }
    
    private static String normalizeHyperlink(String hyperlink){
    	if(hyperlink==null){
    		return null;
    	}
   	String[] hyperlinkParts = hyperlink.split("\\?");
    	if(hyperlinkParts.length<2){
    		return hyperlink;
    	}
    	String baseURI = hyperlinkParts[0];
    	String queryString = hyperlinkParts[1];
    	
    	String[] queryParts = queryString.split("&");
    	Arrays.sort(queryParts);
    	
    	String normalizedLink = baseURI+"?";
    	for(int i=0;i<queryParts.length;i++){
    		if(i!=0){
    			normalizedLink+="&";
    		}
    		normalizedLink+=queryParts[i];
    	}
    	return normalizedLink;
    	
    }
    
    public static MenuDTO getMenuDTOByHyperlinkAndRequestType(String hyperlink,int requestType){
    	hyperlink = normalizeHyperlink(hyperlink);
    	for(MenuDTO menuDTO:mapOfMenuToMenuID.values()){
    		
    		String menuDTOHyperlinkString = menuDTO.hyperLink.replaceAll("\\?", "\\\\?").replaceAll("\\.", "\\\\.");
    		menuDTOHyperlinkString = normalizeHyperlink(menuDTOHyperlinkString);
    		if(menuDTO.requestMethodType == requestType && menuDTOHyperlinkString!=null && menuDTOHyperlinkString.trim().length()>0 && hyperlink.matches( menuDTOHyperlinkString+".*" )){
    			return menuDTO;
    		}
    	}
    	return null;
    }
    
    
    public static MenuDTO getMenuDTOByMenuID(int menuID){
    	MenuDTO menuDTO = mapOfMenuToMenuID.get(menuID);
    	return menuDTO == null?null:cloneMenu(menuDTO);
    }
    
    public static List<MenuDTO> getInvisibleRootMenuList(){
    	
    	List<MenuDTO> rootMenuList = getRootMenuList();
    	return getVisibleRootMenuDTOListByRootMenuList(rootMenuList,false);
    	
    }
    
    public static List<MenuDTO> getVisibleRootMenuList(){
    	
    	List<MenuDTO> rootMenuList = getRootMenuList();
    	
    	logger.debug(rootMenuList);
    	
    	return getVisibleRootMenuDTOListByRootMenuList(rootMenuList,true);
    	
    }
    
    
    public static List<MenuDTO> getVisibleRootMenuDTOListByRootMenuList(List<MenuDTO> rootMenuList,boolean isVisible){
    	List<MenuDTO> visibleRootMenuList = new ArrayList<>();
    	
    	for(MenuDTO rootMenuDTO:rootMenuList){
    		
    		if(rootMenuDTO.isVisible == isVisible){
    			visibleRootMenuList.add(rootMenuDTO);
    		}
    		
    	}
    	return visibleRootMenuList;
    }
    
    
    public static List<MenuDTO> getRootMenuList(){
    	
    	Map<Integer,MenuDTO> mapOfMenuDTOCloneToMenuID = new HashMap<>();
    	

    	
    	
    	
    	for(MenuDTO menuDTO:mapOfMenuToMenuID.values()){
    		
    		MenuDTO menuDTOClone = cloneMenu(menuDTO);
    		
    		mapOfMenuDTOCloneToMenuID.put(menuDTOClone.menuID, menuDTOClone);
    		
    	}
    	
    	
    	List<MenuDTO> rootMenuList = new ArrayList<>();
    	
    	
    	
    	
    	
    	
    	
    	
    	List<MenuDTO> allCloneMenu = new ArrayList<>(mapOfMenuDTOCloneToMenuID.values());
    	
    	Collections.sort(allCloneMenu, new Comparator<MenuDTO>() {

			@Override
			public int compare(MenuDTO o1, MenuDTO o2) {

				if(o1.orderIndex == o2.orderIndex){
					return 0;
				}else if(o1.orderIndex<o2.orderIndex){
					return -1;
				}else{
					return 1;
				}
				
			}
		});
    	
    	
    	
    	
    	
    	for(MenuDTO menuDTO:allCloneMenu){
    		if(menuDTO.parentMenuID==-1){
    			rootMenuList.add(menuDTO);
    		}else{
    			MenuDTO parentMenuDTO = mapOfMenuDTOCloneToMenuID.get(menuDTO.parentMenuID);
    			if(parentMenuDTO!=null){
    				parentMenuDTO.addChildPermission(menuDTO);
    			}
    		}
    	}
    	
    	return rootMenuList;
    	
    }
    
    
	private MenuRepository() {
		reload();
	}
	
	public synchronized static MenuRepository getInstatnce(){
		if (instance == null){
			instance = new MenuRepository();			
		}
		return instance;
	}
	
	private static List<MenuDTO> getPreorderedMenuList(MenuDTO menuDTO){
		List<MenuDTO> resultMenuList = new ArrayList<>();
		
		resultMenuList.add(menuDTO);
		for(MenuDTO childMenu: menuDTO.getChildMenuList()){
			resultMenuList.addAll(getPreorderedMenuList(childMenu));
		}
		
		return resultMenuList;
	}
	
	public static List<MenuDTO> getPreorderedMenuList(){
		List<MenuDTO> preorderedMenuList = new ArrayList<>();
		
		for(MenuDTO menuDTO: MenuRepository.preorderedMenuList){
			MenuDTO clonedMenu = cloneMenu(menuDTO);
			preorderedMenuList.add(clonedMenu);
		}
		
		
		return preorderedMenuList;
	}
	
	public static List<MenuDTO> getPreorderedNonAPIMenuList(){
		List<MenuDTO> preorderedMenuList = new ArrayList<>();
		
		for(MenuDTO menuDTO: MenuRepository.preorderedMenuList){
			if(menuDTO.isAPI==false){
				MenuDTO clonedMenu = cloneMenu(menuDTO);
				preorderedMenuList.add(clonedMenu);
			}
		}
		
		return preorderedMenuList;
	}
	
	public static List<MenuDTO> getPreorderedAPIMenuList(){
		List<MenuDTO> preorderedMenuList = new ArrayList<>();
		
		for(MenuDTO menuDTO: MenuRepository.preorderedMenuList){
			if(menuDTO.isAPI==false){
				MenuDTO clonedMenu = cloneMenu(menuDTO);
				preorderedMenuList.add(clonedMenu);
			}
		}
		
		return preorderedMenuList;
	}
	
	public static List<MenuDTO> getAllMenuList(){
		List<MenuDTO> menuList = new ArrayList<>();
		for(MenuDTO menuDTO: MenuRepository.preorderedMenuList){
			MenuDTO menuDTOClone = cloneMenu(menuDTO);
			menuList.add(menuDTOClone);
		}
		return menuList;
	}
	
	public static void reload() {		
		try
		{
			MenuDAO menuDAO = new MenuDAO();
			List<MenuDTO> allMenuList = menuDAO.getAllMenuList();
			List<MenuDTO> rootMenuList = new ArrayList<>();
			

			mapOfMenuToMenuID = new HashMap<>();
			
			for(MenuDTO menuDTO: allMenuList){				
				mapOfMenuToMenuID.put(menuDTO.menuID, menuDTO);
				if(menuDTO.getParentMenuID()==-1){
					rootMenuList.add(menuDTO);
				}
			}

			for(MenuDTO menuDTO: allMenuList){
				int parentMenuID = menuDTO.parentMenuID;
				if(mapOfMenuToMenuID.containsKey(parentMenuID)){
					mapOfMenuToMenuID.get(parentMenuID).addChildPermission(menuDTO);
				}
			}
			
			
			List<MenuDTO> preorderedMenuDTOList = new ArrayList<>();
			
			for(MenuDTO rootMenu: rootMenuList){
				preorderedMenuDTOList.addAll(getPreorderedMenuList(rootMenu));
			}
			MenuRepository.preorderedMenuList = preorderedMenuDTOList;
			
		}
		catch(Exception ex)
		{
			logger.fatal("",ex);
		}		
	}

}
