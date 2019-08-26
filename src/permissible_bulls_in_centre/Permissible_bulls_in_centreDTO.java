package permissible_bulls_in_centre;
import java.util.*; 


public class Permissible_bulls_in_centreDTO {

	public long iD = 0;
	public int bullType = 0;
	public int centreType = 0;
	public long dateOfEntry = 0;
	public long dateOfExpiration = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Permissible_bulls_in_centreDTO[" +
            " iD = " + iD +
            " bullType = " + bullType +
            " centreType = " + centreType +
            " dateOfEntry = " + dateOfEntry +
            " dateOfExpiration = " + dateOfExpiration +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}