package serverDatabases;
/* 
 * Simple class whose objects can be exchanged between the
 * client and the Server by remote invocation of methods. 
 */
import java.io.Serializable;

public class State implements Serializable {

	private String name;
	private String capital;

	public State(String name, String capital) {
		this.name = name;
		this.capital = capital;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}
	
	public String toString(){
		return capital;
	}
}


