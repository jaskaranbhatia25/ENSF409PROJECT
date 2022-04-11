/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

public class ClientType implements Cloneable {
	
	private final int CLIENT_ID;
	private final String CLIENT;
	private final int WHOLE_GRAINS;
	private final int FRUIT_VEGGIES;
	private final int PROTEIN;
	private final int OTHER;
	private final int CALORIES;
	
	/**
	 * Constructor for ClientType
	 * @param clientID
	 * @param client
	 * @param wholeGrains
	 * @param fruitVeggies
	 * @param protein
	 * @param other
	 * @param calories
	 */
	public ClientType( int clientID, String client , int wholeGrains,  int fruitVeggies,  int protein, int other, int calories) {	
	    this.CLIENT_ID = clientID;
	    this.CLIENT = client;
	    this.WHOLE_GRAINS = wholeGrains;
	    this.FRUIT_VEGGIES = fruitVeggies;
	    this.PROTEIN = protein;
	    this.OTHER = other;
	    this.CALORIES = calories;		
	}
	
	/**
	 * getter for ClientID
	 * @return int
	 */
	public int getClientID() {
		return this.CLIENT_ID;
	}
	
	/**
	 * getter for Client i.e male, female etc
	 * @return String
	 */
	public String getClient() {
		return this.CLIENT;
	}
	
	/**
	 * getter for WholeGrains content in percentage
	 * @return int
	 */
	public int getWholeGrains() {
		return this.WHOLE_GRAINS;
	}
	
	/**
	 * getter for FruitVeggies content in percentage
	 * @return int
	 */
	public int getFruitVeggies() {
		return this.FRUIT_VEGGIES;
	}
	
	/**
	 * getter for Protein content in percentage
	 * @return int
	 */
	public int getProtein() {
		return this.PROTEIN;
	}
	
	/**
	 * getter for other content in percentage
	 * @return int
	 */
	public int getOther() {
		return this.OTHER;
	}
	
	/**
	 * getter for Calories content in percentage
	 * @return int
	 */
	public int getCalories() {
		return this.CALORIES;
	}
}
