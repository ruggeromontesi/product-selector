package seb.homework.productselector.domain.verifier;

import lombok.RequiredArgsConstructor;
import seb.homework.productselector.domain.product.CustomerInfoDto;
import seb.homework.productselector.domain.product.Product;
import seb.homework.productselector.domain.product.find.FindProductDto;
import seb.homework.productselector.domain.product.find.FindProductUseCase;


import java.util.List;

@RequiredArgsConstructor
public class ProductsSelectionAdviser implements ProductsProviderUseCase {

   private final FindProductUseCase port;

   @Override
   public List<String> getProducts(CustomerInfoDto dto) {
      List<FindProductDto> products = port.findAll();

      return products.stream()
            .map(Product::from)
            .filter(p -> p.isUserEligible(dto))
            .map(Product::getName)
            .toList();
   }
}