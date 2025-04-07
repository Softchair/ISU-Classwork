package hw4;

import api.DefaultNode;
import api.Expression;
import api.Scope;
import parser.ProgramNode;

public abstract class Equations implements Expression {
	
	/**
	 * Stores the left hand side of an expression
	 */
	private Expression lhs;
	/**
	 * Stores the right hand side of an expression
	 */
	private Expression rhs;
	
	/**
	 * Constructs and equation with a left and right side
	 * @param lhs Variable to be on the left side of the equation
	 * @param rhs Variable to be on the right side of the equation
	 */
	protected Equations(Expression lhs, Expression rhs) {
		this.lhs = lhs;
	    this.rhs = rhs;
	}
	
	/**
	 * Returns the left hand side of an equation
	 * @param env Scope to be used to evaluate the equation
	 * @return Value of the left hand side of an equation
	 */
	public int getLhs(Scope env) {
		int leftVal = lhs.eval(env);
		return leftVal;
	}
	
	/**
	 * Returns the right hand side of an equation
	 * @param env Scope to be used to evaluate the equation
	 * @return Value of the right hand side of an equation
	 */
	public int getRhs(Scope env) {
		int rightVal = rhs.eval(env);
		return rightVal;
	}
	
	/**
	 * Returns the child, either the left or right hand expression
	 * @param The child to return, 0 is left, 1 is right
	 */
	public ProgramNode getChild(int i) {
		if (i == 0) {
			return lhs;
		} else if (i == 1) {
			return rhs;
		} else {
	      return new DefaultNode("Invalid index " + i + " for type " + this.getClass().getName());
	    }
	}
	
	/**
	 * Gets the text
	 */
	public String getText() {
		return "";
	}
	
	/**
	 * Returns the amount of children
	 */
	public int getNumChildren() {
		return 2;
	}
	
	/**
	 * Returns as a string
	 */
	@Override
	public String toString() {
		return makeString();
	}
	
	//abstracts
	
	/**
	 * Evaluates the expression based on the Scope
	 * @param givin scope
	 */
	public abstract int eval(Scope env);
	
	/**
	 * Returns the label of the class
	 */
	public abstract String getLabel();
	
}
