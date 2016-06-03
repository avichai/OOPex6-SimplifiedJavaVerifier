package oop.ex6.verifier;

/**
 * Pool of verifieres that probably will be used in thr program.
 * 
 * @author Avichai, nimi
 *
 */
class PoolOfVerifiers {
	static VariableVerifier variableVerifier = new VariableVerifier();
	static MethodDeclarationVerifier methodVerifier = new MethodDeclarationVerifier();
	static WhileVerifier whileVerifier = new WhileVerifier();
	static MethodCallingVerifier methodCallingVerifier = new MethodCallingVerifier();
	static IfVerifier ifVerifier = new IfVerifier();
	static ConditionalParameterVerifier paramVerifier = new ConditionalParameterVerifier();
	static VariableAssignmentVerifier variableAsignmentVerifier = new VariableAssignmentVerifier();
}
