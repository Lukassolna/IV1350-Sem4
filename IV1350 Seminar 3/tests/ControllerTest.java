

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Controller;
import integration.DatabaseException;
import integration.ExternalSystemHandler;
import integration.DatabaseException;
import integration.NoItemFoundException;
import integration.Printer;
import model.CashRegister;
import model.Item;
import model.Sale;


class ControllerTest {
	private CashRegister cashRegister;
	private Printer printer;
	private ExternalSystemHandler extSysHan;
	private Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		cashRegister = new CashRegister();
		printer = new Printer();
		extSysHan = new ExternalSystemHandler();
		controller = new Controller(cashRegister, printer, extSysHan);
	}

	@AfterEach
	void tearDown() throws Exception {
		cashRegister=null;
		printer=null;
		extSysHan=null;
		controller=null;
	}

	@Test
	void testStartSale() {
		controller.startSale();
		assertNotNull(controller.getSaleInformation(), "Sale information should not be null");
		assertTrue(controller.getSaleInformation() instanceof Sale, "Sale information should be an instance of Sale");
	}
	public class YourClassNameTest {
	    
	   
	}
	@Test
	// This was changed for seminar 4
	void testEnterIdentifierValidItem() throws DatabaseException{
		controller.startSale();
		boolean result=false;
		try {	
			 result = controller.enterIdentifier(1); //1 is a valid itemID
	        }
		catch (NoItemFoundException e){
			fail("Exception was thrown for a valid id");
		

		assertTrue(result, "enterIdentifier() should return true for a valid item ID");
		assertEquals(1, controller.getSaleInformation().getItemList().size(), "Sale should have one item after adding a valid item ID");
		assertEquals(1,controller.getSaleInformation().getItemList().get(0).getItemID(),"The item identifier added to sale does not match entered ID");
	}
	}
	@Test
	//THIS WAS ADDED FOR SEMINAR 4
	void testEnterInvalidIdentifierException() throws DatabaseException {

		controller.startSale();
		boolean result=false;

		try {
			result=controller.enterIdentifier(123);
			
		} catch (NoItemFoundException e) {

			assertEquals(123, e.getIncorrectID());
			assertEquals("Item not found in inventory.", e.getMessage());
		}
		assertFalse(result, "enterIdentifier() should return false for an invalid item ID");
        assertEquals(0, controller.getSaleInformation().getItemList().size(), "Sale should have zero items after adding an invalid item ID");
    }
		

	  


  

              
            
    

	@Test
	//THIS WAS ADDED FOR SEMINAR 4
	void testEnterDataBaseException() throws NoItemFoundException  {

		controller.startSale();
		 

		try {
			controller.enterIdentifier(99);
		
		} catch (DatabaseException a) {
			assertNotNull(a.getErrorMessage());
			assertEquals("Database not found, try reconnecting",a.getMessage());



		}
		
		

	}
	


	@Test
	void testApplyDiscountValidCustomerId() throws NoItemFoundException, DatabaseException {
		controller.startSale();
		controller.enterIdentifier(1); 
		double discountTest=0.2;
		double priceBefore=controller.getSaleInformation().getTotalPrice();
		controller.applyDiscount(1); // CustomerID of 1 has a discount of 0.2
		double priceAfter=controller.getSaleInformation().getTotalPrice();

		assertEquals(priceAfter, priceBefore*(1-discountTest),0.001, "Discount should be applied for customer ID 1");

	}



	@Test
	void testApplyDiscountInvalidCustomerId() throws NoItemFoundException, DatabaseException {
		controller.startSale();
		controller.enterIdentifier(1);

		double priceBefore=controller.getSaleInformation().getTotalPrice();
		controller.applyDiscount(40); // CutomerID of 40 is not eligible for discount

		double priceAfter=controller.getSaleInformation().getTotalPrice();

		assertEquals(priceBefore, priceAfter, "Price should not change for an invalid customer ID");
	}



	@Test
	void testPay() throws NoItemFoundException, DatabaseException {
		controller.startSale();
		controller.enterIdentifier(1);
		controller.pay(100);

		assertEquals(100, cashRegister.getTotalBalance(), "Cash register balance should be updated after calling pay()");
	}



	@Test
	public void testEndSale() throws NoItemFoundException, DatabaseException {
		controller.startSale();

		controller.enterIdentifier(1);

		double expectedTotalPrice = 14; // the expected total price with VAT and discounts applied
		assertEquals(expectedTotalPrice, controller.endSale(), 0.01); // delta of 0.01 to account for floating point errors
	}
}