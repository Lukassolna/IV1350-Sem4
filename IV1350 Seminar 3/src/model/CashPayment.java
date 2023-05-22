package model;

public class CashPayment {
	/**
	 * Represents a cash payment.
	 * 
	 * @param amount the amount of cash paid
	 */
	private double amount;

	public CashPayment(double amount) {
		this.amount = amount;
	}

	/**
	 * Returns the amount of cash paid.
	 * 
	 * @return the amount of cash paid
	 */
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double inputAmount) {
		amount=inputAmount;
	}

}
