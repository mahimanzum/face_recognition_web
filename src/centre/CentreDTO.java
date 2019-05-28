package centre;
import java.util.*; 


public class CentreDTO {

	public long iD = 0;
    public String nameEn = "";
    public String nameBn = "";
    public String address = "";
    public String contactPerson = "";
    public String phoneNumber = "";
    public String email = "";
	public boolean isDeleted = false;
    public String description = "";
	
    @Override
	public String toString() {
            return "$CentreDTO[" +
            " iD = " + iD +
            " nameEn = " + nameEn +
            " nameBn = " + nameBn +
            " address = " + address +
            " contactPerson = " + contactPerson +
            " phoneNumber = " + phoneNumber +
            " email = " + email +
            " isDeleted = " + isDeleted +
            " description = " + description +
            "]";
    }

}