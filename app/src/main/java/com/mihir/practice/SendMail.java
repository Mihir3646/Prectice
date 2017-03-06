package com.mihir.practice;

import android.os.Build;
import android.os.StrictMode;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author mihir
 * @description Used for Sending E-Mail
 */
public class SendMail {
    private String from;
    private String to;
    private String subject;
    private String password;
    private String text;

    /**
     * @param from
     * @param to
     * @param subject
     * @param text
     * @param password
     * @description constructor of {@link}SendMail
     */
    public SendMail(String from, String to, String subject, String text, String password) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.password = password;
    }

    /**
     * @throws Exception
     * @description Method for Send E-Mail
     */
    public void send() throws Exception {
        System.out.println("Send Mail");
        try {
            if (Build.VERSION.SDK_INT > 14) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            System.out.println("MAIL");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
