package discount;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuyXGetYDiscount implements Discount {

    private final int numArticles;
    private final int numFree;

    private boolean checkDiscount(int quantity) {
        return numArticles <= quantity;
    }

    @Override
    public double apply(int quantity) {
        return checkDiscount(quantity) ? ((quantity / numArticles) * numFree + quantity % numArticles) : quantity;

    }
}
