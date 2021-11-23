package com.eunwoosoft.comm.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public class MailSendUtil {
	private static final Logger LOG = LoggerFactory.getLogger(MailSendUtil.class);
	
	
	public void sendMailException(FwkParameterMap parameterMap) throws Exception {

		final String FROM = FwkMessageUtil.getMessage("smtp.mail.from");

		final String FROMNAME = FwkMessageUtil.getMessage("EW.EPRO.ENTRPS_NM");

		final String TO = parameterMap.getString("P_TO_MAIL");

		final String SMTP_USERNAME = FwkMessageUtil.getMessage("smtp.mail.from");	// 발신자 사용자 ID
		final String SMTP_PASSWORD = FwkMessageUtil.getMessage("smtp.mail.password");	//발신자 사용자 비밀번호 

		final String HOST = FwkMessageUtil.getMessage("smtp.mail.server.ip");
		
		final String PORT = FwkMessageUtil.getMessage("smtp.mail.server.port");

		final String SUBJECT = parameterMap.getString("P_MSG_TTL");	// 제목
		final String BODY = parameterMap.getString("P_MSG_CNTN");	// 내용

		Properties props = System.getProperties();
		
		props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        
        LOG.debug("@@@ MailSendUtil >> TO ==> "+ TO);
        LOG.debug("@@@ MailSendUtil >> FROM ==> "+ FROM);
        LOG.debug("@@@ MailSendUtil >> SUBJECT ==> "+ SUBJECT);
        LOG.debug("@@@ MailSendUtil >> BODY ==> "+ BODY);
        
        Session session = Session.getDefaultInstance(props);
 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html;charset=euc-kr");
        
        Transport transport = session.getTransport();
 
//        try {
        	LOG.debug("@@@ sendMail ::: Sending...");
            
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients()); 
 
            LOG.debug("@@@ sendMail ::: Email sent!");
//        } catch (Exception ex) {
//            ex.printStackTrace();
// 
//        } finally {
//            transport.close();
//        }

//		props.put("mail.debug", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", 25);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.transport.protocol", "smtp");
		
		/**	smtp: **/
		/**	starttls.enable: false **/
		/**	host:keri.re.kr **/
		/**	port: 25 **/
		/**	user:noreply@keri.re.kr **/
		/**	password: KeriDev$*1 **/

		/*String fromMail = "user:noreply@keri.re.kr";
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", "true");
		
		Authenticator auth = new MyAuthentication();
		Session session = Session.getDefaultInstance(props, auth);
		
//		String fromMail = parameterMap.getString("P_FROM_MAIL");
		String toMail = parameterMap.getString("P_TO_MAIL");
			
		MimeMessage msg = new MimeMessage(session);
		msg.setSentDate(new Date());
		InternetAddress fromAddr = new InternetAddress(fromMail);
		msg.setFrom(fromAddr);
		InternetAddress toAddr = new InternetAddress(toMail);
		msg.setRecipient(Message.RecipientType.TO, toAddr);
		
		msg.setSubject(parameterMap.getString("P_MAIL_SUBJ"));
		msg.setText(parameterMap.getString("P_MAIL_TEXT"));
		msg.setHeader("content-Type", "text/html; charset=UTF-8");
		
		Transport.send(msg);*/
		
	} 
	

	
	public void sendMail(FwkParameterMap parameterMap) throws Exception {

		final String FROM = FwkMessageUtil.getMessage("smtp.mail.from");

		final String FROMNAME = FwkMessageUtil.getMessage("EW.EPRO.ENTRPS_NM");

		final String TO = parameterMap.getString("P_TO_MAIL");

		final String SMTP_USERNAME = FwkMessageUtil.getMessage("smtp.mail.from");	// 발신자 사용자 ID
		final String SMTP_PASSWORD = FwkMessageUtil.getMessage("smtp.mail.password");	//발신자 사용자 비밀번호 

		final String HOST = FwkMessageUtil.getMessage("smtp.mail.server.ip");
		
		final String PORT = FwkMessageUtil.getMessage("smtp.mail.server.port");

		final String SUBJECT = parameterMap.getString("P_MSG_TTL");	// 제목
		final String BODY = parameterMap.getString("P_MSG_CNTN");	// 내용

		Properties props = System.getProperties();
		
		props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        
        LOG.debug("@@@ MailSendUtil >> TO ==> "+ TO);
        LOG.debug("@@@ MailSendUtil >> FROM ==> "+ FROM);
        LOG.debug("@@@ MailSendUtil >> SUBJECT ==> "+ SUBJECT);
        LOG.debug("@@@ MailSendUtil >> BODY ==> "+ BODY);
        
        Session session = Session.getDefaultInstance(props);
 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html;charset=euc-kr");
        
        Transport transport = session.getTransport();
 
        try {
        	LOG.debug("@@@ sendMail ::: Sending...");
            
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients()); 
 
            LOG.debug("@@@ sendMail ::: Email sent!");
        } catch (Exception ex) {
            ex.printStackTrace();
 
        } finally {
            transport.close();
        }

//		props.put("mail.debug", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", 25);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.transport.protocol", "smtp");
		
		/**	smtp: **/
		/**	starttls.enable: false **/
		/**	host:keri.re.kr **/
		/**	port: 25 **/
		/**	user:noreply@keri.re.kr **/
		/**	password: KeriDev$*1 **/

		/*String fromMail = "user:noreply@keri.re.kr";
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", "true");
		
		Authenticator auth = new MyAuthentication();
		Session session = Session.getDefaultInstance(props, auth);
		
//		String fromMail = parameterMap.getString("P_FROM_MAIL");
		String toMail = parameterMap.getString("P_TO_MAIL");
			
		MimeMessage msg = new MimeMessage(session);
		msg.setSentDate(new Date());
		InternetAddress fromAddr = new InternetAddress(fromMail);
		msg.setFrom(fromAddr);
		InternetAddress toAddr = new InternetAddress(toMail);
		msg.setRecipient(Message.RecipientType.TO, toAddr);
		
		msg.setSubject(parameterMap.getString("P_MAIL_SUBJ"));
		msg.setText(parameterMap.getString("P_MAIL_TEXT"));
		msg.setHeader("content-Type", "text/html; charset=UTF-8");
		
		Transport.send(msg);*/
		
	} 
}

class MyAuthentication extends Authenticator {
	PasswordAuthentication pa;
	
	public MyAuthentication() {
//		String id = "";
//		String pwd = "";
		
		String id = "noreply@keri.re.kr";	
		String pwd = "KeriDev$*1";
		
		pa = new PasswordAuthentication(id, pwd);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
