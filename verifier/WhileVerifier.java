package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.Variable;

/**
 * Class for verifing a while line.
 * 
 * @author Avichai, nimi
 *
 */
class WhileVerifier implements VerifierInteface {

	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	@Override
	public void verify(String line) throws CodeSyntaxException {
		Matcher matcherStartWhile = PoolOfRegex.WHILE_START.matcher(line);
		Matcher matcherEndWhile = PoolOfRegex.IF_OR_WHILE_END.matcher(line);
		if (matcherStartWhile.lookingAt() && matcherEndWhile.find()) {
			String paramSeq = line.substring(matcherStartWhile.end(), matcherEndWhile.start());
			ConditionalParameterVerifier paramVerifier = PoolOfVerifiers.paramVerifier;
			paramVerifier.setGlobalVariables(this.globalVariable);
			paramVerifier.setLocalVariables(this.localVariable);
			paramVerifier.verify(paramSeq);
		} else {
			throw new CodeSyntaxException();
		}
	}

	/**
	 * setting the global variable arrayList field.
	 * 
	 * @param globalVariables
	 *            that was declared on the outer scope.
	 */
	void setGlobalVariables(ArrayList<Variable> globalVariables) {
		this.globalVariable = globalVariables;
	}

	/**
	 * setting the local variable arrayList field.
	 * 
	 * @param localVariables
	 *            that was declared inside the function in a more outer or the
	 *            same scope.
	 */
	void setLocalVariables(ArrayList<Variable> localVariables) {
		this.localVariable = localVariables;
	}
}
