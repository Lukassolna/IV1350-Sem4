package model;

public class Item {
	private int itemID;
	private double itemPrice;
	private double VAT;
	private String itemName;
	

	/**
	 * Constructs an Item object with the given parameters.
	 *
	 * @param itemID    the ID of the item
	 * @param itemPrice the price of the item
	 * @param VAT       the VAT of the item
	 * @param itemName  the name of the item
	 */
	public Item(int itemID, double itemPrice, double VAT, String itemName) {
		this.itemPrice = itemPrice;
		this.VAT = VAT;
		this.itemID = itemID;
		this.itemName = itemName;
	}

	/**
	 * Returns the price of the item.
	 *
	 * @return the price of the item
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * Returns the VAT of the item.
	 *
	 * @return the VAT of the item
	 */
	public double getVAT() {
		return VAT;
	}

	/**
	 * Returns the ID of the item.
	 *
	 * @return the ID of the item
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * Returns the name of the item.
	 *
	 * @return the name of the item
	 */
	public String getItemName() {
		return itemName;
	}
}
