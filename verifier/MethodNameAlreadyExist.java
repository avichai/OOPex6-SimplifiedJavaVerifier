package oop.ex6.verifier;

/**
 * Exception for method name already exist.
 * 
 * @author Avichai, nimi
 *
 */
public class MethodNameAlreadyExist extends CodeFlowException {

	private static final String METHOD_NAME_ALREADY_EXIST = "\nMethod name already exist";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + METHOD_NAME_ALREADY_EXIST;
	}

}
