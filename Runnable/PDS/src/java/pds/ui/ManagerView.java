package pds.ui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import pds.core.Register;

/**
 * 
 * @author kevin
 *
 */
public class ManagerView extends JTabbedPane {

	
	public ManagerView(Register model){
		addTab("Employee Management",new EmployeeView(model));
		//addTab("Configure Menu", new ConfigMenuView());
	    JComponent logView = new LogView(model);
	    addTab("Logs",logView);
	}
	class MainPanel extends JPanel{
		public MainPanel(){
			setLayout(new GridBagLayout());
			
			JPanel storeActionPanel = new JPanel(new FlowLayout());
			JButton openStoreButton = new JButton("Open Store");
			JButton closeStoreButton = new JButton("Close Store");
			storeActionPanel.add(openStoreButton);
			storeActionPanel.add(closeStoreButton);
			
			
			
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = .5;
			constraints.weighty = .5;
			
			add(storeActionPanel,constraints);
			
			
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			JTextArea infoScreen = new JTextArea();
			infoScreen.setEditable(false);
			add(infoScreen,constraints);
			
		}
	}
	

	

	
}
