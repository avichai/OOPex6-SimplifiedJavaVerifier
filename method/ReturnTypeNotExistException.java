package oop.ex6.method;


/**
 * Exception for function's return type not exist.
 * 
 * @author Avichai, nimi
 *
 */
public class ReturnTypeNotExistException extends FunctionDecSyntaxException {

	private static final String FUNCTION_S_RETURN_TYPE_NOT_EXIST = "\nfunction's return type not exist";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + FUNCTION_S_RETURN_TYPE_NOT_EXIST;
	}
}
