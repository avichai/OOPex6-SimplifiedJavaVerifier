package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.Variable;

/**
 * Class for verifing a if line.
 * 
 * @author Avichai, nimi
 *
 */
class IfVerifier implements VerifierInteface {

	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	@Override
	public void verify(String line) throws CodeSyntaxException {
		Matcher matcherStartIf = PoolOfRegex.IF_START.matcher(line);
		Matcher matcherEndIf = PoolOfRegex.IF_OR_WHILE_END.matcher(line);
		if (matcherStartIf.lookingAt() && matcherEndIf.find()) {
			String paramSeq = line.substring(matcherStartIf.end(),
					matcherEndIf.start());
			ConditionalParameterVerifier paramVerifier = PoolOfVerifiers.paramVerifier;
			paramVerifier.setGlobalVariables(this.globalVariable);
			paramVerifier.setLocalVariables(this.localVariable);
			paramVerifier.verify(paramSeq);
		} else {
			throw new IllegalSyntaxForBooleanStatement();
		}

	}

	/**
	 * setting the global variable arrayList field.
	 * 
	 * @param globalVariables that was declared on the outer scope.
	 */
	void setGlobalVariables(ArrayList<Variable> globalVariables) {
		this.globalVariable = globalVariables;
	}

	/**
	 * setting the local variable arrayList field.
	 * 
	 * @param localVariables that was declared inside the function in a more outer or the same scope.
	 */
	void setLocalVariables(ArrayList<Variable> localVariables) {
		this.localVariable = localVariables;
	}

}
