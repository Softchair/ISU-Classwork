package lab7.calculator;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class CalculatorTest {
	
	/**
	 * 
	 * BOTH OF THESE TESTS ARE BROKEN
	 */

	@Test
	public void testAddition() {
		Calculator calculator = new Calculator();

	    JButton seven = (JButton) calculator.getComponent("6");
	    JButton add = (JButton) calculator.getComponent(19);
	    JButton three = (JButton) calculator.getComponent(12);
	    JButton equals = (JButton) calculator.getComponent(24);
	    JButton displayField = (JButton) calculator.getComponent(1);

	    // Simulate user input: 7 + 3 =
	    seven.doClick();
	    add.doClick();
	    three.doClick();
	    equals.doClick();

	    // Verify that the result is correct
	    assertEquals("10.0", displayField.getText());
	}
	
	@Test
    public void testCalculator() throws Exception {
        Calculator calculator = new Calculator();
        Robot robot = new Robot();

        // Test addition
        robot.mouseMove(calculator.add.getX() + calculator.add.getWidth(), calculator.add.getY() + calculator.add.getHeight());
        robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(50000);
	}
	
}
	//SHE BROKEN
//	@Test
//    public void testCalculator2() throws Exception {
//		Calculator calculator = new Calculator();
//		Robot robot = new Robot();
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(calculator.displayField.getX() + calculator.displayField.getWidth() / 2, calculator.displayField.getY() + calculator.displayField.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.keyPress(KeyEvent.VK_1);
//        robot.keyRelease(KeyEvent.VK_1);
//        robot.mouseMove(calculator.add.getX() + calculator.add.getWidth() / 2, calculator.add.getY() + calculator.add.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(calculator.displayField.getX() + calculator.displayField.getWidth() / 2, calculator.displayField.getY() + calculator.displayField.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.keyPress(KeyEvent.VK_2);
//        robot.keyRelease(KeyEvent.VK_2);
//        robot.mouseMove(calculator.equalOut.getX() + calculator.equalOut.getWidth() / 2, calculator.equalOut.getY() + calculator.equalOut.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        assertEquals("Result should be 3", "3.0", calculator.equalOut.getText().substring(2));
//
//        // Test clearing the calculator
//        robot.mouseMove(calculator.displayField.getX() + calculator.displayField.getWidth() / 2, calculator.displayField.getY() + calculator.displayField.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(calculator.equalOut.getX() + calculator.equalOut.getWidth() / 2, calculator.equalOut.getY() + calculator.equalOut.getHeight() / 2);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        assertEquals("Result should be 0", "0.0", calculator.equalOut.getText().substring(2));
//    }
