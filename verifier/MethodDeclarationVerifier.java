package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.method.FunctionDecSyntaxException;
import oop.ex6.method.MethodParameter;

/*
 * This class is a verifier for method declaration lines. It verifies the declaration line,
 * and adds a new Method object to the program's known methods list with the fields
 * as declared in this line.
 */
class MethodDeclarationVerifier implements VerifierInteface {

	private static final int TYPE_IND = 0;
	private static final int NAME_IND = 1;

	// The method's name.
	String methodName;
	// List of the method's parameters, at the same order they appear in the
	// declaration.
	ArrayList<MethodParameter> methodParam;

	/**
	 * construct new method declaration verifier.
	 */
	MethodDeclarationVerifier() {
		this.methodParam = new ArrayList<MethodParameter>();
	}

	/*
	 * Getter for methodParam.
	 */
	ArrayList<MethodParameter> getMethodParam() {
		return this.methodParam;
	}

	/*
	 * Getter for the method's name.
	 */
	String gedMethodName() {
		return this.methodName;
	}

	@Override
	public void verify(String line) throws FunctionDecSyntaxException,VarNameAlreadyExist {
		setMethodName(line);
		setMethodParameters(line);
	}

	/*
	 * Finds the parameters in the method declaration line, creates matching
	 * MethodParameters objects, and adds those objects to methodParam list.
	 */
	private void setMethodParameters(String line)
			throws FunctionDecSyntaxException, VarNameAlreadyExist {
		Matcher paramMatcher = PoolOfRegex.PARAMETERS.matcher(line);
		paramMatcher.find();
		String paramString = line.substring(paramMatcher.start() + 1, paramMatcher.end() - 1).trim();
		if (!paramString.equals(Constant.EMPTY)) {
			String[] paramDecList = paramString.split(Constant.COMMA);
			for (String paramDec : paramDecList) {
				getParamFromParamDecList(paramDec);
			}
		} else {
			this.methodParam.clear();
		}
	}

	/*
	 * adds the parameter to the parameters list if possible.
	 */
	private void getParamFromParamDecList(String paramDec)
			throws FunctionDecSyntaxException, VarNameAlreadyExist {
		paramDec = paramDec.trim();
		Matcher variableDec = PoolOfRegex.VARIABLE_DECLARATION.matcher(paramDec);
		if (!variableDec.matches()) {
			throw new FunctionDecSyntaxException();
		}
		boolean isFinal = false;
		String type, name;
		Matcher wordMatcher = PoolOfRegex.NAME.matcher(paramDec);
		wordMatcher.find();
		String firstWord = paramDec.substring(wordMatcher.start(),wordMatcher.end());
		if (firstWord.equals(Constant.FINAL)) {
			isFinal = true;
			int paramEnd = paramDec.length();
			paramDec = paramDec.substring(wordMatcher.end(), paramEnd).trim();
		}
		String[] splitParam = paramDec.split(Constant.SPACE);
		type = splitParam[TYPE_IND].trim();
		name = splitParam[NAME_IND].trim();
		MethodParameter parameter = new MethodParameter(type, isFinal, name);
		for (MethodParameter param : this.methodParam) {
			if (param.getName().equals(parameter.getName())) {
				throw new VarNameAlreadyExist();
			}
		}
		this.methodParam.add(parameter);
	}

	/*
	 * Finds the name of the method declared.
	 */
	private void setMethodName(String line) {
		Matcher voidMatcher = PoolOfRegex.VOID_MATCHER.matcher(line);
		voidMatcher.find();
		int lineEnd = line.length();
		line = line.substring(voidMatcher.end(), lineEnd);
		Matcher nameMatcher = PoolOfRegex.NAME.matcher(line);
		nameMatcher.find();
		this.methodName = line.substring(nameMatcher.start(), nameMatcher.end());
	}

	/*
	 * Clears the list of MethodParameters.
	 */
	void reset() {
		this.methodParam.clear();
	}
}
