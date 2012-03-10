package pds.order;

import java.io.Serializable;
import java.util.*;

import pds.sim.*;

/**
 * @author Goombas
 *
 */

/**
 * Item class: Simulates the existence of an item with basic item properties
 * (name, price, space, toppingable)
 */
public class Item implements Serializable {

	private String name;
	private double price;
	private int space;
	private boolean isDone;
	private boolean isProcessing;
	private boolean toppingable;
	private int actualTime;
	private ArrayList<Integer> orderTimes;
	private ArrayList<Topping> leftSide;
	private ArrayList<Topping> rightSide;

	public Item(String name, double price, int space, boolean toppingable,
			ArrayList<Integer> orderTimes) {
		this.name = name;
		this.price = price;
		this.space = space;
		this.orderTimes = orderTimes;
		this.leftSide = new ArrayList<Topping>();
		this.rightSide = new ArrayList<Topping>();
		this.isDone = false;
		this.isProcessing = false;
		this.toppingable = toppingable;
		actualTime = 0;
	}

	/**
	 * The get name function to return the private name variable of the item
	 * 
	 * @return String name
	 */
	public String getName() {
		return this.name;

	}

	/**
	 * The get price function to return the private price variable of the item
	 * 
	 * @return Double price
	 */
	public double getPrice() {
		return this.price;

	}

	/**
	 * The get space function to return the private space variable of the item
	 * 
	 * @return Integer space
	 */
	public int getSpace() {
		return this.space;
	}

	/**
	 * takes in a topping and adds it into the arraylist called leftside to
	 * simulate the existence of that topping on the left side of the item
	 * 
	 * @param top
	 *            - which is a Topping object
	 */
	public void addToppingLeft(Topping top) {
		if (this.isToppingable()) {
			this.price += top.getPrice();
			this.leftSide.add(top);
		}
	}
	
	public boolean isWhole(){
		int c=0;
		if(this.getLeftSide().size() != this.getRightSide().size()){
			return false;
		}
		for(Topping t: this.getLeftSide()){
			if(!t.equals(this.getRightSide().get(c))){
				return false;
			}
			c++;
		}
		return true;
	}

	/**
	 * gets the arrayList that holds all of the toppings for the left side of
	 * the item
	 * 
	 * @return ArrayList<Topping> leftside
	 */
	public ArrayList<Topping> getLeftSide() {
		return this.leftSide;
	}

	/**
	 * takes in a topping and adds it into the arraylist called rightside to
	 * simulate the existence of that topping on the right side of the item
	 * 
	 * @param top
	 */
	public void addToppingRight(Topping top) {
		if (this.isToppingable()) {
			this.rightSide.add(top);
			this.price += top.getPrice();

		}
	}

	public ArrayList<Integer> getOrderTimes() {
		return orderTimes;
	}

	/**
	 * gets the arraylist that holds all of the toppings for the right side of
	 * the item
	 * 
	 * @return ArrayList<Topping> rightside
	 */
	public ArrayList<Topping> getRightSide() {

		return this.rightSide;
	}

	/**
	 * takes in a topping and adds it to both arraylists leftside and rightside
	 * to simulate the topping on the entire item
	 * 
	 * @param top
	 *            - which is a Topping object
	 */
	public void addTopping(Topping top) {
		if (this.isToppingable()) {
			this.leftSide.add(top);
			this.rightSide.add(top);
		}
	}

	/**
	 * calculates and returns the total time it takes for an item to finish
	 * processing
	 * 
	 * @return Integer total
	 */
	public int getTime() {
		int total = 0;
		for (int i : orderTimes) {
			total += i;
		}
		return total;
	}

	/**
	 * gets the first index in the arraylist ordertimes which acts as the
	 * current timer for the current stage
	 * 
	 * @return Integer
	 */
	public int getCurrentTime() {
		return orderTimes.get(0);
	}

	/**
	 * returns whether or not the item is finished with its current stage
	 * 
	 * @return Boolean isDone
	 */
	public boolean isDone() {
		return this.isDone;
	}

	/**
	 * sets the isProcessing boolean (which tells where or not the item is being
	 * worked on) to the parameter boolean
	 * 
	 * @param isProcess
	 *            - which is a boolean
	 */
	public void setIsProcessing(boolean isProcess) {
		this.isProcessing = isProcess;
	}

	/**
	 * returns whether or not the item is being worked on
	 * 
	 * @return Boolean isProcessing
	 */
	public boolean isProcessing() {
		return this.isProcessing;
	}

	/**
	 * returns whether or not the current item is able to have toppings on it
	 * 
	 * @return Boolean toppingable
	 */
	public boolean isToppingable() {
		return this.toppingable;
	}

	/**
	 * sets the isDone boolean to the parameter boolean to indicate whether or
	 * not the item is finished with its current stage
	 * 
	 * @param isDone
	 *            - which is a boolean
	 */
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}

	/**
	 * gets the actual amount of time the item has been processing
	 * 
	 * @return Integer actualTime
	 */
	public int getActualTime() {
		return actualTime;
	}

	/**
	 * removes the first integer in the orderTimes arrayList to simulate the
	 * next stage's time
	 */
	public void nextStage() {
		orderTimes.remove(0);
	}

	/**
	 * tick method simply is supposed to reduce the processing time for the
	 * stage the item is currently in first checks to see if the current time
	 * has anything left to tick. if not, then the boolean isDone is set to
	 * true. if so, then the tick method simply reduces the time by 1
	 */
	public void tick() {
		if (getCurrentTime() <= 1) {
			isDone = true;
		} else {
			orderTimes.set(0, orderTimes.get(0) - 1);
		}
		actualTime += 1;
	}

	public String toString() {
		String str = "<html>";
		str += "<br>"+ this.name + "......."+this.getPrice()+"</br>";
		if(this.isToppingable()&&1==2){
		str += "<br> Toppings:" + "</br>";
		
		if (this.isWhole()) {
			str += "<br>"+"	Whole: <br>";
			for (Topping t : this.getLeftSide()) {
				str += "<br>	" + t.getName() + "</br>";
			}
		} else {
			str += "<br>	Left: </br>";
			for (Topping t : this.getLeftSide()) {
				str += "<br>	" + t.getName() + "</br>";
			}
			str += "<br>	Right: </br>";
			for (Topping t : this.getRightSide()) {
				str += "<br>	" + t.getName() + "</br>";
			}
		}
		}
		str += "</html>";
		return str;
	}

}
