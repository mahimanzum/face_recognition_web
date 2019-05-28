package candidate_bull_production;
import java.util.*; 


public class Candidate_bull_productionDTO {

	public long iD = 0;
	public long productionDate = 0;
	public int numberOfCandidateBulls = 0;
	public int sourceType = 0;
	public boolean isDeleted = false;
    public String description = "";
	
    @Override
	public String toString() {
            return "$Candidate_bull_productionDTO[" +
            " iD = " + iD +
            " productionDate = " + productionDate +
            " numberOfCandidateBulls = " + numberOfCandidateBulls +
            " sourceType = " + sourceType +
            " isDeleted = " + isDeleted +
            " description = " + description +
            "]";
    }

}