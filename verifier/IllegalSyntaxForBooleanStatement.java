package oop.ex6.verifier;


/**
 * Exception for illegal syntax of boolean statement.
 * 
 * @author Avichai, nimi
 *
 */
public class IllegalSyntaxForBooleanStatement extends BooleanStatementException {

	private static final String ILLEGAL_SYNTAX_OF_BOOLEAN_STATEMENT = 
			"\nillegal syntax of boolean statement";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + ILLEGAL_SYNTAX_OF_BOOLEAN_STATEMENT;
	}
}
