package semen_distribution;
import java.util.*; 


public class Semen_distributionDTO {

	public long iD = 0;
	public int bullType = 0;
	public int noOfDose = 0;
	public long requisitionId = 0;
	public int groupId = 0;
	public long transactionDate = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Semen_distributionDTO[" +
            " iD = " + iD +
            " bullType = " + bullType +
            " noOfDose = " + noOfDose +
            " requisitionId = " + requisitionId +
            " groupId = " + groupId +
            " transactionDate = " + transactionDate +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}