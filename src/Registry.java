
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import registry.RegistryRequestMessage;
import registry.RemoteObjectReference;
import registry.ReturnMessage;

import exceptionSys.CustomRemoteException;

import util.Config;

/* 
 * Simple Registry Implementation for our RMI which stores the RemoteObjectReferences
 * which are registered by the server and passes them to the clients who request
 * for those objects.
 */

public class Registry {
	private HashMap<String, RemoteObjectReference> objectReferenceTable;
	private int registryPort = Config.getRegistryPort();

	public Registry() {
		this.objectReferenceTable = new HashMap<String, RemoteObjectReference>();
	}

	public Registry(int port) {
		this.registryPort = port;
		this.objectReferenceTable = new HashMap<String, RemoteObjectReference>();
	}

	public void start() {
		System.out.println("Starting the Registry...");
		try {
			ServerSocket inSocket = new ServerSocket(registryPort);
			Socket outSocket = new Socket();
			ReturnMessage returnMessage;
			/* Listen for nodes wanting to connect to the registry */
			while (true) {
				outSocket = inSocket.accept();

				ObjectInputStream ois = new ObjectInputStream(
						outSocket.getInputStream());
				RegistryRequestMessage inMsg = (RegistryRequestMessage) ois
						.readObject();
				/* Return message as per the command received */
				switch (inMsg.getCommand()) {

				/* Sends the list of objects available with the registry */
				case LIST:
					String refList = objectReferenceTable.keySet().toString();
					Object b = refList;
					returnMessage = new ReturnMessage(b, false);
					System.out.println(refList);
					break;
				
				/* Sends the ObjectReference requested for by the client */
				case LOOKUP:
					returnMessage = new ReturnMessage(
							objectReferenceTable.get(inMsg.getId()), false);
					break;
					
				/* 
				 * Binds the sent ObjectReference to the registry to serve it
				 * to the requesting clients later.
				 */
				case BIND:
					RemoteObjectReference ror = inMsg.getRemoteObject();
					if (ror != null) {
						if (objectReferenceTable.get(inMsg.getId()) == null) {
							objectReferenceTable.put(inMsg.getId(), ror);
							returnMessage = new ReturnMessage("OK", false);
							System.out.println("Added Object " + inMsg.getId()
									+ " to the registry");

						} else {
							returnMessage = new ReturnMessage(
									new CustomRemoteException("Object with id "
											+ inMsg.getId() + " already bound"),
									true);
						}
					} else {
						returnMessage = new ReturnMessage(
								new CustomRemoteException(
										"Null Object cannot be bound"), true);
					}
					break;
				
				/* Facilitates rebinding of the object with the registry*/
				case REBIND:
					RemoteObjectReference rebindObject = inMsg
							.getRemoteObject();
					if (rebindObject != null) {
						if (objectReferenceTable.get(inMsg.getId()) == null) {
							returnMessage = new ReturnMessage(
									new CustomRemoteException("Object with id "
											+ inMsg.getId() + " not present."),
									true);

						} else {
							objectReferenceTable.remove(inMsg.getId());
							objectReferenceTable.put(inMsg.getId(),
									rebindObject);
							returnMessage = new ReturnMessage("OK", false);
							System.out.println("Added Object " + inMsg.getId()
									+ " to the registry");

						}
					} else {
						returnMessage = new ReturnMessage(
								new CustomRemoteException(
										"Null Object cannot be bound"), true);
					}
					break;

				default:
					returnMessage = null;
				}

				ObjectOutputStream oos = new ObjectOutputStream(
						outSocket.getOutputStream());
				oos.writeObject(returnMessage);
				oos.flush();

				ois.close();
				oos.close();
				outSocket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Registry server = new Registry(Config.getRegistryPort());
		server.start();
	}

}
