/**
The Sale class represents a sale in the point of sale system.
It keeps track of the items purchased, the total price, the payment received, and the time of the sale.
It also provides methods for adding items to the sale, calculating the total price, applying a discount,
and printing a receipt.
*/
package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.Printer;

public class Sale {
	private LocalTime timeOfSale; // the time the sale was made
	private Map<Item, Integer> itemQuantityMap; // a map of items and their quantities in the sale
	private double totalPrice; // the total price of the sale
	private CashPayment payment; // the amount of payment received for the sale
	private CashPayment paymentAfterChange;
	private Printer printer; // the printer used to print the receipt
	private List<Item> itemList; // a list of items in the sale
	private double change;
	private List <SaleObserver>  saleObservers = new ArrayList<>();

	/**
	 * Creates a new Sale object. initializes empty HashMap and ArrayList and sets
	 * timeOfSale Hashmap is for keeping track of quantities and itemList is a list
	 * of items
	 * 
	 *
	 * @param printer The printer to be used to print the receipt.
	 */
	public Sale(Printer printer) {
		timeOfSale = LocalTime.now().withNano(0);
		itemQuantityMap = new HashMap<>();
		this.printer = printer;
		itemList = new ArrayList<>();
		
	}

	/**
	 * Adds an item to the sale. If the item is already in the sale, its quantity is
	 * increased. Otherwise, it is added to the sale with a quantity of 1. The total
	 * price of the sale is updated accordingly.
	 *
	 * @param item The item to be added to the sale.
	 */
	public void addItem(Item item) {
		itemList.add(item);
		if (itemQuantityMap.containsKey(item)) {
			int quantity = itemQuantityMap.get(item);
			itemQuantityMap.put(item, quantity + 1);
		} else {
			itemQuantityMap.put(item, 1);
		}

		totalPrice += item.getItemPrice() * (1 + item.getVAT());
	}

	/**
	 * Returns the list of items in the sale.
	 *
	 * @return The list of items in the sale.
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * Returns the total price of the sale.
	 *
	 * @return The total price of the sale.
	 */
	public double getTotalPrice() {
		return Math.round(totalPrice * 100) / 100.0;
	}

	/**
	 * Applies a discount to the total price of the sale. The discount is given as a
	 * decimal and must be between 0 and 1. Updates total price based on discount
	 *
	 * @param inputDiscount The discount to be applied to the sale.
	 */
	public void applyDiscount(double inputDiscount) {
		double discountPercentage = 1-inputDiscount;
		totalPrice *= discountPercentage;

	}


	

	/**
	 * Sets the payment amount for the sale.
	 *
	 * @param paidAmount The amount paid for the sale.
	 */
	public void pay(CashPayment cashPayment) {
		
		payment = cashPayment;
		paymentAfterChange=new CashPayment((payment.getAmount()-change()));
		notifyObservers();
	}

	/**
	 * Prints a receipt for the sale using the printer.
	 */
	public void printReceipt() {
		Receipt receipt = new Receipt(this);
		printer.print(receipt);
	}

	public double getPayment() {
		return payment.getAmount();
	}
	public double getPaymentAfterchange() {
		return paymentAfterChange.getAmount();
	}

	/**
	 * Calculates the change and returns it rounded.
	 */
	public double change() {
		change = payment.getAmount() - totalPrice;
		return Math.round(change * 100) / 100.0;
	}

	public LocalTime getTimeOfSale() {
		return timeOfSale;
	}

	public Map<Item, Integer> getItemQuantityMap() {
		return itemQuantityMap;

	}

	public Printer getPrinter() {
		return printer;
	}
	
    /**
     * Notifies all observers the total amount paid for the sale.
     */
    private void notifyObservers() {
    	
        for(SaleObserver obs : saleObservers) {
            obs.newPayment(paymentAfterChange);
        }
    }
    
    /**
     * Adds a observer that will be notified when the sale has concluded
     * 
     * 
     * @param obs The observer to add
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }
    
    /**
     * Adds multiple observers that will be notified when the sale has concluded
     *
     * @param obsevers The observers to add
     */
    public void addSaleObservers(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
    }
	
}
