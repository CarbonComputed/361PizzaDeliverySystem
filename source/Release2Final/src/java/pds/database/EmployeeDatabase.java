package pds.database;

/**
 * @author kmc8206
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pds.ui.ModifiedObservable;

public class EmployeeDatabase extends ArrayList<Employee> {

	private Employee loggedOn;
	private ModifiedObservable observable;

	public EmployeeDatabase() {
		super();
		observable = new ModifiedObservable();
	}

	public ModifiedObservable getObservable() {
		return observable;
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(this);
	}

	public boolean add(Employee e) {
		super.add(e);
		observable.setChanged();
		observable.notifyObservers();
		observable.clearChanged();
		return true;
	}

	public void fire(Employee e) throws Exception {
		int managercnt=0;
		for(Employee emp : this){
			if(emp.isManager()){
				managercnt++;
			}
		}
		if(managercnt <= 1 && e.isManager()){
			throw new Exception("There has to be at least one Admin at all times");
		}
		if (loggedOn.isManager()) {
			this.remove(e);
			this.writeDatabase();
			observable.setChanged();
			observable.notifyObservers();
			observable.clearChanged();
		} else {
			throw new Exception("A Non Manager cannot Fire Employees");
		}
	}

	public void promote(Employee e) throws Exception {
		loggedOn.promote(e);
		observable.setChanged();
		observable.notifyObservers();
		observable.clearChanged();
	}

	public void register(Employee employee) throws Exception {
		for (Employee e : this) {
			if (e.getUserName().equals(employee.getUserName())) {
				throw new Exception("Username already Exists");
			}
		}
		add(employee);
		this.writeDatabase();
	}

	public Employee login(String username, String password) throws Exception {
		for (Employee e : getEmployees()) {
			if (e.getUserName().equals(username)
					&& e.getPassword().equals(password)) {
				loggedOn = e;
				return e;

			}
		}
		throw new Exception("Invalid Login");
	}

	public void logOut() {
		loggedOn = null;
	}

	public Employee getLoggedOn() {
		return loggedOn;

	}

	public void writeDatabase() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"database/Employee.db")));
			writer.write("#Refrain from Editing or hacking!\n");

			for (Employee e : this) {
				writer.write(e.getUserName());
				writer.write(",");
				writer.write(e.getName());
				writer.write(",");
				writer.write(e.getPassword());
				writer.write(",");
				writer.write("" + e.isManager());
				writer.write("\n");

			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readDatabase() throws FileNotFoundException, Exception {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"database/Employee.db")));
			String in = "";
			try {
				while ((in = reader.readLine()) != null) {
					// in.replaceAll(" ", "");
					in = in.trim();
					if (!in.startsWith("#")) {
						String line[] = in.split("(\\\",\\\"|(?<!\\\"),)");
						if (line.length != 4) {
							throw new Exception("Malformed Database File");
						}
						try {
							this.add(new Employee(line[0].trim(), line[1]
									.trim(), line[2].trim(), Boolean
									.parseBoolean(line[3].trim())));
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception("Malformed Database File");
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
			throw e;
		}
	}

	public static void main(String args[]) {
		EmployeeDatabase db = new EmployeeDatabase();
		try {
			db.readDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(db.get(1));

	}
}
