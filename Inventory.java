package edu.ucalgary.ensf409;

public class Inventory{
	
	private ArrayList<FoodItem> inventoryItems = new ArrayList<>();
	
	public Inventory(){	
	}
	public void removeItem(int index){
		
		inventoryItems .set(index -1, null);
		
	}	
	public ArrayList<FoodItem> getInventoryItems(){
		return inventoryItems ;
	}
    public FoodItem getInventoryItem(int index){
        return inventoryItems.get(index);
    }		
	
}	
	
	
	
	
	
	
