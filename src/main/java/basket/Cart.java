package basket;

import discount.Discount;
import product.Product;

import java.util.Map;

public interface Cart {
    Map<String, CartItem> addProduct(Product product);
    void changeDiscount(String productCode, Discount discount);
    double getTotalPrice();
}
