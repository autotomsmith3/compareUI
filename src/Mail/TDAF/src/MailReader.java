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
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*; 
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeMultipart; 
import javax.mail.search.FlagTerm; 
import javax.mail.BodyPart; 
import javax.mail.MessagingException; 
import javax.mail.Multipart; 

import org.omg.CORBA.portable.InputStream;
  
public class MailReader extends ZljLibrary { 
    Folder inbox; 
    int waitEmailTime=2;//wait new email time until time out; The "3" = 30 seconds 
    int secs=10; //wait time; wait(secs); 
    boolean delelted=false; 
    // Constructor of the calss. 
	public MailReader(String emailSuj, String content1,String content2,String content3, String content4, String content5) throws IOException { 
        /* Set the mail properties */
        ZljLibrary td = new ZljLibrary(); 
        Properties props = System.getProperties(); 
        props.setProperty("mail.store.protocol", "imaps"); 
        try { 
            /* Create the session and get the store for read the mail. */
            Session session = Session.getDefaultInstance(props, null); 
            Store store = session.getStore("imaps"); 
            boolean contd = store.isConnected(); 
            // store.connect("imap.gmail.com","<mail ID> ", "<Password>"); 
            store.connect("imap.gmail.com", 993, "tdautof1@gmail.com", 
                    "Autodata1"); //Tried ports: 25,465,587 all failed in xp
            contd = store.isConnected(); 
            // Error shows when connecting: 
            // com.sun.mail.util.MailConnectException: Couldn't connect to host, 
            // port: imap.gmail.com, 993; timeout -1; 
            // nested exception is: 
            // java.net.ConnectException: Connection refused: connect 
            // The error means that the OS denies permission to open a TCP 
            // socket? 
            // Or is a firewall blocking outgoing connections? 
            // it could be an ipv6 problem (u may need 
            // -Djava.net.preferIPv4Stack=true), 
            // check here stackoverflow.com/questions/16110345/… 
            // proxy was blocking connection 
            // /* Mention the folder name which you want to read. */ 
            int countTime=0; 
            while ((countTime<waitEmailTime) && (!delelted)) { 
            inbox = store.getFolder("Inbox"); 
            System.out.println("No of Unread Messages : "
                    + inbox.getUnreadMessageCount()); 
  
            /* Open the inbox using store. */
            // inbox.open(Folder.READ_ONLY); 
            inbox.open(Folder.READ_WRITE); 
  
            /* Get the messages which is unread in the Inbox */
            // Message messages[] = inbox.search(new FlagTerm(new 
            // Flags(Flag.SEEN), false)); // works well; 
            Message[] messages = inbox.search(new FlagTerm( 
                    new Flags(Flag.SEEN), false)); 
  
            int msgslength = messages.length; 
            System.out.println("MESSAGE # = " + msgslength); 
  
            for (int i = 0; i < messages.length; i++) { 
                System.out.println("MESSAGE #" + (i + 1) + ":"); 
                String msgSub = messages[i].getSubject(); 
  
                if (msgSub.equals(emailSuj)) { 
                    // if (msgSub.equals("Your Recent Payment")){ 
                    // String msgCont=messages[i].getContent(); 
                    System.out.println("Msg Content = "
                            + messages[i].toString()); 
  
                    // // ?******************************** 
                    // // try { 
                    // // Object obj = messages[i].getContent(); 
                    // // Multipart mp = (Multipart)obj; 
                    // 
                    // Multipart mp =(Multipart)messages[i].getContent(); // 
                    // (Multipart) messages[i].getContent(); 
                    // // MimeBodyPart part = (MimeBodyPart)mp.getBodyPart(0); 
                    // // BodyPart bp = ((Multipart) 
                    // messages[i].getContent()).getBodyPart(0); 
                    // 
                    // for(int j=0;j<mp.getCount();j++) { 
                    // BodyPart bodyPart = mp.getBodyPart(j); 
                    // if (bodyPart.isMimeType("text/*")) { 
                    // String s = (String) 
                    // bodyPart.getContent();//.getContent(); 
                    // System.out.println("Msg Content = "+s); 
                    // } 
                    // } 
                    // 
                    // // for(int i=0;i<multipart.getCount();i++) { 
                    // // BodyPart bodyPart = multipart.getBodyPart(i); 
                    // // if (bodyPart.isMimeType("text/*")) { 
                    // // String s = (String) bodyPart.getContent(); 
                    // // } 
                    // // } 
                    // 
                    // 
                    // // } catch (Exception ex) { 
                    // // 
                    // System.out.println("Exception arise at the time of read mail"); 
                    // // } 
                    // 
                    // // ?******************************** 
  
                    System.out.println("SENT DATE:" + messages[i].getSentDate()); 
                    System.out.println("SUBJECT:" + messages[i].getSubject()); 
//                   System.out.println("CONTENT:" +bp.getContent().toString()); 
//                   System.out.println("CONTENT: "+messages[i].getContent()); 
  
                    // Message msg = inbox.getMessage(inbox.getMessageCount()); 
                    Object msgObj = messages[i].getContent(); 
                    String emailText = msgObj.toString(); 
                    BodyPart clearTextPart = null; 
  
                    // Multipart multipart = (Multipart) msgObj; 
  
                    String conType = messages[i].getContentType(); 
                    System.out.println("Email Content Type =:" + conType); 
                    boolean typeExist = messages[i].isMimeType("text/html"); 
  
                    if (typeExist) { 
                        boolean cont1 = emailText.contains(content1); 
                        boolean cont2 = emailText.contains(content2); 
                        boolean cont3 = emailText.contains(content3); 
                        boolean cont4 = emailText.contains(content4); 
                        boolean cont5 = emailText.contains(content5); 
                        if (cont1 && cont2 && cont3 && cont4 && cont5){ 
                            rwExcel(true, "Verify Email - "+emailSuj+" Contents", "All Contents are correct!"); 
                        }else if (!cont1){ 
                            rwExcel(false, "Verify Email - "+emailSuj+" Content1", content1); 
                        }else if (!cont2){ 
                            rwExcel(false, "Verify Email - "+emailSuj+" Content2", content1); 
                        }else if (!cont3){ 
                            rwExcel(false, "Verify Email - "+emailSuj+" Content3", content1); 
                        }else if (!cont4){ 
                            rwExcel(false, "Verify Email - "+emailSuj+" Content4", content1); 
                        }else if (!cont5){ 
                            rwExcel(false, "Verify Email - "+emailSuj+" Content5", content1); 
                        } 
                          
                          
                          
                        int beginIdx = emailText.indexOf("Payment Date:"); 
                        int endIdx = emailText.indexOf("Payment Amount:"); 
                        String wtdString = emailText.substring(beginIdx + 14, 
                                beginIdx + 24); 
  
                        try { 
                            cont5 = messages[i].isMimeType("text/html"); 
  
//                          cont5 = (msgObj instanceof Multipart);//not working 
  
                            // Multipart mp = (Multipart) msgObj; 
                            // Part part=mp.getBodyPart(0); 
                            // String msg=(String)part.getContent(); 
                        } catch (Exception ex) { 
                            System.out 
                                    .println("Exception arise at the time of read mail"); 
                            ex.printStackTrace(); 
                        } 
  
                        // getContent(messages[i]) ; 
  
                        // //You need to iterate through all multiparts, then 
                        // check MIME type of the Part in order to know if you 
                        // have to treat it like a text or an attachment. 
                        // for(int l=0;l<multipart.getCount();l++) { 
                        // BodyPart bodyPart = multipart.getBodyPart(l); 
                        // if (bodyPart.isMimeType("text/*")) { 
                        // String s = (String) bodyPart.getContent(); 
                        // } 
                        // } 
                        // //You need to iterate through all multiparts, then 
                        // check MIME type of the Part in order to know if you 
                        // have to treat it like a text or an attachment. 
  
                        System.out.println("CONTENT: " + emailText); 
                        System.out.println("CONTENT: "); 
  
                    } else { 
                        System.out.println("CONTENT: Not =="); 
                        rwExcel(false, "Verify Email Subject = "+emailSuj, "Email MimeType is NOT text/html!"); 
                    } 
  
                    messages[i].setFlag(Flags.Flag.DELETED, true); 
                    // messages[i].setFlag(Flag.DELETED, true); //works 
                    System.out.println(" i = " + i + ":  Subject = " + msgSub 
                            + "  is deleted!"); 
                    rwExcel(true, "Verify Email Subject = "+emailSuj, "Email has been deleted!"); 
                    i=messages.length; 
                    delelted=true; 
                } 
            } 
            countTime++; 
            if (!delelted) 
            Wait(secs); 
            }//End of while  
            if (countTime>=waitEmailTime){ 
                rwExcel(false, "Verify Email Subject = "+emailSuj, "Email does not exist!"); 
            } 
            // ?******************************** 
            // 
            // /* Use a suitable FetchProfile */ 
            // FetchProfile fp = new FetchProfile(); 
            // fp.add(FetchProfile.Item.ENVELOPE); 
            // fp.add(FetchProfile.Item.CONTENT_INFO); 
            // inbox.fetch(messages, fp); 
            // ?******************************** 
            try { 
  
                // printAllMessages(messages); 
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
            // e.printStackTrace(); 
            // System.exit(2); 
            rwExcel(false, "Try to connect Email Service", "Connection fails"); 
            return; 
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
  
    public void getContent(Message msg) throws Exception { 
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
  
    // original one 
    public void dumpPart(Part p) throws Exception { 
        // Dump input stream .. 
        InputStream is = (InputStream) p.getInputStream(); 
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
  
    // From: 
    // http://stackoverflow.com/questions/13331989/how-to-handle-multipart-alternative-mail-with-javamail 
    // } else if (cont instanceof Multipart) { 
    // try { 
    // Multipart mp = (Multipart) msg.getContent(); 
    // int mp_count = mp.getCount(); 
    // for (int b = 0; b < 1; b++) { 
    // dumpPart(mp.getBodyPart(b)); 
    // } 
    // } catch (Exception ex) { 
    // System.out.println("Exception arise at get Content"); 
    // ex.printStackTrace(); 
    // } 
    // } 
    // } 
    // 
    // 
    // public void dumpPart(Part p) throws Exception { 
    // email = null; 
    // String contentType = p.getContentType(); 
    // System.out.println("dumpPart" + contentType); 
    // InputStream is = p.getInputStream(); 
    // if (!(is instanceof BufferedInputStream)) { 
    // is = new BufferedInputStream(is); 
    // } 
    // int c; 
    // final StringWriter sw = new StringWriter(); 
    // while ((c = is.read()) != -1) { 
    // sw.write(c); 
    // } 
    // 
    // if (!sw.toString().contains("<div>")) { 
    // mpMessage = sw.toString(); 
    // getReferentie(mpMessage); 
    // } 
    // } 
  
    public static void main(String args[]) throws IOException { 
        // new MailReader("Your Recent Payment"); 
//        String subject0="Action Required - Confirm Email Update"; 
//        String content1="TDAF Email Disclaimer"; 
//        String content2="This message is to notify you that the email address within your"; 
//        String content3="If you are unable to click on the confirmation link below, copy and paste the URL location into your Internet browser."; 
//        String content4="Please note: Because email is not a secure method of communicating personal information, this email box does not accept replies. Please visit tdautofinance.com for answers to our most frequently asked questions."; 
//        String content5="TDAF Email Disclaimer"; 
      String subject0="Your Recently Deleted Payment"; 
      String content1="Dear FN313107 LN313107"; 
      String content2="Thank you for choosing TD Auto Finance."; 
      String content3=""; 
      String content4=""; 
      String content5="TDAF Email Disclaimer"; 
        new MailReader(subject0, content1, content2,content3,content4,content5); 
          
        // new MailReader("Your Recent Payment"); 
         subject0="Your Recent Payment"; 
         content1="Dear FN313107 LN313107,"; 
         content2="Thank you for scheduling your TD Auto Finance payment online. Your transaction information is listed below."; 
         content3="Payment Amount:"; 
         content4="Please allow 1 business day for payment processing and 2 business days for your payment to appear in your online payment history."; 
         content5="tdautofinance.com"; 
          
        new MailReader(subject0, content1, content2,content3,content4,content5); 
          
         subject0="Your Recently Deleted Payment"; 
         content1="Dear FN313107 LN313107,"; 
         content2="This message confirms that your scheduled online payment to TD Auto Finance has been deleted. Your transaction information is listed below."; 
         content3="tdautofinance.com"; 
         content4="Thank you for choosing TD Auto Finance."; 
         content5="Please note: Because email is not a secure method of communicating personal information, this email box does not accept replies. Please visit"; 
          
        new MailReader(subject0, content1, content2,content3,content4,content5); 
        // new MailReader("Available jobs in your area"); 
    } 
} 