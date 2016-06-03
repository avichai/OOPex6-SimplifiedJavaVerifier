package oop.ex6.verifier;

/**
 * Exception for trying to call a method poorly.
 * 
 * @author Avichai, nimi
 *
 */
public class MethodCallingException extends CodeFlowException {

	private static final String TRYING_TO_CALL_A_METHOD_POORLY = "\ntrying to call a method poorly";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + TRYING_TO_CALL_A_METHOD_POORLY;
	}
}
