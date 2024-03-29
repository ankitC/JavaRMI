package serverDatabases;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.rmi.Remote;

import registry.RemoteInvocationMessage;
import registry.ReturnMessage;

/* 
 * The execution agent is a runnable which does the method running for the skeleton.
 * It takes in the method invocation and communicates the result back to the calling
 * client.
 */
public class ExecutionAgent implements Runnable{

    private RemoteInvocationMessage RemoteMessage;
    private Remote localObj;
    private Socket client;

    public ExecutionAgent(RemoteInvocationMessage message, Remote remoteObject, Socket client){
        this.RemoteMessage = message;
        this.localObj = remoteObject;
        this.client = client;
    }

    /* Execution agent calls the method as if a local invocation was happening */
    @Override
    public void run() {

        String[] types = RemoteMessage.getArgTypes();
        Class[] paraTypes = new Class[types.length];

        for(int i = 0; i < types.length; i++){
            try {
                paraTypes[i] = Class.forName(types[i]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        /* Determine the method to invoke and get the return value */
        Method method = null;
        try {
            method = localObj.getClass().getMethod(RemoteMessage.getMethodName(),paraTypes);

            Object returnVal = null;
            boolean hasException = false;
            try{
                returnVal = method.invoke(localObj,RemoteMessage.getArgs());
            }catch (Exception e){
                hasException = true;
                returnVal = e;
            }
            /* Communicating the return value to the client */
            ReturnMessage rm = new ReturnMessage(returnVal,hasException);

            try{
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(rm);
                oos.flush();
                oos.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(!client.isClosed()){
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
