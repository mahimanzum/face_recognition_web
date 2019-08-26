package user;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import report.Join;

/**
 * @author Kayesh Parvez
 *
 */

@TableName("user_type")
public class UserTypeDTO {
	@PrimaryKey
	@ColumnName("ID")
	@Join(UserDTO.class)
	public int ID;
	@ColumnName("name_en")
	public String name_en;
	@ColumnName("name_bn")
	public String name_bn;
	@ColumnName("dashboard")
	public String dashboard;
	@Override
	public String toString() {
		return "UserTypeDTO [ID=" + ID + ", name_en=" + name_en + ", name_bn=" + name_bn + ", dashboard=" + dashboard
				+ "]";
	}

	
	
}
