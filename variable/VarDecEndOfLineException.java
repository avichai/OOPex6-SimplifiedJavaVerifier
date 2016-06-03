package oop.ex6.variable;

/**
 * Exception for wrong ending of variable declaration.
 * 
 * @author Avichai, nimi
 *
 */
public class VarDecEndOfLineException extends VariablesDeclarationException {

	private static final String WRONG_ENDING_OF_VARIABLE_DECLARATION = "\nwrong ending of variable declaration";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + WRONG_ENDING_OF_VARIABLE_DECLARATION;
	}
}
