package facial_recognization;
import java.util.*; 


public class Facial_recognizationDTO {

	public long iD = 0;
    public String name = "";
    public String address = "";
    public String phone = "";
    public String email = "";
    public String image = "";
	public boolean isDeleted = false;
	public double match_parcentage = 0;
	
    @Override
	public String toString() {
            return "$Facial_recognizationDTO[" +
            " iD = " + iD +
            " name = " + name +
            " address = " + address +
            " phone = " + phone +
            " email = " + email +
            " image = " + image +
            " isDeleted = " + isDeleted +
            "]";
    }

}