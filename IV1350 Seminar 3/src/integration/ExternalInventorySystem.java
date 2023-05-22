package integration;

import java.util.ArrayList;

import model.Item;

/**
 * The ExternalInventorySystem class represents an external inventory system
 * that keeps track of items and their information.
 */
public class ExternalInventorySystem {

	private ArrayList<Item> inventory;

	/**
	 * Constructs a new ExternalInventorySystem object and initializes the inventory
	 * with some default items.
	 */
	public ExternalInventorySystem() {
		inventory = new ArrayList<Item>();
		inventory.add(new Item(1, 14, 0, "Milk"));
		inventory.add(new Item(2, 10, 0.2, "Tomato"));
		inventory.add(new Item(3, 3.5, 0.12, "Orange Juice"));
		inventory.add(new Item(4, 2.5, 0.1, "Apple Juice"));
		inventory.add(new Item(5, 8, 0.4, "Bananas"));
		inventory.add(new Item(6, 3, 0.1, "Carrots"));
		inventory.add(new Item(7, 12, 0.33, "Eggs"));
		inventory.add(new Item(8, 4, 0.12, "Bread"));
		inventory.add(new Item(9, 6, 0.04, "Cheese"));
		inventory.add(new Item(10, 20, 0.05, "Steak"));
	}

	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item the item to be added to the inventory.
	 */
	public void addItem(Item item) {
		inventory.add(item);
	}

	/**
	 * Private and only used inside updateInventory Removes an item from the
	 * inventory.
	 * 
	 * @param item the item to be removed from the inventory.
	 */
	private void removeItem(Item item) {
		inventory.remove(item);
	}

	/**
	 * Returns the inventory of items.
	 * 
	 * @return the inventory of items.
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * Returns information about an item with a specific item ID.
	 * 
	 * @param itemID the item ID to search for.
	 * @return the item with the matching item ID.
	 * @throws NoItemFoundException if no item is found with the specified item ID.
	 */
	public Item fetchItemInformation(int itemID) throws NoItemFoundException,DatabaseException {
		if (itemID==99) {
			 throw new DatabaseException("Database not found, try reconnecting");
			
		}
		for (Item item : inventory) {
			if (item.getItemID() == itemID) {
				return item;
			}
		}
		throw new NoItemFoundException(itemID, "Item not found in inventory.");
	}
	/**
	 * Updates the inventory by removing an item.
	 * 
	 * @param item the item to be removed from the inventory.
	 */
	public void updateInventory(Item item) {
		removeItem(item);
	}
}

