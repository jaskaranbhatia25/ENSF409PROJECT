@author Aleksander Rudolf, Ansh Singh, Jaskaran Bhatia
@version 2.0
@since 1.0 - Mar. 28/2022
*/

package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUIOrder extends JFrame implements ActionListener, MouseListener {
	
	private int numOfOrders = 0;

	private Order order;
	private SQLData myData;
	
	private JButton submitInfo;
	private JButton addFamily;
	private String male;
	private String female;
	private String o8;
	private String u8;
	
    private int numOfMales = -1;
	private int numOfFemales =-1;
	private int over8=-1;
	private int under8=-1;
	
    private JLabel instructions;
    private JLabel maleLabel;
    private JLabel femaleLabel;
    private JLabel o8Label;
    private JLabel u8Label;
    
    private JTextField maleInput;
    private JTextField femaleInput;
    private JTextField o8Input;
    private JTextField u8Input;
    
    /**
     * Constructor
     * @param myData
     * @throws CloneNotSupportedException
     */
    public GUIOrder(SQLData myData) throws CloneNotSupportedException {
        super("Hamper Ordering System");
		this.order = new Order(); 
		this.myData = myData;
		this.myData.connectDatabase();
		
		JOptionPane.showMessageDialog(this,"Thank you for using our hamper ordering system. Please hit ok to start a new order.");
		setupGUI();
        setSize(350,300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    /**
     * Writes the formatted order summary to a file.
     * @param sentence
     * @param numOfORders
     */
    private void output(String sentence, int numOfOrders) {
		try {
			FileWriter writer = new FileWriter("Order_Summary_"+numOfOrders+".txt", true);
			writer.write(sentence + "\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /*
     * Creates the GUI Frames/Buttons
     */
    public void setupGUI() {
        instructions = new JLabel("Please enter family information.");
        maleLabel = new JLabel("Number of Males:");
        femaleLabel = new JLabel("Number of Females:");
        o8Label = new JLabel("Number of children over 8:");
        u8Label = new JLabel("Number of children under 8:");
        
        maleInput = new JTextField("e.g. 1", 15);
        femaleInput = new JTextField("e.g. 1", 15);
        o8Input = new JTextField("e.g. 1", 15);
        u8Input = new JTextField("e.g. 1", 15);    
        
        maleInput.addMouseListener(this);
        femaleInput.addMouseListener(this);
        o8Input.addMouseListener(this);
        u8Input.addMouseListener(this);
        
        this.submitInfo = new JButton("Submit order");
        submitInfo.addActionListener(this);
		
        this.addFamily = new JButton("Save family information");
        addFamily.addActionListener(this); 
		
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(instructions);
        clientPanel.add(maleLabel);
        clientPanel.add(maleInput);
        clientPanel.add(femaleLabel);
        clientPanel.add(femaleInput);
        clientPanel.add(o8Label);
        clientPanel.add(o8Input);
        clientPanel.add(u8Label);
        clientPanel.add(u8Input);
        submitPanel.add(submitInfo);
        submitPanel.add(addFamily);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    
    /*
     * Event handler to process clicking a button.
     */
    public void actionPerformed(ActionEvent event) {
       if(event.getSource()== addFamily) {
    	   male = maleInput.getText();
    	   female = femaleInput.getText();
    	   o8 = o8Input.getText();
    	   u8 = u8Input.getText();
    	   if(validateInput()){
    		   order.addFamily(numOfMales,numOfFemales,over8,under8);
    		   JOptionPane.showMessageDialog(this, "Family added successfully.");
    		   maleInput.setText("");
    		   femaleInput.setText("");
    		   o8Input.setText("");
    		   u8Input.setText("");
    	   }
       }
       if(event.getSource()== submitInfo) {
    	   ArrayList<Family> families = order.getFamilies();
    	   
    	   for(int i=0;i<families.size();i++) {
    		   Family family = families.get(i);
    		   family.createHamper(this.myData,  family.getNumOfMales(), family.getNumOfFemales(), family.getNumOfChildrenOver8(), family.getNumOfChildrenUnder8());
    		   Hamper hamper = family.getHamper();
    		   hamper.fillHamper();
    		   hamper.updateInventory();
    		   System.out.println(hamper.getTotalCalories());
    	   }
    	   
    	   order.validateOrder();
    	   if(order.getValidOrder()) {
    		   myData.updateDatabase();
    		   numOfOrders++;
    		   output(order.formatSummary(),numOfOrders);
    		   System.out.println(order.formatSummary());
    		   try {
    			   myData.refreshOriginalInventoryWhenOrderSuccess();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    		   this.order = new Order();
               maleInput.setText("");
               femaleInput.setText("");
               o8Input.setText("");
               u8Input.setText("");
    		   JOptionPane.showMessageDialog(this, "Your order is placed and all the hampers are successfully created.\nPlease hit ok to start another order or press cross button at the top right corner");
    	   }
    	   else {
    		   if(order.getFamilies().size()==0) {
    			   JOptionPane.showMessageDialog(this, "An order must have atleast 1 family.");
    		   }
    		   else {
    	    		try {
    					myData.refreshUpdatedInventoryWhenOrderFails();
    				} catch (CloneNotSupportedException e) {
    					e.printStackTrace();
    				}
    	    		this.order = new Order();
    	            maleInput.setText("");
    	            femaleInput.setText("");
    	            o8Input.setText("");
    	            u8Input.setText("");
    	    		JOptionPane.showMessageDialog(this, "Sorry! We connot process your order because of insufficient food items in the inventory.\nPlease hit ok to start another order or close the program");
    	    	   } 
    		   }
       } 
    }
    
    /*
     * Event handler to process clicking into a text box of the GUI with a mouse.
     */
    public void mouseClicked(MouseEvent event) {
        
        if(event.getSource().equals(maleInput))
            maleInput.setText("");

        if(event.getSource().equals(femaleInput))
            femaleInput.setText("");

        if(event.getSource().equals(o8Input))
            o8Input.setText("");

        if(event.getSource().equals(u8Input))
            u8Input.setText("");     
    }
    
    /*
     * Event handlers to process different mouse actions.
     */
    public void mouseEntered(MouseEvent event) {}

    public void mouseExited(MouseEvent event) {}

    public void mousePressed(MouseEvent event) {}

    public void mouseReleased(MouseEvent event) {}
    
    /**
     * Validates the input being entered into the Hamper Order Form via GUI to check for boundary conditions of the input.
     * @return boolean
     */
    private boolean validateInput() {
        
        boolean allInputValid = true;
		
		try{
			numOfMales = Integer.parseInt(male);
	        if( numOfMales < 0 || numOfMales > 10) {
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of males is out of range, please enter an integer between  and 10.");
	        }

		} catch(Exception e) {
			allInputValid = false;
			if(male.equals(""))
				JOptionPane.showMessageDialog(this, "Number of males field cannot be left empty.");
			else
               JOptionPane.showMessageDialog(this, male + " is invalid value for the number of males.");
        }
		
		try {
			numOfFemales = Integer.parseInt(female);
	        if(numOfFemales < 0 || numOfFemales > 10) {
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of females is out of range, please enter an integer between 0 and 10.");
	        }

		} catch(Exception e) {
			allInputValid = false;
			if(female.equals(""))
				JOptionPane.showMessageDialog(this, "Number of females field cannot be left empty.");
			else
                JOptionPane.showMessageDialog(this, female + " is invalid value for the number of females.");
        }
		try {
			over8 = Integer.parseInt(o8);
	        if( over8 < 0 || over8 > 10) {
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this,"The value entered for  the number of children over eight is out of range, please enter an integer between 0 and 10.");
	        }

		} catch(Exception e) {
			allInputValid = false;
			if(o8.equals(""))
				JOptionPane.showMessageDialog(this, "Number of children over 8 field cannot be left empty.");
			else
                JOptionPane.showMessageDialog(this, o8 + " is invalid value for the number of children over eight.");
        }
		try {
			under8 = Integer.parseInt(u8);

	        if(under8 < 0 || under8 > 10) {
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of children under eight is out of range, please enter an integer between 0 and 10.");
	        }
		} catch(Exception e) {
			allInputValid = false;
			if(u8.equals(""))
				JOptionPane.showMessageDialog(this, "Number of children under 8 field cannot be left empty.");
			else
                JOptionPane.showMessageDialog(this, u8 + " is invalid value for the number of children under eight.");
        }		
        if(numOfMales==0 && numOfFemales==0 && over8==0 && under8==0) {
        	allInputValid = false;
        	JOptionPane.showMessageDialog(this, "You cannot create a family with zero members.");
        }
        return allInputValid;
    }    
}
