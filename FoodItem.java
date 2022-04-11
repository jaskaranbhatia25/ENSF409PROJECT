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
	 * 
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
	 * 
	 * @return String
	 */
    public String getName() {
		return this.NAME;
	}
    
    /**
     * 
     * @return int
     */
	public int getItemID() {
		return this.ITEM_ID;
	}
	
    /**
     * 
     * @return int
     */
	public int getGrainContent() {
		return this.GRAIN_CONTENT;
	}
	
    /**
     * 
     * @return int
     */
	public int getFVContent() {
		return this.FV_CONTENT;
	}
	
    /**
     * 
     * @return int
     */
	public int getProContent() {
		return this.PRO_CONTENT;
	}
	
    /**
     * 
     * @return int
     */
	public int getOtherContent() {
		return this.OTHER;
	}
	
    /**
     * 
     * @return int
     */
	public int getCalories() {
		return this.CALORIES;
	}
	
    /**
     * 
     * @return Object
     */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
