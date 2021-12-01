package discount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountByPercentageTest {
    @Test
    public void fiftyPerentOffTest(){
        Discount discount = new DiscountByPercentage(4, 0.5f);
        assertEquals(discount.apply(2), 2);
        assertEquals(discount.apply(5), 2.5);
        assertEquals(discount.apply(6), 3);
    }

}
