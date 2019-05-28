package approval_status;
import java.util.*; 


public class Approval_statusDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Approval_statusDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " isDeleted = " + isDeleted +
            "]";
    }

}