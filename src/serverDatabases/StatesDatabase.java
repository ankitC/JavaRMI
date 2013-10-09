package serverDatabases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class StatesDatabase implements CapitalQueryInterface{
	private static Map<String, State> states = new HashMap<String, State>();

	public void buildStateDatabase(){
		try {
			String line = null;

			BufferedReader brs = new BufferedReader(new InputStreamReader(
                    StatesDatabase.class.getClassLoader().getResourceAsStream("states.txt")));

			while ((line = brs.readLine()) != null) {
				String[] sc = line.split(":");
				State state = new State(sc[0], sc[1]);
				states.put(sc[0].toLowerCase(), state);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public State getStateCapital(String state) {
		return states.get(state.toLowerCase());
	}
	
	
	@Override
	public String getNationCapital(String nation) {
		// TODO Auto-generated method stub
		return null;
	}
}


