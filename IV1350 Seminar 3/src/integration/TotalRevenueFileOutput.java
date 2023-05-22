package integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.CashPayment;
import model.SaleObserver;

public class TotalRevenueFileOutput implements SaleObserver {
	 private PrintWriter revenueFile;
	    
    private double totalRevenue;
  

    @Override
    public void newPayment(CashPayment amount) {
        addNewPayment(amount);
        printToFile();
    }

    /**
     * Adds a new payment by increasing the total revenue.
     * 
     * @param amount the amount paid for the sale
     */
    private void addNewPayment(CashPayment amount) {
        totalRevenue += amount.getAmount();
    }

    /**
     * Prints the total amount of revenue to a file.
     */
    private void printToFile() {
        try {
        	revenueFile = new PrintWriter(new FileWriter("totalRevenue.txt"), true);
           
           revenueFile.println("The total revenue is : " + totalRevenue);
            
        } catch (IOException e) {
            System.out.println("Failed to write total revenue to file: " + e.getMessage());
        }
    }
}



