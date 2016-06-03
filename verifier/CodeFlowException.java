package oop.ex6.verifier;

import oop.ex6.main.CodeException;

/**
 * Exception for code flow Exception.
 * 
 * @author Avichai, nimi
 *
 */
public class CodeFlowException extends CodeException {

	private static final String CODE_FLOW_EXCEPTION = "\ncode flow Exception";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + CODE_FLOW_EXCEPTION;
	}

}
