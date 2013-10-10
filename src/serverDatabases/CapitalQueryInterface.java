package serverDatabases;

import exceptionSys.CapitalNotFoundException;

import java.rmi.Remote;

/* Interface listing the methods implemented by the database */
public interface CapitalQueryInterface extends Remote {
	
	public String getCapital(String queryELement) throws CapitalNotFoundException;
	public String toString();
	

}
