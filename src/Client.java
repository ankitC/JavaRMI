import registry.RemoteObjectReference;
import serverDatabases.CapitalQueryInterface;
import util.RegistryMessenger;


public class Client {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RegistryMessenger clientSideRegistryMessenger =  new RegistryMessenger();
		try {
			RemoteObjectReference localNationsDBObject = clientSideRegistryMessenger.lookup("CountriesDb");
			System.out.println("Getting Stub");
			CapitalQueryInterface countriesQuery = (CapitalQueryInterface)localNationsDBObject.getStub();
			System.out.println("Got Stub");
			RemoteObjectReference localStateDBObjectReference = clientSideRegistryMessenger.lookup("StatesDb");
			CapitalQueryInterface statesQuery = (CapitalQueryInterface) localStateDBObjectReference.getStub();
			
			System.out.println(countriesQuery.getNationCapital("India"));
			System.out.println(statesQuery.getStateCapital("Ohio").toString());
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
