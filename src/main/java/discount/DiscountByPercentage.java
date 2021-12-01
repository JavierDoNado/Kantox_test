package discount;

import lombok.AllArgsConstructor;

/**
 * Implementation of discount interface.
 * Contains the rules to apply a discount by percentage.
 * The class has two attributes, one of them set the minimum number of articles needed to apply the discount
 * and the other the percentage of discount.
 */
@AllArgsConstructor
public class DiscountByPercentage implements Discount {

    private final int minNumUnits;
    private final float percentageDiscount;

    private boolean checkDiscount(int quantity) {
        return minNumUnits<=quantity;
    }

    /**
     * If there are enough items, it applies the percentage of discount. Then, return the ratio to apply
     * @param quantity number of items
     * @return ratio to apply to price
     */
    @Override
    public double apply(int quantity) {
        return checkDiscount(quantity) ? quantity * percentageDiscount : quantity;
    }
}
