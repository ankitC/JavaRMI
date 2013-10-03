import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CapitalDatabase {

    private static Map<String, String> nations = new HashMap<String, String>();
    private static Map<String, State> states = new HashMap<String, State>();

    static {
        BufferedReader brn = new BufferedReader(new InputStreamReader(
                CapitalDatabase.class.getClassLoader().getResourceAsStream("nations.txt")));

        String line = null;

        try {
            while ((line = brn.readLine()) != null) {
                String[] nc = line.split(":");
                nations.put(nc[0].toLowerCase(), nc[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static String getNationCapital(String nation) {
        return nations.get(nation.toLowerCase());
    }

    public static State getStateCapital(String state) {
        return states.get(state.toLowerCase());
    }

    public static class State {

        private String name;
        private String capital;

        private State(String name, String capital) {
            this.name = name;
            this.capital = capital;
        }

        public String getName() {
            return name;
        }

        public String getCapital() {
            return capital;
        }
    }
}
