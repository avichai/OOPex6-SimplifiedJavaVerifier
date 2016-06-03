package oop.ex6.variable;

/**
 * Exception for problem in variable type.
 * 
 * @author Avichai, nimi
 *
 */
public class VariablesTypeException extends VariablesDeclarationException {

	private static final String PROBLEM_IN_VARIABLE_TYPE = "\nproblem in variable type";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_VARIABLE_TYPE;
	}
}
