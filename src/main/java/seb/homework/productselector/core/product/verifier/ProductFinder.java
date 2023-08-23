package seb.homework.productselector.core.product.verifier;

import seb.homework.productselector.core.product.CustomerInfoDto;
import seb.homework.productselector.core.product.Product;

import java.util.List;

public class ProductFinder implements ProductsProviderUseCase{

   private final List<Product> verifiers;

   public ProductFinder(List<Product> verifiers) {
      this.verifiers = verifiers;
   }

   @Override
   public List<String> getProducts(CustomerInfoDto dto) {
      return verifiers.stream()
            .filter(p -> p.isUserEligible(dto))
            .map(Product::getName)
            .toList();
   }
}
