package oop.ex6.verifier;

/**
 * Exception for calling a method that is not exist.
 * 
 * @author Avichai, nimi
 *
 */
public class MethodNotExistException extends CodeFlowException {

	private static final String CALLING_A_METHOD_THAT_IS_NOT_EXIST = "\ncalling a method that is not exist";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + CALLING_A_METHOD_THAT_IS_NOT_EXIST;
	}
}
