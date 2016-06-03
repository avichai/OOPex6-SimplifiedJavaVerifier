package oop.ex6.verifier;


/**
 * Exception for problem of declartion.
 * 
 * @author Avichai, nimi
 *
 */
public class DeclarationException extends CodeSyntaxException {

	private static final String PROBLEM_OF_DECLARTION = "\nproblem of declartion";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + PROBLEM_OF_DECLARTION;
	}
}
