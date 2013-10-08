package registry;
import java.io.Serializable;

public class RemoteInvocationMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String objectId; /*Unique for each object*/
	private String methodName;
	private Object[] args;
	private String[] argTypes;

	/*Create a simple RIM*/
	public RemoteInvocationMessage(String objectId, String methodName,
			Object[] args) {
		
		this.objectId = objectId;
		this.methodName = methodName;
		this.args = args;

		String[] types = new String[args.length];
		for (int i = 0; i < types.length; i++) {
			types[i] = args[i].getClass().getName();
		}

		this.argTypes = types;
	}

	/*Setters Getters*/
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String[] getArgTypes() {
		return argTypes;
	}

	public void setArgTypes(String[] argTypes) {
		this.argTypes = argTypes;
	}

}
