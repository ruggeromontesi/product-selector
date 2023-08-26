package com.seb.homework.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.product.create.CreateProductController;
import seb.homework.productselector.product.create.CreateProductUseCase;
import seb.homework.productselector.product.select.SelectProductController;
import seb.homework.productselector.verifier.ProductsSelectionAdviser;

@Configuration
public class RestControllerConfig {

   @Bean
   public CreateProductController createProductController (CreateProductUseCase productCreator) {
      return new CreateProductController(productCreator);
   }

   @Bean
   public SelectProductController selectProductController (ProductsSelectionAdviser adviser) {
      return new SelectProductController(adviser);
   }
}
