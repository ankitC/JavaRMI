import registry.RemoteObjectReference;
import serverDatabases.CapitalQueryInterface;
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
			RemoteObjectReference localStateDBObjectReference = clientSideRegistryMessenger.lookup("StatesDb");
			
			
			System.out.println("Getting Stub");
			CapitalQueryInterface countriesQuery = (CapitalQueryInterface)localNationsDBObject.getStub();
			CapitalQueryInterface statesQuery = (CapitalQueryInterface) localStateDBObjectReference.getStub();
			System.out.println("Got Stub");
			
			/* Querying the databases */
			System.out.println(countriesQuery.getNationCapital("India"));
			System.out.println(statesQuery.getStateCapital("Ohio").toString());
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
