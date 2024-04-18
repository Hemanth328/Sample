package pbs.feop.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailPizzword {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean senMail(String to, String subject, String body) {
		
		boolean isSent = false;
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailSender.send(mimeMessage);
			
			isSent = true;
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return isSent;
	}
}
