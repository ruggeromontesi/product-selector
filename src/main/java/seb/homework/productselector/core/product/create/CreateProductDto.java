package seb.homework.productselector.core.product.create;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateProductDto {
   String name;
   Integer minAge;
   Integer maxAge;
   Integer minIncome;
   Integer maxIncome;
   Boolean mustBeStudent;
}
