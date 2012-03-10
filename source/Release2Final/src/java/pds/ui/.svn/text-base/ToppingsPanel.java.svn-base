/**
 * 
 */
package pds.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pds.database.ToppingList;
import pds.order.Topping;

/**
 * @author Rafiqy
 * 
 */
@SuppressWarnings("serial")
public class ToppingsPanel extends JPanel {

	private ToppingList currToppings;
	private JPanel toppingsPaneR;
	private JPanel toppingsPaneL;
	private JPanel toppingsPaneC;
	private ArrayList<JCheckBox> centerGroup;
	private ArrayList<JCheckBox> leftGroup;
	private ArrayList<JCheckBox> rightGroup;
	private ToppingOptionsListener halfOrWhole;
	private int length;
	private int width;

	public ToppingsPanel(ToppingList toppings, int len, int wid) {
		super(new BorderLayout());

		length = len;
		width = wid;
		
		currToppings = toppings;
		
		halfOrWhole = new ToppingOptionsListener();
		halfOrWhole.setToppingPanel(this);
		
		centerGroup = new ArrayList<JCheckBox>();
		leftGroup = new ArrayList<JCheckBox>();
		rightGroup = new ArrayList<JCheckBox>();

		JPanel Half_Whole = new JPanel(new GridLayout(1, 0));
		JRadioButton wholeButton = new JRadioButton("Add Whole Topping");
		wholeButton.setActionCommand("Whole Menu");
		JRadioButton halfButton = new JRadioButton("Add Half Topping");
		halfButton.setActionCommand("Half Menu");
		
		wholeButton.addActionListener(halfOrWhole);
		halfButton.addActionListener(halfOrWhole);
		

		Font f = wholeButton.getFont();
		Font F = new Font("temp", f.getStyle(), 20);

		wholeButton.setFont(F);
		halfButton.setFont(F);

		ButtonGroup group = new ButtonGroup();
		group.add(wholeButton);
		group.add(halfButton);
		Half_Whole.add(wholeButton, BorderLayout.EAST);
		Half_Whole.add(halfButton, BorderLayout.WEST);
		add(Half_Whole, BorderLayout.NORTH);
		makeToppingsPanes();
	}

	/**
	 * 
	 */
	private void makeToppingsPanes() {
		
		toppingsPaneR = new JPanel(new GridLayout(length, width));
		toppingsPaneL = new JPanel(new GridLayout(length, width));
		toppingsPaneC = new JPanel(new GridLayout(length, width));
		
		for (Topping top : currToppings) {
			JCheckBox checkBox1 = new JCheckBox(top.getName());
			JCheckBox checkBox2 = new JCheckBox(top.getName());
			JCheckBox checkBox3 = new JCheckBox(top.getName());
			
			Font f = checkBox1.getFont();
			Font F = new Font("temp", f.getStyle(), 20);
			checkBox1.setFont(F);
			checkBox2.setFont(F);
			checkBox3.setFont(F);

			toppingsPaneC.add(checkBox3);
			centerGroup.add(checkBox3);
			toppingsPaneR.add(checkBox2);
			rightGroup.add(checkBox2);
			toppingsPaneL.add(checkBox1);
			leftGroup.add(checkBox1);
			
			if (top.getName().equalsIgnoreCase("pepperoni")) {
				checkBox1.setSelected(true);
				checkBox2.setSelected(true);
				checkBox3.setSelected(true);
			}

		}
	}

	public void wholeToppingsPanel() {

		remove(toppingsPaneR);
		remove(toppingsPaneL);

		add(toppingsPaneC, BorderLayout.CENTER);
		updateUI();
	}
	
	public void halfToppingsPanel(){
		
		remove(toppingsPaneC);

		add(toppingsPaneR, BorderLayout.EAST);
		add(toppingsPaneL, BorderLayout.WEST);
		updateUI();
	}

	/**
	 * @return
	 */
	public ToppingList getCurrToppings() {
		return currToppings;
	}

	/**
	 * @return
	 */
	public JPanel getToppingsCheckBoxes() {
		return toppingsPaneR;
	}

	/**
	 * 
	 */
	public ArrayList<JCheckBox> getCenterButtons() {
		return centerGroup;
		
	}

	/**
	 * @return
	 */
	public ArrayList<JCheckBox> getLeftButtons() {
		
		return leftGroup;
	}

	/**
	 * @return
	 */
	public ArrayList<JCheckBox> getRightButtons() {
		
		return rightGroup;
	}
}
