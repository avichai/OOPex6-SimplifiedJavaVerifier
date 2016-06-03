package oop.ex6.verifier;

import oop.ex6.main.CodeException;

/**
 * interface for all the verifires
 * 
 * @author Avichai, nimi
 *
 */
interface VerifierInteface {
	/*
	 * verify a given line by the verifier filters.
	 * 
	 * @param line the line to verify.
	 * 
	 * @throws CodeException problems in the code.
	 */
	void verify(String line) throws CodeException;
}
