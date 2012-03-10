/**
 * 
 */
package pds.sim;

import java.util.ArrayList;

import pds.order.Order;

/**
 * @author Goombas
 * interface for the workers in the pizza shop (ex. Chef, Oven, Driver)
 */
public interface Worker {
	
	public String getJob();
	
	public int getTotalSpace();
	
	public int getAvailableSpace();

	public void tick();
}
