package report;
import java.util.*; 


public class ReportDTO {

	public long iD = 0;
	public long reportingDate = 0;
	public long reporterId = 0;
	public long vehicleId = 0;
	public long lostDate = 0;
	public long foundDate = 0;
	public long statusId = 0;
	public int thanaAddress = 0;
    public String blog = "";
    public String image1 = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$ReportDTO[" +
            " iD = " + iD +
            " reportingDate = " + reportingDate +
            " reporterId = " + reporterId +
            " vehicleId = " + vehicleId +
            " lostDate = " + lostDate +
            " foundDate = " + foundDate +
            " statusId = " + statusId +
            " thanaAddress = " + thanaAddress +
            " blog = " + blog +
            " image1 = " + image1 +
            " isDeleted = " + isDeleted +
            "]";
    }

}