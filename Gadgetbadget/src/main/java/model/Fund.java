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
	
	//insert
		public String insertItem(String reasearcherID, String reasearchTopic, String fundersID, String amount, String card_number,String date, String cVv)
		 {
			String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into funds(`fundID`, `reasearcherID`, `reasearchTopic`, `fundersID`, `amount`, `cardNumber`, `date`, `cvv`)"
			 + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, reasearcherID);
			 preparedStmt.setString(3, reasearchTopic);
			 preparedStmt.setString(4, fundersID);
			 preparedStmt.setDouble(5, Double.parseDouble(amount));
			 preparedStmt.setString(6, card_number);
			 preparedStmt.setString(7, date);
			 preparedStmt.setString(8, cVv);
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
		public String readItems()
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
			 "<th>cardNumber</th>" +"<th>Expire date</th>" +"<th>cvv</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
		
			 String query = "select * from funds";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String fundID = Integer.toString(rs.getInt("fundID"));
			 String reasearcherID = Integer.toString(rs.getInt("reasearcherID"));
			 String reasearchTopic = Integer.toString(rs.getInt("fundersID"));
			 String fundersID = Integer.toString(rs.getInt("reasearchTopic"));
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
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + fundID
			 + "'>" + "</form></td></tr>";
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
		public String updateItem(String fundId, String reasearchId, String funderId,String reasearchTopic, String amount, String cardNumber,String date, String cVv)
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
			 preparedStmt.setString(1, reasearchId);
			 preparedStmt.setString(2, funderId);
			 preparedStmt.setString(3, reasearchTopic);
			 preparedStmt.setDouble(4, Double.parseDouble(amount));
			 preparedStmt.setString(5, cardNumber);
			 preparedStmt.setString(6, date);
			 preparedStmt.setString(7, cVv);
			 preparedStmt.setInt(8, Integer.parseInt(fundId));
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
		
		public String deleteItem(String fundID)
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
