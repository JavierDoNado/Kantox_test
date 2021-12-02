package basket;

import discount.Discount;
import pricing.PricingService;
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

    private final Map<String, Discount> discountRelation;

    private final PricingService pricingService;

    public ShoppingCart(Map<String, Discount> discountRelation, PricingService pricingService){
        this.discountRelation = discountRelation;
        this.pricingService = pricingService;
    }

    @Override
    public void changeDiscount(String productCode, Discount discount){
        discountRelation.put(productCode, discount);
        reCalculatePrice(productCode);
    }

    private void reCalculatePrice(String productCode){
        CartItem cartItem = basket.get(productCode);
        if(Objects.nonNull(cartItem)){
            cartItem.setTotalPrice(getCartItemPrice(cartItem, productCode));
            basket.put(productCode, cartItem);
        }
    }

    /**
     * Add a product to the basket
     * @param product Product to add
     * @return the updated basket
     */
    @Override
    public Map<String, CartItem> addProduct(Product product) {
        CartItem cartInfo = basket.getOrDefault(product.getCode(),
                CartItem.builder().quantity(0).priceByUnit(product.getPrice()).totalPrice(0.0).build());
        increaseCartInfo(cartInfo, product);
        basket.put(product.getCode(), cartInfo);
        return basket;
    }

    private void increaseCartInfo(CartItem cartInfo, Product product){
        cartInfo.setQuantity(cartInfo.getQuantity()+1);
        cartInfo.setTotalPrice(getCartItemPrice(cartInfo, product.getCode()));
    }

    private Double getCartItemPrice(CartItem cartInfo, String product){
        return pricingService.calculatePrice(cartInfo.getPriceByUnit(), cartInfo.getQuantity(),
                discountRelation.getOrDefault(product, null));
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
