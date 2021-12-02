package pricing;

import discount.Discount;

public interface PricingService {
    double calculatePrice(double price, int quantity, Discount discount);
}
