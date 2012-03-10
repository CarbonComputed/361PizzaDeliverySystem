package pds.database;

/**
 * Customer.java
 *
 * Customer class - Represents a customer
 *
 * @author Goombas
 * 
 */

import java.io.Serializable;

import pds.order.Item;

/**
 * 
 * @author kmc8206
 * Represents the customer of the shop. Has a name, location, phone number and a favorite item 
 */
public class Customer implements Serializable {
  
  private String name;
  private Location location;
  private String phoneNumber;
  private Item favoriteItem;
  
  public Customer(String name, Location location, String phoneNumber) { 
     this.name = name;
     this.location = location;
     this.phoneNumber= phoneNumber;
  } 
  
  /**
   * Returns the customer's location 
   * @return Location location 
   */
  public Location getLocation() {
	  return this.location;
  }
  
  /**
   * returns the customer's phone number 
   * @return String phoneNumber
   */
  public String getPhoneNumber() {
	  return this.phoneNumber;
  }
  
  /**
   * gets the name of the customer 
   * @return String name
   */
  public String getName() {
	  return this.name;
  }
  
  /**
   * Sets the customer's favorite item to the indicated item
   * @param i - Item 
   * @throws NullPointerException
   */
  public void setFavoriteItem(Item i) throws NullPointerException{
	  if(i == null){
		 throw new NullPointerException();
	  }
	  this.favoriteItem = i;
	 
  }
  
  /**
   *  Gets the customer's favorite Item 
   * @return Item favoriteItem 
   */
  public Item getFavoriteItem(){
	  return this.favoriteItem;
  }
}
