package hw4;

import api.Expression;
import api.Scope;
import parser.ProgramNode;

public abstract class EquationsOne implements Expression {
	
	/**
	 * The expression that is stored
	 */
	private Expression expr;
	
	/**
	 * Constructs a Equation with one expression
	 * @param expr the expression to use to be constructed
	 */
	protected EquationsOne(Expression expr) {
		this.expr = expr;
	}
	
	/**
	 * Gets the value of the expression
	 * @param env The scope to be used to evaluate the expression
	 * @return The value of the expression
	 */
	public int getExpr(Scope env) {
		return expr.eval(env);
	}
	
	/**
	 * Returns the expression
	 */
	public ProgramNode getChild(int i) {
		return expr;
	}
	
	/**
	 * Gets the text
	 */
	public String getText() {
		return "";
	}
	
	/**
	 * Gets the number of children
	 */
	public int getNumChildren() {
		return 1;
	}
	
	/**
	 * Makes the expression into a string and returns it
	 */
	@Override
	public String toString() {
		return makeString();
	}

	//abstract methods
	
	/**
	 * Returns the label of the class
	 */
	public abstract String getLabel();

	/**
	 * Evaluates the expression
	 */
	public abstract int eval(Scope env);
	
}
