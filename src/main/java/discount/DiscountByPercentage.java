package discount;

/**
 * Implementation of discount interface.
 * Contains the rules to apply a discount by percentage.
 * The class has two attributes, one of them set the minimum number of articles needed to apply the discount
 * and the other the percentage of discount.
 */
public class DiscountByPercentage implements Discount {

    private static final Integer MIN_NUM_ARTICLES = 1;
    private static final Float MIN_PERCENTAGE= 0.0f;
    private static final Float MAX_PERCENTAGE= 1.0f;
    private static final String PERCENTAGE_ERROR= "Percentage discount must be between 0.0 and 1.0";
    private static final String MIN_NUM_ARTICLES_ERROR = "The minimum number of articles at least must be 1";

    private final int minNumUnits;
    private final float percentageDiscount;

    public DiscountByPercentage(int minNumUnits, float percentageDiscount){
        if(minNumUnits<MIN_NUM_ARTICLES){
            throw new IllegalArgumentException(MIN_NUM_ARTICLES_ERROR);
        }
        if(percentageDiscount<=MIN_PERCENTAGE || percentageDiscount>=MAX_PERCENTAGE){
            throw new IllegalArgumentException(PERCENTAGE_ERROR);
        }
        this.minNumUnits = minNumUnits;
        this.percentageDiscount = percentageDiscount;
    }

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
