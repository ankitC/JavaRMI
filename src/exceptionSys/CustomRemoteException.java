package exceptionSys;
public class CustomRemoteException extends Exception {

	/*
	 * Custom exception Handler for the system. 
	 */
	private static final long serialVersionUID = 1L;

	public CustomRemoteException() {
		super();
	}

	public CustomRemoteException(String message) {
		super(message);
	}

}
