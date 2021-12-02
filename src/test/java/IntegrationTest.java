import pricing.PricingService;
import pricing.PricingServiceImpl;
import basket.Cart;
import basket.ShoppingCart;
import discount.BuyXGetYDiscount;
import discount.Discount;
import discount.DiscountByPercentage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import product.Product;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IntegrationTest {

    private static Product grenTea;
    private static Product coffe;
    private static Product strawberries;

    private static PricingService pricingService;
    private static  Map<String, Discount> discountByProduct = new HashMap<>();



    @BeforeAll
    public static void setUp(){
        Discount buy2pay1 = new BuyXGetYDiscount(2, 1);
        Discount buy3pay2 = new BuyXGetYDiscount(3, 2);
        Discount percentageDiscount = new DiscountByPercentage(3, 0.9f);
        grenTea = Product.builder().code("GR1").name("Green tea").price(3.11).build();
        strawberries = Product.builder().code("SR1").name("Strawberries").price(5.00).build();
        coffe = Product.builder().code("CF1").name("Coffee").price(11.23).build();


        discountByProduct.put("GR1", buy2pay1);
        discountByProduct.put("SR1", percentageDiscount);
        discountByProduct.put("CF1", buy3pay2);


        pricingService = new PricingServiceImpl();


    }

    @Test
    public void Test_1(){
        //GR1,SR1,GR1,GR1,CF1
        Cart shoppingCar = new ShoppingCart(discountByProduct, pricingService);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(coffe);

        assertEquals(shoppingCar.getTotalPrice(), 22.45);

    }

    @Test
    public void Test_2(){
        //GR1,GR1
        Cart shoppingCar = new ShoppingCart(discountByProduct, pricingService);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(grenTea);
        assertEquals(shoppingCar.getTotalPrice(), 3.11);

    }

    @Test
    public void Test_3(){
        //SR1,SR1,GR1,SR1
        Cart shoppingCar = new ShoppingCart(discountByProduct, pricingService);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(strawberries);

        assertEquals(shoppingCar.getTotalPrice(), 16.61);

    }

    @Test
    public void Test_4(){
        // GR1,CF1,SR1,CF1,CF1
        Cart shoppingCar = new ShoppingCart(discountByProduct, pricingService);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(coffe);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(coffe);
        shoppingCar.addProduct(coffe);

        assertEquals(shoppingCar.getTotalPrice(), 30.57);

    }
}
