package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.BadVariableNameFormat;
import oop.ex6.variable.FinalVariableNotInitialized;
import oop.ex6.variable.InvalidInputForVariable;
import oop.ex6.variable.InvalidTypeException;
import oop.ex6.variable.VarDecEndOfLineException;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableNameException;
import oop.ex6.variable.VariableNotInitialized;
import oop.ex6.variable.VariablesTypeException;

/**
 * Varifies a variable decleration line.
 * 
 * @author Avichai, nimi
 *
 */
class VariableVerifier implements VerifierInteface {

	private ArrayList<String> varNames = new ArrayList<String>();
	private ArrayList<String> varTypes = new ArrayList<String>();
	private ArrayList<Boolean> varInitialized = new ArrayList<Boolean>();
	private ArrayList<Boolean> varIsFinal = new ArrayList<Boolean>();

	private String varType;
	private boolean isFinal = false;

	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	@Override
	public void verify(String line) throws InvalidInputForVariable,VariableNotInitialized,
			VarDecEndOfLineException,VariablesTypeException, VariableNameException,
			FinalVariableNotInitialized {
		resetFields();
		line = line.trim();
		Matcher ifFinalDec = PoolOfRegex.FINAL_VAR_DEC.matcher(line);
		if (ifFinalDec.lookingAt()) {
			this.isFinal = true;
			line = (line.substring(ifFinalDec.end() + 1)).trim();
		}
		Matcher varTypematch = PoolOfRegex.VAR_TYPE_DEC.matcher(line);
		if (varTypematch.lookingAt()) {
			this.varType = line.substring(varTypematch.start(), varTypematch.end());
			line = (line.substring(varTypematch.end() + 1)).trim();
		} else {
			throw new InvalidTypeException();
		}
		if (line.endsWith(Constant.SMICOLON)) {
			line = line.substring(0, line.length() - 1);
		} else {
			throw new VarDecEndOfLineException();
		}
		String[] variablesDec = line.split(Constant.COMMA);
		for (String variable : variablesDec) {
			verifyVariableDec(variable);
		}
	}

	/*
	 * verifing only the variable dec itself (i.e.
	 * varName|varname=5|varname=other variable of the same type)
	 */
	private void verifyVariableDec(String variable)
			throws VariableNameException, VariableNotInitialized,
			VariablesTypeException, FinalVariableNotInitialized {
		this.varIsFinal.add(this.isFinal);
		this.varTypes.add(this.varType);
		variable = variable.trim();
		Matcher getVarName = PoolOfRegex.VAR_NAME.matcher(variable);
		if (getVarName.lookingAt()) {
			this.varNames.add(variable.substring(getVarName.start(), getVarName.end()));
		} else {
			throw new VariableNameException();
		}
		variable = variable.substring(getVarName.end()).trim();
		if (variable.startsWith(Constant.EQUAL_OPARATOR)) {
			this.varInitialized.add(true);
			variable = (variable.substring(1)).trim();
			StaticVerifiersMethods.checkIfValidType(this.globalVariable,
					this.localVariable, variable, this.varType);
		} else {
			this.varInitialized.add(false);
			if (this.isFinal) {
				throw new FinalVariableNotInitialized();
			}
			if (!variable.isEmpty()) {
				throw new BadVariableNameFormat();
			}
		}
	}

	/*
	 * reseting the fields.
	 */
	private void resetFields() {
		this.isFinal = false;
		this.varNames.clear();
		this.varInitialized.clear();
		this.varIsFinal.clear();
		this.varTypes.clear();
	}

	/**
	 * 
	 * @return arrayList of all the variable names declaried on the input line.
	 */
	ArrayList<String> getVarNames() {
		return this.varNames;
	}

	/**
	 * 
	 * @return arrayList of all the variable types declaried on the input line.
	 */
	ArrayList<String> getVarTypes() {
		return this.varTypes;
	}

	/**
	 * 
	 * @return arrayList of all the variable isInitialized field on the input
	 *         line.
	 */
	ArrayList<Boolean> getVarInitialized() {
		return this.varInitialized;
	}

	/**
	 * 
	 * @return arrayList of all the variable in final field on the input line.
	 */
	ArrayList<Boolean> getVarIsFinal() {
		return this.varIsFinal;
	}

	/**
	 * setting the global variable arrayList field.
	 * 
	 * @param globalVariable
	 *            that was declared on the outer scope.
	 */
	void setGlobalVariables(ArrayList<Variable> globalVariable) {
		this.globalVariable = globalVariable;
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
