package pds.database;

/**
 * @author kmc8206
 */

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Customers extends HashMap<String, Customer> implements
		Serializable {

	private LocationDatabase locDatabase;

	public Customers(LocationDatabase locDb) {
		super();
		this.locDatabase = locDb;
	}

	// TODO: Remove stringly typing
	public Customer newCustomer(String name, Location location,
			String phoneNumber) {
		Customer c = new Customer(name, location, phoneNumber);
		this.put(phoneNumber, c);

		return c;
	}

	public void writeDatabase() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"database/Customer.db")));
			writer.write("#Refrain from Editing\n");
			
			for (Customer c : this.values()) {
				writer.write(c.getName());
				writer.write(",");
				writer.write(c.getPhoneNumber());
				writer.write(",");
				writer.write(c.getLocation().getLocation());
				writer.write("\n");

			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Customer put(String key, Customer value){
		Customer c = super.put(key, value);
		this.writeDatabase();
		return c;
	}
	
	

	public void readDatabase() throws Exception {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"database/Customer.db")));
			String in = "";
			try {
				while ((in = reader.readLine()) != null) {
					//in.replaceAll(" ", "");
					in = in.trim();
					if (!in.startsWith("#")) {
						String line[] = in.split(",");
						if (line.length != 3) {
							throw new Exception("Malformed Database File");
						}
						try {

							this.put(
									line[1],
									new Customer(line[0], locDatabase
											.find(line[2]), line[1]));

						} catch (Exception e) {
							if (e.getMessage().equals("Invalid Location")) {
								throw e;
							} else {
								throw new Exception("Malformed Database File");
							}

						}

					}
				}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// throw e;
			return;
		}
	}

}
