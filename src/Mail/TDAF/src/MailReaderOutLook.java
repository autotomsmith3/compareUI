package Mail.TDAF.src;
//public class MailReader {
//
//}
/*
*  This is the code for read the unread mails from your mail account.
*  Requirements:
*      JDK 1.5 and above
*      Jar:mail.jar
*
*/
//package com.info.mail;
//package com.mail; // it's for send email code
import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

public class MailReaderOutLook {
	Folder inbox;

	// Constructor of the calss.
	public MailReaderOutLook(String emailSuj) throws IOException {
		/* Set the mail properties */
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			/* Create the session and get the store for read the mail. */
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			// store.connect("imap.gmail.com","<mail ID> ", "<Password>");
//			store.connect("owa.autodata.net", "lucas.zhou@autodata.net", "Znxdj19591112");  //connect time out. Not refused
			boolean contd=store.isConnected();
//			store.connect("casarray.london.autodata.net", "lucas.zhou@autodata.net", "Znxdj19591209");// origain one
			store.connect("outlook.office365.com", "zhoul@autodatacorp.org", "Znxdj19591209"); // lucas.zhou@autodata.net not working
//		  	store.connect("owa.autodata.net", 993, "lucas.zhou@autodata.net", "Znxdj19591209");
			
			/* Mention the folder name which you want to read. */
			inbox = store.getFolder("Inbox");
			System.out.println("No of Unread Messages : "
					+ inbox.getUnreadMessageCount());

			/* Open the inbox using store. */
//			inbox.open(Folder.READ_ONLY);
			inbox.open(Folder.READ_WRITE);

			/* Get the messages which is unread in the Inbox */
			Message messages[] = inbox.search(new FlagTerm(
					new Flags(Flag.SEEN), false));

			int msgslength = messages.length;
			System.out.println("MESSAGE # = " + msgslength);
			
			for (int i = 0; i < messages.length; i++) {
				System.out.println("MESSAGE #" + (i + 1) + ":");
				String msgSub=messages[i].getSubject();
				
				if (msgSub.equals(emailSuj)){
//				if (msgSub.equals("Your Recent Payment")){					
					//	String msgCont=messages[i].getContent();
					System.out.println("Msg Content = "); // messages[i].getContent());
					
					
////					?********************************
////					try {
////			        Object obj = messages[i].getContent();
////			        Multipart mp = (Multipart)obj;
//
//			        Multipart mp =(Multipart)messages[i].getContent(); // (Multipart) messages[i].getContent();
////			        MimeBodyPart part = (MimeBodyPart)mp.getBodyPart(0);
////			        BodyPart bp = ((Multipart) messages[i].getContent()).getBodyPart(0);
//			        
//			        for(int j=0;j<mp.getCount();j++) {
//			            BodyPart bodyPart = mp.getBodyPart(j);
//			            if (bodyPart.isMimeType("text/*")) {
//			                String s = (String) bodyPart.getContent();//.getContent();
//			                System.out.println("Msg Content = "+s);
//			            }
//			        }
//			        
////			        for(int i=0;i<multipart.getCount();i++) {
////			            BodyPart bodyPart = multipart.getBodyPart(i);
////			            if (bodyPart.isMimeType("text/*")) {
////			                String s = (String) bodyPart.getContent();
////			            }
////			        }     
//			        
//			        
////					} catch (Exception ex) {
////						System.out.println("Exception arise at the time of read mail");
////					}
//					
////					?********************************		        
			        
			        
			        System.out.println("SENT DATE:" + messages[i].getSentDate());
			        System.out.println("SUBJECT:" + messages[i].getSubject());
//			        System.out.println("CONTENT:" + bp.getContent().toString());
					
			        
			        
			        
					
//					messages[i].setFlag(Flags.Flag.DELETED, true); //works 2016.07.23
//					messages[i].setFlag(Flag.DELETED, true); //works 
					System.out.println(" i = "+i+":  Subject = " + msgSub+"  is deleted!");
	
				}
				

			}
			

			
//		?********************************		
//			
//			/* Use a suitable FetchProfile */
//			FetchProfile fp = new FetchProfile();
//			fp.add(FetchProfile.Item.ENVELOPE);
//			fp.add(FetchProfile.Item.CONTENT_INFO);
//			inbox.fetch(messages, fp);
//		?********************************	
			try {
	
//				printAllMessages(messages);
				inbox.close(true);
				store.close();
			} catch (Exception ex) {
				System.out.println("Exception arise at the time of read mail");
				ex.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	public void printAllMessages(Message[] msgs) throws Exception {
		int msgslength = msgs.length;
		System.out.println("MESSAGE #" + msgslength);

		for (int i = 0; i < msgs.length; i++) {
			System.out.println("MESSAGE #" + (i + 1) + ":");
			printEnvelope(msgs[i]);
			

		}
	}

	/* Print the envelope(FromAddress,ReceivedDate,Subject) */
	public void printEnvelope(Message message) throws Exception {
		Address[] a;
		// FROM
		if ((a = message.getFrom()) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("FROM: " + a[j].toString());
			}
		}
		// TO
		if ((a = message.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("TO: " + a[j].toString());
			}
		}
		String subject = message.getSubject();
		Date receivedDate = message.getReceivedDate();
		String content = message.getContent().toString();
		System.out.println("Subject : " + subject);
		System.out.println("Received Date : " + receivedDate.toString());
		System.out.println("Content : " + content);
		getContent(message);
	}

	public void getContent(Message msg) {
		try {
			String contentType = msg.getContentType();
			System.out.println("Content Type : " + contentType);
			Multipart mp = (Multipart) msg.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++) {
				dumpPart(mp.getBodyPart(i));
			}
		} catch (Exception ex) {
			System.out.println("Exception arise at get Content");
			ex.printStackTrace();
		}
	}

	public void dumpPart(Part p) throws Exception {
		// Dump input stream ..
		InputStream is = p.getInputStream();
		// If "is" is not already buffered, wrap a BufferedInputStream
		// around it.
		if (!(is instanceof BufferedInputStream)) {
			is = new BufferedInputStream(is);
		}
		int c;
		System.out.println("Message : ");
		while ((c = is.read()) != -1) {
			System.out.write(c);
		}
	}

	public static void main(String args[]) throws IOException {
		System.out.println("Started:");
		new MailReaderOutLook("Reset Password for VDVI");
	}
}
