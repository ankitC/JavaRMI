/*
 * Capitals Server Class is a database of capitals of 244 countries 
 * in the world and 50 states of United States.
 * We export the databases to the registry for the clients 
 * to query the server remotely by invoking native methods.
 */

import registry.RemoteObjectReference;
import serverDatabases.*;
import util.RegistryMessenger;

import java.net.UnknownHostException;

public class CapitalsServer {

	private static ServerRMIHelper serverRMIHelper = new ServerRMIHelper();

	public CapitalsServer() {
		CapitalsServer.serverRMIHelper=new ServerRMIHelper();
	}

	public static void main(String[] args) {

		System.out.println("Starting Server...");
		       
        /* Starting the serverSide RMI Helper which intercepts the 
         * remotely generated method invoke messages 
         */
        serverRMIHelper.handleRMIRequests();
		
		System.out.println("Building Countries Database...");
		/* Making the database */
		NationsDatabase countries = new NationsDatabase();
		countries.buildNationsDatabase();
		System.out.println("Countries Database Initialized...");
		System.out.println("Building States Database...");
		StatesDatabase states = new StatesDatabase();
		states.buildStateDatabase();
		System.out.println("States Database Initialized");
		
		System.out.println("Making database archive");
		DatabaseArchive dataArchive= new DatabaseArchive();
		
		/* Export the object to the ServerSide Helper which acts as a skeleton*/
		System.out.println("Exporting Objects...");

		try {
			RemoteObjectReference remoteCountriesDb = serverRMIHelper.createRemoteObjectReference
					("CountriesDb", CapitalQueryInterface.class.getName(), countries);
			RemoteObjectReference remoteStatesDb = serverRMIHelper.createRemoteObjectReference
					("StatesDb", CapitalQueryInterface.class.getName(), states);
			RemoteObjectReference remoteDatabaseArchive = serverRMIHelper.createRemoteObjectReference
					("DatabaseArchive",  DatabaseOfDatabases.class.getName(), dataArchive);
			System.out.println("Finished exporting Objects to the RMI helper...");
			
			System.out.println("Adding the databases to the Archive");
			dataArchive.addDatabaseToArchive("CountriesDb",remoteCountriesDb);
			dataArchive.addDatabaseToArchive("StatesDb", remoteStatesDb);


			System.out.println("Binding the objects to the Registry...");
			/* Registering Objects with the Registry */
			RegistryMessenger registryMessenger = new RegistryMessenger();
		
			registryMessenger.bind(remoteDatabaseArchive);
			registryMessenger.bind(remoteCountriesDb);
			registryMessenger.bind(remoteStatesDb);

			System.out.println("Finished binding the object with the registry...");
			System.out.println("Starting to service remote requests...");

		} catch (UnknownHostException e) {
			System.out.println("Could not connect to the Registry Host!");
			//e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception Occured at the Server End!! Please check!");
		}

	}

	public ServerRMIHelper getServerRMIHelper() {
		return serverRMIHelper;
	}

	public void setServerRMIHelper(ServerRMIHelper serverRMIHelper) {
		CapitalsServer.serverRMIHelper = serverRMIHelper;
	}

}

