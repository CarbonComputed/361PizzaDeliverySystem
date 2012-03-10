package pds.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import pds.core.Register;
import pds.order.Order;

/**
 * 
 * @author kevin
 *
 */
public class LogView extends JPanel implements Observer,Serializable {

    private JLabel titleLabel;
    private JTextArea orderViewArea;
    private JTextArea managerInfo;
    private Register model;
    private JList orderJList;
    private final JFormattedTextField dateStartField;
    private final JFormattedTextField dateEndField;
    JScrollPane scroll2;
    JScrollPane scroll;

    public LogView(final Register model) {
    	this.model = model;
    	model.addObserver(this);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();

        titleLabel = new JLabel("Logging Information");
        // titleLabel.setPreferredSize(new Dimension(600,50));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        String[] tempstuff = { "ALL" };
        JComboBox stageList = new JComboBox(tempstuff);
        stageList.setSelectedIndex(0);

        JComboBox otherList = new JComboBox(tempstuff);
        otherList.setSelectedIndex(0);
        
        JButton findOrders = new JButton("Find Orders");
        
        managerInfo = new JTextArea();
        managerInfo.setEditable(false);
        managerInfo.setFont(new Font(managerInfo.getFont().getName(),Font.PLAIN,managerInfo.getFont().getSize()+8));
        managerInfo.setText(model.getOrderLog().toString());
        
        JLabel dateStart = new JLabel("Date Start:   ");
        JLabel dateEnd = new JLabel("Date End:   ");

         dateStartField = new JFormattedTextField(
				createFormatter("##/##/####"));
        dateEndField = new JFormattedTextField(
				createFormatter("##/##/####"));
        
        dateStartField.setFont(new Font(dateStartField.getFont().getName(),Font.PLAIN,dateStartField.getFont().getSize()+8));
        dateEndField.setFont(new Font(dateEndField.getFont().getName(),Font.PLAIN,dateEndField.getFont().getSize()+8));

        
        dateStartField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        dateEndField.setFocusLostBehavior(JFormattedTextField.COMMIT);
        
        dateStartField.setColumns(7);
        dateEndField.setColumns(7);

        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(dateStart);
        datePanel.add(dateStartField);
        datePanel.add(dateEnd);
        datePanel.add(dateEndField);


        orderJList = new JList();
        orderJList.setListData(model.getOrderLog().toArray());
        orderJList
                .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        orderViewArea = new JTextArea();
        orderViewArea.setEditable(false);
        orderViewArea.setText("");
        orderViewArea.setFont(new Font(orderViewArea.getFont().getName(),Font.PLAIN,orderViewArea.getFont().getSize()+8));

        JScrollPane oscroll = new JScrollPane(orderJList);
        scroll2 = new JScrollPane(managerInfo);
        scroll = new JScrollPane(orderViewArea);
        
        JPanel rightPanel = new JPanel(new GridLayout(2,0));
        rightPanel.add(scroll);
        rightPanel.add(scroll2);
        
        scroll.setBorder(new EmptyBorder(8,8,8,8));
        scroll2.setBorder(new EmptyBorder(8,8,8,8));
        rightPanel.setBorder(new EmptyBorder(8,8,8,8));
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(oscroll,BorderLayout.CENTER);
        
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(datePanel);
        topPanel.add(findOrders);
        
        leftPanel.add(topPanel,BorderLayout.NORTH);
        
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(rightPanel,BorderLayout.CENTER);
        bottom.add(leftPanel,BorderLayout.WEST);
        
        add(bottom,BorderLayout.CENTER);


        /*
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titleLabel, constraints);



        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(datePanel, constraints);
        
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(findOrders, constraints);

        /*
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(otherList, constraints);

        JScrollPane oscroll = new JScrollPane(orderJList);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(oscroll, constraints);

        //otherList.setVisible(false);
        scroll = new JScrollPane(orderViewArea);
       
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.ipady = 5;
        constraints.weighty =  1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(scroll, constraints);
        
        scroll2 = new JScrollPane(managerInfo);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.ipady = 5;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(scroll2, constraints);
*/
        
        
        findOrders.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String pattern = "MM/dd/yyyy";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				
				Date d1 = null;
				Date d2 = null;
				try {
					d1 = format.parse(dateStartField.getText());
					d2 = format.parse(dateEndField.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, "Error Parsing Date");
					
					JOptionPane.showMessageDialog(null, "Error interpreting Date");
					orderJList.setListData(model.getOrderLog().toArray());
					
					return;
				}
				
				
				orderJList.setListData(model.getOrderLog().getOrders("ALL", d1, d2).toArray());
			}
        	
        });
        
        orderJList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(orderJList.getSelectedValue() !=null){
					Order o = (Order) orderJList.getSelectedValue();
					orderViewArea.setText(o.getData());
				}
				scroll.validate();
				scroll2.validate();
				validate();
				//if()
			}
        	
        });
 
        
        //add(mainPanel);
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
    

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		int x = orderJList.getSelectedIndex();
		if(arg instanceof Order){
			Order o2 = (Order) arg;
			if(o2.getState().equals("Delivered")||o2.getState().equals("Deleted")){
				dateStartField.setText("");
				dateEndField.setText("");
				orderJList.setListData(model.getOrderLog().toArray());
				if(x>=0){
					orderJList.setSelectedIndex(x);
				}
			}
			o2.getObservable().addObserver(this);
			return;
		}

	}

}