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
public class BackArrowListener implements ActionListener {

	MenuPanel menuP;
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuItemsPanel items = menuP.getMenuItems();
		NavigationPanel nav = menuP.getNavigationPanel();
		items.previousMenu();
		buttonVisibility(items, nav);
	}

	/**
	 * @param menuPanel
	 */
	public void setMenu(MenuPanel menuPanel) {
		menuP = menuPanel;
		
	}
	
	public void buttonVisibility(MenuItemsPanel itemsMenu, NavigationPanel nav){
		
		if(itemsMenu.getStartIndex() == 0){
			nav.hideBackButton();
		}
		else{
			nav.showBackButton();
		}
		
		if(itemsMenu.getStartIndex() + itemsMenu.getGridArea() > itemsMenu.getNumOfItems()){
			nav.hideForwardButton();
		}
		
		else{
			nav.showForwardButton();
		}
	}

}
