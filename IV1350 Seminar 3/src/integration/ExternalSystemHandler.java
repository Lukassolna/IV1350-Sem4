package integration;

import model.Item;
import model.Sale;

/**
 * A class that handles interactions with external systems, such as the discount
 * database, external accounting system, and external inventory system.
 */
public class ExternalSystemHandler {
	private DiscountDatabase discountDatabase;
	private ExternalAccountingSystem extAcc;
	private ExternalInventorySystem extInv;

	/**
	 * Creates a new instance of ExternalSystemHandler with initialized instances of
	 * the DiscountDatabase, ExternalAccountingSystem, and ExternalInventorySystem
	 * classes.
	 */
	public ExternalSystemHandler() {
		discountDatabase = new DiscountDatabase();
		extAcc = new ExternalAccountingSystem();
		extInv = new ExternalInventorySystem();
	}

	/**
	 * Returns the DiscountDatabase instance associated with this
	 * ExternalSystemHandler.
	 *
	 * @return the DiscountDatabase instance associated with this
	 *         ExternalSystemHandler.
	 */
	public DiscountDatabase getDiscountDatabase() {
		return discountDatabase;
	}

	/**
	 * Returns the ExternalAccountingSystem instance associated with this
	 * ExternalSystemHandler.
	 *
	 * @return the ExternalAccountingSystem instance associated with this
	 *         ExternalSystemHandler.
	 */
	public ExternalAccountingSystem getExternalAccountingSystem() {
		return extAcc;
	}

	/**
	 * Returns the ExternalInventorySystem instance associated with this
	 * ExternalSystemHandler.
	 *
	 * @return the ExternalInventorySystem instance associated with this
	 *         ExternalSystemHandler.
	 */
	public ExternalInventorySystem getExternalInventorySystem() {
		return extInv;
	}

	/**
	 * Updates the external systems (external inventory system and external
	 * accounting system) with the given sale information.
	 *
	 * @param sale the Sale object representing the sale to be processed.
	 */
	public void updateExternalSystems(Sale sale) {
		// Update external inventory system with all the items in the sale
		for (Item item : sale.getItemList()) {
			extInv.updateInventory(item);
		}

		// Update external accounting system with the sale information
		extAcc.updateAccounting(sale);
	}

	/**
	 * Fetches the discount to be applied to a sale based on the customer ID.
	 *
	 * @param customerID the ID of the customer for whom the discount should be
	 *                   applied.
	 * @return the discount amount to be applied to the sale (as a decimal value
	 *         between 0 and 1).
	 */
	public double fetchDiscount(int customerID) {
		double discountToApply = discountDatabase.fetchDiscount(customerID);
		
		return discountToApply;
	}
}
