### Explanation

My approach is based on the relationship between products and discounts.
Two types of discounts have been implemented based on requirements and that are customizable based on given parameters.
These two implementations are based on buy x and pay for y, and a percentage discount on the price with a minimum of items (for example 3).


On the other hand, there is the shopping cart that maintains for each product code a status of the quantity, total price and product unitary price.
If a product obtains another type of discount, the basket automatically recalculates the price of the basket with the new associated discount.

On the other hand, I have dispensed with frameworks like spring as much as possible. That according to points of view may be more or less correct.

