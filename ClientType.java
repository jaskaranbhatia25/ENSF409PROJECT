package edu.ucalgary.ensf409;

public class ClientType implements Cloneable {
	
	private final int CLIENT_ID;
	private final String CLIENT;
	private final int WHOLE_GRAINS;
	private final int FRUIT_VEGGIES;
	private final int PROTEIN;
	private final int OTHER;
	private final int CALORIES;
	
	public ClientType( int clientID, String client , int wholeGrains,  int fruitVeggies,  int protein, int other, int calories){	
	    this.CLIENT_ID = clientID;
	    this.CLIENT = client;
	    this.WHOLE_GRAINS = wholeGrains;
	    this.FRUIT_VEGGIES = fruitVeggies;
	    this.PROTEIN = protein;
	    this.OTHER = other;
	    this.CALORIES = calories;		
	}
	
	public int getClientID(){
		return this.CLIENT_ID;
	}
	
	public String getClient(){
		return this.CLIENT;
	}
	
	public int getWholeGrains(){
		return this.WHOLE_GRAINS;
	}
	
	public int getFruitVeggies(){
		return this.FRUIT_VEGGIES;
	}
	
	public int getProtein(){
		return this.PROTEIN;
	}
	
	public int getOther(){
		return this.OTHER;
	}
	
	public int getCalories(){
		return this.CALORIES;
	}
}
