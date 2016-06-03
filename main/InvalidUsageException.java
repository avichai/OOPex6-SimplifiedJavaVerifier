package oop.ex6.main;

/**
 * Represents an exception caused by invalid program arguments.
 * 
 * @author Avichai, nimi
 *
 */
public class InvalidUsageException extends ProgramException {

	// The String representing the exception.
	private static final String REPRESENTATION = "InvalidUsageException";

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + "\n" + REPRESENTATION;
	}
}