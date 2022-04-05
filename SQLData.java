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
        private ResultSet results;
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
            results = myStmt.executeQuery("SELECT * FROM "+DAILY_CLIENT_NEEDS);
	    while (results.next()){
		  ClientType client = new ClientType(getInt("ClientID"), getString("Client"), getInt("WholeGrains"), getInt("FruitVeggies"), getInt("Protein"), getInt("Other"), getInt("Calories"));
		  this.clients.add(client);
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
	
	public void updateDatabase() {
		
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
