/**
 * 
 */
package pds.sim;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import pds.database.Customer;
import pds.database.Employee;
import pds.database.Location;
import pds.database.MenuDatabase;
import pds.database.StageDatabase;
import pds.order.Item;
import pds.order.Order;


/**
 * @author kmc8206
 *
 */
public class Simulation extends Thread {
	
	private CopyOnWriteArrayList<Stage> stages;
	private CopyOnWriteArrayList<Order> dailyOrders;
	private int simulationSpeed;
	private state STATE;
	
	private enum state{PAUSED,ACTIVE};
	
	public Simulation(int simulationSpeed){
		super();
		stages = new CopyOnWriteArrayList<Stage>();
		dailyOrders = new CopyOnWriteArrayList<Order>();
		this.simulationSpeed = simulationSpeed;
		this.STATE = state.ACTIVE;
	}
	
	public void addOrder(Order order) throws NullPointerException{
		stages.get(0).addOrder(order);
		dailyOrders.add(order);
	}
	
	public void removeOrder(Order order) throws Exception{
		if(order.getState().equals("Delivered")){
			throw new Exception("The Order has already been Delivered");
		}
		for(Stage stage: stages){
			stage.removeOrder(order);
		}
		//dailyOrders.remove(order);
	}
	
	public CopyOnWriteArrayList<Order> getDailyOrders(){
		return dailyOrders;
	}
	
	
	public void addStage(Stage stage) throws NullPointerException{
		if(stage == null){
			throw new NullPointerException("Stage is null!");
		}
		stages.add(stage);
	}
	
	
	public void run(){
		while(true){
			if(STATE == state.ACTIVE){
			int i = 0;
			for(Stage stage: stages){
				stage.tick();
				for(Order order: stage.getFinishedQueue()){
					if(i == stages.size()-1){
						//TODO Delivered!
						order.setState("Delivered");
						System.out.println(order.getActualTime());

						System.out.println("Delivered!");
					}
					else{
						System.out.println();
						System.out.println(order);
						System.out.println();
						stages.get(i+1).addOrder(order);
					}
				
				}
				stage.getFinishedQueue().clear();
				//System.out.println(stage.getFinishedQueue().size());
				
				i++;
			}
			}
			try {
				this.sleep(simulationSpeed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				return;
			}
		}
		
	}
	
	public CopyOnWriteArrayList<Stage> getStages(){
		return stages;
	}
	
	public CopyOnWriteArrayList<Order> getDailyOrders(String filter){
		CopyOnWriteArrayList<Order> filterOrders = new CopyOnWriteArrayList<Order>();
		for(Order o:this.dailyOrders){
			if(o.getState().contains(filter)){
				filterOrders.add(o);
			}
		}
		return filterOrders;
	}
	
	public Stage getStage(int index){
		return stages.get(index);
	}
	
	public void pauseSim(){
		STATE = state.PAUSED;
	}
	
	public void resumeSim(){
		STATE = state.ACTIVE;
	}
	

	
	public void test(){
		Location loc = new Location("RIT",18);
		Customer customer = new Customer("kevin", loc, "8884442222");
		Employee emp = new Employee("dipizza","dipizza","password",true);
		Order order = new Order(customer, emp);
		
		ArrayList<Integer> orderTimes = new ArrayList<Integer>();
		orderTimes.add(9);
		orderTimes.add(8);
		orderTimes.add(7);
		orderTimes.add(3);
		//orderTimes.add(customer.getLocation().getDistance());
		System.out.println(stages.get(1).size());
		ArrayList<Integer> orderTimes2 = new ArrayList<Integer>();
		orderTimes2.add(5);
		orderTimes2.add(4);
		orderTimes2.add(3);
		orderTimes2.add(4);
		orderTimes2.add(customer.getLocation().getDistance());

		Item i = new Item("Small Pizza",5.5,5, true, orderTimes);

		Item i2 = new Item("Big Pizza",9,2, true, orderTimes2);
		
		ArrayList<Integer> orderTimes3 = new ArrayList<Integer>();
		orderTimes3.add(2);
		orderTimes3.add(3);
		orderTimes3.add(2);
		orderTimes3.add(8);
		Item i3 = new Item("Pizza Logs", 2, 3, false,orderTimes3);
		for(Stage s: stages){
			System.out.println(s.size());
		}
	//	order.addAll(menuDb.getItemOptions());
		//order.add(i3);
		//Order order2 = new Order(customer, emp);
		//order.add(i2);

		order.add(i);
	/*
		s.addStage(prep);
		s.addStage(oven);
		s.addStage(glazing);
		s.addStage(delivery);
		*/
		addOrder(order);
	}
	
	public static void main(String args[]){
		Simulation s = new Simulation(50);
		
		Stage prep = new Stage("Chef",2,90,true);
		Stage oven = new Stage("Oven",2,50,false);
		Stage glazing = new Stage("Glazing",1,50,true);
		Stage delivery = new Stage("Driver",1,50,true);
		
		Location loc = new Location("RIT",18);
		Customer customer = new Customer("kevin", loc, "8884442222");
		Employee emp = new Employee("dipizza","dipizza","password",true);
		Order order = new Order(customer, emp);
		
		ArrayList<Integer> orderTimes = new ArrayList<Integer>();
		orderTimes.add(9);
		orderTimes.add(8);
		orderTimes.add(7);
	//	orderTimes.add(3);
		orderTimes.add(customer.getLocation().getDistance());
		ArrayList<Integer> orderTimes2 = new ArrayList<Integer>();
		orderTimes2.add(5);
		orderTimes2.add(4);
		orderTimes2.add(3);
		orderTimes2.add(4);
		orderTimes2.add(customer.getLocation().getDistance());

		Item i = new Item("Small Pizza",5.5,5, true, orderTimes);

		Item i2 = new Item("Big Pizza",9,2, true, orderTimes2);
		
		ArrayList<Integer> orderTimes3 = new ArrayList<Integer>();
		orderTimes3.add(2);
		orderTimes3.add(3);
		orderTimes3.add(2);
		orderTimes3.add(8);
		Item i3 = new Item("Pizza Logs", 2, 3, false,orderTimes3);
		
	//	order.addAll(menuDb.getItemOptions());
	//	order.add(i3);
		//Order order2 = new Order(customer, emp);
		//order.add(i2);

		order.add(i);
	
		s.addStage(prep);
		s.addStage(oven);
		s.addStage(glazing);
		s.addStage(delivery);
		
		s.addOrder(order);
		//System.out.println(order.getDateTaken().toGMTString());
		//s.addOrder(order2);
		s.start();
	//	try {
		//	s.removeOrder(order);
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		System.out.println(order.getState());
		
	}
	
}
