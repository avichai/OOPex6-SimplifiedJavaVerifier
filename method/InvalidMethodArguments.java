package oop.ex6.method;

/**
 * Exception for invalid arguments given to a method.
 * 
 * @author Avichai, nimi
 *
 */
public class InvalidMethodArguments extends FunctionDecSyntaxException {

	private static final String MESSAGE = "\ninvalid method arguments.";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + MESSAGE;
	}
}