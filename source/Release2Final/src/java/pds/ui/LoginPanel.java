package pds.ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import pds.database.Employee;
import pds.database.EmployeeDatabase;


/**
 * 
 * @author kevin
 *
 */
public class LoginPanel extends JPanel {
	
	
	private EmployeeDatabase model;
	
	private Employee user;
	
	private MainGUI mainWindow;
	private JTextField userField;
	private JPasswordField passField;
	private ModifiedObservable viewObservable;
	private LoginPanel thisInstance;
	
	public LoginPanel(MainGUI observer,EmployeeDatabase model){
		thisInstance =this;
		user = null;
		this.model = model;
		mainWindow = observer;
		viewObservable = new ModifiedObservable();
		viewObservable.addObserver(observer);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		this.userField = new JTextField(15);
		this.passField = new JPasswordField(15);
		JButton loginButton = new JButton("Login");
		passField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					new LoginListener().actionPerformed(new ActionEvent(this,1,"hello!"));
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
		JLabel titleLabel = new JLabel("DiPizza's PDS");
		constraints.insets = new Insets(10, 10, 10, 10);
		cancelButton.addActionListener(new CancelListener());
		loginButton.addActionListener(new LoginListener());
		
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
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(passwordLabel,constraints);
		
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
		this.add(passField,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(cancelButton,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(loginButton,constraints);
		userField.requestFocus();
		userField.requestDefaultFocus();
	}
	
	public Employee getUser(){
		return user;
	}
	
	public void setVisible(boolean aFlag){
		super.setVisible(aFlag);
		this.userField.setText("");
		this.passField.setText("");
	}
	
	class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				user = model.login(userField.getText(), passField.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				userField.setText("");
				passField.setText("");
				userField.requestFocus();
				return;
			}
			viewObservable.setChanged();
			viewObservable.notifyObservers(thisInstance);
			viewObservable.clearChanged();
			user = null;
		}
		
		
	}
	
	class CancelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			mainWindow.closeWindow();
			// TODO Auto-generated method stub
			//window.dispose();
		}
		
	}
	


	
}
