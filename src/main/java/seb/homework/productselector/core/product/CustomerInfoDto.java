package seb.homework.productselector.core.product;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class CustomerInfoDto {
   Integer age;
   Integer income;
   boolean student;

}
