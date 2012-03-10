package pds.order;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import pds.database.Customer;
import pds.database.Employee;
import pds.ui.ModifiedObservable;

/**
 * Order class that holds and manages a list of items.
 * 
 * @author Goombas
 */
public class Order extends CopyOnWriteArrayList<Item> implements Serializable {

	private Customer cust;
	private Employee initiator;
	private int totalTime;
	private double totalPrice;
	private int totalSpace;
	private String state;
	private Date dateTaken;
	private transient ModifiedObservable observable;

	public Order(Customer cust, Employee init) {
		super();
		this.cust = cust;
		this.initiator = init;
		this.totalTime = 0;
		this.totalPrice = 0;
		dateTaken = new Date();

		observable = new ModifiedObservable();

	}

	/**
	 * returns the customer that the order belongs too
	 * 
	 * @return Customer
	 */
	public Customer getCustomer() {
		return this.cust;
	}

	/**
	 * adds an item into the order and updates observable settings
	 * 
	 * @return Boolean
	 */
	public boolean add(Item e) {
		super.add(e);
		if (e.getTime() > totalTime) {
			totalTime = e.getTime();
		}
		observable.setChanged();
		observable.notifyObservers();
		observable.clearChanged();
		return true;
	}

	/**
	 * gets the employee who took the order
	 * 
	 * @return Employee
	 */
	public Employee getInitiator() {
		return this.initiator;
	}

	/**
	 * Calculates and returns the total price of the order and its items
	 * 
	 * @return Double totalPrice
	 */
	public double getTotalPrice() {
		totalPrice = 0;
		for (Item i : this) {
			totalPrice += i.getPrice();
		}
		return totalPrice;
	}

	/**
	 * gets the total expected time of preparation, cooking and other item
	 * stages
	 * 
	 * @return Integer totalTime
	 */
	public int getExpectedTime() {
		return totalTime;
	}

	/**
	 * calculates and returns the actual time the order has been processing
	 * 
	 * @return Integer
	 */
	public int getActualTime() {
		int max = 0;
		for (Item i : this) {
			if (i.getActualTime() > max) {
				max = i.getActualTime();
			}
		}
		return max;
	}

	/**
	 * calculates and returns the total space the order and its items takes up
	 * 
	 * @return Integer totalSpace
	 */
	public int getTotalSpace() {
		for (Item i : this) {
			this.totalSpace += i.getSpace();
		}
		return this.totalSpace;
	}

	/**
	 * returns the date the order was taken
	 * 
	 * @return Date
	 */
	public Date getDateTaken() {
		return this.dateTaken;
	}

	/**
	 * checks all of the items in the Order to see if they are all finished with
	 * their current stage. if so, the order is set to done as well
	 * 
	 * @return Boolean
	 */
	public boolean isDone() {
		for (Item i : this) {
			if (!i.isDone()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checks all of the items in the order to see if any are still processing.
	 * if so, then the order is still processing
	 * 
	 * @return Boolean
	 */
	public boolean isProcessing() {
		for (Item i : this) {
			if (!i.isProcessing()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * goes through each item of the order and advances them to the next stage
	 * (ex. prepare stage -> cook stage)
	 */
	public void nextStage() {
		for (Item i : this) {
			i.nextStage();
		}
	}

	/**
	 * sets a stringly typed state and updates overservables (ex. set state to
	 * "Cooking")
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
		observable.setChanged();
		observable.notifyObservers(this);
		observable.clearChanged();
	}

	/**
	 * gets the current state of the order (ex. "Cooking")
	 * 
	 * @return String
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * gets the order observable
	 * 
	 * @return ModifiedObservable
	 */
	public ModifiedObservable getObservable() {
		return observable;
	}

	/**
	 * gets the information of the order. (ex. customer's name, phone number,
	 * location, the time the order was taken, the stage its currently in ect.)
	 * 
	 * @return String
	 */
	public String getData() {

		String str = "";
		str += "Order: \n";
		str += this.getCustomer().getPhoneNumber() + "\n";
		str += this.getCustomer().getName() + "\n";
		str += this.getCustomer().getLocation().getLocation() + "\n";
		str += "$" + String.format("%1$,.2f", this.getTotalPrice()) + "\n";
		str += "Time Taken: " + this.getDateTaken().toGMTString() + "\n";
		str += "Estimated Time: " + this.getExpectedTime() + "\n";
		str += "Actual Time: " + this.getActualTime() + "\n";
		str += "Status: " + this.getState() + "\n";
		str += "Items: " + "\n";
		for (Item i : this) {
			str += i.getName() + "\n";
			if(i.isToppingable()){
			if (i.isWhole()) {
				for (Topping t : i.getLeftSide()) {
					str += "  " + "Whole: " + t.getName() + "\n";
				}
			} else {
				for (Topping t : i.getLeftSide()) {
					str += "  " + "Left: " + t.getName() + "\n";
				}
				for (Topping t : i.getRightSide()) {
					str += "  " + "Right: " + t.getName() + "\n";
				}
			}
		}
		}
		return str;
	}

	/**
	 * A simple toString method to get the order information
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "";
		str += "<html>";
		str += this.getCustomer().getPhoneNumber() + "-"
				+ this.getCustomer().getName() + "<br>";
		str += this.getCustomer().getLocation().getLocation() + "<br>";
		str += "Status: +" + this.getState() + "<br>";
		str += "</html>";
		return str;

	}
}
