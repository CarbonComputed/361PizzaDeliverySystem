/**
 * 
 */
package pds.ui;

import java.awt.*;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.MaskFormatter;

/**
 * @author Rafiqy
 *
 */
public class LoginScreen extends JPanel {

	//labels for the username and password fields
	private JLabel loginID;
	private JLabel password;
	
	//text fields for the username and password
	private JFormattedTextField loginIDField;
	private JPasswordField userPassword;
	
	//public constructor for the login screen
	public LoginScreen(){
		super(new BorderLayout());
		
		//sets up the loginID field so that it must take in 3 letters and 4 digits
		loginIDField = new JFormattedTextField(createFormatter("UUU####"));
		userPassword = new JPasswordField();
		
		
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
