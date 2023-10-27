package model; 
import java.sql.*; 

public class Payment 
{ //A common method to connect to the DB
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
		
		
		public String insertPayment(String code, String cus_id, String month, String bill, String date){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into payment_form (`payment_id`,`payment_code`,`customer_id`,`month_of_bill`,`bill_amount`,`date`)"+" values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, code); 
						preparedStmt.setString(3, cus_id); 
						preparedStmt.setString(4, month); 
						preparedStmt.setString(5, bill); 
						preparedStmt.setString(6, date); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						output = "Inserted successfully"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "Error while inserting the payment details."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
			public String readPayments(){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								
								return "Error while connecting to the database for reading."; 
								
								} 
							// Prepare the html table to be displayed
							output = "<table border='1'><tr><th>Payment Code</th><th>Customer ID</th>" +
									"<th>Month of Bill</th>" + 
									"<th>Amount</th>" +
									"<th>Date</th>" +
									"<th>Update</th><th>Remove</th></tr>"; 
 
							String query = "select * from payment_form"; 
							Statement stmt = con.createStatement(); 
							ResultSet rs = stmt.executeQuery(query); 
							// iterate through the rows in the result set
							while (rs.next()) 
							{ 
									String payment_id = Integer.toString(rs.getInt("payment_id")); 
									String payment_code = rs.getString("payment_code"); 
									String customer_id = rs.getString("customer_id"); 
									String month_of_bill = rs.getString("month_of_bill"); 
									String bill_amount = rs.getString("bill_amount"); 
									String date = rs.getString("date"); 
									// Add into the html table
									output += "<tr><td>" + payment_code + "</td>"; 
									output += "<td>" + customer_id + "</td>"; 
									output += "<td>" + month_of_bill + "</td>"; 
									output += "<td>" + bill_amount + "</td>"; 
									output += "<td>" + date + "</td>"; 
									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
											+ "<td><form method='post' action='payment_form.jsp'>"
											+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='payment_id' type='hidden' value='" + payment_id 
											+ "'>" + "</form></td></tr>"; 
							} 
								con.close(); 
								// Complete the html table
								output += "</table>"; 
						} 
						catch (Exception e){ 
									output = "Error while reading the payment details."; 
									System.err.println(e.getMessage()); 
						} 
						return output; 
						} 
			
			
			public String updatePayment(String ID, String code, String cus_id, String month, String bill, String date){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE payment_form SET payment_code=?,customer_id=?,month_of_bill=?,bill_amount=?,date=? WHERE payment_id=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
						
							preparedStmt.setString(1, code); 
							preparedStmt.setString(2, cus_id); 
							preparedStmt.setString(3, month); 
							preparedStmt.setString(4, bill); 
							preparedStmt.setString(5, date); 
							preparedStmt.setInt(6, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							output = "Updated successfully"; 
					} 
					
					catch (Exception e){ 
						
						output = "Error while updating the payment details."; 
						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deletePayment(String payment_id){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from payment_form where payment_id=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(payment_id)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
					} 
					
					catch (Exception e){ 
						output = "Error while deleting the payment details."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
