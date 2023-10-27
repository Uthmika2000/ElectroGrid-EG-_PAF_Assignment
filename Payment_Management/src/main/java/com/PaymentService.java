package com; 

import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Payments") 
public class PaymentService{ 
	
			Payment paymentObj = new Payment(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readPayments() 
			 { 
				return paymentObj.readPayments(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertPayment(@FormParam("payment_code") String payment_code, 
									 @FormParam("customer_id") String customer_id, 
									 @FormParam("month_of_bill") String month_of_bill, 
									 @FormParam("bill_amount") String bill_amount,
									 @FormParam("date") String date){ 
				
				String output = paymentObj.insertPayment(payment_code, customer_id, month_of_bill, bill_amount, date); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updatePayment(String paymentData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
				//Read the values from the JSON object
				String payment_id = paymentObject.get("payment_id").getAsString(); 
				String payment_code = paymentObject.get("payment_code").getAsString(); 
				String customer_id = paymentObject.get("customer_id").getAsString(); 
				String month_of_bill = paymentObject.get("month_of_bill").getAsString(); 
				String bill_amount = paymentObject.get("bill_amount").getAsString(); 
				String date = paymentObject.get("date").getAsString(); 
				String output = paymentObj.updatePayment(payment_id, payment_code, customer_id, month_of_bill, bill_amount, date); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deletePayment(String paymentData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <payment id>
				String payment_id = doc.select("payment_id").text(); 
				String output = paymentObj.deletePayment(payment_id); 
				return output; 
			}

			
					


}
