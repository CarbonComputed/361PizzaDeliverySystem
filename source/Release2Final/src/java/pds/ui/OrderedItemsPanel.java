/**
 * 
 */
package pds.ui;
import java.util.Vector;
import javax.swing.JList;

import pds.order.Item;


/**
 * @author Rafiqy
 *
 */
@SuppressWarnings("serial")
public class OrderedItemsPanel extends JList{

	private Vector<Item> orderedItemsList;
	public OrderedItemsPanel(){
		super();
		orderedItemsList = new Vector<Item>();
	}
	
	public void addToList( Item  ele){
		
		orderedItemsList.add(ele);
	}

	/**
	 * @return
	 */
	public Vector<Item> getList() {
		
		return orderedItemsList;
	}
	
	public void clearList(){
		orderedItemsList.clear();
		setListData(orderedItemsList);
		validate();
	}

}
