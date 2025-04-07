package hw4;

import api.Expression;
import api.Scope;

/**
 * Node type representing a relational expression
 * with the "less than" operator.  If the left
 * operand evaluates to a smaller value than the 
 * right operand, this expression evaluates to 1; otherwise,
 * it evaluates to zero.
 * <ul>
 *   <li>There are two children; the first is the left operand, and the second 
 *   is the right operand.
 *   <li>The getLabel() method returns a string consisting of the less-than symbol
 *   (&lt;).
 *   <li>The getText() method returns an empty string.
 * </ul>
 */
public class RopLessThan extends Equations implements Expression
{
  /**
   * Constructs an expression with the given left and right sides.
   * @param lhs
   *   expression for the left-hand-side operand
   * @param rhs
   *   expression for the left-hand-side operand
   */
  public RopLessThan(Expression lhs, Expression rhs) {
	  super(lhs, rhs);
  }
  
  @Override
  public String getLabel() {
	  return "<";
  }

  @Override
  public int eval(Scope env) {
	  if (super.getLhs(env) < super.getRhs(env)) {
		  return 1;
	  } else {
		  return 0;
	  }
  }
}