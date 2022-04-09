package edu.ucalgary.ensf409;

import java.awt.EventQueue;

public class HamperOrderingSystem {
    public static void main(String[] args) {
        SQLData myData = new SQLData("jdbc:mysql://localhost/FOOD_INVENTORY","student","ensf");

        EventQueue.invokeLater(() -> {
            try {
				new GUIOrder(myData).setVisible(true);
			} catch (CloneNotSupportedException e) {
				
				e.printStackTrace();
			}        
        });
    }
}
