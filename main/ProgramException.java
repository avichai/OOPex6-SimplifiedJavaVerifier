package oop.ex6.main;

/**
 * Exception for problem with the functionality of the program.
 * 
 * @author Avichai, nimi
 *
 */
public class ProgramException extends SjavacException {

	private static final String PROBLEM_WITH_THE_FUNCTIONALITY_OF_THE_PROGRAM = 
			"\nproblem with the functionality of the program";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_WITH_THE_FUNCTIONALITY_OF_THE_PROGRAM;
	}

}
