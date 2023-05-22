/**
A class representing an external accounting system that keeps track of all sales made by the store stored as a list of Sale objects
*/
package integration;

import java.util.ArrayList;
import java.util.List;

import model.Sale;

public class ExternalAccountingSystem {
	private List<Sale> allSales;

	/**
	 * Constructs a new ExternalAccountingSystem object and initializes an empty
	 * list of sales.
	 */
	public ExternalAccountingSystem() {
		allSales = new ArrayList<>();
	}

	/**
	 * Updates the accounting system with the provided Sale object by adding it to
	 * the list of all sales.
	 * 
	 * @param inputSale the Sale object to add to the accounting system
	 */
	public void updateAccounting(Sale inputSale) {
		allSales.add(inputSale);
	}
}