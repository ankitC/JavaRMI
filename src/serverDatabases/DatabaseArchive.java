package serverDatabases;

import java.util.LinkedHashMap;

import registry.RemoteObjectReference;

public class DatabaseArchive implements DatabaseOfDatabases {
	
	private LinkedHashMap<String, RemoteObjectReference> databaseArchive;
	
	public DatabaseArchive(){
		databaseArchive = new LinkedHashMap<String,	RemoteObjectReference>();
	}
	public void addDatabaseToArchive(String dataBaseName, RemoteObjectReference database){
		databaseArchive.put(dataBaseName, database);
	}

	@Override
	public RemoteObjectReference getDatabase(String databaseName) {
		return databaseArchive.get(databaseName);
	}

}
