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
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pds.core.Register;
import pds.database.Employee;

/**
 * 
 * @author kevin
 *
 */
public class EmployeeView extends JPanel implements Observer{
	
	private Register model;
	private JList employeeJList;
	private JTextArea employeeViewArea;
	private InitialRegistrationView initReg;
	private JDialog view;
	
	public EmployeeView(final Register model){
		setLayout(new BorderLayout(20,20));
		this.model = model;
		view = new JDialog();
		view.setModal(true);
		model.getEmployeeDatabase().getObservable().addObserver(this);
		JPanel leftPanel = new JPanel(new BorderLayout(20,20));
		JPanel rightPanel = new JPanel(new BorderLayout(20,20));
		List s = new ArrayList();
		s.add("test1");
		s.add("test2");
		employeeJList = new JList();
		employeeJList.setListData(model.getEmployeeDatabase().toArray());
		employeeJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		employeeJList.setPreferredSize(new Dimension(500,800));
		
		employeeJList.setSelectedIndex(0);
		JScrollPane one = new JScrollPane(employeeJList);
		//leftPanel.add(stageList,BorderLayout.NORTH);
		leftPanel.add(one,BorderLayout.CENTER);
		
		employeeViewArea = new JTextArea();
		employeeViewArea.setEditable(false);
		employeeViewArea.setText(model.getEmployeeDatabase().get(0).getData());
        employeeViewArea.setFont(new Font(employeeViewArea.getFont().getName(),Font.PLAIN,employeeViewArea.getFont().getSize()+8));

		JPanel buttonArea = new JPanel(new GridLayout(1,3));
		JButton promoteOrderButton = new JButton("Promote");
		JButton fireOrderButton = new JButton("Fire");
		JButton addEmployeeButton = new JButton("Add");
		
		promoteOrderButton.setFont(new Font(promoteOrderButton.getFont().getName(),Font.BOLD,promoteOrderButton.getFont().getSize()+12));
		fireOrderButton.setFont(new Font(fireOrderButton.getFont().getName(),Font.BOLD,fireOrderButton.getFont().getSize()+12));
		addEmployeeButton.setFont(new Font(addEmployeeButton.getFont().getName(),Font.BOLD,addEmployeeButton.getFont().getSize()+12));

		
		buttonArea.add(addEmployeeButton);
		buttonArea.add(promoteOrderButton);
		buttonArea.add(fireOrderButton);
		
		JLabel employeeDataLabel = new JLabel("Employee Data");
		JScrollPane two = new JScrollPane(employeeViewArea);
		rightPanel.add(employeeDataLabel,BorderLayout.NORTH);
		rightPanel.add(two,BorderLayout.CENTER);
		rightPanel.add(buttonArea, BorderLayout.SOUTH);
		
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		final JPanel southPanel = new JPanel();
		promoteOrderButton.addActionListener(new ActionListener(){
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selected = employeeJList.getSelectedIndex();
				if(employeeJList.getSelectedValue() != null){
					Employee e = (Employee) employeeJList.getSelectedValue();
					try {
						model.getEmployeeDatabase().promote(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					//	e1.prjavintStackTrace();
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}
					JOptionPane.showMessageDialog(null, e.getName()+" has been promoted!");
					employeeJList.setSelectedIndex(selected);
				}
				// TODO Auto-generated method stub
			//	southPanel.setVisible(true);
				//validate();
			}
			
		});
		
		addEmployeeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					initReg = new InitialRegistrationView(model,view); 
					view.add(initReg);

					view.pack();
					view.setVisible(true);
				
				
				
				
			}
			
		});
		
		fireOrderButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(employeeJList.getSelectedValue() != null){
					Employee e = (Employee) employeeJList.getSelectedValue();
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to fire "+e.getName()+"?");
					if(confirm==JOptionPane.OK_OPTION){
						try {
							model.getEmployeeDatabase().fire(e);
						} catch (Exception e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
						}
					}
				}
				
			}
			
		});
		
		employeeJList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(employeeJList.getSelectedValue() != null){
					Employee e = (Employee) employeeJList.getSelectedValue();
					employeeViewArea.setText(e.getData()+"\n"+"Orders Taken: "+ model.getOrderLog().getNumberofOrdersTaken(e));
					
				}
			}
			
		});
		
		JPanel mainContainer = new JPanel(new BorderLayout());
		
		mainContainer.add(leftPanel,BorderLayout.WEST);
		mainContainer.add(rightPanel,BorderLayout.CENTER);
		
		JLabel activeOrderLabel = new JLabel("Employees");
		add(activeOrderLabel,BorderLayout.NORTH);
		add(mainContainer,BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//southPanel.add(new MenuPanel());
		add(southPanel,BorderLayout.SOUTH);
		southPanel.setVisible(false);

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(initReg != null){
			view.remove(initReg);
		}
		view.dispose();
		employeeJList.setListData(model.getEmployeeDatabase().getEmployees().toArray());
		employeeViewArea.setText(model.getEmployeeDatabase().getEmployees().get(0).getData());
		
		
		
	}
	
	
	
}