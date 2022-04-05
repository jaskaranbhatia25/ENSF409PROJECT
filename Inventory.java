package edu.ucalgary.ensf409;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Cloneable {
	
	private ArrayList<FoodItem> inventoryItems = new ArrayList<>();
	
	public Inventory(){}
	
	public void removeItem(int index){
		inventoryItems .set(index -1, null);	
	}	
	
	public ArrayList<FoodItem> getInventoryItems(){
		return inventoryItems ;
	}
	
    public FoodItem getInventoryItem(int index){
        return inventoryItems.get(index);
    }		
	
	public Object clone() throws CloneNotSupportedException {
		Inventory copy = (Inventory)super.clone();
		copy.inventoryItems = new ArrayList<FoodItem>();
		Iterator<FoodItem> iterator = this.inventoryItems.iterator();

		while(iterator.hasNext()) {
			copy.inventoryItems.add((FoodItem)iterator.next().clone());
		}
		return copy;
	}
}
