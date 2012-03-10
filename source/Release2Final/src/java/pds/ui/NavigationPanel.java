/**
 * 
 */
package pds.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Rafiqy
 *
 */
public class NavigationPanel extends JPanel {

	private ArrowButton previous;
	private ArrowButton next;
	private JButton orderButton;
	
	
	
	public NavigationPanel(){
		super(new GridLayout(3,0));
		previous = new ArrowButton(SwingConstants.NORTH, 1, 20);
		next = new ArrowButton(SwingConstants.SOUTH, 1, 20);		
		orderButton = new JButton("Place Order");
		add(previous);
		add(next);
		add(orderButton);
	}

	public NavigationPanel(OrderButtonListener order, ForwardArrowListener forward, BackArrowListener back){
		super(new GridLayout(3,0));
		previous = new ArrowButton(SwingConstants.NORTH, 1, 20);
		next = new ArrowButton(SwingConstants.SOUTH, 1, 20);		
		orderButton = new JButton("Place Order");
		orderButton.addActionListener(order);
		next.addActionListener(forward);
		previous.addActionListener(back);
		add(previous);
		add(next);
		add(orderButton);
		previous.setVisible(false);
	}
	
	/**
	 * @param startButton
	 */
	public NavigationPanel(StartOrderListener startButton) {
		super(new GridLayout(3,0));
		previous = new ArrowButton(SwingConstants.NORTH, 1, 20);
		next = new ArrowButton(SwingConstants.SOUTH, 1, 20);
		orderButton = new JButton("Start Order");
		orderButton.addActionListener(startButton);
		add(previous);
		add(next);
		add(orderButton);
		previous.setVisible(false);
		next.setVisible(false);
	}

	/**
	 * @param toppingButton
	 */
	public NavigationPanel(ToppingButtonListener toppingButton) {
		super(new GridLayout(3,0));
		previous = new ArrowButton(SwingConstants.NORTH, 1, 20);
		next = new ArrowButton(SwingConstants.SOUTH, 1, 20);
		orderButton = new JButton("Done Adding Toppings");
		orderButton.addActionListener(toppingButton);
		add(previous);
		add(next);
		add(orderButton);
		previous.setVisible(false);
		next.setVisible(false);
	}

	/**
	 * 
	 */
	public JButton getOrderButton() {
		return orderButton;	
	}	
	
	public void hideForwardButton(){
		next.setVisible(false);
	}

	public void showForwardButton(){
		next.setVisible(true);
	}
	
	public void hideBackButton(){
		previous.setVisible(false);
	}
	
	public void showBackButton(){
		previous.setVisible(true);
	}
}
