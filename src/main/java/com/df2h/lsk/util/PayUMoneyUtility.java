package com.df2h.lsk.util;



import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.df2h.lsk.payumoney.pojo.OrderDetails;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
@Component
public class PayUMoneyUtility {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private Integer error;

	@Autowired
	CoreUtility coreUtility;
	public boolean empty(String s) {
		LOGGER.info("Check if Empty : "+s);
		boolean status = false;

		if (s == null || s.trim().equals("")) {
			status = true;
		} else {
			status = false;
		}
		LOGGER.info("Return status : "+status);
		return status;
	}

	public Map<String,String> validateOrderDetails (OrderDetails orderDetails){
		LOGGER.info("Performing validateGatewayOrder ..");
		Map<String,String> fieldErrorsMap = new HashMap<>();
		if(orderDetails!=null){
			if(!(orderDetails.getName()!=null && orderDetails.getName().length()>0))
				fieldErrorsMap.put("Name", "Name Cant be left blank");

			if(!(orderDetails.getEmail()!=null && orderDetails.getEmail().length()>0))
				fieldErrorsMap.put("Email", "Email Cant be left blank");
			else if(!(orderDetails.getEmail()!=null && orderDetails.getEmail().length()>0 && coreUtility.isValidEmail(orderDetails.getEmail()))){
				fieldErrorsMap.put("Email", "Email is Invalid");
			}

			if(!(orderDetails.getEmail()!=null && orderDetails.getEmail().length()>0))
				fieldErrorsMap.put("Email", "Email Cant be left blank");

			if(!(orderDetails.getFirstName()!=null && orderDetails.getFirstName().length()>0))
				fieldErrorsMap.put("FirstName", "FirstNameCant be left blank");

			if(!(orderDetails.getProductInfo()!=null && orderDetails.getProductInfo().length()>0))
				fieldErrorsMap.put("ProductName", "ProductName Cant be left blank");

			if(!(orderDetails.getSurl()!=null && orderDetails.getSurl().length()>0))
				fieldErrorsMap.put("Surl", "Surl Cant be left blank");

			if(!(orderDetails.getFurl()!=null && orderDetails.getFurl().length()>0))
				fieldErrorsMap.put("Furl", "Furl Cant be left blank");

			if(!(orderDetails.getPhone()!=null && orderDetails.getPhone().length()>0))
				fieldErrorsMap.put("Phone", "Phone Cant be left blank");

			if(!(orderDetails.getPhone()!=null && orderDetails.getPhone().length()==10))
				fieldErrorsMap.put("Phone", "Phone is In Valid");

			if(!(orderDetails.getAmount()!=null ))
				fieldErrorsMap.put("Amount", "Amount Cant be left blank");

			if(!(orderDetails.getKey()!=null && orderDetails.getKey().length()>0))
				fieldErrorsMap.put("Key", "Key Cant be left blank");

			if(!(orderDetails.getTxnId()!=null && orderDetails.getTxnId().length()>0))
				fieldErrorsMap.put("TransactionID", "TransactionID Cant be left blank");

			if(!(orderDetails.getMerchantId()!=null && orderDetails.getMerchantId().length()>0))
				fieldErrorsMap.put("MerchantId", "MerchantId Cant be left blank");
		}else{
			fieldErrorsMap.put("EmptyGatewayOrder", "Entire orderDetails is Empty");  
		}
		LOGGER.info("fieldErrorsMap :"+fieldErrorsMap);
		return fieldErrorsMap;
	}



	public String hashCal(String type, String str) {
		LOGGER.info("Generating Hash Value . type :"+type+" , str : "+str);
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		LOGGER.info("Returning the Generated Hash Value : "+hexString.toString());
		return hexString.toString();
	}

	/*protected Map<String, String> hashCalMethod(OrderDetails orderDetails)
			throws ServletException, IOException {
		String key = "";
		String salt = "";
		String action1 = "";
		String base_url = "https://sandboxsecure.payu.in";
		error = 0;
		String hashString = "";
		Enumeration paramNames = request.getParameterNames();
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> urlParams = new HashMap<String, String>();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			params.put(paramName, paramValue);
		}
		String txnid = "";
		if (empty(params.get("txnid"))) {
			Random rand = new Random();
			String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
			txnid = rndm;
			params.remove("txnid");
			params.put("txnid", txnid);
			txnid = hashCal("SHA-256", rndm).substring(0, 20);
		} else {
			txnid = params.get("txnid");
		}


		String txn = "abcd";
		String hash = "";
		String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
		String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		if (empty(params.get("hash")) && params.size() > 0) {
			if (empty(params.get("key")) || empty(txnid) || empty(params.get("amount")) || empty(params.get("firstname")) || empty(params.get("email")) || empty(params.get("phone")) || empty(params.get("productinfo")) || empty(params.get("surl")) || empty(params.get("furl")) || empty(params.get("service_provider"))) {
				error = 1;
			} else {

				String[] hashVarSeq = hashSequence.split("\\|");
				for (String part : hashVarSeq) {
					if (part.equals("txnid")) {
						hashString = hashString + txnid;
						urlParams.put("txnid", txnid);
					} else {
						hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part).trim());
						urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
					}
					hashString = hashString.concat("|");
				}
				hashString = hashString.concat(salt);
				hash = hashCal("SHA-512", hashString);
				action1 = base_url.concat("/_payment");
				String[] otherPostParamVarSeq = otherPostParamSeq.split("\\|");
				for (String part : otherPostParamVarSeq) {
					urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
				}

			}
		} else if (!empty(params.get("hash"))) {
			hash = params.get("hash");
			action1 = base_url.concat("/_payment");
		}

		urlParams.put("hash", hash);
		urlParams.put("action", action1);
		urlParams.put("hashString", hashString);
		return urlParams;
	}
*/
	public static void trustSelfSignedSSL() {
		try {
			final SSLContext ctx = SSLContext.getInstance(
					"TLS");
			final X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
					// do nothing
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
					// do nothing
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[]{tm}, null);
			SSLContext.setDefault(ctx);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
}
