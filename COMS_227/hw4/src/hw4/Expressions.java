package hw4;

import api.Expression;
import api.Instruction;
import api.Scope;
import parser.ProgramNode;

public abstract class Expressions implements Instruction {
	
	/**
	 * The expression that is stored and used for everything in the class
	 */
	private Expression expr;
	
	/**
	 * Constructs a expression with the givin expression
	 * @param expr The expression to be used
	 */
	protected Expressions(Expression expr) {
		this.expr = expr;
	}
	
	/**
	 * Gets the text
	 */
	public String getText() {
		return "";
	}
	
	/**
	 * Gets the expression
	 * @return The expression in this class
	 */
	protected Expression getExpr() {
		return expr;
	}
	
	/**
	 * Returns the amount of children
	 */
	public int getNumChildren() {
		return 2;
	}
	
	/**
	 * Turns the expression into a string
	 */
	@Override
	public String toString() {
		return makeString();
	}
	
	//abstract
	
	/**
	 * Gets the children based on the number i
	 * @param i The child to get
	 */
	public abstract ProgramNode getChild(int i);
	
	/**
	 * Gets the label of the class
	 */
	public abstract String getLabel();
	
	/**
	 * Executes the expression
	 */
	public abstract void execute(Scope env);
}
