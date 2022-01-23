package com.df2h.lsk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:payumoney_gateway.properties")
public class PayUMoneyProperties {

	@Value("${payumoney.gateway.environment}")
	private String environment;
	
	@Value("${payumoney.gateway.environment.test.key}")
	private String testEnvironmentKey;
	
	@Value("${payumoney.gateway.environment.test.salt}")
	private String testEnvironmentSalt;
	
	@Value("${payumoney.gateway.environment.production.key}")
	private String productionEnvironmentKey;
	
	@Value("${payumoney.gateway.environment.production.salt}")
	private String productionEnvironmentSalt;
	
	@Value("${payumoney.gateway.environment.production.request.hash.sequence}")
	private String requestHashSequence;
	
	@Value("${payumoney.gateway.environment.production.response.hash.sequence}")
	private String responseHashSequence;
	
	
	/**
	 * @return the responseHashSequence
	 */
	public String getResponseHashSequence() {
		return responseHashSequence;
	}

	/**
	 * @param responseHashSequence the responseHashSequence to set
	 */
	public void setResponseHashSequence(String responseHashSequence) {
		this.responseHashSequence = responseHashSequence;
	}

	/**
	 * @return the requestHashSequence
	 */
	public String getRequestHashSequence() {
		return requestHashSequence;
	}

	/**
	 * @param requestHashSequence the requestHashSequence to set
	 */
	public void setRequestHashSequence(String requestHashSequence) {
		this.requestHashSequence = requestHashSequence;
	}

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * @return the testEnvironmentKey
	 */
	public String getTestEnvironmentKey() {
		return testEnvironmentKey;
	}

	/**
	 * @param testEnvironmentKey the testEnvironmentKey to set
	 */
	public void setTestEnvironmentKey(String testEnvironmentKey) {
		this.testEnvironmentKey = testEnvironmentKey;
	}

	/**
	 * @return the testEnvironmentSalt
	 */
	public String getTestEnvironmentSalt() {
		return testEnvironmentSalt;
	}

	/**
	 * @param testEnvironmentSalt the testEnvironmentSalt to set
	 */
	public void setTestEnvironmentSalt(String testEnvironmentSalt) {
		this.testEnvironmentSalt = testEnvironmentSalt;
	}

	/**
	 * @return the productionEnvironmentKey
	 */
	public String getProductionEnvironmentKey() {
		return productionEnvironmentKey;
	}

	/**
	 * @param productionEnvironmentKey the productionEnvironmentKey to set
	 */
	public void setProductionEnvironmentKey(String productionEnvironmentKey) {
		this.productionEnvironmentKey = productionEnvironmentKey;
	}

	/**
	 * @return the productionEnvironmentSalt
	 */
	public String getProductionEnvironmentSalt() {
		return productionEnvironmentSalt;
	}

	/**
	 * @param productionEnvironmentSalt the productionEnvironmentSalt to set
	 */
	public void setProductionEnvironmentSalt(String productionEnvironmentSalt) {
		this.productionEnvironmentSalt = productionEnvironmentSalt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentGatewayProperties [environment=" + environment + ", testEnvironmentKey=" + testEnvironmentKey
				+ ", testEnvironmentSalt=" + testEnvironmentSalt + ", productionEnvironmentKey="
				+ productionEnvironmentKey + ", productionEnvironmentSalt=" + productionEnvironmentSalt
				+ ", requestHashSequence=" + requestHashSequence + "]";
	}
	
	
	
}
