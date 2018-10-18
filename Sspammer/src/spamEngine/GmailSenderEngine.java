package spamEngine;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import config.Config;
import gui.Gui;

public class GmailSenderEngine implements SpammerEngine {

	private Gui gui;
	private String emailAddress;
	private String messageText;
	private String subject;
	private int num;
	private Transport transport;
	private MimeMessage message;
	private Session session;

	public GmailSenderEngine(Gui gui, String emailAddress, String messageText,
	        String subject, int num) {
		this.gui = gui;
		this.emailAddress = emailAddress;
		this.messageText = messageText;
		this.subject = subject;
		this.num = num;
	}

	public boolean attack(boolean fireConstantly) {
		try {
			gui.addTextToNextLine("Begining attack on " + emailAddress);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
			        "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			session = Session.getDefaultInstance(props, null);
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Config.UserName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
			        emailAddress));
			message.setSubject(subject);
			message.setText(messageText);
			transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", Config.UserName,
			        Config.Password);
			int count = 0;
			if (fireConstantly) {
				while (send(count)) {
					count = count + 1;
				}
				gui.addTextToNextLine("The message\n\tsubject: " + subject
				        + "\n\t" + messageText + "\nwas sent " + (num)
				        + " times to " + emailAddress);
			}
			return true;
		} catch (Exception e) {
			try {
				transport.close();
			} catch (MessagingException e1) {
				return false;
			}
			return false;
		}
	}

	public boolean send(int count) {
		if (count < num) {
			try {

				transport.sendMessage(message, message.getAllRecipients());
				gui.addTextToNextLine((count + 1) + " message sent "
				        + (num - count - 1)
				        + " messages left to be sent:\tGmail");
				return true;
			} catch (MessagingException e) {
				return false;
			}
		}
		return false;
	}
}
