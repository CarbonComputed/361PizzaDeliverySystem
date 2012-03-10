/**
 * 
 */
package pds.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import pds.order.Item;

/**
 * @author Rafiqy
 * 
 */
@SuppressWarnings("serial")
public class MenuItemsPanel extends JPanel {

	private MenuButtonListener menuButtons;
	private int menuWidth;
	private int menuLength;
	private int startIndex;
	private List<Item> items;

	public MenuItemsPanel(MenuButtonListener menuListener, int length, int width) {
		super(new GridLayout(length, width));

		menuLength = length;
		menuWidth = width;

		menuButtons = menuListener;

		items = new ArrayList<Item>();

		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Medium Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));
		items.add(new Item("Small Pizza", 5.25,7,true,new ArrayList<Integer>()));

		addButtons(items);
	}

	public MenuItemsPanel(ArrayList<Item> itemList, MenuButtonListener menuListener,
			int length, int width) {
		super(new GridLayout(length, width));

		items = itemList;

		menuLength = length;
		menuWidth = width;

		menuButtons = menuListener;

		addButtons(items);

	}

	public void setLength(int width) {
		menuWidth = width;
		setLayout(new GridLayout(menuLength, menuWidth));
		addButtons(items);
	}

	/**
	 * @param items2
	 *            a list of items adds as many buttons as possible to the
	 *            current panel
	 */
	private void addButtons(List<Item> items2) {

		removeAll();
		
		int endIndex = startIndex + menuLength * menuWidth;
		if (endIndex > items2.size()) {
			updateUI();
			GridLayout layout2 = new GridLayout(menuLength, menuWidth);
			setLayout(layout2);
			MenuButton button;
			for (int i = startIndex; i < items2.size(); i++) {
				button = new MenuButton(items.get(i));
				button.setForeground(Color.BLACK);
				button.setBackground(Color.WHITE);
				button.addActionListener(menuButtons);
				add(button);
			}
		}
		
		else{
			updateUI();
			GridLayout layout2 = new GridLayout(menuLength, menuWidth);
			setLayout(layout2);
			MenuButton button;
			for (int i = startIndex; i < endIndex; i++) {
				button = new MenuButton(items.get(i));
				button.setForeground(Color.BLACK);
				button.setBackground(Color.WHITE);
				button.setActionCommand(items.get(i).getName() + " \t ---------- \t "
						+ items.get(i).getPrice());
				button.addActionListener(menuButtons);
				add(button);
			}
			
		}
	}
	
	public void increaseStartIndex(int startIndex2) {
		if (startIndex2 < items.size()) {
			startIndex = startIndex2;
		}
		else{
			return;
		}
	}

	public void decreaseStartIndex(int startIndex2) {
		if (startIndex2 >= 0) {
			startIndex = startIndex2;
		}
		else{
			return;
		}
	}

	public int getGridArea() {

		return menuLength * menuWidth;
	}

	public void nextMenu() {
		int possibleIndex = startIndex + getGridArea();
		increaseStartIndex(possibleIndex);
		addButtons(items);
		validate();
	}

	public void previousMenu() {
		int possibleIndex = startIndex - getGridArea();
		decreaseStartIndex(possibleIndex);
		addButtons(items);
		validate();
	}

	/**
	 * @return
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @return
	 */
	public int getNumOfItems() {
		return items.size();
	}

}