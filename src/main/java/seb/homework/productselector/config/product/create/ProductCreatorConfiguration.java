package seb.homework.productselector.config.product.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.core.product.create.CreateProductUseCase;
import seb.homework.productselector.core.product.create.ProductCreator;
import seb.homework.productselector.db.product.create.CreateProductJdbcAdapter;

@Configuration
public class ProductCreatorConfiguration {

   @Bean
   public CreateProductUseCase createProductUseCase(CreateProductJdbcAdapter createProductJdbcAdapter) {
      return new ProductCreator(createProductJdbcAdapter);
   }
}
