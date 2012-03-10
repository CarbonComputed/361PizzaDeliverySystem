package pds.database;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author kmc8206
 * Class to represent the employee (cashier/manager)
 */
public class Employee implements Serializable {
	
	private boolean isManager;
	private String name;
	private String password;
	private String userName;
	private Date dateAdded;
	
	public Employee(String userName, String name, String password, boolean isManager){
		this.name = name;
		this.password = password;
		this.isManager = isManager;
		this.userName = userName;
		dateAdded = new Date();
	}
	
	/**
	 * Gets the user name of the employee (used for logging into the system)
	 * @return String userName
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * A boolean to see if the employee is a manager or just a regular employee (managers have special rights)
	 * @return Boolean isManager
	 */
	public boolean isManager(){
		return isManager;
	}
	
	/**
	 * gets the name of the employee
	 * @return String name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * gets the date the employee was added to the system  
	 * @return Date dateAdded
	 */
	public Date getDateAdded(){
		return dateAdded;
	}
	
	/**
	 * Gets the employee's password (used for logging into the system)
	 * @return String password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Sets the manager boolean to the indicated boolean (employee will be either manager or not manager) 
	 * @param isManager - Boolean 
	 */
	public void setManager(boolean isManager){
		this.isManager = isManager;
	}
	
	
	/**
	 * promotes an employee to a manager changing their manager boolean to true 
	 * @param e Employee
	 * @throws Exception
	 */
	public void promote(Employee e) throws Exception{
		if(!this.isManager){
			throw new Exception("A Non-Manager cannot promote to Manager");
		}
		e.setManager(true);
		
	}
	
	/**
	 * Gets the employee information (user name, real name, if they are a manager, and when they were hired/added to system)
	 * @return String 
	 */
	public String getData(){
		String str = "";
		str += "UserName: "+userName+"\n";
		str += "Name: " + name + "\n";
		str += "Admin: " + isManager +"\n";
		str += "Date Hired: "+ dateAdded.toGMTString();
		return str;
		
	}
	
	/**
	 * simple toString to make printing out the employee information easier
	 */
	public String toString(){
		String str="";
		str += "<html>";
		str += name+"<br>"+"UserName: "+this.userName ;
		str += "</html>";
		return str;
	}
	
	
}
