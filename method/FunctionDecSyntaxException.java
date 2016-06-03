package oop.ex6.method;

/**
 * Exception for problem in the function's decleration syntax.
 * 
 * @author Avichai, nimi
 *
 */
public class FunctionDecSyntaxException extends FunctionDeclarationException {

	private static final String PROBLEM_IN_THE_FUNCTION_S_DECLERATION_SYNTAX = 
			"\nproblem in the function's decleration syntax";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_FUNCTION_S_DECLERATION_SYNTAX;
	}

}
