package edu.ucalgary.ensf409;

import java.util.*;

public class Hamper {
	
	private SQLData myData;
	private final double TOTAL_WHOLE_GRAIN_CONTENT;
	private final double TOTAL_FV;
	private final double TOTAL_PROTEIN;
	private final double TOTAL_OTHER;
	private final double TOTAL_CALORIES;
	private boolean valid = false;
	private ArrayList<FoodItem> hamperItems = new ArrayList<>();
	
	public Hamper(SQLData myData, int male, int female,  int under8, int above8){
		
		int maleCalories = myData.getClients().get(0).getCalories();
		int femaleCalories = myData.getClients().get(1).getCalories();
		int under8Calories = myData.getClients().get(2).getCalories();
		int over8Calories = myData.getClients().get(3).getCalories();
		this.myData = myData;	
		this.TOTAL_WHOLE_GRAIN_CONTENT  = (myData.getClients().get(0).getWholeGrains()*male*0.01*maleCalories
										+ myData.getClients().get(1).getWholeGrains()*female*0.01*femaleCalories 
										+ myData.getClients().get(2).getWholeGrains()*under8*0.01*under8Calories 
										+ myData.getClients().get(3).getWholeGrains()*above8*0.01*over8Calories)*7;
		
	    this.TOTAL_FV   = (myData.getClients().get(0).getFruitVeggies()*male*0.01*maleCalories
						+ myData.getClients().get(1).getFruitVeggies()*female*0.01*femaleCalories 
					    + myData.getClients().get(2).getFruitVeggies()*under8*0.01*under8Calories 
						+ myData.getClients().get(3).getFruitVeggies()*above8*0.01*over8Calories)*7;
	    
        this.TOTAL_PROTEIN	= (myData.getClients().get(0).getProtein()*male*0.01*maleCalories
					        + myData.getClients().get(1).getProtein()*female*0.01*femaleCalories 
					        + myData.getClients().get(2).getProtein()*under8*0.01*under8Calories 
					        + myData.getClients().get(3).getProtein()*above8*0.01*over8Calories)*7;
        
		this.TOTAL_OTHER = (myData.getClients().get(0).getOther()*male*0.01*maleCalories
					    + myData.getClients().get(1).getOther()*female*0.01*femaleCalories 
					    + myData.getClients().get(2).getOther()*under8*0.01*under8Calories 
					    + myData.getClients().get(3).getOther()*above8*0.01*over8Calories)*7;
		
        this.TOTAL_CALORIES =  (maleCalories + femaleCalories + under8Calories + over8Calories)*7;
	}	
	
	public ArrayList<FoodItem> getHamperItems(){
		return 	this.hamperItems ;
	}
	
    public double getTotalWholeGrains(){
        return 	this.TOTAL_WHOLE_GRAIN_CONTENT;
    }
    
    public double getTotalFV(){
		return  this.TOTAL_FV;
	}	
    
	public double getTotalProtein(){
		return this.TOTAL_PROTEIN;
	}
	
	public double getTotalOther(){
		return this.TOTAL_OTHER ;
	}
	
	public double getTotalCalories(){
		return this.TOTAL_CALORIES ;
	}
	
	public SQLData getMyData(){
		return this.myData;
	}
	
	public void setMyData(SQLData myData){
		this.myData = myData;
	}
	
    public void updateInventory(){
        Inventory inventory = myData.getUpdatedInventory();	  
		ArrayList<FoodItem> foodInsideInventory = inventory.getInventoryItems();
		if(valid){
		    for(int i = 0; i<hamperItems.size();i++){
				foodInsideInventory.set(hamperItems.get(i).getItemID()-1, null);
			}
        }					
	}
    
	public boolean getValid(){
		return this.valid;
	}
	
    public void setValid(boolean validity){
        this.valid = validity;
    }
    
    public void fillHamper(){

    }
}
