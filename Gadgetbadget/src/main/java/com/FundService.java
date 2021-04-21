package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//For JSON
import com.google.gson.*;

import model.Fund;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Funds")
public class FundService {

	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return fundObj.readItems();
	 }
	
	//insert
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertItem(@FormParam("reasearcherID") String reasearcherID,
			 @FormParam("reasearchTopic") String reasearchTopic,
			 @FormParam("fundersID") String fundersID,
			 @FormParam("amount") String amount,
			 @FormParam("cardNumber") String cardNumber,
			@FormParam("date") String date,
			@FormParam("cvv") String cvv)
			{
			 String output = fundObj.insertItem(reasearcherID,reasearchTopic, fundersID, amount, cardNumber, date, cvv);
			return output;
			}
			
			//updateItem
			@PUT
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateItem(String fundData)
			{
			//Convert the input string to a JSON object
			 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
			//Read the values from the JSON object
			 String fundID = fundObject.get("fundID").getAsString();
			 String reasearcherID = fundObject.get("reasearcherID").getAsString();
			 //String BuyerID = fundObj.get("BuyerID").getAsString();
			 String reasearchTopic = fundObject.get("reasearchTopic").getAsString();
			 String fundersID = fundObject.get("fundersID").getAsString();
			 String amount = fundObject.get("amount").getAsString();
			 String cardNumber = fundObject.get("cardNumber").getAsString();
			 String date = fundObject.get("date").getAsString();
			 String cvv = fundObject.get("cvv").getAsString();
			 
			 String output = fundObj.updateItem(fundID, reasearcherID, reasearchTopic, fundersID, amount, cardNumber, date, cvv);
			return output;
			}
			
			//Delete method
			@DELETE
			@Path("/")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deletePayment(String fundData)
			{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

			//Read the value from the element <itemID>
			 String fundID = doc.select("fundID").text();
			 String output = fundObj.deleteItem(fundID);
			return output;
			}
}
