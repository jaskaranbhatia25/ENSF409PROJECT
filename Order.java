package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements OrderSummary {

	private ArrayList<Family> families = new ArrayList<Family>();
	private boolean validOrder = false;
	private String name;
	private LocalDate date = LocalDate.now();
	
	
	public Order() {}
	
	public void addFamily(int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) throws IllegalArgumentException {
		Family newFamily = Family(numOfMales, numOfFemales, numOfChildrenOver8, numOfChildrenUnder8);
		this.families.add(newFamily);
	}
	
	public ArrayList<Family> getFamilies(){
		return this.families;
	}
	
	public Family getFamily(int index) {
		return this.families.get(index);
	}
	
	public void setValidOrder(boolean validity) {
		this.validOrder = validity;
	}
	
	public boolean getValidOrder() {
		return this.validOrder;
	}
	
	public void validateOrder() {
		for(int i = 0; i < this.families.size(); i++) {
			if(families.get(i).getHamper().getValid() == false) {
				this.validOrder = false;
				break;
			}
		}
		this.validOrder = true;
	}
	
	@Override
	public String formatSummary() {
		StringBuilder formattedSummary = new StringBuilder();
		formattedSummary.append("Hamper Order Form\n\n" + "Name: " + this.name + "\n" + "Date: " + this.date + "\n\n");
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
