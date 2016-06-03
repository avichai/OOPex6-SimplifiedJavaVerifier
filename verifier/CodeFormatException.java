package oop.ex6.verifier;

/**
 * Exception for bad format of the code.
 * 
 * @author Avichai, nimi
 *
 */
public class CodeFormatException extends CodeSyntaxException {

	private static final String FOR_BAD_FORMAT_OF_THE_CODE = "\nfor bad format of the code";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return super.toString() + FOR_BAD_FORMAT_OF_THE_CODE;
	}

}
