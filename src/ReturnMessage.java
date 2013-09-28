import java.io.Serializable;

public class ReturnMessage implements Serializable{

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
