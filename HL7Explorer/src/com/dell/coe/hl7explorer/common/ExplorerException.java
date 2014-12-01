package com.dell.coe.hl7explorer.common;

/**
 * Throwable Exceptions 
 * @author Tikam Ahuja 
 */


public class ExplorerException extends Exception {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private String message = Constants.EMPTY_STRING;

	public ExplorerException() {
		super();
	}

	public ExplorerException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}
}
