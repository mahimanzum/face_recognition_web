package semen_requisition;
import java.util.*; 


public class Semen_requisitionDTO {

	public long iD = 0;
	public int groupId = 0;
	public int centreType = 0;
	public int breedType = 0;
	public int noOfDose = 0;
	public long requisitionDate = 0;
	public boolean isDelivered = false;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Semen_requisitionDTO[" +
            " iD = " + iD +
            " groupId = " + groupId +
            " centreType = " + centreType +
            " breedType = " + breedType +
            " noOfDose = " + noOfDose +
            " requisitionDate = " + requisitionDate +
            " isDelivered = " + isDelivered +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}