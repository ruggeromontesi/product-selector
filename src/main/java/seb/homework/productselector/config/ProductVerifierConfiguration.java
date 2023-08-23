package seb.homework.productselector.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seb.homework.productselector.domain.product.find.FindProductPort;
import seb.homework.productselector.domain.product.find.FindProductUseCase;
import seb.homework.productselector.domain.product.find.ProductFinder;
import seb.homework.productselector.domain.verifier.ProductsSelectionAdviser;

@Configuration
public class ProductVerifierConfiguration {

   @Bean
   public ProductsSelectionAdviser productFinder(FindProductUseCase productUseCase) {
      return new ProductsSelectionAdviser(productUseCase);
   }

   @Bean
   public FindProductUseCase findProductUseCase(FindProductPort findProductPort) {
      return new ProductFinder(findProductPort);
   }

}

