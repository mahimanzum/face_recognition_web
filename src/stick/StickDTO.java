package stick;
import java.util.*; 


public class StickDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public long semenCollectionId = 0;
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$StickDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " semenCollectionId = " + semenCollectionId +
            " isDeleted = " + isDeleted +
            "]";
    }

}