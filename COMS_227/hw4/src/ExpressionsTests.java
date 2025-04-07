import api.Expression;
import api.Instruction;
import hw4.OutputInstruction;
import parser.ProgramNode;
import api.Scope;
import hw4.Literal;
import hw4.AopAdd;
import hw4.AssignmentInstruction;
import hw4.BlockInstruction;
import hw4.Identifier;
import hw4.RopLessThan;
import hw4.WhileInstruction;
import api.Expression;

public class ExpressionsTests {

	public static void main(String[] args) {
		
		
//		//OutputInstructions test
//		Expression e = new AopAdd(new Literal(2), new Literal(3));
//		OutputInstruction out = new OutputInstruction(e);
//		ProgramNode test = out.getChild(0); // child 0 should be e
//		System.out.println(e == test); // expected true
//		out.execute(new Scope()); // expected: 5 printed to console
		
		//WhileInstruction tests
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
		Scope env = new Scope();
		((Instruction) main).execute(env);
		
		
	}

}
