package serverDatabases;

import java.rmi.Remote;

public interface CapitalQueryInterface extends Remote {
	
	public State getStateCapital(String state);
	public String getNationCapital(String nation);
	public String toString();
	

}
