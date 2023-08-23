package seb.homework.productselector.core.product.config;


import seb.homework.productselector.core.product.Product;
import seb.homework.productselector.core.product.verifier.ProductFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductVerifierConfiguration {

   private static final String CURRENT_ACCOUNT = "Current Account";
   private static final Product CURRENT_ACCOUNT_PRODUCT = Product.builder().name(CURRENT_ACCOUNT).minIncome(0).minAge(17).build();
   private static final String CURRENT_ACCOUNT_PLUS = "Current Account Plus";
   private static final Product CURRENT_ACCOUNT_PLUS_PRODUCT = Product.builder().name(CURRENT_ACCOUNT_PLUS).minIncome(40000).minAge(17).build();
   private static final String JUNIOR_SAVER_ACCOUNT = "Junior Saver Account";
   private static final Product JUNIOR_SAVER_ACCOUNT_PRODUCT = Product.builder().name(JUNIOR_SAVER_ACCOUNT).maxAge(18).build();
   private static final String STUDENT_ACCOUNT = "Student Account";
   private static final Product STUDENT_ACCOUNT_PRODUCT = Product.builder().name(STUDENT_ACCOUNT).minAge(17).mustBeStudent(true).build();
   private static final String SENIOR_ACCOUNT = "Senior Account";
   private static final Product SENIOR_ACCOUNT_PRODUCT = Product.builder().name(SENIOR_ACCOUNT).minAge(64).build();
   private static final String DEBIT_CARD = "Debit Card";
   private static final Product DEBIT_CARD_PRODUCT = Product.builder().name(DEBIT_CARD).maxIncome(12001).minAge(17).build();
   private static final String CREDIT_CARD = "Credit Card";
   private static final Product CREDIT_CARD_PRODUCT = Product.builder().name(CREDIT_CARD).minIncome(12000).minAge(17).build();
   private static final String GOLD_CREDIT_CARD = "Gold Credit Card";
   private static final Product GOLD_CREDIT_CARD_PRODUCT = Product.builder().name(GOLD_CREDIT_CARD).minIncome(40000).minAge(17).build();

   @Bean
   public ProductFinder productFinder() {
      return new ProductFinder(List.of(
            CURRENT_ACCOUNT_PRODUCT,
            CURRENT_ACCOUNT_PLUS_PRODUCT,
            JUNIOR_SAVER_ACCOUNT_PRODUCT,
            STUDENT_ACCOUNT_PRODUCT,
            SENIOR_ACCOUNT_PRODUCT,
            DEBIT_CARD_PRODUCT,
            CREDIT_CARD_PRODUCT,
            GOLD_CREDIT_CARD_PRODUCT
      ));
   }

   private static Product getCurrentAccount() {
      return Product.builder()
            .name("Current Account")
            .maxAge(17).build();
   }
}
