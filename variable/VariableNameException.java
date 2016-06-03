package oop.ex6.variable;

/**
 * Exception for problem in the variable name.
 * 
 * @author Avichai, nimi
 *
 */
public class VariableNameException extends VariablesDeclarationException {

	private static final String PROBLEM_IN_THE_VARIABLE_NAME = "\nproblem in the variable name";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_VARIABLE_NAME;
	}
}
