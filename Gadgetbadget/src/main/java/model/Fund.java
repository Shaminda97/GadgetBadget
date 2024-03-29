package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	//con
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
	
	
	public String insertFund(String reasearcherID, String reasearchTopic, String fundersID, String amount,String cardNumber,String date,String cvv)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement 
	 String query = " insert into funds (`fundID`, `reasearcherID`, `reasearchTopic`, `fundersID`, `amount`, `cardNumber`, `date`, `cvv`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setInt(2, Integer.parseInt(reasearcherID));
	 preparedStmt.setString(3, reasearchTopic);
	 preparedStmt.setInt(4, Integer.parseInt(fundersID));
	 preparedStmt.setDouble(5, Double.parseDouble(amount));
	 preparedStmt.setInt(6, Integer.parseInt(cardNumber));
	 preparedStmt.setString(7, date);
	 preparedStmt.setInt(8, Integer.parseInt(cvv));
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the fund.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
		
		//Admin read Item
		public String readFunds()
		 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>reasearcherID</th><th>fundersID</th>" +
			 "<th>reasearchTopic</th>" +"<th>amount</th>" +
			 "<th>cardNumber</th>" +"<th>Expire date</th>" +"<th>cvv</th>";/* +
			 "<th>Update</th><th>Remove</th></tr>";*/
		
			 String query = "select * from funds";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String fundID = Integer.toString(rs.getInt("fundID"));
			 String reasearcherID = Integer.toString(rs.getInt("reasearcherID"));
			 String reasearchTopic = Integer.toString(rs.getInt("fundersID"));
			 String fundersID = rs.getString("reasearchTopic");
			 String amount = Double.toString(rs.getDouble("amount"));
			 String cardNumber = Integer.toString(rs.getInt("cardNumber"));
			 String date = rs.getString("date");
			 String cvv = Integer.toString(rs.getInt("cvv"));
			 // Add into the html table
			 output += "<tr><td>" + reasearcherID + "</td>";
			 output += "<td>" + reasearchTopic + "</td>";
			 output += "<td>" + fundersID + "</td>";
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
		
		//fund read by fund id
		public String readFundByID(String fundID)
		 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>reasearcherID</th><th>fundersID</th>" +
			 "<th>reasearchTopic</th>" +"<th>amount</th>" +
			 "<th>cardNumber</th>" +"<th>Expire date</th>" +"<th>cvv</th>";// +
			// "<th>Update</th><th>Remove</th></tr>";
		
			 String query = "select * from funds where fundID = ?";
			 //Statement stmt = con.createStatement();
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 preparedStmt.setInt(1, Integer.parseInt(fundID));
			 ResultSet rs = preparedStmt.executeQuery();
			 while (rs.next())
			 {
			 //String fundID = Integer.toString(rs.getInt("fundID"));
			 String reasearcherID = Integer.toString(rs.getInt("reasearcherID"));
			 String reasearchTopic = Integer.toString(rs.getInt("fundersID"));
			 String fundersID = rs.getString("reasearchTopic");
			 String amount = Double.toString(rs.getDouble("amount"));
			 String cardNumber = Integer.toString(rs.getInt("cardNumber"));
			 String date = rs.getString("date");
			 String cvv = Integer.toString(rs.getInt("cvv"));
			 // Add into the html table
			 output += "<tr><td>" + reasearcherID + "</td>";
			 output += "<td>" + reasearchTopic + "</td>";
			 output += "<td>" + fundersID + "</td>";
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
		public String updateFunds(String fundID, String reasearcherID, String reasearchTopic, String fundersID,String amount, String cardNumber,String date, String cvv)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE funds SET reasearcherID=?,reasearchTopic=?, fundersID=?,amount=?,cardNumber=?,date=?,cvv=? WHERE fundID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(reasearcherID));
			 preparedStmt.setString(2, reasearchTopic);
			 preparedStmt.setInt(3, Integer.parseInt(fundersID)); 
			 preparedStmt.setDouble(4, Double.parseDouble(amount));
			 preparedStmt.setInt(5, Integer.parseInt(cardNumber));
			 preparedStmt.setString(6, date);
			 preparedStmt.setInt(7, Integer.parseInt(cvv));
			 preparedStmt.setInt(8, Integer.parseInt(fundID));
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
		
		public String deleteFunds(String fundID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from funds where fundID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(fundID));
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
