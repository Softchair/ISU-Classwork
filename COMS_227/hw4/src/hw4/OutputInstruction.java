package hw4;

import api.DefaultNode;
import api.Expression;
import api.Instruction;
import api.Scope;
import parser.ProgramNode;

/**
 * Instruction type whose execution causes the value of an expression to
 * be printed to the console.
 * <ul>
 *   <li>There is one child, the expression whose value is to be printed.
 *   <li>The getLabel() method returns the string "Output".
 *   <li>The getText() method returns an empty string.
 * </ul>
 */
public class OutputInstruction extends Expressions implements Instruction
{
  
  /**
   * Constructs an output statement for the given expression.
   * @param expr
   *   given expression
   */
  public OutputInstruction(Expression expr) {
	  super(expr);
  }
  
  @Override
  public String getLabel() {
	  return "Output";
  }
  
  @Override
  public int getNumChildren() {
	  return 1;
  }
  
  @Override
  public ProgramNode getChild(int i) {
	  if (i == 0) {
		  return getExpr();
	  } else {
		  return new DefaultNode("Invalid index " + i + " for type " + this.getClass().getName());
	  }
  }  

  @Override
  public void execute(Scope env) {
	  System.out.println(getExpr().eval(env));
  }

}
