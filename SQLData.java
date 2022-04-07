package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

public class SQLData {

	private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD; 
    private Connection dbConnect;
    private ResultSet resultsClient;
	private ResultSet resultsAvailableFood;
	private Inventory originalInventory=new Inventory();
	private Inventory updatedInventory=new Inventory();;
	private ArrayList<ClientType> clients=new ArrayList<>();
	
	public SQLData(String dbURL, String username, String password) {
		this.DBURL = dbURL;
		this.USERNAME = username;
		this.PASSWORD = password;
	}
	
	public void connectDatabase() throws CloneNotSupportedException {
		
        try {
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME , this.PASSWORD);
            Statement myStmt = dbConnect.createStatement();
            resultsClient = myStmt.executeQuery("SELECT * FROM " + "DAILY_CLIENT_NEEDS");
            while (resultsClient.next()){
            	ClientType client = new ClientType(resultsClient.getInt("ClientID"), resultsClient.getString("Client"), resultsClient.getInt("WholeGrains"), resultsClient.getInt("FruitVeggies"), resultsClient.getInt("Protein"), resultsClient.getInt("Other"), resultsClient.getInt("Calories"));
            	this.clients.add(client);
            }	
            resultsAvailableFood = myStmt.executeQuery("SELECT * FROM " + "AVAILABLE_FOOD");
            while(resultsAvailableFood.next()){
            	FoodItem foodItem = new FoodItem(resultsClient.getInt("ItemID"), resultsClient.getString("Name"), resultsClient.getInt("GrainContent"), resultsClient.getInt("FVContent"), resultsClient.getInt("ProContent"), resultsClient.getInt("Other"), resultsClient.getInt("Calories"));
            	this.originalInventory.getInventoryItems().add(foodItem);
            }
	    	this.updatedInventory = (Inventory)this.originalInventory.clone();	        
            myStmt.close();  
        } catch (SQLException e) {
        	e.printStackTrace();
        }  
	}
	
	public void close() {
        
		try {
            resultsClient.close();
            resultsAvailableFood.close();
            dbConnect.close();
           }catch (SQLException e) {
            e.printStackTrace();
          }
	}   
	
	private void delete(int id) {
         try {
        	 String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
             PreparedStatement myStmt = dbConnect.prepareStatement(query);
             myStmt.setInt(1, id);
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
	
	public ArrayList<ClientType> getClients() {
		return this.clients;
	}
}
