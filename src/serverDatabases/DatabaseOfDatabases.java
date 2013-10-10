package serverDatabases;

import java.rmi.Remote;

import registry.RemoteObjectReference;

public interface DatabaseOfDatabases extends Remote {
	
	public RemoteObjectReference getDatabase(String databaseName);

}
