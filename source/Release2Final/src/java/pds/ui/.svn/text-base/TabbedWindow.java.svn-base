

/**
 * 
 */
package pds.ui;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import pds.core.Register;
import pds.database.Employee;
import pds.database.LocationDatabase;


/**
 * @author Rafiqy
 *
 */
@SuppressWarnings("serial")
public class TabbedWindow extends JTabbedPane {
	
	JComponent custInfo;
	JComponent menuPanel;
	Register reg;


	
	public TabbedWindow(Register model, Employee user){
		
		reg = model;
		custInfo = new CustomerInfoPanel(model.getLocationDatabase(), this);
		menuPanel = new MenuPanel(this,model);
		
	    addTab("Take Order", custInfo);
		JComponent activeOrderPanel = new ActiveOrderView(model);
	    addTab("View Daily Orders", activeOrderPanel);
		JComponent managerView = new ManagerView(model);
		if(model.getEmployeeDatabase().getLoggedOn().isManager()){
			addTab("Manager Actions", managerView);
		}
	    
	}


	/**
	 * @return
	 */
	public JComponent getCustInfoPanel() {
		
		return custInfo;
	}


	/**
	 * @return
	 */
	public JComponent getMenuPanel() {
		
		return menuPanel;
	}


	public Register getRegister(){
		return reg;
	}

}
