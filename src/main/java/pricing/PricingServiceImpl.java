package pricing;

import discount.Discount;

import java.util.Objects;

public class PricingServiceImpl implements PricingService{

    @Override
    public double calculatePrice(double price, int quantity, Discount discount) {
        return Objects.nonNull(discount) ?
                discount.apply(quantity) * price :
                price * quantity;
    }
}
