/**
 * This is the customerInfoPanel class
 * CustomerInfoPanel is a class that extends JPanel
 * CustomerInfoPanel contains:
 * 3 JLabels, mapped to the FormattedTextFields and JCombobox
 * 2 JFormattedTextFields, used for input of the customer phone number and name 
 * 1 JComboBox, used for selection of the customers location
 * 
 */

package pds.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import pds.database.Location;
import pds.database.LocationDatabase;

/**
 * @author Rafiqy
 * 
 */
@SuppressWarnings("serial")
public class CustomerInfoPanel extends JPanel {

	// Labels to identify customer information fields
	private JLabel phoneNumberLabel;
	private JLabel nameLabel;
	private JLabel locationLabel;

	// Strings for the labels;
	private static String phoneNumberString = "Phone Number: ";
	private static String nameString = "Name: ";
	private static String locationString = "Delivery Location: ";

	// Fields for customer information
	private JFormattedTextField phoneNumberField;
	private JFormattedTextField nameField;
	private JComboBox locationBox;

	// a navigation panel to get to creating the order
	private NavigationPanel navPanel;

	// button listener for the navigation panel
	private StartOrderListener startButton;

	//
	private PhoneNumberListener numberListener;

	private LocationDatabase locations;
	private TabbedWindow mainWindow;

	/**
	 * pubic constructor for the CustomerInfoPanel used for testing creates a
	 * list of strings for use as the locations
	 * 
	 */
	public CustomerInfoPanel() {
		super(new BorderLayout());

		// sets the values for the panels
		phoneNumberLabel = new JLabel(phoneNumberString);
		nameLabel = new JLabel(nameString);
		locationLabel = new JLabel(locationString);

		// Instantiates the Start order button
		startButton = new StartOrderListener();
		startButton.setMenu(this);

		// Instantiates the navigation panel
		// adds the button listener to the panel
		navPanel = new NavigationPanel(startButton);

		// Sets the phone number field to take in 10 digits
		phoneNumberField = new JFormattedTextField(
				createFormatter("(###)###-####"));
		phoneNumberField.setColumns(20);

		// Sets the name field to take in a letter followed by any 20 characters
		MaskFormatter nameFormatter = createFormatter("U********************");
		nameFormatter.setValidCharacters("abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ,");
		nameField = new JFormattedTextField(nameFormatter);
		nameField.setColumns(20);

		// used for testing purposes
		// sets up the location box with 4 options
		String[] locationStrings = { "RIT", "SJF", "NAZ", "U of R" };
		locationBox = new JComboBox(locationStrings);

		// maps the labels with the fields
		phoneNumberLabel.setLabelFor(phoneNumberField);
		nameLabel.setLabelFor(nameField);
		locationLabel.setLabelFor(locationBox);

		// adds the labels to a new JPanel
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(phoneNumberLabel);
		labelPane.add(nameLabel);
		labelPane.add(locationLabel);

		// sets the fields to a new JPanel
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(phoneNumberField);
		fieldPane.add(nameField);
		fieldPane.add(locationBox);

		// adds the labels and fields to the customer information panel
		add(labelPane, BorderLayout.WEST);
		add(fieldPane, BorderLayout.CENTER);
		add(navPanel, BorderLayout.EAST);

	}

	/**
	 * @ Param List<?> locations locations is a generic list of anything
	 * 
	 * pubic constructor for the CustomerInfoPanel takes in a list of locations
	 * and creates a JComboBox to represent the locations
	 * 
	 */
	@SuppressWarnings("static-access")
	public CustomerInfoPanel(LocationDatabase locations2, TabbedWindow tabWindow) {
		super(new BorderLayout());
		
		//sets the mainwindow to tabwindow
		mainWindow = tabWindow;

		// sets the values for the panels
		phoneNumberLabel = new JLabel(phoneNumberString);
		nameLabel = new JLabel(nameString);
		locationLabel = new JLabel(locationString);

		// changed the font size
		Font f = phoneNumberLabel.getFont();
		Font F = new Font("temp", f.getStyle(), 20);

		// changes the font of the labels
		phoneNumberLabel.setFont(F);
		nameLabel.setFont(F);
		locationLabel.setFont(F);

		//
		numberListener = new PhoneNumberListener();
		numberListener.setCustInfoPanel(this);

		// Instantiates the Start order button
		startButton = new StartOrderListener();
		startButton.setMenu(this);
		startButton.setMainWindow(tabWindow);

		// Instantiates the navigation panel
		// adds the button listener to the panel
		navPanel = new NavigationPanel(startButton);

		// Sets the phone number field to take in 10 digits
		phoneNumberField = new JFormattedTextField(
				createFormatter("(###)###-####"));
		phoneNumberField.setColumns(20);
		phoneNumberField.setCaretPosition(0);
		phoneNumberField.setFocusLostBehavior(phoneNumberField.COMMIT);
		phoneNumberField.setFont(F);
		phoneNumberField.addFocusListener(numberListener);

		// Sets the name field to take in a letter followed by any 20 characters
		MaskFormatter nameFormatter = createFormatter("U********************");
		nameFormatter.setValidCharacters("abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ, ");
		nameField = new JFormattedTextField(nameFormatter);
		nameField.setColumns(20);
		nameField.setCaretPosition(0);
		nameField.setFocusLostBehavior(nameField.COMMIT);
		nameField.setFont(F);

		// sets up the location box with the locations that are
		// in the database
		locations = locations2;
		String[] locationNames = new String[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			locationNames[i] = locations.get(i).getLocation();
		}

		locationBox = new JComboBox(locationNames);
		locationBox.setFont(new Font("temp", f.getStyle(), 20));

		// maps the labels with the fields
		phoneNumberLabel.setLabelFor(phoneNumberField);
		nameLabel.setLabelFor(nameField);
		locationLabel.setLabelFor(locationBox);

		// adds the labels to a new JPanel
		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(phoneNumberLabel);
		labelPane.add(nameLabel);
		labelPane.add(locationLabel);

		// sets the fields to a new JPanel
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(phoneNumberField);
		fieldPane.add(nameField);
		fieldPane.add(locationBox);

		JPanel custInfoPane = new JPanel(new GridLayout());
		custInfoPane.add(labelPane);
		custInfoPane.add(fieldPane);
		custInfoPane.setBorder(new EmptyBorder(250, 250, 250, 250));

		// adds the labels and fields to the customer information panel
		add(custInfoPane, BorderLayout.CENTER);
		add(navPanel, BorderLayout.EAST);

	}

	/**
	 * @ param s s is a string that consist of characters that are acceptable
	 * input for a MaskFormatter
	 * 
	 * @ return MaskFormatter
	 * 
	 * attempts to create a MaskFormatter and returns that MaskFormatter if the
	 * creation was successful
	 * 
	 */
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

	/**
	 * 
	 */
	public void clearFields() {
		phoneNumberField.setText("");
		nameField.setText("");
		phoneNumberField.setCaretPosition(0);
		nameField.setCaretPosition(0);
		locationBox.setSelectedIndex(0);

	}

	/**
	 * @return String
	 */
	public Location getCustLocation() {

		return locations.get(locationBox.getSelectedIndex());
	}

	/**
	 * @return String
	 */
	public int getCustLocationID() {

		return locationBox.getSelectedIndex();
	}

	/**
	 * @return String
	 */
	public String getCustName() {
		String name = nameField.getText();
		name = name.replace(" ", "");
		return name;
	}

	/**
	 * @return String
	 */
	public String getCustPhoneNumber() {
		String phoneNumber = phoneNumberField.getText();
		phoneNumber = phoneNumber.replace(")", "");
		phoneNumber = phoneNumber.replace("(", "");
		phoneNumber = phoneNumber.replace("-", "");
		return phoneNumber;
	}

	public TabbedWindow getMainWindow(){
		return mainWindow;
	}

	/**
	 * @return
	 */
	public LocationDatabase getLocations() {
		return locations;
	}

	/**
	 * @param indexOf
	 */
	public void setCustLocation(int locationID) {
		locationBox.setSelectedIndex(locationID);
		
	}

	/**
	 * @param name
	 */
	public void setCustName(String name) {
		nameField.setText(name);
		
	}

	/**
	 * 
	 */
	public void custPhoneNumberFieldDoCommit() {
		try {
			phoneNumberField.commitEdit();
		} catch (ParseException e) {
			
		}
		
	}
}
