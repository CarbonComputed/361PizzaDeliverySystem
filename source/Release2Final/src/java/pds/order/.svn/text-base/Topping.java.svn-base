package pds.order;

import java.io.Serializable;

/**
 * @author Goombas
 *
 */

/**
 * Topping class that creates and manages instances of toppings
 */
public class Topping implements Serializable{

	private String name;
	private double price;
	
	public Topping(String name, double price){
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Gets the name of the topping (ex.Pepperoni)
	 * @return String 
	 */
	public String getName(){
		return this.name;
	
	}
	
	/**
	 * gets the price of the topping 
	 * @return Double 
	 */
	public double getPrice(){
		return this.price;
	}
	
	public boolean equals(Topping t){
		if(t.getName().equals(this.getName())){
			return true;
		}
		return false;
	}
	
}
