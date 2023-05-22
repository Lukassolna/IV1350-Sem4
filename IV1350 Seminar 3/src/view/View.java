package view;

import java.io.IOException;
import java.util.Scanner;

import controller.Controller;
import integration.DatabaseException;
import integration.LogWriter;

import integration.NoItemFoundException;
import integration.TotalRevenueFileOutput;
import model.Item;

/**
 * View is a class that represents a user interface and contains hard coded
 * controller calls + printouts
 * 
 * 
 */

public class View {
	private Controller controller;
	private LogWriter logWriter;

	/**
	 * Constructs a new View object with the specified Controller.
	 * 
	 * @param controller the Controller object to use for handling user input and
	 *                   processing sales
	 */
	public View(Controller controller) {
		this.controller = controller;
		controller.addSaleObserver(new TotalRevenueView());
		controller.addSaleObserver(new TotalRevenueFileOutput());
		try {
			logWriter=new LogWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Starts a new sale by invoking the appropriate methods on the Controller
	 * object.
	 * 
	 * (scanner as input was added in order to be able test that multiple sales
	 * generated the correct output to observers. Could be locale inside start)
	 */

	public void start(Scanner scanner) {
		controller.startSale();

		
		int itemId;

		do {
			System.out.print("Enter item ID (or 0 to finish): ");
			itemId = scanner.nextInt();
			if (itemId != 0) {
				
				try {
				controller.enterIdentifier(itemId);
				}
			catch (NoItemFoundException e) {
		    	logWriter.logException(e);
		    	System.out.println(e.getIncorrectID());
		    	System.out.println(e.errorMessage());
		    	
		       
		        
		        
		    } catch (DatabaseException a) {
		    	logWriter.logException(a);
		    	System.out.println(a.getErrorMessage());
		    	
		    	
		      
		        
		    }
				
			}
			System.out.println("\nRunning total is: " + controller.getSaleInformation().getTotalPrice() + " SEK\n\n_______");
			System.out.println("Current cart:\n");
			for(Item item : controller.getItemsInSale()) {
				System.out.println(item.getItemName() + ": " + item.getItemPrice()+"\n");
			}
			System.out.println("_______\n");

		} while (itemId != 0);
		
		System.out.print("Enter customer ID for discount: ");
		int customerId = scanner.nextInt();
		controller.applyDiscount(customerId);
		double totalPrice = controller.endSale();
		System.out.println("Discount has been added. The new total is: " + totalPrice);

		double payment;
		do {
			System.out.print("Enter payment amount: ");
			payment = scanner.nextDouble();
			if (payment < controller.getSaleInformation().getTotalPrice()) {
				System.out.println("Payment must exceed the sale total!");
			}
		} while (payment < controller.getSaleInformation().getTotalPrice());

		controller.pay(payment);
		double change = controller.change();
		System.out.println("Your change: " + change + "\n");

		controller.printReceipt();
		
	}

}

