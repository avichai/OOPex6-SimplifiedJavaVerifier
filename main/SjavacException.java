package oop.ex6.main;

/**
 * Exception for problem in the Sjavac file.
 * 
 * @author Avichai, nimi
 *
 */
public class SjavacException extends Exception {

	private static final String PROBLEM_IN_THE_SJAVAC_FILE = "\nproblem in the Sjavac file";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_THE_SJAVAC_FILE;
	}
}
