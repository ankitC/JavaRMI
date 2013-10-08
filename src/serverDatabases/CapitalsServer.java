package serverDatabases;

import java.net.UnknownHostException;
import registry.RemoteObjectReference;
import util.RegistryMessenger;

public class CapitalsServer {
	
	private static ServerRMIHelper serverRMIHelper = new ServerRMIHelper();
	
	public CapitalsServer() {
		this.serverRMIHelper=new ServerRMIHelper();
	}

	public static void main(String[] args) {
		
		CapitalsServer ourServer = new CapitalsServer();
		
		System.out.println("Starting Server");
		System.out.println("Building Countries Database...");
		NationsDatabase countries = new NationsDatabase();
		countries.buildNationsDatabase();
		System.out.println("Countries Database Initialized...");
		System.out.println("Building States Database...");
		StatesDatabase states = new StatesDatabase();
		states.buildStateDatabase();
		System.out.println("States Database Initialized");
		
		
		System.out.println("Exporting Objects...");
		
		try {
			RemoteObjectReference remoteCountriesDb = serverRMIHelper.exportRemoteObject("CountriesDb", CapitalQueryInterface.class.getName(), countries);
			RemoteObjectReference remoteStatesDb = serverRMIHelper.exportRemoteObject("StatesDb", CapitalQueryInterface.class.getName(), states);
			System.out.println("Finished exporting Objects to the RMI helper...");
			System.out.println("Binding the object to the RMI...");
			
			RegistryMessenger registryMessenger = new RegistryMessenger();
			
			registryMessenger.bind(remoteCountriesDb);
			registryMessenger.bind(remoteStatesDb);
			
			System.out.println("Finished binding the object with the registry...");
			System.out.println("Starting to service remote requests...");
			serverRMIHelper.serve();
			
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public ServerRMIHelper getServerRMIHelper() {
		return serverRMIHelper;
	}

	public void setServerRMIHelper(ServerRMIHelper serverRMIHelper) {
		this.serverRMIHelper = serverRMIHelper;
	}

}
