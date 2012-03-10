/**
 * 
 */
package pds.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import pds.core.Register;
import pds.database.Employee;

/**
 * @author kmc8206
 *
 */
public class MainView extends JPanel {
	
	private TabbedWindow mainTab;
	private StatusHeaderView statusView;
	
	public MainView(MainGUI gui, Register model, Employee loggedOn){
		setLayout(new BorderLayout());
		mainTab = new TabbedWindow(model,loggedOn);
		add(mainTab, BorderLayout.CENTER);
		statusView = new StatusHeaderView(gui, model,loggedOn);
		add(statusView, BorderLayout.NORTH);
		
	}
}
