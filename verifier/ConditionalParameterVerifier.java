package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.Variable;

/*
 * A verifier for a conditional parameter. 
 */
class ConditionalParameterVerifier implements VerifierInteface {

	// Lists of the global and local variables the program knows.
	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	/*
	 * Setter for the global variables list.
	 */
	void setGlobalVariables(ArrayList<Variable> globalVariables) {
		this.globalVariable = globalVariables;
	}

	/*
	 * Setter for the local variables list.
	 */
	void setLocalVariables(ArrayList<Variable> localVariables) {
		this.localVariable = localVariables;
	}

	@Override
	public void verify(String line) throws IllegalBooleanParameters {
		// splits the line to the conditions used.
		String[] parameters = line.split(PoolOfRegex.CONDITIONS_SEPARATOR);
		for (String param : parameters) {
			param = param.trim();
			Matcher booleanMatcher = PoolOfRegex.BOOLEAN.matcher(param);
			if (!booleanMatcher.matches()) {
				Variable knownParameter = StaticVerifiersMethods.searchVariable(this.globalVariable,
																			this.localVariable, param);
				if (!StaticVerifiersMethods.checkBoolean(knownParameter)) {
					throw new IllegalBooleanParameters();
				}
			}
		}
	}

}