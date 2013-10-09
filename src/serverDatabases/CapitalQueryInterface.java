package serverDatabases;

import java.rmi.Remote;

/* Interface listing the methods implemented by the database */
public interface CapitalQueryInterface extends Remote {
	
	public State getStateCapital(String state);
	public String getNationCapital(String nation);
	public String toString();
	

}
