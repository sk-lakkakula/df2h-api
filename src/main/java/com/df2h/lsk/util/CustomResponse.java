package com.df2h.lsk.util;

import org.json.simple.JSONObject;

public class CustomResponse {

	
	private String responseCode;
	private String responseMessage;
	private JSONObject responseData;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public JSONObject  getResponseData() {
		return responseData;
	}
	public void setResponseData(JSONObject responseData) {
		this.responseData = responseData;
	}
	
	@Override
	public String toString() {
		return "CustomResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseData=" + responseData + "]";
	}
	
}
