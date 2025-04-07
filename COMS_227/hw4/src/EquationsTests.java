import api.Expression;
import api.Scope;
import hw4.AopAdd;
import hw4.AopDivide;
import hw4.AopMultiply;
import hw4.AopSubtract;
//import hw4.AssignmentInstruction;
//import hw4.Identifier;
import hw4.Literal;
import hw4.LopAnd;
//import util.ParserUtil;
//import viewer.TreeViewer;
import hw4.LopOr;
import hw4.RopEqual;
import hw4.RopLessThan;
import util.ParserUtil;
import viewer.TreeViewer;

public class EquationsTests {

	public static void main(String[] args) {
		
		String t = "(2 + 3) < 4 || !(5 == -(6 * 7))";
		Expression ee = ParserUtil.parseExpression(t);
		// optionally, start the tree viewer
		TreeViewer.start(ee);
		System.out.println("Value: " + ee.eval(new Scope()));
		System.out.println();
		
		
		//AopAdd tests
		Expression e0 = new Literal(3);
		Expression e1 = new Literal(42);
		Expression e = new AopAdd(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 45
		e = new AopAdd(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 45
		e = new AopAdd(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 84
		e = new AopAdd(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 6
		System.out.println();
		
		//AopSubtract tests
		e0 = new Literal(5);
		e1 = new Literal(10);
		e = new AopSubtract(e0, e1);
		System.out.println(e.eval(new Scope())); //expected -5
		e = new AopSubtract(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 5
		e = new AopSubtract(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new AopSubtract(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		System.out.println();
		
		//AopMultiply tests
		e0 = new Literal(3);
		e1 = new Literal(5);
		e = new AopMultiply(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 15
		e = new AopMultiply(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 15
		e = new AopMultiply(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 25
		e = new AopMultiply(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 9
		System.out.println();
		
		//AopDivide tests
		e0 = new Literal(10);
		e1 = new Literal(5);
		e = new AopDivide(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 2
		e0 = new Literal(5);
		e1 = new Literal(25);
		e = new AopDivide(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 5
		e = new AopDivide(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new AopDivide(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 1
		System.out.println();
		
		//LopAnd tests
		e0 = new Literal(0);
		e1 = new Literal(42);
		e = new LopAnd(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new LopAnd(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new LopAnd(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new LopAnd(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		System.out.println();
		
		//LopOr tests
		e0 = new Literal(0);
		e1 = new Literal(5);
		e = new LopOr(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new LopOr(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new LopOr(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new LopOr(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		System.out.println();
		
		//RopEqual tests
		e0 = new Literal(0);
		e1 = new Literal(5);
		e = new RopEqual(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new RopEqual(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new RopEqual(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new RopEqual(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 1
		System.out.println();
		
		//RopLessThan tests
		e0 = new Literal(1);
		e1 = new Literal(5);
		e = new RopLessThan(e0, e1);
		System.out.println(e.eval(new Scope())); //expected 1
		e = new RopLessThan(e1, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new RopLessThan(e1, e1);
		System.out.println(e.eval(new Scope())); //expected 0
		e = new RopLessThan(e0, e0);
		System.out.println(e.eval(new Scope())); //expected 0
		
	}

}
