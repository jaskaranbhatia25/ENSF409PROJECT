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
	
	public Inventory() {}
	
	/**
	 * 
	 * @param index
	 */
	public void removeItem(int index) {
		inventoryItems .set(index -1, null);	
	}	
	
	/**
	 * 
	 * @return ArrayList<FoodItem>
	 */
	public ArrayList<FoodItem> getInventoryItems() {
		return inventoryItems ;
	}
	
	/**
	 * 
	 * @param index
	 * @return FoodItem
	 */
    public FoodItem getInventoryItem(int index) {
        return inventoryItems.get(index);
    }		
	
    /**
     * 
     * @return Object
     */
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
