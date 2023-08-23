package seb.homework.productselector.core.product.verifier;

import lombok.RequiredArgsConstructor;
import seb.homework.productselector.core.product.CustomerInfoDto;
import seb.homework.productselector.core.product.Product;
import seb.homework.productselector.db.product.FindProductJdbcAdapter;

import java.util.List;

@RequiredArgsConstructor
public class ProductFinder implements ProductsProviderUseCase {

   private final FindProductJdbcAdapter adapter;

   @Override
   public List<String> getProducts(CustomerInfoDto dto) {

      List<Product> products = adapter.findAll();
      return products.stream()
            .filter(p -> p.isUserEligible(dto))
            .map(Product::getName)
            .toList();
   }
}
