package com; 

import model.Power; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Powers") 
public class PowerService{ 
	
			Power powerObj = new Power(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readPowers() 
			 { 
				return powerObj.readPowers(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertPower(@FormParam("numberOfUnits") String numberOfUnits, 
									 @FormParam("DomesticPrice") String DomesticPrice, 
									 @FormParam("CommercialPrice") String CommercialPrice, 
									 @FormParam("Date") String Date){ 
				
				String output = powerObj.insertPower(numberOfUnits, DomesticPrice, CommercialPrice, Date); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updatePower(String powerData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject powerObject = new JsonParser().parse(powerData).getAsJsonObject(); 
				//Read the values from the JSON object
				String rateID = powerObject.get("rateID").getAsString(); 
				String numberOfUnits = powerObject.get("numberOfUnits").getAsString(); 
				String DomesticPrice = powerObject.get("DomesticPrice").getAsString(); 
				String CommercialPrice = powerObject.get("CommercialPrice").getAsString(); 
				String Date = powerObject.get("Date").getAsString(); 
				String output = powerObj.updatePower(rateID, numberOfUnits, DomesticPrice, CommercialPrice, Date); 
				return output; 
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deletePower(String powerData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(powerData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <itemID>
				String rateID = doc.select("rateID").text(); 
				String output = powerObj.deletePower(rateID); 
				return output; 
			}

			
					
}