/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory implements Cloneable {
	
	private ArrayList<FoodItem> inventoryItems = new ArrayList<FoodItem>();
	/**
	 * default constructor for inventory
	 */
	public Inventory() {}
	
	/**
	 * getter for inventoryItems
	 * @return ArrayList<FoodItem>
	 */
	public ArrayList<FoodItem> getInventoryItems() {
		return inventoryItems ;
	}
	/*
	 * setter for inventory items
	 */
	public void setInventoryItems(ArrayList<FoodItem>inventoryItems) {
		this.inventoryItems = inventoryItems ;
	}
	/**
	 * returns a FoodItem at particular index at ArrayList
	 * @param index
	 * @return FoodItem
	 */
    public FoodItem getInventoryItem(int index) {
        return inventoryItems.get(index);
    }		
	
    /**
     * Clones Inventory
     * @return Object
     */
	public Object clone() throws CloneNotSupportedException {
		Inventory copy = (Inventory)super.clone();
		copy.inventoryItems = new ArrayList<FoodItem>();
		Iterator<FoodItem> iterator = this.inventoryItems.iterator();
        // This while loops clones every element of ArrayList of FoodItem
		while(iterator.hasNext()) {
			copy.inventoryItems.add((FoodItem)iterator.next().clone());
		}
		return copy;
	}
}
