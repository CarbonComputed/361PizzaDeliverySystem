/**
 * 
 */
package pds.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import pds.database.Customer;
import pds.database.Location;

/**
 * @author Rafiqy
 * 
 */
public class StartOrderListener implements ActionListener {

	private JComponent custInfo;
	private TabbedWindow mainWindow;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (custInfo.getClass().getName() == "pds.ui.CustomerInfoPanel") {
			if (((CustomerInfoPanel) custInfo).getCustPhoneNumber().length() == 10
					&& !((CustomerInfoPanel) custInfo).getCustPhoneNumber()
							.endsWith(" ")) {
				if (!((CustomerInfoPanel) custInfo).getCustName().startsWith(
						" ")) {
					if (((CustomerInfoPanel) custInfo).getCustLocationID() != 0) {
						String number = ((CustomerInfoPanel) custInfo)
								.getCustPhoneNumber();
						String name = ((CustomerInfoPanel) custInfo)
								.getCustName();
						Location loc = ((CustomerInfoPanel) custInfo)
								.getCustLocation();
						Customer cust = mainWindow.getRegister()
								.getCustomerDatabase().get(number);
						if (cust == null) {
							cust = new Customer(name, loc, number);
							mainWindow.getRegister().getCustomerDatabase()
									.put(number, cust);
						}
						try {
							ActionListener ordListener = ((MenuPanel) mainWindow
									.getMenuPanel()).getNavigationPanel()
									.getOrderButton().getActionListeners()[0];
							((OrderButtonListener) ordListener).setCust(cust);
						} catch (Exception e) {

						}
						((CustomerInfoPanel) custInfo).clearFields();
						mainWindow.setComponentAt(0, mainWindow.getMenuPanel());
						mainWindow.updateUI();
					} else {
						((CustomerInfoPanel) custInfo).clearFields();
						JOptionPane.showMessageDialog(null,
								"No Location Selected");
					}
				} else {
					((CustomerInfoPanel) custInfo).clearFields();
					JOptionPane.showMessageDialog(null, "Invalid Name");
				}
			} else {
				((CustomerInfoPanel) custInfo).clearFields();
				JOptionPane.showMessageDialog(null, "Invalid Phone Number");
			}

		}
	}

	/**
	 * @param customerInfoPanel
	 *            sets the customer info panel to this listener cust info panel
	 */
	public void setMenu(CustomerInfoPanel customerInfoPanel) {
		custInfo = customerInfoPanel;

	}

	/**
	 * @param tabWindow
	 */
	public void setMainWindow(TabbedWindow tabWindow) {
		mainWindow = tabWindow;

	}

}
