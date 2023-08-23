package seb.homework.productselector.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.core.product.Product;
import seb.homework.productselector.core.product.verifier.ProductFinder;
import seb.homework.productselector.db.product.FindProductJdbcAdapter;

@Configuration
public class ProductVerifierConfiguration {

   @Bean
   public ProductFinder productFinder(FindProductJdbcAdapter findProductJdbcAdapter) {
      return new ProductFinder(findProductJdbcAdapter);
   }
}
