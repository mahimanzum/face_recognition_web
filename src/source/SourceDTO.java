package source;
import java.util.*; 


public class SourceDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public boolean isDeleted = false;
    public String description = "";
	
    @Override
	public String toString() {
            return "$SourceDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " isDeleted = " + isDeleted +
            " description = " + description +
            "]";
    }

}