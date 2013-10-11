package serverDatabases;

import registry.RemoteInvocationMessage;
import registry.RemoteObjectReference;
import util.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerRMIHelper {

	private int port = Config.getServerPort(); 
	private HashMap<String, Remote> exportedObjectReferences;

	public ServerRMIHelper() {
		this.exportedObjectReferences = new HashMap<String, Remote>();
	}

	public ServerRMIHelper(int port) {
		this.port = port;
		this.exportedObjectReferences = new HashMap<String, Remote>();
	}


	/*
	 * Creates the RemoteObjectReference which will be registered with the 
	 * registry. The ID with which the object wants to be registred as 
	 * and the interface to be exported to the remote client
	 * is used to create the RemoteObjectReference. 
	 */
	public RemoteObjectReference createRemoteObjectReference(String id, String remoteInterface, Remote obj) throws UnknownHostException {

		this.exportedObjectReferences.put(id,obj);
		String ip = InetAddress.getLocalHost().getHostAddress();
		return new RemoteObjectReference(ip,this.port,id,remoteInterface);
	}


	/**
	 * This method starts serving RMI requests for remote objects on this server. 
	 * Once the server code is done registering all the remote objects it has 
	 * to offer with the dispatcher and registry,
	 * this method is started. It listens on the port for any incoming RMI requests,
	 * unmarshalls the message, calls the requested method on the requested remote 
	 * object and creates replies back to the requester with the results. 
	 */
	public void handleRMIRequests() {

		Executor executor = Executors.newCachedThreadPool();
		executor.execute(
                new Runnable() {
                    public void run() {
                        ServerSocket serverSock;
                        try {
                            serverSock = new ServerSocket(port);
                            String ip = InetAddress.getLocalHost().getHostAddress();
                            System.out.println(ip);
                            Executor executor = Executors.newCachedThreadPool();
                            while (true) {
                                try {
                                    Socket client = serverSock.accept();
                                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                                    RemoteInvocationMessage imsg = (RemoteInvocationMessage) ois.readObject();

                                    Remote obj = exportedObjectReferences.get(imsg.getObjectId());
                                    executor.execute(new ExecutionAgent(imsg, obj, client));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

	}

}


