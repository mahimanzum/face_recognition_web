package login;

public class LoginDTO {
	//only different fields will be present + userID. We shall use UserID to get other user info to avoid duplicacy
	public long userID = -1;
	public String loginSourceIP;


	@Override
	public String toString() {
		return "LoginDTO [" + ", userID=" + userID 
				+ ", loginSourceIP=" + loginSourceIP
				+"]";
	}
}
