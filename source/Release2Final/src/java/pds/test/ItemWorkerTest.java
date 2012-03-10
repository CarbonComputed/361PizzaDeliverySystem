/**
 * 
 */
package pds.test;
import java.util.*;

import junit.framework.TestCase;

import pds.order.Item;
import pds.sim.ItemWorker;
import pds.sim.Worker;

/**
 * @author Goombas
 *
 */
public class ItemWorkerTest extends TestCase{
	
	private Item first;
	private Item second;
	private ItemWorker iw;
	
	public void setUp(){
		ArrayList<Integer> largeList = new ArrayList<Integer>();
		largeList.add(5);
		largeList.add(10);
		ArrayList<Integer> smallList = new ArrayList<Integer>();
		smallList.add(2);
		smallList.add(5);
		first = new Item("LargePizza", 10.5, 10, true, largeList);
		second = new Item("SmallPizza", 5.25, 5, true, smallList);
		iw = new ItemWorker("Oven", 25);
	}
	
	public void testGetJob(){
		assertTrue(iw.getJob().equals("Oven"));
	}
	
	public void testGetTotalSpace(){
		assertTrue(iw.getTotalSpace() == 25);
	}
	
	public void testGetAvailableSpace(){
		iw.process(first);
		iw.process(second);
		assertTrue(iw.getAvailableSpace() == iw.getTotalSpace() - (first.getSpace() + second.getSpace()));
	}
	
	public void testProcess(){
		iw.process(first);
		iw.process(second);
		assertTrue(iw.size() == 2);
	}
	
	public void testGetAvailability(){
		assertTrue(iw.getAvailability(first) == true);
		assertTrue(iw.getAvailability(second) == true);
	}
	
	public void testRemove(){
		iw.process(first);
		iw.process(second);
		assertTrue(iw.size() == 2);
		iw.remove(first);
		assertTrue(iw.size() == 1);
		assertTrue(iw.getAvailableSpace() == 20);
		iw.remove(second);
		assertTrue(iw.size() == 0);
		assertTrue(iw.getAvailableSpace() == 25);
	}
	
	public void testTick(){
		iw.process(first);
		iw.process(second);
		iw.tick();
		assertTrue(first.getActualTime() == 1);
		assertTrue(second.getActualTime() ==1);
	}
}
