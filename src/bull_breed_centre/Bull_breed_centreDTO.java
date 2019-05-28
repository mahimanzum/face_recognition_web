package bull_breed_centre;
import java.util.*; 


public class Bull_breed_centreDTO {

	public long iD = 0;
	public int bullType = 0;
	public int breedType = 0;
	public int centreType = 0;
	public int grsOffice = 0;
	public long grsOfficer = 0;
    public String infoFile = "";
    public String bullImage = "";
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Bull_breed_centreDTO[" +
            " iD = " + iD +
            " bullType = " + bullType +
            " breedType = " + breedType +
            " centreType = " + centreType +
            " grsOffice = " + grsOffice +
            " grsOfficer = " + grsOfficer +
            " infoFile = " + infoFile +
            " bullImage = " + bullImage +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}