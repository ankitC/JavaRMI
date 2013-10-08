import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class NationsDatabase {
	private static Map<String, String> nations = new HashMap<String, String>();

	public void buildNationsDatabase(){ 
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
	}
	
	 public String getNationCapital(String nation) {
	        return nations.get(nation.toLowerCase());
	    }

}
