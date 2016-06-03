package oop.ex6.verifier;

import java.util.regex.Pattern;

/**
 * Class for all the regexes used in the program that are already compiled!
 * 
 * @author Avichai, nimi
 *
 */
class PoolOfRegex {

	// Matches a beginning of a if line.
	static final Pattern IF_START = Pattern.compile("if\\s*\\(");
	
	// Matches an ending of a if or while line.
	static final Pattern IF_OR_WHILE_END = Pattern.compile("\\)\\s*\\{");
	
	// Matches a beginning of a while line.
	static final Pattern WHILE_START = Pattern.compile("while\\s*\\(");

	// // Matches a valid variable type.
	static final Pattern VAR_TYPE_DEC = Pattern.compile("int|double|String|boolean|char");
	
	// Matches a final variable declaration.
	static final Pattern FINAL_VAR_DEC = Pattern.compile("final");

	// Matches a valid variable name.
	static final Pattern VAR_NAME = Pattern.compile("([a-zA-Z]+\\w*|[_]+\\w+)");

	// Matches a valid start of variable declaration.
	static final Pattern VAR_DEC_MATCHER = 
			Pattern.compile("(int|double|String|boolean|char)\\s|final\\s*");

	// Matches a beginning of a method call.
	static final Pattern METHOD_CALL = Pattern.compile("\\w+\\s*\\(");

	// Matches any char in the line.
	static final Pattern ANY_CHAR = Pattern.compile(".");

	// Matches a condition operator (&&, ||).
	static final String CONDITIONS_SEPARATOR = "(\\&\\&)|(\\|\\|)";

	static final String BOOLEAN_TYPE = "boolean";

	// Matches an explicit boolean or number.
	static final Pattern BOOLEAN = Pattern.compile("true|false|\\d+(.\\d+)?");

	static final Pattern VOID_MATCHER = Pattern.compile("void");

	// Matches a method call. doesn't check validity.
	static final Pattern LEGAL_METHOD_CALL = Pattern.compile("\\s*[a-zA-Z]\\w*\\s*\\(.*\\)\\s*;\\s*");

	static final Pattern NAME = Pattern.compile("\\w+");

	static final Pattern PARAMETERS = Pattern.compile("\\(.*\\)");

	// General pattern of variable declaration. doesn't check validity of
	// inserted values.
	static final Pattern VARIABLE_DECLARATION = Pattern
			.compile("(final\\s+)?(int|double|String|boolean|char)\\s+[a-zA-Z]+\\w*|[_]+\\w+");

	// Matches an explicit String.
	static final Pattern STRING = Pattern.compile("\".*\"");

	// Matches explicit int.
	static final Pattern INT = Pattern.compile("\\s*\\d+\\s*|\\s*-\\d+\\s*");

	// Matches explicit double.
	static final Pattern DOUBLE = Pattern.compile("\\s*\\d+(.\\d+)?\\s*|\\s*-\\d+(.\\d+)?\\s*");

	// Matches explicit char.
	static final Pattern CHAR = Pattern.compile("\\'.\\'");

}
