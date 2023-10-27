package com; 

import model.Bill; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Bills") 
public class BillService{ 
	
			Bill billObj = new Bill(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readItems() 
			 { 
				return billObj.readBill(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertBill(@FormParam("billName") String billName, 
									 @FormParam("billAddress") String billAddress, 
									 @FormParam("billUnits") String billUnits, 
									 @FormParam("billUnitPrice") String billUnitPrice,
									 @FormParam("billTotPrice") String billTotPrice,
									 @FormParam("billDate") String billDate){ 
				
				String output = billObj.insertBill(billName, billAddress, billUnits, billUnitPrice,billTotPrice,billDate); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateBill(String billData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
				//Read the values from the JSON object
				String billID = billObject.get("billID").getAsString(); 
				String billName = billObject.get("billName").getAsString(); 
				String billAddress = billObject.get("billAddress").getAsString(); 
				String billUnits = billObject.get("billUnits").getAsString(); 
				String billUnitPrice = billObject.get("billUnitPrice").getAsString();
				String billTotPrice = billObject.get("billTotPrice").getAsString();
				String billDate = billObject.get("billDate").getAsString();
				String output = billObj.updateBill(billID, billName, billAddress, billUnits, billUnitPrice,billTotPrice,billDate); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteBill(String billData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <itemID>
				String billID = doc.select("billID").text(); 
				String output = billObj.deleteBill(billID); 
				return output; 
			}

			
					
}