import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class StatesDatabase {
	private static Map<String, State> states = new HashMap<String, State>();

	public void buildStateDatabase(){

		String line = null;

		BufferedReader brs = new BufferedReader(new InputStreamReader(
				CapitalDatabase.class.getClassLoader().getResourceAsStream("states.txt")));

		try {
			while ((line = brs.readLine()) != null) {
				String[] sc = line.split(":");
				State state = new State(sc[0], sc[1]);
				states.put(sc[0].toLowerCase(), state);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static State getStateCapital(String state) {
		return states.get(state.toLowerCase());
	}
}


