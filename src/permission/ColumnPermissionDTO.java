package permission;


public class ColumnPermissionDTO {

	public long ID;	
	public long roleID;	
	public int columnID;
	
	
	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public long getRoleID() {
		return roleID;
	}


	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}



	public int getColumnID() {
		return columnID;
	}


	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}


	@Override
	public String toString() {
		return "ColumnPermissionDTO [ID=" + ID + ", roleID=" + roleID 
				+ ", columnID=" + columnID + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
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
		ColumnPermissionDTO other = (ColumnPermissionDTO) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	
}
