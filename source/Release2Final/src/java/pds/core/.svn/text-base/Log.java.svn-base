/**
 * Log.java
 */
package pds.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pds.database.Employee;
import pds.order.Item;
import pds.order.Order;
import pds.sim.Simulation;
import pds.ui.ModifiedObservable;



/**
 * @author kmc8206
 *
 */
public class Log extends CopyOnWriteArrayList < Order > implements Serializable {
	
	private transient ModifiedObservable observable;
	private transient Register register;
	
	/**
	 * Constructs the Log with a reference to the register object
	 * @param register Reference to the Register object.
	 */
	public Log(Register register){
		super();
		observable = new ModifiedObservable();
		this.register = register;
	}
	
	/**
	 * Computes the average cost of all the orders
	 * @return The average cost of all the orders
	 */
	public double getAverageCost(){
		if(this.isEmpty()){
			return 0;
		}
		double total = 0;
		for(Order o : this){
			total += o.getTotalPrice();
		}
		return total / this.size();
	}
	
	/**
	 * Computes the average time taken of all the orders.
	 * @return the average time taken of all the orders.
	 */
	public double getAverageTime(){
		if(this.isEmpty()){
			return 0;
		}
		int total = 0;
		for(Order o : this){
			total += o.getActualTime();
		}
		return total / this.size();
	}
	
	/**
	 * Computes the number of orders the specified employee has taken
	 * @param e The employee being checked
	 * @return the number of orders.
	 */
	public int getNumberofOrdersTaken(Employee e){
		int n=0;
		for(Order o: this){
			if(o.getInitiator().equals(e)){
				n++;
			}
		}
		return n;
	}
	
	/**
	 * Returns all the orders in the specified stage
	 * @param The specified stage.
	 * @return The filterd List of orders.
	 */
	public List<Order> getOrders(String stage){
		ArrayList < Order > ordersFilter = new ArrayList < Order >();
		for(Order o : this){
			if(stage.equals(o.getState())){
				ordersFilter.add(o);
			}
		}
		return Collections.unmodifiableList(ordersFilter);
	}
	
	/**
	 * Returns the filtered list of orders accordint to a start and end date.
	 * @param stage Possibe stage filter (Unused for now)
	 * @param d1 The start date
	 * @param d2 The ending date
	 * @return The filtered list of orders.
	 */
	public List<Order> getOrders(String stage,Date d1, Date d2){
		
		ArrayList < Order > ordersFilter = new ArrayList < Order >();
		for(Order o : this){
			if(o.getDateTaken().after(d1) && o.getDateTaken().before(d2)||
					(o.getDateTaken().getDay()==d1.getDay()&&o.getDateTaken().getMonth()==d1.getMonth()
					&&o.getDateTaken().getYear()==d1.getYear())||
					(o.getDateTaken().getDay()==d2.getDay()&&o.getDateTaken().getMonth()==d2.getMonth()
					&&o.getDateTaken().getYear()==d2.getYear())||o.getDateTaken().before(d1) && o.getDateTaken().after(d2)
					){
				
				ordersFilter.add(o);
			}
		}
		return Collections.unmodifiableList(ordersFilter);
	}
	
	/**
	 * Returns the average item ordered(Unimplemented)
	 * @return The average item ordered.
	 */
	public Item getAverageItem(){
		return null;
	}
	
	/**
	 * Returns the total number of orders.
	 * @return
	 */
	public int getTotalOrders(){
		return this.size();
	}
	
	/**
	 * Returns the total profit made.
	 * @return The total profit.
	 */
	public double getTotalProfit(){
		double total = 0;
		for(Order o : this){
			total += o.getTotalPrice();
		}
		return total;
	}
	
	/**
	 * Returns the profit for the day
	 * @return Returns the profit for the day.
	 */
	public double getDailyProfit(){
		double total = 0;
		for(Order o: register.getSimulation().getDailyOrders()){
			total += o.getTotalPrice();
		}
		return total;
	}
	
	/**
	 * Returns the required manager data.
	 */
	public String toString(){
		String str = "";
		str += "Manager Data:" + "\n";
		str += "Daily Order Count: " + this.register.getSimulation().getDailyOrders().size() +"\n";
		str += "All Order Count: " + getTotalOrders() + "\n";
		str += "Daily Profit: " + getDailyProfit() + "\n";
		str += "Total Profit: " + getTotalProfit() + "\n";
		str += "Average Time: " + getAverageTime() +"\n";
		str += "Average Cost: " + getAverageCost();
		return str;
	}
	
	/**
	 * Serializes the log to the file.
	 * @throws FileNotFoundException If a file is not found.
	 * @throws IOException If anything goes wrong when reading.
	 */
	public void writeDatabase() throws FileNotFoundException, IOException{
		try {
			ObjectOutput output = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("history/Orders.ser")));
		
			output.writeObject(this);
			
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated cacotch block
			throw e;
		}
	}
	
	/**
	 * Reads the serialized order log.
	 * @throws Exception Throws an exception if anything goes wrong.
	 */
	public void readDatabase() throws Exception{
		try{
	      InputStream file = new FileInputStream( "history/Orders.ser" );
	      InputStream buffer = new BufferedInputStream( file );
	      ObjectInput input = new ObjectInputStream ( buffer );
	      CopyOnWriteArrayList < Order > log = (CopyOnWriteArrayList < Order >) input.readObject();
	      this.addAll(log);
	      input.close();
		}catch(FileNotFoundException e){
			return;
		} catch(Exception e){
			//throw e;
			return;
		}
	}
	
	/**
	 * Returns this observable object.
	 * @return The observable object.
	 */
	public ModifiedObservable getObservable(){
		return observable;
	}
	
}
