/**
 * 
 */
package pds.core;

import javax.swing.JOptionPane;

import pds.sim.Simulation;
import pds.ui.MainGUI;

/**
 * @author kevin
 *
 */
public class Main {
	public static void main(String args[]){
		Register register = new Register();
		try {
			register.readDatabases();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
		MainGUI mainWindow = new MainGUI(register);
		mainWindow.init();
	}
}

