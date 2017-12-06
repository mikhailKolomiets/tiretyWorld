package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by mihail on 27.09.16.
 */
public class MailSender {

    final private String senderAddres = "bufixteam@gmail.com";
    final private String passwordSA = "39glnbs94v";
    private String messageOb = "";


    public void send(String userEmail, String theme, String body) {
        Properties deliveryPropopties = new Properties();
        deliveryPropopties.put("mail.smtp.host", "smtp.gmail.com");
        deliveryPropopties.put("mail.smtp.port", "587");
        deliveryPropopties.put("mail.smtp.starttls.enable", "true");
        deliveryPropopties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(deliveryPropopties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddres, passwordSA);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            
            body = new String(body.getBytes(), "UTF-8");
            theme = new String(theme.getBytes(), "UTF-8");
            
            msg.setFrom(new InternetAddress(senderAddres));
            InternetAddress[] address = {new InternetAddress(userEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(theme);
            msg.setSentDate(new Date());
            msg.setText(body);
            
            System.out.println(theme + " <----------------- maybe else");

            Transport.send(msg);
        } catch (Exception mex) {
            mex.printStackTrace();
            messageOb = mex.toString();
            //messageOb = "Message send error";

        }
    }

    public String getMessageOb() {
        return messageOb;
    }
}
