package util;
import exceptionSys.CustomRemoteException;
import registry.RegistryRequestMessage;
import registry.RemoteObjectReference;
import registry.ReturnMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* 
 * This is a vehicle to communicate with the registry .
 *  It takes the commands and makes a RegistryRequestMessage out of it.
 *  It sends this message to the Registry, and gets the return message.
 *  Using the ReturnedMessage class, it makes sense of the returned message
 *  and takes appropriate action.
 */

public class RegistryMessenger {
	private int registryPort = Config.getRegistryPort();
	private String registryAddress = Config.getRegistryAddress();

	public RegistryMessenger(int port, String registryAddress) {
		this.registryPort = port;
		this.registryAddress = registryAddress;
	}

	public RegistryMessenger(){

	}

	/* lookup the RemoteObject with the passed ObjectID  */ 
	public RemoteObjectReference lookup(String requestObjectid) throws Exception {

		RegistryRequestMessage messageToSend = new RegistryRequestMessage(
				Command.LOOKUP, requestObjectid, null);

		RemoteObjectReference returnedObject = null;
		ReturnMessage returnedMessage = communicateToRegistryServer(messageToSend);
		/* Get the message returned by the RegistryServer */
		returnedObject = (RemoteObjectReference) returnedMessage
				.getReturnValue();
		return returnedObject;
	}

	/* Bind the object to the registry */
	public void bind(RemoteObjectReference objReference) throws Exception {
		/* Bind the object to the ID on the registry */
		RegistryRequestMessage messageToSend = new RegistryRequestMessage(
				Command.BIND, objReference.getObjectId(), objReference);

		ReturnMessage returnedMessage = communicateToRegistryServer(messageToSend);

		/* Check if the binding went fine */
		if (!returnedMessage.isException()) {
			System.out.println("Successfully bind object with id: "
					+ objReference.getObjectId());
		}
	}
	
	/*List out all the objects that are available with the registry */
	public String list() throws Exception{
		RegistryRequestMessage messageToSend = new RegistryRequestMessage(
				Command.LIST, null, null);
		ReturnMessage returnedMessage = communicateToRegistryServer(messageToSend);
		if (!returnedMessage.isException()) {
			String avList = (String) returnedMessage.getReturnValue();
			return avList;
		}
		else
			return null;
	}

	/* 
	 * Helper function to communicate with the Registry Server .
	 * Marshalls the RegistryRequestMessage and then Demarshalls
	 * the returnedMessage from the registry.
	 */
	private ReturnMessage communicateToRegistryServer(
			RegistryRequestMessage message) throws Exception {

		Socket sock = null;
		ReturnMessage returnedMessage = null;
		
		try {
			sock = new Socket(registryAddress, registryPort);
			ObjectOutputStream oos = new ObjectOutputStream(
					sock.getOutputStream());
			oos.writeObject(message);
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			returnedMessage = (ReturnMessage) ois.readObject();

			if (returnedMessage.isException()) {
				throw (CustomRemoteException) returnedMessage.getReturnValue();
			}

			oos.close();
			ois.close();
			sock.close();
		} catch (CustomRemoteException e) {
			throw e;
		} catch (Exception RegistryMessengerException) {
			throw RegistryMessengerException;
		} finally {
			try {
				if (sock != null && !sock.isClosed()) {
					sock.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnedMessage;
	}

}
