/**
 * 
 */
package pds.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;

import pds.core.Register;
import pds.database.Customer;
import pds.database.Location;
import pds.order.Item;

/**
 * @author Rafiqy
 * 
 */

public class OrderButtonListener implements ActionListener {

	private JComponent menuP;
	private TabbedWindow mainWindow;
	private Customer currCust;
	private Register model;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuP.getClass().getName() == "pds.ui.MenuPanel") {

			OrderedItemsPanel orderedItems = ((MenuPanel) menuP).getSidePanel()
					.getOrderedItems();
			@SuppressWarnings("unchecked")
			ArrayList<Item> items = new ArrayList<Item>((Collection)orderedItems.getList());
			
			orderedItems.clearList();

			TotalPanel total = ((MenuPanel) menuP).getSidePanel()
					.getTotalPanel();
			total.clearOrder();
			((MenuPanel) menuP).menuView();

			try {
				System.out.println("TEEEEST");
				model.takeOrder(currCust, items);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mainWindow.setComponentAt(0, mainWindow.getCustInfoPanel());
				mainWindow.updateUI();
			
		}
	}
	
	public void setModel(Register model){
		this.model = model;
	}
	
	public void setMenu(MenuPanel menu) {

		menuP = menu;
	}
	

	/**
	 * @param tabWindow
	 */
	public void setMainWindow(TabbedWindow tabWindow) {
		mainWindow = tabWindow;
		
	}

	/**
	 * @param cust
	 */
	public void setCust(Customer cust) {
		currCust = cust;
		
	}
	
	public Customer getCust(){
		return currCust;
	}

}
