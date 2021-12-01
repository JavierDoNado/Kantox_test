import basket.Cart;
import basket.ShoppingCart;
import discount.BuyXGetYDiscount;
import discount.Discount;
import discount.DiscountByPercentage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import product.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    private static Discount buy2pay1;
    private static Discount buy3pay2;
    private static Discount percentageDiscount;

    private static Product grenTea;
    private static Product coffe;
    private static Product strawberries;



    @BeforeAll
    public static void setUp(){
        buy2pay1 = new BuyXGetYDiscount(2,1);
        grenTea = new Product("GR1", "Green tea", 3.11, buy2pay1);
        percentageDiscount = new DiscountByPercentage(3,0.9f);
        strawberries = new Product("SR1", "Strawberries", 5.00, percentageDiscount);
        buy3pay2 = new BuyXGetYDiscount(3,2);
        coffe = new Product("CF1", "Coffee", 11.23, buy3pay2);

    }

    @Test
    public void Test_1(){
        //GR1,SR1,GR1,GR1,CF1
        Cart shoppingCar = new ShoppingCart();
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
        Cart shoppingCar = new ShoppingCart();
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(grenTea);
        assertEquals(shoppingCar.getTotalPrice(), 3.11);

    }

    @Test
    public void Test_3(){
        //SR1,SR1,GR1,SR1
        Cart shoppingCar = new ShoppingCart();
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(strawberries);

        assertEquals(shoppingCar.getTotalPrice(), 16.61);

    }

    @Test
    public void Test_4(){
        // GR1,CF1,SR1,CF1,CF1
        Cart shoppingCar = new ShoppingCart();
        shoppingCar.addProduct(grenTea);
        shoppingCar.addProduct(coffe);
        shoppingCar.addProduct(strawberries);
        shoppingCar.addProduct(coffe);
        shoppingCar.addProduct(coffe);

        assertEquals(shoppingCar.getTotalPrice(), 30.57);

    }
}
