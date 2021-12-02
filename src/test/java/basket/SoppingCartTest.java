package basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pricing.PricingService;
import discount.Discount;
import product.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SoppingCartTest {


    @Mock
    private PricingService pricingService;

    @Mock
    private Discount discount;

    @Mock
    private Discount discount2;

    @InjectMocks
    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp(){
        Map<String, Discount> discountByProduct = new HashMap<>();
        discountByProduct.put("GR1", discount);
        shoppingCart = new ShoppingCart(discountByProduct, pricingService);
    }

    @Test
    public void addProductTest(){
        Map<String, CartItem> basket;
        Product grenTea = Product.builder().code("GR1").name("Green tea").price(3.11).build();
        shoppingCart.addProduct(grenTea);
        basket = shoppingCart.addProduct(grenTea);
        assertFalse(basket.isEmpty());
        assertEquals(basket.get("GR1").getQuantity(),2);
    }

    @Test
    public void totalPriceTest() {
        Product grenTea = Product.builder().code("GR1").name("Green tea").price(3.11).build();
        when(pricingService.calculatePrice(3.11, 2,discount)).thenReturn(6.22);
        when(pricingService.calculatePrice(3.11, 1,discount)).thenReturn(3.11);
        shoppingCart.addProduct(grenTea);
        shoppingCart.addProduct(grenTea);

        assertEquals(shoppingCart.getTotalPrice(),6.22);

    }

    @Test void changeDiscountTest(){

        Product grenTea = Product.builder().code("GR1").name("Green tea").price(3.11).build();
        when(pricingService.calculatePrice(3.11, 1,discount)).thenReturn(3.11);
        when(pricingService.calculatePrice(3.11, 1,discount2)).thenReturn(2.1);

        shoppingCart.addProduct(grenTea);
        assertEquals(shoppingCart.getTotalPrice(),3.11);
        shoppingCart.changeDiscount("GR1", discount2);
        assertEquals(shoppingCart.getTotalPrice(),2.1);

    }

}
