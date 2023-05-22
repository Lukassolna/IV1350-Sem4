/**
Represents a receipt for a sale, containing information about the sale and its items, total price, and payment information.
*/
package model;

import java.util.Map;

public class Receipt {
	private String receiptText;

	/**
	 * Constructs a new Receipt object based on the provided Sale object.
	 * 
	 * @param sale the Sale object representing the sale for which the receipt is
	 *             being created
	 */
	public Receipt(Sale sale) {

		StringBuilder sb = new StringBuilder();

		// Header and time of sale
		sb.append("=== RECEIPT ===\n\n");
		sb.append("Time of sale: ").append(sale.getTimeOfSale()).append("\n\n");

		sb.append("Items:\n");

		// Generate a list of items and its quantities

		for (Map.Entry<Item, Integer> entry : sale.getItemQuantityMap().entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();

			double itemPrice = Math.round(item.getItemPrice() * (1 + item.getVAT()) * 100) / 100.0;

			sb.append(quantity);
			sb.append(" - ");
			sb.append(item.getItemName());
			sb.append(" - ");
			sb.append(itemPrice).append(" SEK \n");

		}

		// Total price
		sb.append("Total price: ").append(sale.getTotalPrice()).append(" SEK\n");

		// Payment
		sb.append("Payment: ").append(sale.getPayment()).append(" SEK\n");
		
		// Change
		sb.append("Change: ").append(sale.change()).append(" SEK\n");

		this.receiptText = sb.toString();
	}

	/**
	 * getter for Receipt
	 * 
	 * @return the text representation of the receipt
	 */
	public String getReceiptText() {
		return receiptText;
	}

}