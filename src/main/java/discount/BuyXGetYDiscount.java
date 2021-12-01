package discount;

import lombok.AllArgsConstructor;

/**
 * Implementation of discount interface.
 * Contains the rules to apply buy x and get y free (for example 3x2)
 * The class has two attributes, one of them set the number of articles that we have to buy, the other, the number
 * of articles that we have to pay
 */
@AllArgsConstructor
public class BuyXGetYDiscount implements Discount {

    private final int numArticles;
    private final int numToPay;

    private boolean checkDiscount(int quantity) {
        return numArticles <= quantity;
    }

    /**
     * Method that calculates the ratio to apply to our quantity based in the attributes and quantity of items.
     * If quantity is less than our numArticles required to apply the offer, it returns the same quantity.
     * If quantity is more than our numArticles it calculates the ratio
     * @param quantity number of items
     * @return number of items with the offer
     */
    @Override
    public double apply(int quantity) {
        return checkDiscount(quantity) ? ((quantity / numArticles) * numToPay + quantity % numArticles) : quantity;

    }
}
