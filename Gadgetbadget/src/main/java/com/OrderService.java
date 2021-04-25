package com;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class OrderService {

	OrderService orderService = new OrderService();

	// Test Function
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String testFunction() {
		
		String res = "Test Function Working!!";
		
		return res;
	}
			

	// Create order
	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_HTML)
	public String createOrders(

			@FormParam("orderID") int orderID, @FormParam("nuOfItems") int nuOfItems,
			@FormParam("BuyerID") String BuyerID, @FormParam("amount") double amount,
			@FormParam("amount") String orderDate)

	{

		return orderService.createOrders(orderID, nuOfItems, BuyerID, amount, orderDate);
	}

	// Find order by order ID
	@GET
	@Path("/id/{orderID}")
	public String getOrderById(@FormParam("orderID") String orderID) {

		return orderService.getOrderById(orderID);
	}

	// Delete an order
	@DELETE
	@Path("/delete/{orderID}")
	public String deleteOrder(@FormParam("orderID") String orderID) {

		return orderService.deleteOrder(orderID);
	}

	// Update an order
	@PUT
	@Path("/")
	public String updateOrder(@FormParam("orderID") String orderID) {

		return orderService.updateOrder(orderID);
	}
}
