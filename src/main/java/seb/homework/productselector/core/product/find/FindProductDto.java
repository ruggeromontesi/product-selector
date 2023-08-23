package seb.homework.productselector.core.product.find;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FindProductDto {
   String name;
   Integer minAge;
   Integer maxAge;
   Integer minIncome;
   Integer maxIncome;
   Boolean mustBeStudent;
}
