package bull;
import java.util.*; 


public class BullDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public long dateOfBirth = 0;
	public long breedType = 0;
	public long statusType = 0;
    public String dam = "";
    public String sire = "";
    public String maternalGrandDam = "";
    public String maternalGrandSire = "";
    public String paternalGrandDam = "";
    public String paternalGrandSire = "";
	public int milkYieldOfDam = 0;
	public int milkYieldOfSiresDam = 0;
	public int progenyMilkYield = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$BullDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " dateOfBirth = " + dateOfBirth +
            " breedType = " + breedType +
            " statusType = " + statusType +
            " dam = " + dam +
            " sire = " + sire +
            " maternalGrandDam = " + maternalGrandDam +
            " maternalGrandSire = " + maternalGrandSire +
            " paternalGrandDam = " + paternalGrandDam +
            " paternalGrandSire = " + paternalGrandSire +
            " milkYieldOfDam = " + milkYieldOfDam +
            " milkYieldOfSiresDam = " + milkYieldOfSiresDam +
            " progenyMilkYield = " + progenyMilkYield +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}