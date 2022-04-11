/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

public class FoodItem implements Cloneable {
	
	private final int ITEM_ID;
	private final int GRAIN_CONTENT;
	private final int FV_CONTENT;
	private final int PRO_CONTENT;
	private final int OTHER;
	private final int CALORIES;
	private final String NAME;
	
	/**
	 * constructor for FoodItem
	 * @param itemID 
	 * @param name 
	 * @param grainContent 
	 * @param fvContent
	 * @param proContent 
	 * @param other 
	 * @param calories 
	 */
	public FoodItem(int itemID, String name, int grainContent, int fvContent, int proContent, int other, int calories) {
		this.ITEM_ID = itemID;
		this.NAME = name;
		this.GRAIN_CONTENT = grainContent;
		this.FV_CONTENT = fvContent;
		this.PRO_CONTENT = proContent;
		this.OTHER = other;
		this.CALORIES = calories;
	}
	
	/**
	 *  getter method for Name
	 * @return String 
	 */
    public String getName() {
		return this.NAME;
	}
    
    /**
     *  getter method for ItemID
     * @return int 
     */
	public int getItemID() {
		return this.ITEM_ID;
	}
	
    /**
     * getter method for GrainContent
     * @return int
     */
	public int getGrainContent() {
		return this.GRAIN_CONTENT;
	}
	
    /**
     * getter method for FVContent
     * @return int
     */
	public int getFVContent() {
		return this.FV_CONTENT;
	}
	
    /**
     * getter method for ProContent
     * @return int 
     */
	public int getProContent() {
		return this.PRO_CONTENT;
	}
	
    /**
     *  getter method for OtherContent
     * @return int 
     */
	public int getOtherContent() {
		return this.OTHER;
	}
	
    /**
     *  getter method for calories
     * @return int
     */
	public int getCalories() {
		return this.CALORIES;
	}
	
    /**
     * clone method for FoodItem class
     * @return Object 
     */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
