package hw4;

import java.util.ArrayList;

import api.ArgList;
import api.DefaultNode;
import api.Expression;
import api.Function;
import api.Scope;
import parser.ProgramNode;

/**
 * Expression representing the value of a function call. All expressions
 * in the argument list are evaluated with respect to the current scope,
 * and the resulting values are associated with the corresponding parameter names in
 * a new "local" Scope.  This local scope is used to evaluate the function body
 * (its BlockInstruction) and after that, the function return expression. 
 * Variables in the current scope are not accessible during execution of the function
 * body.
 * The eval method of this call expression returns value of the function's
 * return expression.
 * <ul>
 *   <li>There are two children; the first is the Function object, and the second 
 *   is the argument list.
 *   <li>The getLabel() method returns the string "Call".
 *   <li>The getText() method returns the getText() string of the Function
 * </ul>
 */
public class CallExpression implements Expression
{
	
	private Function function;
	
	private ArrayList<ArgList> args;
	
	/**
	 * Constructs a CallExpression for the given function and argument list.
	 * @param f
	 *   the function to be called
	 * @param args
	 *   the arguments to the function
	 */
	public CallExpression(Function f, ArgList args) {
		function = f;
		this.args = new ArrayList<ArgList>();
	}
	  
	/**
	 * Sets the Function object for this CallExpression
	 * @param f
	 *   the function to be called
	 */
	public void setFunction(Function f) {
	  // TODO
	}

	@Override
	public ProgramNode getChild(int i) {
		if (i == 0) {
			return function;
		} else if (i == 1) {
			return (ProgramNode) args;
		} else {
			return new DefaultNode("Invalid index " + i + " for type " + this.getClass().getName());
		}
	}

	@Override
	public String getLabel() {
		return "Call";
	}

	@Override
	public int getNumChildren() {
		return 2;
	}

	@Override
	public String getText() {
		return function.getText();
	}

	@Override
	public int eval(Scope env) {
		// TODO Auto-generated method stub
		return 0;
	}
  

}
