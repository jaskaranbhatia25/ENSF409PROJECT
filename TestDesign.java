/**
@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 1.3
@since 1.0 - Mar. 28/2022
*/

/*
 * This is a class designed to test the functionality of Group #81's ENSF409 Hamper Project.
 */

package edu.ucalgary.ensf409;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.*;

public class TestDesign {

	{	SQLData testData = new SQLData();
	
	ClientType male = new ClientType(0, "male", 20, 30, 40, 10, 100);
	ClientType female = new ClientType(1, "female", 20, 30, 40, 10, 50);
	ClientType childover8 = new ClientType(2, "childover8", 20, 30, 40, 10, 30);
	ClientType childunder8 = new ClientType(3, "childunder8", 20, 30, 40, 10, 15);
	ArrayList<ClientType> clients = testData.getClients();
	clients.add(male);
	clients.add(female);
	clients.add(childover8);
	clients.add(childunder8);
	
	FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce",80, 10, 10, 50, 0);
	FoodItem pasta = new FoodItem( 2,"Pasta", 80, 10, 0, 10, 75);
	FoodItem beef = new FoodItem( 3,"Beef", 10, 0, 80, 10, 100);
	FoodItem apples = new FoodItem( 4,"Apples", 30, 50, 10, 10, 50);
	FoodItem candy = new FoodItem(5,"Candy",  10, 0, 0, 90, 150);
	testData.getUpdatedInventory().getInventoryItems().add(tomatoSauce);
	testData.getUpdatedInventory().getInventoryItems().add(pasta);
	testData.getUpdatedInventory().getInventoryItems().add(apples);
	testData.getUpdatedInventory().getInventoryItems().add(candy);
	testData.getUpdatedInventory().getInventoryItems().add(beef);
}
	/* This test checks if the addFamily() method throws an IllegalArgumentException if an
	illegal character is used in attempting to add a family object to the Families ArrayList in the Order class.*/
    @Test
    public void testAddFamilyIllegalArgumentException() {
    	public Order testOrder = new Order();
        boolean testResult = false;
        try {
            testOrder.addFamilies(a, 0, 0, 0);
        }
        catch (IllegalArgumentException e) {
            testResult = true;
        }
        catch (Exception e) { }
        assertTrue("Invalid method argument did not not throw an IllegalArgumentException", testResult);
    }
	
	/* This test checks to see that the setValidOrder() method inside of the Order class properly sets the
	Order validity to true.*/
    @Test
	public void testOrderSetValidOrder(){
		Order testOrder = new Order();
		testOrder.setValidOrder(true);
		boolean expected = true;
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
		Order testOrder = new Order();
		testOrder.addFamily(1,1,1,1);
		Family myFamily = testOrder.getFamily(0);
		 myFamily.createHamper(testData, 1, 1, 1, 1);
		 Hamper myHamper = myFamily.getHamper();
		assertEquals("Method ValidateOrder did not set validOrder true when the order was valid: ", true, testOrder.getValidOrder());
		
	}	
	
	/* This test checks to see if the getValidOrder() method returns a boolean value of false, when there
	atleast one Family with a valid Hamper, and atleast one Family with an invalid Hamper in the order.*/
	@Test
	public void testOrderValidateOrderWhenOrderIsInvalid(){
		Order testOrder = new Order();
		testOrder.addFamily(1,1,1,1);
		testOrder.addFamily(5,5,5,1);
		Family myFamilyOne = testOrder.getFamily(0);
		Family myFamilyTwo = testOrder.getFamily(1);
		myFamilyOne.createHamper(testData, myFamilyOne.getNumOfMales() , myFamilyOne.getNumOfFemales(), myFamilyOne.getNumOfChildrenOver8(), myFamilyOne.getNumOfChildrenUnder8());
	    myFamilyTwo.createHamper(testData, myFamilyTwo.getNumOfMales() , myFamilyTwo.getNumOfFemales(), myFamilyTwo.getNumOfChildrenOver8(), myFamilyTwo.getNumOfChildrenUnder8());
		Hamper myHamperOne = myFamilyOne.getHamper();
		Hamper myHamperTwo = myFamilyTwo.getHamper();
		myHamperOne.fillHamper();
        myHamperOne.updateInventory();
        myHamperTwo.fillHamper();
        myHamperTwo.updateInventory();		
		assertEquals("Method ValidateOrder did not set validOrder false when the order was invalid: ", false, testOrder.getValidOrder());
		
	}	

	/* This test checks to see if the getFamilies() method of the Order object returns a Family ArrayList
	with one Family object in it.*/
    @Test
    public void testOrderGetFamily() {
    	 Order testOrder = new Order();
    	testOrder.addFamily(1,0,0,0);
    	assertNotNull("getFamilies returned null, expected not null", testOrder.getFamilies());
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
    	Family testFamily = new Family(1,0,0,0);

    	testFamily.createHamper(testData, testFamily.getNumOfMales() , testFamily.getNumOfFemales(), testFamily.getNumOfChildrenOver8(), testFamily.getNumOfChildrenUnder8());
    	Hamper testHamper = testFamily.getHamper();
    	assertNotNull("createHamper returned null, expected not null", testHamper);
    }
    
	/*This test checks to see if the getHamper() method of the Family object returns the Hamper object
	for the Family object it was created for.*/
    @Test
    public void testFamilyGetHamper() {
    	Order testOrder = new Order();
    	testOrder.addFamily(1,0,0,0);
    	Family testFamily = testOrder.getFamily(0);
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
    	String expectedOutput = "Hamper Order Form\n\nName: Doe\nDate: April 12/2022\n\nOriginal Request\nHamper 1: 1 Adult Male\n\nHamper 1 Items:\n0  Tomato Sauce\n1  Pasta\n2  Beef";
    	Order testOrder = new Order();
    	testOrder.addFamily(1,0,0,0);
    	Family testFamily = testOrder.getFamily(0);
    	testFamily.createHamper(testData, testFamily.getNumOfMales() , testFamily.getNumOfFemales(), testFamily.getNumOfChildrenOver8(), testFamily.getNumOfChildrenUnder8());
    	String actualOutput = Order.formatSummary(1);
    	assertEquals("formatSummaray did not match the expected output", expectedOutput, actualOutput);
    }
    
	/* This test checks to see if the getItems() method of the Inventory class successfully returns
	a FoodItem ArrayList.*/
    @Test
    public void testInventoryGetItems() {

    	ArrayList<FoodItem> testInventory = testData.getUpdatedInventory().getInventoryItems();
    	assertNotNull("getItems returned null, expected not null", testInventory);
    }

	/* This test checks to see if the removeItem() method of the Inventory class removes
	a specified FoodItem from the FoodItem ArrayList of the Inventory class.*/
    @Test
    public void testInventoryRemoveItem() {

    	SQLData testData = new SQLData();
    	FoodItem tomatoSauce = new FoodItem(1,"Tomato Sauce", 0, 80, 10, 10, 50);
    	testData.getUpdatedInventory().getInventoryItems().add(tomatoSauce);
    	
    	testData.getUpdatedInventory().removeItem(0);
    	ArrayList<FoodItem> testInventory = testData.getUpdatedInventory().getInventoryItems();
    	FoodItem testFoodItem = testInventory.get(0);
    	assertNull("Specified food item was not removed from the inventory, expected null", testFoodItem);
    }
    
	/* This test checks to see if the getItem() method of the Inventory class returns a specified FoodItem
	of the FoodItem ArrayList of the inventory class.*/
    @Test
    public void testInventoryGetItem() {
    	
    	SQLData testData = new SQLData();
    	
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
		int expectedITEMID = 0;
		
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
		Order myOrder = new Order();
		myOrder.addFamily(1,3,1,1);
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
		
	
    	int expectedTotalWG = (int) ((0.2*100+2*0.2*50+2*0.2*30+0.20*15)*7);
    	int expectedTotalFV = (int) ((0.3*100+2*0.3*50+2*0.3*30+0.3*15)*7);
    	int expectedTotalProtien = (int) ((0.4*100+2*0.4*50+2*0.4*30+0.40*15)*7);
    	int expectedTotalOther = (int) ((0.1*100+2*0.1*50+2*0.1*30+0.1*15)*7);
		int expectedTotalCal =(100+50+30+15)*7;
		
		Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());
		Hamper myHamper = myFamily.getHamper();
		int actualTotalWG = (int) myHamper.getTotalWholeGrains();
		int actualTotalFV = (int) myHamper.getTotalFV();
        int actualTotalProtien = (int) myHamper.getTotalProtein();
    	int actualTotalOther = (int) myHamper.getTotalOther();
		int actualTotalCal	= (int) myHamper.getTotalCalories();	

    	assertEquals("Constructor or Getter for TotalWG gave incorrect value", expectedTotalWG, actualTotalWG);
    	assertEquals("Constructor or Getter for TotalFV gave incorrect value", expectedTotalFV, actualTotalFV);
    	assertEquals("Constructor or Getter for TotalProtien gave incorrect value", expectedTotalProtien, actualTotalProtien);
    	assertEquals("Constructor or Getter for TotalOther gave incorrect value", expectedTotalOther, actualTotalOther);
		assertEquals("Constructor or Getter for TotalCAlories gave incorrect value", expectedTotalCal, actualTotalCal);
    }
	
    /* This test just makes sure that if setMydata() method 
	inside Hamper sets correct  SQLData object in myData field which can be initialized 
	we pass in testData declared at the top of the file as argument in setMyData() method and
	then return this value using getter, we check if testData and object reference 
	returned by getMyData() points to the same SQLData object*/
    @Test
    public void testHamperSetMyData() {
        Hamper myHamper = new Hamper(testData, 0, 0, 0, 0);
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
		Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		 myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());		
		 Hamper myHamper = myFamily.getHamper();
		 SQLData actualMyData = myHamper.getMyData();		
        assertSame("Method getMyData did not return the expected result: ", testData, actualMyData);
    }
	
	/* This test checks if getValid() method returns default value false */
    @Test 
    public void testHamperSetValid(){
		Hamper myHamper = new Hamper(testData, 1, 0, 0, 0);
		myHamper.setValid(true);
		boolean expected = true;
       assertEquals("Method setValid did not return the expected result: ", expected, myHamper.getValid());	
	}
	/* This test checks if the setValid(0) changes the boolean value of the field valid to true*/
	@Test 
    public void testHamperGetValid(){
        Hamper myHamper = new Hamper(testData, 1, 0, 0, 0);
		boolean expected = false;
        assertEquals("Method getValid did not return the expected result: ", expected, myHamper.getValid());	
	} 
	
	/* This test makes sure that valid field of the class Hamper is false when we fill the Hamper 
	but there are insufficient fooditems inside the inventory, then method fillHamper will pass in
	boolean value false to the field valid, so when we call getValid(), it should return false*/
	@Test
	public void testHamperGetValidWhenInsufficientInventoryItems(){
	   SQLData testData = new SQLData();
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
		
	    Order myOrder = new Order();
		myOrder.addFamily(1,1,1,1);
		Family myFamily = myOrder.getFamily(0);
		 myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		 Hamper myHamper = myFamily.getHamper();
		 myHamper.fillHamper();
        assertEquals("Method getValid did not return the expected true when inventory had insufficient fooditems: ", true, myHamper.getValid());		
	}	
		
	/* This test checks that weather FoodItems in the array has nutrional attributes greater than
    equal to the target values of nutrional needs, however this test does not account for
    how much food is wasted or if this is the best combination or not*/	 
	@Test
	public void testHamperfillHamperWhenSufficientFoodInInventory(){
	    Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		Hamper myHamper = myFamily.getHamper();
		myHamper.fillHamper();
		ArrayList<FoodItem> myItems = myHamper.getHamperItems();
		int wg =0;
		int fv = 0;
		int prt = 0;
		int othr = 0;
		int cal = 0;
		
		for(int i=0; i<myItems.size();i++){
			wg+=myItems.get(i).getGrainContent();
			fv+=myItems.get(i).getFVContent();
			prt+=myItems.get(i).getProContent();
			othr+=myItems.get(i).getOtherContent();
			cal+=myItems.get(i).getCalories();
	    }
        assertTrue( wg>=myHamper.getTotalWholeGrains() && fv>=myHamper.getTotalFV()	&& prt>=myHamper.getTotalProtein() && othr>=myHamper.getTotalOther() && cal>=myHamper.getTotalCalories()) ;
	}	
	
	/* This test is to check if the fill hamper sets the FoodItems Arraylist inside 
	the hamper to null when it cannot fill the hamper requirements and does not meet the target nutrional needs values*/
	@Test
	public void testHamperfillHamperWhenInsufficientFoodInInventory(){
		
	    SQLData testData = new SQLData();
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
		 myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());	
		 Hamper myHamper = myFamily.getHamper();
		 myHamper.fillHamper();
		ArrayList<FoodItem> myItems = myHamper.getHamperItems();
		assertNull("FoodItems was not null after we called fillHamper for insufficient fooditems in inventory .", myItems);
	}
	
	/* If the valid field of the hamper is true, we expect that updatedInventory field of the SQLData class needs to be updated.
	which means that FoodItems which are inserted inside arraylist of Hamper should no longer be part of UpdatedInventory, since 
	we are going to be consistent with indices of the ArrayList in Inventory and ItemID of every FoodItem i.e if the FoodItem has ItemID 7,
	then it will be 7th index inside the ArrayList of FoodItems inside Inventory,so if we delete an item, we set that index of the arraylist to be null,
	so after we update Inventory, UpdatedInventory field will have ArrayList of FoodItems and all the foodItems inside hamper with their respected
	ItemIDs will be null inside Inventory*/
    @Test
    public void testHamperUpdateInventoryWhenValidIsTrue(){
	    Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		Inventory beforeUpdate = testData.getUpdatedInventory();
         myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());		
         Hamper myHamper = myFamily.getHamper();
         myHamper.fillHamper();
		myHamper.updateInventory();
		ArrayList<FoodItem> myItemsInHamper = myHamper.getHamperItems();
		Inventory afterUpdatingInventory = testData.getUpdatedInventory();
		for(int i = 0; i<myItemsInHamper.size();i++){
			assertNull(afterUpdatingInventory.getInventoryItems().get(myItemsInHamper.get(i).getItemID()));
		}
		// more code trying to fill items in hamper with inventory, if any item in hamper is found in inventory, test fails
	}
	
	/* If the valid is false, no updation in inventory should take place, so this test is just making sure if 
	inventory as same number of foodItems as it had before creating Hamper abd af6ter creating hamper*/
    @Test
    public void testHamperUpdateInventoryWhenValidIsFalse(){
		
	    SQLData testData = new SQLData();
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
	    Order myOrder = new Order();
		myOrder.addFamily(1,2,2,1);
		Family myFamily = myOrder.getFamily(0);
		Inventory beforeUpdate = testData.getUpdatedInventory();			
         myFamily.createHamper(testData, myFamily.getNumOfMales() , myFamily.getNumOfFemales(), myFamily.getNumOfChildrenOver8(), myFamily.getNumOfChildrenUnder8());		
         Hamper myHamper = myFamily.getHamper();
         myHamper.fillHamper();
		myHamper.updateInventory();
	    Inventory afterUpdate = testData.getUpdatedInventory();
		assertEquals("updateInventory deleted FoodItems from inventory when the hamper was incomplete: ", beforeUpdate.getInventoryItems().size(), afterUpdate.getInventoryItems().size());
	}
	
	/* This is the test for food items in which we check the amount of grain, fv, protein, other, calories content and item id and compare
	the expected values of these to the actual values.*/
	@Test 
	public void testFoodItemGetters(){
		
		Inventory myInventory = testData.getUpdatedInventory();
		FoodItem myFoodItem = myInventory.getInventoryItems().get(0);
		
        String expectedName = "Tomato Sauce";
        double expectedgrainContent = 0;
		double expectedfvContent = 0.8*50;
		double expectedproteinContent =0.1*50);
    	double expectedOther =  0.1*50;
    	double expectedCalories = 50;
    	double expectedITEMID = 0;
		
		String actualName = myFoodItem.getName();
		double actualgrainContent = myFoodItem.getGrainContent();
		double actualfvContent = myFoodItem.getFVContent();
		double actualproteinContent = myFoodItem.getProContent();
		double actualOther = myFoodItem.getOtherContent();
		double actualCalories = myFoodItem.getCalories();
		double actualITEMID = myFoodItem.getItemID();
		
   	    assertEquals(" getName gave incorrect value", expectedName, actualName);
    	assertEquals(" getGrainContent gave incorrect value", expectedgrainContent, actualgrainContent);
    	assertEquals(" getFVContent gave incorrect value", expectedfvContent, actualfvContent);
    	assertEquals(" getProContent gave incorrect value", expectedproteinContent, actualproteinContent);
		assertEquals(" getOtherContent gave incorrect value", expectedOther, actualOther);
        assertEquals(" getCalories gave incorrect value", expectedCalories, actualCalories);
        assertEquals(" getItemID gave incorrect value", expectedITEMID, actualITEMID);		
	}	

	/* This test has hard coded values of nutrients of male, female and children both above and under 8.
	these are the nutrients of all the things they have to have in the hanper. This checks what all our clients need to have as the nutrients. */
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
		int actualclientID = male.getClientID();
		int actualwholeGrains = male.getWholeGrains();
		int actualfruitVeggies = male.getFruitVeggies();
		int actualProtein = male.getProtein();
		int actualOther = male.getOther();
		int actualCalories = male.getCalories();
		
		assertEquals(" getClient gave incorrect value", expectedClient, actualClient);
    	assertEquals(" getWholeGrains gave incorrect value", expectedwholeGrains, actualwholeGrains);
    	assertEquals(" getFruitsVeggies gave incorrect value", expectedfruitVeggies, actualfruitVeggies);
    	assertEquals(" getProtein gave incorrect value", expectedProtein, actualProtein);
		assertEquals(" getOther gave incorrect value", expectedOther, actualOther);
        assertEquals(" getCalories gave incorrect value", expectedCalories, actualCalories);
        assertEquals(" getClientID gave incorrect value", expectedclientID, actualclientID);		
	}
}
