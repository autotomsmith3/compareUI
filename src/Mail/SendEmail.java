package Mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import competitiveCompareUI.competitiveCompareUIController;

public class SendEmail {
	public static void sendEmails(String gmailUsername, String gmailPassword, String toEmails, int emailNumber,
			String Subject, String content) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmailUsername, gmailPassword);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gmailUsername));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails));
			message.setSubject(Subject);
			message.setText(content);
			Transport.send(message);
			System.out.println("\n Sent " + (emailNumber) + " Email(s). Done!");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("\n Failed to send " + (emailNumber) + " Email(s). Done!");
			throw new RuntimeException(e);
		}
	}

	public void SendAlertEmail(String env, String brand, String urlString, String tc) {
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompareGoAllTrims.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String gmailUsername = prop.getProperty(env + ".gmailUsername");
		String gmailPassword = prop.getProperty(env + ".gmailPassword");
		String sendEmailsList[] = fetchOneDemArrayFromPropFile(env + ".sendEmailsList", prop);
		int emailNumber = sendEmailsList.length;
		String sendEmailsString = prop.getProperty(env + ".sendEmailsList");

		String Subject = prop.getProperty(env + ".Subject");
		String content = prop.getProperty(env + ".content");
		sendEmails(gmailUsername, gmailPassword, sendEmailsString, emailNumber, env + " - " + brand + " - " + Subject,
				content + "\n\n\n URL: " + urlString);
		System.out.println("\n\n*****************Sending Email is Complete!*******************\n");
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

}
