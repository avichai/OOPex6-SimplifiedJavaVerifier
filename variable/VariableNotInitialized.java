package oop.ex6.variable;

/**
 * Exception for assigning variable that was not initialized.
 * 
 * @author Avichai, nimi
 *
 */
public class VariableNotInitialized extends VariablesDeclarationException {

	private static final String ASSIGNING_VARIABLE_THAT_WAS_NOT_INITIALIZED = 
			"\nassigning variable that was not initialized";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + ASSIGNING_VARIABLE_THAT_WAS_NOT_INITIALIZED;
	}
}
