package hw4;

import api.DefaultNode;
import api.Expression;
import api.Instruction;
import api.Scope;
import parser.ProgramNode;

/**
 * Instruction type representing a conditional.  A conditional has an
 * expression and two instructions.  If the expression evaluates to a 
 * nonzero value (i.e., "true"), the first instruction is executed; otherwise, the 
 * second instruction is executed.
 * <ul>
 *   <li>There are three children; the first is the expression, the second 
 *   is the instruction for the "true", the third is the instruction for
 *   the "false" branch
 *   <li>The getLabel() method returns the string "If".
 *   <li>The getText() method returns an empty string.
 * </ul>
 */
public class IfInstruction extends Expressions implements Instruction
{
  
  private Instruction s0;
  
  private Instruction s1;
	
	
  /**
   * Constructs a conditional statement from the given condition
   * and alternative actions.
   * @param condition
   *    an expression to use as the condition
   * @param s0
   *    statement to be executed if the condition is true (nonzero)
   * @param s1
   *    statement to be executed if the condition is false (zero)
   */
  public IfInstruction(Expression condition, Instruction s0, Instruction s1) {
	  super(condition);
	  this.s0 = s0;
	  this.s1 = s1;
  }
  
  public String getLabel() {
	  return "If";
  }
  
  @Override
  public int getNumChildren() {
	  return 3;
  }
  
  public ProgramNode getChild(int i) {
	  if (i == 0) {
		  return getExpr();
	  } else if (i == 1) {
		  return s0; //HERE NOT DONE
	  } else if (i == 2) {
		  return s1; //HERE NOT DONE
	  } else {
		  return new DefaultNode("Invalid index " + i + " for type " + this.getClass().getName());
	  }
  }

  @Override
  public void execute(Scope env) {
	  if (getExpr().eval(env) != 0) {
		  s0.execute(env);
	  } else {
		  s1.execute(env);
	  }
  }

}
