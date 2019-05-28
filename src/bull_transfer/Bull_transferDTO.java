package bull_transfer;
import java.util.*; 


public class Bull_transferDTO {

	public long iD = 0;
	public long dateOfTransfer = 0;
	public int bullType = 0;
	public int fromCentreType = 0;
	public int toCentreType = 0;
	public long entryDate = 0;
	public long exitDate = 0;
    public String description = "";
	public int approvalStatusType = 0;
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Bull_transferDTO[" +
            " iD = " + iD +
            " dateOfTransfer = " + dateOfTransfer +
            " bullType = " + bullType +
            " fromCentreType = " + fromCentreType +
            " toCentreType = " + toCentreType +
            " entryDate = " + entryDate +
            " exitDate = " + exitDate +
            " description = " + description +
            " approvalStatusType = " + approvalStatusType +
            " isDeleted = " + isDeleted +
            "]";
    }

}