package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.method.BadReturnException;
import oop.ex6.method.FunctionDecSyntaxException;
import oop.ex6.method.FunctionDeclarationException;
import oop.ex6.method.Method;
import oop.ex6.method.MethodParameter;
import oop.ex6.method.ReturnTypeNotExistException;
import oop.ex6.variable.BadVariableNameFormat;
import oop.ex6.variable.FinalVariableNotInitialized;
import oop.ex6.variable.InvalidInputForVariable;
import oop.ex6.variable.VarDecEndOfLineException;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableNameException;
import oop.ex6.variable.VariableNameNotDeclared;
import oop.ex6.variable.VariableNotInitialized;
import oop.ex6.variable.VariablesTypeException;

/**
 * This class verifies lines of code accordingly to the Sjavac language.
 * 
 * @author Avichai, nimi
 *
 */
public class Verifier {

	private static final int MIN_DEPTH_FOR_METHOD_VAR = 1;

	private static final String RETURN = "return";

	private static final String CLOSING_BRACKET = "}";

	private ArrayList<Variable> globalVariable;

	private ArrayList<Variable> localVariables;

	private ArrayList<Method> methods;

	/**
	 * creates a new verifier. initialize fields.
	 */
	public Verifier() {
		this.globalVariable = new ArrayList<Variable>();
		this.methods = new ArrayList<Method>();
		this.localVariables = new ArrayList<Variable>();
	}

	/**
	 * Setting all the global variables and verifies that the lines are valid.
	 * 
	 * @param outerScope
	 *            arraylist of string, each string represent a line of variable
	 *            declaration.
	 * @throws VarNameAlreadyExist
	 *             the code has two global variables with the same name.
	 * @throws InvalidInputForVariable
	 *             the code has assigned poorly to one of the variables.
	 * @throws VariableNotInitialized
	 *             the code has assigned an uninitialized variable to another.
	 * @throws VarDecEndOfLineException
	 *             the cod has ended a line poorly.
	 * @throws VariablesTypeException
	 *             the code try to use bad type for a variable.
	 * @throws VariableNameException
	 *             the code try to declare var with illegal name.
	 * @throws FinalVariableNotInitialized
	 *             final variable not initialized in declaration
	 */
	public void setGlobalVeriables(ArrayList<String> outerScope)
			throws VarNameAlreadyExist, InvalidInputForVariable, VariableNotInitialized,
			VarDecEndOfLineException, VariablesTypeException, VariableNameException,
			FinalVariableNotInitialized {
		for (String variableDec : outerScope) {
			VariableVerifier variableVerifier = PoolOfVerifiers.variableVerifier;
			variableVerifier.setGlobalVariables(this.globalVariable);
			variableVerifier.setLocalVariables(new ArrayList<Variable>());
			variableVerifier.verify(variableDec);
			ArrayList<String> varNames = variableVerifier.getVarNames();
			ArrayList<String> varTypes = variableVerifier.getVarTypes();
			ArrayList<Boolean> varInitialized = variableVerifier.getVarInitialized();
			ArrayList<Boolean> varIsFinal = variableVerifier.getVarIsFinal();

			for (int i = 0; i < varNames.size(); i++) {
				for (Variable variable : this.globalVariable) {
					if (variable.getName().equals(varNames.get(i))) {
						throw new VarNameAlreadyExist();
					}
				}
				this.globalVariable.add(new Variable(varTypes.get(i), varNames.get(i),
						      				varInitialized.get(i), 0, varIsFinal.get(i)));
			}
		}

	}

	/**
	 * Setting all of the available methods to the verifier instance and
	 * verifies that the lines are valid.
	 * 
	 * @param methodsLine
	 *            arraylist of string, each string represent a method
	 *            declaration line.
	 * @throws MethodNameAlreadyExist
	 *             the code has two method with the same name
	 * @throws FunctionDecSyntaxException
	 *             the code declare poorly a function.
	 * @throws VarNameAlreadyExist
	 *             declaring to vars with the same name
	 */
	public void setMethods(ArrayList<String> methodsLine)
			throws MethodNameAlreadyExist, FunctionDecSyntaxException, VarNameAlreadyExist {
		for (String methodDec : methodsLine) {
			MethodDeclarationVerifier methodVerifier = PoolOfVerifiers.methodVerifier;
			methodVerifier.verify(methodDec);
			for (Method method : this.methods) {
				if (method.getName().equals(methodVerifier.gedMethodName())) {
					throw new MethodNameAlreadyExist();
				}
			}
			String methodName = new String(methodVerifier.gedMethodName());
			ArrayList<MethodParameter> methodParams = new ArrayList<>();
			if (methodVerifier.getMethodParam() != null) {
				for (MethodParameter param : methodVerifier.getMethodParam()) {
					methodParams.add(new MethodParameter(param.getType(), param.isFinal(),
									  param.getName()));
				}
				this.methods.add(new Method(methodName, methodParams));
				methodVerifier.reset();
			} else {
				this.methods.add(new Method(methodName, null));
			}

		}

	}

	/**
	 * verifies all the inner scopes.
	 * 
	 * @param scopes
	 *            each array is are all the lines of one method.
	 * @throws VarNameAlreadyExist
	 *             the code tries to create local variable in the same scope
	 *             were there is another variable with the same name.
	 * @throws CodeSyntaxException
	 *             problem on the syntax of the code.
	 * @throws MethodNotExistException
	 *             trying to use a method that does not exist.
	 * @throws AssignmentToFinalVar
	 *             trying to assign to a variable that is final.
	 * @throws MethodCallingException
	 *             trying to call a method poorly
	 */
	public void checkScopes(ArrayList<ArrayList<String>> scopes)
			throws VarNameAlreadyExist, MethodNotExistException,
			CodeSyntaxException, AssignmentToFinalVar, MethodCallingException {
		int numMethod = 0;
		for (ArrayList<String> scope : scopes) {
			this.localVariables.clear();
			scope.remove(0);
			int numScope = 1;
			if (this.methods.get(numMethod).getMethodParam() != null) {
				ArrayList<MethodParameter> currentMethodParam = 
												this.methods.get(numMethod).getMethodParam();
				for (MethodParameter methodParam : currentMethodParam) {
					this.localVariables.add(new Variable(methodParam.getType(), methodParam.getName(),
							                 true, MIN_DEPTH_FOR_METHOD_VAR, methodParam.isFinal()));
				}
			}
			boolean doesReturn = verifyReturnLine(scope.get(scope.size() - 2));
			if (!doesReturn || !scope.get(scope.size() - 1).trim().equals("}")) {
				throw new ReturnTypeNotExistException();
			}
			for (String line : scope) {
				verifyLine(line, numScope);
			}
			numMethod++;
		}
	}

	/**
	 * Verifying a line of code.
	 * 
	 * @param line
	 *            the line to verify.
	 * @param numScope
	 *            the scope inside a function.(the most outside scope represent
	 *            with 1).
	 * @throws CodeSyntaxException
	 *             problem on the syntax of the code.
	 * @throws MethodNotExistException
	 *             trying to use a method that does not exist.
	 * @throws VarNameAlreadyExist
	 *             the code try to declare variable when another var on the same
	 *             scope has the same name.
	 * @throws AssignmentToFinalVar
	 *             trying to assign to a variable that is final.
	 * @throws MethodCallingException
	 *             trying to call a method poorly
	 */
	private void verifyLine(String line, int numScope)
			throws CodeSyntaxException, MethodNotExistException,
			VarNameAlreadyExist, AssignmentToFinalVar, MethodCallingException {
		if (line != null) {
			Matcher methodMatcher = PoolOfRegex.METHOD_CALL.matcher(line);
			Matcher matcherStartIf = PoolOfRegex.IF_START.matcher(line);
			Matcher matcherStartWhile = PoolOfRegex.WHILE_START.matcher(line);
			Matcher variableDecMatcher = PoolOfRegex.VAR_DEC_MATCHER.matcher(line);
			if (matcherStartIf.lookingAt()) { // if it's a if line
				verifyIfLine(line, numScope);
			} else if (matcherStartWhile.lookingAt()) { // if it's a while line
				verifyWhileLine(line, numScope);
			} else if (methodMatcher.lookingAt()) { // if it's a method call
				verifyMethodCallingLine(line);
			} else if (line.startsWith(CLOSING_BRACKET)) { // if it's a closing
															// line
				this.localVariables = closeScopeInsideMethod(numScope);
			} else if (line.startsWith(RETURN)) {
				verifyReturnLine(line);
			} else if (variableDecMatcher.lookingAt()) { // if it's a var dec.
				verifyVarDec(line, numScope);

			} else { // it's a var assignment
				verifyVarAssignment(line);
			}
		}
	}

	/**
	 * verify assignment to a variable line.
	 * 
	 * @param line
	 *            the line to verify.
	 * @throws BadVariableNameFormat
	 *             bad format for a variable name.
	 * @throws VariableNameNotDeclared
	 *             the code try to do action on some variable, but there is no
	 *             such variable.
	 * @throws VariableNotInitialized
	 *             the code try to assign a variable that is not initialized.
	 * @throws InvalidInputForVariable
	 *             the code tries to put invalid argument for a variable.
	 * @throws VarDecEndOfLineException
	 *             the line of code does not end correctly.
	 * @throws CodeFormatException
	 *             the code has some bad format in it.
	 * @throws VariablesTypeException
	 *             the code try to use bad type for a variable.
	 * @throws AssignmentToFinalVar
	 *             trying to assign to a variable that is final.
	 */
	private void verifyVarAssignment(String line) throws BadVariableNameFormat,
			VariableNameNotDeclared, VariableNotInitialized,
			InvalidInputForVariable, VarDecEndOfLineException,
			CodeFormatException, VariablesTypeException, AssignmentToFinalVar {
		VariableAssignmentVerifier variableAsignmentVerifier = PoolOfVerifiers.variableAsignmentVerifier;
		variableAsignmentVerifier.setGlobalVariable(this.globalVariable);
		variableAsignmentVerifier.setLocalVariable(this.localVariables);
		variableAsignmentVerifier.verify(line);
	}

	/**
	 * verify a variable declaration.
	 * 
	 * @param line
	 *            the line to verify.
	 * @param numScope
	 *            the scope inside a function.(the most outside scope represent
	 *            with 1).
	 * @throws VarNameAlreadyExist
	 *             the code tries to create local variable in the same scope
	 *             were there is another variable with the same name.
	 * @throws InvalidInputForVariable
	 *             the code tries to put invalid argument for a variable.
	 * @throws VariableNotInitialized
	 *             the code try to assign a variable that is not initialized.
	 * @throws VarDecEndOfLineException
	 *             the line of code does not end correctly.
	 * @throws VariablesTypeException
	 *             the code try to use bad type for a variable.
	 * @throws VariableNameException
	 *             the code try to declare var with illegal name.
	 * @throws FinalVariableNotInitialized
	 *             final variable not initialized in declaration
	 */
	private void verifyVarDec(String line, int numScope)
			throws VarNameAlreadyExist, InvalidInputForVariable,
			VariableNotInitialized, VarDecEndOfLineException,
			VariablesTypeException, VariableNameException,
			FinalVariableNotInitialized {
		VariableVerifier variableVerifier = PoolOfVerifiers.variableVerifier;
		variableVerifier.setGlobalVariables(this.globalVariable);
		variableVerifier.setLocalVariables(this.localVariables);
		variableVerifier.verify(line);

		ArrayList<String> varNames = variableVerifier.getVarNames();
		ArrayList<String> varTypes = variableVerifier.getVarTypes();
		ArrayList<Boolean> varInitialized = variableVerifier.getVarInitialized();
		ArrayList<Boolean> varIsFinal = variableVerifier.getVarIsFinal();
		for (int i = 0; i < varNames.size(); i++) {
			for (Variable variable : this.localVariables) {
				if (variable.getName().equals(varNames.get(i))) {
					if (variable.getDepth() == numScope) {
						throw new VarNameAlreadyExist();
					}
				}
			}
			this.localVariables.add(new Variable(varTypes.get(i), varNames.get(i),
							        varInitialized.get(i), numScope, varIsFinal.get(i)));
		}
	}

	/**
	 * verify return line.
	 * 
	 * @param line
	 *            the line to verify.
	 * @return boolean for knowing if we are suppose to be on the last line of
	 *         the function.
	 * @throws BadReturnException
	 *             the return line is incorrectly.
	 */
	private static boolean verifyReturnLine(String line)
			throws BadReturnException {
		boolean lastLine;
		line = (line.replace(RETURN, Constant.EMPTY)).trim();
		if (line.endsWith(";")) {
			if (line.length() != 1) {
				line = line.substring(0, line.length());
			} else {
				lastLine = true;
				return lastLine;
			}
		}
		Matcher returnLineMatcher = PoolOfRegex.ANY_CHAR.matcher(line);
		if (returnLineMatcher.find()) {
			throw new BadReturnException();
		}
		lastLine = true;
		return lastLine;
	}

	/**
	 * closing a scope.
	 * 
	 * @param numScope
	 *            the scope inside a function.(the most outside scope represent
	 *            with 1).
	 * @return an arraylist of the local variabales.
	 */
	private ArrayList<Variable> closeScopeInsideMethod(int numScope) {
		ArrayList<Variable> temp = new ArrayList<>();
		if (this.localVariables != null) {
			for (Variable variable : this.localVariables) {
				if (variable.getDepth() != numScope) {
					temp.add(variable);
				}
			}
			numScope--;
			return temp;
		}
		return null;
	}

	/**
	 * verify a method call.
	 * 
	 * @param line
	 *            the line to verify.
	 * @throws MethodNotExistException
	 *             the code try to call a method that does not exist.
	 * @throws VariablesTypeException
	 *             the code declare variable with a bad type.
	 * @throws VariableNotInitialized
	 *             the code assign a variable pointer when the variable is not
	 *             initialized
	 * @throws VariableNameNotDeclared
	 *             the assigned variable is not declared.
	 * @throws FunctionDeclarationException
	 *             problem in the declartation of the function.
	 * @throws MethodCallingException
	 *             trying to call a method poorly
	 */
	private void verifyMethodCallingLine(String line)
			throws MethodNotExistException, VariableNameNotDeclared,
			VariableNotInitialized, VariablesTypeException,
			FunctionDeclarationException, MethodCallingException {
		MethodCallingVerifier methodCallingVerifier = PoolOfVerifiers.methodCallingVerifier;
		methodCallingVerifier.setMethods(this.methods);
		methodCallingVerifier.setGlobalVariables(this.globalVariable);
		methodCallingVerifier.setLocalVariables(this.localVariables);
		methodCallingVerifier.verify(line);

	}

	/**
	 * verify a while line.
	 * 
	 * @param line
	 *            the line to verify.
	 * @param numScope
	 *            the scope inside a function.(the most outside scope represent
	 *            with 1).
	 * @throws CodeSyntaxException
	 *             a problem in the syntax of the code.
	 */
	private void verifyWhileLine(String line, int numScope) throws CodeSyntaxException {
		WhileVerifier whileVerifier = PoolOfVerifiers.whileVerifier;
		whileVerifier.setGlobalVariables(this.globalVariable);
		whileVerifier.setLocalVariables(this.localVariables);
		whileVerifier.verify(line);
		numScope++;
	}

	/**
	 * verify a if line.
	 * 
	 * @param line
	 *            the line to verify.
	 * @param numScope
	 *            the scope inside a function.(the most outside scope represent
	 *            with 1).
	 * @throws CodeSyntaxException
	 *             a problem in the syntax of the code.
	 */
	private void verifyIfLine(String line, int numScope) throws CodeSyntaxException {
		IfVerifier ifVerifier = PoolOfVerifiers.ifVerifier;
		ifVerifier.setGlobalVariables(this.globalVariable);
		ifVerifier.setLocalVariables(this.localVariables);
		ifVerifier.verify(line);
		numScope++;
	}
}
