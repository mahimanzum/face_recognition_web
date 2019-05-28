package liquid_semen;
import java.util.*; 


public class Liquid_semenDTO {

	public long iD = 0;
	public int centreType = 0;
	public int bullType = 0;
	public int noOfDose = 0;
	public int volume = 0;
	public int density = 0;
	public int progressiveMotility = 0;
	public int colorType = 0;
	public long collectionDistributionDate = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Liquid_semenDTO[" +
            " iD = " + iD +
            " centreType = " + centreType +
            " bullType = " + bullType +
            " noOfDose = " + noOfDose +
            " volume = " + volume +
            " density = " + density +
            " progressiveMotility = " + progressiveMotility +
            " colorType = " + colorType +
            " collectionDistributionDate = " + collectionDistributionDate +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}