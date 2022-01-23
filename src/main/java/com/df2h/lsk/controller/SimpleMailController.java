package com.df2h.lsk.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.config.EmailProperties;
import com.df2h.lsk.model.UserDetails;
import com.df2h.lsk.service.UserDetailsService;
import com.df2h.lsk.util.PasswordGenerator;

@RestController
public class SimpleMailController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private JavaMailSender sender;

	@Autowired
	EmailProperties emailProperties;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordGenerator passwordGenerator;
	
	@RequestMapping("/sendTemporaryPassword")
	public JSONObject sendMail(@RequestParam (name = "recipientsEmail" )String recipientsEmail) {
		JSONObject jsonObject =new JSONObject ();
		if(recipientsEmail!=null && recipientsEmail.length()>0){
			LOGGER.info("Executing sendMail in SimpleMailController Senders Address :"+recipientsEmail);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			try {
				helper.setTo(recipientsEmail);
				helper.setText(emailProperties.getTempPasswordTemplate()+"\n Temporary Password : "+passwordGenerator.generatePassword(10)+"\n"
						+"\n"+emailProperties.getCopyrightsContent()+"\n"+emailProperties.getSignature());
				helper.setSubject("Mail  - Direct Farm To Home ");
			} catch (MessagingException e) {
				e.printStackTrace();
				jsonObject .put("responseCode", "1001");
				jsonObject .put("responseMessage", "Failed sending Email ");
				jsonObject .put("responseData", "failure");
				return jsonObject;
			}
			LOGGER.info("Before sending Mail - SimpleMailController");
			sender.send(message);
			jsonObject .put("responseCode", "0000");
			jsonObject .put("responseMessage", "Successfuly sent Email..!");
			jsonObject .put("responseData", "success");
			return jsonObject;
			
		}else{
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed sending Email as Missing Senders Email Id");
			jsonObject .put("responseData", "failure");
			return jsonObject;
		}
	}


	@RequestMapping("/sendResetPaswordUrlToMail")
	public JSONObject sendResetPaswordUrlToMail(@RequestParam (name = "username" )String username) {
		LOGGER.info("Exec sendResetPaswordUrl");
		JSONObject jsonObject =new JSONObject ();
		UserDetails userDetails =null;
		String emailAddress = null;
		try {
			userDetails  = userDetailsService.fetchByUserDetailsByName(username);
			if(userDetails!=null && userDetails.getRole()!=null && userDetails.getRole().length()>0){
				switch (userDetails.getRole().toUpperCase()) {
				case "ADMINISTRATOR":
					emailAddress = userDetails.getAdministrator().getEmail();
					break;
				case "SUPPLIER":
					emailAddress = userDetails.getSupplier().getEmail();
					break;
				case "CONSUMER":
					emailAddress = userDetails.getConsumer().getEmail();
					break;
				case "FARMER":
					emailAddress = userDetails.getFarmer().getEmail();
					break;

				default:
					break;
				}
			}
			if(emailAddress!=null && emailAddress.length()>0){
				LOGGER.info("Sending the password reet LINK to registerd Email Address "+emailAddress);
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);

				helper.setTo(emailAddress);
				helper.setText(emailProperties.getTempPasswordTemplate()+"\n Temporary Password : "+passwordGenerator.generatePassword(10)+"\n"
						+"\\n"+emailProperties.getCopyrightsContent());
				helper.setSubject("Direct Farm To Home - Password Reset Link");
				LOGGER.info("Before sending Mail - SimpleMailController");
				sender.send(message);
				jsonObject .put("responseCode", "0000");
				jsonObject .put("responseMessage", "Successfuly sent Password reset Link to Registered Email..!");
				jsonObject .put("responseData", "success");		
			}else{
				LOGGER.info("Failed Sending the password reset LINK to registerd Email Address ");
				jsonObject .put("responseCode", "1000");
				jsonObject .put("responseMessage", "Failed Sending Password reset Link to Registered Email..!");
				jsonObject .put("responseData", "Failure");
			}
				
			

		} catch (MessagingException e) {
			e.printStackTrace();
			jsonObject .put("responseCode", "1001");
			jsonObject .put("responseMessage", "Failed sending Email ");
			jsonObject .put("responseData", "failure");
			return jsonObject;
		}
		
		return jsonObject;
	}

}
