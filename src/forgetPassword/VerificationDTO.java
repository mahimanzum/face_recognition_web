package forgetPassword;

import java.io.Serializable;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import connection.DatabaseConnection;

/**
 * @author Alam
 */
@TableName("verification")
public class VerificationDTO implements Serializable {

	private static final long serialVersionUID = -7996395171609749651L;

	@PrimaryKey
	@ColumnName("id")
	private long id;
	
	@ColumnName("token")
	private String token;
	
	@ColumnName("username")
	private String username;
	
	@ColumnName("timestamp")
	private long lastModificationTime;
	
	//1=forget password, 2 = verify phone, 3 = verify email, 4 = otp for login
	@ColumnName("authPurpose")
	private int authPurpose;
	
	@ColumnName("expirationTime")
	private long expirationTime;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public long getLastModificationTime() {
		
		return lastModificationTime;
	}

	public void setLastModificationTime(long lastModificationTime) {
		
		this.lastModificationTime = lastModificationTime;
		
	}


	public long getExpirationTime() {
		return expirationTime;
	}


	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public int getAuthPurpose() {
		return authPurpose;
	}


	public void setAuthPurpose(int authPurpose) {
		this.authPurpose = authPurpose;
	}


	@Override
	public String toString() {
		return "ForgetPassword [id=" + id + ", token=" + token + ", username=" + username + ", lastModificationTime="
				+ lastModificationTime + "]";
	}
}
