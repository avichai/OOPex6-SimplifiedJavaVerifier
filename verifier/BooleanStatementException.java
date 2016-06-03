package oop.ex6.verifier;



/**
 * Exception for problem in boolean statement.(i.e if/while statement).
 * 
 * @author Avichai, nimi
 *
 */
public class BooleanStatementException extends DeclarationException {

	private static final String PROBLEM_IN_BOOLEAN_STATEMENT = "\nproblem in boolean statement";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_IN_BOOLEAN_STATEMENT;
	}

}
