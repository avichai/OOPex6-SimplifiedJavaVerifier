package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableNameNotDeclared;
import oop.ex6.variable.VariableNotInitialized;
import oop.ex6.variable.VariablesTypeException;

/**
 * static method that are mutual to many verifiers.
 * 
 * @author Avichai, nimi
 *
 */
class StaticVerifiersMethods {
	
	/*
	 * Checks if an argument fits to a specified type.
	 * 
	 * throws VariableNotInitialized the assignd variable is not initialized.
	 * throws InvalidInputForVariable bad input for variable.
	 */
	static void checkIfValidType(ArrayList<Variable> globalVariables,
			ArrayList<Variable> localVariables, String argument, String argType)
			throws VariableNotInitialized, VariableNameNotDeclared,VariablesTypeException {
		Matcher typeMatcher = null;
		switch (argType) {
		case Constant.INTEGER_CONS:
			typeMatcher = PoolOfRegex.INT.matcher(argument);
			break;
		case Constant.BOOLEAN_CONS:
			typeMatcher = PoolOfRegex.BOOLEAN.matcher(argument);
			break;
		case Constant.DOUBLE_CONST:
			typeMatcher = PoolOfRegex.DOUBLE.matcher(argument);
			break;
		case Constant.CHAR_CONS:
			typeMatcher = PoolOfRegex.CHAR.matcher(argument);
			break;
		case Constant.STRING_CONS:
			typeMatcher = PoolOfRegex.STRING.matcher(argument);
			break;
		}
		if (!typeMatcher.matches()) {
			checkVariable(globalVariables, localVariables, argument, argType);
		}
	}

	/*
	 * Checks if the relevant variable known to the program, fits the specified
	 * type, and it is also initialized.
	 */
	private static void checkVariable(ArrayList<Variable> globalVariables,
			ArrayList<Variable> localVariables, String variable, String varType)
			throws VariableNameNotDeclared, VariableNotInitialized,VariablesTypeException {
		Variable searchedVar = searchVariable(globalVariables, localVariables, variable);
		if (searchedVar != null) {
			if (searchedVar.isInitialized()) {
				if (!searchedVar.getType().equals(varType)) {
					if (searchedVar.getType().equals(Constant.INTEGER_CONS) && 
														varType.equals(Constant.DOUBLE_CONST)) {
						throw new VariablesTypeException();
					}
				}
			} else {
				throw new VariableNotInitialized();
			}
		} else {
			throw new VariableNameNotDeclared();
		}
	}

	/*
	 * searches for a variable with the specified name in the localVariables and
	 * globalVariables lists. Returns the variable that is declared in the
	 * deepest scope from all the known variables with this name. If there is no
	 * known variable with this name, returns null.
	 */
	static Variable searchVariable(ArrayList<Variable> globalVariables,
			ArrayList<Variable> localVariables, String name) {
		if (name == null) {
			return null;
		}
		Variable searchedVariable = null;
		if (globalVariables != null) {
			for (Variable var : globalVariables) {
				if (name.equals(var.getName())) {
					searchedVariable = var;
				}
			}
		}
		if (localVariables != null) {
			for (Variable var : localVariables) {
				if (name.equals(var.getName())) {
					if ((searchedVariable == null) || 
						(searchedVariable != null && searchedVariable.getDepth() <= var.getDepth())){
						searchedVariable = var;
					}
				}
			}
		}
		return searchedVariable;
	}

	/*
	 * Checks if a variable holds a boolean value.
	 */
	static boolean checkBoolean(Variable variable) {
		if (variable != null) {
			if (variable.isInitialized() && variable.getType().equals(PoolOfRegex.BOOLEAN_TYPE)) {
				return true;
			}
		}
		return false;
	}
}
