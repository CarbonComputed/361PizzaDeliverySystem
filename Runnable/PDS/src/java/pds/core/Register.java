/**
 * Register.java
 * This class acts almost as a facade for the gui to the rest of the program.
 * It contains all the necessary references that the gui might need.
 */
package pds.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import pds.database.Customer;
import pds.database.Customers;
import pds.database.Employee;
import pds.database.EmployeeDatabase;
import pds.database.Location;
import pds.database.LocationDatabase;
import pds.database.MenuDatabase;
import pds.database.StageDatabase;
import pds.database.ToppingList;
import pds.order.Item;
import pds.order.Order;
import pds.order.Topping;
import pds.sim.Simulation;

/**
 * @author kmc8206
 * 
 */
public class Register extends Observable {

	private boolean isOpen;
	private EmployeeDatabase empDatabase;
	private LocationDatabase locDatabase;
	private Customers custDatabase;
	private Log orderLog;
	private boolean isFirstRun;
	private Simulation simulation;
	private MenuDatabase menuDatabase;
	private StageDatabase stageDatabase;
	private ToppingList toppingList;

	/**
	 * Constructs the register object.
	 */
	public Register() {
		super();
		isOpen = false;
		empDatabase = new EmployeeDatabase();
		locDatabase = new LocationDatabase();
		toppingList = new ToppingList();
		custDatabase = new Customers(locDatabase);
		simulation = new Simulation(500);
		menuDatabase = new MenuDatabase(simulation);
		stageDatabase = new StageDatabase(simulation);
		orderLog = new Log(this);
		isFirstRun = false;
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();

	}

	/**
	 * Returns a reference to the location database.
	 * 
	 * @return A reference to the location database.
	 */
	public LocationDatabase getLocationDatabase() {
		return locDatabase;
	}

	/**
	 * Reads in all the databases.
	 * 
	 * @throws Exception
	 *             If anything goes wrong when the database is read.
	 */
	public void readDatabases() throws Exception {

		try {
			empDatabase.readDatabase();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			isFirstRun = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		if (empDatabase.isEmpty()) {
			isFirstRun = true;
		}
		locDatabase.readDatabase();
		custDatabase.readDatabase();
		orderLog.readDatabase();
		stageDatabase.readDatabase();
		menuDatabase.readDatabase();
		toppingList.readDatabase();

	}

	/**
	 * Returns a reference to all the toppings.
	 * 
	 * @return A reference to the topping database.
	 */
	public ToppingList getToppingDatabase() {
		return toppingList;
	}

	/**
	 * This function is used for filtering orders based on a stage/filter
	 * 
	 * @param number
	 *            The customers phone number
	 * @param filter
	 *            The string that is being looked for
	 * @return An ArrayList of orders that have been found with the filter.
	 * @throws Exception
	 *             Throws an Exception if no matching orders are found.
	 */
	public CopyOnWriteArrayList<Order> findCustomerOrder(String number,
			String filter) throws Exception {
		filter = filter.toLowerCase();
		if (number.length() == 0) {
			CopyOnWriteArrayList<Order> orders = new CopyOnWriteArrayList<Order>();
			for (Order o : this.getSimulation().getDailyOrders()) {
				if ((o.getState().toLowerCase().contains(filter)
						|| filter.equals("all") || filter.equals(o.getState()))) {
					orders.add(o);
				}
			}
			if (orders.isEmpty()) {
				throw new Exception("No Orders Found");
			}
			return orders;
		}
		CopyOnWriteArrayList<Order> orders = new CopyOnWriteArrayList<Order>();
		for (Order o : this.getSimulation().getDailyOrders()) {
			if (o.getCustomer().getPhoneNumber().equals(number)
					&& (o.getState().toLowerCase().contains(filter)
							|| filter.equals("all") || filter.equals(o
							.getState()))) {
				orders.add(o);
			}
		}
		if (orders.isEmpty()) {
			throw new Exception("No Orders Found");
		}
		return orders;
	}

	/**
	 * Takes an order and adds it to the simulation
	 * 
	 * @param cust
	 *            The customer identified with the order.
	 * @param orderItems
	 *            The items the customer just chose.
	 * @throws Exception
	 *             If The store is not open or if there are no items in the
	 *             order.
	 */
	public void takeOrder(Customer cust, ArrayList<Item> orderItems)
			throws Exception {
		if (orderItems.isEmpty()) {
			throw new Exception("There are no items in the order");
		}
		if (!this.isOpen) {
			throw new Exception("The Store is not Open");
		}

		CopyOnWriteArrayList<Item> itemsCopy = new CopyOnWriteArrayList<Item>();
		for (Item i : orderItems) {
			ArrayList<Integer> orderTimes = new ArrayList<Integer>();
			for (int x : i.getOrderTimes()) {
				orderTimes.add(x);

			}
			orderTimes.add(cust.getLocation().getDistance());
			Item newitem = new Item(i.getName(), i.getPrice(), i.getSpace(),
					i.isToppingable(), orderTimes);
			
			if(i.isToppingable()){
			if (i.isWhole()) {
				for (Topping t : i.getLeftSide()) {
					Topping nt = new Topping(t.getName(), t.getPrice());
					newitem.addTopping(nt);
				}
			} else {
				for (Topping t : i.getLeftSide()) {
					Topping nt = new Topping(t.getName(), t.getPrice());
					newitem.addToppingLeft(nt);
				}
				for (Topping t : i.getRightSide()) {
					Topping nt = new Topping(t.getName(), t.getPrice());
					newitem.addToppingRight(nt);
				}
			}
		}
			itemsCopy.add(newitem);
		}
		
		Order order = new Order(cust, this.getEmployeeDatabase().getLoggedOn());
		for (Item i : itemsCopy) {
			order.add(i);
		}
		simulation.addOrder(order);
		orderLog.add(0, order);

		this.setChanged();
		this.notifyObservers(order);
		this.clearChanged();

	}

	/**
	 * Returns a reference to the simulation
	 * 
	 * @return The simulation object.
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	/**
	 * Closes the store and stops the simulation thread.
	 * 
	 * @throws Exception
	 *             If the store is already closed or if orders are processing.
	 */
	public void close() throws Exception {
		if (!this.isOpen) {
			throw new Exception("The Store is already closed");
		}
		for (Order o : simulation.getDailyOrders()) {
			if (!o.getState().equals("Delivered")
					&& !o.getState().equals("Deleted")) {
				throw new Exception("Cannot Close While Orders are Processing");
			}
		}
		isOpen = false;
		custDatabase.writeDatabase();
		empDatabase.writeDatabase();
		orderLog.writeDatabase();
		simulation.interrupt();

	}

	/**
	 * Returns a reference to the order log.
	 * 
	 * @return
	 */
	public Log getOrderLog() {
		return orderLog;
	}

	/**
	 * Opens the store and starts the simulation.
	 * 
	 * @throws Exception
	 *             Not implemented
	 */
	public void open() throws Exception {
		isOpen = true;
		simulation.start();
		// simulation.test();

	}

	/**
	 * Returns true if the store is open.
	 * 
	 * @return True if the store is open.
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Returns true if it is the first time the program is being ran.
	 * 
	 * @return True if it is the first time the program is ran.
	 */
	public boolean isFirstRun() {
		return isFirstRun;
	}

	/**
	 * Returns a reference to the menu.
	 * 
	 * @return A reference to the menu.
	 */
	public MenuDatabase getMenu() {
		return menuDatabase;
	}

	/**
	 * Returns a refernce to the customer database.
	 * 
	 * @return A reference to customer database.
	 */
	public Customers getCustomerDatabase() {
		return custDatabase;
	}

	/**
	 * Returns a reference to the Employee Database
	 * 
	 * @return A reference to the Employee Database.
	 */
	public EmployeeDatabase getEmployeeDatabase() {
		return empDatabase;
	}

}
