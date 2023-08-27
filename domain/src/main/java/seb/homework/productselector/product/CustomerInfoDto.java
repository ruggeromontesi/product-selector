package seb.homework.productselector.product;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CustomerInfoDto {
   @NonNull
   Integer age;
   @NonNull
   Integer income;
   boolean student;
}
