package permission;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

	public List<MenuDTO> getAlignMenuListAll()
	{
		List<MenuDTO> rootMenuList = new ArrayList<MenuDTO>(MenuRepository.getRootMenuList());

		List<MenuDTO> allMenuList = new ArrayList<MenuDTO>();
		for(MenuDTO menu: rootMenuList){
			allMenuList.addAll(  alignMenuNames(menu, menu.isVisible? "|--->":""));
		}
		return allMenuList;
	}
	
	public List<MenuDTO> getAlignMenuListAllSeparated()
	{
		List<MenuDTO> rootMenuList = new ArrayList<MenuDTO>(MenuRepository.getRootMenuList());

		List<MenuDTO> allMenuList = new ArrayList<MenuDTO>();

		List<MenuDTO> visibleMenuList =  new ArrayList<>();
		List<MenuDTO> hiddenMenuList = new ArrayList<>();

		for(MenuDTO menu: rootMenuList){
			
			if(menu.isVisible){
				visibleMenuList.addAll(  alignMenuNames(menu,  "|--->"));
			}else{
				hiddenMenuList.add(menu);
			}
			
		}
		allMenuList.addAll( visibleMenuList);
		allMenuList.addAll(hiddenMenuList);
		return allMenuList;
	}
	
	private List<MenuDTO> alignMenuNames(MenuDTO menuDTO,String prefix){

		List<MenuDTO> resultList = new ArrayList<MenuDTO>();
		resultList.add(menuDTO);

		menuDTO.menuName = prefix+menuDTO.menuName;

		prefix = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+prefix;


		if(menuDTO.getChildMenuList()!=null){
			for(MenuDTO childMenuDTO:menuDTO.getChildMenuList()){
				resultList.addAll(  alignMenuNames(childMenuDTO, prefix));
			}
		}

		return resultList;
	}
	
	public boolean isAncestorByRootAndNode(MenuDTO root,MenuDTO node){
		if(node == null){
			return false;
		}
		if(node.menuID == root.menuID){
			return true;
		}
		if(node.parentMenuID == -1){
			return false;
		}
		MenuDTO parentMenu = MenuRepository.getMenuDTOByMenuID(node.parentMenuID);
		return isAncestorByRootAndNode(root, parentMenu);
	}
	
	public String getAllAncestorMenus(int menuID)
	{		
		 return getAllAncestorMenus(menuID, ">");
	}
	public String getAllAncestorMenus(int menuID, String separator)
	{		
		 MenuDTO menuDTO = MenuRepository.getMenuDTOByMenuID(menuID);
		 String menuHierarchy = menuDTO.menuName;
		 while(menuDTO.getParentMenuID() > -1)
		 {
			 menuDTO = MenuRepository.getMenuDTOByMenuID(menuDTO.getParentMenuID());
			 menuHierarchy = menuDTO.menuName + separator + menuHierarchy;
		 }
		 return menuHierarchy;
	}
}
