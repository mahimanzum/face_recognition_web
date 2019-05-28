package grs_top_layer;
import java.util.*; 


public class Grs_top_layerDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
	public int layerNumber = 0;
    public String description = "";
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Grs_top_layerDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " layerNumber = " + layerNumber +
            " description = " + description +
            " isDeleted = " + isDeleted +
            "]";
    }

}