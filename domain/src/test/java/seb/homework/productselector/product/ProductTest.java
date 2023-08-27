package seb.homework.productselector.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class ProductTest {

   private static final String TEST_PRODUCT = "test product";
   private static final int MIN_AGE = 25;
   private static final int MAX_AGE = 45;
   private static final int MIN_INCOME = 2000;
   private static final int MAX_INCOME = 15000;
   private static final boolean MUST_BE_STUDENT = true;

   private Product product;

   @BeforeEach
   void setUp() {
      product = getProduct();
   }

   @Test
   void should_returnIsUserEligible() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(MIN_AGE + 4)
            .income(MIN_INCOME + 400)
            .student(MUST_BE_STUDENT)
            .build();

      assertTrue(product.isUserEligible(customerInfoDto));
   }

   private static Product getProduct() {
      return Product.builder()
            .name(TEST_PRODUCT)
            .minAge(MIN_AGE)
            .maxAge(MAX_AGE)
            .minIncome(MIN_INCOME)
            .maxIncome(MAX_INCOME)
            .mustBeStudent(MUST_BE_STUDENT)
            .build();
   }

   @Test
   void should_returnUserNotEligible_when_ageBelowMinimum() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(MIN_AGE - 1)
            .income(MAX_INCOME - 500)
            .student(MUST_BE_STUDENT)
            .build();

      assertFalse(product.isUserEligible(customerInfoDto));
   }

   @Test
   void should_returnUserNotEligible_when_ageAboveMaximum() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(MAX_AGE + 2)
            .income(MIN_INCOME + 300)
            .student(MUST_BE_STUDENT)
            .build();

      assertFalse(product.isUserEligible(customerInfoDto));
   }

   @Test
   void should_returnUserNotEligible_when_incomeBelowMinimum() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(30)
            .income(MIN_INCOME - 5)
            .student(MUST_BE_STUDENT)
            .build();

      assertFalse(product.isUserEligible(customerInfoDto));
   }

   @Test
   void should_returnUserNotEligible_when_incomeAboveMaximum() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(MIN_AGE + 4)
            .income(MAX_INCOME + 300)
            .student(MUST_BE_STUDENT)
            .build();

      assertFalse(product.isUserEligible(customerInfoDto));
   }

   @Test
   void should_returnUserNotEligible_when_studentConditionNotMet() {
      CustomerInfoDto customerInfoDto = CustomerInfoDto.builder()
            .age(MIN_AGE + 4)
            .income(MAX_INCOME + 300)
            .student(!MUST_BE_STUDENT)
            .build();

      assertFalse(product.isUserEligible(customerInfoDto));
   }
}