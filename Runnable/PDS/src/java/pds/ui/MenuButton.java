/**
 * 
 */
package pds.ui;

import javax.swing.JButton;

import pds.order.Item;

/**
 * @author Rafiqy
 *
 */
@SuppressWarnings("serial")
public class MenuButton extends JButton {

	private Item buttonItem;
	
	MenuButton(Item item1){
		super(item1.getName());
		buttonItem = item1;
	}
	
	public Item getButtonItem(){
		return buttonItem;
	}
}
