/**
 * 
 */
package pds.ui;
import java.util.Vector;

import javax.swing.JList;

/**
 * @author Rafiqy
 *
 */
@SuppressWarnings("serial")
public class TotalPanel extends JList {
	
	private Double totalPrice;
	private Double estimatedTime;
	private Vector<Double> price_time;
	
	public TotalPanel(){
		super();
		totalPrice = (double) 0;
		estimatedTime = (double) 0;
		price_time = new Vector<Double>();
		price_time.add(totalPrice);
		price_time.add(estimatedTime);
		setListData(price_time);
	}

	/**
	 * @return totalPrice
	 * returns the current total order price
	 */
	public double getTotal() {
		return totalPrice;
	}

	/**
	 * @param newTotal
	 * sets the totalPrice of the order to new total
	 */
	public void setTotal(double newTotal) {
		totalPrice = newTotal;
		price_time.set(0, totalPrice);
		setListData(price_time);
		validate();
	}

	/**
	 * 
	 */
	public void clearOrder() {
		totalPrice = (double) 0;
		estimatedTime = (double) 0;
		price_time.set(0, totalPrice);
		price_time.set(1, estimatedTime);
		setListData(price_time);
		validate();
	}

}
