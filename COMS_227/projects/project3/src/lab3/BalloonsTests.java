package lab3;

import balloon4.Balloon;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BalloonsTests {

	private Balloon b;
	
	@Before
	public void setup() {
		b = new Balloon(10);
	}
	
	@Test
    public void testInitialRadius()
    {
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testDoubleBlow()
    {
		b.blow(3);
		b.blow(3);
		assertEquals(6, b.getRadius());
    }
	
	@Test
    public void testPopBlowAfter()
    {
		b.pop();
		b.blow(5);
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testDeflateIsPop()
    {
		b.blow(3);
		b.deflate();
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testPopBlow()
    {
		b.blow(5);
		b.pop();
		b.blow(3);
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testBlowDeflateBlow()
    {
		b.blow(5);
		b.deflate();
		b.blow(3);
		assertEquals(3, b.getRadius());
    }
	
	@Test
    public void testIsPopped()
    {
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testIsPoppedBlow()
    {
		b.blow(5);
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testDeflate()
    {
		b.blow(3);
		b.deflate();
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testPopRadius()
    {
		b.blow(5);
		b.pop();
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testDeflateRadius()
    {
		b.blow(5);
		b.deflate();
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testBlow10()
    {
		b.blow(10);
		assertEquals(10, b.getRadius());
    }

	@Test
    public void testBlowPop()
    {
		b.blow(5);
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testBlow()
    {
		b.blow(5);
		assertEquals(5, b.getRadius());
    }
	
	@Test
    public void testIsDeflated()
    {
		b.blow(5);
		b.deflate();
		assertEquals(0, b.getRadius());
    }

	@Test
    public void testOverInflate()
    {
		b.blow(11);
		assertEquals(true, b.isPopped());
    }
}
