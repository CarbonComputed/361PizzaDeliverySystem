package pds.database;

import java.util.*;
import pds.sim.*;
import java.io.*;

/**
 * @author Goombas
 * 
 */
public class StageDatabase {

	private Simulation sim;

	public StageDatabase(Simulation sim) {
		this.sim = sim;

	}

	public void readDatabase() throws FileNotFoundException, Exception {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(new File(
					"config/Stage.cfg")));
			String in = "";

			try {
				while ((in = buff.readLine()) != null) {
					in = in.trim();
					if (!in.startsWith("#")) {
						String line[] = in.split(",");
						if (line.length != 4) {
							throw new Exception("Incorrect Datafile Format");
						}
						try {

							this.sim.addStage(new Stage(line[0].trim(), Integer
									.parseInt(line[1].trim()), Integer
									.parseInt(line[2].trim()), Boolean
									.parseBoolean(line[3].trim())));
						} catch (Exception e) {
							e.printStackTrace();
							throw new Exception("Incorrect Datafile Format");
						}
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
		StageDatabase stageD = new StageDatabase(sim);
		stageD.readDatabase();
		System.out.println(stageD.sim.getStage(0).getName());
	}

}
