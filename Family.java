/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

public class Family {

	private final int NUM_OF_MALES;
	private final int NUM_OF_FEMALES;
	private final int NUM_OF_CHILDREN_OVER8;
	private final int NUM_OF_CHILDREN_UNDER8;
	private Hamper hamper;
	
	/**
	 * constructor for Family
	 * @param numOfMales
	 * @param numOfFemales
	 * @param numOfChildrenOver8
	 * @param numOfChildrenUnder8
	 */
	public Family(int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) {
		this.NUM_OF_MALES = numOfMales;
		this.NUM_OF_FEMALES = numOfFemales;
		this.NUM_OF_CHILDREN_OVER8 = numOfChildrenOver8;
		this.NUM_OF_CHILDREN_UNDER8 = numOfChildrenUnder8;
	}
	
	/**
	 * getter method for the number of males
	 * @return int 
	 */
	public int getNumOfMales() {
		return this.NUM_OF_MALES;
	}
	
	/**
	 * getter method for the number of females
	 * @return int 
	 */
	public int getNumOfFemales() {
		return this.NUM_OF_FEMALES;
	}
	
	/**
	 * getter method for the number of female
	 * @return int 
	 */
	public int getNumOfChildrenOver8() {
		return this.NUM_OF_CHILDREN_OVER8;
	}
	
	/**
	 * getter method for the number of children under 8
	 * @return int 
	 */
	public int getNumOfChildrenUnder8() {
		return this.NUM_OF_CHILDREN_UNDER8;
	}
	/**
	 * getter method for the hamper
	 * @return Hamper 
	 */
	public Hamper getHamper() {
		return this.hamper;
	}
	
	/**
	 * this calls the hamper constructor and sets all the fields with total values by communicating with the SQLData
	 * @param myData
	 * @param numOfMales
	 * @param numOfFemales
	 * @param numOfChildrenOver8
	 * @param numOfChildrenUnder8
	 */
	public void createHamper(SQLData myData, int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) {
		this.hamper = new Hamper(myData, numOfMales, numOfFemales, numOfChildrenOver8, numOfChildrenUnder8);
	}
}
