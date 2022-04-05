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
		return CLIENT;
	}
	
	public int getWholeGrains(){
		return WHOLE_GRAINS;
	}
	
	public int getFruitVeggies(){
		return FRUIT_VEGGIES;
	}
	
	public int getProtein(){
		return PROTEIN;
	}
	
	public int getOther(){
		return OTHER;
	}
	
	public int getCalories(){
		return CALORIES;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
