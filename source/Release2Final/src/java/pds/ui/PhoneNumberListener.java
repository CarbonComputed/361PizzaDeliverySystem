/**
 * 
 */
package pds.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import pds.database.Customer;
import pds.database.Customers;

/**
 * @author Rafiqy
 * 
 */
public class PhoneNumberListener implements FocusListener {

	CustomerInfoPanel custInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {
		custInfo.custPhoneNumberFieldDoCommit();
		String number = custInfo.getCustPhoneNumber();
		Customers customerDatabase = custInfo.getMainWindow().getRegister()
				.getCustomerDatabase();
		Customer cust = customerDatabase.get(number);
		if(cust != null){
			custInfo.setCustName(cust.getName());
			custInfo.setCustLocation(custInfo.getLocations().indexOf(cust.getLocation()));
			custInfo.validate();
		}
		
	}

	public void setCustInfoPanel(CustomerInfoPanel custPanel) {
		custInfo = custPanel;
	}
}
