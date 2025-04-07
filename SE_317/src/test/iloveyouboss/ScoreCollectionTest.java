package iloveyouboss;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ScoreCollectionTest {

    @Test
    public void arithmeticMean() throws Exception {
    }

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        //Arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        //Act
        int actualResult = collection.arithmeticMean();

        //Assert
        assertThat(actualResult, equalTo(6));
    }
}