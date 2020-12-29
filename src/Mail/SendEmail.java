package Mail;

import java.io.IOException;
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

import competitiveCompareUI.competitiveCompareUIController;

public class SendEmail {
	public static void sendEmails(String sendToEmail, String gmailUsername, String gmailPassword, String[] toEmails,
			String Subject, String content) {
		if (sendToEmail.equalsIgnoreCase("Yes")) {
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
	}

	public void SendAlertEmail(String env, String brand, String urlString, String tc) {
		Properties prop = new Properties();
		try {
			prop.load(competitiveCompareUIController.class.getClassLoader()
					.getResourceAsStream("./data/competitiveCompareGoAllTrims.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String sendToEmail = prop.getProperty("CompetitiveCompare.sendToEmail");
		String gmailUsername = prop.getProperty(env + ".gmailUsername");
		String gmailPassword = prop.getProperty(env + ".gmailPassword");
		String sendEmailsList[] = fetchOneDemArrayFromPropFile(env + ".sendEmailsList", prop);
		String Subject = prop.getProperty(env + ".Subject");
		String content = prop.getProperty(env + ".content");
		sendEmails(sendToEmail, gmailUsername, gmailPassword, sendEmailsList, env + " - " + brand + " - " + Subject,
				content + "\n\n\n URL: " + urlString);
		System.out.println("\n\n*****************Sending Email is Complete!*******************\n");
	}

	private static String[] fetchOneDemArrayFromPropFile(String propertyName, Properties propFile) {
		// get array split up by the colin
		String a[] = propFile.getProperty(propertyName).split(",");
		return a;
	}

	public static void main(String[] args) {
		String sendToEmail = "Yes";
		String gmailUsername = "sautotomsmithjj@gmail.com";
		String gmailPassword = "Autodata1";
		String[] sendEmailsList = { "lucas.zhou@autodatasolutions.com" };
		String Subject = "Application Failure Alert Testing...ignore please.";
		String content = "Hello,\n\n Please ignore this email. This is testing alert from monitor application!";
		String urlString = "http://qa1-compare.gm-test.autodata.tech/kia/ca/vehicle/#/select/primary/compare";
		sendEmails(sendToEmail, gmailUsername, gmailPassword, sendEmailsList, Subject,
				content + "\n\n\n URL: " + urlString);
		System.out.println("\n\n*****************Complete!*******************\n");
	}

}
