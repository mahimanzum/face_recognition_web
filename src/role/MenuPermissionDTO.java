package role;

import annotation.ColumnName;
import annotation.TableName;
import permission.MenuDTO;
import report.Join;

@TableName("menu_permission")
public class MenuPermissionDTO {
	@Join(RoleDTO.class)
	@ColumnName("roleID")
	public long roleID;
	@ColumnName("menuID")
	@Join(MenuDTO.class)
	public int menuID;
}
