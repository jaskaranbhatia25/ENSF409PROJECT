/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;
import java.util.ArrayList;

public class Order implements OrderSummary {

	private ArrayList<Family> families = new ArrayList<Family>();
	private boolean validOrder = false;
	private String gapMessage = new String();
	
	public Order() {}
	
	/**
	 * Constructor for order
	 * @param numOfMales
	 * @param numOfFemales
	 * @param numOfChildrenOver8
	 * @param numOfChildrenUnder8
	 * @throws IllegalArgumentException
	 */
	public void addFamily(int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) throws IllegalArgumentException {
		Family newFamily = new Family(numOfMales, numOfFemales, numOfChildrenOver8, numOfChildrenUnder8);
		this.families.add(newFamily);
	}
	
	/**
	 * getter for families
	 * @return ArrayList<Family>
	 */
	public ArrayList<Family> getFamilies(){
		return this.families;
	}
	
	/**
	 * This getters returns Family at particular index taking index as arguement
	 * @param index
	 * @return Family
	 */
	public Family getFamily(int index) {
		return this.families.get(index);
	}
	
	/**
	 * setter for validity
	 * @param validity
	 */
	public void setValidOrder(boolean validity) {
		this.validOrder = validity;
	}
	
	/**
	 * getter for validity
	 * @return boolean
	 */
	public boolean getValidOrder() {
		return this.validOrder;
	}
	
	/*
	 * This method checks if a order is valid. Set validity true if all the hampers have their valid field as true
	 * else set validity to be false
	 */
	public void validateOrder() {
		if(families.size()!=0) {
			for(int i = 0; i < this.families.size(); i++) {
				if(families.get(i).getHamper().getValid()==false) {
					this.gapMessage = new String(families.get(i).getHamper().checkGaps());
					return;
				}
			}
			this.validOrder=true;
		}

	}
	/**
	 * Returns a string of nutrional components which are insufficient inside inventory
	 * @return
	 */
	public String getGapMessage() {
		return this.gapMessage;
	}
	
	/**
	 * implements method formatSummary from OrderSummary interface,
	 * returns string as Order form
	 * @return String
	 */
	@Override
	public String formatSummary() {
		StringBuilder formattedSummary = new StringBuilder();
		formattedSummary.append("Hamper Order Form\n\n" + "Name:\n" + "Date:\n\n");
		for(int i = 0; i < this.families.size(); i++) {
			formattedSummary.append("Hamper " + (i+1) + " Items:\n");
			ArrayList<FoodItem> hamperItems = this.families.get(i).getHamper().getHamperItems();
			for(int j = 0; j < hamperItems.size(); j++) {
				if(j == hamperItems.size() - 1) {
					formattedSummary.append(hamperItems.get(j).getItemID() + "  " + hamperItems.get(j).getName());
				}
				else {
					formattedSummary.append(hamperItems.get(j).getItemID() + "  " + hamperItems.get(j).getName() + "\n");
				}
				
			}
			if(i == this.families.size() - 1) {
				break;
			}
			else {
				formattedSummary.append("\n\n\n");
			}
		}
		return formattedSummary.toString();
	}
}
