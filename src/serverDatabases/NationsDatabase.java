package serverDatabases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Database of Nations and their Capitals */

public class NationsDatabase implements CapitalQueryInterface {
	private static Map<String, String> nations = new HashMap<String, String>();

	/* Builds the database from the file for national capitals */
	public void buildNationsDatabase() {
		System.out.println("Trying to build database");

		BufferedReader brn;
		try {
			brn = new BufferedReader(new InputStreamReader(new FileInputStream(
					"nations.txt")));

			String line = null;

			while ((line = brn.readLine()) != null) {
				String[] nc = line.split(":");
				nations.put(nc[0].toLowerCase(), nc[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Returns the name of the capital for the input nation */
	public String getCapital(String nation) {
		return nations.get(nation.toLowerCase());
	}

}
