package apa_target;
import java.util.*; 


public class Apa_targetDTO {

	public long iD = 0;
	public int semenCollection = 0;
	public int artificialInsemenation = 0;
	public int progeny = 0;
	public int candidateBullProduction = 0;
    public String description = "";
	public long entryDate = 0;
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Apa_targetDTO[" +
            " iD = " + iD +
            " semenCollection = " + semenCollection +
            " artificialInsemenation = " + artificialInsemenation +
            " progeny = " + progeny +
            " candidateBullProduction = " + candidateBullProduction +
            " description = " + description +
            " entryDate = " + entryDate +
            " isDeleted = " + isDeleted +
            "]";
    }

}