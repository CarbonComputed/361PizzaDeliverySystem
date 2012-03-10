/**
 * 
 */
package pds.test;
import java.util.*;

import junit.framework.TestCase;

import pds.database.Employee;
import pds.database.Customer;
import pds.database.Location;
import pds.order.Item;
import pds.order.Order;
import pds.sim.OrderWorker;


/**
 * @author Goombas
 *
 */
public class OrderWorkerTest extends TestCase{
	
	private Item first;
	private Item second;
	private Order ord;
	private OrderWorker iw;
	private Employee init;
	private Customer cust;
	private Location local;
	private OrderWorker ow;
	
	
	public void setUp(){
		ArrayList<Integer> largeList = new ArrayList<Integer>();
		largeList.add(5);
		largeList.add(10);
		ArrayList<Integer> smallList = new ArrayList<Integer>();
		smallList.add(2);
		smallList.add(5);
		local = new Location("RIT", 25);
		first = new Item("LargePizza", 10.5, 10, true, largeList);
		second = new Item("SmallPizza", 5.25, 5, true, smallList);
		init = new Employee("1", "Steve", "szabo", true);
		cust = new Customer("Rafiqy", local, "1234567890");
		ord = new Order(cust, init);
		ord.add(first);
		ord.add(second);
		ow = new OrderWorker("Chef", 1);
	}
	
	public void testGetJob(){
		assertTrue(ow.getJob().equals("Chef"));
		
	}
	
	public void testGetTotalSpace(){
		assertTrue(ow.getTotalSpace() == 1);
	}
	
	public void testGetAvailableSpace(){
		assertTrue(ow.getAvailableSpace() == 1);
		ow.add(ord);
		assertTrue(ow.getAvailableSpace() == 0);
	}
	
	public void testAdd(){
		assertTrue(ow.size() == 0);
		ow.add(ord);
		assertTrue(ow.size() == 1);
	}
	
	public void testRemove(){
		assertTrue(ow.size() == 0);
		ow.add(ord);
		assertTrue(ow.size() == 1);
		ow.remove(ord);
		assertTrue(ow.size() == 0);
	}
	
	public void testIsAvailable(){
		assertTrue(ow.size() == 0);
		assertTrue(ow.isAvailable() == true);
		ow.add(ord);
		assertTrue(ow.size() == 1);
		assertTrue(ow.isAvailable() == false);
	}
	
	public void testGetFinishedQueue(){
		assertTrue(ow.getFinishedQueue().size() == 0);
		ow.getFinishedQueue().add(ord);
		assertTrue(ow.getFinishedQueue().size() == 1);
	}
	
	public void testTick(){
		ow.add(ord);
		ow.tick();
		assertTrue(ow.get(0).get(0).getActualTime() == 1);
		assertTrue(ow.get(0).get(1).getActualTime() == 1);
	}
}
