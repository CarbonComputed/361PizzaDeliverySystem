/**
 * 
 */
package pds.ui;

/**
 * @author kevin
 *
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import pds.core.Register;
import pds.database.Employee;
import pds.database.EmployeeDatabase;



public class InitialRegistrationView extends JPanel {
	
	
	private Register model;
	
	private MainGUI mainWindow;
	private JTextField userField;
	private Window currentWindow;
	private JTextField nameField;
	private JPasswordField passField;
	private ModifiedObservable viewObservable;
	
	private JCheckBox adminCheck;
	private boolean isComplete;
	

	
	public InitialRegistrationView(MainGUI observer,Register model, JFrame currentWindow){
		
		this.currentWindow = currentWindow;
		isComplete = false;
		this.model = model;
		mainWindow = observer;
		viewObservable = new ModifiedObservable();
		viewObservable.addObserver(observer);
		init();

	}
	
	public InitialRegistrationView(Register model,Window currentWindow){
		viewObservable = new ModifiedObservable();
		this.currentWindow = currentWindow;
		this.model = model;
		isComplete = false;
		init();
	}
	
	private void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel nameLabel = new JLabel("Name: ");
		adminCheck = new JCheckBox();
		JLabel adminCheckLabel = new JLabel("Administrator");
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		this.userField = new JTextField(15);
		this.passField = new JPasswordField(15);
		this.nameField = new JTextField(15);
		JButton loginButton = new JButton("Register");
		passField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					new RegisterListener().actionPerformed(new ActionEvent(this,1,"hello!"));
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JButton cancelButton = new JButton("Cancel");
		JLabel titleLabel = new JLabel("DiPizas PDS First Time Registration");
		constraints.insets = new Insets(10, 10, 10, 10);
		cancelButton.addActionListener(new CancelListener());
		loginButton.addActionListener(new RegisterListener());
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(titleLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(usernameLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(passwordLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(adminCheckLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(nameLabel,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(userField,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(nameField,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(passField,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(adminCheck,constraints);
		
	
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(cancelButton,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(loginButton,constraints);
	}
	public boolean isComplete(){
		return isComplete;
	}
	
	class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			isComplete = true;
			try {
				model.getEmployeeDatabase().register(new Employee(userField.getText(),nameField.getText(),passField.getText(),adminCheck.isSelected()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				isComplete = false;
				JOptionPane.showMessageDialog(null, e.getMessage());
				
			}
			
			viewObservable.setChanged();
			viewObservable.notifyObservers();
			viewObservable.clearChanged();
		
		}
	}
	
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			currentWindow.dispose();
			// TODO Auto-generated method stub
			
		}
		
	}
	


	
}

