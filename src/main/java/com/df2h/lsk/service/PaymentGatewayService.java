package com.df2h.lsk.service;

import java.util.Map;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df2h.lsk.config.PayUMoneyProperties;
import com.df2h.lsk.pojo.EnvironmentDetails;
import com.df2h.lsk.pojo.OrderDetails;
import com.df2h.lsk.util.CoreUtility;
import com.df2h.lsk.util.PayUMoneyUtility;

@Service("paymentGatewayService")
public class PaymentGatewayService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	com.df2h.lsk.util.PayUMoneyUtility payUMoneyUtility ;

	@Autowired
	com.df2h.lsk.config.PayUMoneyProperties paymentGatewayProperties;

	@Autowired
	com.df2h.lsk.util.CoreUtility coreUtility;

	String clientId,clientSecretId,PayUMoneyUrl ,grantType;

	public String generateRequestHashCode(com.df2h.lsk.payumoney.pojo.OrderDetails orderDetails){
		LOGGER.info("Exec generateRequestHashCode PaymentGatewayService");
		String serverCalculatedHash = "";
		String hashSequenceWithProperties = "";
		String txnId ="";
		Map<String,String> orderDetailsErrorsMap = null;

        if (payUMoneyUtility.empty(orderDetails.getTxnId())) {
            Random rand = new Random();
            String rndmTxnNo = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnId = payUMoneyUtility.hashCal("SHA-512", rndmTxnNo);
            txnId = txnId .substring(0, 20);
            LOGGER.info("Generated TXN ID : "+txnId);
            orderDetails.setTxnId(txnId);
            
        } 
		//		HASH SEQ: 	 "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|salt";
		orderDetailsErrorsMap = payUMoneyUtility.validateOrderDetails(orderDetails);
		if(!orderDetailsErrorsMap.isEmpty()){
//			txnId = randomNumGeneration();
			orderDetails.setTxnId(txnId);
			hashSequenceWithProperties = orderDetails.getKey().concat("|").concat(orderDetails.getTxnId().concat("|").concat(orderDetails.getAmount().concat("|").concat(orderDetails.getProductInfo()
					.concat("|").concat(orderDetails.getFirstName().concat("|").concat(orderDetails.getEmail().concat("|").concat(paymentGatewayProperties.getTestEnvironmentSalt()))))));
			LOGGER.info("hashSequenceWithProperties  : "+hashSequenceWithProperties);
			serverCalculatedHash=  payUMoneyUtility.hashCal("SHA-512", hashSequenceWithProperties);
		}

		LOGGER.info("Exec generateHashCode :--> serverCalculatedHash"+serverCalculatedHash);
		return serverCalculatedHash;
	}

	public String generateResponseHashCode(com.df2h.lsk.payumoney.pojo.OrderDetails orderDetails){
		LOGGER.info("Exec generateResponseHashCode PaymentGatewayService");
		String serverCalculatedResponseHash = "";
		String hashSequenceWithProperties = "";
		String txnId ="";
		Map<String,String> orderDetailsErrorsMap = null;
		if (payUMoneyUtility.empty(orderDetails.getTxnId())) {
            Random rand = new Random();
            String rndmTxnNo = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnId = payUMoneyUtility.hashCal("SHA-512", rndmTxnNo);
            txnId = txnId .substring(0, 20);
            LOGGER.info("Generated TXN ID : "+txnId);
            orderDetails.setTxnId(txnId);
        }
		//		HASH SEQ: 	 salt|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|email|firstname|productinfo|amount|txnid|key
		orderDetailsErrorsMap = payUMoneyUtility.validateOrderDetails(orderDetails);
		if(!orderDetailsErrorsMap.isEmpty()){
			orderDetails.setTxnId(txnId);
			hashSequenceWithProperties = orderDetails.getKey().concat("|").concat(orderDetails.getTxnId().concat("|").concat(orderDetails.getAmount().concat("|").concat(orderDetails.getProductInfo()
					.concat("|").concat(orderDetails.getFirstName().concat("|").concat(orderDetails.getEmail().concat("|").concat(paymentGatewayProperties.getTestEnvironmentSalt()))))));
			hashSequenceWithProperties = paymentGatewayProperties.getTestEnvironmentSalt().concat("|").concat(orderDetails.getEmail().concat("|").concat(orderDetails.getFirstName().concat("|")
					.concat(orderDetails.getProductInfo().concat("|").concat("|").concat(orderDetails.getAmount().concat("|").concat(orderDetails.getTxnId().concat("|").concat(orderDetails.getKey()))))));
			LOGGER.info("hashSequenceWithProperties  : "+hashSequenceWithProperties);
			serverCalculatedResponseHash=  payUMoneyUtility.hashCal("SHA-512", hashSequenceWithProperties);
		}

		serverCalculatedResponseHash =  payUMoneyUtility.hashCal("SHA-512", serverCalculatedResponseHash);
		LOGGER.info("Exec generateHashCode :--> serverCalculatedHash"+serverCalculatedResponseHash);
		return serverCalculatedResponseHash;
	}

	public String randomNumGeneration(){
		LOGGER.info("Exce ... randomNumGeneration for TXN ID : ");

		Random rand = new Random();
		String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
		LOGGER.info("Exiting ... randomNumGeneration  TXN ID: "+rndm);
		return rndm;
	}
}
