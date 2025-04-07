package lab4a;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import lab4a.*;

public class BearingTest {
	@Test
	//public void answersValidBearing() throws Exception {
	public void answersValidBearing() {
		try {
			assertThat(new Bearing(Bearing.MAX).value(), equalTo(Bearing.MAX));
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}

	@Test
	//public void answersAngleBetweenItAndAnotherBearing() throws Exception {
	public void answersAngleBetweenItAndAnotherBearing() {
		try {
			assertThat(new Bearing(15).angleBetween(new Bearing(12)), equalTo(3));
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}

	@Test
	//public void angleBetweenIsNegativeWhenThisBearingSmaller() throws Exception {
	public void angleBetweenIsNegativeWhenThisBearingSmaller() {
		try {
			assertThat(new Bearing(12).angleBetween(new Bearing(15)), equalTo(-3));
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}

	//Testing boundaries of bearing
	@Test(expected = BearingOutOfRangeException.class)
	public void throwsExceptionWhenValueLessThanZero() throws Exception {
		Bearing b = new Bearing(-1);
	}

	//Testing MAX boundary
	@Test(expected = BearingOutOfRangeException.class)
	public void throwsExceptionWhenValueGreaterThanMax() throws Exception {
		Bearing b = new Bearing(Bearing.MAX + 1);
	}

	//Testing for same bearing angle
	@Test
	public void angleBetweenIsZero() throws Exception {
		assertThat(new Bearing(20).angleBetween(new Bearing(20)), equalTo(0));
	}

	//Testing when bearings are opposite
	@Test
	public void whenBearingsAreOpposite() throws Exception {
		assertThat(new Bearing(0).angleBetween(new Bearing(180)), equalTo(-180));
	}

	//Testing when bearings are consecutive
	@Test
	public void whenBearingsAreConsecutive() throws Exception {
		assertThat(new Bearing(0).angleBetween(new Bearing(1)), equalTo(-1));
	}

	//Testing for largest possible bearing angle
	@Test
	public void whenBearingsAre360Apart() throws Exception {
		assertThat(new Bearing(0).angleBetween(new Bearing(360)), equalTo(-360));
	}

	//Testing MAX bearing for angle between
	@Test
	public void angleBetweenIs0WhenMAX() throws	Exception {
		assertThat(new Bearing(Bearing.MAX).angleBetween(new Bearing(Bearing.MAX)), equalTo(0));
	}

	@Test
	public void angleBetweenIsNegative() throws Exception {
		assertThat(new Bearing(100).angleBetween(new Bearing(50)), equalTo(50));
	}
}
