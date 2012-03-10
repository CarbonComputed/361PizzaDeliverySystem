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
 * Class the represents a worker that works by item rather than by an entire order (ex. Oven manages individual items)
 */
public class ItemWorker extends CopyOnWriteArrayList<Item> implements Worker{

	private String job;
	private int totalSpace;
	private int availableSpace;
	private boolean isAvailable;
	
	public ItemWorker(String job, int totalSpace){
		this.job = job;
		this.totalSpace = totalSpace;
		this.availableSpace = this.totalSpace;
		this.isAvailable = true;

	}
	
	/**
	 * get job returns the role of the worker (ex. Chef, Oven, Driver)
	 * @return String job
	 */
	public String getJob(){
		return this.job;
	}
	
	/**
	 * returns the total amount of space the worker can hold (in the case of the item worker it goes by item space units rather than units of entire orders)
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
	 * Adds an item into the worker and updates the available space(Ex. adds an item into the oven)
	 * @param i - Item
	 */
	public void process(Item i){
		super.add(i);
		this.availableSpace -= i.getSpace();
		i.setIsProcessing(true);
	}
	
	/**
	 * figures out whether or not the worker can fit in the desired item
	 * @param e - Item 
	 * @return Boolean 
	 */
	public boolean getAvailability(Item e){
		if(this.totalSpace < 0){
			return true;
		}
		if(this.getAvailableSpace() - e.getSpace() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * removes the specified item from the worker and updates the available space 
	 * @param i - Item 
	 * @return Boolean 
	 */
	public boolean remove(Item i){
		this.availableSpace += i.getSpace();
		return super.remove(i);
	}
	
	/**
	 * Simple tick method to tick all of the items in the worker and update their stage time as real time passes
	 */
	public void tick(){
		for(Item i: this){
			i.tick();
		}
	}

}
