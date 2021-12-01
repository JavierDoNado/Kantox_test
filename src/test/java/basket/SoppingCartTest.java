package basket;

import org.junit.jupiter.api.Test;
import product.Product;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SoppingCartTest {

    private Cart shoppingCart;

    @Test
    public void addProductTest(){
        shoppingCart = new ShoppingCart();
        Map<String, CartItem> basket;
        Product grenTea = new Product("GR1", "Green tea", 3.11, null);
        shoppingCart.addProduct(grenTea);
        basket = shoppingCart.addProduct(grenTea);
        assertFalse(basket.isEmpty());
        assertEquals(basket.get("GR1").getQuantity(),2);
    }

    @Test
    public void totalPriceTest() {
        shoppingCart = new ShoppingCart();

        Product grenTea = new Product("GR1", "Green tea", 3.11, null);
        shoppingCart.addProduct(grenTea);
        grenTea = new Product("GR1", "Green tea", 3.11, null);
        shoppingCart.addProduct(grenTea);

        assertEquals(shoppingCart.getTotalPrice(),6.22);

    }

}
