package com.df2h.lsk.pojo;

public class EnvironmentDetails {

	String clientId,clientSecretId,instamojoUrl,grantType;

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecretId
	 */
	public String getClientSecretId() {
		return clientSecretId;
	}

	/**
	 * @param clientSecretId the clientSecretId to set
	 */
	public void setClientSecretId(String clientSecretId) {
		this.clientSecretId = clientSecretId;
	}

	/**
	 * @return the instamojoUrl
	 */
	public String getInstamojoUrl() {
		return instamojoUrl;
	}

	/**
	 * @param instamojoUrl the instamojoUrl to set
	 */
	public void setInstamojoUrl(String instamojoUrl) {
		this.instamojoUrl = instamojoUrl;
	}

	/**
	 * @return the grantType
	 */
	public String getGrantType() {
		return grantType;
	}

	/**
	 * @param grantType the grantType to set
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnvironmentDetails [clientId=" + clientId + ", clientSecretId=" + clientSecretId + ", instamojoUrl="
				+ instamojoUrl + ", grantType=" + grantType + "]";
	}
	
	
}
