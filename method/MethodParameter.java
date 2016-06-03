package oop.ex6.method;

/**
 * Class represent a method parameter.
 * 
 * @author Avichai, nimi
 *
 */
public class MethodParameter {
	private String type;
	private boolean isFinal;
	private String name;

	/**
	 * creates new method parameter.
	 * 
	 * @param type
	 *            the type of the parameter.
	 * @param isFinal
	 *            true if the parameter is final.
	 * @param name
	 *            the name of the parameter.
	 */
	public MethodParameter(String type, boolean isFinal, String name) {
		this.type = type;
		this.isFinal = isFinal;
		this.name = name;
	}

	/**
	 * 
	 * @return the name of the parameter.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return the type of the parameter.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @return true if the parameter is final.
	 */
	public boolean isFinal() {
		return this.isFinal;
	}

}
