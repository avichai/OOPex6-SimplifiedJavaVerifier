package oop.ex6.parsing;

/**
 * Represents an exception caused by a block of code that was not closed
 * properly.
 * 
 * @author Avichai, nimi
 *
 */
public class BlockNotClosedException extends ParserException {

	// The String representing the exception.
	private static final String REPRESENTATION = "BlockNotClosedException";

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + "\n" + REPRESENTATION;
	}
}