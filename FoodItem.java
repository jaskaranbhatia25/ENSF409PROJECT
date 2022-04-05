package edu.ucalgary.ensf409;

public class FoodItem implements Cloneable {
	
	private final int ITEM_ID;
	private final int GRAIN_CONTENT;
	private final int FV_CONTENT;
	private final int PRO_CONTENT;
	private final int OTHER;
	private final int CALORIES;
	private final String NAME;
	
	public FoodItem(int itemID, String name, int grainContent, int fvContent, int proContent, int other, int calories){
		this.ITEM_ID = itemID;
		this.NAME = name;
		this.GRAIN_CONTENT = grainContent;
		this.FV_CONTENT = fvContent;
		this.PRO_CONTENT = proContent;
		this.OTHER = other;
		this.CALORIES = calories;
	}
		
    public String getName(){
		return this.NAME;
	}
    
	public int getItemID(){
		return this.ITEM_ID;
	}
	
	public int getGrainContent(){
		return this.GRAIN_CONTENT;
	}
	
	public int getFVContent(){
		return this.FV_CONTENT;
	}
	
	public int getProContent(){
		return this.PRO_CONTENT;
	}
	
	public int OtherContent(){
		return this.OTHER;
	}
	
	public int getCalories(){
		return this.CALORIES;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
