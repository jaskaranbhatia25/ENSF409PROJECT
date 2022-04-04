package edu.ucalgary.ensf409;

public class Inventory{
	
	private ArrayList<FoodItem> items = new ArrayList<>();
	
	public Inventory(){	
	}
	public void removeItem(int index){
		
		items.set(index -1, null);
		
	}	
	public ArrayList<FoodItem> getItems(){
		return items;
	}
    public FoodItem getItem(int index){
        return items.get(index);
    }		
	
}	
	
	
	
	
	
	
