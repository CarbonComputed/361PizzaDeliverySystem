package pds.test;

import java.util.*;

import pds.database.Employee;
import pds.database.Customer;
import pds.database.Location;
import pds.order.Item;
import pds.order.Order;

import junit.framework.*;

/**
 * @author Goombas
 * 
 */

public class OrderTest extends TestCase {

	private Location local;
	private Customer cust;
	private Employee init;
	private Order ord;
	private ArrayList<Integer> largeList;
	private ArrayList<Integer> smallList;
	private Item first;
	private Item second;

	public void setUp() {
		local = new Location("RIT", 25);
		cust = new Customer("Steve", local, "201-819-0965");
		init = new Employee("1", "Kevin", "password", true);
		ord = new Order(cust, init);
		ArrayList<Integer> largeList = new ArrayList<Integer>();
		largeList.add(5);
		largeList.add(10);
		ArrayList<Integer> smallList = new ArrayList<Integer>();
		smallList.add(2);
		smallList.add(5);
		first = new Item("LargePizza", 10.5, 10, true, largeList);
		second = new Item("SmallPizza", 5.25, 5, true, smallList);
	}

	public void testGetCustomer(){
		assertTrue(ord.getCustomer().equals(cust));
	}
	
	public void testGetInitiator(){
		assertTrue(ord.getInitiator().equals(init));
	}
	
	public void testAdd(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.size() == 2);
	}
	
	public void testGetPrice() {
		ord.add(first);
		ord.add(second);
		assertTrue(ord.getTotalPrice() == (first.getPrice() + second.getPrice()));
	}

	public void testGetExpectedTime(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.getExpectedTime() == 15);
	}
	
	public void testGetActualTime(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.getActualTime() == first.getActualTime() + second.getActualTime());
	}
	
	public void testGetTotalSpace(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.getTotalSpace() == first.getSpace() + second.getSpace());
	}
	
	public void testIsDone(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.isDone() == false);
	}
	
	public void testIsProcessing(){
		ord.add(first);
		ord.add(second);
		assertTrue(ord.isProcessing() == false);
	}
	
	public void testNextStage(){
		ord.add(first);
		ord.add(second);
		ord.nextStage();
		assertTrue(first.getOrderTimes().get(0) == 10);
		assertTrue(second.getOrderTimes().get(0) == 5);
	}
	
	public void testSetState(){
		ord.setState("Cooking");
		assertTrue(ord.getState().equals("Cooking"));
	}
	
	public void testGetState(){
		ord.setState("Cooking");
		assertTrue(ord.getState().equals("Cooking"));
	}
}
