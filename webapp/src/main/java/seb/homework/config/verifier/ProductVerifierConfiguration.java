package seb.homework.config.verifier;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import seb.homework.productselector.product.find.FindProductJdbcAdapter;
import seb.homework.productselector.product.find.FindProductUseCase;
import seb.homework.productselector.product.find.ProductFinder;
import seb.homework.productselector.verifier.ProductsSelectionAdviser;

@Configuration
public class ProductVerifierConfiguration {

   @Bean
   public ProductsSelectionAdviser productFinder(FindProductUseCase productUseCase) {
      return new ProductsSelectionAdviser(productUseCase);
   }

   @Bean
   public FindProductUseCase findProductUseCase(FindProductJdbcAdapter findProductJdbcAdapter) {
      return new ProductFinder(findProductJdbcAdapter);
   }

   @Bean
   public FindProductJdbcAdapter findProductJdbcAdapter(NamedParameterJdbcTemplate jdbcTemplate) {
      return new FindProductJdbcAdapter(jdbcTemplate);
   }

}

