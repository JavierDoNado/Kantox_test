package basket;

import product.Product;

import java.util.Map;

public interface Cart {
    Map addProduct(Product product);
    double getTotalPrice();
}
