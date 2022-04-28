package com.ehcare.ehcare.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ehcare.ehcare.entities.Mail;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(Mail mail) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "Zencare") );
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());
			
			mailSender.send(mimeMessageHelper.getMimeMessage());
			
		}catch(MessagingException e) {
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		

		
	}
}
