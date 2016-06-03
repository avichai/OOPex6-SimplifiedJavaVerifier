package oop.ex6.variable;


/**
 * Exception for invalid variable type.
 * 
 * @author Avichai, nimi
 *
 */
public class InvalidTypeException extends VariablesTypeException {

	private static final String INVALID_VARIABLE_TYPE = "\ninvalid variable type";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + INVALID_VARIABLE_TYPE;
	}

}
