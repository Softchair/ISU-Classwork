package lab4a;

import static org.junit.Assert.*;

import java.awt.Point;

import static org.hamcrest.CoreMatchers.*;
import static lab4a.ConstrainsSidesTo.constrainsSidesTo;
import org.junit.*;
public class RectangleTest {
	private Rectangle rectangle;
	@After
	public void ensureInvariant() {
		 assertThat(rectangle, constrainsSidesTo(100));
	}

	@Test
	public void answersArea() throws Exception {
		rectangle = new Rectangle(new Point(5, 5), new Point (15, 10));
		assertThat(rectangle.area(), equalTo(50));
	}

	@Test
	public void allowsDynamicallyChangingSize() throws Exception {
		try {
			rectangle = new Rectangle(new Point(5, 5));
			rectangle.setOppositeCorner(new Point(130, 130));
			assertThat(rectangle.area(), equalTo(15625));
		} catch (Exception e) {
			System.out.println(1);
		}
	}
}