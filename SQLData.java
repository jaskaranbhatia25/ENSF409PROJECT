package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLData {

        private final String DBURL;
        private final String USERNAME;
        private final String PASSWORD; 
        private Connection dbConnect;
        private ResultSet resultsClient;
	private ResultSet resultsAvailableFood;
	private Inventory originalInventory;
	private Inventory updatedInventory;
	private ArrayList<ClientType> clients;
	
	public SQLData(String dbURL, String username, String password) {
		this.DBURL = dbURL;
		this.USERNAME = username;
		this.PASSWORD = password;
	}
	
	public void connectDatabase() {
		
        try{
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME , this.PASSWORD);
            Statement myStmt = dbConnect.createStatement();
            resultsClient = myStmt.executeQuery("SELECT * FROM "+DAILY_CLIENT_NEEDS);
	    while (resultsClient.next()){
		  ClientType client = new ClientType(getInt("ClientID"), getString("Client"), getInt("WholeGrains"), getInt("FruitVeggies"), getInt("Protein"), getInt("Other"), getInt("Calories"));
		  this.clients.add(client);
	    }
	    while(resultsAvailableFood.next()){
		    
	      // we will use clone to create updatedInventory i.e clone originalInventory to updatedInventory	    
		    
	    }	    
	    myStmt.close();    
        } catch (SQLException e) {
            e.printStackTrace();
        }  
	}
       public void close() {
        
          try {
            results.close();
            dbConnect.close();
           }catch (SQLException e) {
            e.printStackTrace();
          }
        }   
	
	private void delete(int id) {
         try {
                String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
                PreparedStatement myStmt = dbConnect.prepareStatement(query);
                myStmt.setString(1, id);
                myStmt.executeUpdate();
                myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		
	}
	public void updateDatabase(){
	
	      for(int i = 0; i< updatedInventory.getInventoryItems().size();i++){
		   if(updatedInventory.getInventoryItems().get(i)==null){
			   delete(i+1);
		   }
	      }	      	
	}	
	
	public Inventory getOriginalInventory() {
		return this.originalInventory;
	}
	
	public Inventory getUpdatedInventory() {
		return this.updatedInventory;
	}
	
	public Clients getClients() {
		return this.clients;
	}
}
