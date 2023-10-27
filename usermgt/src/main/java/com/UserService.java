package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;






@Path("/User")
public class UserService{

User userObj = new User();


@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readUsers()
{
return userObj.readUsers();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertUser(@FormParam("username") String username,
@FormParam("password") String password,
@FormParam("address") String address,
@FormParam("contactno") String contactno){

String output = userObj.insertUser(username, password, address, contactno);
return output;

}


@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateUser(String userData)
{
//Convert the input string to a JSON object
JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
//Read the values from the JSON object
String userid = userObject.get("userid").getAsString();
String username = userObject.get("username").getAsString();
String password = userObject.get("password").getAsString();
String address = userObject.get("address").getAsString();
String contactno = userObject.get("contactno").getAsString();
String output = userObj.updateUser(userid, username, password, address, contactno);
return output;

}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteUser(String userData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

//Read the value from the element <userid>
String userid = doc.select("userid").text();
String output = userObj.deleteUser(userid);
return output;
}





}