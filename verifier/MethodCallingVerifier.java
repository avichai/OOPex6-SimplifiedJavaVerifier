package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.method.*;
import oop.ex6.variable.*;

/*
 * A verifier for a method call line. 
 */
class MethodCallingVerifier implements VerifierInteface {

	// A list of the Method objects the program knows.
	private ArrayList<Method> methods;

	// Lists of the local and global variables the program knows.
	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	@Override
	public void verify(String line) throws MethodNotExistException,
			VariableNameNotDeclared, VariableNotInitialized,
			VariablesTypeException, FunctionDeclarationException,
			MethodCallingException {
		if (this.methods != null) {
			Matcher legalCallMatcher = PoolOfRegex.LEGAL_METHOD_CALL
					.matcher(line);
			if (legalCallMatcher.matches()) {
				vrifyLegalCallLine(line);
			} else {
				throw new MethodCallingException();
			}
		} else {
			throw new MethodNotExistException();
		}
	}

	/*
	 * verifies a method call line that has a good syntax.
	 */
	private void vrifyLegalCallLine(String line)
			throws VariableNameNotDeclared, VariableNotInitialized,
			VariablesTypeException, InvalidMethodArguments {
		line.trim();
		String methodName = findName(line);
		String[] callArguments = findArgs(line);
		Method matchMethod = findMethod(this.methods, methodName);
		if (matchMethod != null) {
			ArrayList<MethodParameter> methodParams = matchMethod.getMethodParam();
			if (methodParams != null && callArguments != null) {
				if (methodParams.size() == callArguments.length) {
					for (int i = 0; i < methodParams.size(); i++) {
						StaticVerifiersMethods.checkIfValidType(this.globalVariable,this.localVariable, 
														callArguments[i],methodParams.get(i).getType());
					}
				} else {
					throw new InvalidMethodArguments();
				}
			} else {
				if (callArguments != null) {
					throw new InvalidMethodArguments();
				}
			}
		}
	}

	/*
	 * Searches for a method with the specified name in methods list. If no such
	 * method exists, returns null.
	 */
	private static Method findMethod(ArrayList<Method> methods, String name) {
		if (name == null) {
			return null;
		}
		for (Method method : methods) {
			if (name.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	/*
	 * Returns the name of the method in a legal method call line.
	 */
	private static String findName(String line) {
		Matcher nameMatcher = PoolOfRegex.NAME.matcher(line);
		nameMatcher.find();
		String methodName = line.substring(nameMatcher.start(),nameMatcher.end());
		return methodName;
	}

	/*
	 * Creates a list of the arguments given to the method.
	 */
	private static String[] findArgs(String line) {
		Matcher argsMatcher = PoolOfRegex.PARAMETERS.matcher(line);
		argsMatcher.find();
		String argsString = line.substring(argsMatcher.start() + 1,argsMatcher.end() - 1);
		if (argsString.equals(Constant.EMPTY)) {
			return null;
		}
		String[] argsList = argsString.split(Constant.COMMA);
		return argsList;
	}

	/*
	 * Setter for methods.
	 */
	void setMethods(ArrayList<Method> methods1) {
		this.methods = methods1;
	}

	/*
	 * Setter for globalVariables.
	 */
	void setGlobalVariables(ArrayList<Variable> globalVariables) {
		this.globalVariable = globalVariables;
	}

	/*
	 * Setter for localVariables.
	 */
	void setLocalVariables(ArrayList<Variable> localVariables) {
		this.localVariable = localVariables;
	}
}
