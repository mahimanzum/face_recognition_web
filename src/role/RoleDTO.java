package role;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import report.Join;
import user.UserDTO;

@TableName("role")
public class RoleDTO
{
	@ColumnName("roleName")
	public String roleName = "";
	@ColumnName("description")
    public String description = "";
    @PrimaryKey
    @ColumnName("ID")
    @Join({UserDTO.class,MenuPermissionDTO.class})
    public long ID;
    @ColumnName("isDeleted")
    public boolean isDeleted;
    @ColumnName("lastModificationTime")
    public long lastModificationTime;       
}