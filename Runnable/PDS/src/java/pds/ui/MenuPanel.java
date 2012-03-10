package pds.ui;
import pds.core.Register;
import pds.order.Item;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


/**
 *  This is the menuPanel class
 *  This is a holder class for the components that 
 *  make up the menu
 *  it instantiates:
 *  a SideMenuPanel
 *  a MenuItemsPanel and
 *  a MenuButtonListener
 *  
 * 
 */

/**
 * @author Rafiqy
 * 
 */

@SuppressWarnings("serial")
public class MenuPanel extends JPanel{

	
	// creates locations for the panels that compose the
	// menu
	private SideMenuPanel sideMenu;
	private NavigationPanel navigationMenu;
	private NavigationPanel toppingNavMenu;
	private ToppingButtonListener toppingButton;
	private MenuItemsPanel orderMenu;
	private ToppingsPanel toppingMenu;
	private MenuButtonListener menuListener;
	private OrderButtonListener orderListener;
	private ForwardArrowListener forwardArrow;
	private BackArrowListener backArrow;
	@SuppressWarnings("unused")
	private TabbedWindow mainWindow;
	
	
	public MenuPanel(TabbedWindow tabWindow, Register model) {
	
		//sets the layout for the entire menu panel
		super(new BorderLayout());

		mainWindow = tabWindow;
		
		
		// creates a new MenuButtonLisener and set it's menu
		// to this object
		menuListener = new MenuButtonListener();
		menuListener.setMenu(this);
		
		//creates a new OrderButtonListener and sets its menu
		orderListener = new OrderButtonListener();
		orderListener.setMenu(this);
		orderListener.setMainWindow(tabWindow);
		orderListener.setModel(model);
		
		//creates a new topping button listener
		toppingButton = new ToppingButtonListener();
		toppingButton.setMenu(this);
		
		//creates a new forwardArrowListener and sets its menu
		forwardArrow = new ForwardArrowListener();
		forwardArrow.setMenu(this);
		
		//creates a new forwardArrowListener and sets its menu
		backArrow = new BackArrowListener();
		backArrow.setMenu(this);
		
		//Initializes the side menu panel
		sideMenu = new SideMenuPanel();
		
		//Initializes the order Menu panel
		orderMenu = new MenuItemsPanel(model.getMenu().getItemOptions(),menuListener, 3,3);
		
		//Initializes the toppings menu panel
		toppingMenu = new ToppingsPanel(model.getToppingDatabase(), 0, 3);
		
		//Initializes the navigation menu
		navigationMenu = new NavigationPanel(orderListener, forwardArrow, backArrow);
		
		//Initializes the topping navigation menu
		toppingNavMenu = new NavigationPanel(toppingButton);
		
		//adds the menu, sideMenu and the place order button to the main panel
		add(toppingMenu, BorderLayout.CENTER);
		add(orderMenu, BorderLayout.CENTER);
		add(sideMenu, BorderLayout.WEST);
		add(navigationMenu, BorderLayout.EAST);
	}
	
	public MenuPanel(ArrayList<Item> menuItems,
						ArrayList<String> locations){
	
		
		
		//sets the layout for the entire menu panel
		setLayout(new BorderLayout());
		
		//Initializes the side menu panel
		sideMenu = new SideMenuPanel();
		
		//Initializes the order Menu panel
		orderMenu = new MenuItemsPanel(menuItems, menuListener, 5, 5);
		
		//creates a location for the place order button
		//adds the create order button to the panel
		JButton orderButton = new JButton("Place Order");
		JPanel orderButtonArea = new JPanel(new FlowLayout());
		orderButtonArea.add(orderButton);
		JPanel areaToAddOrderButtonArea = new JPanel(new BorderLayout());
		areaToAddOrderButtonArea.add(orderButtonArea, BorderLayout.WEST);
		
		//adds the menu, sideMenu and the place order button to the main panel
		add(orderMenu, BorderLayout.CENTER);
		add(areaToAddOrderButtonArea, BorderLayout.SOUTH);
		add(sideMenu, BorderLayout.WEST);	
		
	}

	/**
	 * @return SideMenuPanel
	 * returns this menu panel's side menu
	 */
	public SideMenuPanel getSidePanel() {
		
		return sideMenu;
	}

	/**
	 * @return orderMenu
	 */
	public MenuItemsPanel getMenuItems() {

		return orderMenu;
	}

	/**
	 * @return navigationMenu
	 */
	public NavigationPanel getNavigationPanel() {

		return navigationMenu;
	}

	/**
	 * @param toppings
	 */
	public void toppingView(){
		remove(orderMenu);
		add(toppingMenu, BorderLayout.CENTER);
		remove(navigationMenu);
		add(toppingNavMenu, BorderLayout.EAST);
		updateUI();
	}
	
	public void menuView(){
		remove(toppingMenu);
		add(orderMenu, BorderLayout.CENTER);
		remove(toppingNavMenu);
		add(navigationMenu, BorderLayout.EAST);
		updateUI();
	}

	/**
	 * @return
	 */
	public ToppingsPanel getToppingPanel() {
		
		return toppingMenu;
	}

}