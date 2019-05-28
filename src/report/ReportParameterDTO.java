package report;

public class ReportParameterDTO {
	public long menuID;
	public String canonicalFullName;
	public String userDefinedFullName;
	public String dataComment;
	public String operatorType;
	public String dataValue;
	
	public String getDisplayDivName(){
		String[] tokens = canonicalFullName.split("\\.");
		String tableName = tokens[tokens.length-2];
		String fieldName = tokens[tokens.length-1];
		return "display."+tableName+"."+fieldName;
	}
	
	public Class<?> getClassObject() throws ClassNotFoundException{
		String[] tokens = canonicalFullName.split("\\.");
		String tableName = "";
		for(int i=0;i<tokens.length-1;i++){
			if(i!=0){
				tableName+=".";
			}
			tableName+=tokens[i];
		}
		return Class.forName(tableName);
	}
	
	public String getClassName(){
		String[] tokens = canonicalFullName.split("\\.");
		String tableName = tokens[tokens.length-2];
		return tableName;
	}
	
	@Override
	public String toString() {
		return "ReportParameterDTO [menuID=" + menuID + ", canonicalFullName=" + canonicalFullName
				+ ", userDefinedFullName=" + userDefinedFullName + ", dataComment=" + dataComment + ", operatorType="
				+ operatorType + "]";
	}
	
}
