package model;

public class CashRegister {
	private CashPayment payment;
	private double totalBalance;

	/**
	 * Adds a cash payment to the cash register.
	 * 
	 * @param payment The cash payment to be added.
	 */
	public void addPayment(CashPayment payment) {
		this.payment = payment;
		// Add the payment amount to the total balance
		this.totalBalance += payment.getAmount();
	}

	/**
	 * Gets the total balance in the cash register.
	 * 
	 * @return The total balance in the cash register.
	 */
	public double getTotalBalance() {
		return totalBalance;
	}

}