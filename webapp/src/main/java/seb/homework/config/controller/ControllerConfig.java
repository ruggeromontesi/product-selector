package seb.homework.config.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.ProductMvcController;
import seb.homework.productselector.verifier.ProductsSelectionAdviser;

@Configuration
public class ControllerConfig {

   @Bean
   public ProductMvcController productMvcController(ProductsSelectionAdviser productFinder) {
      return new ProductMvcController(productFinder);
   }
}
