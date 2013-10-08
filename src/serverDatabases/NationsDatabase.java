package serverDatabases;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;



public class NationsDatabase implements CapitalQueryInterface{
	private static Map<String, String> nations = new HashMap<String, String>();

	public void buildNationsDatabase(){
		System.out.println("Trying to build database");
		
		BufferedReader brn;
		try {
			brn = new BufferedReader(new InputStreamReader(
					new FileInputStream("../nations.txt")));
		
		
				//CapitalDatabase.class.getClassLoader().getResourceAsStream("../nations.txt")));
		
		String line = null;

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

	@Override
	public State getStateCapital(String state) {
		// TODO Auto-generated method stub
		return null;
	}

}
