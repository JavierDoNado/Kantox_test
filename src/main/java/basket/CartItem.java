package basket;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CartItem {
    private  String productName;
    private  int quantity;
    private  double totalPrice;
}
