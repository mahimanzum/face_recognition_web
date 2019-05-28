package progeny_record;
import java.util.*; 


public class Progeny_recordDTO {

	public long iD = 0;
	public int centreType = 0;
	public int bullType = 0;
	public int numberOfMaleCalfs = 0;
	public int numberOfFemaleCalfs = 0;
	public long dateOfEntry = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Progeny_recordDTO[" +
            " iD = " + iD +
            " centreType = " + centreType +
            " bullType = " + bullType +
            " numberOfMaleCalfs = " + numberOfMaleCalfs +
            " numberOfFemaleCalfs = " + numberOfFemaleCalfs +
            " dateOfEntry = " + dateOfEntry +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}