package forgetPassword;

public class VerificationConstants {
	
	public static final int DELAY_TIME_IN_MINUTES = 5;
	public static final int DELAY_TIME_IN_MILLIS = DELAY_TIME_IN_MINUTES * 60 * 1000;
	
	public static final int VERIFICATION_TYPE_FORGET_PASSWORD = 1;
	public static final int VERIFICATION_TYPE_PHONE_NUMBER = 2;
	public static final int VERIFICATION_TYPE_EMAIL = 3;
	public static final int VERIFICATION_TYPE_OTP_AT_LOGIN = 4;
	
	public static final String CACHE_OBJECT = "cache_object";
	public static final String CACHE_LOGIN_DTO = "cache_login_dto";
	public static final String CACHE_LOGIN_USERNAME = "cache_login_username";
	public static final String CACHE_FORWARD = "cache_forward";
	public static final String CACHE_OTP_VERIFICATION_URL = "cache_otp_verification_url";
	public static final String CACHE_PHONE_NUMBER = "cache_phone_number";
	public static final String CACHE_EMAIL = "cache_email";
}
