package pds.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pds.core.Register;

/**
 * 
 * @author kevin
 *
 */
public class MainGUI implements Observer {
	
	private boolean loggedOn;
	private JFrame mainWindow;
	private LoginPanel loginPanel;
	private Register model;
	private InitialRegistrationView regisView;
	private KitchenConfigView kitchenConfigView;
	private MainView mainView;
	
	public MainGUI(Register model){
		loggedOn = false;
		mainWindow = new JFrame("DiPizzas Pizza Delivery System");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.model = model;
	}
	
	public void init(){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(model.isFirstRun()){
			regisView = new InitialRegistrationView(this,model,this.mainWindow);
			mainWindow.add(regisView);
			mainWindow.pack();
			mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			mainWindow.setVisible(true);
			mainWindow.validate();
		}
		else if(!loggedOn){
			displayLoginScreen();
			
		}
		else{
			displayStoreConfig();
			displayHomeScreen();
		}
	}
	
	
	public void displayStoreConfig(){
		mainWindow.remove(loginPanel);
		kitchenConfigView = new KitchenConfigView(this, model);
		mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainWindow.add(kitchenConfigView);
		mainWindow.validate();
		
		
	}
	
	public void closeWindow(){
		mainWindow.dispose();
	}
	
	public void displayHomeScreen(){
		
		if(model.isOpen()){
			mainWindow.remove(kitchenConfigView);
			mainWindow.remove(loginPanel);
			
		}
		
		//if(mainView == null){
		mainView = new MainView(this, model ,model.getEmployeeDatabase().getLoggedOn());
		mainWindow.add(mainView);
		mainWindow.pack();
		mainWindow.validate();
	}
	
	public void displayLoginScreen(){
		if(regisView != null){
			mainWindow.remove(regisView);
		}
		if(mainView != null){
			mainWindow.remove(mainView);
		}
		
		if(model.isOpen()){
			mainWindow.remove(loginPanel);
		}
		loginPanel = new LoginPanel(this, model.getEmployeeDatabase());
		
		mainWindow.add(loginPanel);
		loginPanel.setVisible(true);
		mainWindow.pack();
		mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainWindow.setVisible(true);
		mainWindow.validate();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		if(model.getEmployeeDatabase().getLoggedOn() != null && arg1 instanceof LoginPanel && !model.isOpen()){
			loggedOn = true;
			displayStoreConfig();
			
		
		}
		else if(model.isOpen() && model.getEmployeeDatabase().getLoggedOn() != null){
			displayHomeScreen();
		}
		else if(model.getEmployeeDatabase().getLoggedOn() == null){
			if(mainView != null){
				mainWindow.remove(mainView);
			}
			displayLoginScreen();
		}
		
	}
	
}
