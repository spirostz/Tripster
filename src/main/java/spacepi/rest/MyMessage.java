package spacepi.rest;

import java.io.Serializable;

public class MyMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1281873460950505035L;
	
	private String message;

	
	public MyMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
