/**
 * 
 */
package pds.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;

import pds.database.ToppingList;
import pds.order.Item;
import pds.order.Topping;

/**
 * @author Rafiqy
 * 
 */
public class ToppingButtonListener implements ActionListener {

	MenuPanel menuP;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ToppingsPanel toppings = menuP.getToppingPanel();
		if (toppings.getComponentCount() == 2) {
			addToppings(toppings.getCenterButtons());
			resetToppingMenu(toppings.getCenterButtons());
		} else {
			addLeftToppings(toppings.getLeftButtons());
			addRightToppings(toppings.getRightButtons());
			resetToppingMenu(toppings.getLeftButtons());
			resetToppingMenu(toppings.getRightButtons());
		}
		menuP.menuView();
	}

	/**
	 * @param menuPanel
	 */
	public void setMenu(MenuPanel menuPanel) {
		menuP = menuPanel;

	}

	public void resetToppingMenu(ArrayList<JCheckBox> toppings) {
		for (JCheckBox box : toppings) {
			if (box.getText().equalsIgnoreCase("pepperoni")) {
				box.setSelected(true);
			} else {
				box.setSelected(false);
			}
		}
	}

	public void addLeftToppings(ArrayList<JCheckBox> leftToppings) {
		ArrayList<Topping> setToppings = new ArrayList<Topping>();
		ToppingList availToppings = menuP.getToppingPanel().getCurrToppings();
		ArrayList<JCheckBox> selectedToppings = leftToppings;
		for (int i = 0; i < selectedToppings.size(); i++) {
			if (selectedToppings.get(i).isSelected()) {
				setToppings.add(availToppings.get(i));
			}
		}
		int lstLen = menuP.getSidePanel().getOrderedItems().getList().size();
		TotalPanel tot = menuP.getSidePanel().getTotalPanel();
		Item curItem = menuP.getSidePanel().getOrderedItems().getList()
				.get(lstLen - 1);
		for (Topping top : setToppings) {
			curItem.addToppingLeft(top);
			tot.setTotal(tot.getTotal() + top.getPrice());
		}
	}

	public void addRightToppings(ArrayList<JCheckBox> rightToppings) {
		System.out.println("HEERE");
		ArrayList<Topping> setToppings = new ArrayList<Topping>();
		ToppingList availToppings = menuP.getToppingPanel().getCurrToppings();
		ArrayList<JCheckBox> selectedToppings = rightToppings;
		for (int i = 0; i < selectedToppings.size(); i++) {
			if (selectedToppings.get(i).isSelected()) {
				setToppings.add(availToppings.get(i));
			}
		}
		int lstLen = menuP.getSidePanel().getOrderedItems().getList().size();
		TotalPanel tot = menuP.getSidePanel().getTotalPanel();
		Item curItem = menuP.getSidePanel().getOrderedItems().getList()
				.get(lstLen - 1);
		for (Topping top : setToppings) {
			curItem.addToppingRight(top);
			tot.setTotal(tot.getTotal() + tot.getTotal() + top.getPrice());
		}
	}

	public void addToppings(ArrayList<JCheckBox> toppings) {
		
		ArrayList<Topping> setToppings = new ArrayList<Topping>();
		ToppingList availToppings = menuP.getToppingPanel().getCurrToppings();
		ArrayList<JCheckBox> selectedToppings = toppings;
		for (int i = 0; i < selectedToppings.size(); i++) {
			if (selectedToppings.get(i).isSelected()) {
				setToppings.add(availToppings.get(i));
			}
		}
		int lstLen = menuP.getSidePanel().getOrderedItems().getList().size();
		TotalPanel tot = menuP.getSidePanel().getTotalPanel();
		Item curItem = menuP.getSidePanel().getOrderedItems().getList()
				.get(lstLen - 1);
		for (Topping top : setToppings) {
			curItem.addTopping(top);
			tot.setTotal(tot.getTotal() + top.getPrice());
		}
	}
}
