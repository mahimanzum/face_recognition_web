package pb;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import javax.mail.*;



public class MailSmsSender {
	
	private static class SMTPAuthenticator extends Authenticator {
		private PasswordAuthentication authentication;

		public SMTPAuthenticator(String login, String password) {
			authentication = new PasswordAuthentication(login, password);
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
	public static void sms() {

		try {
//			String recipient = "+441234567890";
//			String message = " Greetings from Mr. Gupta! Have a nice day!";
//			String username = "admin";
//			String password = "abc123";
//			String originator = "+440987654321";
			String smsUser = "mukto_user";
			String smsPassword = "RbaQuYng";
			String phoneNumber ="+8801707555436";
			String content = "intelligent+human+being";
			final String requestUrl = "http://bulksms.teletalk.com.bd/link_sms_send.php?op=SMS" +
                    "&user=" + smsUser +
                    "&pass=" + smsPassword +
                    "&mobile=" + phoneNumber +
                    "&charset=UTF-8" +
                    "&sms=" + content;
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			System.out.println(uc.getResponseMessage());
			uc.disconnect();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void mail() {
		try {
			String from = "grsa2ibd@gmail.com";
			// String from = "xyz.com";
			String to = "vampireegg@gmail.com";
			String subject = "Dummy mail Subject.";
			String message = "Mail Text.";
			String login = "grsa2ibd@gmail.com";
			String password = "grs@a2!bd";
			// String login = "xyz.com";
			// String password = "password";

			Properties props = new Properties();
			props.setProperty("mail.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.starttls.enable", "true");
			//props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			 props.put("mail.smtp.socketFactory.port", "465");
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "false");
			
			

			Authenticator auth = new SMTPAuthenticator(login, password);

			Session session = Session.getInstance(props, auth);

			// Session session = Session.getDefaultInstance(props);

			MimeMessage msg = new MimeMessage(session);

			try {
				msg.setText(message);
				msg.setSubject(subject);
				msg.setFrom(new InternetAddress(from));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				System.out.println("trying to send mail");
				Transport transport = session.getTransport("smtp");
				transport.connect(null, login, password);
				transport.send(msg);
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
