package pds.order;

import java.util.*;
/**
 * @author Goombas
 *
 */
public class ModifyableItem extends Item {
	
	private ArrayList<Topping> toppings;
	
	public ModifyableItem(String name, double price, int space, boolean toppingable, ArrayList<Integer> orderTimes){
		super(name, price, space,  toppingable,  orderTimes);
		this.toppings = new ArrayList<Topping>();	
	}
	
	public void addTopping(Topping top){
		toppings.add(top);
	}
	
	public void removeTopping(Topping top){
		for (Topping t: toppings){
			toppings.remove(t);
		}
	}
}
