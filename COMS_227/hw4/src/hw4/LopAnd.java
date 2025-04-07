package hw4;

import api.Expression;
import api.Scope;

/**
 * Node type representing a logical expression 
 * with the "and" operator. If both operands evaluate
 * to a nonzero value, then this expression evaluates to 1;
 * otherwise, this expression evaluates to zero.
 * <ul>
 *   <li>There are two children; the first is the left operand, and the second 
 *   is the right operand.
 *   <li>The getLabel() method returns a string consisting of a double ampersand
 *   (&amp;&amp;).
 *   <li>The getText() method returns an empty string.
 * </ul>
 */
public class LopAnd extends Equations implements Expression
{
  /**
   * Constructs an expression with the given left and right sides.
   * @param lhs
   *   expression for the left-hand-side operand
   * @param rhs
   *   expression for the left-hand-side operand
   */
  public LopAnd(Expression lhs, Expression rhs) {
	  super(lhs, rhs);
  }
  
  @Override
  public String getLabel() {
	  return "&&";
  }

  @Override
  public int eval(Scope env) {
	  if (getLhs(env) != 0 && getRhs(env) != 0) {
		  return 1;
	  } else {
		  return 0;
	  }
  }
  
}