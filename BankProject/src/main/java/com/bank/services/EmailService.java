package com.bank.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public static void sendEmail(String subject, String message, String to) {
		
		String from = "*******";
		
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("******","******");
			}
			
		});
		
		session.setDebug(true);
		
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setFrom(from);
			
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			mimeMessage.setSubject(subject);
			
			mimeMessage.setText(message);
			
			Transport.send(mimeMessage);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
