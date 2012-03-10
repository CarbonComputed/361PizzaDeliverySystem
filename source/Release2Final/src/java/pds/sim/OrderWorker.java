/**
 * 
 */
package pds.sim;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import pds.order.Item;
import pds.order.Order;

/**
 * @author Goombas
 * @author kmc8206
 * Class the represents a worker that works by order rather than the individual items (ex. Chef can only manage 1 order at a time)
 */
public class OrderWorker extends CopyOnWriteArrayList<Order> implements Worker{
	
	private String job;
	private int totalSpace;
	private int availableSpace;
	private ArrayList<Order> finishedQueue;
	
	public OrderWorker(String job, int totalSpace){
		this.job = job;
		this.totalSpace = totalSpace;
		this.availableSpace = this.totalSpace;
		this.finishedQueue = new ArrayList<Order>();
	}
	
	/**
	 * get job returns the role of the worker (ex. Chef, Oven, Driver)
	 * @return String job
	 */
	public String getJob(){
		return this.job;
	}
	
	/**
	 * returns the total amount of space the worker can hold (in the case of the order worker it goes by how many orders the worker can handle at a time rather than space units of individual items)
	 * @returns Integer
	 */
	public int getTotalSpace(){
		return this.totalSpace;
	}
	
	/**
	 * gets the remaining space left in the worker
	 * @return Integer availableSpace 
	 */
	public int getAvailableSpace(){
		return this.availableSpace;
	}

	/**
	 * Adds an order into the worker and updates the available space (Ex. gives an order to the chef)
	 * @param o - Order
	 */
	public boolean add(Order o){
		super.add(o);
		this.availableSpace -= 1;
		return true;
		
	}
	
	/**
	 * removes the specified order from the worker and updates the available space 
	 * @param 0 - Order 
	 * @return Boolean 
	 */
	public boolean remove(Order o){
		super.remove(o);
		this.availableSpace += 1;
		return true;
	}
	
	
	/**
	 * checks to see if there is still space in the worker
	 * @return Boolean 
	 */
	public boolean isAvailable(){
		if(this.totalSpace < 0){
			return true;
		}
		if(this.size() < this.availableSpace){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the list of finished orders in the worker
	 * @return ArrayList<Order> 
	 */
	public ArrayList<Order> getFinishedQueue(){
		return this.finishedQueue;
	}
	
	/**
	 * Simple tick method to tick all of the orders and items in the worker and update their stage times as real time passes
	 */
	public void tick(){
		for(Order o: this){
			boolean orderDone=true;
			for(Item i: o){
				if(!i.isDone()){
					orderDone = false;
				}
			}
			if(orderDone){
				finishedQueue.add(o);
				this.remove(o);
			}
		}
		
		for(Order o:this){
			for(Item i: o){
				i.tick();
			}
		}
		
	}


}
