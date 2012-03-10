/**
 * 
 */
package pds.test;
import java.util.*;

import junit.framework.TestCase;

import pds.database.Customer;
import pds.database.Location;
import pds.order.Item;


/**
 * @author Goombas
 *
 */
public class CustomerTest extends TestCase{
	
	private Location local;
	private Customer cust;
	private Item pizza;
	
	public void setUp(){
		ArrayList<Integer> pizzaList = new ArrayList<Integer>();
		pizzaList.add(2);
		pizzaList.add(5);
		pizza = new Item("Pizza", 5,5,true, pizzaList);
		local = new Location("RIT", 25); 
		cust = new Customer("Steve", local, "2018190965");
	}
	
	public void testGetLocation(){
		assertTrue(cust.getLocation().equals(local));
	}
	
	public void testGetPhoneNumber(){
		assertTrue(cust.getPhoneNumber().equals("2018190965"));
	}
	
	public void testGetName(){
		assertTrue(cust.getName().equals("Steve"));
	}
	
	public void testSetFavoriteItem(){
		cust.setFavoriteItem(pizza);
		assertTrue(cust.getFavoriteItem().equals(pizza));
	}
	
	public void testGetFavoriteItem(){
		cust.setFavoriteItem(pizza);
		assertTrue(cust.getFavoriteItem().equals(pizza));
	}
}
