package permission;

import java.util.ArrayList;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import report.Join;
import role.MenuPermissionDTO;

@TableName("menu")
public class MenuDTO {
	
	public static int GET = 1;
	public static int POST = 2;
	@PrimaryKey
	@ColumnName("menu.menuID")
	@Join(MenuPermissionDTO.class)
	public int menuID;	
	@ColumnName("menu.parentMenuID")
	public int parentMenuID;	
	@ColumnName("menu.menuName")
	public String menuName;
	@ColumnName("menu.menuNameBangla")
	public String menuNameBangla;
	@ColumnName("menu.hyperLink")
	public String hyperLink;
	@ColumnName("menu.languageTextID")
	public int languageTextID = -1;
	@ColumnName("menu.orderIndex")
	public int orderIndex;
	@ColumnName("menu.selectedMenuID")
	public int selectedMenuID;
	@ColumnName("menu.isVisible")
	public boolean isVisible;
	@ColumnName("menu.requestMethodType")
	public int requestMethodType;
	@ColumnName("menu.icon")
	public String icon;
	@ColumnName("menu.isAPI")
	public boolean isAPI;
	@ColumnName("menu.constantName")
	public String constant;

	ArrayList<MenuDTO> childMenuList = new ArrayList<>();
	
    public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getParentMenuID() {
		return parentMenuID;
	}

	public void setParentMenuID(int parentMenuID) {
		this.parentMenuID = parentMenuID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getLanguageTextID() {
		return languageTextID;
	}

	public void setLanguageTextID(int languageTextID) {
		this.languageTextID = languageTextID;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getSelectedMenuID() {
		return selectedMenuID;
	}

	public void setSelectedMenuID(int selectedMenuID) {
		this.selectedMenuID = selectedMenuID;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getRequestMethodType() {
		return requestMethodType;
	}

	public void setRequestMethodType(int requestMethodType) {
		this.requestMethodType = requestMethodType;
	}

	public ArrayList<MenuDTO> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(ArrayList<MenuDTO> childMenuList) {
		this.childMenuList = childMenuList;
	}

	public void addChildPermission(MenuDTO dto)
    {
        childMenuList.add(dto);
    }

    public void removeChildPermission(MenuDTO dto)
    {
        for(int i = 0; i < childMenuList.size(); i++)
        {
            MenuDTO x = childMenuList.get(i);
            if(x.menuID == dto.menuID)
            {
                childMenuList.remove(i);
                return;
            }
        }

    }


	public String getHyperLink() {
		return hyperLink;
	}


	public void setHyperLink(String hyperLink) {
		this.hyperLink = hyperLink;
	}


	@Override
	public String toString() {
		return "MenuDTO [menuID=" + menuID + ", parentMenuID=" + parentMenuID + ", menuName=" + menuName
				+ ", moduleTypeID="  + ", hyperLink=" + hyperLink
				+ ", childMenuList="  + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + menuID;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuDTO other = (MenuDTO) obj;
		if (menuID != other.menuID)
			return false;
		return true;
	}


	
	

}
