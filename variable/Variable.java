package oop.ex6.variable;

/**
 * Class represent a variable.
 * 
 * @author Avichai
 *
 */
public class Variable {
	private final String type;
	private final String name;
	private boolean isInitialized;
	private final int depth;
	private final boolean isFinal;

	/**
	 * create new variable instance.
	 * 
	 * @param type
	 *            the type of the variable.
	 * @param name
	 *            the name of the veriable.
	 * @param isInitialized
	 *            true if the variable is initialized.
	 * @param depth
	 *            the number of scopes inside the method the varibale declared
	 *            in.
	 * @param isFinal
	 *            true if the variable if final.
	 */
	public Variable(String type, String name, boolean isInitialized, int depth, boolean isFinal) {
		this.type = type;
		this.name = name;
		this.isInitialized = isInitialized;
		this.depth = depth;
		this.isFinal = isFinal;
	}

	/**
	 * 
	 * @return true if the variable if final.
	 */
	public boolean isFinal() {
		return this.isFinal;
	}

	/**
	 * 
	 * @return true if the variable is initialized.
	 */
	public boolean isInitialized() {
		return this.isInitialized;
	}

	/**
	 * 
	 * @param isInitialized
	 *            put true if the variable is initialized.
	 */
	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	/**
	 * 
	 * @return the type of the variable.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @return the name of the veriable.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return the number of scopes inside the method the varibale declared in.
	 */
	public int getDepth() {
		return this.depth;
	}

}
