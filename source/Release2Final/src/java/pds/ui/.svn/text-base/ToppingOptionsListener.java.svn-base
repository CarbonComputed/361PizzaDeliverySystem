/**
 * 
 */
package pds.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Rafiqy
 *
 */
public class ToppingOptionsListener implements ActionListener {

	ToppingsPanel toppingP;
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Whole Menu")){
			toppingP.wholeToppingsPanel();
		} else{
			toppingP.halfToppingsPanel();
		}

	}

	/**
	 * @param toppingsPanel
	 */
	public void setToppingPanel(ToppingsPanel toppingsPanel) {
		
		toppingP = toppingsPanel;
	}

}
