package oop.ex6.variable;

/**
 * Exception for variable name not found.
 * 
 * @author Avichai, nimi
 *
 */
public class VariableNameNotDeclared extends VariableNameException {

	private static final String VARIABLE_NAME_NOT_FOUND = "\nvariable name not found";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + VARIABLE_NAME_NOT_FOUND;
	}
}
