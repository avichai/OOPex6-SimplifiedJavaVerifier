package oop.ex6.method;


/**
 * Exception for bad return type of a function.
 * 
 * @author Avichai
 *
 */
public class BadReturnException extends FunctionDecSyntaxException {

	private static final String BAD_RETURN_TYPE_OF_A_FUNCTION = "\nbad return type of a function";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + BAD_RETURN_TYPE_OF_A_FUNCTION;
	}

}
