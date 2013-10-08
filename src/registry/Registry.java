package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import exceptionSys.CustomRemoteException;


import util.Config;

public class Registry {
	private HashMap<String, RemoteObjectReference> objectReferenceTable;
	private int registryPort;

	public Registry() {
		this.objectReferenceTable = new HashMap<String, RemoteObjectReference>();
	}

	public Registry(int port) {
		this.registryPort = port;
		this.objectReferenceTable = new HashMap<String, RemoteObjectReference>();
	}

	public void start() {
		try {
			ServerSocket inSocket = new ServerSocket(registryPort);
			Socket outSocket = new Socket();
			ReturnMessage returnMessage;

			while (true) {
				outSocket = inSocket.accept();

				ObjectInputStream ois = new ObjectInputStream(
						outSocket.getInputStream());
				RegistryRequestMessage inMsg = (RegistryRequestMessage) ois
						.readObject();

				switch (inMsg.getCommand()) {
				case LOOKUP:
					returnMessage = new ReturnMessage(
							objectReferenceTable.get(inMsg.getId()), false);
					break;

				case BIND:
					RemoteObjectReference ror = inMsg.getRemoteObject();
					if (ror != null) {
						if (objectReferenceTable.get(inMsg.getId()) == null) {
							objectReferenceTable.put(inMsg.getId(), ror);
							returnMessage = new ReturnMessage("OK", false);
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
		Registry server = new Registry(Config.getRegistrySocket());
		server.start();
	}

}
