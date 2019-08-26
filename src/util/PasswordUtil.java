package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public final class PasswordUtil
{
  private static PasswordUtil instance;

  private PasswordUtil()
  {
  }

  public synchronized String encrypt(String plaintext) throws Exception
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA"); //step 2
    }
    catch(NoSuchAlgorithmException e)
    {
      throw new Exception(e.getMessage());
    }
    try
    {
      md.update(plaintext.getBytes("UTF-8")); //step 3
    }
    catch(UnsupportedEncodingException e)
    {
      throw new Exception(e.getMessage());
    }

    byte raw[] = md.digest(); //step 4
    String hash = (new Base64()).encodeToString(raw); //step 5 
    return hash; //step 6
  }
  
  
  /*public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash||!stored_hash.startsWith("$2y$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
		StringBuilder hashed_password_builder = new StringBuilder(stored_hash);
		if(stored_hash.startsWith("$2y$")){
			hashed_password_builder.setCharAt(2, 'a');
		}
		password_verified = BCrypt.checkpw(password_plaintext, hashed_password_builder.toString());

		return(password_verified);
	}*/

  public static synchronized PasswordUtil getInstance() //step 1
  {
    if(instance == null)
    {
       instance = new PasswordUtil(); 
    } 
    return instance;
  }
  
  public String bytesToHex(byte[] bytes) {
	    StringBuilder builder = new StringBuilder();
	    for (byte b: bytes) {
	      builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	  }
  
  public static void main(String args[]) throws Exception
  {
	  String plainPassword="admin2";
	  String encrypted = PasswordUtil.getInstance().encrypt(plainPassword);
	  System.out.println("encrypted " + encrypted);
	 
  }
  
  
}
