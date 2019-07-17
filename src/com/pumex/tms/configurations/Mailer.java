package com.pumex.tms.configurations;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * @author Jinshad P.T.
 * @Date 28/01/2016
 */
public class Mailer {

	// private static String HOST = "smtp.gmail.com";
	private static String HOST = "gmail-smtp-msa.l.google.com";

	private static String USER = "tms.pumex@gmail.com";
	private static String PASSWORD = "tms@pumex";
	private static String PORT = "465";
	private static String FROM = "quoteappserver@gmail.com";
	private static String STARTTLS = "true";
	private static String AUTH = "true";
	private static String DEBUG = "false";
	private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static String SUBJECT = "Secret Mail";

	public static String sendMail(String email, String subject, String content) {

		Mailer mail = new Mailer();
		Address[] address = new InternetAddress[1];
		try {
			address[0] = new InternetAddress(email);

			mail.sendMail(address, null, content, subject, FROM);
			return "success";

		} catch (Exception e) {
			return "Error! Message : " + e;
		}

	}

	/*
	 * Method to send mail
	 * 
	 * @return sent status
	 */
	public static String sendMail(String email, String adminmail,
			String subject, String content) {

		Mailer mail = new Mailer();
		Address[] address = new InternetAddress[1];
		Address[] cc = new InternetAddress[1];
		try {
			address[0] = new InternetAddress(email);
			cc[0] = new InternetAddress(adminmail);

			mail.sendMail(address, cc, content, subject, FROM);
			return "succes";

		} catch (Exception e) {
			return "Error! Message : " + e;
		}

	}

	/*
	 * Method to send mail to multiple address
	 * 
	 * @return sent status
	 */
	public static String sendMail(Address[] address, String subject,
			String content) {

		Mailer mail = new Mailer();
		try {

			mail.sendMail(address, null, content, subject, FROM);
			return "succes";

		} catch (Exception e) {
			return "Error! Message : " + e;
		}

	}

	/*
	 * Method to send mail to multiple address and cc
	 * 
	 * @return sent status
	 */
	public synchronized void sendMail(Address[] mailTo, Address[] cc,
			String Content, String email_subject, String fromAddress)
			throws Exception {
		// String TO = mailTo;
		String TEXT = Content + " <br>";

		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);

		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.user", USER);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", STARTTLS);
		props.put("mail.smtp.debug", DEBUG);
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setContent(TEXT, "text/html");
			message.setSubject(email_subject);
			message.setFrom(new InternetAddress(fromAddress));
			message.addRecipients(RecipientType.TO, mailTo);
			if (cc != null)
				message.addRecipients(RecipientType.CC, cc);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception("Exception in Mail.sendMail" + e.getMessage());
		}
	}

}