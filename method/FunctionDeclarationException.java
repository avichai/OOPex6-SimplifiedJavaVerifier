package oop.ex6.method;

import oop.ex6.verifier.DeclarationException;

/**
 * Exception for problem in the function's Declaration.
 * 
 * @author Avichai, nimi
 *
 */
public class FunctionDeclarationException extends DeclarationException {

	private static final String PROBLEM_IN_THE_FUNCTION_S_DECLARATION = 
			"\nproblem in the function's Declaration";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_FUNCTION_S_DECLARATION;
	}
}
