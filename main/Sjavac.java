package oop.ex6.main;

import java.io.File;

import oop.ex6.parsing.FileParser;
import oop.ex6.parsing.SJavaFileParser;
import oop.ex6.verifier.Verifier;

/**
 * class for checking a Sjavac code file.
 * 
 * @author Avichai, nimi
 *
 */
public class Sjavac {
	// The index of the file in the program arguments array.
	private static final int FILE_INDEX = 0;

	// The expected number of program arguments.
	private static final int ARGS_NUMBER = 1;

	// The print values of the program.
	private static final int VERIFIED = 0, NOT_VERIFIED = 1, ERROR = 2;

	private File fileToCheck;

	/**
	 * creates new JavacFile instance
	 * 
	 * @param javacFile
	 *            a sjavac File.
	 */
	public Sjavac(String javacFile) {
		this.fileToCheck = new File(javacFile);
	}

	/**
	 * runs the tests on the input file. output: 1 if the code is valid. 2 if
	 * there was an i.o problem. 0 if the code is not valid.
	 */
	public void runCheks() {
		try {
			if (this.fileToCheck.exists() && !this.fileToCheck.isDirectory()) {
				FileParser parser = new SJavaFileParser(this.fileToCheck);
				Verifier verifier = new Verifier();
				parser.parseCode();
				verifier.setGlobalVeriables(parser.getOuterScope());
				verifier.setMethods(parser.getMethods());
				verifier.checkScopes(parser.getScopes());
			} else {
				throw new java.io.IOException();
			}
			System.out.println(VERIFIED);

		} catch (CodeException e) {
			e.printStackTrace();
			System.out.println(NOT_VERIFIED);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			System.out.println(ERROR);
		}
	}

	/**
	 * main method.
	 * 
	 * @param args
	 *            input file by the user.
	 */
	public static void main(String[] args) {
		try {
			if (args.length == ARGS_NUMBER) {
				Sjavac mySjavac = new Sjavac(args[FILE_INDEX]);
				mySjavac.runCheks();
			} else {
				throw new InvalidUsageException();
			}
		} catch (InvalidUsageException e) {
			e.printStackTrace();
			System.out.println(ERROR);
		}

	}
}
