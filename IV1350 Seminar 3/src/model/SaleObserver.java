package model;

/**
 * Observer interface that receives notifications about completed sales. 
 * When a sale is completed, the observers are notified.
 */
public interface SaleObserver {
    /**
     * called when a sale is completed
     * 
     * @param paidAmount The amount paid for the completed sale.
     */
    void newPayment(CashPayment paidAmount);
}