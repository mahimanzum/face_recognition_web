package breed;
import java.util.*; 


public class BreedDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public boolean isDeleted = false;
    public String description = "";
	
    @Override
	public String toString() {
            return "$BreedDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " isDeleted = " + isDeleted +
            " description = " + description +
            "]";
    }

}