import parser.ProgramNode;
import util.ParserUtil;
import api.Scope;
import hw4.*;
import api.*;
import api.Junebug;


public class tests {
	
	public static void main(String[] args) {
	
	// A literal value.
	Expression e0 = new Literal(2);
	Expression e1 = new Literal(3);

	// a literal always evaluates to itself
	// (Note the scope is not actually being used here)
	Scope env = new Scope();
	System.out.println(e0.eval(env)); // expected 2
	System.out.println(e1.eval(env)); // expected 3
	// create the expression 2 + 3
	Expression aSum = new AopAdd(e0, e1);
	System.out.println(aSum.eval(env)); // evaluates to 5
	// create the expression (2 + 3) + 4
	Expression e2 = new Literal(4);
	Expression anotherSum = new AopAdd(aSum, e2);
	System.out.println(anotherSum.eval(env)); // expected 9
	
	// create an expression with a variable, say x + 4
	Identifier id = new Identifier("x");
	Expression sumWithVar = new AopAdd(id, new Literal(4));

	// to evaluate it, we need to provide a scope in which x has a value
	env = new Scope();
	env.put("x", 42);
	System.out.println(sumWithVar.eval(env)); // expected 46
	System.out.println();
	
	//more
	
	// create an expression with a variable, say x + 4
	 id = new Identifier("x");
	 sumWithVar = new AopAdd(id, new Literal(4));

	 // to evaluate it, we need to provide a scope in which x has a value
	 env = new Scope();
	 env.put("x", 42);
	 System.out.println(sumWithVar.eval(env)); // expected 46
	 System.out.println();
	 
	 //more
	 // we could also give x a value by executing an assignment statement
	 // (recall that anotherSum was the expression (2 + 3) + 4).
	 AssignmentInstruction a = new AssignmentInstruction(id, anotherSum);

	 // executing the assignment will cause the expression to be evaluated
	 // and stored as the value of x
	 a.execute(env);
	 System.out.println(sumWithVar.eval(env)); // 9 + 4 = 13
	 System.out.println();
	 
	 //more
	// count = 1
	 Identifier indexVar = new Identifier("count");
	 Instruction initialize =
	 new AssignmentInstruction(indexVar, new Literal(1));

	 // count < 11
	 Expression test = new RopLessThan(indexVar, new Literal(11));

	 // output(count)
	 Instruction display = new OutputInstruction(indexVar);

	 // count = count + 1
	 Expression sum = new AopAdd(indexVar, new Literal(1));
	 Instruction increment = new AssignmentInstruction(indexVar, sum);

	 // create the loop statement using a block for the body
	 BlockInstruction block = new BlockInstruction();
	 block.addStatement(display);
	 block.addStatement(increment);
	 Instruction loop = new WhileInstruction(test, block);

	 // combine initialization and loop into a block
	 BlockInstruction main = new BlockInstruction();
	 main.addStatement(initialize);
	 main.addStatement(loop);

	 // now run it
	 env = new Scope();
	 main.execute(env); //expected 1-10
	 System.out.println();
	 
	 //more
	 
	
	}
}
