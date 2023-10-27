package model; 
import java.sql.*; 

public class Power 
{


//A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertPower(String units, String dprice, String cprice, String date){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into power_rate (`rateID`,`numberOfUnits`,`DomesticPrice`,`CommercialPrice`,`Date`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, units); 
						preparedStmt.setString(3, dprice); 
						preparedStmt.setString(4, cprice); 
						preparedStmt.setString(5, date); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						output = "Inserted successfully"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "Error while inserting the rates."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
			public String readPowers(){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								
								return "Error while connecting to the database for reading."; 
								
								} 
							// Prepare the html table to be displayed
							output ="<html>"
									+"<head>"
									  +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">"	
									  +"</head>"
									  
									  +"<body>"+  
									
									"<table border='1' class='table'><tr><th>Rate ID</th><th>Number of Units</th><th>Domestic Price per Unit</th>" +
									"<th>Commercial Price per Unit</th>" + 
									"<th>Date</th>" +
									"<th>Update</th><th>Remove</th></tr>"; 
 
							String query = "select * from power_rate"; 
							Statement stmt = con.createStatement(); 
							ResultSet rs = stmt.executeQuery(query); 
							// iterate through the rows in the result set
							while (rs.next()) 
							{ 
									String rateID = Integer.toString(rs.getInt("rateID")); 
									String numberOfUnits = rs.getString("numberOfUnits"); 
									String DomesticPrice = rs.getString("DomesticPrice"); 
									String CommercialPrice = rs.getString("CommercialPrice"); 
									String Date = rs.getString("Date"); 
									// Add into the html table
									output += "<tr><td>" + rateID  + "</td>";
									output += "<td>" + numberOfUnits + "</td>";
									output += "<td>" + DomesticPrice + "</td>";
									output += "<td>" + CommercialPrice + "</td>";
									output += "<td>" + Date + "</td>"; 
									
									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
											+ "<td><form method='post' action='power_rate.jsp'>"
											+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='rateID' type='hidden' value='" + rateID 
											+ "'>" + "</form></td></tr>"; 
							} 
								con.close(); 
								// Complete the html table
								output += "</table>"; 
						} 
						catch (Exception e){ 
									output = "Error while reading the rates."; 
									System.err.println(e.getMessage()); 
						} 
						return output; 
						} 
			
			
			public String updatePower(String ID , String units, String dprice, String cprice, String date){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE power_rate SET numberOfUnits=?,DomesticPrice=?,CommercialPrice=?,Date=? WHERE rateID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, units); 
							preparedStmt.setString(2, dprice); 
							preparedStmt.setString(3, cprice); 
							preparedStmt.setString(4, date); 
							preparedStmt.setInt(5, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							output = "Updated successfully"; 
					} 
					
					catch (Exception e){ 
						
						output = "Error while updating the rates."; 
						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deletePower(String rateID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from power_rate where rateID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(rateID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
					} 
					
					catch (Exception e){ 
						output = "Error while deleting the rate."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
