package artificial_insemenation_record;
import java.util.*; 


public class Artificial_insemenation_recordDTO {

	public long iD = 0;
	public int centreType = 0;
	public int bullType = 0;
	public int noOfAI = 0;
	public long entryDate = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Artificial_insemenation_recordDTO[" +
            " iD = " + iD +
            " centreType = " + centreType +
            " bullType = " + bullType +
            " noOfAI = " + noOfAI +
            " entryDate = " + entryDate +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}