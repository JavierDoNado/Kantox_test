package discount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class BuyXGetYDiscountTest {
    
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
}