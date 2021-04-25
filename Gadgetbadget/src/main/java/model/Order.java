package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order {

	// Database Connection
	private Connection connect() {
		Connection con = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			// Access Database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Create Order
	public String creatOrder(int orderID, String BuyerID, int nuOfItems, double amount, String orderDate) {

		String printMsg = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Database Connecting Error - Post";
			}

			// create a Prepared Statement
			String DBquery = " insert into order(`orderID` , `BuyerID`, `nuOfItems`, `amount`, `orderDate`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(DBquery);

			// Set Values
			preparedStmt.setInt(1, orderID);
			preparedStmt.setInt(2, Integer.parseInt(BuyerID));
			preparedStmt.setInt(3, nuOfItems);
			preparedStmt.setDouble(4, amount);
			preparedStmt.setString(5, orderDate);

			// Execute the Prepared Statement
			preparedStmt.execute();
			con.close();

			// Print Success Message
			printMsg = "Inserted Successful";

		} catch (Exception e) {

			// Print Failed Message
			printMsg = "ERROR! Insertion Failed";

			System.err.println(e.getMessage());
		}

		return printMsg;

	}
	
	//Update an Existing Order
		public String updateOrder(int orderID, String BuyerID, int nuOfItems, double amount, String orderDate)
		{
			 String printMsg = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 
			 // create a prepared statement
			 String DBquery = "UPDATE orders SET orderID=?,BuyerID=?,nuOfItems=?,amount=?,orderDate=?";
			 PreparedStatement preparedStmt = con.prepareStatement(DBquery);
			 
			 // binding values
			 	preparedStmt.setInt(1, orderID);
				preparedStmt.setInt(2, Integer.parseInt(BuyerID));
				preparedStmt.setInt(3, nuOfItems);
				preparedStmt.setDouble(4, amount);
				preparedStmt.setString(5, orderDate);
				
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 printMsg = "Updated order successfully";
			 }
			 
			 catch (Exception e)
			 {
				 printMsg = "Error while updating the order.";
				 System.err.println(e.getMessage());
			 }
			 
			 return printMsg;
			}
		
		
		
		
	// View order by orderID
		public String getOrderById(String orderID) {

			String printMsg = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "ERROR while connecting to the DATABASE";
				}

				// create a prepared statement
				String DBquery = "SELECT * FROM Orde WHERE orderID =" + " '" + orderID + "' ";

				PreparedStatement pst = con.prepareStatement(DBquery);
				ResultSet rst = pst.executeQuery(DBquery);

				while (rst.next()) {

					String OrderMethod = rst.getString("ORDER_METHOD");
					String BuyerID= rst.getString("BUYER_ID");
					String TotalAmount = rst.getString("TOTAL_AMOUNT");

					printMsg = "OrderMethod : " + OrderMethod + " & " + "BuyerID : " + BuyerID + " & " + "Total Amount : "
							+ TotalAmount;

				}

				// execute the statement
				con.close();
			}

			catch (Exception e) {
				printMsg = "Error while Finding Order.";
				System.err.println(e.getMessage());
			}

			return printMsg;
		}

	// Delete an Existing Order
	public String deletOrder(int orderID) {
		
		String printMsg = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Database Connecting Error - Delete";
			}
			
			// create a prepared statement
			String DBquery = "delete from orders where orderID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(DBquery);
			
			// binding values
			preparedStmt.setInt(1, orderID);
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			printMsg = "Order Deleted successfully";
			
		} catch (Exception e) {
			
			printMsg = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		
		return printMsg;
	}
	

}
