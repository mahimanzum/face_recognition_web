package semen_collection;
import java.util.*; 


public class Semen_collectionDTO {

	public long iD = 0;
	public int bullType = 0;
	public int noOfDose = 0;
	public int volume = 0;
	public int density = 0;
	public int progressiveMortality = 0;
	public int colorType = 0;
	public long transactionDate = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Semen_collectionDTO[" +
            " iD = " + iD +
            " bullType = " + bullType +
            " noOfDose = " + noOfDose +
            " volume = " + volume +
            " density = " + density +
            " progressiveMortality = " + progressiveMortality +
            " colorType = " + colorType +
            " transactionDate = " + transactionDate +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}