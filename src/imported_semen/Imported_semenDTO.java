package imported_semen;
import java.util.*; 


public class Imported_semenDTO {

	public long iD = 0;
    public String bullTag = "";
	public long dateOfBirth = 0;
    public String dam = "";
    public String sire = "";
    public String maternalGrandDam = "";
    public String maternalGrandSire = "";
    public String paternalGrandDam = "";
    public String paternalGrandSire = "";
	public int milkYieldOfDam = 0;
	public int milkYieldOfSiresDam = 0;
	public int progenyMilkYield = 0;
    public String breed = "";
	public long dateOfEntry = 0;
	public int receivedAmount = 0;
	public int distributedAmount = 0;
    public String toWhomDistributed = "";
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Imported_semenDTO[" +
            " iD = " + iD +
            " bullTag = " + bullTag +
            " dateOfBirth = " + dateOfBirth +
            " dam = " + dam +
            " sire = " + sire +
            " maternalGrandDam = " + maternalGrandDam +
            " maternalGrandSire = " + maternalGrandSire +
            " paternalGrandDam = " + paternalGrandDam +
            " paternalGrandSire = " + paternalGrandSire +
            " milkYieldOfDam = " + milkYieldOfDam +
            " milkYieldOfSiresDam = " + milkYieldOfSiresDam +
            " progenyMilkYield = " + progenyMilkYield +
            " breed = " + breed +
            " dateOfEntry = " + dateOfEntry +
            " receivedAmount = " + receivedAmount +
            " distributedAmount = " + distributedAmount +
            " toWhomDistributed = " + toWhomDistributed +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}