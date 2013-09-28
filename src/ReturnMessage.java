import java.io.Serializable;

public class ReturnMessage implements Serializable{

	/*Returned for each RIM*/
	Object returnVal;
	boolean isException;

	public ReturnMessage(Object returnVal, boolean isException) {
		this.isException = isException;
		this.returnVal = returnVal;
	}

	public Object getReturnVal() {
		return returnVal;
	}

	public boolean isException() {
		return isException;
	}

}
