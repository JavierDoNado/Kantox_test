package discount;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountByPercentage implements Discount {

    private final int minNumUnits;
    private final float percentageDiscount;

    private boolean checkDiscount(int quantity) {
        return minNumUnits<=quantity;
    }

    @Override
    public double apply(int quantity) {
        return checkDiscount(quantity) ? quantity * percentageDiscount : quantity;
    }
}
