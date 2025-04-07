package lab4b;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SparseArrayTestClass {

    SparseArray array;

    @Before
    public void setup() {
        array = new SparseArray();
    }

    @Test
    public void handlesInsertionInDescendingOrder() {
        array.put(7, "seven");
        array.checkInvariants();
        array.put(6, "six");
        array.checkInvariants();
        assertThat(array.get(6), equalTo("six"));
        assertThat(array.get(7), equalTo("seven"));
    }

    @Test
    public void testInsertNullValue() {
        array.put(0, null);
        array.checkInvariants();
        assertEquals(0, array.size());
    }

    @Test
    public void testInsertReplaceValue() {
        array.put(6, "seis");
        array.put(6, "six");
        assertEquals("six", array.get(6));
    }


}
