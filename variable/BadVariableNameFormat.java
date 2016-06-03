package oop.ex6.variable;


/**
 * Exception for bad variable name format.
 * 
 * @author Avichai, nimi
 *
 */
public class BadVariableNameFormat extends VariableNameException {

	private static final String BAD_VARIABLE_NAME_FORMAT = "\nbad variable name format";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + BAD_VARIABLE_NAME_FORMAT;
	}
}
