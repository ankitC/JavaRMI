import registry.RemoteObjectReference;
import serverDatabases.CapitalQueryInterface;
import serverDatabases.DatabaseOfDatabases;
import util.RegistryMessenger;


/*
 * Simple Client that looks up the databases from the registry which is hosted remotely 
 * and then queries the database for the capital of the country or of the state.
 */
public class Client {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RegistryMessenger clientSideRegistryMessenger =  new RegistryMessenger();
		try {
			System.out.println("Listing the available Objects");
			System.out.println("Availale Objects:"+clientSideRegistryMessenger.list());
			System.out.println("Selecting the object from the list...");
			
			/*lookup and accept the remote reference from the registry*/
			RemoteObjectReference localNationsDBObject = clientSideRegistryMessenger.lookup("CountriesDb");
			
			
			
			System.out.println("Getting Stub for countries database");
			CapitalQueryInterface countriesQuery = (CapitalQueryInterface)localNationsDBObject.getStub();
			System.out.println("Got Stub");
			
		
			System.out.println("Querying database for capital of a country by RMI...");
			/* Querying the databases */
			System.out.println(countriesQuery.getCapital("India"));
			
			System.out.println("Getting the database archive from the registry....");
			RemoteObjectReference localDatabaseArchive = clientSideRegistryMessenger.lookup("DatabaseArchive");
			DatabaseOfDatabases databaseArchive = (DatabaseOfDatabases) localDatabaseArchive.getStub();
			System.out.println("Getting the remote reference of states database from the database Archive...");
			RemoteObjectReference localStateDBObjectReference = databaseArchive.getDatabase("StatesDb");
			System.out.println("Got the database");
			System.out.println("Making local stub for the returned reference..");
			CapitalQueryInterface statesQuery = (CapitalQueryInterface) localStateDBObjectReference.getStub();
			System.out.println("Querying the database for capital");
			System.out.println(statesQuery.getCapital("Ohio").toString());
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
