/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements OrderSummary {

	private ArrayList<Family> families = new ArrayList<Family>();
	private boolean validOrder = false;
	private String name = "Order";
	private LocalDate date = LocalDate.now();
	
	public Order() {}
	
	/**
	 * 
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
	 * 
	 * @return ArrayList<Family>
	 */
	public ArrayList<Family> getFamilies(){
		return this.families;
	}
	
	/**
	 * 
	 * @param index
	 * @return Family
	 */
	public Family getFamily(int index) {
		return this.families.get(index);
	}
	
	/**
	 * 
	 * @param validity
	 */
	public void setValidOrder(boolean validity) {
		this.validOrder = validity;
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean getValidOrder() {
		return this.validOrder;
	}
	
	/*
	 * 
	 */
	public void validateOrder() {
		if(families.size()!=0) {
			for(int i = 0; i < this.families.size(); i++) {
				if(!families.get(i).getHamper().getValid()) {
					return;
				}
			}
			this.validOrder=true;
		}

	}
	
	/**
	 * @param numOfOrders
	 * @return String
	 */
	@Override
	public String formatSummary(int numOfOrders) {
		StringBuilder formattedSummary = new StringBuilder();
		formattedSummary.append("Hamper Order Form\n\n" + "Name: " + this.name +" "+numOfOrders+ "\n" + "Date: " + this.date + "\n\n");
		for(int i = 0; i < this.families.size(); i++) {
			formattedSummary.append("Hamper " + (i+1) + " Items\n");
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
