package seb.homework.productselector.domain.product;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerInfoDto {
   Integer age;
   Integer income;
   boolean student;

}
