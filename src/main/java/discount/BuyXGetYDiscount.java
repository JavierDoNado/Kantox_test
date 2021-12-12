package discount;


/**
 * Implementation of discount interface.
 * Contains the rules to apply buy x and get y free (for example 3x2)
 * The class has two attributes, one of them set the number of articles that we have to buy, the other, the number
 * of articles that we have to pay
 */
public class BuyXGetYDiscount implements Discount {

    private static final Integer MIN_NUM_ARTICLES = 1;
    private static final Integer MIN_NUM_TO_PAY= 0;
    private static final String MIN_NUM_ARTICLES_ERROR = "The minimum number of articles at least must be 1";
    private static final String NUM_ARTICLES_TO_PAY_ERROR = "Articles to pay must be higher than the number of articles to buy";
    private static final String MIN_NUM_TO_PAY_ERROR = "The number of articles to pay at least must be 0";

    private final int numArticles;
    private final int numToPay;

    public BuyXGetYDiscount(int numArticles, int numToPay){
        if(numArticles<MIN_NUM_ARTICLES){
            throw new IllegalArgumentException(MIN_NUM_ARTICLES_ERROR);
        }
        if(numToPay<MIN_NUM_TO_PAY){
            throw new IllegalArgumentException(MIN_NUM_TO_PAY_ERROR);
        }
        if(numArticles<=numToPay){
            throw new IllegalArgumentException(NUM_ARTICLES_TO_PAY_ERROR);
        }
        this.numArticles = numArticles;
        this.numToPay = numToPay;
    }

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
