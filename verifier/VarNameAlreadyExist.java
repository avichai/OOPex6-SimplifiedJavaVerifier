package oop.ex6.verifier;


/**
 * Exception for using the same name for a variables more than once.
 * 
 * @author Avichai, nimi
 *
 */
public class VarNameAlreadyExist extends CodeFlowException {

	private static final String VARIABLE_NAME_ALREADY_USED = "\nvariable name already used";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + VARIABLE_NAME_ALREADY_USED;
	}
}
