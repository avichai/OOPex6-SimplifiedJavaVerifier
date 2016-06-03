package oop.ex6.variable;

/**
 * Exception for bad input for variable.
 * 
 * @author Avichai, nimi
 *
 */
public class InvalidInputForVariable extends VariablesDeclarationException {

	private static final String BAD_INPUT_FOR_VARIABLE = "\nbad input for variable";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + BAD_INPUT_FOR_VARIABLE;
	}
}
