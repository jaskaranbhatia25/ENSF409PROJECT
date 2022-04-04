package edu.ucalgary.ensf409;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLData {

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD; 
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
        } catch (SQLException e) {
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
