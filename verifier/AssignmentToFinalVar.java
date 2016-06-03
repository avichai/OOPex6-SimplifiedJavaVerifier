package oop.ex6.verifier;

/**
 * Exception for trying to assign some value to a variable that is final.
 * 
 * @author Avichai, nimi
 * 
 */
public class AssignmentToFinalVar extends CodeFlowException {

	private static final String TRYING_TO_ASSIGN_SOME_VALUE_TO_A_VARIABLE_THAT_IS_FINAL = 
			"\ntrying to assign some value to a variable that is final";
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()
				+ TRYING_TO_ASSIGN_SOME_VALUE_TO_A_VARIABLE_THAT_IS_FINAL;
	}

}
