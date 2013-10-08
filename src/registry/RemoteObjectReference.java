package registry;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RemoteObjectReference implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ipAddress;
    private int port;
    private String objectId;
    private String remoteInterfaceName;
    
    public RemoteObjectReference(){
    	
    }
    
    public RemoteObjectReference(String ipaddr, int port, String objID, String remoteIntName) {
		this.ipAddress = ipaddr;
		this.port = port;
		this.objectId = objID;
		this.remoteInterfaceName = remoteIntName;
    }

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getRemoteInterfaceName() {
		return remoteInterfaceName;
	}

	public void setRemoteInterfaceName(String remoteInterfaceName) {
		this.remoteInterfaceName = remoteInterfaceName;
	}
   
	/*Returns the proxyObject for the requested Object/Interface*/
    public Object getStub() throws ClassNotFoundException{
    	InvocationHandler handler = new RemoteInvocationHandler(this.ipAddress,this.port,this.objectId);

        Class<?> className = Class.forName(this.remoteInterfaceName);

        Object proxyObject =  Proxy.newProxyInstance(
                className.getClassLoader(),
                new Class[]{className},
                handler);

        return proxyObject;
    }
}
