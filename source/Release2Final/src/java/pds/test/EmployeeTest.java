/**
 * 
 */
package pds.test;
import java.util.*;

import pds.database.Employee;

import junit.framework.TestCase;


/**
 * @author Goombas
 *
 */
public class EmployeeTest extends TestCase{
	
	private Employee emp;
	private Employee manager;
	
	public void setUp(){
		emp = new Employee("user1", "Steve", "pass1", false);
		manager = new Employee("user2", "Kevin", "pass2", true);
	}
	
	public void testGetUserName(){
		assertTrue(emp.getUserName().equals("user1"));
	}
	
	public void testIsManager(){
		assertTrue(emp.isManager() == false);
	}
	
	public void testGetName(){
		assertTrue(emp.getName().equals("Steve"));
	}
	
	public void testGetPassword(){
		assertTrue(emp.getPassword().equals("pass1"));
	}
	
	public void testSetManager(){
		emp.setManager(true);
		assertTrue(emp.isManager() == true);
	}
	
	public void testPromote() throws Exception{
		manager.promote(emp);
		assertTrue(emp.isManager() == true);
		
	}
}
