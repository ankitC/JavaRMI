package util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import exceptionSys.CustomRemoteException;

import registry.RegistryRequestMessage;
import registry.RemoteObjectReference;
import registry.ReturnMessage;


public class RegistryMessenger {
	private int registryPort = Config.getRegistryPort();
	private String registryHost = "localhost";

	public RegistryMessenger(int port, String registryHost) {
		this.registryPort = port;
		this.registryHost = registryHost;
	}
	
	public RegistryMessenger(){
		
	}

	public RemoteObjectReference lookup(String id) throws Exception {

		RegistryRequestMessage messageToSend = new RegistryRequestMessage(
				Command.LOOKUP, id, null);

		RemoteObjectReference returnedObject = null;
		ReturnMessage returnedMessage = communicateToRegistryServer(messageToSend);
		/* Get the message returned by the RegistryServer */
		returnedObject = (RemoteObjectReference) returnedMessage
				.getReturnValue();
		return returnedObject;
	}

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

	/* Helper function to communicate with the Registry Server */
	private ReturnMessage communicateToRegistryServer(
			RegistryRequestMessage message) throws Exception {

		Socket sock = null;
		ReturnMessage returnedMessage = null;
		/*Maybe have a method to do this seperately, doing the same somewhere else as well*/
		try {
			sock = new Socket(registryHost, registryPort);
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
		} catch (Exception e1) {
			// TODO: add logic for retries?
			throw e1;
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
