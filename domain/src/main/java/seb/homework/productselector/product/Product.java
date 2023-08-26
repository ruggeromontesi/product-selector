package seb.homework.productselector.product;

import lombok.Builder;
import lombok.Data;
import seb.homework.productselector.product.find.FindProductDto;

@Builder
@Data
public class Product implements ProductSuitabilityUseCase{

   Long id;
   String name;
   Integer minAge;
   Integer maxAge;
   Integer minIncome;
   Integer maxIncome;
   Boolean mustBeStudent;


   @Override
   public boolean isUserEligible(CustomerInfoDto dto) {
      return isAgeSuitable(dto.getAge())
            && isIncomeSuitable(dto.getIncome())
            && checkStudentCondition(dto.isStudent());
   }

   private boolean isAgeSuitable(int age) {
      return (minAge == null || age > minAge) &&
            (maxAge == null || age < maxAge);
   }

   private boolean isIncomeSuitable(int income) {
      return (minIncome == null || income > minIncome) &&
            (maxIncome == null || income < maxIncome);
   }

   private boolean checkStudentCondition(boolean isStudent) {
      return mustBeStudent == null || !mustBeStudent || isStudent;
   }

   public static Product from(FindProductDto dto) {
      return Product.builder()
            .name(dto.getName())
            .minAge(dto.getMinAge())
            .maxAge(dto.getMaxAge())
            .minIncome(dto.getMinIncome())
            .maxIncome(dto.getMaxIncome())
            .mustBeStudent(dto.getMustBeStudent())
            .build();

   }
}
