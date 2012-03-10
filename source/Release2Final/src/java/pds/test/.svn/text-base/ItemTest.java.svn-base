package pds.test;
import java.util.*;

import junit.framework.TestCase;
import pds.order.Item;
import pds.order.Topping;
/**
 * @author Goombas
 *
 */
public class ItemTest extends TestCase{
	
	private ArrayList<Integer> largeList;
	private ArrayList<Integer> smallList;
	private ArrayList<Integer> logList;
	private Item largePizza;
	private Item smallPizza;
	private Item pizzaLogs;
	private String lName = "LargePizza";
	private String sName = "SmallPizza";
	private String logName = "PizzaLogs";
	private Topping top;
	
	public void setUp(){
	ArrayList<Integer> largeList = new ArrayList<Integer>();
	largeList.add(5);
	largeList.add(10);
	largePizza = new Item(lName, 10.50, 10, true, largeList);
	ArrayList<Integer> smallList = new ArrayList<Integer>();
	smallList.add(2);
	smallList.add(5);
	smallPizza = new Item(sName, 5.25, 5, true, smallList);
	ArrayList<Integer> logList = new ArrayList<Integer>();
	logList.add(0);
	logList.add(5);
	pizzaLogs = new Item(logName, 3.50, 3, false, logList);
	top = new Topping("Pepperoni", 1.25);
	}
	
	public void testGetName(){
		assertTrue(largePizza.getName().equals(lName));
		assertTrue(smallPizza.getName().equals(sName));
		assertTrue(pizzaLogs.getName().equals(logName));
	}
	
	public void testGetPrice(){
		assertTrue(largePizza.getPrice() == 10.50);
		assertTrue(smallPizza.getPrice() == 5.25);
		assertTrue(pizzaLogs.getPrice() == 3.50);
	}
	
	public void testGetSpace(){
		assertTrue(largePizza.getSpace() == 10);
		assertTrue(smallPizza.getSpace() == 5);
		assertTrue(pizzaLogs.getSpace() == 3);
	}
	
	public void testAddToppingLeft(){
		largePizza.addToppingLeft(top);
		smallPizza.addToppingLeft(top);
		pizzaLogs.addToppingLeft(top);
		assertTrue(largePizza.getLeftSide().get(0) == top);
		assertTrue(smallPizza.getLeftSide().get(0) == top);
		assertTrue(pizzaLogs.getLeftSide().size() == 0);
	}
	
	public void testAddToppingRight(){
		largePizza.addToppingRight(top);
		smallPizza.addToppingRight(top);
		pizzaLogs.addToppingRight(top);
		assertTrue(largePizza.getRightSide().get(0) == top);
		assertTrue(smallPizza.getRightSide().get(0) == top);
		assertTrue(pizzaLogs.getRightSide().size() == 0);
	}
	
	public void testGetLeftSide(){
		largePizza.addToppingLeft(top);
		smallPizza.addToppingLeft(top);
		pizzaLogs.addToppingLeft(top);
		assertTrue(largePizza.getLeftSide().get(0) == top);
		assertTrue(smallPizza.getLeftSide().get(0) == top);
		assertTrue(pizzaLogs.getLeftSide().size() == 0);
	}
	
	public void testGetRightSide(){
		largePizza.addToppingRight(top);
		smallPizza.addToppingRight(top);
		pizzaLogs.addToppingRight(top);
		assertTrue(largePizza.getRightSide().get(0) == top);
		assertTrue(smallPizza.getRightSide().get(0) == top);
		assertTrue(pizzaLogs.getRightSide().size() == 0);
	}
	
	public void testAddTopping(){
		largePizza.addToppingLeft(top);
		smallPizza.addToppingLeft(top);
		pizzaLogs.addToppingLeft(top);
		largePizza.addToppingRight(top);
		smallPizza.addToppingRight(top);
		pizzaLogs.addToppingRight(top);
		assertTrue(largePizza.getRightSide().get(0) == top);
		assertTrue(smallPizza.getRightSide().get(0) == top);
		assertTrue(pizzaLogs.getRightSide().size() == 0);
		assertTrue(largePizza.getLeftSide().get(0) == top);
		assertTrue(smallPizza.getLeftSide().get(0) == top);
		assertTrue(pizzaLogs.getLeftSide().size() == 0);
	}
	
	public void testGetTime(){
		assertTrue(largePizza.getTime() == 15);
		assertTrue(smallPizza.getTime() == 7);
		assertTrue(pizzaLogs.getTime() == 5);
	}
	
	public void testGetCurrentTime(){
		assertTrue(largePizza.getCurrentTime() == 5);
		assertTrue(smallPizza.getCurrentTime() == 2);
		assertTrue(pizzaLogs.getCurrentTime() == 0);	
	}
	
	public void testIsDone(){
		assertTrue(largePizza.isDone() == false);
		assertTrue(smallPizza.isDone() == false);
		assertTrue(pizzaLogs.isDone() == false);
		
	}
	
	public void testSetIsProcessing(){
		largePizza.setIsProcessing(true);
		smallPizza.setIsProcessing(true);
		pizzaLogs.setIsProcessing(true);
		assertTrue(largePizza.isProcessing() == true);
		assertTrue(smallPizza.isProcessing() == true);
		assertTrue(pizzaLogs.isProcessing() == true);
	}
	
	public void testIsProcessing(){
		largePizza.setIsProcessing(false);
		smallPizza.setIsProcessing(false);
		pizzaLogs.setIsProcessing(false);
		assertTrue(largePizza.isProcessing() == false);
		assertTrue(smallPizza.isProcessing() == false);
		assertTrue(pizzaLogs.isProcessing() == false);
	}
	
	public void testIsToppingable(){
		assertTrue(largePizza.isToppingable() == true);
		assertTrue(smallPizza.isToppingable() == true);
		assertTrue(pizzaLogs.isToppingable() == false);
	}
	
	public void testSetIsDone(){
		largePizza.setIsDone(true);
		smallPizza.setIsDone(true);
		pizzaLogs.setIsDone(true);
		assertTrue(largePizza.isDone() == true);
		assertTrue(smallPizza.isDone() == true);
		assertTrue(pizzaLogs.isDone() == true);
	}
	
	public void testGetActualTime(){
		assertTrue(largePizza.getActualTime() == 0);
		assertTrue(smallPizza.getActualTime() == 0);
		assertTrue(pizzaLogs.getActualTime() == 0);
	}
	
	public void testNextStage(){
		largePizza.nextStage();
		smallPizza.nextStage();
		pizzaLogs.nextStage();
		assertTrue(largePizza.getCurrentTime() == 10);
		assertTrue(smallPizza.getCurrentTime() == 5);
		assertTrue(pizzaLogs.getCurrentTime() == 5);
	}
	
	public void testTick(){
		largePizza.tick();
		smallPizza.tick();
		pizzaLogs.tick();
		assertTrue(largePizza.getActualTime() == 1);
		assertTrue(smallPizza.getActualTime() == 1);
		assertTrue(pizzaLogs.getActualTime() == 1);
		
	}
}
