/**
 * 
 */
package pds.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author kevin
 * 
 */
@SuppressWarnings("serial")
public class LocationDatabase extends ArrayList<Location> {

	public LocationDatabase() {
		super();
	}

	public Location find(String loc) throws Exception {
		for (Location l : this) {
			if (l.getLocation().equals(loc)) {
				return l;
			}
		}
		throw new Exception("Invalid Location");
	}

	public void readDatabase() throws Exception {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"database/Location.db")));
			String in = "";
			try {
				while ((in = reader.readLine()) != null) {
					// in.replaceAll(" ", "");
					in = in.trim();
					if (!in.startsWith("#")) {
						String line[] = in.split(",");
						if (line.length != 2) {
							throw new Exception("Malformed Database File");
						}
						try {
							this.add(new Location(line[0], Integer
									.parseInt(line[1])));
						} catch (Exception e) {
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
}
