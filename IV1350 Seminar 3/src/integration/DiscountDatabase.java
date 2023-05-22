/**
This class represents a database of discounts, stored as a map with customer IDs as keys and
discount percentages as values.
*/
package integration;

import java.util.HashMap;
import java.util.Map;

public class DiscountDatabase {
	private Map<Integer, Double> discounts;

	/**
	 * Constructs a new DiscountDatabase and initializes it with some sample
	 * discounts for customer IDs 1, 2, and 3.
	 */
	public DiscountDatabase() {
		discounts = new HashMap<Integer, Double>();
		discounts.put(1, 0.2);
		discounts.put(2, 0.4);
		discounts.put(3, 0.15);
	}

	/**
	 * Looks up the discount percentage for the given customer ID.
	 *
	 * @param customerID the ID of the customer for which to fetch the discount
	 *                   percentage
	 * @return the discount percentage as a decimal value between 0 and 1. 0 if no
	 *         discount is found
	 */
	public double fetchDiscount(int customerID) {
		Double discountPercentage = discounts.get(customerID);
		if (discountPercentage != null) {
			return discountPercentage;
		} else {
			return 0.0;
		}
	}
}
