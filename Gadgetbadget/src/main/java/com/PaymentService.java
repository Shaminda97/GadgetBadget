package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Payment;



@Path("/Payment") 
public class PaymentService {
	
	Payment paymentObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return paymentObj.readItems();
	 //return "Hello";
	 } 
	
	/*@GET
	@Path("{BuyerId}")
	public Response GET(@PathParam("BuyerId")int BuyerId) {
		
		return null;
	}*/
	
	
	//insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("ProductID") String ProductID,
		 @FormParam("BuyerID") String BuyerID,
		 @FormParam("amount") String amount,
		 @FormParam("cardNumber") String cardNumber,
		@FormParam("date") String date,
		@FormParam("cvv") String cvv)
		{
		 String output = paymentObj.insertItem(ProductID, BuyerID, amount, cardNumber, date, cvv);
		return output;
		}
		
		//updateItem
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		 String PayID = itemObject.get("PayID").getAsString();
		 String ProductID = itemObject.get("ProductID").getAsString();
		 String BuyerID = itemObject.get("BuyerID").getAsString();
		 String amount = itemObject.get("amount").getAsString();
		 String cardNumber = itemObject.get("cardNumber").getAsString();
		 String date = itemObject.get("date").getAsString();
		 String cvv = itemObject.get("cvv").getAsString();
		 
		 String output = paymentObj.updateItem(PayID, ProductID, BuyerID, amount, cardNumber, date, cvv);
		return output;
		}

		//Delete method
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String PayID = doc.select("PayID").text();
		 String output = paymentObj.deleteItem(PayID);
		return output;
		}
}
