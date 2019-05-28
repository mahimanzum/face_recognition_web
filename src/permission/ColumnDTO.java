package permission;


public class ColumnDTO {
	public int columnID;
	public String columnName;
	
	
	public int getColumnID() {
		return columnID;
	}


	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	


	@Override
	public String toString() {
		return "ColumnDTO [columnID=" + columnID + ", columnName=" + columnName 
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnID;
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
		ColumnDTO other = (ColumnDTO) obj;
		if (columnID != other.columnID)
			return false;
		return true;
	}
	
	
}
