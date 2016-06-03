package oop.ex6.main;

/**
 * Exception for problem in the code.
 * 
 * @author Avichai, nimi
 *
 */
public class CodeException extends SjavacException {

	private static final String PROBLEM_IN_THE_CODE = "\nproblem in the code";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_CODE;
	}
}
