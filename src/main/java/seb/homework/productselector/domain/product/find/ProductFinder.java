package seb.homework.productselector.domain.product.find;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductFinder implements FindProductUseCase {

   private final FindProductPort findProductPort;

   @Override
   public List<FindProductDto> findAll() {
      return findProductPort.findAll();
   }
}
