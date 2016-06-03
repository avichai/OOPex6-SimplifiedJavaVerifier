package oop.ex6.variable;

import oop.ex6.verifier.DeclarationException;


/**
 * Exception for problem in the variable declaration.
 * 
 * @author Avichai, nimi
 *
 */
public class VariablesDeclarationException extends DeclarationException {

	private static final String PROBLEM_IN_THE_VARIABLE_DECLARATION = "\nproblem in the variable declaration";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_VARIABLE_DECLARATION;
	}
}
