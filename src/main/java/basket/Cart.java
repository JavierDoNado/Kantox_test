package basket;

import product.Product;

import java.util.Map;

public interface Cart {
    Map<String, CartItem> addProduct(Product product);
    double getTotalPrice();
}
