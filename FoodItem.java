package edu.ucalgary.ensf409;

public class FoodItem{
	private final int ITEM_ID;
	private final int GRAIN_CONTENT;
	private final int FV_CONTENT;
	private final int PRO_CONTENT;
	private final int OTHER;
	private final int CALORIES;
	private final String NAME;
	
	public FoodItem(String name, int itemID, int grainContent, int fvContent, int proContent, int other, int calories){
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
		this.GRAIN_CONTENT;
	}
	public int getFVContent(){
		this.FV_CONTENT;
	}
	public int getProContent(){
		this.PRO_CONTENT;
	}
	public int OtherContent(){
		this.OTHER;
	}
	public int getCalories(){
		this.CALORIES;
	}
}
