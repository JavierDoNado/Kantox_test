package pricing;


import discount.Discount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceImplTest {

    @Mock
    private Discount discount;

    @InjectMocks
    private PricingServiceImpl pricingService;

    @Test
    public void priceServiceDiscountNullTest(){
        double priceMock = 2.1;
        int quantityMock = 1;
        assertEquals(pricingService.calculatePrice(priceMock, quantityMock, null), priceMock, 0.001);
    }

    @Test
    public void priceServiceDiscountTest(){
        double priceMock = 2.1;
        int quantityMock = 1;
        when(discount.apply(1)).thenReturn(2.0);
        assertEquals(pricingService.calculatePrice(priceMock, quantityMock, discount), priceMock*2, 0.001);
    }

}