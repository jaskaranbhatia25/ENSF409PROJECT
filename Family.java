package edu.ucalgary.ensf409;

public class Family {

	private final int NUM_OF_MALES;
	private final int NUM_OF_FEMALES;
	private final int NUM_OF_CHILDREN_OVER8;
	private final int NUM_OF_CHILDREN_UNDER8;
	private Hamper hamper;
	
	public Family(int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) {
		this.NUM_OF_MALES = numOfMales;
		this.NUM_OF_FEMALES = numOfFemales;
		this.NUM_OF_CHILDREN_OVER8 = numOfChildrenOver8;
		this.NUM_OF_CHILDREN_UNDER8 = numOfChildrenUnder8;
	}
	
	public int getNumOfMales() {
		return this.NUM_OF_MALES;
	}
	
	public int getNumOfFemales() {
		return this.NUM_OF_FEMALES;
	}
	
	public int getNumOfChildrenOver8() {
		return this.NUM_OF_CHILDREN_OVER8;
	}
	
	public int getNumOfChildrenUnder8() {
		return this.NUM_OF_CHILDREN_UNDER8;
	}
	
	public Hamper getHamper() {
		return this.hamper;
	}
	
	public void createHamper(SQLData myData, int numOfMales, int numOfFemales, int numOfChildrenOver8, int numOfChildrenUnder8) {
		this.hamper = new Hamper(myData, numOfMales, numOfFemales, numOfChildrenOver8, numOfChildrenUnder8);
	}
}
