package discount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BuyXGetYDiscountTest {

    private static final String MIN_NUM_ARTICLES_ERROR = "The minimum number of articles at least must be 1";
    private static final String NUM_ARTICLES_TO_PAY_ERROR = "Articles to pay must be higher than the number of articles to buy";
    private static final String MIN_NUM_TO_PAY_ERROR = "The number of articles to pay at least must be 0";
    
    @Test
    public void get2pay1Test(){
        Discount discount = new BuyXGetYDiscount(2,1);
        assertEquals(discount.apply(2), 1);
        assertEquals(discount.apply(5), 3);
        assertEquals(discount.apply(1), 1);
    }

    @Test
    public void get3pay2Test(){
        Discount discount = new BuyXGetYDiscount(3,2);
        assertEquals(discount.apply(3), 2);
        assertEquals(discount.apply(5), 4);
        assertEquals(discount.apply(2), 2);
    }

    @Test
    public void get8pay3Test(){
        Discount discount = new BuyXGetYDiscount(8,3);
        assertEquals(discount.apply(8), 3);
        assertEquals(discount.apply(15), 10);
        assertEquals(discount.apply(7), 7);
    }

    @Test
    public void wrongMinNumArticles(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new BuyXGetYDiscount(0,3));
        assertEquals(exception.getMessage(), MIN_NUM_ARTICLES_ERROR);
    }

    @Test
    public void wrongMinNumLessThanNumToPay(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new BuyXGetYDiscount(1,3));
        assertEquals(exception.getMessage(), NUM_ARTICLES_TO_PAY_ERROR);
    }

    @Test
    public void wrongMinNumToPay(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            new BuyXGetYDiscount(2,-1));
        assertEquals(exception.getMessage(), MIN_NUM_TO_PAY_ERROR);
    }
}