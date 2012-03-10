/**
 * 
 */
package pds.ui;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;


/**
 * @author Rafiqy
 *
 */
@SuppressWarnings("serial")
public class SideMenuPanel extends JPanel {
	
	private OrderedItemsPanel orderedItems;
	private TotalPanel total;
	private JScrollPane orderScroll;
	private JScrollPane totalScroll;
	private JLabel orderLabel;
	private JLabel totalLabel;
	
	public SideMenuPanel(){
		
		//sets the layout for the sideMenu
		//places the customerInfo, orderScroll and the totalScroll to the
		//sideMenu

		super(new BorderLayout());
		
		setMinimumSize(getSize());
		
		orderedItems = new OrderedItemsPanel();
		total = new TotalPanel();
		orderScroll = new JScrollPane(orderedItems);
		totalScroll = new JScrollPane(total);
		orderLabel = new JLabel("Ordered Items");
		totalLabel = new JLabel("Order Total & Estimated Delivery Time");
		
		//orderedItems.addListSelectionListener();
		
		orderLabel.setLabelFor(orderScroll);
		totalLabel.setLabelFor(totalScroll);
		
		JPanel tot = new JPanel(new BorderLayout());
		tot.add(totalLabel, BorderLayout.BEFORE_FIRST_LINE);
		tot.add(totalScroll, BorderLayout.AFTER_LAST_LINE);
		
		add(orderLabel, BorderLayout.PAGE_START);
		add(orderScroll, BorderLayout.CENTER);
		add(tot, BorderLayout.AFTER_LAST_LINE);
		
	}
	
	
	
	public OrderedItemsPanel getOrderedItems(){
		
		return orderedItems;
	}


	/**
	 * @return CustomerInfoPanel
	 */
	public TotalPanel getTotalPanel() {
		
		return total;
	}

}

