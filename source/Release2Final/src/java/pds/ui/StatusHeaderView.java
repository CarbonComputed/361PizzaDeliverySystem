/**
 * 
 */
package pds.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pds.core.Register;
import pds.database.Employee;
import pds.order.Order;

/**
 * @author kmc8206
 *
 */
public class StatusHeaderView extends JPanel implements Observer{
	
	private JLabel orderCount;
	private JLabel orderProfit;
	private Register model;
	private MainGUI gui;
	private JButton pauseButton;
	
	public StatusHeaderView(final MainGUI gui,final Register model, Employee loggedOn){
		this.gui = gui;
		this.model = model;
		model.addObserver(this);
		setLayout(new BorderLayout());
		JPanel buttonBorder = new JPanel(new FlowLayout());
		//JButton openStore = new JButton("Open Store");s
		JLabel userLabel = new JLabel(loggedOn.getName()+"      ");
		userLabel.setFont(new Font(userLabel.getFont().getName(),Font.BOLD,userLabel.getFont().getSize()+1));
		JButton closeStore = new JButton("Close Store");
		pauseButton = new JButton("Pause");
		pauseButton.setBackground(Color.red);
		pauseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(pauseButton.getText().equals("Pause")){
					model.getSimulation().pauseSim();
					pauseButton.setText("Resume");
					pauseButton.setBackground(Color.green);
					

				}
				else{
					model.getSimulation().resumeSim();
					pauseButton.setText("Pause");
					pauseButton.setBackground(Color.red);
				}
			}
			
		});
		closeStore.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					model.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				JOptionPane.showMessageDialog(null, "The Store is now Closed.\nThe program will now exit.");
				gui.closeWindow();
			}
			
		});
		JButton logOut = new JButton("Log Out");
		logOut.addActionListener(new LogOutListener());
		buttonBorder.add(userLabel);
		buttonBorder.add(closeStore);
		buttonBorder.add(pauseButton);
		add(buttonBorder,BorderLayout.WEST);
		//add(logOut, BorderLayout.EAST);
		JPanel statView = new JPanel(new FlowLayout());
		orderCount = new JLabel("Daily Orders: 0");
		orderProfit = new JLabel("Daily Profit: $0.00");
		orderCount.setFont(new Font(orderCount.getFont().getName(),Font.BOLD,orderCount.getFont().getSize()+1));
		orderProfit.setFont(new Font(orderProfit.getFont().getName(),Font.BOLD,orderProfit.getFont().getSize()+1));
		orderCount.setForeground(Color.red);
		statView.add(orderCount);
		statView.add(orderProfit);
		statView.add(new JLabel("     "));
		statView.add(logOut);
		add(statView, BorderLayout.EAST);
		update(null,null);
		
	}
	
	class LogOutListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = JOptionPane.showConfirmDialog(null, "All Current Data will be lost.\nProceed With Logout?");
			if(i == JOptionPane.OK_OPTION){
				model.getEmployeeDatabase().logOut();
			
				gui.closeWindow();
				gui.displayLoginScreen();
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		orderCount.setText("Daily Orders: "+model.getSimulation().getDailyOrders().size());
		double profit = 0;
		for(Order o: model.getSimulation().getDailyOrders()){
			profit += o.getTotalPrice();
		}
		orderProfit.setText("Daily Profit: $"+ profit);
	}
	
}
