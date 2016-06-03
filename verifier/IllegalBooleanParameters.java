package oop.ex6.verifier;


/**
 * Exception for illegal parameters for boolean statement.
 * 
 * @author Avichai, nimi
 *
 */
public class IllegalBooleanParameters extends BooleanStatementException {

	private static final String ILLEGAL_PARAMETERS_FOR_BOOLEAN_STATEMENT = 
			"\nillegal parameters for boolean statement";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + ILLEGAL_PARAMETERS_FOR_BOOLEAN_STATEMENT;
	}
}
