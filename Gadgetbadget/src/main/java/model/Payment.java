package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	//connection
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	//insert
	public String insertPayment(String ProductID, String BuyerID, String amount, String cardNumber,String date, String cvv)
	 {
		String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into payments(`PayID` , `ProductID`, `BuyerID`, `amount`, `cardNumber`, `date`, `cvv`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setInt(2, Integer.parseInt(ProductID));
		 preparedStmt.setInt(3, Integer.parseInt(BuyerID));
		 preparedStmt.setDouble(4, Double.parseDouble(amount));
		 preparedStmt.setInt(5, Integer.parseInt(cardNumber));
		 preparedStmt.setString(6, date);
		 preparedStmt.setInt(7, Integer.parseInt(cvv));
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		
	 }
	
	//Admin read Item
	public String readPayment()
	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>ProductID</th><th>BuyerID</th>" +
		 "<th>amount</th>" +
		 "<th>cardNumber</th>" +"<th>Expire date</th>" +"<th>cvv</th>";/* +
		 "<th>Update</th><th>Remove</th></tr>";*/
	
		 String query = "select * from payments";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String PayID = Integer.toString(rs.getInt("PayID"));
		 String ProductID = Integer.toString(rs.getInt("ProductID"));
		 String BuyerID = Integer.toString(rs.getInt("BuyerID"));
		 String amount = Double.toString(rs.getDouble("amount"));
		 String cardNumber = Integer.toString(rs.getInt("cardNumber"));
		 String date = rs.getString("date");
		 String cvv = Integer.toString(rs.getInt("cvv"));
		 // Add into the html table
		 output += "<tr><td>" + ProductID + "</td>";
		 output += "<td>" + BuyerID + "</td>";
		 output += "<td>" + amount + "</td>";
		 output += "<td>" + cardNumber + "</td>";
		 //output += "<td>" + amount + "</td>";
		 output += "<td>" + date + "</td>";
		 output += "<td>" + cvv + "</td>";
		 
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
	
	//Read paymentById
	public String readpaymentById(String PayID)
	 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>ProductID</th><th>BuyerID</th>" +
		 "<th>amount</th>" +
		 "<th>cardNumber</th>" +"<th>Expire date</th>" +"<th>cvv</th>";/* +
		 "<th>Update</th><th>Remove</th></tr>";*/
	
		 String query = "select * from payments where PayID = ?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 preparedStmt.setInt(1, Integer.parseInt(PayID));
		 ResultSet rs = preparedStmt.executeQuery();
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 //String PayID = Integer.toString(rs.getInt("PayID"));
		 String ProductID = Integer.toString(rs.getInt("ProductID"));
		 String BuyerID = Integer.toString(rs.getInt("BuyerID"));
		 String amount = Double.toString(rs.getDouble("amount"));
		 String cardNumber = Integer.toString(rs.getInt("cardNumber"));
		 String date = rs.getString("date");
		 String cvv = Integer.toString(rs.getInt("cvv"));
		 // Add into the html table
		 output += "<tr><td>" + ProductID + "</td>";
		 output += "<td>" + BuyerID + "</td>";
		 output += "<td>" + amount + "</td>";
		 output += "<td>" + cardNumber + "</td>";
		 //output += "<td>" + amount + "</td>";
		 output += "<td>" + date + "</td>";
		 output += "<td>" + cvv + "</td>";
		 
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
	
	//update
	public String updatePayment(String PayID, String ProductID, String BuyerID, String amount, String card_number,String date, String cVv)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE payments SET ProductID=?,BuyerID=?,amount=?,cardNumber=?,date=?,cvv=? WHERE PayID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, ProductID);
		 preparedStmt.setString(2, BuyerID);
		 preparedStmt.setDouble(3, Double.parseDouble(amount));
		 preparedStmt.setString(4, card_number);
		 preparedStmt.setString(5, date);
		 preparedStmt.setString(6, cVv);
		 preparedStmt.setInt(7, Integer.parseInt(PayID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	public String deletePayment(String PayID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payments where PayID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(PayID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
