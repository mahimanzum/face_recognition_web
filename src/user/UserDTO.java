package user;

import java.util.ArrayList;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import common.PerformanceLog;
import report.Join;
import role.RoleDTO;

/**
 * @author Kayesh Parvez
 *
 */
@TableName("user")
public class UserDTO {
	@PrimaryKey
	@ColumnName("user.ID")
	@Join(PerformanceLog.class)
	public long ID;
	@ColumnName("user.username")
	public String userName = "";
	@ColumnName("user.password")
	public String password = "";
	@ColumnName("user.userType")
	@Join(UserTypeDTO.class)
	public int userType = 1;
	@ColumnName("user.roleID")
	@Join(RoleDTO.class)
	public long roleID = 0;
	@ColumnName("user.languageID")
	public int languageID = 1;
	@ColumnName("user.isDeleted")
	public boolean isDeleted;
	@ColumnName("user.mailAddress")
	public String mailAddress = "";
	@ColumnName("user.fullName")
	public String fullName = "";
	@ColumnName("user.phoneNo")
	public String phoneNo = "";
	@ColumnName("user.centre_Type")
	public int centreType = 3;
	public String roleName = "";
	public String userTypeName = "";
	public ArrayList<String> loginIPs = new ArrayList<>();
	@ColumnName("user.otoSMS")
	public boolean otpSMS;
	@ColumnName("user.otpEmail")
	public boolean otpEmail;
	@ColumnName("user.otpPushNotification")
	public boolean otpPushNotification;
	
	@Override
	public String toString() {
		return "UserDTO [ID=" + ID + ", userName=" + userName + ", userType=" + userType + ", roleID=" + roleID
				+ ", languageID=" + languageID + ", isDeleted=" + isDeleted + ", mailAddress=" + mailAddress
				+ ", fullName=" + fullName + ", phoneNo=" + phoneNo + ", roleName=" + roleName + ", userTypeName="
				+ userTypeName + ", loginIPs=" + loginIPs + "]";
	}
	


}
