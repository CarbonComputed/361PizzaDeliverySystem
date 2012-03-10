/**
 * 
 */
package pds.sim;

import java.util.concurrent.CopyOnWriteArrayList;

import pds.order.Item;
import pds.order.Order;

/**
 * @author kmc8206
 * Class to act as a certain stage in the order process (ex. Preparation Stage, Cooking Stage, Delivery Stage)
 */
public class Stage extends CopyOnWriteArrayList < Worker > {
	
	private String name;
	private int size;
	private boolean byOrder;
	
	private CopyOnWriteArrayList < Order > waitingQueue;
	private CopyOnWriteArrayList < Order > processingQueue;
	private CopyOnWriteArrayList < Order > finishedQueue;
	
	public Stage(String name, int nWorkers,int size, boolean byOrder){
		this.name = name;
		this.byOrder = byOrder;
		this.size = size;
		waitingQueue = new CopyOnWriteArrayList < Order >();
		finishedQueue = new CopyOnWriteArrayList < Order >();
		processingQueue = new CopyOnWriteArrayList < Order >();
		for(int x=0;x<nWorkers;x++){
			if(byOrder){
				this.add(new OrderWorker(name, size));
			}
			else{
				this.add(new ItemWorker(name, size));
			}
		}
	}
	
	/**
	 * Gets the name of the stage (ex. Cooking Stage)
	 * @return String name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets how much space each worker in the stage can hold
	 * @return Integer size
	 */
	public int getWorkerSize(){
		return size;
	}
	
	/**
	 * Determines whether or not the stage works by order or by item (true if by order, false is by item)
	 * This is the difference between a Chef who works on individual orders at a time and an Oven which works on separate items at once
	 * @return Boolean byOrder
	 */
	public boolean isByOrder(){
		return byOrder;
	}
	
	/**
	 * The stage checks which queue the order is in (waiting queue/processing queue/finished queue), removes it from that queue and sets it's state to "Deleted" so it can either be moved to the next stage by the Simulation or just remain a completed order  
	 * @param o - Order 
	 * @throws Exception
	 */
	public void removeOrder(Order o) throws Exception{
		
		if(this.name.equals("Driver")&& (this.processingQueue.contains(o) || this.waitingQueue.contains(o)|| this.finishedQueue.contains(o))){
			throw new Exception("The Order is in its Delivery Stage");
		}
		if(this.size() > 0){
			if(this.get(0) instanceof ItemWorker){
				removeSameItems(o);
			}
		}

		if(finishedQueue.contains(o)){
			finishedQueue.remove(o);
		}
		if(waitingQueue.contains(o)){
			waitingQueue.remove(o);
		}
		if(processingQueue.contains(o)){
			processingQueue.remove(o);
		}
		for(Worker w:this){
			if(w instanceof OrderWorker){
				OrderWorker ow = (OrderWorker)w;
				if(ow.contains(o)){
					ow.remove(o);
				}
				if(ow.getFinishedQueue().contains(o)){
					ow.remove(o);
				}
			}
		}
		o.setState("Deleted");
	}
	
	
	/**
	 * Adds an order into the stage waiting queue and sets the state to waiting. On the next tick the Simulation will know to move it into the preparation queue if there is an available worker
	 * @param order - Order
	 * @throws NullPointerException
	 */
	public void addOrder(Order order) throws NullPointerException{
		if(order == null){
			throw new NullPointerException("Order is null!");
		}
		waitingQueue.add(order);
		System.out.println("Waiting: "+ name+ " Stage");
		order.setState("Waiting-"+ name+ "-Stage");
		for(Item i:order){
			i.setIsDone(false);
			i.setIsProcessing(false);
		}
		
		
	}
	
	/**
	 * returns the list of finished orders in the stage 
	 * @return CopyOnWriteArrayList<Order> finishedQueue
	 */
	public CopyOnWriteArrayList < Order > getFinishedQueue(){
		return finishedQueue;
	}

	/**
	 * tick method that runs through all of the works and their orders to update the stage times of the items and determine if the items are finished and can move on to the next stage or if they are still processing. Depending on whether or not the worker is an item worker or an order worker, the tick method updates the orders differently. 
	 */
	public void tick(){
		for(Worker worker : this){
			worker.tick();
			
				if(worker instanceof OrderWorker){
					OrderWorker orderWorker = (OrderWorker) worker;
					for(Order ord: this.waitingQueue){
						if(orderWorker.isAvailable()){
					//	if(ord.getTotalSpace() <= orderWorker.getAvailableSpace()){
							orderWorker.add(ord);
							ord.setState("Processing-"+ name+ "-Stage");
							System.out.println("Order has been added to processing: "+ name);
							processingQueue.add(ord);
							waitingQueue.remove(ord);
					//	}
						}
					}
					for(Order ord: orderWorker.getFinishedQueue()){
						finishedQueue.add(ord);
						ord.setState("Finished-"+ name+"-Stage");
						System.out.println("finished ord "+name);
						
						processingQueue.remove(ord);
					}
					orderWorker.getFinishedQueue().clear();
					
					
				}else if(worker instanceof ItemWorker){
						ItemWorker itemWorker = (ItemWorker) worker;
						for(Order ord: this.waitingQueue){
							
							
							for(Item i: ord){
								if(!i.isDone() && !i.isProcessing()&&itemWorker.getAvailability(i)){
									itemWorker.process(i);
									if(!processingQueue.contains(ord)){
										processingQueue.add(ord);
									}
								}
						}
						if(ord.isProcessing()){
							ord.setState("Processing-"+ name+ "-Stage");

							System.out.println(ord.get(0).getName()+"\nOrder has been added to processing: "+ name);

							this.waitingQueue.remove(ord);
						}
					}
					
					for(Order ord : this.processingQueue){
						if(ord.isDone()){
							finishedQueue.add(ord);
							ord.setState("Finished-"+ name+ "-Stage");

							System.out.println("finished item "+name);
							int x = processingQueue.indexOf(ord);
							processingQueue.remove(x);
							for(Order o : finishedQueue){
									removeSameItems(o);
							}
							
						}
					}
				}
			}

		
		for(Order o : finishedQueue){
			System.out.println("Done! "+ name);
			o.nextStage();
		}
	}
	
	/**
	 * Helper function to remove the items from an itemworker when given a certain order. 
	 * @param o -Order
	 */
	public void removeSameItems(Order o){
		for(Item i : o){
			for(Worker w : this){
				if(w instanceof ItemWorker){
					ItemWorker iw = (ItemWorker) w;
					for(Item j: iw){
						if(i.equals(j)){
							iw.remove(j);
							
						}
						
					}
				}
			}
		
			
			
		
	}
	}

}