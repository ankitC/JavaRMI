package serverDatabases;

import exceptionSys.CapitalNotFoundException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Database of State and their Capitals */

public class StatesDatabase implements CapitalQueryInterface{
	private static Map<String, State> states = new HashMap<String, State>();
	
	/* Builds the database from the file for state capitals */
	public void buildStateDatabase(){
		try {
			String line = null;

			BufferedReader brs = new BufferedReader(new InputStreamReader(
					new FileInputStream("states.txt")));

			while ((line = brs.readLine()) != null) {
				String[] sc = line.split(":");
				State state = new State(sc[0], sc[1]);
				states.put(sc[0].toLowerCase(), state);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public String getCapital(String state) throws CapitalNotFoundException {
        if (state == null) throw new CapitalNotFoundException();

        State resultState = states.get(state.toLowerCase());

        if (resultState == null) throw new CapitalNotFoundException();

        return resultState.getCapital();
    }
}


