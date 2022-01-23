package com.df2h.lsk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:email.properties")
public class EmailProperties {
	/*
	 * email.temporary.password.reset.template = Please go ahead and use this temporary password to login and do reset your password.
	email.temporary.password.reset.password = \n Password:
	email.temporary.password.coprights.content = This is an automatically generated email \u2013 please do not reply to it. If you have any queries ,please email admin@directfarmtohome.com*/

	@Value("${email.temporary.password.reset.template}")
	private String tempPasswordTemplate;

	@Value("${email.temporary.password.reset.password}")
	private String temporaryPassword;

	@Value("${email.temporary.password.coprights.content}")
	private String copyrightsContent;

	
	@Value("${email.temporary.password.signature}")
	private String signature;
	
	
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the tempPasswordTemplate
	 */
	public String getTempPasswordTemplate() {
		return tempPasswordTemplate;
	}

	/**
	 * @param tempPasswordTemplate the tempPasswordTemplate to set
	 */
	public void setTempPasswordTemplate(String tempPasswordTemplate) {
		this.tempPasswordTemplate = tempPasswordTemplate;
	}

	/**
	 * @return the temporaryPassword
	 */
	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	/**
	 * @param temporaryPassword the temporaryPassword to set
	 */
	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}

	/**
	 * @return the copyrightsContent
	 */
	public String getCopyrightsContent() {
		return copyrightsContent;
	}

	/**
	 * @param copyrightsContent the copyrightsContent to set
	 */
	public void setCopyrightsContent(String copyrightsContent) {
		this.copyrightsContent = copyrightsContent;
	}


}
