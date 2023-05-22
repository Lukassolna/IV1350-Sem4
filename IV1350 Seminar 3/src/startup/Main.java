package startup;

import java.io.IOException;
import java.util.Scanner;

import controller.Controller;
import integration.ExternalSystemHandler;
import integration.LogWriter;
import integration.Printer;
import model.CashRegister;
import model.Item;
import view.View;

public class Main {

	/**
	 * The main method that starts the program.
	 * 
	 * This method initializes the CashRegister, Printer, and ExternalSystemHandler
	 * objects, creates a new Controller object with these objects, and passes it to
	 * a new View object to start the user interface. The main method is the entry
	 * point for the program and is responsible for setting up the necessary objects
	 * and starting the program execution.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		 
		CashRegister cashRegister = new CashRegister();
		Printer printer = new Printer();
		ExternalSystemHandler extSysHan = new ExternalSystemHandler();
		Controller contr = new Controller(cashRegister, printer, extSysHan);
		View view = new View(contr);
		Scanner scanner = new Scanner(System.in);
		view.start(scanner);
		
		
		
		
		
	}
}
