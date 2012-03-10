package pds.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import pds.core.Register;
import pds.order.Order;
import pds.sim.Stage;

/**
 * 
 * @author kevin
 *
 */
public class ActiveOrderView extends JPanel implements Observer{
	
	private Register model;
	private ArrayList < String > filters;
	private JList orderJList;
	private JComboBox stageList;
	private JTextArea orderViewArea;
	private JFormattedTextField phoneNumberField;
	
	public ActiveOrderView(final Register model){
		this.model = model;
		filters = new ArrayList < String >();
		filters.add("ALL");
		for(Stage s: model.getSimulation().getStages()){
			filters.add(s.getName().toUpperCase() +"-"+"STAGE");
		}
		filters.add("Delivered");
		filters.add("Deleted");
		phoneNumberField = new JFormattedTextField(createFormatter("(###)-###-####"));
		phoneNumberField.setFocusLostBehavior(JFormattedTextField.COMMIT);
		phoneNumberField.setColumns(13);
		phoneNumberField.setFont(new Font(phoneNumberField.getFont().getName(),Font.PLAIN,phoneNumberField.getFont().getSize()+10));
		JButton findEmployee = new JButton("Find Customer");
		findEmployee.setFont(new Font(findEmployee.getFont().getName(),Font.PLAIN,findEmployee.getFont().getSize()+10));
		
		setLayout(new BorderLayout(20,20));
		JPanel leftPanel = new JPanel(new BorderLayout(20,20));
		JPanel rightPanel = new JPanel(new BorderLayout(20,20));
		
		JPanel above = new JPanel(new FlowLayout());
		
		above.add(phoneNumberField);
		above.add(findEmployee);
		

		
		stageList = new JComboBox(filters.toArray());
		stageList.setFont(new Font(stageList.getFont().getName(),Font.PLAIN,stageList.getFont().getSize()+10));
		
		stageList.setSelectedIndex(0);
		orderJList = new JList();
		orderJList.setListData(model.getSimulation().getDailyOrders().toArray());
		orderJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		orderJList.setPreferredSize(new Dimension(500,800));
		orderJList.setSelectedIndex(0);
		
		JPanel leftleftPanel = new JPanel(new BorderLayout());
		
		JScrollPane scroll1 = new JScrollPane(orderJList);
		
		leftPanel.add(stageList,BorderLayout.NORTH);
		leftPanel.add(scroll1,BorderLayout.CENTER);
		
		leftleftPanel.add(above, BorderLayout.NORTH);
		leftleftPanel.add(leftPanel, BorderLayout.CENTER);
		
		orderViewArea = new JTextArea();
		orderViewArea.setEditable(false);
		orderViewArea.setText("");
		orderViewArea.setFont(new Font(orderViewArea.getFont().getName(),Font.PLAIN,orderViewArea.getFont().getSize()+10));

		JPanel buttonArea = new JPanel(new GridLayout(1,2));
		JButton editOrderButton = new JButton("Edit");
		JButton removeOrderButton = new JButton("Remove");
		editOrderButton.setFont(new Font(editOrderButton.getFont().getName(),Font.BOLD,editOrderButton.getFont().getSize()+12));
		removeOrderButton.setFont(new Font(removeOrderButton.getFont().getName(),Font.BOLD,removeOrderButton.getFont().getSize()+12));
		//buttonArea.add(editOrderButton);
		buttonArea.add(removeOrderButton);
		
		JLabel orderDataLabel = new JLabel("Order Data");
		
		rightPanel.add(orderDataLabel,BorderLayout.NORTH);
		JScrollPane scr = new JScrollPane(orderViewArea);
		rightPanel.add(scr,BorderLayout.CENTER);
		rightPanel.add(buttonArea, BorderLayout.SOUTH);
		
		//leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		leftleftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		rightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		final JPanel southPanel = new JPanel();
		if(orderJList.getSelectedValue() != null){
			Order o = (Order) orderJList.getSelectedValue();
			orderViewArea.setText(o.getData());
		}
		removeOrderButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(orderJList.getSelectedValue() != null){
					try {
						model.getSimulation().removeOrder((Order)orderJList.getSelectedValue());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}
				}
				
				JOptionPane.showMessageDialog(null, "The Order Has been removed");
			}
			
		});
		editOrderButton.addActionListener(new ActionListener(){
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				southPanel.setVisible(true);
				validate();
			}
			
		});
		
		orderJList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(orderJList.getSelectedValue() != null){
					Order o = (Order) orderJList.getSelectedValue();
					orderViewArea.setText(o.getData());
				}
			}
			
		});
		
		findEmployee.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String phonenumber = phoneNumberField.getText();
				phonenumber = phonenumber.replaceAll("-", "");
				phonenumber = phonenumber.replaceAll("\\(", "");
				phonenumber = phonenumber.replaceAll("\\)", "");
				phonenumber = phonenumber.trim();
				if(phonenumber.length()!=10&&phonenumber.length()!=0){
					
					JOptionPane.showMessageDialog(null, "Invalid Phone Number");
					phoneNumberField.setText("");
					phoneNumberField.requestFocus();
					return;
				}
				
				try {
					orderJList.setListData(model.findCustomerOrder(phonenumber,(String) stageList.getSelectedItem()).toArray());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(null, e.getMessage());
					phoneNumberField.setText("");
					phoneNumberField.requestFocus();
					return;
				}
				JOptionPane.showMessageDialog(null, "Order(s) Found!");
				orderJList.setSelectedIndex(0);
				Order o = (Order) orderJList.getSelectedValue();
				if(o != null){
					orderViewArea.setText(o.getData());
				}
			}
			
		});
		
		
		JPanel mainContainer = new JPanel(new BorderLayout());
		
		mainContainer.add(leftleftPanel,BorderLayout.WEST);
		mainContainer.add(rightPanel,BorderLayout.CENTER);
		
		JLabel activeOrderLabel = new JLabel("Active Orders");
		add(activeOrderLabel,BorderLayout.NORTH);
		add(mainContainer,BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//southPanel.add(new MenuPanel());
		add(southPanel,BorderLayout.SOUTH);
		southPanel.setVisible(false);
		model.addObserver(this);

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 instanceof Order){
			Order o = (Order) arg1;
			o.getObservable().addObserver(this);
		}
		int y = orderJList.getSelectedIndex();
		if(orderJList.getSelectedValue() != null){
			Order o = (Order) orderJList.getSelectedValue();
			orderViewArea.setText(o.getData());
		}
		orderJList.setListData(model.getSimulation().getDailyOrders().toArray());
		orderJList.setSelectedIndex(y);

//		/orderJList.validate();
	}
	
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}
	
	
	

	                                  

}
