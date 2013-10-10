package registry;
import java.io.Serializable;

public class ReturnMessage implements Serializable{

	/**
	 * Format of the return message. It holds the 
	 * returned value and defines if it is an exception
	 * or not.
	 */
	private static final long serialVersionUID = 1L;
	/*Returned for each RIM*/
	private Object returnValue;
	private boolean isException;

	public ReturnMessage(Object returnVal, boolean isException) {
		this.isException = isException;
		this.returnValue = returnVal;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public boolean isException() {
		return isException;
	}

}
