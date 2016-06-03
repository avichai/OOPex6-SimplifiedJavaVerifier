package oop.ex6.method;


/**
 * Exception for method should have ended but didn't.
 * @author Avichai
 *
 */
public class MethodSouldHaveEnded extends FunctionDecSyntaxException{


	private static final String METHOD_SHOULD_HAVE_ENDED_BUT_DIDN_T = 
			"\nmethod should have ended but didn't";
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return super.toString() + METHOD_SHOULD_HAVE_ENDED_BUT_DIDN_T;
	}
}
