/**
 * 
 */
package pds.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rafiqy
 *
 */
public class MenuButtonListener implements ActionListener {
	
	private MenuPanel menuP;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuButton button1 = (MenuButton) e.getSource();
		OrderedItemsPanel orderedItems =  menuP.getSidePanel().getOrderedItems();
		orderedItems.addToList(button1.getButtonItem());
		orderedItems.setListData(orderedItems.getList());
		
		TotalPanel total = menuP.getSidePanel().getTotalPanel();
		total.setTotal(total.getTotal() + button1.getButtonItem().getPrice());
		
		if(button1.getButtonItem().isToppingable()){
			menuP.toppingView();
		}

	}
	
	public void setMenu( MenuPanel menu){
	
		menuP = menu;
	}
}
