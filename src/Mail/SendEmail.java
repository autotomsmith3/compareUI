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
	public static void sendEmails(String gmailUsername, String gmailPassword, String[] toEmails, String Subject,
			String content) {
//		final String username = "sautotomsmithjj@gmail.com";
//		final String password = "Autodata1";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmailUsername, gmailPassword);
			}
		});
		int emailNumber = toEmails.length;
		// Integer.toString(emailNumber)

		try {
			for (int i = 0; i <= emailNumber - 1; i++) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(gmailUsername));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails[i]));
				message.setSubject(Subject);
				message.setText(content);
				Transport.send(message);
			}
			System.out.println("\n Sent " + (emailNumber) + " Email(s). Done!");
		} catch (MessagingException e) {
			System.out.println("\n Failed to send " + (emailNumber) + " Email(s). Done!");
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		String gmailUsername = "sautotomsmithjj@gmail.com";
		String gmailPassword = "Autodata1";
		String[] sendEmailsList = { "lucas.zhou@autodatasolutions.com" };// "lucas.zhou@autodatasolutions.com", "sautotomsmithjj@gmail.com"
//		int emailNumber = emails.length;
		String Subject = "Application Failure Alert Testing...ignore please.";
		String content = "Hello,\n\n Please ignore this email. This is testing alert from monitor application!";

		sendEmails(gmailUsername, gmailPassword, sendEmailsList, Subject, content);
		System.out.println("\n\n*****************Complete!*******************\n");
	}

}
