/**
 * 
 */
package pds.ui;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pds.core.Register;
import pds.sim.ItemWorker;
import pds.sim.OrderWorker;
import pds.sim.Stage;

/**
 * @author kevin
 *
 */
public class KitchenConfigView extends JPanel {
	
	private ArrayList<JFormattedTextField> fields;
	private ModifiedObservable modObservable;
	
	private boolean isComplete;
	
	private Register model;
	
	public KitchenConfigView(MainGUI main, Register model){
		this.model = model;
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(model.getSimulation().getStages().size()+2,2));
		modObservable = new ModifiedObservable();
		modObservable.addObserver(main);
		GridLayout gridLayout = new GridLayout(5,2,16,16);
		setLayout(new GridBagLayout());
		isComplete = false;
		JLabel storeConfigLabel = new JLabel("Store Configuration");
		JButton openStoreButton = new JButton("Open Store");
		openStoreButton.addActionListener(new OpenStoreListener());
		fields = new ArrayList<JFormattedTextField>();
		

		container.add(storeConfigLabel);
		container.add(new JLabel("        "));
		for(int x=0;x<model.getSimulation().getStages().size();x++){
			container.add(new JLabel("# of "+model.getSimulation().getStage(x).getName()+"s"));
			JFormattedTextField field = new JFormattedTextField(createFormatter("#####"));
			field.setFocusLostBehavior(JFormattedTextField.COMMIT);
			container.add(field);
			field.setText(""+model.getSimulation().getStage(x).size());
			fields.add(field);
			//field.setEditable(false);
		}
		container.add(new JLabel(""));
		container.add(openStoreButton);
		//add(new JLabel(""));
		
		container.setBorder(new EmptyBorder(8,8,8,8));
		
		this.requestFocus();
		this.requestFocusInWindow();
		
		openStoreButton.setFocusable(true);
		openStoreButton.requestFocus();
		openStoreButton.requestFocusInWindow();
		openStoreButton.setMnemonic(KeyEvent.VK_ENTER);
		openStoreButton.setEnabled(true);
		
		
		add(container);
		//add(nCooks, 1, 1);
		//add(nOvens, 2, 1);
		//add(nDrivers, 3, 1);
		
	
		
		
		
		
		
		
		
		
	}
	
	public boolean isComplete(){
		return isComplete;
	}
	
	private class OpenStoreListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int i = 0;
			for(Stage s: model.getSimulation().getStages()){
					JFormattedTextField field = fields.get(i);
					int num = Integer.parseInt(field.getText().trim());
					int initSize = s.size();
					while(s.size() <= num){
						if(s.isByOrder()){
							s.add(new OrderWorker(s.getName(),s.getWorkerSize()));
						}
						else{
							s.add(new ItemWorker(s.getName(),s.getWorkerSize()));
						}
					}
					while(s.size() > num){
						s.remove(0);
					}
					
					i++;
			}
			try {
				model.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				return;
			}
			isComplete = true;
			modObservable.setChanged();
			modObservable.notifyObservers();
			modObservable.clearChanged();
		}
		
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
