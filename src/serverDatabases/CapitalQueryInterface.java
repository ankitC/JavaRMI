package serverDatabases;

import java.rmi.Remote;

/* Interface listing the methods implemented by the database */
public interface CapitalQueryInterface extends Remote {
	
	public String getCapital(String queryELement);
	public String toString();
	

}
