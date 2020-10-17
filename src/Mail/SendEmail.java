package Mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void sendEmails(String gmailUsername, String gmailPassword, String[] emails, String Subject,
			String content) {
		final String username = "sautotomsmithjj@gmail.com";
		final String password = "Autodata1";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		int emailNumber = emails.length;
		// Integer.toString(emailNumber)

		try {
			for (emailNumber = 0; emailNumber <= 1; emailNumber++) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("sautotomsmithjj@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails[emailNumber]));
				message.setSubject("Application Failure Alert Testing...ignore please.");
				message.setText(content);
				Transport.send(message);
			}
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		String gmailUsername = "sautotomsmithjj@gmail.com";
		String gmailPassword = "Autodata1";
		String[] emails = { "lucas.zhou@autodatasolutions.com", "sautotomsmithjj@gmail.com" };
//		int emailNumber = emails.length;
		String Subject = "Application Failure Alert Testing...ignore please.";
		String content = "Hello,\n\n Please ignore this email. This is testing alert from monitor application!";

		sendEmails(gmailUsername, gmailPassword, emails, Subject, content);
		System.out.println("\n\n*****************Complete!*******************\n");
	}

}
