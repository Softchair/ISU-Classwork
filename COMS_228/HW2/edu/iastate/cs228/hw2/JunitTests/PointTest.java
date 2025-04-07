package edu.iastate.cs228.hw2.JunitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.iastate.cs228.hw2.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point s = new Point(3, 5);

//    @BeforeEach
//    void setUp() {
//        Point s = new Point(3, 5);
//        s.setXorY(false);
//    }

    @Test
    void getX() {
        assertEquals(3, s.getX());
    }

    @Test
    void getY() {
        assertEquals(5, s.getY());
    }

    @Test
    void setXorY() {
        s.setXorY(false);
        s.setXorY(true);
        assertEquals(true, s.xORy);
    }

    @Test
    void testEqualsTrue() {
        Point d = new Point(3, 5);
        assertEquals(true, s.equals(d));
    }

    @Test
    void testEqualsFalse() {
        Point d = new Point(4, 5);
        assertEquals(false, s.equals(d));
    }

    @Test
    void compareTo1() {
        Point d = new Point(1, 2);
        assertEquals(1, s.compareTo(d));
    }

    @Test
    void compareTo0() {
        Point d = new Point(3, 5);
        assertEquals(0, s.compareTo(d));
    }

    @Test
    void compareToNEG1() {
        Point d = new Point(7, 10);
        assertEquals(-1, s.compareTo(d));
    }

    @Test
    void testToString() {
        assertEquals("(3, 5)", s.toString());
    }
}