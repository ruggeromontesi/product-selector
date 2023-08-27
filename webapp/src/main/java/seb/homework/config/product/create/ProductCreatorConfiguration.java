package seb.homework.config.product.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.product.create.CreateProductJdbcAdapter;
import seb.homework.productselector.product.create.CreateProductUseCase;
import seb.homework.productselector.product.create.ProductCreator;

@Configuration
public class ProductCreatorConfiguration {

   @Bean
   public CreateProductUseCase createProductUseCase(CreateProductJdbcAdapter createProductJdbcAdapter) {
      return new ProductCreator(createProductJdbcAdapter);
   }
}
