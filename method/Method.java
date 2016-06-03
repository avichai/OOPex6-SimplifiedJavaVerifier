package oop.ex6.method;

import java.util.ArrayList;

/**
 * class represent a method in a code.
 * 
 * @author Avichai, nimi
 *
 */
public class Method {
	private String name;
	private ArrayList<MethodParameter> methodParam;

	/**
	 * creates a new method instance.
	 * 
	 * @param name
	 *            the name of the method.
	 * @param methodParam
	 *            the parameters of the method.
	 */
	public Method(String name, ArrayList<MethodParameter> methodParam) {
		super();
		this.name = name;
		if(methodParam != null){
			this.methodParam = methodParam;
		}else{
			this.methodParam = null;
		}
	}

	/**
	 * 
	 * @return the name of the method.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return the parameters of the method.
	 */
	public ArrayList<MethodParameter> getMethodParam() {
		return this.methodParam;
	}

}
