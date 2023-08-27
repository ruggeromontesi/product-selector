package seb.homework.config.product.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import seb.homework.productselector.product.create.CreateProductUseCase;
import seb.homework.productselector.product.create.ProductCreator;
import seb.homework.productselector.product.create.CreateProductJdbcAdapter;

@Configuration
public class ProductCreatorConfiguration {

   @Bean
   public CreateProductUseCase createProductUseCase(CreateProductJdbcAdapter createProductJdbcAdapter) {
      return new ProductCreator(createProductJdbcAdapter);
   }

   @Bean
   public CreateProductJdbcAdapter createProductJdbcAdapter(NamedParameterJdbcTemplate jdbc) {
      return new CreateProductJdbcAdapter(jdbc);
   }
}
