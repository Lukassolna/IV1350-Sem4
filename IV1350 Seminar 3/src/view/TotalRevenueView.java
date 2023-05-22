package view;

import model.CashPayment;
import model.SaleObserver;

public class TotalRevenueView implements SaleObserver {
	private double totalRevenue;
	
	
	 @Override
	    public void newPayment(CashPayment amount) {
	        addNewPayment(amount);
	        printCurrentState();
	    }
	 
	  /**
	     * adds a new payment by increasing the total
	     * 
	     * @param amount the amount paid for the sale
	     */
	    private void addNewPayment(CashPayment amount) {
	        totalRevenue = totalRevenue+amount.getAmount();
	    }
	 
	  /**
	     * Prints the total revenue since the program started.
	     */
	    private void printCurrentState() {
	       
	        System.out.print("The total revenue for all completed sales is: ");
	        System.out.println(totalRevenue);
	       
	    }


}
