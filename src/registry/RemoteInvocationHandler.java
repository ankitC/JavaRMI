package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;
    private String objectID;

    public RemoteInvocationHandler(String host,int port, String objId){
        this.host = host;
        this.port = port;
        this.objectID = objId;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {

        RemoteInvocationMessage invokeMsg = new RemoteInvocationMessage(objectID,method.getName(),objects);

        Socket sock = null;
        Object returnObject = null;

        try {
            sock = new Socket(this.host,this.port);
            /*Marshall the Invokation Message*/
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            oos.writeObject(invokeMsg);
            
            /*Wait for the return Message and unmarhsall it*/
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            ReturnMessage returnedMessage = (ReturnMessage)ois.readObject();

            /* Get the value if the object retuned is not an exception */
            if(!returnedMessage.isException()) { 
            	returnObject = returnedMessage.getReturnValue();
            } else {
                throw (Exception) returnedMessage.getReturnValue();
            }
            
            ois.close();
            oos.close();
            sock.close();
        } catch (Exception e){ // If exception is returned, throw an exception
            throw e.getCause();
        }finally {
            if(sock != null) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnObject;
    }
}

