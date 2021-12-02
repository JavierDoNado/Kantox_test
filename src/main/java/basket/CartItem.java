package basket;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CartItem {
    private  double priceByUnit;
    private  int quantity;
    private  double totalPrice;
}
