package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class TokenGenerator {

	public static String getOTP()
	{
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return ""+n;
	}
	
	public static String getDoubleEncryptedToken() throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		String token = PasswordUtil.getInstance().bytesToHex(salt.digest());
		return token;
	}
}
