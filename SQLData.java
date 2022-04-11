/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

public class SQLData {

	private String DBURL = new String();
    private String USERNAME= new String();
    private String PASSWORD= new String(); 
    private Connection dbConnect;
    private ResultSet resultsClient;
	private ResultSet resultsAvailableFood;
	private Inventory originalInventory=new Inventory();
	private Inventory updatedInventory=new Inventory();;
	private ArrayList<ClientType> clients=new ArrayList<>();
	
	/**
	 * constructor for SQLData
	 * @param dbURL 
	 * @param username 
	 * @param password 
	 */
	public SQLData(String dbURL, String username, String password) {
		this.DBURL = dbURL;
		this.USERNAME = username;
		this.PASSWORD = password;
	}
	
	public SQLData() {}
	
	/*
	 *   this means that whenever an order fails,  the updated inventory will be
	 *    replaced back by the original inventory
	 */ 
	public void refreshUpdatedInventoryWhenOrderFails() throws CloneNotSupportedException {
		Inventory refreshedUpdatedInventory = (Inventory)originalInventory.clone();
		this.updatedInventory = refreshedUpdatedInventory;
	}
	
	/*
	 * refreshOriginalInventoryWhenOrderSuccess clone method for SQLData class, this means that whenever 
	 * an order succeeds, the original inventory will be replaced by the updated inventory
	 */
	public void refreshOriginalInventoryWhenOrderSuccess() throws CloneNotSupportedException {
		Inventory refreshedOriginalInventory = (Inventory)updatedInventory.clone();
		this.originalInventory = refreshedOriginalInventory;
	}	
	
	/* this method is for making a connection between the database
	 * 
	 */
	public void connectDatabase() throws CloneNotSupportedException {
        try {
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME , this.PASSWORD);
            Statement myStmt = dbConnect.createStatement();
            resultsClient = myStmt.executeQuery("SELECT * FROM " + "DAILY_CLIENT_NEEDS");
            while (resultsClient.next()) {
            	ClientType client = new ClientType(resultsClient.getInt("ClientID"), resultsClient.getString("Client"), resultsClient.getInt("WholeGrains"), resultsClient.getInt("FruitVeggies"), resultsClient.getInt("Protein"), resultsClient.getInt("Other"), resultsClient.getInt("Calories"));
            	this.clients.add(client);
            }	
            resultsAvailableFood = myStmt.executeQuery("SELECT * FROM " + "AVAILABLE_FOOD");
            while(resultsAvailableFood.next()) {
            	FoodItem foodItem = new FoodItem(resultsAvailableFood.getInt("ItemID"), resultsAvailableFood.getString("Name"), resultsAvailableFood.getInt("GrainContent"), resultsAvailableFood.getInt("FVContent"), resultsAvailableFood.getInt("ProContent"), resultsAvailableFood.getInt("Other"), resultsAvailableFood.getInt("Calories"));
            	this.originalInventory.getInventoryItems().add(foodItem);
            }
	    	this.updatedInventory = (Inventory)this.originalInventory.clone();	        
            myStmt.close();  
        } catch (SQLException e) {
        	e.printStackTrace();
        }  
	}
	
	/*
	 * 
	 */
	public void close() {
		try {
            resultsClient.close();
            resultsAvailableFood.close();
            dbConnect.close();
           }catch (SQLException e) {
            e.printStackTrace();
          }
	}   
	
	/**
	 * 
	 * @param id
	 */
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
	
	/* 
	 * this method is for the updating the inventory after a particular order succeeds or fails
	 */
	public void updateDatabase() {
		for(int i = 0; i< updatedInventory.getInventoryItems().size();i++) {
			if(updatedInventory.getInventoryItems().get(i).getName().equals("")) {
				delete(i+1);
			}
		}	      	
	}	
	
	/**
	 * getter method for getting the original inventory.
	 * @return Inventory 
	 */
	public Inventory getOriginalInventory() {
		return this.originalInventory;
	}
	
	/**
	 *  getter method for getting the updated inventory after creation of each hamper.
	 * @return Inventory
	 */
	public Inventory getUpdatedInventory() {
		return this.updatedInventory;
	}
	 /**
	  * getter method for getting the clients.
	  * @return ArrayList<ClientType> 
	  */
	public ArrayList<ClientType> getClients() {
		return this.clients;
	}
}
