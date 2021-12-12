package product;

import discount.Discount;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @NonNull
    private String code;
    private String name;
    private double price;
}
