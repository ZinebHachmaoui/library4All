package com.autoformation.libraryForAll;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMail {

	public static boolean envoyerMailSMTP(boolean debug, String contenu, String destinataire, String subject, String emmeteur) {

		boolean result = false;
		try {
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "bootes.ns.local");
			Session session = Session.getDefaultInstance(prop, null);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emmeteur));
			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress(destinataire);
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject(subject);
			message.setText(contenu);
			message.setSentDate(new Date());
			session.setDebug(debug);
			Transport.send(message);
			result = true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
