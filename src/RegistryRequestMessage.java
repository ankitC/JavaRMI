/*Format of message in which the clients communicate with the registry*/

import java.io.Serializable;

public class RegistryRequestMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Command command;
	private String id;
	private RemoteObjectReference remoteObject;
	
		
	public RegistryRequestMessage(Command command, String id,
			RemoteObjectReference remoteObject) {
		super();
		this.command = command;
		this.id = id;
		this.remoteObject = remoteObject;
	}
	
	
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RemoteObjectReference getRemoteObject() {
		return remoteObject;
	}
	public void setRemoteObject(RemoteObjectReference remoteObject) {
		this.remoteObject = remoteObject;
	}
	
	

}
