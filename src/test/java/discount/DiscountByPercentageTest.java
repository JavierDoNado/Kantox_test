package discount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountByPercentageTest {

    private static final String PERCENTAGE_ERROR= "Percentage discount must be between 0.0 and 1.0";
    private static final String MIN_NUM_ARTICLES_ERROR = "The minimum number of articles at least must be 1";

    @Test
    public void fiftyPerentOffTest(){
        Discount discount = new DiscountByPercentage(4, 0.5f);
        assertEquals(discount.apply(2), 2);
        assertEquals(discount.apply(5), 2.5);
        assertEquals(discount.apply(6), 3);
    }

    @Test
    public void wrongMinNumArticles(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new DiscountByPercentage(0,0.5f));
        assertEquals(exception.getMessage(), MIN_NUM_ARTICLES_ERROR);
    }

    @Test
    public void wrongMinPercentage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new DiscountByPercentage(1,0.0f));
        assertEquals(exception.getMessage(), PERCENTAGE_ERROR);
    }

    @Test
    public void wrongMaxPercentage(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new DiscountByPercentage(1,1.0f));
        assertEquals(exception.getMessage(), PERCENTAGE_ERROR);
    }
}
