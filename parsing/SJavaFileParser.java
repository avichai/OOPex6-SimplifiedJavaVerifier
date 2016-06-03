package oop.ex6.parsing;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * This class represents a parser object that gets an s-java code file , and has
 * the ability to divide this file logically to declared elements (methods,
 * global variables), and scopes (functions, conditions, loops).
 * 
 * @author nimi, avichai
 *
 */
public class SJavaFileParser implements FileParser {

	// The patterns that the parser should know.
	private static final Pattern METHOD_DECLARATION = Pattern
			.compile("\\s*void\\s+[a-zA-Z]\\w*\\s*\\(.*\\)\\s*\\{\\s*");
	private static final Pattern SCOPE_OPENER = Pattern.compile(".*\\{\\s*");
	private static final Pattern SCOPE_CLOSER = Pattern.compile(" *\\}\\s*");

	private static final String COMMENT_STARTER = "//";
	private static final String EMPTY = "";

	// A list for lines that are not a method declaration, and doesn't belong to
	// an inner scope.
	private ArrayList<String> outerScopeLines;
	// A list for method declaration lines.
	private ArrayList<String> methodDeclarationLines;
	// A list of all of the file's method declaration blocks.
	private ArrayList<ArrayList<String>> allMethodBlocks;
	// The code file to be parsed.
	private File codeFile;

	// The last read line of the file.
	private String currLine;
	// Matchers for opening and closing scope patterns.
	private Matcher closer;
	private Matcher opener;
	// A list the lines of the current method declaration block.
	private ArrayList<String> methodBlock;

	/**
	 * A constructor.
	 * 
	 * @param file
	 *            The code File to be parsed.
	 */
	public SJavaFileParser(File file) {
		this.codeFile = file;
		this.outerScopeLines = new ArrayList<String>();
		this.methodDeclarationLines = new ArrayList<String>();
		this.allMethodBlocks = new ArrayList<ArrayList<String>>();
	}

	@Override
	public void parseCode() throws IOException, ParserException {
		try (LineNumberReader reader = new LineNumberReader(new FileReader(
				this.codeFile))) {
			this.currLine = reader.readLine();
			this.allMethodBlocks = new ArrayList<ArrayList<String>>();
			while (this.currLine != null) {
				Matcher matcher = METHOD_DECLARATION.matcher(this.currLine);
				if (matcher.matches()) {
					this.methodDeclarationLines.add(this.currLine);
					this.methodBlock = new ArrayList<String>();
					int depth = 0;
					enterScope(reader, depth);
					this.allMethodBlocks.add(this.methodBlock);
				} else {
					this.currLine = this.currLine.trim();
					if (!(this.currLine.startsWith(COMMENT_STARTER) || this.currLine.equals(EMPTY))){
						this.outerScopeLines.add(this.currLine);
					}
					this.currLine = reader.readLine();
				}
			}
		}
	}

	@Override
	public ArrayList<String> getOuterScope() {
		return this.outerScopeLines;
	}

	@Override
	public ArrayList<String> getMethods() {
		return this.methodDeclarationLines;
	}

	@Override
	public ArrayList<ArrayList<String>> getScopes() {
		return this.allMethodBlocks;
	}

	/*
	 * This method adds a method declaration's lines in the code to
	 * allMethodBlocks list. If the method gets to a more inner scope, it calls
	 * itself recursively, to check if all blocks are closed properly, and
	 * throws BlockNotClosed exception if it isn't.
	 */
	private void enterScope(LineNumberReader reader, int depth)
			throws IOException, BlockNotClosedException {
		// A list for the current scope's lines.
		this.methodBlock.add(this.currLine);
		proceed(reader, depth);
		while (!(this.closer.matches())) {
			if (this.opener.matches()) {
				enterScope(reader, ++depth);
				depth--;
			} else {
				this.currLine = this.currLine.trim();
				if (!(this.currLine.startsWith(COMMENT_STARTER) || this.currLine.equals(EMPTY))) {
					this.methodBlock.add(this.currLine);
				}
				proceed(reader, depth);
			}
		}
		this.methodBlock.add(this.currLine);
		proceed(reader, depth);
	}

	/*
	 * this method is called inside an inner scope, reads the next line of the
	 * file, and updates the closer and opener matchers to hold the current
	 * line. If the line is null, then it means the scope was not closed
	 * properly, and a BlockNotClosed exception is thrown.
	 */
	private void proceed(LineNumberReader reader, int depth)
			throws BlockNotClosedException, IOException {
		this.currLine = reader.readLine();
		if (this.currLine == null) {
			if (depth != 0) {
				throw new BlockNotClosedException();
			} else {
				return;
			}
		}
		this.currLine = this.currLine.trim();
		this.closer = SCOPE_CLOSER.matcher(this.currLine);
		this.opener = SCOPE_OPENER.matcher(this.currLine);
	}

}