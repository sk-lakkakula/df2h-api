package com.df2h.lsk.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.df2h.lsk.config.PayUMoneyProperties;
import com.df2h.lsk.payumoney.pojo.OrderDetails;
import com.df2h.lsk.service.PaymentGatewayService;
import com.df2h.lsk.util.CoreUtility;

@RestController
@CrossOrigin(origins = "*")
/**
 * 
 * @author slakkakula
 *
 */
public class PayUMoneyController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	PaymentGatewayService paymentGatewayService;

	@Autowired
	CoreUtility coreUtility;

	@Autowired
	PayUMoneyProperties paymentGatewayProperties;

	@Autowired
	Environment env;


	@GetMapping("/testurl")
	public JSONObject testUrl() {
		LOGGER.info("Exec testUrl");
		JSONObject json = new JSONObject ();
		json.put("mesg", "Success");
		return json;
	}

	@PostMapping("/reqHash/{env}")
	public JSONObject reqHash(@PathVariable (value="env") String env,@RequestBody OrderDetails orderDetails) {
		LOGGER.info("Exec reqHash");
		JSONObject json = new JSONObject ();
		String hashCode = "";
		if(env!=null){
			if(env.equalsIgnoreCase("test")){
				orderDetails.setKey(paymentGatewayProperties.getTestEnvironmentKey());
			}else{
				orderDetails.setKey(paymentGatewayProperties.getProductionEnvironmentKey());
			}
		}
		if(orderDetails!=null){
			hashCode = paymentGatewayService.generateRequestHashCode(orderDetails);
		}
		json.put("Hash Code : ", hashCode);
		return json;
	}
	
	@GetMapping("/getResponseHashCode")
	public JSONObject getResponseHashCode(@PathVariable (value="env") String env,@RequestBody OrderDetails orderDetails) {
		LOGGER.info("Exec getResponseHashCode");
		JSONObject json = new JSONObject ();
		if(env!=null){
			if(env.equalsIgnoreCase("test")){
				orderDetails.setKey(paymentGatewayProperties.getTestEnvironmentKey());
			}else{
				orderDetails.setKey(paymentGatewayProperties.getProductionEnvironmentKey());
			}
		}
		if(orderDetails!=null){
			paymentGatewayService.generateResponseHashCode(orderDetails);
		}
		json.put("mesg", "Success");
		
		return json;
	}
	/*@PostMapping("/createPO/{env}")
	public JSONObject createPO(@PathVariable (value="env") String env) {
		LOGGER.info("Exec createPaymentOrder env : "+env);
		JSONObject json = new JSONObject ();
		json.put("mesg", "From createPO");
		return json;
	}*/
	
	/*
	 * Create Payment Order @PathVariable(value = "id") Long userId
	 */
	/*@PostMapping("/paymentorder/{env}")
	public JSONObject createPaymentOrder(@PathVariable (value="env") String env,@RequestBody GatewayOrder gatewayOrder) {
		LOGGER.info("Exec createPaymentOrder env : "+env+", GatewayOrder  : "+gatewayOrder );
		JSONObject responseJSON = new JSONObject ();
		LOGGER.info("Else Block of instamojoObject Creating ..Failed Creating instamojoObject  ");
		responseJSON.put("responseData", null);
		responseJSON.put("responseCode", "1002");
		responseJSON.put("responseMessage", "Failed Creating instamojoObject  ");
		LOGGER.info("responseJSON from Controller :  "+responseJSON);
		return responseJSON;
	}
*/
	/*
	 * Create Payment Order @PathVariable(value = "id") Long userId
	 */
/*	@PostMapping("/paymentrequest/{env}")
	public JSONObject createPaymentRequest(@PathVariable (value="env") String env){//,@RequestBody PaymentRequest paymentRequest) {
		LOGGER.info("Exec createPaymentRequest env : "+env+", paymentRequest  : ");//+paymentRequest);

				ApiContext.Mode mode = null;
		String clientId = null;
		String clientSecretId = null;
		Instamojo instamojoObject = null; 
		 		JSONObject responseJSON = new JSONObject ();

		 if(env!=null ){
			 //			mode = paymentGatewayUtility.getApiContextModeByEnvironment(env);
		 }
		 clientId = paymentGatewayProperties.getTestEnvironmentClientId()!=null?paymentGatewayProperties.getTestEnvironmentClientId():"";
		clientSecretId = paymentGatewayProperties.getTestEnvironmentClientSecretId()!=null?paymentGatewayProperties.getTestEnvironmentClientSecretId():"";
		instamojoObject = paymentGatewayService.getInstamojoInstance(clientId, clientSecretId,mode);
		  
		 //		if(instamojoObject !=null){
		 LOGGER.info(" ******* Order Request has NO Error as part of Validations so Returning:-->  ");
		 if(true){
			 try {
				 //				PaymentRequest tempPaymentRequest = instamojoObject.createPaymentRequest(paymentRequest);
				 	if(tempPaymentRequest !=null ){
					LOGGER.info("tempPaymentRequest.getId"+tempPaymentRequest.getId());
					System.err.println("tempPaymentRequest.getId"+tempPaymentRequest.getId());
					responseJSON.put("responseData", tempPaymentRequest);
					responseJSON.put("responseCode", "0000");
					responseJSON.put("responseMessage", "Successfully Created Payment Request.");
				}else{
					responseJSON.put("responseData", null);
					responseJSON.put("responseCode", "1000");
					responseJSON.put("responseMessage", "Failed Created Payment Request.");
				}

			 } catch (HTTPException e) {
				 LOGGER.info("FAILED IN  : .Payment Request() : ");
				 System.err.println("FAILED IN Payment Request : ");
				 responseJSON.put("responseData", e);
				 responseJSON.put("responseCode", "1002");
				 responseJSON.put("responseMessage", "failed Creating Payment Request."+e.getStackTrace());
				 e.printStackTrace();
				 responseJSON.put("error", e.getMessage());

			 } 
			 catch (Exception e) {
				 System.err.println("FAILED IN Payment Request : ");
				 responseJSON.put("responseData", e);
				 responseJSON.put("responseCode", "1004");
				 responseJSON.put("responseMessage", "failed Creating Payment Request."+e.getStackTrace());
				 e.printStackTrace();
			 }

		 }else{
			 LOGGER.info(" ******* Order Request has Error ");
			 responseJSON.put("responseData", null);
			 responseJSON.put("responseCode", "1005");
			 responseJSON.put("responseMessage", "Env is Empty");

		 }
		 LOGGER.info("responseJSON from Controller :  "+responseJSON);
		 return responseJSON;
	}*/
}