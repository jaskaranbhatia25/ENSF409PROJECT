/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

import java.util.*;

public class Hamper {
	
	private SQLData myData;
	private final double TOTAL_WHOLE_GRAIN;
	private final double TOTAL_FV;
	private final double TOTAL_PROTEIN;
	private final double TOTAL_OTHER;
	private final double TOTAL_CALORIES;
	private double actualGrainContent;
	private double actualFV;
	private double actualProtein;
	private double actualOther;
	private double actualCalories;
	private boolean valid = false;
	private  FoodItem dummyFood = new FoodItem(-1,"",-1,-1,-1,-1,-1);
	private ArrayList<FoodItem> hamperItems = new ArrayList<>();
	
	/**
	 * 
	 * @param myData
	 * @param male
	 * @param female
	 * @param under8
	 * @param above8
	 */
	public Hamper(SQLData myData, int male, int female,  int under8, int above8){
		
		int maleCalories = myData.getClients().get(0).getCalories();
		int femaleCalories = myData.getClients().get(1).getCalories();
		int under8Calories = myData.getClients().get(2).getCalories();
		int over8Calories = myData.getClients().get(3).getCalories();
		this.myData = myData;	
		this.TOTAL_WHOLE_GRAIN = (myData.getClients().get(0).getWholeGrains()*male*0.01*maleCalories
									+ myData.getClients().get(1).getWholeGrains()*female*0.01*femaleCalories 
									+ myData.getClients().get(2).getWholeGrains()*under8*0.01*under8Calories 
									+ myData.getClients().get(3).getWholeGrains()*above8*0.01*over8Calories)*7;
		
	    this.TOTAL_FV = (myData.getClients().get(0).getFruitVeggies()*male*0.01*maleCalories
	    					+ myData.getClients().get(1).getFruitVeggies()*female*0.01*femaleCalories 
							+ myData.getClients().get(2).getFruitVeggies()*under8*0.01*under8Calories 
							+ myData.getClients().get(3).getFruitVeggies()*above8*0.01*over8Calories)*7;
	    
        this.TOTAL_PROTEIN = (myData.getClients().get(0).getProtein()*male*0.01*maleCalories
					        	+ myData.getClients().get(1).getProtein()*female*0.01*femaleCalories 
					        	+ myData.getClients().get(2).getProtein()*under8*0.01*under8Calories 
					        	+ myData.getClients().get(3).getProtein()*above8*0.01*over8Calories)*7;
        
		this.TOTAL_OTHER = (myData.getClients().get(0).getOther()*male*0.01*maleCalories
							+ myData.getClients().get(1).getOther()*female*0.01*femaleCalories 
					    	+ myData.getClients().get(2).getOther()*under8*0.01*under8Calories 
					    	+ myData.getClients().get(3).getOther()*above8*0.01*over8Calories)*7;
		
        this.TOTAL_CALORIES = (maleCalories*male + femaleCalories*female + under8Calories*under8 + over8Calories*above8)*7;
	}
	
	/**
	 * 
	 * @param hamperItems
	 * @param actualWholeGrains
	 * @param actualFruitVeggies
	 * @param actualProtein
	 * @param actualOther
	 * @param actualCalories
	 */
	public Hamper(ArrayList<FoodItem> hamperItems, double actualWholeGrains, double actualFruitVeggies, double actualProtein, double actualOther, double actualCalories) {
		this.TOTAL_WHOLE_GRAIN = 0;
		this.TOTAL_FV = 0;
		this.TOTAL_PROTEIN = 0;
		this.TOTAL_OTHER = 0;
		this.TOTAL_CALORIES = 0;
		this.hamperItems = hamperItems;
		this.actualGrainContent = actualWholeGrains;
		this.actualFV = actualFruitVeggies;
		this.actualProtein = actualProtein;
		this.actualOther = actualOther;
		this.actualCalories = actualCalories;
	}
	
	/**
	 * 
	 * @return ArrayList<FoodItem>
	 */
	public ArrayList<FoodItem> getHamperItems(){
		return 	this.hamperItems ;
	}

    /**
     * 
     * @return double
     */
    public double getTotalWholeGrains(){
        return 	this.TOTAL_WHOLE_GRAIN;
    }
    
    /**
     * 
     * @return double
     */
    public double getTotalFV(){
		return  this.TOTAL_FV;
	}	
    
    /**
     * 
     * @return double
     */
	public double getTotalProtein(){
		return this.TOTAL_PROTEIN;
	}
	
    /**
     * 
     * @return double
     */
	public double getTotalOther(){
		return this.TOTAL_OTHER ;
	}
	
    /**
     * 
     * @return double
     */
	public double getTotalCalories(){
		return this.TOTAL_CALORIES ;
	}
	
    /**
     * 
     * @return double
     */
    public double getActualWholeGrains(){
        return 	this.actualGrainContent;
    }
    
    /**
     * 
     * @return double
     */
    public double getActualFV(){
		return  this.actualFV;
	}	
    
    /**
     * 
     * @return double
     */
	public double getActualProtein(){
		return this.actualProtein;
	}
	
    /**
     * 
     * @return double
     */
	public double getActualOther(){
		return this.actualOther;
	}
	
    /**
     * 
     * @return double
     */
	public double getActualCalories(){
		return this.actualCalories;
	}
	
	/**
	 * 
	 * @return SQLData
	 */
	public SQLData getMyData(){
		return this.myData;
	}
	
	/**
	 * 
	 * @param myData
	 */
	public void setMyData(SQLData myData){
		this.myData = myData;
	}
	
	/*
	 * 
	 */
    public void updateInventory(){
        Inventory inventory = myData.getUpdatedInventory();	  
		ArrayList<FoodItem> foodInsideInventory = inventory.getInventoryItems();
		if(valid){
		    for(int i = 0; i<hamperItems.size();i++){
				foodInsideInventory.set(hamperItems.get(i).getItemID()-1, this.dummyFood);
			}
        }					
	}
    
    /**
     * 
     * @return boolean
     */
	public boolean getValid(){
		return this.valid;
	}
	
	/**
	 * 
	 * @param validity
	 */
    public void setValid(boolean validity){
        this.valid = validity;
    }
    
    /**
     * 
     * @param set
     * @return  ArrayList<ArrayList<FoodItem>>
     */
	public ArrayList<ArrayList<FoodItem>> getSubsets(ArrayList<FoodItem> set) {

		ArrayList<ArrayList<FoodItem>> subsetCollection = new ArrayList<ArrayList<FoodItem>>();

		if (set.size() == 0) {
			subsetCollection.add(new ArrayList<FoodItem>());
		} 
		else {
			ArrayList<FoodItem> reducedSet = new ArrayList<FoodItem>();
			reducedSet.addAll(set);
			FoodItem first = reducedSet.remove(0);
			ArrayList<ArrayList<FoodItem>> subsets = getSubsets(reducedSet);
			subsetCollection.addAll(subsets);
			subsets = getSubsets(reducedSet);

			for (ArrayList<FoodItem> subset : subsets) {
				if(!first.getName().equals("")) {
					subset.add(0, first);
				}
			}
			subsetCollection.addAll(subsets);
		}
		return subsetCollection;
	}
	
	/*
	 * 
	 */
	public void fillHamper() {
		
    	Inventory inventory = myData.getUpdatedInventory();
    	ArrayList<FoodItem> items = inventory.getInventoryItems();
    	
    	if(items.size() <= 25) {
    		ArrayList<ArrayList<FoodItem>> allsubsets = getSubsets(items);
        	ArrayList<Hamper> hamperCombos = new ArrayList<Hamper>();
        	
        	for(int i = 0; i < allsubsets.size(); i++) {
        		double wg = 0;
        		double fv = 0;
        		double pro = 0;
        		double other = 0;
        		double cal = 0;
        		for(int j = 0; j < allsubsets.get(i).size(); j++) {
        			wg += allsubsets.get(i).get(j).getGrainContent()*allsubsets.get(i).get(j).getCalories()*0.01;
        			fv += allsubsets.get(i).get(j).getFVContent()*allsubsets.get(i).get(j).getCalories()*0.01;
        			pro += allsubsets.get(i).get(j).getProContent()*allsubsets.get(i).get(j).getCalories()*0.01;
        			other += allsubsets.get(i).get(j).getOtherContent()*allsubsets.get(i).get(j).getCalories()*0.01;
        			cal += allsubsets.get(i).get(j).getCalories();
        		}
        		System.out.println("wg" + wg + " fv" + fv + " pro" + pro + " other" + other + " cal" + cal);
        		if(wg>=TOTAL_WHOLE_GRAIN && fv>=TOTAL_FV && pro>=TOTAL_PROTEIN && other>=TOTAL_OTHER && cal>=TOTAL_CALORIES) {
        			hamperCombos.add(new Hamper(allsubsets.get(i), wg, fv, pro, other, cal));
        		}
        	}
        	if(!hamperCombos.isEmpty()) {
        		Hamper optimalHamper = hamperCombos.get(0);
        		for(int i = 0; i < hamperCombos.size(); i++) {
        			if(hamperCombos.get(i).getActualCalories() < optimalHamper.getActualCalories()) {
        				optimalHamper = hamperCombos.get(i);
        			}
        		}
            this.hamperItems = optimalHamper.getHamperItems();
            this.valid = true;
        	}
        	else {
        		return;
        	}
    	}
    	else {
    		double wg = 0;
    		double fv = 0;
    		double pro = 0;
    		double other = 0;
    		double cal = 0;
    		for(int i = 0; i < items.size(); i++) {
    			if(wg<=TOTAL_WHOLE_GRAIN || fv<=TOTAL_FV || pro<=TOTAL_PROTEIN || other<=TOTAL_OTHER || cal<=TOTAL_CALORIES) {
    				this.hamperItems.add(items.get(i));
    				wg+=items.get(i).getGrainContent()*items.get(i).getCalories()*0.01;
    				fv+=items.get(i).getFVContent()*items.get(i).getCalories()*0.01;
    				pro+=items.get(i).getProContent()*items.get(i).getCalories()*0.01;
    				other+=items.get(i).getOtherContent()*items.get(i).getCalories()*0.01;
    				cal+=items.get(i).getCalories();
    			}
    		}
    		if(wg>=TOTAL_WHOLE_GRAIN && fv>=TOTAL_FV && pro>=TOTAL_PROTEIN && other>=TOTAL_OTHER && cal>=TOTAL_CALORIES) {
    			this.valid = true;
    		}
    		else {
    			return;
    		}
    	}
	}
}
