/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 3.0
@since 1.0 - Mar. 28/2022
*/

/*
 * This is a class designed to test the functionality of Group #81's ENSF409 Hamper Project.
 */

package edu.ucalgary.ensf409;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;

/* All the checks for boundary conditions such as:
 *  1) Number of any ClientType cannot be less than 0 or greater than 10.
 *  2) Order cannot be submitted with 0 families added i.e order should have atleast 1 family.
 *  3) We cannot create a family with 0 family members.
 *  4) We cannot pass in a String or non-integer value for number of males. females, etc.. inside family constructor
 *  The above boundary conditions are checked inside the GUIOrder class by ValidateInput method
 *  So, there are no tests for exceptions or boundary conditions as all the data is filtered before it is passed into any
 *  constructor with no possible situation that any kind of java exception be thrown.
 */
public class TestDesign {

    private SQLData testData = new SQLData("ansh","aleks","jassi");
    
    private static final double DELTA = 1e-15;
	private ClientType male = new ClientType(0, "male", 20, 30, 40, 10, 100);
	private ClientType female = new ClientType(1, "female", 20, 30, 40, 10, 50);
	private ClientType childover8 = new ClientType(2, "childover8", 20, 30, 40, 10, 30);
	private ClientType childunder8 = new ClientType(3, "childunder8", 20, 30, 40, 10, 15);
	private ArrayList<ClientType> clients = testData.getClients();

	
	private FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce",0, 80, 10, 10, 50);
	private FoodItem pasta = new FoodItem( 2,"Pasta", 80, 10, 0, 10, 75);
	private FoodItem beef = new FoodItem( 3,"Beef", 10, 0, 80, 10, 100);
	private FoodItem apples = new FoodItem( 4,"Apples", 30, 50, 10, 10, 50);
	private FoodItem candy = new FoodItem(5,"Candy",  10, 0, 0, 90, 150);

	/**
	 * This method fills FoodItems in Inventory and add ClientType to clients ArrayList inside SQLData
	 */
	public void fillSQLData() {
		this.clients.add(male);
		this.clients.add(female);
		this.clients.add(childover8);
		this.clients.add(childunder8);
		this.testData.getUpdatedInventory().getInventoryItems().add(tomatoSauce);
		this.testData.getUpdatedInventory().getInventoryItems().add(pasta);
		this.testData.getUpdatedInventory().getInventoryItems().add(beef);
		this.testData.getUpdatedInventory().getInventoryItems().add(apples);
		this.testData.getUpdatedInventory().getInventoryItems().add(candy);

	}

	
	/* This test checks to see that the setValidOrder() method inside of the Order class properly sets the
	Order validity to true.*/
    @Test
	public void testOrderSetValidOrder(){
		Order testOrder = new Order();
		testOrder.setValidOrder(true);
        assertEquals("Method setValidOrder did not return the expected result: ", true, testOrder.getValidOrder());	
	}	
	
	/* This test checks to see if the getValidOrder() method returns a boolean value of false, when there is
	no Families with Hampers in the order.*/
	@Test
	public void testOrderGetValidOrder(){
		Order testOrder = new Order();
        assertEquals("Method getValidOrder did not return the expected result: ", false, testOrder.getValidOrder());	
	}	
	
	/* This test checks to see if the getValidOrder() method returns a boolean value of true, when there is
	A Familiy with a valid Hamper in the order.*/
	@Test
	public void testOrderValidateOrderWhenOrderIsValid(){
		 fillSQLData();
		 Order testOrder = new Order();
		 testOrder.addFamily(0,0,1,0);
		 Family myFamily = testOrder.getFamily(0);
		 myFamily.createHamper(testData, 0, 0, 1, 0);
		 Hamper myHamper = myFamily.getHamper();
		 myHamper.fillHamper();
		 testOrder.validateOrder();
		 assertEquals("Method ValidateOrder did not set validOrder true when the order was valid: ", true, testOrder.getValidOrder());
		
	}	
	
	/* This test checks to see if the getValidOrder() method returns a boolean value of false, when there
	atleast one Family with a valid Hamper, and atleast one Family with an invalid Hamper in the order.*/
	@Test
	public void testOrderValidateOrderWhenOrderIsInvalid(){
		fillSQLData();
		Order testOrder = new Order();
		testOrder.addFamily(1,0,0,0);
		testOrder.addFamily(5,5,5,1);
		Family myFamilyOne = testOrder.getFamily(0);
		Family myFamilyTwo = testOrder.getFamily(1);
		myFamilyOne.createHamper(testData, myFamilyOne.getNumOfMales() , myFamilyOne.getNumOfFemales(), myFamilyOne.getNumOfChildrenOver8(), myFamilyOne.getNumOfChildrenUnder8());
	    myFamilyTwo.createHamper(testData, myFamilyTwo.getNumOfMales() , myFamilyTwo.getNumOfFemales(), myFamilyTwo.getNumOfChildrenOver8(), myFamilyTwo.getNumOfChildrenUnder8());
		Hamper myHamperOne = myFamilyOne.getHamper();
		Hamper myHamperTwo = myFamilyTwo.getHamper();
		myHamperOne.fillHamper();
        myHamperTwo.fillHamper();	
        testOrder.validateOrder();
		assertEquals("Method ValidateOrder did not set validOrder false when the order was invalid: ", false, testOrder.getValidOrder());
		
	}	

	/* This test checks to see if the getFamilies() method of the Order object returns a Family ArrayList
	with one Family object in it.*/
    @Test
    public void testOrderGetFamilies() {
    	 Order testOrder = new Order();
    	testOrder.addFamily(1,0,0,0);
    	assertNotNull("getFamilies returned null, expected not null", testOrder.getFamilies());
    }
	/* This test checks to see if the getFamily() method of the Order object returns a Family object*/
    @Test
    public void testOrderGetFamily() {
   	 Order testOrder = new Order();
   	testOrder.addFamily(1,0,0,0);
   	testOrder.getFamily(0);
   	assertNotNull("getFamilies returned null, expected not null", testOrder.getFamily(0));
   }
	/* This test checks to see if the addFamily() method in Order sucessfully adds a Family to the
	Family ArrayList.*/
	@Test
    public void testOrderAddFamily() {
    	Order testOrder = new Order();
    	testOrder.addFamily(1,0,0,0);
    	assertNotNull("getFamilies returned null, expected not null", testOrder.getFamilies().get(0));
    }
	
	/* This test checks to see if the Family object constructor successfully creates a family object.*/
    @Test
    public void testFamilyConstructor() {
    	Family testFamily = new Family(1,0,0,0);
    	assertNotNull("Family constructor failed to create a Family object", testFamily);
    }
    
	/* This test checks to see if the createHamper() method in the Family class
	sucessfully creates a Hamper object for the Family object it is called from.*/
    @Test
    public void testFamilyCreateHamper() {
    	fillSQLData();
    	Family testFamily = new Family(1,0,0,0);

    	testFamily.createHamper(testData, testFamily.getNumOfMales() , testFamily.getNumOfFemales(), testFamily.getNumOfChildrenOver8(), testFamily.getNumOfChildrenUnder8());
    	Hamper testHamper = testFamily.getHamper();
    	assertNotNull("createHamper returned null, expected not null", testHamper);
    }
    
	/*This test checks to see if the getHamper() method of the Family object returns the Hamper object
	for the Family object it was created for.*/
    @Test
    public void testFamilyGetHamper() {
    	fillSQLData();
    	Family testFamily = new Family(1,0,0,0);
    	testFamily.createHamper(testData, testFamily.getNumOfMales() , testFamily.getNumOfFemales(), testFamily.getNumOfChildrenOver8(), testFamily.getNumOfChildrenUnder8());
    	Hamper testHamper = testFamily.getHamper();
    	assertNotNull("getHamper returned null, expected not null", testHamper);
    }

	/* This test checks to see if all of the getters for the numOfMales, numOfFemales, numOfChildrenOver8, and numOfChildrenUnder8,
	for the Family object.*/
    @Test
    public void testFamilyNumOfGetters() {
    	int expectedNumOfMales = 1;
    	int expectedNumOfFemales = 1;
    	int expectedNumOfChildrenOver8 = 1;
    	int expectedNumOfChildrenUnder8 = 1;
    	
    	Family testObj = new Family(expectedNumOfMales, expectedNumOfFemales, expectedNumOfChildrenOver8, expectedNumOfChildrenUnder8);
    	int actualNumOfMales = testObj.getNumOfMales();
    	int actualNumOfFemales = testObj.getNumOfFemales();
    	int actualNumOfChildrenOver8 = testObj.getNumOfChildrenOver8();
    	int actualNumOfChildrenUnder8 = testObj.getNumOfChildrenUnder8();
    	assertEquals("Constructor or Getter for numOfMales gave incorrect value", expectedNumOfMales, actualNumOfMales);
    	assertEquals("Constructor or Getter for numOfFemales gave incorrect value", expectedNumOfFemales, actualNumOfFemales);
    	assertEquals("Constructor or Getter for numOfChildrenOver8 gave incorrect value", expectedNumOfChildrenOver8, actualNumOfChildrenOver8);
    	assertEquals("Constructor or Getter for numOfChildrenUnder8 gave incorrect value", expectedNumOfChildrenUnder8, actualNumOfChildrenUnder8);
    }
    
	/* This method checks to see if the formartSummary() method implemented in the Order object, from
	the OrderSummaray interface properly formats the summary of a valid order.*/
    @Test
    public void testOrderSummaryFormatSummary() {
    	fillSQLData();
    	String expectedOutput = "Hamper Order Form\n\nName:\nDate:\n\nHamper 1 Items:\n1  Tomato Sauce\n2  Pasta\n3  Beef\n4  Apples";
    	Order testOrder = new Order();
    	testOrder.addFamily(0,0,1,0);
    	Family testFamily = testOrder.getFamily(0);
       testFamily.createHamper(testData, testFamily.getNumOfMales() , testFamily.getNumOfFemales(), testFamily.getNumOfChildrenOver8(), testFamily.getNumOfChildrenUnder8());
       Hamper testHamper = testFamily.getHamper();
       testHamper.fillHamper();
    	String actualOutput = testOrder.formatSummary();
    	assertEquals("formatSummaray did not match the expected output", expectedOutput, actualOutput);
    }
    @Test
    /*
     * This test checks for whether checkGaps determine right deficiencies
     */
	public void testCheckGaps() {
		String expectedString = "whole grains,fruits or veggies,protein,other content,";
		
		fillSQLData();
		Hamper testHamper = new Hamper(testData, 1, 1, 1, 1);
		testHamper.setActualWholeGrains(0);
		testHamper.setActualFV(0);
		testHamper.setActualProtein(0);
		testHamper.setActualOther(0);
		testHamper.setActualCalories(testHamper.getTotalCalories()+1);
		
		assertEquals("checkGaps method did not identify the correct deficiences.", expectedString, testHamper.checkGaps());
	}
	
	@Test
	/*
	 * This tests check if the gapMessage returns expected string
	 */
	public void testGetGapMessage() {
		String expectedString = "";
		
		Order testOrder = new Order();
		
		assertEquals("getGapMessage did not return the correct string", expectedString, testOrder.getGapMessage());
	}
    
	/* This test checks to see if the getInventoryItems() method of the Inventory class successfully returns
	a FoodItem ArrayList.*/
    @Test
    public void testInventoryGetInventoryItems() {
    	fillSQLData();
    	ArrayList<FoodItem> testInventory = testData.getUpdatedInventory().getInventoryItems();
    	assertNotNull("getItems returned null, expected not null", testInventory);
    }

    
	/* This test checks to see if the getItem() method of the Inventory class returns a specified FoodItem
	of the FoodItem ArrayList of the inventory class.*/
    @Test
    public void testInventoryGetItem() {
    	
    	SQLData testData = new SQLData("ansh","aleks","jassi");
    	
    	FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce", 0, 80, 10, 10, 50);
    	testData.getUpdatedInventory().getInventoryItems().add(tomatoSauce);

		Inventory testInventory = testData.getUpdatedInventory();
		FoodItem myFoodItem = testInventory.getInventoryItems().get(0);
		
        String expectedName = "Tomato Sauce";
		int expectedgrainContent = 0;
		int expectedfvContent = 80;
		int expectedproteinContent = 10;
		int expectedOther = 10;
		int expectedCalories = 50;
		int expectedITEMID = 1;
		
		String actualName = myFoodItem.getName();
		int actualgrainContent = myFoodItem.getGrainContent();
		int actualfvContent = myFoodItem.getFVContent();
		int actualproteinContent = myFoodItem.getProContent();
		int actualOther = myFoodItem.getOtherContent();
		int actualCalories = myFoodItem.getCalories();
		int actualITEMID = myFoodItem.getItemID();
		
   	    assertEquals("getItem returned wrong Food Item, therefore getName gave incorrect value", expectedName, actualName);
    	assertEquals("getItem returned wrong Food Item, therefore getGrainContent gave incorrect value", expectedgrainContent, actualgrainContent);
    	assertEquals("getItem returned wrong Food Item, therefore getFVContent gave incorrect value", expectedfvContent, actualfvContent);
    	assertEquals("getItem returned wrong Food Item, therefore getProContent gave incorrect value", expectedproteinContent, actualproteinContent);
		assertEquals("getItem returned wrong Food Item, therefore getOtherContent gave incorrect value", expectedOther, actualOther);
        assertEquals("getItem returned wrong Food Item, therefore getCalories gave incorrect value", expectedCalories, actualCalories);
        assertEquals("getItem returned wrong Food Item, therefore getItemID gave incorrect value", expectedITEMID, actualITEMID);
    }

	/* This test checks if the hamper constructor successfully creates
	a hamper object and if the hamper fields inside family is not null 
	after calling the createHamper() method inside family,
	tests for fields of the hamper are done separately*/
	@Test
    public void testHamperConstructor() {
		fillSQLData();
		Order myOrder = new Order();
		myOrder.addFamily(1,0,1,0);		
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());
		Hamper myHamper =myFamily.getHamper();
        assertNotNull("Hamper constructor did not create an object when called from family.", myHamper);
    }	
	
	/* This test checks the getters of the class Hamper by hard coding the expected results, 
	we created our own dummy inventory and dummy clintTypes at the top of this 
	test file to check the functionaly of the getters of Hamper class*/
    @Test
    public void testHamperTotalValueGettersAndConstructor() {
		
    	fillSQLData();
    	double expectedTotalWG =  ((0.2*100+2*0.2*50+2*0.2*30+0.20*15)*7);
    	double expectedTotalFV =  ((0.3*100+2*0.3*50+2*0.3*30+0.3*15)*7);
    	double expectedTotalProtien =  ((0.4*100+2*0.4*50+2*0.4*30+0.40*15)*7);
    	double expectedTotalOther =  ((0.1*100+2*0.1*50+2*0.1*30+0.1*15)*7);
    	double expectedTotalCal =(100+2*50+2*30+15)*7;
    	double expectedWG =  40.3;	
    	double expectedFV =  20.3;
    	double expectedProtien =  21.21;
    	double expectedOther =  53.32;
    	double expectedCal = 12.23;
		Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());
		Hamper myHamper = myFamily.getHamper();
		myHamper.setActualCalories(expectedCal);
		myHamper.setActualOther(expectedOther);
		myHamper.setActualFV(expectedFV);
		myHamper.setActualProtein(expectedProtien);
		myHamper.setActualWholeGrains(expectedWG);
		double actualTotalWG =  myHamper.getTotalWholeGrains();
		double actualTotalFV =  myHamper.getTotalFV();
		double actualTotalProtien =  myHamper.getTotalProtein();
		double actualTotalOther =  myHamper.getTotalOther();
		double actualTotalCal	=  myHamper.getTotalCalories();	
		double actualWG =  myHamper.getActualWholeGrains();
		double actualFV =  myHamper.getActualFV();
		double actualProtien =  myHamper.getActualProtein();
		double actualOther =  myHamper.getActualOther();
		double actualCal	=  myHamper.getActualCalories();
    	assertEquals("Constructor or Getter for TotalWG gave incorrect value", expectedTotalWG, actualTotalWG, DELTA);
    	assertEquals("Constructor or Getter for TotalFV gave incorrect value", expectedTotalFV, actualTotalFV,DELTA);
    	assertEquals("Constructor or Getter for TotalProtien gave incorrect value", expectedTotalProtien, actualTotalProtien,DELTA);
    	assertEquals("Constructor or Getter for TotalOther gave incorrect value", expectedTotalOther, actualTotalOther,DELTA);
		assertEquals("Constructor or Getter for TotalCalories gave incorrect value", expectedTotalCal, actualTotalCal,DELTA);
		assertEquals("Constructor or Getter for actualWG gave incorrect value", expectedWG, actualWG, DELTA);
    	assertEquals("Constructor or Getter for actualFV gave incorrect value", expectedFV, actualFV,DELTA);
    	assertEquals("Constructor or Getter for actualProtien gave incorrect value", expectedProtien, actualProtien,DELTA);
    	assertEquals("Constructor or Getter for actualOther gave incorrect value", expectedOther, actualOther,DELTA);
		assertEquals("Constructor or Getter for Calories gave incorrect value", expectedCal, actualCal,DELTA);
    }
	
    /* This test just makes sure that if setMydata() method 
	inside Hamper sets correct  SQLData object in myData field which can be initialized 
	we pass in testData declared at the top of the file as argument in setMyData() method and
	then return this value using getter, we check if testData and object reference 
	returned by getMyData() points to the same SQLData object*/
    @Test
    public void testHamperSetMyData() {
    	fillSQLData();
        Hamper myHamper = new Hamper(testData, 1, 0, 0, 0);
		myHamper.setMyData(testData);
		SQLData actualMyData = myHamper.getMyData();
        assertSame("Method setMyData did not return the expected result: ", testData, actualMyData);
    }
	
    /* This test just makes sure that if getMydata() method 
	inside Hamper can return SQLData object which can be initialized 
	from Hamper constructor, we make sure than testData declared at the
	top of the file and object reference returned by getMyData() points to the same SQLData object*/
    @Test
    public void testHamperGetMyData() {
    	fillSQLData();	
		 Hamper myHamper = new Hamper(testData,1,0,1,0);
		 SQLData actualMyData = myHamper.getMyData();		
        assertSame("Method getMyData did not return the expected result: ", testData, actualMyData);
    }
	
	/* This test checks if getValid() method returns default value false */
    @Test 
    public void testHamperSetValid(){
    	fillSQLData();
		Hamper myHamper = new Hamper(testData, 1, 0, 0, 0);
		myHamper.setValid(true);
		boolean expected = true;
       assertEquals("Method setValid did not return the expected result: ", expected, myHamper.getValid());	
	}
	/* This test checks if the setValid(0) changes the boolean value of the field valid to true*/
	@Test 
    public void testHamperGetValid(){
		fillSQLData();
        Hamper myHamper = new Hamper(testData, 1, 0, 0, 0);
		boolean expected = false;
        assertEquals("Method getValid did not return the expected result: ", expected, myHamper.getValid());	
	} 
	
	/* This test makes sure that valid field of the class Hamper is false when we fill the Hamper 
	but there are insufficient fooditems inside the inventory, then method fillHamper will pass in
	boolean value false to the field valid, so when we call getValid(), it should return false*/
	@Test
	public void testHamperGetValidWhenInsufficientInventoryItems(){
		SQLData testData = new SQLData("ansh","aleks","jassi");
	   ClientType male = new ClientType(0, "male", 20, 30, 40, 10, 10);
	   ClientType female = new ClientType(1, "female", 20, 30, 40, 10, 50);
	   ClientType childover8 = new ClientType(2, "childover8", 20, 30, 40, 10, 30);
	   ClientType childunder8 = new ClientType(3, "childunder8", 20, 30, 40, 10, 15);
	   testData.getClients().add(male);
	   testData.getClients().add(female);
	   testData.getClients().add(childover8);
	   testData.getClients().add(childunder8);

	   FoodItem pasta = new FoodItem(1,"Pasta",  80, 10, 0, 10, 75);
	   FoodItem beef = new FoodItem(2,"Beef", 10, 0, 80, 10, 100);
	   testData.getUpdatedInventory().getInventoryItems().add(pasta);
	   testData.getUpdatedInventory().getInventoryItems().add(beef);

	    Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		Hamper myHamper = myFamily.getHamper();
		myHamper.fillHamper();
		
        assertEquals("Method getValid did not return the expected false when inventory had insufficient fooditems: ", false, myHamper.getValid());		
	}
	
   /* This test makes sure that valid field of the class Hamper is true when we fill the Hamper 
	and there are sufficient foodItems inside the inventory, then method fillHamper will pass in
	boolean value true to the field valid, so when we call getValid(), it should return true*/
	@Test
	public void testHamperGetValidWhenSufficientInventoryItems(){
		fillSQLData();
	    Order myOrder = new Order();
		myOrder.addFamily(0,0,1,0);
		Family myFamily = myOrder.getFamily(0);
		 myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		 Hamper myHamper = myFamily.getHamper();
		 myHamper.fillHamper();
        assertEquals("Method getValid did not return the expected true when inventory had insufficient fooditems: ", true, myHamper.getValid());		
	}	
		
	/* This test checks for the best possible combo of foodItems for the hamper such that it wastes ,minimum food*/
	@Test
	public void testHamperfillHamperBestComboWhenSufficientFoodInInventory(){
		fillSQLData();
	    Order myOrder = new Order();
		myOrder.addFamily(0,0,0,1);
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		Hamper myHamper = myFamily.getHamper();
		myHamper.fillHamper();
		for(int i= 0;i<myHamper.getHamperItems().size();i++) {
			System.out.println(myHamper.getHamperItems().get(i).getName());
		}
		
		
		ArrayList<FoodItem> actualItems = myHamper.getHamperItems();
		ArrayList<FoodItem> expectedItems = new ArrayList<>();
		expectedItems.add(tomatoSauce);
		expectedItems.add(beef);
		expectedItems.add(apples);
		for(int i= 0;i<expectedItems.size();i++) {
			assertTrue("Fillhamper did not give the best possible hamper combo",actualItems.get(i).getName().equals(expectedItems.get(i).getName()));
		}
        
	}	
	
	
	/* If the valid field of the hamper is true, we expect that updatedInventory field of the SQLData class needs to be updated.
	which means that FoodItems which are inserted inside arraylist of Hamper should no longer be part of UpdatedInventory, since 
	we are going to be consistent with indices of the ArrayList in Inventory and ItemID of every FoodItem i.e if the FoodItem has ItemID 7,
	then it will be 6th index inside the ArrayList of FoodItems inside Inventory,so if we delete an item, we set that index of the arraylist to be null,
	so after we update Inventory, UpdatedInventory field will have ArrayList of FoodItems and all the foodItems inside hamper with their respected
	ItemIDs will be null inside Inventory*/
    @Test
    public void testHamperUpdateInventoryWhenValidIsTrue(){
    	fillSQLData();
	    Order myOrder = new Order();
		myOrder.addFamily(0,0,1,0);
		Family myFamily = myOrder.getFamily(0);
         myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());		
         Hamper myHamper = myFamily.getHamper();
         myHamper.fillHamper();
		myHamper.updateInventory();
		ArrayList<FoodItem> myItemsInHamper = myHamper.getHamperItems();
		Inventory afterUpdatingInventory = testData.getUpdatedInventory();
		for(int i = 0; i<myItemsInHamper.size();i++){
			assertTrue("This item was not replaced by dummy FoodItem inside Inventory",afterUpdatingInventory.getInventoryItems().get(myItemsInHamper.get(i).getItemID()-1).getName().equals(""));
		}
		// more code trying to fill items in hamper with inventory, if any item in hamper is found in inventory, test fails
	}
	
	/* If the valid is false, no updation in inventory should take place, so this test is just making sure if 
	inventory as same number of foodItems as it had before creating Hamper abd af6ter creating hamper*/
    @Test
    public void testHamperUpdateInventoryWhenValidIsFalse(){
		
    	SQLData testData = new SQLData("ansh","aleks","jassi");
	    ClientType male = new ClientType(0, "male", 20, 30, 40, 10, 10);
	    ClientType female = new ClientType(1, "female", 20, 30, 40, 10, 50);
	    ClientType childover8 = new ClientType(2, "childover8", 20, 30, 40, 10, 30);
	    ClientType childunder8 = new ClientType(3, "childunder8", 20, 30, 40, 10, 15);
	    testData.getClients().add(male);
	    testData.getClients().add(female);
	    testData.getClients().add(childover8);
	    testData.getClients().add(childunder8);

	    FoodItem pasta = new FoodItem(1,"Pasta",  80, 10, 0, 10, 75);
	    FoodItem beef = new FoodItem(2,"Beef",  10, 0, 80, 10, 100);
		testData.getUpdatedInventory().getInventoryItems().add(pasta);
		testData.getUpdatedInventory().getInventoryItems().add(beef);
	    Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		Inventory beforeUpdate = testData.getUpdatedInventory();			
         myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());		
         Hamper myHamper = myFamily.getHamper();
         myHamper.fillHamper();
		myHamper.updateInventory();
	    Inventory afterUpdate = testData.getUpdatedInventory();
		for(int i = 0; i<beforeUpdate.getInventoryItems().size();i++){
			assertSame("This item did not have same reference as before after calling update inventory when valid was false",beforeUpdate.getInventoryItems().get(i),afterUpdate.getInventoryItems().get(i));
		}
	}
	
	/* This is the test for food items in which we check the amount of grain, fv, protein, other, calories content and item id and compare
	the expected values of these to the actual values.*/
	@Test 
	public void testFoodItemGetters(){
		fillSQLData();
		Inventory myInventory = testData.getUpdatedInventory();
		FoodItem myFoodItem = myInventory.getInventoryItems().get(0);

        String expectedName = "Tomato Sauce";
        double expectedgrainContent = 0;
		double expectedfvContent = 80;
		double expectedproteinContent =10;
    	double expectedOther =  10;
    	double expectedCalories = 50;
    	double expectedITEMID = 1;
		
		String actualName = myFoodItem.getName();
		double actualgrainContent = myFoodItem.getGrainContent();
		double actualfvContent = myFoodItem.getFVContent();
		double actualproteinContent = myFoodItem.getProContent();
		double actualOther = myFoodItem.getOtherContent();
		double actualCalories = myFoodItem.getCalories();
		double actualITEMID = myFoodItem.getItemID();
		
   	    assertEquals(" getName gave incorrect value", expectedName, actualName);
    	assertEquals(" getGrainContent gave incorrect value", expectedgrainContent, actualgrainContent,DELTA);
    	assertEquals(" getFVContent gave incorrect value", expectedfvContent, actualfvContent,DELTA);
    	assertEquals(" getProContent gave incorrect value", expectedproteinContent, actualproteinContent,DELTA);
		assertEquals(" getOtherContent gave incorrect value", expectedOther, actualOther,DELTA);
        assertEquals(" getCalories gave incorrect value", expectedCalories, actualCalories,DELTA);
        assertEquals(" getItemID gave incorrect value", expectedITEMID, actualITEMID,DELTA);		
	}	

	/* This test has hard coded values of nutrients of male, female and children both above and under 8.
	these are the nutrients of all the things they have to have in the hamper. This checks what all our clients need to have as the nutrients. */
	
	@Test
	public void testClientType(){
		
		
		double expectedclientID = 0;
		double expectedwholeGrains = 0.2*100;
		double expectedfruitVeggies = 0.3*100;
		double expectedProtein =0.4*100;
		double expectedOther = 0.1*100;
		double expectedCalories = 100;
		
		String expectedClient = "male";
		
		String actualClient = male.getClient();
		double actualclientID = male.getClientID();
		double actualwholeGrains = male.getWholeGrains();
		double actualfruitVeggies = male.getFruitVeggies();
		double actualProtein = male.getProtein();
		double actualOther = male.getOther();
		double actualCalories = male.getCalories();
		
		assertEquals(" getClient gave incorrect value", expectedClient, actualClient);
    	assertEquals(" getWholeGrains gave incorrect value", expectedwholeGrains, actualwholeGrains,DELTA);
    	assertEquals(" getFruitsVeggies gave incorrect value", expectedfruitVeggies, actualfruitVeggies,DELTA);
    	assertEquals(" getProtein gave incorrect value", expectedProtein, actualProtein,DELTA);
		assertEquals(" getOther gave incorrect value", expectedOther, actualOther,DELTA);
        assertEquals(" getCalories gave incorrect value", expectedCalories, actualCalories,DELTA);
        assertEquals(" getClientID gave incorrect value", expectedclientID, actualclientID,DELTA);		
	}
	
	//SQLData class tests for methods which does not have any connection with SQLData base
	
	/*
	 * This tests check if the getters of SQL data does not return null after we add add a client object to clienType arraylist
	 * , or when we have Inventory created, the functionality for particular getters for FoodItem and ClientType are tested
	 * in different junit tests   
	 */
	@Test
	public void testGettersForSQLData() {
			
			SQLData testData = new SQLData("alexander","ansh","jaskaran");
			ArrayList<ClientType> clients = testData.getClients();
			ClientType male = new ClientType(0, "male", 20, 30, 40, 10, 100);
			FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce",0, 80, 10, 10, 50);
			clients.add(male);
			testData.getOriginalInventory().getInventoryItems().add(tomatoSauce);
			testData.getUpdatedInventory().getInventoryItems().add(tomatoSauce);
			assertNotNull("getOriginalInventory returned null, expected not null", testData.getOriginalInventory());
			assertNotNull("getUpdatedInventory returned null, expected not null", testData.getUpdatedInventory());
			assertNotNull("getClients returned null, expected not null", testData.getClients());
	}
	
	
	/*
	 * test for setters in SQLData checks if the refrence of the object passed in setter is same 
	 * as the refrence of the object returned by their respective getters
	 */
	@Test
	public void testSettersForSQLData() {
			
			SQLData testData = new SQLData("alexander","ansh","jaskaran");
			ArrayList<ClientType> clients = new ArrayList<>();
			Inventory inventory = new Inventory();
			testData.setClients(clients);
			testData.setOriginalInventory(inventory);
			testData.setUpdatedInventory(inventory);
			assertSame("setOriginalInventory did not set properly",inventory, testData.getOriginalInventory());
			assertSame("setUpdatedInventory did not set properly",inventory, testData.getUpdatedInventory());
			assertSame("setClients did not set properly", clients,testData.getClients());
	}
	
	/*
	 * This test checks that whether original inventory is replaced by deep copy of updated inventory
	 * this method will only be called when order is successfully made in the actual program
	 */
	@Test
	public void testForRefreshOriginalInventoryWhenOrderSuccess() {
		fillSQLData();
		try {
			testData.refreshOriginalInventoryWhenOrderSuccess();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		Inventory original = testData.getOriginalInventory();
		Inventory updated = testData.getUpdatedInventory();
        assertFalse("Did not update originalInventory with deep copy of updatedInventory inventory refrences were same",original == updated);
        assertFalse("Did not update originalInventory with deep copy of updatedInventory because array list refrences were same",original.getInventoryItems() == updated.getInventoryItems());
        assertFalse("Did not update originalInventory with deep copy of updatedInventory because FoodItem at first index was not deep copy",original.getInventoryItems().get(0) == updated.getInventoryItems().get(0));            
	}
	/*
	 * This test checks that whether updated inventory is replaced by deep copy of original inventory i.e before update
	 * this method will only be called when order is unsuccessfull in the actual program
	 */	
	@Test
	public void testForRefreshUpdatedInventoryWhenOrderFails() {
		FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce",0, 80, 10, 10, 50);
	    FoodItem pasta = new FoodItem( 2,"Pasta", 80, 10, 0, 10, 75);
		FoodItem beef = new FoodItem( 3,"Beef", 10, 0, 80, 10, 100);
	    FoodItem apples = new FoodItem( 4,"Apples", 30, 50, 10, 10, 50);
		FoodItem candy = new FoodItem(5,"Candy",  10, 0, 0, 90, 150);
		SQLData testData = new SQLData("ansh","aleks","jassi");
		testData.getOriginalInventory().getInventoryItems().add(tomatoSauce);
		testData.getOriginalInventory().getInventoryItems().add(pasta);
		testData.getOriginalInventory().getInventoryItems().add(beef);
		testData.getOriginalInventory().getInventoryItems().add(apples);
		testData.getOriginalInventory().getInventoryItems().add(candy);
		try {
			testData.refreshUpdatedInventoryWhenOrderFails();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		Inventory original = testData.getOriginalInventory();
		Inventory updated = testData.getUpdatedInventory();
        assertFalse("Did not update updatedInventory with deep copy of originalInventory as inventory refrences were same",original == updated);
        assertFalse("Did not update updatedInventory with deep copy of originalInventory because array list refrences were same",original.getInventoryItems() == updated.getInventoryItems());
        assertFalse("Did not update updatedInventory with deep copy of originalInventory because FoodItem at first index was not deep copy.",original.getInventoryItems().get(0) == updated.getInventoryItems().get(0));            
	}
}
