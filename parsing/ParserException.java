package oop.ex6.parsing;

import oop.ex6.main.CodeException;

/**
 * Represents an exception occurred in the parsing process.
 * 
 * @author  Avichai, nimi
 *
 */
public class ParserException extends CodeException {

	// The String representing the exception.
	private static final String REPRESENTATION = "ParserException";

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + "\n" + REPRESENTATION;
	}
}