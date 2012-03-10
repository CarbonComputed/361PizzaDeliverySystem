/**
 * 
 */
package pds.database;

import pds.order.*;
import java.util.*;
import pds.sim.*;
import java.io.*;

/**
 * @author Goombas
 * 
 */
public class MenuDatabase {

	private ArrayList<Item> itemOptions;
	private ArrayList<Topping> toppingOptions;
	private Simulation sim;

	public MenuDatabase(Simulation sim) {
		this.sim = sim;
		this.itemOptions = new ArrayList<Item>();
		this.toppingOptions = new ArrayList<Topping>();
	}

	public Simulation getSimulation() {
		return this.sim;
	}

	public ArrayList<Item> getItemOptions() {
		return this.itemOptions;
	}

	public ArrayList<Topping> getToppingOptions() {
		return this.toppingOptions;
	}

	public void readDatabase() throws FileNotFoundException, Exception {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File(
					"config/Menu.cfg")));
			String in = "";
			try {
				while ((in = buff.readLine()) != null) {
					in = in.trim();
					if (!in.startsWith("#")) {
						String line[] = in.split(",");
						ArrayList<Integer> stageTimes = new ArrayList<Integer>();
						if (line.length != sim.getStages().size() + 3) {
							throw new Exception("Invalid Menu File");
						}
						for (int i = 4; i < line.length; i++) {
							try {
								stageTimes.add(Integer.parseInt(line[i]));
							} catch (Exception e) {
								throw new Exception("Invalid File");
							}
						}
						Item i = new Item(line[0], Double.parseDouble(line[1]),
								Integer.parseInt(line[2]),
								Boolean.parseBoolean(line[3]), stageTimes);
						itemOptions.add(i);
					}
				}
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	public static void main(String args[]) throws FileNotFoundException,
			Exception {
		Simulation sim = new Simulation(1000);
		MenuDatabase menu = new MenuDatabase(sim);
		menu.readDatabase();
		for (Item i : menu.getItemOptions()) {
			System.out.println(i.getName());
			System.out.println(i.getPrice());
			System.out.println(i.getSpace());
		}
	}

}
