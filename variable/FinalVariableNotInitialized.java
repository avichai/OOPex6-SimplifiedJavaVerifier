package oop.ex6.variable;

/**
 * Exception for final variable not initialized in declaration.
 * 
 * @author Avichai, nimi
 *
 */
public class FinalVariableNotInitialized extends VariablesDeclarationException {

	private static final String FINAL_VARIABLE_NOT_INITIALIZED_IN_DECLARATION = 
			"\nfinal variable not initialized in declaration";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + FINAL_VARIABLE_NOT_INITIALIZED_IN_DECLARATION;
	}

}
