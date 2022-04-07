/*
Copyright Ann Barcomb and Emily Marasco, 2021
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import javax.swing.*;


import java.awt.event.*;
import java.util.ArrayList;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUIOrder extends JFrame implements ActionListener, MouseListener{

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
    
    public GUIOrder(Order order, SQLData myData) throws CloneNotSupportedException{
        super("Hamper Ordering System");
		this.order = order; 
		this.myData = myData;
		this.myData.connectDatabase();
		
        setupGUI();
        setSize(350,300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    public void setupGUI(){
        
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
        
        this.submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);
		
        this.addFamily = new JButton("Add next family");
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
    
    public void actionPerformed(ActionEvent event){
		
      	
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
    		   //hamper.fillHamper();
    		   //hamper.updateInventory();
    	   }
    	   order.validateOrder();
    	   if(order.getValidOrder()) {
    		  // myData.updateDatabase();
    		   myData.close();
    		   //order.formatSummary();
    		   JOptionPane.showMessageDialog(this, "Your order is placed and all the hampers are successfully created.");
    		   System.exit(0);
    	   }
    	   else
    		   myData.close();
    		   JOptionPane.showMessageDialog(this, "Sorry! We connot process your order because of insufficient food items in the inventory.");
    		   System.exit(0);
    		   

       }
       
    }
    
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(maleInput))
            maleInput.setText("");

        if(event.getSource().equals(femaleInput))
            femaleInput.setText("");

        if(event.getSource().equals(o8Input))
            o8Input.setText("");

        if(event.getSource().equals(u8Input))
            u8Input.setText("");
                
    }
    
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
      
    
    private boolean validateInput(){
        
        boolean allInputValid = true;
		
		try{
			numOfMales = Integer.parseInt(male);
	        if( numOfMales < 0 || numOfMales > 10){
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of males is out of range, please enter an integer between 1 and 10.");
	        }

		}catch(Exception e){
			allInputValid = false;
            JOptionPane.showMessageDialog(this, male + " is invalid value for the number of males.");
        }
		
		try{
		
			numOfFemales = Integer.parseInt(female);
	        if( numOfFemales < 0 || numOfFemales > 10){
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of females is out of range, please enter an integer between 1 and 10.");
	        }

		}catch(Exception e){
			allInputValid = false;
            JOptionPane.showMessageDialog(this, female + " is invalid value for the number of females.");
        }
		try{

			over8 = Integer.parseInt(o8);
	        if( over8 < 0 || over8 > 10){
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this,"The value entered for  the number of children over eight is out of range, please enter an integer between 1 and 10.");
	        }

		}catch(Exception e){
			allInputValid = false;
            JOptionPane.showMessageDialog(this, o8 + " is invalid value for the number of children over eight.");
        }
		try{
			under8 = Integer.parseInt(u8);

	        if(under8 < 0 || under8 > 10){
	            allInputValid = false;
	            JOptionPane.showMessageDialog(this, "The value entered for the number of children under eight is out of range, please enter an integer between 1 and 10.");
	        }
		}catch(Exception e){
			allInputValid = false;
            JOptionPane.showMessageDialog(this, u8 + " is invalid integer for the number of children under eight.");
        }		
        

        return allInputValid;
        
    }

        
} 