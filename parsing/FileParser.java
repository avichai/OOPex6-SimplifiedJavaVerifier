package oop.ex6.parsing;

import java.util.*;

import oop.ex6.main.CodeException;

/**
 * An interface for a parser object that gets a code file in a known format, and
 * has the ability to divide this file logically to declared elements (methods,
 * global variables), and scopes (functions, conditions, loops).
 * 
 * @author nimi, avichai
 *
 */
public interface FileParser {

	/**
	 * This method parses a code file, and divides it to methods declaration
	 * lines, scopes lines, and outer scope lines.
	 * 
	 * @throws java.io.IOException
	 *             In case of an IOException
	 * @throws CodeException
	 *             an exception in the code structure.
	 */
	public void parseCode() throws CodeException, java.io.IOException;

	/**
	 * A getter for the list holding the lines of the file that are not method
	 * declarations or inner scopes' lines.
	 * 
	 * @return A Strings ArrayList, which it's elements are the lines described
	 *         in the method's description.
	 */
	public ArrayList<String> getOuterScope();

	/**
	 * A getter for the list holding the lines of the file that are method
	 * declarations.
	 * 
	 * @return A Strings ArrayList, which it's elements are lines of method
	 *         declarations.
	 */
	public ArrayList<String> getMethods();

	/**
	 * A getter for the list holding the file's scopes. Every scope is a list of
	 * lines that are located between a pair of matching curly brackets in the
	 * code file, not including lines of inner scopes inside the scope.
	 * 
	 * @return An ArrayList of scopes, which are Strings ArrayLists of the lines
	 *         described in the method's description.
	 */
	public ArrayList<ArrayList<String>> getScopes();
}