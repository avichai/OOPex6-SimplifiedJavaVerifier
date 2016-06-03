package oop.ex6.verifier;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.variable.BadVariableNameFormat;
import oop.ex6.variable.InvalidInputForVariable;
import oop.ex6.variable.VarDecEndOfLineException;
import oop.ex6.variable.Variable;
import oop.ex6.variable.VariableNameNotDeclared;
import oop.ex6.variable.VariableNotInitialized;
import oop.ex6.variable.VariablesTypeException;

/**
 * Verifing a variable assignment line.
 * 
 * @author Avichai, nimi
 *
 */
class VariableAssignmentVerifier implements VerifierInteface {

	private ArrayList<Variable> globalVariable;
	private ArrayList<Variable> localVariable;

	@Override
	public void verify(String line) throws BadVariableNameFormat,VariableNameNotDeclared, 
			CodeFormatException,VariableNotInitialized, InvalidInputForVariable,
			VarDecEndOfLineException, VariablesTypeException,AssignmentToFinalVar {
		line = line.trim();
		Matcher varNameMathcer = PoolOfRegex.VAR_NAME.matcher(line);
		if (!varNameMathcer.lookingAt()) {
			throw new BadVariableNameFormat();
		} else {
			verifyGoodNameForVarAssignment(line, varNameMathcer);
		}
	}

	/*
	 * verify a line for assignment to a variable that has a valid name.
	 */
	private void verifyGoodNameForVarAssignment(String line,
			Matcher varNameMathcer) throws VariableNameNotDeclared, AssignmentToFinalVar,
			VarDecEndOfLineException, VariableNotInitialized, VariablesTypeException, CodeFormatException{
		String varName = line.substring(varNameMathcer.start(), varNameMathcer.end());
		Variable variable = StaticVerifiersMethods.searchVariable(
				this.globalVariable, this.localVariable, varName);
		if (variable == null) {
			throw new VariableNameNotDeclared();
		}
		if (variable.isFinal()) {
			throw new AssignmentToFinalVar();
		}
		line = line.substring(varNameMathcer.end() + 1).trim();
		if (line.endsWith(Constant.SMICOLON)) {
			line = line.substring(0, line.length() - 1);
		} else {
			throw new VarDecEndOfLineException();
		}
		verifyAssinment(line, variable);
	}

	/*
	 * verify the part of the assignment in the line.
	 */
	private void verifyAssinment(String line, Variable variable)
			throws VariableNameNotDeclared, VariableNotInitialized,
			VariablesTypeException, CodeFormatException {
		if (line.startsWith(Constant.EQUAL_OPARATOR)) {
			if (this.localVariable.contains(variable)) {
				variable.setInitialized(true);
			}
			line = (line.substring(1)).trim();
			StaticVerifiersMethods.checkIfValidType(this.globalVariable,
					this.localVariable, line, variable.getType());
		} else {
			throw new CodeFormatException();
		}
	}

	/**
	 * setting the global variable arrayList field.
	 * 
	 * @param globalVariable
	 *            that was declared on the outer scope.
	 */
	void setGlobalVariable(ArrayList<Variable> globalVariable) {
		this.globalVariable = globalVariable;
	}

	/**
	 * setting the local variable arrayList field.
	 * 
	 * @param localVariable
	 *            that was declared inside the function in a more outer or the
	 *            same scope.
	 */
	void setLocalVariable(ArrayList<Variable> localVariable) {
		this.localVariable = localVariable;
	}
}
