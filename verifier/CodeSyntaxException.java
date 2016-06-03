package oop.ex6.verifier;

import oop.ex6.main.CodeException;

/**
 * Exception for problem in the code Syntax.
 * 
 * @author Avichai, nimi
 *
 */
public class CodeSyntaxException extends CodeException {

	private static final String PROBLEM_IN_THE_CODE_SYNTAX = "\nproblem in the code Syntax";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_CODE_SYNTAX;
	}
}
