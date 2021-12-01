package basket;

import discount.Discount;
import product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implementation of Cart. This class has the management of the basket and the products
 * When a product is added to the basket, the price is calculated dynamically applying, if it is possible
 * the discount related with the product.
 * If, for some reason, a product changes its discount rule, the price total of the item related with that product,
 * also will change
 */
public class ShoppingCart implements Cart {

    private static final Integer DIGITS = 2;

    private final Map<String, CartItem> basket = new LinkedHashMap<>();

    /**
     * Add a product to the basket
     * @param product Product to add
     * @return the updated basket
     */
    @Override
    public Map<String, CartItem> addProduct(Product product) {
        CartItem cartInfo = basket.getOrDefault(product.getCode(),
                CartItem.builder().quantity(0).productName(product.getName()).totalPrice(0.0).build());
        increaseCartInfo(cartInfo, product);
        basket.put(product.getCode(), cartInfo);
        return basket;
    }

    private void increaseCartInfo(CartItem cartInfo, Product product){
        cartInfo.setQuantity(cartInfo.getQuantity()+1);
        cartInfo.setTotalPrice(getCartItemPrice(cartInfo.getQuantity(), product));
    }

    private Double getCartItemPrice(int quantity, Product product){
        Discount discount = product.getDiscount();
        return Objects.nonNull(discount) ?
                discount.apply(quantity) * product.getPrice() :
                product.getPrice() * quantity;
    }

    /**
     * Get the total price of the items of our basket
     * @return price rounded with 2 decimals
     */
    @Override
    public double getTotalPrice() {
        BigDecimal result = BigDecimal.valueOf(basket.values().stream().mapToDouble(CartItem::getTotalPrice).sum());
        result = result.setScale(DIGITS, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}
